Instance: MessageADT_A01toBundle
InstanceOf: ConceptMap
Title: "Message ADT_A01 to Bundle Map"
* description = "This ConceptMap represents the mapping from the HL7 V2 ADT_A01 Message to the FHIR Message Bundle."
* id = "message-adt-a01-to-bundle"
* url = "http://hl7.org/fhir/v2-tofhir/message-adt-a01-to-bundle"
* version = "1.0"
* name = "Message_ADT_A01_Map"
* status = #active
* experimental = true
* date = "2020-03-16"
* publisher = "HL7 International, Inc"
* contact.telecom.system = #email
* contact.telecom.value = "v2-to-fhir@lists.hl7.org"
* copyright = "Copyright (c) 2020, HL7 International, Inc., All Rights Reserved."
* sourceUri = "ADT_A01"
* targetUri = "Bundle"
* group.element[0].code = #ADT_A01.MSH
* group.element[0].display = "Message Header"
* group.element[0].target.equivalence = #equivalent
* group.element[0].target.code = #MessageHeader[1]
* group.element[0].target.display = "MessageHeader[1]"
* group.element[1].code = #ADT_A01.MSH
* group.element[1].display = "Message Header"
* group.element[1].target.equivalence = #equivalent
* group.element[1].target.code = #Provenance[1]
* group.element[1].target.display = "Provenance[1]"
* group.element[2].code = #ADT_A01.MSH
* group.element[2].display = "Message Header"
* group.element[2].target.equivalence = #equivalent
* group.element[2].target.code = #Provenance[2]
* group.element[2].target.display = "Provenance[2]"
* group.element[3].code = #ADT_A01.SFT
* group.element[3].display = "Software Segment"
* group.element[3].target.equivalence = #equivalent
* group.element[3].target.code = #MessageHeader[1]
* group.element[3].target.display = "MessageHeader[1]"
* group.element[4].code = #ADT_A01.PID
* group.element[4].display = "Patient Identification"
* group.element[4].target.equivalence = #equivalent
* group.element[4].target.code = #Patient[1]
* group.element[4].target.display = "Patient[1]"
* group.element[5].code = #ADT_A01.PD1
* group.element[5].display = "Additional Demographics"
* group.element[5].target.equivalence = #equivalent
* group.element[5].target.code = #Patient[1]
* group.element[5].target.display = "Patient[1]"
* group.element[6].code = #ADT_A01:follow:PID.ARV
* group.element[6].display = "Access Restrictions"
* group.element[6].target.equivalence = #equivalent
* group.element[6].target.code = #Patient[1]
* group.element[6].target.display = "Patient[1]"
* group.element[6].target.dependsOn[0].property = "value"
* group.element[6].target.dependsOn[0].value = """
# IF ARV-1 IN ("PID", "PD1") AND ARV-3 NOT VALUED\
"""
* group.element[7].code = #ADT_A01:follow:PID.ROL
* group.element[7].display = "Role"
* group.element[7].target.equivalence = #equivalent
* group.element[7].target.code = #Patient[1].generalPractitioner
* group.element[7].target.display = "Patient[1].generalPractitioner"
* group.element[7].target.dependsOn[0].property = "value"
* group.element[7].target.dependsOn[0].value = """
(element("3.1")="PP")and(element("3.3")="HL70443")
# IF ROL-3.1 EQUALS "PP" AND ROL-3.3 EQUALS "HL70443"\
"""
* group.element[8].code = #ADT_A01.PID.ROL
* group.element[8].display = "Role"
* group.element[8].target.equivalence = #equivalent
* group.element[8].target.code = #RelatedPerson[1]
* group.element[8].target.display = "RelatedPerson[1]"
* group.element[8].target.dependsOn[0].property = "value"
* group.element[8].target.dependsOn[0].value = """
(element("3.1")!="PP")or(element("3.3")!="HL70443")
# IF ROL-3.1 NOT EQUALS  "PP" OR ROL-3.3 NOT EQUALS "HL70443"\
"""
* group.element[8].target.dependsOn[0].value = "While most likely all other HL70443 values go to RelatedPerson, the implementation needs to determine whether that is fully accurate."
* group.element[9].code = #ADT_A01.NK1
* group.element[9].display = "Next of Kin / Associated Parties"
* group.element[9].target.equivalence = #equivalent
* group.element[9].target.code = #RelatedPerson[2]
* group.element[9].target.display = "RelatedPerson[2]"
* group.element[9].target.dependsOn[0].property = "value"
* group.element[9].target.dependsOn[0].value = "The FHIR location will depend on the nature of the data being exchanged and how the systems use next of kin data"
* group.element[10].code = #ADT_A01.NK1
* group.element[10].display = "Next of Kin / Associated Parties"
* group.element[10].target.equivalence = #equivalent
* group.element[10].target.code = #Patient[1]
* group.element[10].target.display = "Patient[1]"
* group.element[10].target.dependsOn[0].property = "value"
* group.element[10].target.dependsOn[0].value = "The FHIR location will depend on the nature of the data being exchanged and how the systems use next of kin data"
* group.element[11].code = #ADT_A01.PV1
* group.element[11].display = "Patient Visit"
* group.element[11].target.equivalence = #equivalent
* group.element[11].target.code = #Encounter[1]
* group.element[11].target.display = "Encounter[1]"
* group.element[12].code = #ADT_A01.PV2
* group.element[12].display = "Patient Visit - Additional Info."
* group.element[12].target.equivalence = #equivalent
* group.element[12].target.code = #Encounter[1]
* group.element[12].target.display = "Encounter[1]"
* group.element[13].code = #ADT_A01:follow:PV1.ARV
* group.element[13].display = "Access Restrictions"
* group.element[13].target.equivalence = #equivalent
* group.element[13].target.code = #Encounter[1]
* group.element[13].target.display = "Encounter[1]"
* group.element[13].target.dependsOn[0].property = "value"
* group.element[13].target.dependsOn[0].value = """
# IF ARV-1 IN ("PV1", "PV2") AND ARV-3 NOT VALUED\
"""
* group.element[14].code = #ADT_A01:follow:PV1.ROL
* group.element[14].display = "Role"
* group.element[14].target.equivalence = #equivalent
* group.element[14].target.code = #Encounter[1]
* group.element[14].target.display = "Encounter[1]"
* group.element[15].code = #ADT_A01.OBX
* group.element[15].display = "Observation/Result"
* group.element[15].target.equivalence = #equivalent
* group.element[15].target.code = #Observation
* group.element[15].target.display = "Observation"
* group.element[15].target.dependsOn[0].property = "value"
* group.element[15].target.dependsOn[0].value = "Based on profiles, such as Vital Signs, certain observations are represented on the Observation, while others on its components.  No computable guidance available yet."
* group.element[16].code = #ADT_A01.OBX
* group.element[16].display = "Observation/Result"
* group.element[16].target.equivalence = #equivalent
* group.element[16].target.code = #Observation
* group.element[16].target.display = "Observation"
* group.element[16].target.dependsOn[0].property = "value"
* group.element[16].target.dependsOn[0].value = "Based on profiles, such as Vital Signs, certain observations are represented on the Observation, while others on its components.  No computable guidance available yet."
* group.element[17].code = #ADT_A01.AL1
* group.element[17].display = "Allergy Information"
* group.element[17].target.equivalence = #equivalent
* group.element[17].target.code = #AllergyIntolerance
* group.element[17].target.display = "AllergyIntolerance"
* group.element[18].code = #ADT_A01.DG1
* group.element[18].display = "Diagnosis Information"
* group.element[18].target.equivalence = #equivalent
* group.element[18].target.code = #Condition
* group.element[18].target.display = "Condition"
* group.element[18].target.dependsOn[0].property = "value"
* group.element[18].target.dependsOn[0].value = "If in context of the patient"
* group.element[19].code = #ADT_A01.DG1
* group.element[19].display = "Diagnosis Information"
* group.element[19].target.equivalence = #equivalent
* group.element[19].target.code = #Condition[1]
* group.element[19].target.display = "Condition[1]"
* group.element[19].target.dependsOn[0].property = "value"
* group.element[19].target.dependsOn[0].value = """
# IF DG1-6 EQUALS "admitting"\
"""
* group.element[20].code = #ADT_A01.DG1
* group.element[20].display = "Diagnosis Information"
* group.element[20].target.equivalence = #equivalent
* group.element[20].target.code = #Condition[1]
* group.element[20].target.display = "Condition[1]"
* group.element[20].target.dependsOn[0].property = "value"
* group.element[20].target.dependsOn[0].value = "If in context of an encounter"
* group.element[21].code = #ADT_A01.DG1
* group.element[21].display = "Diagnosis Information"
* group.element[21].target.equivalence = #equivalent
* group.element[21].target.code = #Condition[1]
* group.element[21].target.display = "Condition[1]"
* group.element[21].target.dependsOn[0].property = "value"
* group.element[21].target.dependsOn[0].value = "If in context of a episode of care"
* group.element[22].code = #ADT_A01.PROCEDURE.PR1
* group.element[22].display = "Procedures"
* group.element[22].target.equivalence = #equivalent
* group.element[22].target.code = #Procedure
* group.element[22].target.display = "Procedure"
* group.element[23].code = #ADT_A01.INSURANCE.IN1
* group.element[23].display = "Insurance"
* group.element[23].target.equivalence = #equivalent
* group.element[23].target.code = #Coverage
* group.element[23].target.display = "Coverage"
* group.element[24].code = #ADT_A01.INSURANCE.IN2
* group.element[24].display = "Insurance Additional Info."
* group.element[24].target.equivalence = #equivalent
* group.element[24].target.code = #Coverage
* group.element[24].target.display = "Coverage"
* group.element[25].code = #ADT_A01.INSURANCE.IN3
* group.element[25].display = "Insurance Additional Info - Cert."
* group.element[25].target.equivalence = #equivalent
* group.element[25].target.code = #Coverage
* group.element[25].target.display = "Coverage"
