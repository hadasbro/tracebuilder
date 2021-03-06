![picture](https://img.shields.io/badge/Maven%20central-1.0.1-green)
![picture](https://img.shields.io/badge/Java-%3E%3D9.0.1-green)
![picture](https://img.shields.io/badge/jUnit-4.12-green)
![picture](https://img.shields.io/badge/License-Apache--2.0-%23008EFF)

**Trace Builder**

Zero dependency trace acumulator.

This is Trace Builder library. Use it to accumulate informations on any controller's route, such as request, response, throwed exceptions, additional messages and more and then save accumulated result to Db or handle by any other way.

## Installation

Maven Repository

    <dependency>
      <groupId>com.github.hadasbro</groupId>
      <artifactId>tracebuilder</artifactId>
      <version>1.0.1</version>
    </dependency>


## Usage

Trace Builder is a singleton based on enum.

Use as **singleton**:

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

Use as normal object **(builder pattern)**:
        
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

1. To set **package** to be considered in Trace Builder when any exception occure use
TraceBuilder::setTracePackage()
TraceBuilder::setTracePackages()
2. To add any **custom info message** use:
TraceBuilder::info()
3. To add **request or response** as a string use:
TraceBuilder::request()
TraceBuilder::response()
4. To get final, accumulated result use:
TraceBuilder::toString()

You can add as much info messages as you need, you can also specify the package which should considered when any exception throwed.

Insert to DB example:

```java
DB.insert((String)TraceBuilder.INSTANCE);
```                


## Additional info

Trace deepth is settled to 30, so if you specify package, TB will be storing 30 last files from the trace.


## Authors

* **Slawomir Hadas** - *author* - [Github](https://github.com/hadasbro)
