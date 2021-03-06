This sections outlines implementation specific considerations that will have to be addressed during the implementation of v2-to-FHIR
transformation project. Not all of these issues will apply to every implementation, but each implementation should review and consider
he entire list to assess whether or not they are relevant for the project at hand. This list is not comprehensive and other
implementation considerations may emerge as transforms are developed, tested and implemented in a Production environment.

### Resource.id Generation and References
When the v2 message is mapped into a FHIR Bundle, resources need to have a resource.id.  At the time of the mapping the actual
Resource.id may not be known if the intent is to update or reference an existing resource.  The following guidance should be followed:

* Give resources a unique id within the bundle so all references in the bundle are correct.
  * Give resources a unique UUID
  * Base the id generation on information in the segment.  But that may vary, e.g.,
    * the UK may opt to use the NHS number for PID to Patient to then be used as reference.
    * another may create random numbers
    * etc.
  * Receiver cannot read any meaning in the value of the .id.

Depending on the next step, the Bundle may be forwarded in a FHIR message, the resource may be persisted as FHIR resources or
translated into local data structures, or used in subsequent RESTful APIs.  The system managing one or more of these steps may
therefore opt to not follow all of the above guidance as they may be able to already be capable of resolving the Resource.id to the
correct existing or new .id.

### Contained Resources
This implementation guide recognizes the distinction between stand-alone and [contained resources](https://www.hl7.org/fhir/references.html#contained)
but does not provide guidance in the mappings as the appropriate usage of contained resources. Implementers should consider the
appropriateness of using contained resources during the transformation process where insufficient data is available in the v2
message to create a stand-alone FHIR resource. For example, if an ROL segment is transformed into a PractitionerRole resource
including the address of the provider office in ROL-11 but lacks the provider's location in ROL-13 there may not be enough
information to create a stand-alone Location resource, but contained Location resource (including the address) may be included in
the PractitionerRole resource.

### Task Management
The FHIR standard includes extensive content related to [workflow management](http://hl7.org/implement/standards/fhir/workflow-module.html)
and [task management](http://hl7.org/implement/standards/fhir/task.html). While many v2 message types don't
infer tasks (eg, they report on a previously completed event), some message types (eg. order messages) may imply the need for an
external system to complete a task (eg. fullfill the order being requested). This implementation does include some mappings to the
Task resource but it is up to implementers to determine if it is appropriate to create Task resources during the transformation
process. For example, an order message may be directed to a system with the intent of notifying the system of the existence of the
order without any expectation that the receiving system will fulfill the order. In this case, the creation of a Task resource is
likely to not be appropriate. Implementers must understand the workflows associated with the data exchange to implement tasks
correctly.

### Provenance
The concept of data provenance is typically only indirectly addressed within the v2 standard, however provenance can often be
inferred by the data in various fields in the v2 message. For example, provenance may be inferred from data in the MSH segment (eg.
the responsible sending organization), the ORC segment (eg. the entering user) or TXA segment (eg. the authenticator). This
implementation guide does include some mapping to the Provenance resource but it is up to implementors to determine the level of
data provenance that should be captured during the transformation process. At a minimum, the authors of this document feel that it
may be appropriate to capture the provenance of the message source and the v2-to-FHIR transformation process. Additional provenance
may be captured from additional fields as appropriate for the implementation.

### Security
Under Construction.

### Patients
Identity management and patient merging/unmerging are complex processes at the best of times. Implementers should carefully consider
how these activities will be impacted by a v2-to-FHIR transformation project. In particular, the workflow chosen (eg. messaging versus
RESTful actions) and how Patient.id is populated may significantly impact how Patient resources are created, updated and deleted by
the receiving system.

### Encounters
Clinically focused v2 messages (eg. ORM/OML, ORU, MDM) may contain limited data regarding an associated patient encounter. This
data content may or may not be sufficient to unambiguously identify a matching encounter in the receiving system. The creation
of Encounter resources should be carefully considered during the implementation process. The use of contained Encounter resources
may be appropriate.

### Practitioners
In v2 messages, it is common for multiple fields (using the XCN data type) to document the data for the same provider (eg, the same
person may be the attending provider in the PV1 segment of an order message as well as the ordering provider in both the ORC and
OBR segments of the same message). In this case, it may be most efficient if the Encounter and ServiceRequest resources reference
the same Practitioner resource. It is critical that implementers consider how they will recognize duplicate provider references in
a given v2 message and reuse Practitioner resources efficiently. Deduplication logic based on data including identifiers, name and
credentials should be employed as part of the transformation strategy to identify potentially reusable resources.

In most FHIR resources, elements which allow a reference to a Practitioner resource also allow a reference to a PractitionerRole
resource. In most scenarios, a v2 field using the XCN data type will typically be mapped to the Practitioner resource, but
implementers may choose to map to the PractitionerRole resource instead. In a few places, an XCN field will be mapped to a
PractitionerRole resource when other fields in the segment can contribute content to the PractitionerRole resource to create a
more robust resource (for example, the ORC segment when mapped to ServiceRequest maps the Ordering Provider (ORC-12) field to
PractitionerRole because the Ordering Facility fields in ORC (ORC-21 through -23) can also contribute to the PractitionerRole resource).

### Results
#### Observations and Components
Depending on context and content an OBX segment in a v2 message may represent a wide variety of different types of data. Typically
however, the OBX segment will map to an Observation resource which in turn is referenced in some other resource (eg, the result
containing OBX segments in an ORU message will typically be transformed into Observation resources which are referenced in
DiagnosticReport.result). Even when an OBX segment clearly maps to an Observation resource, complications may arise when the
contents of multiple OBX segments must be considered as a whole in order to be fully understood or when there are multiple parts to
a single result (that is each OBX segment is a component of an Observation (eg Observation.component). It is critical that
implementers fully understand the type of content that may be presented in an OBX segment.

#### Multiple Occurrences of OBX-5
OBX-5 (Observation Value) is allowed to repeat in the v2 base standard however Observation.value[x] is constrained to a cardinality
of 0..1. In this case, the transform may take a number of forms:

* Where it is possible to concatenate the repeating values into a single value, then it may be possible to use one Observation resource
to capture the result
* Multiple Observation resources may need to be created, one for each occurrence of OBX-5
  * In this instance, it may be important to understand the relationships that can be documented with the partOf and/or hasMember
  elements offered by Observation
* It may be possible to capture each occurrence as a component of a single Observation resource

#### Related Observations
The values of OBX-3 and/or OBX-4 in multiple OBX segments may indicate a relationship between individual observations that must be
maintain during the transformation process in order for the data to remain accurate and of use. For example, when two OBX segments
contain the systolic and diastolic values of a blood pressure reading, the relationship between the two observations must be
maintained. Observation.component can be used to maintain the relationship between related results. Implementers are encouraged
to read the base standard documentation available describing the use of Observation.component as well as think about how related
observations will be identified during the transformation process.

### Implementation Guide Extensions on the Base Standard
Some v2 implementations may adhere to constraints made on the base standard by an implementation guide. Where this happens it may
be necessary to extend or constrain the standard mappings provided by this project. For example, in the US the v2.5.1 immunization
messaging implementation guide describes how to use OBX segments to convey information related patient eligibility, distribution of
educational materials and vaccine funding source. While these concepts are part of the FHIR Immunization resource, the VXU mappings
from this project do not include these transformations as they are defined by the implementation guide, not the base standard.
Implementers should consider local variations from the base standard when developing their transformations.


<div id="disqus_thread"></div>
<script>
var disqus_config = function () {
this.page.url = "http://build.fhir.org.hl7/v2-to-fhir/branches/master/implementation_considerations.html"; // Replace PAGE_URL with your page's canonical URL variable
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
