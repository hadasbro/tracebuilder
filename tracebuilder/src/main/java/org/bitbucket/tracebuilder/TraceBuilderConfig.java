package org.bitbucket.tracebuilder;

import java.util.ArrayList;


@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class TraceBuilderConfig {

    /**
     * default TRACE_LIMIT deep
     */
    final protected int TRACE_LIMIT = 30;

    /**
     * default trace packages to consider
     */
    protected String[] defaultPackagesToScan = {"java.lang"};

}
