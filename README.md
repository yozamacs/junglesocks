# README for Jungle Socks UI test

## Running tests

* Run tests from IntelliJ
    * Import project into IntelliJ using maven
    * Right click on @Test and select _Run..'shouldTestSalesTax'_

## Making Changes

    * To change website URL or any of the html tags used to click on the page, change the values in JungleSocksConstants
    * To make changes to input data, change corresponding fields in Data Provider at the bottom of JungleSocksTest.java

## Future Work

    * Using a reporting tool that opens in an HTML files for clearer results
    * Running tests in parallel to speed up time
    * Run with headless browser to run in background