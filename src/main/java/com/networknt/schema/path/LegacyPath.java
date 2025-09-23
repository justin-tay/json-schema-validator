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
 * Legacy path.
 */
public final class LegacyPath extends NodePath {
    private static class Holder {
        private static LegacyPath INSTANCE = new LegacyPath();
    }

    public static LegacyPath getRoot() {
        return Holder.INSTANCE;
    }

    public LegacyPath() {
        super();
    }

    public LegacyPath(LegacyPath path, String token) {
        super(path, token);
    }

    public LegacyPath(LegacyPath path, int token) {
        super(path, token);
    }

    @Override
    public NodePath append(String token) {
        return new LegacyPath(this, token);
    }

    @Override
    public NodePath append(int index) {
        return new LegacyPath(this, index);
    }

    @Override
    public PathType getPathType() {
        return PathType.LEGACY;
    }
}
