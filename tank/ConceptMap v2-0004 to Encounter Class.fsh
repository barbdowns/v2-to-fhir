Instance: ConceptMapv2-0004toEncounterClass
InstanceOf: ConceptMap
Title: "ConceptMap v2-0004 to Encounter Class Map"
* description = "This ConceptMap represents the mapping from the HL7 V2 Table v2-0004 to the FHIR Encounter Class Value Set."
* id = "conceptmap-v2-0004-to-encounter-class"
* url = "http://hl7.org/fhir/v2-tofhir"
* version = "1.0"
* name = "ConceptMap_v2-0004_Map"
* status = #active
* experimental = true
* date = "2020-03-01"
* publisher = "HL7 International, Inc"
* contact.telecom.system = #email
* contact.telecom.value = "v2-to-fhir@lists.hl7.org"
* copyright = "Copyright (c) 2020, HL7 International, Inc., All Rights Reserved."
* sourceUri = "http://terminology.hl7.org/CodeSystem/v2-0004"
* targetUri = "null"
* group.element[0].code = #E
* group.element[0].display = "Emergency"
* group.element[0].target.equivalence = #equivalent
* group.element[0].target.code = #EMER
* group.element[0].target.display = "http://terminology.hl7.org/CodeSystem/v3-ActCode"
* group.element[1].code = #I
* group.element[1].display = "Inpatient"
* group.element[1].target.equivalence = #equivalent
* group.element[1].target.code = #IMP
* group.element[1].target.display = "http://terminology.hl7.org/CodeSystem/v3-ActCode"
* group.element[2].code = #O
* group.element[2].display = "Outpatient"
* group.element[2].target.equivalence = #equivalent
* group.element[2].target.code = #AMB
* group.element[2].target.display = "http://terminology.hl7.org/CodeSystem/v3-ActCode"
* group.element[3].code = #P
* group.element[3].display = "Preadmit"
* group.element[3].target.equivalence = #equivalent
* group.element[3].target.code = #PRENC
* group.element[3].target.display = "http://terminology.hl7.org/CodeSystem/v3-ActCode"
* group.element[4].code = #R
* group.element[4].display = "Recurring patient"
* group.element[4].target.equivalence = #unmatched
* group.element[5].code = #B
* group.element[5].display = "Obstetrics"
* group.element[5].target.equivalence = #unmatched
* group.element[6].code = #C
* group.element[6].display = "Commercial Account"
* group.element[6].target.equivalence = #unmatched
* group.element[7].code = #N
* group.element[7].display = "Not Applicable"
* group.element[7].target.equivalence = #unmatched
* group.element[8].code = #U
* group.element[8].display = "Unknown"
* group.element[8].target.equivalence = #unmatched
