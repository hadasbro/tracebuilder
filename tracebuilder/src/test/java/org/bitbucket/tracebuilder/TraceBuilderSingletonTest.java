package org.bitbucket.tracebuilder;

import org.hamcrest.CoreMatchers;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class TraceBuilderSingletonTest {

    @Test
    public void generalTest() {

        TraceBuilder tb = TraceBuilderSingleton.INSTANCE
                .addException(new Exception("Test exception"))
                .addException(new Exception("Test exception 2"))
                .addInfo("i1","test-log1")
                .addInfo("test-log2")
                .setRequest("test-setRequest")
                .setResponse("test-response")
                .addTrace();

        String tbString = tb.toString();

        assertThat(tbString, CoreMatchers.containsString("test-log1"));
        assertThat(tbString, CoreMatchers.containsString("test-log2"));
        assertThat(tbString, CoreMatchers.containsString("test-setRequest"));
        assertThat(tbString, CoreMatchers.containsString("test-response"));
        assertThat(tbString, CoreMatchers.containsString("Test exception"));

    }

    @Test
    public void testStackTrace() {

        TraceBuilder tb = TraceBuilderSingleton.INSTANCE
                .addTracePackage("org.bitbucket.tracebuilder")
                .setRequest("test-setRequest")
                .setResponse("test-response")
                .addTrace();

        String tbString = tb.toString();

        assertThat(tbString, CoreMatchers.containsString("org.bitbucket.tracebuilder"));

    }

}
