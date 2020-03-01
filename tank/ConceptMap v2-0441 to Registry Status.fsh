Instance: ConceptMapv2-0441toRegistryStatus
InstanceOf: ConceptMap
Title: "ConceptMap v2-0441 to Registry Status Map"
* description = "This ConceptMap represents the mapping from the HL7 V2 Table v2-0441 to the FHIR Registry Status Value Set."
* id = "conceptmap-v2-0441-to-registry-status"
* url = "http://hl7.org/fhir/v2-tofhir"
* version = "1.0"
* name = "ConceptMap_v2-0441_Map"
* status = #active
* experimental = true
* date = "2020-03-01"
* publisher = "HL7 International, Inc"
* contact.telecom.system = #email
* contact.telecom.value = "v2-to-fhir@lists.hl7.org"
* copyright = "Copyright (c) 2020, HL7 International, Inc., All Rights Reserved."
* sourceUri = "http://terminology.hl7.org/CodeSystem/v2-0441"
* targetUri = ""
* group.element[0].code = #A
* group.element[0].display = "Active"
* group.element[0].target.equivalence = #equivalent
* group.element[0].target.code = #true
* group.element[1].code = #I
* group.element[1].display = "Inactive"
* group.element[1].target.equivalence = #equivalent
* group.element[1].target.code = #false
* group.element[2].code = #L
* group.element[2].display = "Inactive - Lost to follow-up (cancel contract)"
* group.element[2].target.equivalence = #equivalent
* group.element[2].target.code = #false
* group.element[3].code = #M
* group.element[3].display = "Inactive - Moved or gone elsewhere (cancel contract)"
* group.element[3].target.equivalence = #equivalent
* group.element[3].target.code = #false
* group.element[4].code = #P
* group.element[4].display = "Inactive - Permanently inactive (Do not reactivate or add new entries to the record)"
* group.element[4].target.equivalence = #equivalent
* group.element[4].target.code = #false
* group.element[5].code = #O
* group.element[5].display = "Other"
* group.element[5].target.equivalence = #unmatched
* group.element[6].code = #U
* group.element[6].display = "Unknown"
* group.element[6].target.equivalence = #unmatched
