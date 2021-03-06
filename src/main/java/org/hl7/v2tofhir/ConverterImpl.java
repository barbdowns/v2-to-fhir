package org.hl7.v2tofhir;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import com.opencsv.bean.CsvToBeanBuilder;

public abstract class ConverterImpl<T extends Convertible> implements Converter {

    private static final String FHIR_BASE = "https://hl7.org/fhir/R4/", FHIR_TERM = "http://terminology.hl7.org/";
    private static int errCount = 0, warnCount = 0;

    public static class Row {
        String sourceCode;
        String sourceDisplay;
        String targetCode;
        String targetDisplay;
        String condition;
        String conditionDisplay;
        String dataType;
        String mapping;
        String comments;
    }

    private static Map<String,Pair<String,String>> fhirLinks = new TreeMap<>(),
        segmentLinks = new TreeMap<>(),
        tableLinks = new TreeMap<>(),
        dataTypeLinks = new TreeMap<>();

    private static Map<String, String> mappedLinks  = new HashMap<>();
    static {
        mappedLinks.put("Reference", FHIR_BASE + "references.html");
        mappedLinks.put("Meta", FHIR_BASE + "resource.html#Meta");
        mappedLinks.put("xhtml", FHIR_BASE + "narrative.html#xhtml");
        mappedLinks.put("Narrative", FHIR_BASE + "narrative.html#Narrative");
        mappedLinks.put("Extension", FHIR_BASE + "extensibility.html#Extension");
        mappedLinks.put("Dosage", FHIR_BASE + "dosage.html#Dosage");
        mappedLinks.put("ContactDetail", FHIR_BASE + "metadatatypes.html#ContactDetail");
        mappedLinks.put("Contributor", FHIR_BASE + "metadatatypes.html#Contributor");
        mappedLinks.put("DataRequirement", FHIR_BASE + "metadatatypes.html#DataRequirement");
        mappedLinks.put("RelatedArtifact", FHIR_BASE + "metadatatypes.html#RelatedArtifact");
        mappedLinks.put("UsageContext", FHIR_BASE + "metadatatypes.html#UsageContext");
        mappedLinks.put("ParameterDefinition", FHIR_BASE + "metadatatypes.html#ParameterDefinition");
        mappedLinks.put("Expression", FHIR_BASE + "metadatatypes.html#Expression");
        mappedLinks.put("TriggerDefinition", FHIR_BASE + "metadatatypes.html#TriggerDefinition");
    }

    protected String source, sourceName, qualifier, target, targetName;
    protected String parts[] = null;

    private String filename = null;
    private File theSource;
    private String type;
    private List<T> beans;
    private Class<T> classType;

    public ConverterImpl(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void load(File f) throws IOException {
        theSource = f;
        filename = f.getName();
        qualifier = StringUtils.substringBefore(StringUtils.substringAfter(filename,"["),"]");
        if (qualifier.length() > 0) {
            qualifier = "[" + qualifier.replace(" ", "") + "]";
        }
        parts = filename.split("\\s*[\\._\\-]\\s*");
        if (filename.contains("Message")) {
            type = "Message";
            source = sourceName = parts[3];
            target = targetName = "Bundle";
        } else if (filename.contains("Segment") &&
                  !filename.contains("Segment Action Code")  // Hack to fix the one file using the word segment that is vocab
            ) {
            type = "Segment";
            sourceName = StringUtils.substringBefore(parts[2], "[");
            targetName = parts[2];
        } else if (filename.contains("Data Type")) {
            type = "Datatype";
            sourceName = StringUtils.substringBefore(parts[2], "[");
            targetName = parts[2];
        } else if (filename.contains("Concept Map")) {
            type = "Table";
            sourceName = parts[1];
        } else {
        }

        try (FileReader r = new FileReader(f)) {
            beans = new CsvToBeanBuilder<T>(r).withType(classType).build().parse();
        }

        setNames();
    }

    public void setNames() {
        T first = getFirstMappedBean();

        if (first == null) {
            source = cleanId(sourceName);
            target = "Unknown";
            if (targetName == null) {
                targetName = "Unknown";
            }
        } else {
            Row r = first.convert();
            source = r.sourceCode;
            sourceName = r.sourceDisplay;
            target = r.targetCode;
            targetName = r.targetDisplay;
        }
    }

    protected final T getFirstMappedBean() {
        int count = 0;
        for (T bean : beans) {
            // skip the first two lines
            if (count++ < 2) {
                continue;
            }
            Row row = bean.convert();
            // If there is no conversion, go to the next line
            if (row == null) {
                continue;
            }
            return bean;
        }
        return null;
    }

    public String getSourceFileName() {
        return theSource.getPath();
    }

    public String getFishFileName() {
        return String.format("%s %s%s to %s.fsh", type, sourceName, qualifier, targetName);
    }

    public String getId() {
        return cleanId(String.format("%s-%s-to-%s",
            type.toLowerCase(), sourceName + qualifier, targetName));
    }

    public String getMdFileName(String prefix, String suffix) {
        return prefix + "ConceptMap-" + getId() + suffix + ".md";
    }

    public String getHtmlFileName() {
        return "ConceptMap-" + getId() + ".html";
    }

    @Override
    public void store(File loc) throws IOException {
        File f;
        File intro;
        File notes;
        String id = getId();
        String output = getFishFileName();
        if (loc.isDirectory()) {
            f = new File(loc, output);
        } else {
            f = loc;
        }
        intro = new File(getMdFileName("input/includes/", "-intro"));
        if (!intro.getParentFile().exists()) {
            intro.getParentFile().mkdirs();
        }
        notes = new File(getMdFileName("input/includes/", "-notes"));
        if (!notes.getParentFile().exists()) {
            notes.getParentFile().mkdirs();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(f));
            PrintWriter introWriter = new PrintWriter(new FileWriter(intro));
            PrintWriter notesWriter = new PrintWriter(new FileWriter(notes));) {
            pw.printf("Instance: %s%sto%s%n", type, sourceName.replaceAll(" ", ""), targetName.replaceAll(" ", ""));
            pw.println("InstanceOf: ConceptMap");
            pw.printf("Title: \"%s %s to %s Map\"%n", type, sourceName, targetName);
            pw.printf("* description = \"This ConceptMap represents the mapping from the HL7 V2 %s to the FHIR %s.\"%n",
                getV2Description(), getFHIRDescription());
            pw.printf("* id = \"%s\"%n", id);
            pw.printf("* url = \"http://hl7.org/fhir/v2-tofhir/%s\"%n", id);
            pw.println("* version = \"1.0\"");
            pw.printf("* name = \"%s_%s_Map\"%n", type, sourceName);
            pw.println("* status = #active");
            pw.println("* experimental = true");
            pw.printf("* date = \"%tF\"%n", new Date());
            pw.println("* publisher = \"HL7 International, Inc\"");
            pw.println("* contact.telecom.system = #email");
            pw.println("* contact.telecom.value = \"v2-to-fhir@lists.hl7.org\"");
            pw.println("* copyright = \"Copyright (c) 2020, HL7 International, Inc., All Rights Reserved.\"");
            pw.printf("* sourceUri = \"%s\"%n", source);
            pw.printf("* targetUri = \"%s\"%n", target);

            int count = 0;
            int mappedRows = 0;

            for (T bean : beans) {
                // skip the first two lines
                if (count++ < 2) {
                    continue;
                }
                // Convert the line
                Row row = bean.convert();

                // If there is no conversion, go to the next line
                if (row == null) {
                    continue;
                }

                pw.printf("* group.element[%d].code = #%s%n", mappedRows, row.sourceCode.trim());
                if (!StringUtils.isEmpty(row.sourceDisplay)) {
                    pw.printf("* group.element[%d].display = \"%s\"%n", mappedRows, escapeFshString(row.sourceDisplay));
                }

                pw.printf("* group.element[%d].target.equivalence = #%s%n", mappedRows,
                    StringUtils.isEmpty(row.targetCode) ? "unmatched" : "equivalent");

                if (!StringUtils.isEmpty(row.targetCode)) {
                    String targetCode = row.targetCode.contains("=")
                        ? escapeFshString(StringUtils.substringBefore(row.targetCode, "="))
                        : row.targetCode;
                    String targetDisplay = row.targetCode.contains("=")
                        ? "= " + StringUtils.substringAfter(row.targetCode, "=")
                        : row.targetDisplay;

                    String comments = StringUtils.defaultString(row.comments);
                    if (targetCode.contains(",") || targetCode.contains(" ")) {
                        String parts[] = targetCode.split("[, ]");
                        comments = targetCode.substring(parts[0].length()) + ".  " + comments;
                        targetCode = parts[0];
                    }

                    pw.printf("* group.element[%d].target.code = #%s%n", mappedRows, targetCode.trim());
                    if (!StringUtils.isEmpty(targetDisplay)) {
                        pw.printf("* group.element[%d].target.display = \"%s\"%n", mappedRows,
                            escapeFshString(targetDisplay));
                    }
                    if (!StringUtils.isEmpty(comments)) {
                        pw.printf("* group.element[%d].target.comment = \"%s\"%n", mappedRows,
                            escapeFshString(comments));
                    }

                    int dependencies = 0;
                    if (!StringUtils.isEmpty(row.mapping)) {
                        pw.printf("* group.element[%d].target.dependsOn[%d].property = \"%s\"%n", mappedRows,
                            dependencies, "ConceptMap");
                        pw.printf("* group.element[%d].target.dependsOn[%d].value = \"%s\"%n", mappedRows, dependencies,
                            escapeFshString(row.mapping));
                        dependencies++;
                    }

                    if (!StringUtils.isEmpty(row.condition) || !StringUtils.isEmpty(row.conditionDisplay)) {
                        pw.printf("* group.element[%d].target.dependsOn[%d].property = \"%s\"%n",
                            mappedRows, dependencies, "value");
                        if (!StringUtils.isBlank(row.condition)) {
                            pw.printf("* group.element[%d].target.dependsOn[%d].value = \"%s\"%n", mappedRows, dependencies,
                                escapeFshString(row.condition));
                        }
                        if (!StringUtils.isBlank(row.conditionDisplay)) {
                            pw.printf("* group.element[%d].target.dependsOn[%d].value = \"%s\"%n", mappedRows, dependencies,
                                escapeFshString(row.conditionDisplay));
                        }
                        dependencies++;
                    }
                }
                ++mappedRows;
            }

            if (introWriter != null) {
                introWriter.printf(
                    "%nThis ConceptMap represents the mapping from the HL7 V2 %s to the FHIR %s. "
                        + "See also the <a href='https://github.com/HL7/v2-to-fhir/blob/master/tank/%s'>FHIR Shorthand</a> or "
                        + "the <a href='https://github.com/HL7/v2-to-fhir/blob/master/mappings/%s'>CSV Source</a>.%n",
                        getV2Description(),
                        getFHIRDescription(),
                        output, StringUtils.substringAfterLast(theSource.getPath().replace("\\","/"), "mappings/"));

                writeIntro(beans, introWriter);
            }
            if (notesWriter != null) {
                // TBD: Address width and style hacks below
                notesWriter.printf("<div id=\"disqus_thread\" style=\"display: block; width: 640px\"></div>%n" +
                    "<script>%n" +
                    "var disqus_config = function () {%n" +
                    "this.page.url = \"http://build.fhir.org/hl7/v2-to-fhir/branches/master/%s\";  // Replace PAGE_URL with your page's canonical URL variable%n" +
                    "this.page.identifier = \"%s\"; // Replace PAGE_IDENTIFIER with your page's unique identifier variable%n" +
                    "};%n" +
                    "(function() { // DON'T EDIT BELOW THIS LINE%n" +
                    "var d = document, s = d.createElement('script');%n" +
                    "s.src = 'https://v2-to-fhir.disqus.com/embed.js';%n" +
                    "s.setAttribute('data-timestamp', +new Date());%n" +
                    "(d.head || d.body).appendChild(s);%n" +
                    "})();%n" +
                    "</script>%n" +
                    "<noscript>Please enable JavaScript to view the <a href=\"https://disqus.com/?ref_noscript\">comments powered by Disqus.</a></noscript>%n",
                    getHtmlFileName(), id
                    );
            }
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    private Object getFHIRDescription() {
        switch (type) {
        case "Table":
            return String.format("%s Value Set", getTargetName());
        case "Message":
            return String.format("Message Bundle", getTargetName());
        case "Segment":
            return String.format("%s Resource", getTargetName());
        case "Data Type":
            return String.format("%s", getTargetName());
        }
        return null;
    }

    private Object getV2Description() {
        switch (type) {
        case "Table":
            return String.format("Table %s", getSourceName());
        case "Message":
            return String.format("%s Message", getSourceName());
        case "Segment":
            return String.format("%s Segment", getSourceName());
        case "Data Type":
            return String.format("%s Data Type", getSourceName());
        }
        return null;
    }

    protected String escapeFshString(String comments) {
        String value = comments.replace("\r", "");
        // If there is a newline, use Sushi """ syntax
        if (value.contains("\n")) {
            value = "\"\"\n" + value.trim() + "\\\n\"\"";
        } else {
            // Only do quote escaping if not in a multiline string.
            value = value.replace("\"", "\\\"");
        }
        return value.trim();
    }

    private static Pattern LINK_PATTERN = Pattern.compile("http:|https:|ftp:|mailto:");

    protected String escapeHtmlString(String html) {
        if (html == null) {
            return "";
        }
        Matcher m = LINK_PATTERN.matcher(html);
        if (!m.find()) {
            return simpleEscapeHtmlString(html);
        }
        // OK, so we have some links in text that should be made into real links.
        StringBuilder links = new StringBuilder();
        int start = 0;
        while (start < html.length()) {
            int end = m.start();
            String chunk;
            if (end != 0) {
                chunk = html.substring(start, end);
                links.append(simpleEscapeHtmlString(chunk));
            }
            start = end;
            for (end = start; end < html.length(); end++) {
                int c = html.charAt(end);
                if (!Character.isLetterOrDigit(c) && "-+&@#/%?=~_|$!:,.;".indexOf(c) < 0)
                    break;
            }
            // End is either after the last character, or the character that doesn't match a URL character
            // NOTE: While ( and ) are legal in URLs, we are assuming they won't appear in text.

            chunk = html.substring(start, end);
            String addr = chunk;    // Assume address is same as chunk

            // Fix addr for missing protocol
            if (!addr.matches("^(http:|https:|ftp:|mailto:).*$")) {
                if (addr.contains("@")) {
                    addr = "mailto:" + addr;
                } else if (addr.startsWith("www")) {
                    addr = "http://" + addr;
                } else if (addr.startsWith("ftp")) {
                    addr = "ftp://" + addr;
                } else {
                    addr = "http://" + addr;
                }
            }
            links.append("<a href='" + escapeHtmlAttr(addr) + "'>")
                .append(simpleEscapeHtmlString(chunk))
                .append("</a>");
            start = end;
            if (!m.find()) {
                chunk = html.substring(start);
                links.append(simpleEscapeHtmlString(chunk));
                break;
            }
        }
        return links.toString();
    }

    protected String escapeHtmlAttr(String attr) {
        return simpleEscapeHtmlString(attr).replace("'", "&apos;").replace("\"", "&quot;");
    }

    protected String simpleEscapeHtmlString(String html) {
        return html.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    protected abstract void writeIntro(List<T> bean, PrintWriter introWriter);

    private static String cleanId(String name) {
        name = name.contains("/") ? StringUtils.substringAfterLast(name, "/") : name;
        return StringUtils.truncate(StringUtils.replaceChars(name.toLowerCase(), "! _[]", "---").replaceAll("--+", "-"),
            64);
    }

    @Override
    public String getSourceName() {
        return sourceName;
    }

    @Override
    public String getTargetName() {
        return targetName;
    }

    @Override
    public void store() throws IOException {
        store(new File("."));
    }

    @Override
    public String getQualifier() {
        return qualifier;
    }

    protected String makeFhirLink(String fhirCode) {

        if (StringUtils.isEmpty(fhirCode) || "N/A".equals(fhirCode.trim().toUpperCase())) {
            return fhirCode;
        }

        String fhirLinks[] = null;

        // Remove everything after the equals
        String myFhirCode = StringUtils.substringBefore(fhirCode, "=").trim();
        // Remove everything between [] inclusive
        myFhirCode = myFhirCode.replaceAll("\\[[^\\]]*\\]", "");
        // Split at ( and )
        fhirLinks = myFhirCode.split("[\\(\\)]");
        // Create a link for every part remaining.
        StringBuilder links = new StringBuilder();
        String name = null;
        int pos = 0;
        for (String fhirLink: fhirLinks) {
            if (fhirLink == null) {
                continue;
            }
            String mapped = mappedLinks.get(fhirLink);
            if (mapped != null) {
                links.append(makeLink(fhirLink, mapped));
            } else if (isResource(fhirLink)) {
                // If a FHIR Resource, link to fhir/R4/{resource}.html
                links.append(makeLink(fhirLink, FHIR_BASE + fhirLink + ".html"));
            } else if (isResourceField(fhirLink)) {
                // If a FHIR Resource field, link to fhir/R4/{resource}-definitions.html#{field}
                name = StringUtils.substringBefore(fhirLink, ".");
                links.append(makeLink(fhirLink, FHIR_BASE + name + "-definitions.html#" + fhirLink));
            } else if (isFhirDataType(fhirLink)) {
                // If a FHIR Data Type, link to  http://hl7.org/fhir/R4/datatypes.html#{datatype}
                links.append(makeLink(fhirLink, FHIR_BASE + "datatypes.html#" + fhirLink));
            } else if (isFhirDataType(StringUtils.capitalize(fhirLink))) {
                warn("%s used where %s is likely meant.%n", fhirLink, StringUtils.capitalize(fhirLink));
                links.append(makeLink(StringUtils.capitalize(fhirLink), FHIR_BASE + "datatypes.html#" + StringUtils.capitalize(fhirLink)));
            } else if (isFhirDataTypeField(fhirLink)) {
                // If a FHIR Data Type field, link to fhir/R4/datatypes-definitions.html#{field}
                links.append(makeLink(fhirLink, FHIR_BASE + "datatypes-definitions.html#" + fhirLink));
            } else if (StringUtils.startsWith(fhirLink, "http://hl7.org/fhir") &&
                       !StringUtils.startsWith(fhirLink, "http://hl7.org/fhir/R4")) {
                // if it starts with http://hl7.org/fhir and does not contain R4, insert R4
                // and convert to codesystem- style.
                links.append(makeLink(fhirLink,
                    fhirLink.replace("http://hl7.org/fhir/", FHIR_BASE + "codesystem-") + (fhirLink.endsWith(".html") ? "" : ".html")));
            } else if (StringUtils.startsWith(fhirLink,  FHIR_TERM + "CodeSystem/v2-")) {
                // if it starts with  http://terminology.hl7.org/CodeSystem/v2- replace with http://hl7.org/fhir/R4/v2/
                // and append /index.html
                links.append(makeLink(fhirLink,
                    fhirLink.replace(FHIR_TERM + "CodeSystem/v2-", FHIR_BASE + "v2/") + "/index.html")
                );
            } else if (StringUtils.startsWith(fhirLink, FHIR_TERM + "CodeSystem/v3-")) {
                // if it starts with  http://terminology.hl7.org/CodeSystem/v2- replace with http://hl7.org/fhir/R4/v2/
                // and append /index.html
                links.append(makeLink(fhirLink,
                        fhirLink.replace(FHIR_TERM + "CodeSystem/v3-", FHIR_BASE + "v3/") +
                        "/cs.html")
                );
            } else if (fhirLink.matches("^p?[0-9\\-]*$")) {
                links.append(fhirLink);
            } else {
                // Otherwise, force what is likely to be broken link, making someone go through a fix.
                links.append(makeLink(makeBrokenLink(fhirLink),"#broken"));
                error("Link to FHIR %s not found.%n", fhirLink);
            }

            // Append the delimiter character
            pos += fhirLink.length();
            if (pos < myFhirCode.length()) {
                links.append(myFhirCode.charAt(pos++));
            }
        }
        return links.toString();
    }

    private static boolean isFhirDataTypeField(String fhirLink) {
        return fhirLink.contains(".") && isFhirDataType(StringUtils.substringBefore(fhirLink,"."));
    }

    private static boolean isFhirDataType(String fhirLink) {
        Map<String, Map<String, Triple<String, String, String>>> m = ConverterMap.getMap();
        return m.get("FHIR Data Type").get(fhirLink) != null;
    }

    private static boolean isResourceField(String fhirLink) {
        return fhirLink.contains(".") && isResource(StringUtils.substringBefore(fhirLink,"."));
    }

    private static boolean isResource(String fhirLink) {
        Map<String, Map<String, Triple<String, String, String>>> m = ConverterMap.getMap();
        return m.get("FHIR Resource").get(fhirLink) != null;
    }

    protected String makeSegmentLink(String segmentMap, String fhirCode) {
        // if segmentMap is empty, return the value w/o a link.
        if (StringUtils.isBlank(segmentMap)) {
            return segmentMap;
        }

        // Replace all Reference(name) with name
        String mySegmentMap = segmentMap.replaceAll("Reference\\(([^\\)]*)\\)","$1");

        // If fhirCode ends with [1], remove it.
        // if in the form XX- convert to ConceptMap-datatype-XX-to-XX.html
        String myFhirCode = StringUtils.isBlank(fhirCode) ? mySegmentMap :
            fhirCode.replaceAll("[\\[\\(][^\\]\\)]*[\\]\\)]","");

        // if in the form DTNAME(name1)-name2 or DTNAME[name1]-name2 convert to ConceptMap-datatype-DTNAMEname1-to-name2.html
            mySegmentMap = StringUtils.replaceChars(mySegmentMap,"[]{}()", "");

        // if in the form DTNAME-name1 convert to ConceptMap-datatype-DTNAME-to-name1.html
        return makeLink("Segment", segmentMap, "ConceptMap-segment-" + cleanId(String.format("%s-to-%s", mySegmentMap, myFhirCode)) + ".html");
    }

    protected String makeTableLink(String fhirVocab) {
        tableLinks.put(fhirVocab, Pair.of(fhirVocab, null));
        return fhirVocab;
    }

    protected String makeDataTypeLink(String v2DataType, String fhirDatatype) {

        // if v2DataTypeMap is empty, return the value w/o a link.
        if (StringUtils.isBlank(v2DataType)) {
            return v2DataType;
        }

        String myV2DataType = v2DataType;

        // Replace all Reference(name) with name in fhirDataType
        // if in the form XX- convert to ConceptMap-datatype-XX-to-XX.html
        String myFhirDataType = StringUtils.isBlank(fhirDatatype) ? myV2DataType : fhirDatatype.replaceAll("Reference\\(([^\\)]*)\\)","$1").trim();

        // if in the form DTNAME(name1)-name2 or DTNAME[name1]-name2 convert to ConceptMap-datatype-DTNAMEname1-to-name2.html
        myV2DataType = StringUtils.replaceChars(myV2DataType,"[]{}()", "");

        // if in the form DTNAME-name1 convert to ConceptMap-datatype-DTNAME-to-name1.html
        return makeLink("Data Type", v2DataType,
            "ConceptMap-datatype-" + cleanId(String.format("%s-to-%s", myV2DataType, myFhirDataType)) + ".html");
    }


    private static String makeBrokenLink(String fhirLink) {
        return "<span style='font-weight: bold; color: red'>" + fhirLink + "</span>";
    }

    private String makeLink(String type, String text, String link) {
        Map<String, Pair<String, String>> target = null;
        switch (type) {
        case "Segment":
            target = segmentLinks;
            break;
        case "Data Type":
            target = dataTypeLinks;
            break;
        case "Table":
            target = tableLinks;
            break;
        }
        if (target != null) {
            target.put(link, Pair.of(text, getSourceFileName()));
        }
        return makeLink(text, link);
    }

    private String makeLink(String text, String link) {
        return String.format("<a href='%s'>%s</a>", link, text);
    }

    static boolean printLinkData() {
        String names[] = { "FHIR", "Segments", "Tables", "Data Types" };

        Set<String> htmlFiles = new HashSet<>();
        Collection<File> files = FileUtils.listFiles(new File("tank"), new String[] { "fsh" }, false);
        for (File file: files) {
            htmlFiles.add("ConceptMap-" + cleanId(StringUtils.substringBeforeLast(file.getName(), ".fsh")) + ".html");
        }
        List<Map<String,Pair<String, String>>> maps = Arrays.asList(fhirLinks, segmentLinks, tableLinks, dataTypeLinks);
        boolean allValid = true;
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
            for (Map.Entry<String, Pair<String, String>> link: maps.get(i).entrySet()) {
                System.out.print(" ");
                System.out.printf(" %s\t%s%n", link.getValue(), link.getKey());

                // Verify the link target, and report if not valid
                String page = link.getKey();
                if (page.contains(".html") && !htmlFiles.contains(page)) {
                    report(true, link.getValue().getRight(), "Link requested by %s to '%s' does not exist.%n", link.getValue().getLeft(), page);
                    allValid = false;
                }
            }
        }

        return allValid;
    }

    protected void error(String format, Object ... args) {
        report(true, getSourceFileName(), format, args);
    }

    protected void warn(String format, Object ... args) {
        report(false, getSourceFileName(), format, args);
    }

    protected void info(String format, Object ... args) {
        System.out.printf(source + ": " + format, args);
    }

    protected static void report(boolean isError, String source, String format, Object ...args) {
        if (isError) {
            errCount++;
        } else {
            warnCount++;
        }
        System.err.printf((isError ? "E" + errCount : "W" + warnCount) + ") " + source + ": " + format, args);
    }


    protected static int getErrorCount() {
        return errCount;
    }

    protected static int getWarnCount() {
        return warnCount;
    }
}
