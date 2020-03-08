
This ConceptMap represents the mapping from the HL7 V2 null to the FHIR null. See also the <a href='https://github.com/HL7/v2-to-fhir/blob/master/tank/Datatype XTN to ContactPoint[1].fsh'>FHIR Shorthand</a> or the <a href='https://github.com/HL7/v2-to-fhir/blob/master/mappings/datatypes/HL7 Data Type - FHIR R4_ XTN - Sheet1.csv'>CSV Source</a>.
<table class='grid'><thead>
<tr><th colspan='6'>HL7 v2</th><th colspan='3'>Condition (IF True, args)</th><th colspan='7'>HL7 FHIR</th><th>&#xA0;</th><th>Comments</th></tr>
<tr><th title='Rows are listed in sequence of how they appear in the v2 standard. The first column, Sort Order, provides a sort order that can re-create the original v2 standard sequence in case one opts to re-sort/filter the rows.'>Sort Order</th><th title='Contains the formal Data Type Name and Component Sequence according to the base standard using "." as the delimiter.'>Identifier</th><th title='The formal name of the field in the most current published version.'>Name</th><th title='The data type of the field in the most current published version if not deprecated, otherwise the data type at the time it was deprecated and removed.'>Data Type</th><th title='The V2 min cardinality expressed numerically.'>Cardinality - Min</th><th title='The V2 max cardinality expressed numerically.' style='border-right: 2px'>Cardinality - Max</th><th title='Condition in an easy to read syntax (Computable ANTLR)'>Computable ANTLR</th><th title='Condition in FHIRPath Notation'>Computable FHIRPath</th><th title='Condition expressed in narrative form' style='border-right: 2px'>Narrative</th><th title='An existing FHIR attribute in the target FHIR version.'>FHIR Attribute</th><th title='The FHIR attribute’s data type in the target FHIR version.'>Data Type</th><th title='The FHIR min cardinality expressed numerically.'>Cardinality - Min</th><th title='The FHIR max cardinality expressed numerically.' style='border-right: 2px'>Cardinality - Max</th><th title='The URL to the Data Type Map that is to be used for the attribute in this segment.'>Data Type Mapping</th></tr></thead>
<tbody>
<tr><td>1</td><td>XTN.1</td><td>Telephone Number</td><td>ST</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td>ContactPoint[1].value</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>2</td><td>XTN.2</td><td>Telecommunication Use Code</td><td>ID</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[1].use</td><td></td><td>code</td><td>0</td><td>1</td><td></td><td>Telecom Use Code</td><td></td><td></td></tr>
<tr><td>2</td><td>XTN.2</td><td>Telecommunication Use Code</td><td>ID</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.4 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[2].use</td><td></td><td>code</td><td>0</td><td>1</td><td></td><td>Telecom Use Code</td><td></td><td></td></tr>
<tr><td>2</td><td>XTN.2</td><td>Telecommunication Use Code</td><td>ID</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.12 NOT EQUALS XTN.1 VALUED AND XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[3].use</td><td></td><td>code</td><td>0</td><td>1</td><td></td><td>Telecom Use Code</td><td></td><td></td></tr>
<tr><td>3</td><td>XTN.3</td><td>Telecommunication Equipment Type</td><td>ID</td><td>1</td><td style='border-right: 2px'>1</td><td>IF XTN.3 NOT EQUALS "CP" AND XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[1].use</td><td></td><td>code</td><td>0</td><td>1</td><td></td><td>Telecom Equip Type</td><td></td><td></td></tr>
<tr><td>3</td><td>XTN.3</td><td>Telecommunication Equipment Type</td><td>ID</td><td>1</td><td style='border-right: 2px'>1</td><td>IF XTN.3 NOT EQUALS "CP" AND XTN.4 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[2].use</td><td></td><td>code</td><td>0</td><td>1</td><td></td><td>Telecom Equip Type</td><td></td><td></td></tr>
<tr><td>3</td><td>XTN.3</td><td>Telecommunication Equipment Type</td><td>ID</td><td>1</td><td style='border-right: 2px'>1</td><td>IF XTN.3 NOT EQUALS "CP" AND ANDT XTN.1 VALUED AND XTN.12 NOT EQUALTS XTN.1</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[3].use</td><td></td><td>code</td><td>0</td><td>1</td><td></td><td>Telecom Equip Type</td><td></td><td></td></tr>
<tr><td>3</td><td>XTN.3</td><td>Telecommunication Equipment Type</td><td>ID</td><td>1</td><td style='border-right: 2px'>1</td><td>IF XTN.3 NOT EQUALS "CP" AND XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[1].system</td><td></td><td>code</td><td>0</td><td>1</td><td></td><td>Telecom Equip Type</td><td></td><td></td></tr>
<tr><td>3</td><td>XTN.3</td><td>Telecommunication Equipment Type</td><td>ID</td><td>1</td><td style='border-right: 2px'>1</td><td>IF XTN.3 NOT EQUALS "CP" AND XTN.4 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[2].system</td><td></td><td>code</td><td>0</td><td>1</td><td></td><td>Telecom Equip Type</td><td></td><td></td></tr>
<tr><td>3</td><td>XTN.3</td><td>Telecommunication Equipment Type</td><td>ID</td><td>1</td><td style='border-right: 2px'>1</td><td>IF XTN.3 NOT EQUALS "CP" AND XTN.1 VALUED AND XTN.12 NOT EQUALS XTN.1</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[3].system</td><td></td><td>code</td><td>0</td><td>1</td><td></td><td>Telecom Equip Type</td><td></td><td></td></tr>
<tr><td>4</td><td>XTN.4</td><td>Communication Address</td><td>ST</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td>ContactPoint[2].value</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>5</td><td>XTN.5</td><td>Country Code</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[1].extension-contactpoint-country</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>5</td><td>XTN.5</td><td>Country Code</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.4 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[2].extension-contactpoint-country</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>5</td><td>XTN.5</td><td>Country Code</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.12 NOT EQUALS XTN.1 VALUED AND XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[3].extension-contactpoint-country</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>6</td><td>XTN.6</td><td>Area/City Code</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[1].extension-contactpoint-area</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>6</td><td>XTN.6</td><td>Area/City Code</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.4 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[2].extension-contactpoint-area</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>6</td><td>XTN.6</td><td>Area/City Code</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.12 NOT EQUALS XTN.1 VALUED AND XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[3].extension-contactpoint-area</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>7</td><td>XTN.7</td><td>Local Number</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[1].extension-contactpoint-local</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>7</td><td>XTN.7</td><td>Local Number</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.4 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[2].extension-contactpoint-local</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>7</td><td>XTN.7</td><td>Local Number</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.12 NOT EQUALS XTN.1 VALUED AND XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[3].extension-contactpoint-local</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>8</td><td>XTN.8</td><td>Extension</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[1].extension-contactpoint-extension</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>8</td><td>XTN.8</td><td>Extension</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.4 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[2].extension-contactpoint-extension</td><td></td><td>string</td><td>0</td><td>1</td><td></td><td></td><td></td><td></td></tr>
<tr><td>8</td><td>XTN.8</td><td>Extension</td><td>SNM</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.12 NOT EQUALS XTN.1 VALUED AND XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[3].extension-contactpoint-extension</td><td></td><td>string</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>9</td><td>XTN.9</td><td>Any Text</td><td>ST</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td></td><td>ContactPoint[1].#ext.text#</td><td>string</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>9</td><td>XTN.9</td><td>Any Text</td><td>ST</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.4 VALUED</td><td></td><td style='border-right: 2px'></td><td></td><td>ContactPoint[2].#ext.text#</td><td>string</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>9</td><td>XTN.9</td><td>Any Text</td><td>ST</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.12 NOT EQUALS XTN.1 VALUED AND XTN.1 VALUED</td><td></td><td style='border-right: 2px'></td><td></td><td>ContactPoint[3].#ext.text#</td><td>string</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>10</td><td>XTN.10</td><td>Extension Prefix</td><td>ST</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>11</td><td>XTN.11</td><td>Speed Dial Code</td><td>ST</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>12</td><td>XTN.12</td><td>Unformatted Telephone number</td><td>ST</td><td>0</td><td style='border-right: 2px'>1</td><td>IF XTN.12 NOT EQUALS XTN.1</td><td></td><td style='border-right: 2px'></td><td>ContactPoint[3].value</td><td></td><td>string</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>13</td><td>XTN.13</td><td>Effective Start Date</td><td>DTM</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td>ContactPoint[1].period.start</td><td></td><td>dateTime</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>13</td><td>XTN.13</td><td>Effective Start Date</td><td>DTM</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td>ContactPoint[2].period.start</td><td></td><td>dateTime</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>13</td><td>XTN.13</td><td>Effective Start Date</td><td>DTM</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td>ContactPoint[3].period.start</td><td></td><td>dateTime</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>14</td><td>XTN.14</td><td>Expiration Date</td><td>DTM</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td>ContactPoint[1].period.end</td><td></td><td>dateTime</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>14</td><td>XTN.14</td><td>Expiration Date</td><td>DTM</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td>ContactPoint[2].period.end</td><td></td><td>dateTime</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>14</td><td>XTN.14</td><td>Expiration Date</td><td>DTM</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td>ContactPoint[3].period.end</td><td></td><td>dateTime</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>15</td><td>XTN.15</td><td>Expiration Reason</td><td>CWE</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>16</td><td>XTN.16</td><td>Protection Code</td><td>CWE</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>17</td><td>XTN.17</td><td>Shared Telecommunication Identifier</td><td>EI</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>18</td><td>XTN.18</td><td>Preference Order</td><td>NM</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td>ContactPoint[1].rank</td><td></td><td>positiveInt</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>18</td><td>XTN.18</td><td>Preference Order</td><td>NM</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td>ContactPoint[2].rank</td><td></td><td>positiveInt</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
<tr><td>18</td><td>XTN.18</td><td>Preference Order</td><td>NM</td><td>0</td><td style='border-right: 2px'>1</td><td></td><td></td><td style='border-right: 2px'></td><td>ContactPoint[3].rank</td><td></td><td>positiveInt</td><td>0..1</td><td></td><td></td><td></td><td></td><td></td></tr>
</tbody></table>