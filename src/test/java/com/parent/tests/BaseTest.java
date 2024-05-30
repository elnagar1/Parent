package com.parent.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.parent.constants.GeneralConstants;
import org.parent.pages.Web.LoginPage;
import org.parent.utils.Log;
import org.parent.utils.PropertiesFilesHandler;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;

public class BaseTest {
    // Initialize instances of properties files to be used in all tests
    public WebDriver driver;
    Properties generalConfigsProps = new PropertiesFilesHandler().loadPropertiesFile(GeneralConstants.GENERAL_CONFIG_FILE_NAME);
    public String serverUrl = generalConfigsProps.getProperty(GeneralConstants.SERVER_URL);
    public String androidCaps = generalConfigsProps.getProperty(GeneralConstants.ANDROID_CAPS);
    public String iosCaps = generalConfigsProps.getProperty(GeneralConstants.IOS_CAPS);


    @Parameters({"platformName", "email", "password"})
    @BeforeTest
    public void BeforeTest1(@Optional(value = "Android") String platformName, @Optional(value = "01020204040") String email, @Optional(value = "Aa@421998") String password) throws IOException, org.json.simple.parser.ParseException, SQLException, ClassNotFoundException {

        GeneralConstants.DRIVER = driver;
        Log.info("// Login_Credential //" + "\nPlatform: " + platformName + "\nPhone: " + email + "\nPassword: " + password );
        setUp(platformName, email, password);

    }


    public void setUp(String platform, String email, String password) throws IOException, org.json.simple.parser.ParseException {


        if (platform.equalsIgnoreCase(GeneralConstants.ANDROID)) {
            DesiredCapabilities caps = setCaps(androidCaps);
            driver = new AndroidDriver(new URL(serverUrl), caps);

        } else if (platform.equalsIgnoreCase(GeneralConstants.IOS)) {

            DesiredCapabilities caps = setCaps(iosCaps);
            driver = new IOSDriver(new URL(serverUrl), caps);

        } else if (platform.equalsIgnoreCase("BrowserStack")) {
            MutableCapabilities capabilities = new UiAutomator2Options();
        } else if (platform.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://portal-staging.parent.cloud/institute"); // Website URL
        } else if (platform.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.get("https://portal-staging.parent.cloud/institute"); // Website URL
        } else if (platform.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get("https://portal-staging.parent.cloud/institute"); // Website URL
        }
        else if (platform.equalsIgnoreCase("Safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
            driver.get("https://portal-staging.parent.cloud/institute"); // Website URL
        }
        else {
            WebDriverManager.chromedriver().setup();
            driver = new FirefoxDriver();
            driver.get("https://portal-staging.parent.cloud/institute"); // Website URL
        }

    }


    @AfterSuite
    public void tearDown() {
        if (driver != null) {
         //   driver.quit();

            System.out.println("After Test Thread ID: " + Thread.currentThread().getId());

        }
    }


    public static DesiredCapabilities setCaps(String url) throws IOException, org.json.simple.parser.ParseException {

        String filePath = System.getProperty("user.dir") + GeneralConstants.FILE_DELIMITER + url;
        File srcFile = new File(filePath);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(new FileReader(srcFile));

        DesiredCapabilities caps = new DesiredCapabilities(object); // creating an object
        return caps;
    }

}
