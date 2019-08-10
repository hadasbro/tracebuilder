package com.github.hadasbro.tracebuilder;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TraceBuilderObjectTest {
    @Test
    public void generalTest() {
        // test normal object

        TraceBuilder tb2 = new TraceBuilder();

        tb2.addTracePackage("com.github.hadasbro")
                .addException(new Exception("Test exception tb2"))
                .addInfo("My test log tb2")
                .setRequest("Request tb2")
                .setResponse("Response tb2")
                .build();

        assertEquals(tb2.toString(),
                "TraceBuilder{request='Request tb2', response='Response tb2', trace='null', " +
                        "exceptions=[java.lang.Exception: Test exception tb2], info={Info#1=My test log tb2}}");

    }
}
