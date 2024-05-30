package org.parent.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.parent.constants.GeneralConstants;
import org.parent.pages.MainPage;
import org.testng.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static org.parent.utils.Log.propHandler;

public class ExtentReporterNG implements ITestListener, ISuiteListener, IExecutionListener, WebDriverEventListener {
    private ExtentReports extent;
    private ExtentTest test;
    Properties generalConfigsProps = propHandler.loadPropertiesFile(GeneralConstants.GENERAL_CONFIG_FILE_NAME);
    String dateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
    ExtentHtmlReporter htmlReporter;


    // Other methods from ITestListener interface

    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {

    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("***** afterFindBy " + by.toString() + "element *****");

    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println("***** Click " + element + "element *****");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        System.out.println("Element : \u001B[32m" + "***** " + "Get \"" + text + "\"text *****\u001B[0m");

    }

    /************* ITestListener ************/

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        Log.info("TestCases : \u001B[32m" + "***** " + context.getName() + " TestCase Finished " + "*****\u001B[0m");

    }

    @Override
    public void onTestStart(ITestResult Result) {

        Log.info("TestCases : \u001B[32m" + "***** " + Result.getName() + " TestCase start successfully" + "*****\u001B[0m");

        System.out.println(Result.getMethod().getMethodName());


        test = extent.createTest(Result.getMethod().getMethodName());
        MainPage.extantTest = test;
        Log.test=test;
    }

    @Override
    public void onTestSuccess(ITestResult Result) {
        test.log(Status.PASS, "Test passed");
        Log.info("Test Cases : \u001B[32m" + "***** " + Result.getName() + " testcase pass successfully" + "*****\u001B[0m");

    }

    @Override
    public void onTestFailure(ITestResult Result) {
        test.log(Status.FAIL, "Test failed");
        Log.info("The name of the testcase failed is :" + Result.getName());

    }

    @Override
    public void onTestSkipped(ITestResult Result) {
        test.log(Status.SKIP, "Test skipped");
        Log.info("The name of the testcase Skipped is :" + Result.getName());

    }


    @Override
    public void onStart(ITestContext context) {

    }

    /************************************************/


    /*************** ISuiteListener ******************/
    @Override
    public void onStart(ISuite suite) {




        test = extent.createTest("Before : " + suite.getName() + "test suite");
        MainPage.extantTest = test;
        Log.test=test;
        Log.info("                               \u001B[32m" + "***** \"" + suite.getName() + "\" suite is Starting  *****\u001B[0m");

    }

    @Override
    public void onFinish(ISuite suite) {
        Log.info("                               \u001B[32m" + "***** \"" + suite.getName() + "\" suite is Finish  *****\u001B[0m");

    }
    /************************************************/


    /*************** IExecutionListener ******************/
    @Override
    public void onExecutionStart() {

        String extentReportFilePath = generalConfigsProps.getProperty(GeneralConstants.EXTENT_REPORT_FILE_PATH);
        String reportPath = System.getProperty("user.dir") + extentReportFilePath + dateTime + ".html";

        htmlReporter = new ExtentHtmlReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        Log.info("                               \u001B[32m" + " ***** Start Execution  *****\u001B[0m");

    }

    @Override
    public void onExecutionFinish() {

        Log.info("                               \u001B[32m\" ***** End Execution  *****\u001B[0m");
    }
    /************************************************/


}

