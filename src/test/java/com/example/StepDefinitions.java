
package com.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    private static Map<String, Integer> counts = new HashMap<>();

    @Given("I connect to the Oracle database")
    public void iConnectToTheOracleDatabase() throws Exception {
        DatabaseUtil.connect();
    }

    @When("I fetch the row count from the table {string}")
    public void iFetchTheRowCountFromTheTable(String tableName) throws Exception {
        int rowCount = DatabaseUtil.getRowCount(tableName);
        counts.put("databaseRowCount", rowCount);
    }

    @Then("I save the row count as {string}")
    public void iSaveTheRowCountAs(String countName) {
        // Already saved during fetching
    }

    @Given("I open the Excel file {string}")
    public void iOpenTheExcelFile(String filePath) throws Exception {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(file);
        counts.put("excelRowCount", workbook.getSheetAt(0).getLastRowNum());
        workbook.close();
        file.close();
    }

    @When("I get the row count from the Excel file")
    public void iGetTheRowCountFromTheExcelFile() {
        // Already fetched during opening the file
    }

    @Given("I have the row count from the database")
    public void iHaveTheRowCountFromTheDatabase() {
        // Already have from previous steps
    }

    @Given("I have the row count from the Excel file")
    public void iHaveTheRowCountFromTheExcelFile() {
        // Already have from previous steps
    }

    @Then("the row count from the database should be equal to the row count from the Excel file")
    public void theRowCountFromTheDatabaseShouldBeEqualToTheRowCountFromTheExcelFile() {
        assertEquals(counts.get("databaseRowCount"), counts.get("excelRowCount"));
    }
}
