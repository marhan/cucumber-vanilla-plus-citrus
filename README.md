# Cucumber 'vanilla' plus Citrus

This project illustrates how to add Citrus to an existing cucumber project.

**Project structure**  
The package `hellocucumber` contains the test runner as well as two additional packages. `hellocucumber.cucumber` contains
the *cucumber vanilla* test steps while `hellocucumber.citrus` contains the cucumber steps in combination with citrus.

Under `resources`, you'll find the `citrus-application.properties` and the `cucumber.properties`. 
Both are mandatory for the tests to be executed successfully. 

Finally `resources/hellocucumber` contains the feature files specifying the tests.

**Execution**
`mvn clean verify`