
Feature: Get Table Count from Oracle Database

  Scenario: Fetch the row count from a specific table
    Given I connect to the Oracle database
    When I fetch the row count from the table "your_table_name"
    Then I save the row count as "databaseRowCount"
