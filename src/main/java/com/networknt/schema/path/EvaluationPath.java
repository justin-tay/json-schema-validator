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
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * The evaluation path.
 */
public class EvaluationPath implements Path {
    private final ArrayDeque<Object> evaluationPath;
    private volatile String value = null; // computed lazily
    
    protected EvaluationPath(ArrayDeque<Object> evaluationPath, boolean attach) {
        this.evaluationPath = evaluationPath;
    }

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

    public EvaluationPath getParent() {
        ArrayDeque<Object> parent = this.evaluationPath.clone();
        parent.removeLast();
        return new EvaluationPath(parent, true);
    }
    
    /**
     * Checks if this EvaluationPath starts with the segments contained in the given prefix collection.
     * The comparison is done element-by-element from the beginning of the path.
     *
     * @param prefix The collection of objects representing the path prefix to check.
     * @return true if this path begins with the elements in the prefix, false otherwise.
     */
    public boolean startsWith(Collection<?> prefix) {
        // An empty or null prefix always matches
        if (prefix == null || prefix.isEmpty()) {
            return true;
        }
        
        // A prefix cannot be longer than the path itself
        if (prefix.size() > this.evaluationPath.size()) {
            return false;
        }

        // Use iterators for efficient, sequential, element-wise comparison
        Iterator<Object> pathIt = this.evaluationPath.iterator();
        Iterator<?> prefixIt = prefix.iterator();

        while (prefixIt.hasNext()) {
            // Get the next segment from both the path and the prefix
            Object pathSegment = pathIt.next(); // pathIt.hasNext() is guaranteed by the size check
            Object prefixSegment = prefixIt.next();

            // Compare segments using Objects.equals() for null-safe and correct equality
            if (!Objects.equals(pathSegment, prefixSegment)) {
                return false;
            }
        }

        // If the entire prefix was iterated through without mismatch, it's a prefix of this path.
        return true;
    }
    
    /**
     * Convenience method to check if this EvaluationPath starts with another EvaluationPath.
     *
     * @param other The other EvaluationPath to check as a prefix.
     * @return true if this path starts with the other path, false otherwise.
     */
    public boolean startsWith(EvaluationPath other) {
        if (other == null) {
            return true; // A null path is treated as an empty prefix.
        }
        // Delegate the check to the Collection-based method
        return startsWith(other.evaluationPath);
    }

    public String toString() {
        if (this.value == null) {
            StringBuilder builder = new StringBuilder();
            for (Object item : evaluationPath) {
                if (item instanceof Number) {
                    PathType.JSON_POINTER.append(builder, ((Number) item).intValue());
                } else {
                    PathType.JSON_POINTER.append(builder, item.toString());
                }
            }
            this.value = builder.toString();
        }
        return this.value;
    }

    public int length() {
        return this.evaluationPath.size();
    }

    public EvaluationPath append(Object segment) {
        ArrayDeque<Object> copy = this.evaluationPath.clone();
        copy.addLast(segment);
        return new EvaluationPath(copy, true);
    }
}
