/*
 * Copyright (c) 2023 the original author or authors.
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

import java.net.URI;

public class UriReference {
    public static final JsonNodePath ROOT = new JsonNodePath(PathType.URI_REFERENCE);

    /**
     * The relative uri reference to the document.
     */
    public static final JsonNodePath DOCUMENT = ROOT.resolve("#");

    /**
     * Converts a path from a uri reference {@link JsonNodePath}.
     *
     * @param uriReference the path
     * @return the path
     */
    public static JsonNodePath get(String uriReference) {
        JsonNodePath path = null;
        int schemeSeparator = uriReference.indexOf("://");
        if (schemeSeparator != -1) {
            int pathIndex = uriReference.indexOf('/', schemeSeparator + 3);
            if (pathIndex == -1) {
                return ROOT.resolve(uriReference);
            } else {
                path = ROOT.resolve(uriReference.substring(0, pathIndex));
                uriReference = uriReference.substring(pathIndex);
                if (uriReference.charAt(0) == '/' && uriReference.length() > 1) {
                    uriReference = uriReference.substring(1);
                }
            }
        }
        
        String[] values = uriReference.split("/");
        for (int x = 0; x < values.length; x++) {
            if (x == 0 && path == null) {
                if ("#".equals(values[x])) {
                    path = DOCUMENT;
                } else {
                    path = ROOT.resolve(values[x]);
                }
            } else {
                path = path.resolve(values[x]);
            }
        }
        if (uriReference.endsWith("/")) {
            path = path.resolve("");
        }
        return path;
    }
    
    public static String resolve(URI currentUri, String refValue) {
        String uri = currentUri.resolve(refValue).toString();
        if (refValue.equals(uri) && !refValue.contains(":")) {
            // This means resolve didn't work as the path is in the scheme specific part
            String baseUri = currentUri.toString();
            if (refValue.startsWith("#")) {
                uri = baseUri + refValue;
            } else {
                int slash = baseUri.lastIndexOf("/");
                if (slash != -1) {
                    baseUri = baseUri.substring(0, baseUri.lastIndexOf("/"));
                    uri = baseUri + refValue;
                } else {
                    if (!refValue.startsWith("/")) {
                        uri = baseUri + "/" + refValue;
                    } else {
                        uri = baseUri + refValue;
                    }
                }
            }
        }
        return uri;
    }

    public static JsonNodePath getBase(JsonNodePath schemaLocation) {
        JsonNodePath base = schemaLocation;
        while (base.getNameCount() > 0 && !base.getName(-1).contains("#")) {
            base = base.getParent();
        }
        if (base.getNameCount() == 0 || !base.getName(-1).contains("#")) {
            return schemaLocation;
        }
        return base;
    }
}
