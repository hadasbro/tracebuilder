Frequently Asked Questions
----

If you have any questions, please feel free to contact me.

#### What does this library is usefult to?

This is Trace Builder class. Use it to accumulate informations on any controller's route, 
such as request, response, throwed exceptions, 
additional messages etc. and then save accumulated result to Db or handle by any other way.

#### Any examples?
Use as singleton:

```java
// accumulating logs in TraceBuilderSingleton

TraceBuilderSingleton.INSTANCE
        .addException(new Exception("Test exception"))
        .addInfo("i1","test-log1")
        .addInfo("i2","test-log2")
        .setRequest("test-setRequest")
        .setResponse("test-response")
        .addTrace();

// add more logs

TraceBuilderSingleton.INSTANCE
        .addException(new Exception("Another exception"))
        .addInfo("Another info")
        .addTrace();
        
// to string

TraceBuilderSingleton.INSTANCE.toString();
```
    
    
Use as normal object (builder pattern):

```java
// accumulating logs in TraceBuilder object

TraceBuilder tb = new TraceBuilder()
    .addTracePackage("org.bitbucket.tracebuilder")
    .addException(new Exception("Test exception tb2"))
    .addInfo("My test log tb2")
    .setRequest("Request tb2")
    .setResponse("Response tb2")
    .build();	
    
// to string

tb.toString();
```