/*
 * Copyright (c) 2024 the original author or authors.
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
package com.networknt.schema.regex;

import java.nio.charset.Charset;

import org.jcodings.ApplyAllCaseFoldFunction;
import org.jcodings.CaseFoldCodeItem;
import org.jcodings.IntHolder;
import org.jcodings.specific.UTF8Encoding;
import org.jcodings.unicode.UnicodeEncoding;
import org.joni.exception.SyntaxException;

/**
 * ECMAEncoding.
 */
public final class ECMAEncoding extends UnicodeEncoding {
    private final UTF8Encoding delegate = UTF8Encoding.INSTANCE;

    public static final ECMAEncoding INSTANCE = new ECMAEncoding();

    protected ECMAEncoding() {
        super("UTF-8", 1, 4, null, null);
    }

    public int length(byte[] bytes, int p, int end) {
        return delegate.length(bytes, p, end);
    }

    public String getCharsetName() {
        return delegate.getCharsetName();
    }

    public boolean isNewLine(byte[] bytes, int p, int end) {
        return delegate.isNewLine(bytes, p, end);
    }

    public int length(byte c) {
        return delegate.length(c);
    }

    public boolean isCodeCType(int code, int ctype) {
        return delegate.isCodeCType(code, ctype);
    }

    public int codeToMbcLength(int code) {
        return delegate.codeToMbcLength(code);
    }

    public int mbcToCode(byte[] bytes, int p, int end) {
        return delegate.mbcToCode(bytes, p, end);
    }

    public Charset getCharset() {
        return delegate.getCharset();
    }

    public int propertyNameToCType(byte[] name, int p, int end) {
        return delegate.propertyNameToCType(name, p, end);
    }

    /**
     * Extracts code point into it's multibyte representation.
     * 
     * @param code  code
     * @param bytes bytes
     * @param p     p
     * @return character length for the given code point
     * @see org.joni.ScanEnvironment#convertBackslashValue(int)
     * @see <a href=
     *      "https://tc39.es/ecma262/#table-string-single-character-escape-sequences">String
     *      Single Character Escape Sequences</a>
     */
    public int codeToMbc(int code, byte[] bytes, int p) {
        /*
         * org.joni.ScanEnvironment#convertBackslashValue(int) allows certain escape
         * characters that are not specified in
         * https://tc39.es/ecma262/#table-string-single-character-escape-sequences.
         */
        if ((code & 0xffffff80) == 0) {
            switch (code) {
            case 'b':
            case 't':
            case 'n':
            case 'v':
            case 'f':
            case 'r':
            case '\\':
            case 's':
            case 'S':
            case 'd':
            case 'D':
            case 'w':
            case 'W':
                break;
            default:
                // Cannot distinguish \CG vs \a
                // eg: '\007', '\033'
                boolean unknownEscape = (('a' <= code && code <= 'z') || ('A' <= code && code <= 'Z'));
                if (unknownEscape) {
                    throw new SyntaxException("Invalid escape");
                }
            }
        }
        return delegate.codeToMbc(code, bytes, p);
    }

    public int mbcCaseFold(int flag, byte[] bytes, IntHolder pp, int end, byte[] fold) {
        return delegate.mbcCaseFold(flag, bytes, pp, end, fold);
    }

    public void applyAllCaseFold(int flag, ApplyAllCaseFoldFunction fun, Object arg) {
        delegate.applyAllCaseFold(flag, fun, arg);
    }

    public int[] ctypeCodeRange(int ctype, IntHolder sbOut) {
        return delegate.ctypeCodeRange(ctype, sbOut);
    }

    public int leftAdjustCharHead(byte[] bytes, int p, int s, int end) {
        return delegate.leftAdjustCharHead(bytes, p, s, end);
    }

    public boolean isReverseMatchAllowed(byte[] bytes, int p, int end) {
        return delegate.isReverseMatchAllowed(bytes, p, end);
    }

    public int strLength(byte[] bytes, int p, int end) {
        return delegate.strLength(bytes, p, end);
    }

    public int strCodeAt(byte[] bytes, int p, int end, int index) {
        return delegate.strCodeAt(bytes, p, end, index);
    }

    public byte[] toLowerCaseTable() {
        return delegate.toLowerCaseTable();
    }

    public CaseFoldCodeItem[] caseFoldCodesByString(int flag, byte[] bytes, int p, int end) {
        return delegate.caseFoldCodesByString(flag, bytes, p, end);
    }

    public boolean isMbcCrnl(byte[] bytes, int p, int end) {
        return delegate.isMbcCrnl(bytes, p, end);
    }

}
