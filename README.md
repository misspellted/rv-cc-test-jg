# What is this?
This repository contains an assessment of my skills at automating tests of a
website using Selenium.

## How to break everyth... How does it work?

That assumes it works. I cannot guarantee it works on every single system, since
that would be quite the feat, both physically and financially. Of course, if you
are so inclined, it wouldn't hurt to try to run it on your system, preferrably
in a virtual machine.. just in case.

## Requirements

* Well, since this project runs tests against creditcards.com, an active Internet
connection is extremely ultimately power-overwhelmingly highly suggested. Maybe.

* This is a Java project, specifically written in and targeting the 1.8 version.
So I'm pretty sure that's going to be needed for compilation. An installation
of Apache Maven cannot hurt, if command-line execution is desired, instead of
using an Integrated Development Environment (IDE).

* While not strictly required, there is a companion repository containing some
pre-defined property files for the configuration of a couple of different
Selenium WebDriver's. A similarly constructed text file provided as an argument
to the program will suffice.

  - Note: Any similarly constructed text file providing the properties MUST
provide the `webdriver` property, as that is used to check to see if a known
factory can support the desired WebDriver.

* Depending on the WebDriver desired to run the Selenium tests, you may need
some external programs that I could not identify as being usable as a
dependency; for an example of this, see the `firefox.properties` file in the
companion repository, specifically the `webdriver.gecko.driver` property.

## Compilation

Compilation is a relatively easy process: run `mvn package` in the project's
root directory. This will generate a Java ARchive file, `rvcctestjg.jar` in the
`target/` directory.

## Execution

Using the path of the driver properties file, execute the Java ARchive file
using `java -jar <path/to/rvcctestjg.jar> <path/to/driver.properties>` and be
ready to see quite a few warnings fly by in the terminal output area.
