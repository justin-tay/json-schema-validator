## Compatibility with JSON Schema versions

This implementation does not currently generate annotations.

The `pattern` validator by default uses the JDK regular expression implementation which is not ECMA-262 compliant and is thus not compliant with the JSON Schema specification. The library can however be configured to use a ECMA-262 compliant regular expression implementation.

### Known Issues
* The `anyOf` applicator currently returns immediately on matching a schema. This results in the `unevaluatedItems` and `unevaluatedProperties` keywords potentially returning an incorrect result as the rest of the schemas in the `anyOf` aren't processed.
* The `unevaluatedItems` keyword does not currently consider `contains`.


### Legend

| Symbol | Meaning               |
|:------:|:----------------------|
|   🟢   | Fully implemented     |
|   🟡   | Partially implemented |
|   🔴   | Not implemented       |
|   🚫   | Not defined           |

### Keywords Support

| Keyword                    | Draft 4 | Draft 6 | Draft 7 | Draft 2019-09 | Draft 2020-12 |
|:---------------------------|:-------:|:-------:|:-------:|:-------------:|:-------------:|
| $anchor                    | 🚫 | 🚫 | 🚫 | 🟢 | 🟢 |
| $dynamicAnchor             | 🚫 | 🚫 | 🚫 | 🚫 | 🟢 |
| $dynamicRef                | 🚫 | 🚫 | 🚫 | 🚫 | 🟢 |
| $id                        | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| $recursiveAnchor           | 🚫 | 🚫 | 🚫 | 🟢 | 🚫 |
| $recursiveRef              | 🚫 | 🚫 | 🚫 | 🟢 | 🚫 |
| $ref                       | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| $vocabulary                | 🚫 | 🚫 | 🚫 | 🟢 | 🟢 |
| additionalItems            | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| additionalProperties       | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| allOf                      | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| anyOf                      | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| const                      | 🚫 | 🟢 | 🟢 | 🟢 | 🟢 |
| contains                   | 🚫 | 🟢 | 🟢 | 🟢 | 🟢 |
| contentEncoding            | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |
| contentMediaType           | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |
| contentSchema              | 🚫 | 🚫 | 🚫 | 🟢 | 🟢 |
| definitions                | 🟢 | 🟢 | 🟢 | 🚫 | 🚫 |
| defs                       | 🚫 | 🚫 | 🚫 | 🟢 | 🟢 |
| dependencies               | 🟢 | 🟢 | 🟢 | 🚫 | 🚫 |
| dependentRequired          | 🚫 | 🚫 | 🚫 | 🟢 | 🟢 |
| dependentSchemas           | 🚫 | 🚫 | 🚫 | 🟢 | 🟢 |
| enum                       | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| exclusiveMaximum (boolean) | 🟢 | 🚫 | 🚫 | 🚫 | 🚫 |
| exclusiveMaximum (numeric) | 🚫 | 🟢 | 🟢 | 🟢 | 🟢 |
| exclusiveMinimum (boolean) | 🟢 | 🚫 | 🚫 | 🚫 | 🚫 |
| exclusiveMinimum (numeric) | 🚫 | 🟢 | 🟢 | 🟢 | 🟢 |
| if-then-else               | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |
| items                      | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| maxContains                | 🚫 | 🚫 | 🚫 | 🟢 | 🟢 |
| minContains                | 🚫 | 🚫 | 🚫 | 🟢 | 🟢 |
| maximum                    | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| maxItems                   | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| maxLength                  | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| maxProperties              | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| minimum                    | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| minItems                   | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| minLength                  | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| minProperties              | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| multipleOf                 | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| not                        | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| oneOf                      | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| pattern                    | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| patternProperties          | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| prefixItems                | 🚫 | 🚫 | 🚫 | 🚫 | 🟢 |
| properties                 | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| propertyNames              | 🚫 | 🟢 | 🟢 | 🟢 | 🟢 |
| readOnly                   | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |
| required                   | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| type                       | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| unevaluatedItems           | 🚫 | 🚫 | 🚫 | 🟢 | 🟢 |
| unevaluatedProperties      | 🚫 | 🚫 | 🚫 | 🟢 | 🟢 |
| uniqueItems                | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| writeOnly                  | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |

#### Content Encoding

Since Draft 2019-09, the `contentEncoding` keyword does not generate assertions. As the implementation currently does not collect annotations this only generates assertions in Draft 7.

#### Content Media Type

Since Draft 2019-09, the `contentMediaType` keyword does not generate assertions. As the implementation currently does not collect annotations this only generates assertions in Draft 7.

#### Content Schema

The `contentSchema` keyword does not generate assertions. As the implementation currently does not collect annotations this doesn't do anything.

#### Pattern

By default the `pattern` keyword uses the JDK regular expression implementation validating regular expressions. 

This is not ECMA-262 compliant and is thus not compliant with the JSON Schema specification. This is however the more likely desired behavior as other logic will most likely be using the default JDK regular expression implementation to perform downstream processing.

The library can be configured to use a ECMA-262 compliant regular expression validator which is implemented using [joni](https://github.com/jruby/joni). This can be configured by setting `setEcma262Validator` to `true`.

This also requires adding the `joni` dependency.

```xml
<dependency>
    <!-- Used to validate ECMA 262 regular expressions -->
    <groupId>org.jruby.joni</groupId>
    <artifactId>joni</artifactId>
    <version>${version.joni}</version>
</dependency>
```

### Format

Since Draft 2019-09 the `format` keyword only generates annotations by default and does not generate assertions.

This can be configured on a schema basis by using a meta schema with the appropriate vocabulary.

| Version               | Vocabulary                                                    | Value             |
|:----------------------|---------------------------------------------------------------|-------------------|
| Draft 2019-09         | `https://json-schema.org/draft/2019-09/vocab/format`          | `true`            |
| Draft 2020-12         | `https://json-schema.org/draft/2020-12/vocab/format-assertion`| `true`/`false`    | 

This behavior can be overridden to generate assertions on a per-execution basis by setting the `setFormatAssertionsEnabled` to `true`.

| Format                | Draft 4 | Draft 6 | Draft 7 | Draft 2019-09 | Draft 2020-12 |
|:----------------------|:-------:|:-------:|:-------:|:-------------:|:-------------:|
| date                  | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |
| date-time             | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| duration              | 🚫 | 🚫 | 🚫 | 🟢 | 🟢 |
| email                 | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| hostname              | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| idn-email             | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |
| idn-hostname          | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |
| ipv4                  | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| ipv6                  | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| iri                   | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |
| iri-reference         | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |
| json-pointer          | 🚫 | 🟢 | 🟢 | 🟢 | 🟢 |
| relative-json-pointer | 🚫 | 🟢 | 🟢 | 🟢 | 🟢 |
| regex                 | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |
| time                  | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |
| uri                   | 🟢 | 🟢 | 🟢 | 🟢 | 🟢 |
| uri-reference         | 🚫 | 🟢 | 🟢 | 🟢 | 🟢 |
| uri-template          | 🚫 | 🟢 | 🟢 | 🟢 | 🟢 |
| uuid                  | 🚫 | 🚫 | 🟢 | 🟢 | 🟢 |

### Footnotes
1. Note that the validation are only optional for some of the keywords/formats.
2. Refer to the corresponding JSON schema for more information on whether the keyword/format is optional or not.

