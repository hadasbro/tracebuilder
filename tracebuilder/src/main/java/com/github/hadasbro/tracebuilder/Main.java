package com.github.hadasbro.tracebuilder;

public class Main {
    public static void main(String[] args) {


        // test normal object

        TraceBuilder tb2 = new TraceBuilder();

        tb2.addTracePackage("com.github.hadasbro")
                .addException(new Exception("Test exception tb2"))
                .addInfo("My test log tb2")
                .setRequest("Request tb2")
                .setResponse("Response tb2")
                .build();

        System.out.println(tb2.toString());

    }
}
