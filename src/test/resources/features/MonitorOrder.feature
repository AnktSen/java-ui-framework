Feature: Order Monitor
  As a customer, I want to select and purchase a Monitor

  Background: 
    Given user is on login page
    And user enters "Pawan Pankaj" and "Password@123"
    And user clicks on login button
    Then user is redirected to home page

  @LaptopOrder
  Scenario Outline: Fill delivery information and place order
    # These steps are specific to the checkout flow
    When user clicks on a Monitor
    And user clicks on Add to cart button
    And user accepts the success pop-up
    And user clicks on cart tab
    And User clicks on place order button
    And user fills "<Name>" "<Country>" "<City>" "<Credit Card>" "<Months>" "<Year>" in the place order form
    And user clicks on Purchase button
    Then a "Thank you for your purchase" pop-up should be displayed
    
    Examples: 
      | Name         | Country | City    | Credit Card | Months | Year |
      | Pawan Pankaj | India   | Jaunpur | 3333333333  | June   | 2027 |