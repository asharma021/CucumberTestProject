package com.example;

import io.cucumber.java.en.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class DownloadExcelSteps {

    private WebDriver driver;
    private String downloadDir = System.getProperty("user.home") + "/Downloads";
    private String asOfDate;

    @Given("the download folder is cleaned")
    public void cleanDownloadFolder() {
        File folder = new File(downloadDir);
        File[] files = folder.listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(File::delete);
        }
    }

    @When("the user clicks the export button to download the Excel file")
    public void downloadExcelFile() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("URL_OF_THE_PAGE_WITH_EXPORT_BUTTON");

        driver.findElement(By.id("EXPORT_BUTTON_ID")).click();

        // Wait for the download to complete
        try {
            Thread.sleep(5000); // Adjust this wait time based on your needs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("the Excel file should be downloaded, opened, and as_of_date extracted and used in a database query")
    public void processExcelAndRunQuery() {
        // Find the latest downloaded Excel file
        File folder = new File(downloadDir);
        Optional<File> latestFile = Arrays.stream(folder.listFiles((dir, name) -> name.endsWith(".xlsx")))
                .max(Comparator.comparingLong(File::lastModified));

        if (latestFile.isPresent()) {
            File excelFile = latestFile.get();
            try (FileInputStream fis = new FileInputStream(excelFile);
                 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

                // Assuming as_of_date is in the first cell of the first sheet
                Row row = workbook.getSheetAt(0).getRow(0);
                Cell cell = row.getCell(0);
                asOfDate = cell.getStringCellValue();
                System.out.println("as_of_date: " + asOfDate);

                // Run the database query with the extracted as_of_date
                runDatabaseQuery(asOfDate);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                driver.quit();
            }
        } else {
            Assert.fail("No Excel file found in the download directory!");
        }
    }

    private void runDatabaseQuery(String asOfDate) {
        String url = "jdbc:your_database_url";
        String user = "your_database_user";
        String password = "your_database_password";

        String query = "SELECT COUNT(*) FROM P_QSCORE_RT_MNTHLY_DO_GBL_GROUP WHERE as_of_date = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, asOfDate);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("Count: " + count);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
