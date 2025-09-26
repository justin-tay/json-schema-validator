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

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * The evaluation path.
 */
public class EvaluationPath implements Path {
    private final ArrayDeque<Object> evaluationPath;
    private volatile String value = null; // computed lazily

    public EvaluationPath(ArrayDeque<Object> evaluationPath) {
        this.evaluationPath = evaluationPath.clone();
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Object e : evaluationPath) {
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ArrayDeque<Object> other = ((EvaluationPath) obj).evaluationPath;
        if (this.evaluationPath.size() != other.size()) {
            return false;
        }

        Iterator<?> thisIterator = this.evaluationPath.iterator();
        Iterator<?> otherIterator = other.iterator();

        while (thisIterator.hasNext()) {
            Object thisElement = thisIterator.next();
            Object otherElement = otherIterator.next();

            if (thisElement == null) {
                if (otherElement != null) {
                    return false;
                }
            } else {
                if (!thisElement.equals(otherElement)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        if (this.value == null) {
            StringBuilder builder = new StringBuilder();
            for (Object item : evaluationPath) {
                builder.append("/");
                builder.append(item.toString());
            }
            this.value = builder.toString();
        }
        return this.value;
    }
}
