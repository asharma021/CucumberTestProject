package fast.common.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    private static Map<String, Integer> counts = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(StepDefinitions.class.getName());
    private String downloadDir = System.getProperty("user.home") + "/downloads";
    private String asOfDate;

    @Given("I connect to the Oracle database")
    public void iConnectToTheOracleDatabase() throws Exception {
        DatabaseUtil.connect();
    }

    @Given("I open the first Excel file in the downloads folder and read AS_OF_DATE")
    public void iOpenTheFirstExcelFileInTheDownloadsFolder() throws Exception {
        File folder = new File(downloadDir);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".xlsx"));
        if (files != null && files.length > 0) {
            File file = files[0];
            try (FileInputStream fis = new FileInputStream(file);
                 Workbook workbook = WorkbookFactory.create(fis)) {
                Sheet sheet = workbook.getSheetAt(0);

                // Directly read the date from cell A2 (first column of the second row)
                Row dateRow = sheet.getRow(1);
                if (dateRow == null) {
                    throw new RuntimeException("The second row is not found in the Excel file");
                }
                Cell dateCell = dateRow.getCell(0);
                if (dateCell == null) {
                    throw new RuntimeException("The cell in the first column of the second row is not found in the Excel file");
                }

                // Assuming the date is in the format yyyy-MM-dd
                asOfDate = dateCell.getStringCellValue();

                int rowCount = sheet.getLastRowNum();
                counts.put("excelRowCount", rowCount);
                LOGGER.info("Excel row count: " + rowCount);
                LOGGER.info("AS_OF_DATE: " + asOfDate);
            }
        } else {
            LOGGER.warning("No Excel files found in the downloads folder");
        }
    }

    @When("I fetch the row count from the table with the specified query")
    public void iFetchTheRowCountFromTheTable() throws Exception {
        String query = "SELECT COUNT(*) FROM P_QSCORE_WS_OUT_MNTHLY_DQ_GBL_GROUP WHERE as_of_date = '" + asOfDate + "'";
        int rowCount = DatabaseUtil.getRowCountWithQuery(query);
        counts.put("databaseRowCount", rowCount);
        LOGGER.info("Database row count: " + rowCount);
    }
    @When("I fetch the row count from the table with the specified query")
    public void iFetchTheRowCountFromTheTable() throws Exception {
        String query = "SELECT COUNT(*) FROM P_QSCORE_WS_OUT_MNTHLY_DQ_GBL_GROUP WHERE as_of_date = '" + asOfDate + "'";
        int rowCount = DatabaseUtil.getRowCountWithQuery(query);
        counts.put("databaseRowCount", rowCount);
        LOGGER.info("Database row count: " + rowCount);
    }

    @Then("I save the row count as {string}")
    public void iSaveTheRowCountAs(String countName) {
        // Already saved during fetching
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

    @Given("the download folder is cleaned")
    public void cleanDownloadFolder() {
        File folder = new File(downloadDir);
        File[] files = folder.listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(File::delete);
        }
    }

    @Then("I clean up the downloads folder")
    public void iCleanUptheDownloadsFolder() {
        FileUtility.cleanDownloadsFolder(downloadDir);
    }
}
