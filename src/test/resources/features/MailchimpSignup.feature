Feature: User Registration on MailChimp

  Scenario: Create user with valid details
    Given User is on the registration page
    When User enters valid username, email, and password
    And User clicks the Sign Up button
    Then User should be successfully registered

  Scenario: Create user with username more than 100 characters
    Given User is on the registration page
    When User enters username with more than 100 characters
    And User clicks the Sign Up button
    Then User should see an error message for the username field

  Scenario: Create user with already taken username
    Given User is on the registration page
    When User enters an already taken username
    And User clicks the Sign Up button
    Then User should see an error message indicating username is taken

  Scenario: Create user with missing email address
    Given User is on the registration page
    When User enters valid username and password
    And User clicks the Sign Up button
    Then User should see an error message for the email address field

