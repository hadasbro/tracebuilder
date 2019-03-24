package org.bitbucket.tracebuilder;

public class Main {
    public static void main(String[] args) {

        TraceBuilder tb = TraceBuilder.INSTANCE
            .setTracePackage("org.bitbucket.tracebuilder")
            .exceptions(new Exception("Test exception"))
            .info("i1","My test log 1")
            .info("i2","My test log 2")
            .request("My request as a string")
            .response("My response as a string");

        System.out.println(tb.toString());
    }
}
