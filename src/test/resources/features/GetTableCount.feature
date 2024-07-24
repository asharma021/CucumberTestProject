Feature: Database and Excel Row Count Verification

  Scenario: Fetch the row count from a specific table with the specified query
    Given I connect to the Oracle database
    When I fetch the row count from the table with the specified query
    Then I save the row count as "databaseRowCount"

  Scenario: Verify the row count in the downloaded Excel file
    Given I open the Excel file "path_to_your_excel_file"
    When I get the row count from the Excel file
    Then I save the row count as "excelRowCount"

  Scenario: Compare the row counts from the database and the Excel file
    Given I have the row count from the database
    And I have the row count from the Excel file
    Then the row count from the database should be equal to the row count from the Excel file