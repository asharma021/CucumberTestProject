
Feature: Verify Both Counts are Correct

  Scenario: Compare the row counts from the database and the Excel file
    Given I have the row count from the database
    And I have the row count from the Excel file
    Then the row count from the database should be equal to the row count from the Excel file
