package org.parent.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.parent.constants.GeneralConstants;

import java.util.Calendar;
import java.util.Properties;
public class Log {

	public static ExtentTest test;

	// Initialize instances of properties files to be used in all tests
	static PropertiesFilesHandler propHandler = new PropertiesFilesHandler();
	static Properties generalConfigsProps = propHandler.loadPropertiesFile(GeneralConstants.GENERAL_CONFIG_FILE_NAME);
	static boolean addLogToExtentReportFlag = (generalConfigsProps.getProperty(GeneralConstants.ADD_LOG_TO_EXTENT_REPORT)
			.toLowerCase().equals(GeneralConstants.TRUE.toLowerCase()));

// Initialize Log4j logs


	public static Logger Log = LogManager.getLogger(Log.class.getName());//

	// This is to print log for the beginning of the test case, as we usually run so
	// many test cases as a test suite

	public static void startTestCase(String sTestCaseName) {

		if (test != null && addLogToExtentReportFlag)
			test.log(Status.INFO, sTestCaseName + " Test is CREATED");

		Log.info("****************************************************************************************");

		Log.info("****************************************************************************************");

		Log.info("$$$$$$$$$$$$$$$$$$$$$           " + sTestCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$");

		Log.info("****************************************************************************************");

		Log.info("****************************************************************************************");

	}

	// This is to print log for the ending of the test case

	public static void endTestCase(String sTestCaseName) {

		if (test != null && addLogToExtentReportFlag)
			test.log(Status.INFO, sTestCaseName + " Test has ENDED");

		Log.info("XXXXXXXXXXXXXXXXXXXXXXX      " + "      -E---N---D-     " + sTestCaseName
				+ "             XXXXXXXXXXXXXXXXXXXXXX");

		Log.info("X");

		Log.info("X");

		Log.info("X");

		Log.info("X");

	}

	// Need to create these methods, so that they can be called
	static Calendar c ;
	public static void info(String message) {

		c =Calendar.getInstance();
		System.out.println(c.getTime()+" && INFO :  "+message);
		if (test != null && addLogToExtentReportFlag)
			test.log(Status.INFO, message);

	}

	public static void warn(String message, Exception e) {

		Log.warn(message, e);
		if (test != null && addLogToExtentReportFlag)
			test.log(Status.WARNING, message);

	}

	public static void error(String message, Exception e) {

		Log.error(message, e);
		if (test != null && addLogToExtentReportFlag) {
			test.log(Status.FAIL, message);
			test.log(Status.FAIL, e.getMessage());
		}

	}





}
