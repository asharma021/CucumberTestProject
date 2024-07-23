
Feature: Verify Count in Downloaded Excel File

  Scenario: Verify the row count in the downloaded Excel file
    Given I open the Excel file "path_to_your_excel_file"
    When I get the row count from the Excel file
    Then I save the row count as "excelRowCount"
