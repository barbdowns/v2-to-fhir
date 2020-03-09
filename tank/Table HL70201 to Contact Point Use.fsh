Instance: TableHL70201toContactPointUse
InstanceOf: ConceptMap
Title: "Table HL70201 to Contact Point Use Map"
* description = "This ConceptMap represents the mapping from the HL7 V2 Table HL70201 to the FHIR Contact Point Use Value Set."
* id = "table-hl70201-to-contact-point-use"
* url = "http://hl7.org/fhir/v2-tofhir/table-hl70201-to-contact-point-use"
* version = "1.0"
* name = "Table_HL70201_Map"
* status = #active
* experimental = true
* date = "2020-03-09"
* publisher = "HL7 International, Inc"
* contact.telecom.system = #email
* contact.telecom.value = "v2-to-fhir@lists.hl7.org"
* copyright = "Copyright (c) 2020, HL7 International, Inc., All Rights Reserved."
* sourceUri = "HL70201"
* targetUri = "http://hl7.org/fhir/contact-point-use"
* group.element[0].code = #PRN
* group.element[0].display = "Primary Residence Number"
* group.element[0].target.equivalence = #equivalent
* group.element[0].target.code = #home
* group.element[0].target.display = "Home"
* group.element[1].code = #ORN
* group.element[1].display = "Other Residence Number"
* group.element[1].target.equivalence = #unmatched
* group.element[2].code = #WPN
* group.element[2].display = "Work Number"
* group.element[2].target.equivalence = #equivalent
* group.element[2].target.code = #work
* group.element[2].target.display = "Work"
* group.element[3].code = #VHN
* group.element[3].display = "Vacation Home Number"
* group.element[3].target.equivalence = #unmatched
* group.element[4].code = #ASN
* group.element[4].display = "Answering Service Number"
* group.element[4].target.equivalence = #unmatched
* group.element[5].code = #EMR
* group.element[5].display = "Emergency Number"
* group.element[5].target.equivalence = #unmatched
* group.element[6].code = #NET
* group.element[6].display = "Network (email) Address"
* group.element[6].target.equivalence = #unmatched
* group.element[7].code = #BPN
* group.element[7].display = "Beeper Number"
* group.element[7].target.equivalence = #unmatched
* group.element[8].code = #PRS
* group.element[8].display = "Personal"
* group.element[8].target.equivalence = #equivalent
* group.element[8].target.code = #mobile
* group.element[8].target.display = "Mobile"