/*
 * Copyright (c) 2025 the original author or authors.
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
package com.networknt.schema.path;

import com.networknt.schema.NodePath;
import com.networknt.schema.PathType;

/**
 * UriReference path.
 */
public final class UriReferencePath extends NodePath {
    private static class Holder {
        private static UriReferencePath INSTANCE = new UriReferencePath();
    }

    public static UriReferencePath getRoot() {
        return Holder.INSTANCE;
    }

    public UriReferencePath() {
    }

    public UriReferencePath(UriReferencePath path, String token) {
        super(path, token);
    }

    public UriReferencePath(UriReferencePath path, int token) {
        super(path, token);
    }

    @Override
    public NodePath append(String token) {
        return new UriReferencePath(this, token);
    }

    @Override
    public NodePath append(int index) {
        return new UriReferencePath(this, index);
    }

    @Override
    public PathType getPathType() {
        return PathType.URI_REFERENCE;
    }
}
