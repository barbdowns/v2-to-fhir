Instance: DatatypeCWEtocode
InstanceOf: ConceptMap
Title: "Datatype CWE to code Map"
* description = "This ConceptMap represents the mapping from the HL7 V2 null to the FHIR null."
* id = "datatype-cwecode-to-code"
* url = "http://hl7.org/fhir/v2-tofhir/datatype-cwecode-to-code"
* version = "1.0"
* name = "Datatype_CWE_Map"
* status = #active
* experimental = true
* date = "2020-03-16"
* publisher = "HL7 International, Inc"
* contact.telecom.system = #email
* contact.telecom.value = "v2-to-fhir@lists.hl7.org"
* copyright = "Copyright (c) 2020, HL7 International, Inc., All Rights Reserved."
* sourceUri = "CWE"
* targetUri = "code"
* group.element[0].code = #CWE.1
* group.element[0].display = "Identifier"
* group.element[0].target.equivalence = #equivalent
* group.element[0].target.code = #code
* group.element[0].target.display = "code"
* group.element[1].code = #CWE.4
* group.element[1].display = "Alternate Identifier"
* group.element[1].target.equivalence = #equivalent
* group.element[1].target.code = #code
* group.element[1].target.display = "code"
* group.element[1].target.dependsOn[0].property = "value"
* group.element[1].target.dependsOn[0].value = """
# IF CWE.1 NOT VALUED\
"""
