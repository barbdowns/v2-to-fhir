The v2-to-FHIR Project Team is soliciting general comments from reviewers as well as specific comments in a number of areas (see below). Any comments may be entered on the comments section under each of the implementation guide page where they apply rather than making Jira tickets at this time.

### Impact/Scope:
* We are looking to gather case studies from reviewers as to how they could see this project impacting the work they are currently doing in the v2/FHIR space
* We are also looking for input on which use cases (message types, other implementations) we should be focusing on (eg billing or transcription/documents)
* Please comment on implementation considerations we have documented. Are there other things to consider on a per implementation basis?

### Infrastructure:
* While for the purposes of this review, we are using spread sheets to document example mappings from v2 messages, segments and data types to cognate FHIR content, we are looking for comments as to other formats for mapping content. We are actively investigating the use of ConceptMap to document mappings using a FHIR resource type.
* Should we be developing a way to include the original v2 message in the FHIR Bundle (perhaps as a DocumentReference or something pointed to in a Provenance record)?
* We are looking for input on potential value for Bundle.type which may be impacted by the workflow used by the transform (eg messaging versus RESTful exchanges)
  * A type of "message" would likely be common but could other types such as "transaction" also be used by conversion engines?
    * Are there implications for the profile based on the type selected?
* We are looking for feedback on the implementation of Provenance. Currently every message includes the creation of Provenance resources for the message source and transformer. As well selected messages (eg OML) also include Provenance for specific segments (eg ORC contents fields related to the provenance of the ServiceRequest). We are interested in the appropriateness of the existing mappings and any other mappings that should be added.
  * We are also interested in thoughts relating to the population of Provenance.target for the MSH level provenance. Should this element point back to all resources in the Bundle (as they all originate from the same source and transformed by the same process) or should .target only point to the MessageHeader resource?

### Mapping Content:
While the focus of the first Peer Review is not to solidify content, i.e., accuracy and completeness of the mappings, rather organization and infrastructure of the v2-to-FHIR implementation guide, we welcome any feedback on the following:

* Are you using v2 fields that are not yet mapped?  If so, please provide v2 segment-field identifier, the segment flavor(s) you would use them, and the FHIR component you suggest should be used for each flavor.
* Are v2 fields mapped differently than you expected?  If so, please provide the v2 segment-field identifier in the segment flavor(s) and propose an alternative mapping.
* Are any conditions incomplete or incorrect?  If so, please provide the spreadsheet where that is the case and the row(s) you  would have new/alternate suggestions.

Specific questions:

#### AL1 segment
* AL1-4 (Allergy Severity Code) is defined as indicating "the general severity of the allergy". The project team has discussed whether or not this concept best maps to AllergyIntolerance.criticality (Estimate of the potential clinical harm, or seriousness, of the reaction to the identified substance.) or AllergyIntolerance.reaction.severity (Clinical assessment of the severity of the reaction event as a whole, potentially considering multiple different manifestations.). We are asking for input on which FHIR element is the better mapping or if the mappings should indicate both elements and leave the selection of the most appropriate element as an implementation decision.

#### IAM segment
* copy concern from AL1-4

#### DG1 segment
* DG1-19 (Attestation Date/Time) is defined as containing "the time stamp that indicates the date and time that the attestation was signed". The project team is asking for input as whether this maps best to Condition.recordedDate (Date record was first recorded) or the Condition extension assertedDate (The date on which the existence of the Condition was first asserted or acknowledged).

#### OBX segment
* An OBX segment can represent a standalone Observation or may represent a component of an Observation (that is multiple OBX segments may be components of a single Observation). The decision between Observation or Observation Component is likely to be an implementation decision and/or run time decision. We are looking for input from the community has how to best represent the choice of Observation versus Observation.component in the Message maps. Several use cases have been document along with initial proposals for a preferred solution for each use case.

#### ROL segment
* An ROL segment following the PID segment may represent either a representative of either organization or the patient. The participant may be better represented as either a Practitioner/PractitionerRole or RelatedPerson. We are seeking input on which roles are best mapped to there two resource types.

#### OBR segment
* OBR-11 can indicate that the lab needs to collect a specimen for the test. Should this trigger the creation of a Task for specimen collection or does that make presumptions about how the system fulfilling the order manages its own internal processes?

#### XAD segment
* XAD-8 has no clear mapping, but we could use Address.line.  Would that be acceptable?  It would have to be clear from the value that it is a geographic designation that could be used for analytics


<div id="disqus_thread"></div>
<script>
var disqus_config = function () {
this.page.url = "http://build.fhir.org.hl7/v2-to-fhir/branches/master/questions.html"; // Replace PAGE_URL with your page's canonical URL variable
this.page.identifier = this.page.url.substring(this.page.url.lastIndexOf("/")+1, this.page.url.lastIndexOf(".")); // Replace PAGE_IDENTIFIER with your page's unique identifier variable
};
(function() { // DON'T EDIT BELOW THIS LINE
var d = document, s = d.createElement('script');
s.src = 'https://v2-to-fhir.disqus.com/embed.js';
s.setAttribute('data-timestamp', +new Date());
(d.head || d.body).appendChild(s);
})();
</script>
<noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>