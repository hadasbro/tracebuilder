package org.bitbucket.tracebuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SuppressWarnings({"unused", "WeakerAccess"})
public interface TraceBuilderInterface {

    /**
     * setTracePackages
     *
     * @return TraceBuilderInterface
     */
    TraceBuilderInterface setTracePackages(ArrayList<String> trancePackages);

    /**
     * setTracePackages
     *
     * @param trancePackage - package to scan
     * @return TraceBuilder
     */
    TraceBuilderInterface addTracePackage(String trancePackage);

    /**
     * getCurrentTrace
     *
     * @return String
     */
    String getCurrentTrace();


    /**
     * addException
     *
     * @param exception - exception
     * @return TraceBuilder
     */
    TraceBuilderInterface addException(Exception exception);

    /**
     * addInfo
     *
     * @param info - addInfo message
     * @return TraceBuilder
     */
    TraceBuilderInterface addInfo(String info);

    /**
     * addInfo
     *
     * @param key - addInfo key
     * @param info - addInfo message
     * @return TraceBuilder
     */
    TraceBuilderInterface addInfo(String key, String info);

    /**
     * setRequest
     *
     * @param request - setRequest as a string
     * @return TraceBuilder
     */
    TraceBuilderInterface setRequest(String request);

    /**
     * toString
     *
     * @return String
     */
    String toString();

    /**
     * getTrancePackages
     *
     * @return ArrayList<String>
     */
    ArrayList<String> getTrancePackages();

    /**
     * setTrancePackages
     *
     * @param trancePackages - packages to scan
     */
    TraceBuilderInterface setTrancePackages(ArrayList<String> trancePackages);

    /**
     * getRequest
     *
     * @return String
     */
    String getRequest();

    /**
     * getResponse
     *
     * @return String
     */
    String getResponse();

    /**
     * setResponse
     *
     * @param response - response as string
     */
    TraceBuilderInterface setResponse(String response);

    /**
     * getTrace
     *
     * @return String
     */
    String getTrace();

    /**
     * setTrace
     *
     * @param trace - trace as string
     */
    TraceBuilderInterface setTrace(String trace);

    /**
     * getExceptions
     *
     * @return List<Exception>
     */
    List<Exception> getExceptions();

    /**
     * setExceptions
     *
     * @param exceptions - addException
     */
    TraceBuilderInterface setExceptions(List<Exception> exceptions);

    /**
     * getInfo
     *
     * @return Map<String, String>
     */
    Map<String, String> getInfo();

    /**
     * addInfo
     *
     * @param info - addInfo as string
     */
    TraceBuilderInterface addInfo(Map<String, String> info);

    /**
     * addTrace
     * @return
     */
    TraceBuilder addTrace();

}
