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
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    private static Map<String, Integer> counts = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(StepDefinitions.class.getName());

    @Given("I connect to the Oracle database")
    public void iConnectToTheOracleDatabase() throws Exception {
        DatabaseUtil.connect();
    }

    @When("I fetch the row count from the table with the specified query")
    public void iFetchTheRowCountFromTheTable() throws Exception {
        String query = "SELECT COUNT(*) FROM P_QSCORE_RT_MNTHLY_DO_GBL_GROUP WHERE as_of_date = '2023-05-31'";
        int rowCount = DatabaseUtil.getRowCountWithQuery(query);
        counts.put("databaseRowCount", rowCount);
        LOGGER.info("Database row count: " + rowCount);
    }

    @Then("I save the row count as {string}")
    public void iSaveTheRowCountAs(String countName) {
        // Already saved during fetching
    }

    @Given("I open the Excel file {string}")
    public void iOpenTheExcelFile(String filePath) throws Exception {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(file);
        // Adjusting row count to skip the first row
        int rowCount = workbook.getSheetAt(0).getLastRowNum();
        counts.put("excelRowCount", rowCount);
        LOGGER.info("Excel row count: " + rowCount);
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
        LOGGER.info("Row counts match: " + counts.get("databaseRowCount"));
    }
}
