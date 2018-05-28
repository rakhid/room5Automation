
Feature: End to end tests for Room5 application


    @E2ETest @regression
    Scenario Outline: As a user, I want to contact and provide feedback on Room5 webapplication
      Given I am on the Room5 application homepage
      When I scroll to the bottom of the page and click contact
      Then I should be redirected to the contact page
      Then I fill in valid details and acknowledge the checkbox
         | FullName     | EmailID        | Message   |
         | <FullName>   | <EmailID>      | <Message> |
      And  I submit the form
      Then I should see the Message Sent Successfully! message

      Examples:
      | FullName              | EmailID              | Message                           |
      | Divya                 | divya_8989@ymail.com | Add some articles on treks in USA |



    @E2ETest @regression
    Scenario Outline: As a user, I want to select subsribe to the newsletter from room5
      Given I am on the Room5 application homepage
      When I scroll to the bottom of the page to locate subscription box
      And  I fill in  <validEmailID> emailID and donot acknowledge the checkbox
      And  I submit by clicking on Inspire Me
      Then I should see an error message for checkbox
      When I fill in  <invalidEmailID> emailID and acknowledge the checkbox
      And  I submit by clicking on Inspire Me
      Then I should see an error message for email
      When I fill in  <validEmailID> emailID and acknowledge the checkbox
      And  I submit by clicking on Inspire Me
      Then I should be able to see the You are now checked-in! message

      Examples:
        | validEmailID       | invalidEmailID |
        |divya_8989@ymail.com|  yash          |



    @E2ETest @smoke
    Scenario Outline: As a user, I want to Navigate a destination in a particular country
      Given I am on the Room5 application homepage
      When I scroll to the bottom of the page to locate country drop down
      And  I select a country
         | CountryName         |
         | <CountryName>       |
      And I click on a destination
        | DestinationName         |
        | <DestinationName>       |
     Then I should be redirected to the relavent search page
       | CountryName     |DestinationName        |
       | <CountryName>   | <DestinationName>     |

    Examples:
        | CountryName              | DestinationName         |
        | Canada                   | Montreal                |


    @E2ETest @smoke
    Scenario Outline: As a user, I search a destination using search icon on the main page
      Given I am on the Room5 application homepage
      When I click on search icon
      Then I should see a search bar
      When I submit the location
        | LocationName         |
        | <LocationName>       |
      Then I should see the search results message

      Examples:
        | LocationName         |
        | China                |

    @E2ETest @regression
    Scenario: As a tester, I want to ensure that all 6 hyperlinks are present on the bottom of the page
      Given I am on the Room5 application homepage
      When I go to the bottom of the page
      Then I should see 6 hyperlinks

    @E2ETest @regression
    Scenario: As a tester, I want to ensure that all 4 social media links are present on the bottom right of the page
      Given I am on the Room5 application homepage
      When I go to the bottom of the page
      Then I should see 4 social media links