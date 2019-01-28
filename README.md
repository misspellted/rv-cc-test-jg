# What is this?
This repository contains an assessment of my skills at automating tests of a website using Selenium.

## How to break everyth... How does it work?
There is a companion repository, rv-cc-test-jg-props, that contains the *.properties files used in the
available WebDriverFactory's in this project. These files provide a layer of protection, in that the
factory has to recognize the `webdriver` property, such that the FirefoxDriver isn't configured via a
chrome.properties file.

For some of the driver factories, additional configuration is necessary, and the FirefoxDriverFactory
is the current (and only) example of this, where the Gecko Driver executable path is required for the
creation of the FirefoxDriver instance.

Additional properties to configure the desired driver factory will probably be added, such as combining
the HtmlUnitDriverFactory and HtmlUnitWithJsDriverFactory (since enableJavaScript is a configurable
field of the HtmlUnitDriver). Further refinement of the properties files may result in some refactoring,
but let me not get ahead of myself.

For the Selenium tests run, those are constructed and added to a collection to be run by the desired
web driver in the Launcher class. Those are not externally defined (yet?), and so there's not much
configuration to be had in that arena, such as specifying a test suite of selected cases, or running
every single test case to be found in some directory, which would be a nice feature to add.
