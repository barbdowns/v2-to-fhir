Instance: TableHL70085toObservationStatus
InstanceOf: ConceptMap
Title: "Table HL70085 to Observation Status Map"
* description = "This ConceptMap represents the mapping from the HL7 V2 Table HL70085 to the FHIR Observation Status Value Set."
* id = "table-hl70085-to-observation-status"
* url = "http://hl7.org/fhir/v2-tofhir/table-hl70085-to-observation-status"
* version = "1.0"
* name = "Table_HL70085_Map"
* status = #active
* experimental = true
* date = "2020-03-16"
* publisher = "HL7 International, Inc"
* contact.telecom.system = #email
* contact.telecom.value = "v2-to-fhir@lists.hl7.org"
* copyright = "Copyright (c) 2020, HL7 International, Inc., All Rights Reserved."
* sourceUri = "HL70085"
* targetUri = "http://hl7.org/fhir/observation-status"
* group.element[0].code = #A
* group.element[0].display = "Amended"
* group.element[0].target.equivalence = #equivalent
* group.element[0].target.code = #ammended
* group.element[0].target.display = "Ammended"
* group.element[1].code = #B
* group.element[1].display = "Appended"
* group.element[1].target.equivalence = #unmatched
* group.element[2].code = #C
* group.element[2].display = "Correction"
* group.element[2].target.equivalence = #equivalent
* group.element[2].target.code = #corrected
* group.element[2].target.display = "Corrected"
* group.element[3].code = #D
* group.element[3].display = "Deleted"
* group.element[3].target.equivalence = #equivalent
* group.element[3].target.code = #entered-in-error
* group.element[3].target.display = "Entered in Error"
* group.element[4].code = #F
* group.element[4].display = "Final"
* group.element[4].target.equivalence = #equivalent
* group.element[4].target.code = #final
* group.element[4].target.display = "Final"
* group.element[5].code = #I
* group.element[5].display = "In Process"
* group.element[5].target.equivalence = #unmatched
* group.element[6].code = #N
* group.element[6].display = "Not Asked"
* group.element[6].target.equivalence = #unmatched
* group.element[7].code = #O
* group.element[7].display = "Order Detail"
* group.element[7].target.equivalence = #unmatched
* group.element[8].code = #P
* group.element[8].display = "Preliminary"
* group.element[8].target.equivalence = #equivalent
* group.element[8].target.code = #preliminary
* group.element[8].target.display = "Preliminary"
* group.element[9].code = #R
* group.element[9].display = "Entered - Not Verified"
* group.element[9].target.equivalence = #unmatched
* group.element[10].code = #S
* group.element[10].display = "Partial"
* group.element[10].target.equivalence = #equivalent
* group.element[10].target.code = #preliminary
* group.element[10].target.display = "Preliminary"
* group.element[11].code = #V
* group.element[11].display = "Verified"
* group.element[11].target.equivalence = #unmatched
* group.element[12].code = #X
* group.element[12].display = "Cannot be Obtained"
* group.element[12].target.equivalence = #unmatched
* group.element[13].code = #U
* group.element[13].display = "Status Change to Final Without Retransmission"
* group.element[13].target.equivalence = #unmatched
* group.element[14].code = #W
* group.element[14].display = "Original Posted as Wrong"
* group.element[14].target.equivalence = #equivalent
* group.element[14].target.code = #entered-in-error
* group.element[14].target.display = "Entered in Error"
