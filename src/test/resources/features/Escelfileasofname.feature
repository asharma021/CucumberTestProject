Feature: Download Excel and open it

  Scenario: User downloads an Excel file and opens it
    Given the download folder is cleaned
    When the user clicks the export button to download the Excel file
    Then the Excel file should be downloaded, opened, and as_of_date extracted and used in a database query