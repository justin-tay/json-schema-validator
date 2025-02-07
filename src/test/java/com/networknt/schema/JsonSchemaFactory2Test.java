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
package com.networknt.schema;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.management.MBeanServer;

import org.junit.jupiter.api.Test;

import com.networknt.schema.SpecVersion.VersionFlag;

/**
 * Tests for JsonSchemaFactory.
 */
class JsonSchemaFactory2Test {
    static JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V202012);

    //@Test
    void dump() throws URISyntaxException {
        cache();
        dumpHeap("d:/dump/heap.hprof", true);
    }
    
    void cache() throws URISyntaxException {
        String input = "{\r\n"
                + "  \"properties\": {\r\n"
                + "    \"field1\": {\r\n"
                + "      \"type\": \"invalid-type\",\r\n"
                + "      \"description\": \"string\"\r\n"
                + "    }\r\n"
                + "  }\r\n"
                + "}";
        //SchemaValidatorsConfig config = SchemaValidatorsConfig.builder().build();
        JsonSchema schema = factory.getSchema(new URI("classpath:schema/issue1091.json"));
        schema.validate(input, InputFormat.JSON);
        for (int x = 0; x < 20; x++) {
            schema = factory.getSchema(new URI("classpath:schema/issue1091.json"));
            schema.validate(input, InputFormat.JSON);
        }
    }
    
    // This is the name of the HotSpot Diagnostic MBean
    private static final String HOTSPOT_BEAN_NAME =
         "com.sun.management:type=HotSpotDiagnostic";

    // field to store the hotspot diagnostic MBean 
    private static volatile Object hotspotMBean;

    /**
     * Call this method from your application whenever you 
     * want to dump the heap snapshot into a file.
     *
     * @param fileName name of the heap dump file
     * @param live flag that tells whether to dump
     *             only the live objects
     */
    static void dumpHeap(String fileName, boolean live) {
        // initialize hotspot diagnostic MBean
        initHotspotMBean();
        try {
            Class clazz = Class.forName("com.sun.management.HotSpotDiagnosticMXBean");
            Method m = clazz.getMethod("dumpHeap", String.class, boolean.class);
            m.invoke( hotspotMBean , fileName, live);
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception exp) {
            throw new RuntimeException(exp);
        }
    }

    // initialize the hotspot diagnostic MBean field
    private static void initHotspotMBean() {
        if (hotspotMBean == null) {
            synchronized (JsonSchemaFactory2Test.class) {
                if (hotspotMBean == null) {
                    hotspotMBean = getHotspotMBean();
                }
            }
        }
    }

    // get the hotspot diagnostic MBean from the
    // platform MBean server
    private static Object getHotspotMBean() {
        try {
            Class clazz = Class.forName("com.sun.management.HotSpotDiagnosticMXBean");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            Object bean = 
                ManagementFactory.newPlatformMXBeanProxy(server,
                HOTSPOT_BEAN_NAME, clazz);
            return bean;
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception exp) {
            throw new RuntimeException(exp);
        }
    }
}
