package com.github.hadasbro.tracebuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TraceBuilderSingleton singleton
 *
 * this is an acumulator for requests, responses, addException etc.
 * you can use this class to store the setRequest's data and then
 * save it to DB or to logs etc.
 *
 * usage:
 *
 * TraceBuilderSingleton.INSTANCE
 *      .addTracePackage("com.github.hadasbro")
 *      .addException(new Exception())
 *      .addInfo("i1", "My addInfo, log")
 *      .addInfo("i1", "My log 2")
 *      .setRequest("Request as a string")
 *      .response("Response as a string");
 *
 *  then you can log in acumulated result to DB or do whatever
 *
 *  DB.insert((String)TraceBuilderSingleton.INSTANCE);
 *
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public enum TraceBuilderSingleton implements TraceBuilderInterface{

    INSTANCE;

    private static TraceBuilder traceBuilder;

    static {
           if(traceBuilder == null){
               traceBuilder = new TraceBuilder();
           }
    }

    @Override
    public TraceBuilder setTracePackages(ArrayList<String> trancePackages) {
        return traceBuilder.setTracePackages(trancePackages);
    }

    @Override
    public TraceBuilder addTracePackage(String trancePackage) {
        return traceBuilder.addTracePackage(trancePackage);
    }

    @Override
    public String getCurrentTrace() {
        return traceBuilder.getCurrentTrace();
    }

    @Override
    public TraceBuilder addException(Exception exception) {
        return traceBuilder.addException(exception);
    }

    @Override
    public TraceBuilder addInfo(String info) {
        return traceBuilder.addInfo(info);
    }

    @Override
    public TraceBuilder addInfo(String key, String info) {
        return traceBuilder.addInfo(key, info);
    }

    @Override
    public TraceBuilder setRequest(String request) {
        return traceBuilder.setRequest(request);
    }

    @Override
    public ArrayList<String> getTrancePackages() {
        return traceBuilder.getTrancePackages();
    }

    @Override
    public TraceBuilder setTrancePackages(ArrayList<String> trancePackages) {
        return traceBuilder.setTrancePackages(trancePackages);
    }

    @Override
    public String getRequest() {
        return traceBuilder.getRequest();
    }

    @Override
    public String getResponse() {
        return traceBuilder.getResponse();
    }

    @Override
    public TraceBuilder setResponse(String response) {
        return traceBuilder.setResponse(response);
    }

    @Override
    public String getTrace() {
        return traceBuilder.getTrace();
    }

    @Override
    public TraceBuilder setTrace(String trace) {
        return traceBuilder.setTrace(trace);
    }

    @Override
    public List<Exception> getExceptions() {
        return traceBuilder.getExceptions();
    }

    @Override
    public TraceBuilder setExceptions(List<Exception> exceptions) {
        return traceBuilder.setExceptions(exceptions);
    }

    @Override
    public Map<String, String> getInfo() {
        return traceBuilder.getInfo();
    }

    @Override
    public TraceBuilder addInfo(Map<String, String> info) {
        return traceBuilder.addInfo(info);
    }

    /**
     * addTrace
     *
     * @return TraceBuilder
     */
    public TraceBuilder addTrace() {
        return traceBuilder.addTrace();
    }
}
