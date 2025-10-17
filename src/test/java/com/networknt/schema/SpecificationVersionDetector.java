/*
 * Copyright (c) 2016 Network New Technologies Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.networknt.schema;

import tools.jackson.databind.JsonNode;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This class is used to detect schema version
 *
 * @author Subhajitdas298
 * @since 25/06/20
 */
public final class SpecificationVersionDetector {

    private static final Map<String, SpecificationVersion> supportedVersions = new HashMap<>();
    private static final String SCHEMA_TAG = "$schema";

    static {
        supportedVersions.put("draft2019-09", SpecificationVersion.DRAFT_2019_09);
        supportedVersions.put("draft2020-12", SpecificationVersion.DRAFT_2020_12);
        supportedVersions.put("draft4", SpecificationVersion.DRAFT_4);
        supportedVersions.put("draft6", SpecificationVersion.DRAFT_6);
        supportedVersions.put("draft7", SpecificationVersion.DRAFT_7);
    }

    private SpecificationVersionDetector() {
        // Prevent instantiation of this utility class
    }

    /**
     * Detects schema version based on the schema tag: if the schema tag is not present, throws
     * {@link SchemaException} with the corresponding message, otherwise - returns the detected spec version.
     *
     * @param jsonNode JSON Node to read from
     * @return Spec version if present, otherwise throws an exception
     */
    public static SpecificationVersion detect(JsonNode jsonNode) {
        return detectOptionalVersion(jsonNode, true).orElseThrow(
                () -> new SchemaException("'" + SCHEMA_TAG + "' tag is not present")
        );
    }

    /**
     * Detects schema version based on the schema tag: if the schema tag is not present, returns an empty {@link
     * Optional} value, otherwise - returns the detected spec version wrapped into {@link Optional}.
     *
     * @param jsonNode JSON Node to read from
     * @param throwIfUnsupported whether to throw an exception if the version is not supported
     * @return Spec version if present, otherwise empty
     */
    public static Optional<SpecificationVersion> detectOptionalVersion(JsonNode jsonNode, boolean throwIfUnsupported) {
        return Optional.ofNullable(jsonNode.get(SCHEMA_TAG)).map(schemaTag -> {

            String schemaTagValue = schemaTag.asString();
            String schemaUri = SchemaRegistry.normalizeDialectId(schemaTagValue);

            if (throwIfUnsupported) {
                return SpecificationVersion.fromDialectId(schemaUri)
                        .orElseThrow(() -> new SchemaException("'" + schemaTagValue + "' is unrecognizable schema"));
            } else {
                return SpecificationVersion.fromDialectId(schemaUri).orElse(null);
            }
        });
    }


    // For 2019-09 and later published drafts, implementations that are able to
    // detect the draft of each schema via $schema SHOULD be configured to do so
    public static SpecificationVersion detectVersion(JsonNode jsonNode, Path specification, SpecificationVersion defaultVersion, boolean throwIfUnsupported) {
        return Stream.of(
                        detectOptionalVersion(jsonNode, throwIfUnsupported),
                        detectVersionFromPath(specification)
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElse(defaultVersion);
    }

    // For draft-07 and earlier, draft-next, and implementations unable to
    // detect via $schema, implementations MUST be configured to expect the
    // draft matching the test directory name
    public static Optional<SpecificationVersion> detectVersionFromPath(Path path) {
        return StreamSupport.stream(path.spliterator(), false)
                .map(Path::toString)
                .map(supportedVersions::get) 
                .filter(Objects::nonNull)
                .findAny();
    }
}
