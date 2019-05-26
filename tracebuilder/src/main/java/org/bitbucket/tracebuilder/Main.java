package org.bitbucket.tracebuilder;

public class Main {
    public static void main(String[] args) {

        // test singleton

        TraceBuilder tb = TraceBuilderSingleton.INSTANCE
                .addTracePackage("org.bitbucket.tracebuilder")
                .addException(new Exception("Test exception"))
                .addInfo("i1","My test log 1")
                .addInfo("i2","My test log 2")
                .setRequest("My setRequest as a string")
                .setResponse("My response as a string");

        System.out.println(tb.toString());


        // test normal object

        TraceBuilder tb2 = new TraceBuilder();

        tb2.addTracePackage("org.bitbucket.tracebuilder")
                .addException(new Exception("Test exception tb2"))
                .addInfo("My test log tb2")
                .setRequest("Request tb2")
                .setResponse("Response tb2")
                .build();

        System.out.println(tb2.toString());

    }
}
