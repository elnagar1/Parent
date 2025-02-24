package org.parent.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.SneakyThrows;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentReporterNG implements ITestListener, ISuiteListener {

    private static ExtentReports extent;

    public static ExtentTest test;
    private static String reportFileName = "Test-Report-" + getCurrentDateTime() + ".html"; // Dynamic report file name
    private static String reportPath = System.getProperty("user.dir") + "/reports/"; // Report directory

    public synchronized static ExtentReports getExtent() {
        if (extent == null) {
            new File(reportPath).mkdirs();
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath + reportFileName);
            htmlReporter.config().setDocumentTitle("Automation Test Report"); // Report title
            htmlReporter.config().setReportName("Test Execution Results"); // Report name
            htmlReporter.config().setTheme(Theme.STANDARD); // Report theme

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
            // Add more system info as needed
        }
        return extent;
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return dateFormat.format(new Date());
    }


    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite Started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {


        if (extent != null) {
            extent.flush(); // Write the report to the file
        }

        System.out.println("Test Suite Finished!");
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        test = getExtent().createTest(result.getName());

        System.out.println(result.getName() + " test case started!");

    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println(result.getName() + " test case passed!");
    }

    @SneakyThrows
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println(result.getName() + " test case failed!");
        test.fail("Test Case Failed").addScreenCaptureFromPath(captureScreenshot(result.getName()));
        test.fail(result.getThrowable());

    }


    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println(result.getName() + " test case skipped!");
        ExtentTest test = getExtent().createTest(result.getName());
        test.skip("Test Case Skipped");
    }

    // Helper function to capture screenshots (you'll need to implement this)
    private String captureScreenshot(String testName) {

        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + ".png"; // Example path
        File screenshotFile = new File(screenshotPath);
        if (!screenshotFile.exists()) {
            try {
                screenshotFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return screenshotPath; // Return the path to the screenshot
    }





}