package org.parent.constants;

import org.openqa.selenium.WebDriver;

public class GeneralConstants {


    public static final String TRUE = "TRUE";
    //  **********************   General config file and its properties key names ***************************
    public static final String GENERAL_CONFIG_FILE_NAME = "configFiles//GeneralConfigs.properties";
    public static final String ANDROID = "android";
    public static final String IOS = "ios";
    public static final String SERVER_URL = "url";
    //Android Caps
    public static final String ANDROID_CAPS = "androidCaps";
    //IOS Cap
    public static final String IOS_CAPS = "iosCaps";
    public static final int GLOBAL_TIME_OUT = 30;// change it from 60 to 300
    public static final String FILE_DELIMITER = "/";


    //*****************************************//
    public static WebDriver DRIVER;// change it from 60 to 300

    // Extent report configs
    public static final String SCREENSHOT_PASSED_TESTS_PATH = "screenshotsOfPassedTestsPath";
    public static final String EXTENT_REPORT_FILE_PATH = "extentReportFilepath";
    public static final String EXTENT_REPORT_TITLE = "extentReportTitle";
    public static final String EXTENT_REPORT_NAME = "extentReportName";
    public static final String ADD_LOG_TO_EXTENT_REPORT = "addLogToExtentReport";

    public static final String DB_CONFIG_FILE_NAME = "configFiles//DBConfigs.properties";

}
