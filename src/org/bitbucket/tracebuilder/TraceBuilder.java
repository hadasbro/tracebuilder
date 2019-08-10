package org.bitbucket.tracebuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * TraceBuilder singleton
 *
 * this is an acumulator for requests, responses, exceptions etc.
 * you can use this class to store the request's data and then
 * save it to DB or to logs etc.
 *
 * usage:
 *
 * TraceBuilder.INSTANCE
 *      .setTracePackage("org.bitbucket.tracebuilder")
 *      .exceptions(new Exception())
 *      .info("i1", "My info, log")
 *      .info("i1", "My log 2")
 *      .request("Request as a string")
 *      .response("Response as a string");
 *
 *  then you can log in acumulated result to DB or do whatever
 *
 *  DB.insert((String)TraceBuilder.INSTANCE);
 *
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public enum TraceBuilder {

    INSTANCE;

    /**
     * TRACE_LIMIT
     */
    final private int TRACE_LIMIT = 30;

    /**
     * trancePackages
     */
    private ArrayList<String> trancePackages = new ArrayList<>(){{
        add("java.lang");
    }};

    /**
     * String request
     */
    private String request;

    /**
     * String response
     */
    private String response;

    /**
     * String trace
     */
    private String trace;

    /**
     * List<Exception> exceptions
     */
    private List<Exception> exceptions = new ArrayList<>();

    /**
     * Map<String, String> info
     */
    private Map<String, String> info = new HashMap<>();

    /**
     * AtomicInteger iCounter
     */
    private AtomicInteger iCounter = new AtomicInteger(0);

    /**
     * setTracePackages
     *
     * @param trancePackages - packages
     */
    public TraceBuilder setTracePackages(ArrayList<String> trancePackages){
        this.trancePackages = trancePackages;
        return this;
    }

    /**
     * setTracePackages
     *
     * @param trancePackage - package to scan
     * @return TraceBuilder
     */
    public TraceBuilder setTracePackage(String trancePackage){
        this.trancePackages.add(trancePackage);
        return this;
    }

    /**
     * getCurrentTrace
     *
     * @return String
     */
    public String getCurrentTrace(){

        Predicate<StackWalker.StackFrame> packageFiler = f -> {

            if(trancePackages.isEmpty()) {
                return true;
            }

            for(String pckg : trancePackages) {
                if(f.getClassName().startsWith(pckg)){
                    return true;
                }
            }

            return false;
        };

        /*
        get trace, filer only needed packages and collect to string
         */
        return StackWalker
                .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(
                        s -> s
                                .filter(packageFiler)
                                .limit(TRACE_LIMIT)
                                .collect(Collectors.toList())
                )
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(" | "));


    }

    /**
     * exceptions
     *
     * @param exception - exception
     * @return TraceBuilder
     */
    public TraceBuilder exceptions(Exception exception){
        this.exceptions.add(exception);
        return this;
    }

    /**
     * info
     *
     * @param info - info message
     * @return TraceBuilder
     */
    public TraceBuilder info(String info){
        this.info.put("info#" + iCounter.incrementAndGet(), info);
        return this;
    }

    /**
     * info
     *
     * @param key - info key
     * @param info - info message
     * @return TraceBuilder
     */
    public TraceBuilder info(String key, String info){
        this.info.put(key, info);
        return this;
    }

    /**
     * request
     *
     * @param request - request as a string
     * @return TraceBuilder
     */
    public TraceBuilder request(String request){
        this.request = request;
        return this;
    }

    /**
     * response
     *
     * @param response -response string
     * @return TraceBuilder - response string
     */
    public TraceBuilder response(String response){
        this.response = response;
        return this;
    }

    /**
     * trace
     *
     * @param trace - stack trace
     * @return TraceBuilder
     */
    public TraceBuilder trace(String trace){
        this.trace = trace;
        return this;
    }

    /**
     * toString
     *
     * @return String
     */
    @Override
    public String toString() {

        return "TraceBuilder{" +
                "request='" + request + '\'' +
                ", response='" + response + '\'' +
                ", all traces='" + trace + '\'' +
                ", trace='" + this.getCurrentTrace() + '\'' +
                ", exceptions=" + exceptions +
                ", info=" + info +
                '}';
    }

    /**
     * getTrancePackages
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getTrancePackages() {
        return trancePackages;
    }

    /**
     * setTrancePackages
     *
     * @param trancePackages - packages to scan
     */
    public void setTrancePackages(ArrayList<String> trancePackages) {
        this.trancePackages = trancePackages;
    }

    /**
     * getRequest
     *
     * @return String
     */
    public String getRequest() {
        return request;
    }

    /**
     * setRequest
     *
     * @param request - request as string
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * getResponse
     *
     * @return String
     */
    public String getResponse() {
        return response;
    }

    /**
     * setResponse
     *
     * @param response - response as string
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * getTrace
     *
     * @return String
     */
    public String getTrace() {
        return trace;
    }

    /**
     * setTrace
     *
     * @param trace - trace as string
     */
    public void setTrace(String trace) {
        this.trace = trace;
    }

    /**
     * getExceptions
     *
     * @return List<Exception>
     */
    public List<Exception> getExceptions() {
        return exceptions;
    }

    /**
     * setExceptions
     *
     * @param exceptions - exceptions
     */
    public void setExceptions(List<Exception> exceptions) {
        this.exceptions = exceptions;
    }

    /**
     * getInfo
     *
     * @return Map<String, String>
     */
    public Map<String, String> getInfo() {
        return info;
    }

    /**
     * setInfo
     *
     * @param info - info as string
     */
    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
}
