**Trace Builder Master**

This is Trace Builder singleton. Use it to accumulate informations on any controller's route, such as request, response, throwed exceptions, additional messages and more and then save accumulated result to Db or handle by any other way.

*Tracebuilder Autotests uses JUnit 4.12 *
*TraceBuilder::ToString uses ApacheCommons *

---

## Install, include, add to your project

Include TB to your project

1. Load source from Bitbucket.
2. Add to your project

Future: Project will be published in maven central.

---

## Usage

Trace Builder is a singleton based on enum.

Example:

        TraceBuilder tb = TraceBuilder.INSTANCE
                .setTracePackage("org.bitbucket.tracebuilder")
                .exceptions(new Exception("Test exception"))
                .info("i1","My test log 1")
                .info("i2","My test log 2")
                .request("My request as a string")
                .response("My response as a string");



Example 2:
        // package org.bitbucket.tracebuilder_b;

        class A {
            public void method(String[] args) {

                TraceBuilder.INSTANCE
                        .setTracePackages(
                            new ArrayList<>(){{
                                add("package org.bitbucket.tracebuilder_b");
                                add("package org.bitbucket.tracebuilder_c");
                            }}
                        )
                        .request(args.toString())
                        .info("A::method", "info");

                (new B()).method();

            }
        }


        // package org.bitbucket.tracebuilder_b;

        class B {
            public void method() {
                TraceBuilder.INSTANCE
                        .info("B::method", "info 2")
                        .exceptions(new Exception("Exception B::method"));

                DB.log(TraceBuilder.INSTANCE.toString());
            }
        }


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

        DB.insert((String)TraceBuilder.INSTANCE);
                


---

## Additional info

Trace deepth is settled to 30, so if you specify package, TB will be storing 30 last files from the trace.
