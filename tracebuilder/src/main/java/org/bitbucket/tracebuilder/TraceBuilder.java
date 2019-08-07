package org.bitbucket.tracebuilder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * TraceBuilder
 *
 * Builder Pattern
 *
 * usage:
 *  new TraceBuilder()
 * 	.addTracePackage("org.bitbucket.tracebuilder")
 * 	.addException(new Exception("Test exception tb2"))
 * 	.addInfo("My test log tb2")
 * 	.setRequest("Request tb2")
 * 	.setResponse("Response tb2")
 * 	.build();
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class TraceBuilder extends TraceBuilderConfig implements TraceBuilderInterface {

    /**
     * trancePackages
     */
    private ArrayList<String> trancePackages = new ArrayList<>() {{
        addAll(Arrays.asList(defaultPackagesToScan));
    }};

    /**
     * String setRequest
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
     * List<Exception> addException
     */
    private List<Exception> exceptions = new ArrayList<>();

    /**
     * Map<String, String> addInfo
     */
    private Map<String, String> info = new HashMap<>();

    /**
     * AtomicInteger iCounter
     */
    private AtomicInteger iCounter = new AtomicInteger(0);

    /**
     * public no args constructor
     */
    public TraceBuilder() {}

    /**
     * private all args constructor
     *
     * @param trancePackages - trancePackages
     * @param request - request
     * @param response - response
     * @param trace - trace
     * @param exceptions - exceptions
     * @param info - info
     */
    private TraceBuilder(
            ArrayList<String> trancePackages,
            String request,
            String response,
            String trace,
            List<Exception> exceptions,
            Map<String, String> info
    ) {
        this.trancePackages = trancePackages;
        this.request = request;
        this.response = response;
        this.trace = trace;
        this.exceptions = exceptions;
        this.info = info;
    }

    /**
     * setTracePackages
     *
     * @param trancePackages - packages
     */
    public TraceBuilder setTracePackages(ArrayList<String> trancePackages) {
        this.trancePackages = trancePackages;
        return this;
    }

    /**
     * setTracePackages
     *
     * @param trancePackage - package to scan
     * @return TraceBuilder
     */
    public TraceBuilder addTracePackage(String trancePackage) {
        this.trancePackages.add(trancePackage);
        return this;
    }

    /**
     * getCurrentTrace
     *
     * @return String
     */
    public String getCurrentTrace() {

        Predicate<StackWalker.StackFrame> packageFiler = f -> {

            if (trancePackages.isEmpty()) {
                return true;
            }

            for (String pckg : trancePackages) {
                if (f.getClassName().startsWith(pckg)) {
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
     * addException
     *
     * @param exception - exception
     * @return TraceBuilder
     */
    public TraceBuilder addException(Exception exception) {
        this.exceptions.add(exception);
        return this;
    }

    /**
     * addInfo
     *
     * @param info - addInfo message
     * @return TraceBuilder
     */
    public TraceBuilder addInfo(String info) {
        this.info.put("Info#" + iCounter.incrementAndGet(), info);
        return this;
    }

    /**
     * addInfo
     *
     * @param key  - addInfo key
     * @param info - addInfo message
     * @return TraceBuilder
     */
    public TraceBuilder addInfo(String key, String info) {
        this.info.put(key, info);
        return this;
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
    public TraceBuilder setTrancePackages(ArrayList<String> trancePackages) {
        this.trancePackages = trancePackages;
        return this;
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
     * @param request - setRequest as string
     */
    public TraceBuilder setRequest(String request) {
        this.request = request;
        return this;
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
    public TraceBuilder setResponse(String response) {
        this.response = response;
        return this;
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
    public TraceBuilder setTrace(String trace) {
        this.trace = trace;
        return this;
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
     * @param exceptions - addException
     */
    public TraceBuilder setExceptions(List<Exception> exceptions) {
        this.exceptions = exceptions;
        return this;
    }

    /**
     * addExceptions
     *
     * @param exception -
     * @return TraceBuilder
     */
    public TraceBuilder addExceptions(Exception exception) {
        this.exceptions.add(exception);
        return this;
    }

    /**
     * getInfo
     *
     * @return Map<String ,   String>
     */
    public Map<String, String> getInfo() {
        return info;
    }

    /**
     * addInfo
     *
     * @param info - addInfo as string
     */
    public TraceBuilder addInfo(Map<String, String> info) {
        this.info = info;
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
                ", trace='" + trace + '\'' +
                ", exceptions=" + exceptions +
                ", info=" + info +
                '}';
    }

    /**
     * addTrace
     *
     * @return TraceBuilder
     */
    public TraceBuilder addTrace() {
        return setTrace(getCurrentTrace());
    }

    /**
     * build
     *
     * @return TraceBuilder
     */
    public TraceBuilder build() {

        return new TraceBuilder(
                trancePackages,
                request,
                response,
                trace,
                exceptions,
                info
        ).addTrace();
    }

}
