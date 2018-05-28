#Read Me:

#Automation Framework description:

*This automation model is based on simple selenium-java model.
*It supports BDD using cucumber which makes the framework suitable for all stakeholders i.e. Business Analysts, Developers and Testers.
*It is configured to run all tests that are in the feature file.
*Page object model has been implemented which helps in scaling the automation frameworks for large websites such
 as  Room5
*The build tool used here is Gradle.
*Presently the Framework only supports run with firefox browser but it is scalable to use chrome, IE, and Safari (Refer to DriverFactory.Java)

#Prerequisite:

* JDK 1.8
* Gradle
* IntelliJ Idea with cucumber for Java plugin
* JDK & Gradle path should be set


#Steps to run from IntelliJ:

* Clone project
* Import project in IntelliJ with the auto-import gradle option selected(wait for all dependencies to resolve)
* Right-click on CucumberRunTest & select run


#Steps to run from Command Line:

*Open command line & reach till cloned project directory
* Then run ./gradlew clean build

#Make sure to generate and attach results for every test
* It is  attached separately

#Please push your project to a Git repository and share it with us
* Done

#Explain in detail what made you choose these 2 test cases from point 5 above
*The test cases I choose were to check links and social media icons at the bottom of the page because of the following reasons:
1.)The links are important as they help track a user activity on the page
2.)Important tests to keep in regression suite to make sure that any UI changes have not affected the footer of the
    page

#Please list the limitations of your tests.
1.)Presently the Framework only supports run with firefox browser but it is scalable to use chrome, IE, and Safari (Refer to DriverFactory.Java)
2.) I couldn't avoid the usage of Thread.sleep at one place
3.)Framework doesn’t support Image comparison feature


#Few limitations of the website:
* I wanted to automate a couple of more scenarios which I couldn't because of the following bugs:

1.)Email input field on the subscription bar cannot handle trailing whitespaces; It breaks the system on submitting an Email-Id with whitespaces.
2.)The contact page only checks for incorrect Email-Id, It does not check for blank responses in name and message field.
3.)The search field does not clear the last searched results on clicking cross in the top right corner.
