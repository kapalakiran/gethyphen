#Gethyphen Automation

#Project Description

      This project deals with automating the Gethyphen Application's functionalities. Test Cases are added to verify login, create new post with open & multiple choice functionalities.

#Tools & Technologies 
       
      Web Application testing done using : Selenium Webdriver 
      Programming Language used : Java 
      Design Pattern : Page Object Model with Page Factory 
      Build Management Tool : Maven 
      Framework, Annotations and Execution : TestNG

#source folders

#src/main/java -

   It has the following packages -

   a. com.util

      This package has all the driver intialisation, wrapper functions like click,enterText etc.,report functions etc.
      It also has Test data generation functions & excel functions
   b. com.web.pages.gethyphen

      It has all the page object model classes and functions, validations etc.

#src/test/java

   It has the following package -

   a. com.web.gethyphen.testcases

      This package contains the test runner classes with TestNG annotations & they are - 
      1. CreateNewPostTCs - contains TC's related to create new post with all Post & Question type
      2. LoginPageTCs - contains TC's related to login functionality
   
#src/main/resources

   It has the following folder -

   a. prod - prod.properties

      Which contains url,other creds if required

Other important files in the project are as follows-

      #testngxml/gethyphentestng.xml
           It helps us to execute the testcases

      #pom.xml
          It holds the dependencies required for the project execution

      #ExtentReports
          It is the Open source reporting library used to depict the results of the test execution
