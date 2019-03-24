**Trace Builder Master**

This is Trace Builder singleton. Use it to accumulate informations on any controller's route, such as request, response, throwed exceptions, additional messages and more and then save accumulated result to Db or handle by any other way.

*Tracebuilder Autotests uses JUnit 4.12 *

---

## Install, include, add to your project

You’ll start by editing this README file to learn how to edit a file in Bitbucket.

1. Click **Source** on the left side.
2. Click the README.md link from the list of files.
3. Click the **Edit** button.
4. Delete the following text: *Delete this line to make a change to the README from Bitbucket.*
5. After making your change, click **Commit** and then **Commit** again in the dialog. The commit page will open and you’ll see the change you just made.
6. Go back to the **Source** page.

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
