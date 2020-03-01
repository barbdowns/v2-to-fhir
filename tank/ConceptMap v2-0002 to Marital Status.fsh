Instance: ConceptMapv2-0002toMaritalStatus
InstanceOf: ConceptMap
Title: "ConceptMap v2-0002 to Marital Status Map"
* description = "This ConceptMap represents the mapping from the HL7 V2 Table v2-0002 to the FHIR Marital Status Value Set."
* id = "conceptmap-v2-0002-to-marital-status"
* url = "http://hl7.org/fhir/v2-tofhir"
* version = "1.0"
* name = "ConceptMap_v2-0002_Map"
* status = #active
* experimental = true
* date = "2020-03-01"
* publisher = "HL7 International, Inc"
* contact.telecom.system = #email
* contact.telecom.value = "v2-to-fhir@lists.hl7.org"
* copyright = "Copyright (c) 2020, HL7 International, Inc., All Rights Reserved."
* sourceUri = "http://terminology.hl7.org/CodeSystem/v2-0002"
* targetUri = "http://terminology.hl7.org/CodeSystem/v3-MaritalStatus"
* group.element[0].code = #A
* group.element[0].display = "Separated"
* group.element[0].target.equivalence = #equivalent
* group.element[0].target.code = #L
* group.element[0].target.display = "Legally Separated"
* group.element[1].code = #D
* group.element[1].display = "Divorced"
* group.element[1].target.equivalence = #equivalent
* group.element[1].target.code = #D
* group.element[1].target.display = "Divorced"
* group.element[2].code = #M
* group.element[2].display = "Married"
* group.element[2].target.equivalence = #equivalent
* group.element[2].target.code = #M
* group.element[2].target.display = "Married"
* group.element[3].code = #S
* group.element[3].display = "Single"
* group.element[3].target.equivalence = #equivalent
* group.element[3].target.code = #S
* group.element[3].target.display = "Never Married"
* group.element[4].code = #W
* group.element[4].display = "Widowed"
* group.element[4].target.equivalence = #equivalent
* group.element[4].target.code = #W
* group.element[4].target.display = "Widowed"
* group.element[5].code = #C
* group.element[5].display = "Common law"
* group.element[5].target.equivalence = #equivalent
* group.element[5].target.code = #C
* group.element[5].target.display = "Common Law"
* group.element[6].code = #G
* group.element[6].display = "Living together"
* group.element[6].target.equivalence = #equivalent
* group.element[6].target.code = #T
* group.element[6].target.display = "Domestic partner"
* group.element[7].code = #P
* group.element[7].display = "Domestic partner"
* group.element[7].target.equivalence = #equivalent
* group.element[7].target.code = #T
* group.element[7].target.display = "Domestic partner"
* group.element[8].code = #R
* group.element[8].display = "Registered domestic partner"
* group.element[8].target.equivalence = #equivalent
* group.element[8].target.code = #T
* group.element[8].target.display = "Domestic partner"
* group.element[9].code = #E
* group.element[9].display = "Legally Separated"
* group.element[9].target.equivalence = #equivalent
* group.element[9].target.code = #L
* group.element[9].target.display = "Legally Separated"
* group.element[10].code = #N
* group.element[10].display = "Annulled"
* group.element[10].target.equivalence = #unmatched
* group.element[11].code = #I
* group.element[11].display = "Interlocutory"
* group.element[11].target.equivalence = #equivalent
* group.element[11].target.code = #I
* group.element[11].target.display = "Interlocutory"
* group.element[12].code = #B
* group.element[12].display = "Unmarried"
* group.element[12].target.equivalence = #equivalent
* group.element[12].target.code = #U
* group.element[12].target.display = "unmarried"
* group.element[13].code = #U
* group.element[13].display = "Unknown"
* group.element[13].target.equivalence = #equivalent
* group.element[13].target.code = #UNK
* group.element[13].target.display = "unknown"
* group.element[14].code = #O
* group.element[14].display = "Other"
* group.element[14].target.equivalence = #unmatched
* group.element[15].code = #T
* group.element[15].display = "Unreported"
* group.element[15].target.equivalence = #unmatched
