package org.bitbucket.tracebuilder;

import org.hamcrest.CoreMatchers;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class TraceBuilderTest {

    @Test
    public void generalTest() {

        TraceBuilder tb = TraceBuilder.INSTANCE
                .exceptions(new Exception("Test exception"))
                .info("i1","test-log1")
                .info("i2","test-log2")
                .request("test-request")
                .response("test-response");

        String tbString = tb.toString();

        assertThat(tbString, CoreMatchers.containsString("test-log1"));
        assertThat(tbString, CoreMatchers.containsString("test-log2"));
        assertThat(tbString, CoreMatchers.containsString("test-request"));
        assertThat(tbString, CoreMatchers.containsString("test-response"));


    }

    @Test
    public void testStackTrace() {

        TraceBuilder tb = TraceBuilder.INSTANCE
                .setTracePackage("org.bitbucket.tracebuilder")
                .request("test-request")
                .response("test-response");

        String tbString = tb.toString();

        assertThat(tbString, CoreMatchers.containsString("org.bitbucket.tracebuilder"));

    }

}
