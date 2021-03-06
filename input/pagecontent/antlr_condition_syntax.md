The code below contains the ANTLR grammar for Conditions in the V2 to FHIR Implementation
Guide.

```
// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

grammar HL7v2ToFHIRConditions
;

// Not sure if IF and THEN are necessary, they seem redundant
IF
    : 'IF'
;

THEN
    : 'THEN'
;

FIRST_FIELD_DELIM
    : '-'
;

DECIMAL
  : '.'
;

LIST_DELIM
    : ','
;

DIGIT
  : INTEGER
;

STRING
  : '"' STRINGCHAR* '"'
 ;

BINARY_OPERATOR_1
    : 'EQUAL'
    | 'NOT EQUAL'
;

BINARY_OPERATOR_2
    : 'AND'
    | 'OR'
;

LIST_OPERATOR
    : 'IN'
;

NOT
    : 'NOT'
;

VALUED
    : 'VALUED'
;

fragment
INTEGER
  : [0-9]+
  ;

fragment
STRINGCHAR
  : ~["\\]
  | '\\' ["\\]
  ;

DATATYPE
    : [A-Z][A-Z0-9][A-Z0-9]?
;

// HL7v2 element reference rules.
field
    : DIGIT
;

datatype
    : DATATYPE
;

// A nicely structured reference to an HL7v2 element. Do we want to specialize repeated field refs?
v2ref
    : datatype (FIRST_FIELD_DELIM field (DECIMAL field)*)?
;

list
    : '()'                                  # EmptyList
    | '(' v2ref (LIST_DELIM v2ref)* ')'     # RefElementList
    | '(' STRING (LIST_DELIM STRING)* ')'   # StrElementList
    | '(' DIGIT (LIST_DELIM DIGIT)* ')'   # NumElementList

;

constant
  : STRING                                  # ConstString
  | DIGIT (DECIMAL DIGIT)?                  # ConstInt
;

expression
    : expression BINARY_OPERATOR_1 expression # BiOpExpr
    | expression BINARY_OPERATOR_2 expression # BiOpExpr
    | NOT expression                          # UnOpExpr
    | v2ref NOT? LIST_OPERATOR list           # ListExpr
    | '(' expression ')'                      # NestExpr
    | v2ref                                   # RefExpr
    | v2ref NOT? VALUED                       # ExistRef
    | constant                                # ConstExpr
;

// condition is our "root" rule.
condition
    : IF expression
;

Whitespace
    : [ \t]+ -> skip
;
```

<div id="disqus_thread"></div>
<script>
var disqus_config = function () {
this.page.url = "http://build.fhir.org.hl7/v2-to-fhir/branches/master/antlr_condition_syntax.html"; // Replace PAGE_URL with your page's canonical URL variable
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