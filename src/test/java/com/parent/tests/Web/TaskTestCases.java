package com.parent.tests.Web;


import com.parent.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.parent.pages.Web.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.SQLException;

public class TaskTestCases extends BaseTest {

    @Parameters({"platformName", "email", "password"})
    @BeforeTest
    public void BeforeTest2(@Optional(value = "Android") String platformName, @Optional(value = "01020204040") String email, @Optional(value = "Aa@421998") String password) throws IOException, org.json.simple.parser.ParseException, SQLException, ClassNotFoundException {

        //new LoginPage(driver).appLogin(email,password);

    }

    private HomePage homePage;

    @Test
    public void testSelectFromCountry() {

        homePage = new HomePage(driver);
        homePage.selectFromCountry("Egypt");
        homePage.selectToCountry("Australia");
        homePage.clickSubmitBtn();
     //   Assert.assertTrue(homePage.isCountryInList("Australia"));

     //   driver.quit();
    }

  /*  @Test
    public void testSelectToCountry() {

        homePage = new HomePage(driver);
        homePage.selectToCountry("Canada");
        Assert.assertTrue(homePage.isCountryInList("Canada"));

        driver.quit();
    }

    @Test
    public void testClearSelectedFromCountry() {

        homePage = new HomePage(driver);
        homePage.selectFromCountry("Australia");
        homePage.clearSelectedCountry();
        Assert.assertFalse(homePage.isCountryInList("Australia"));

        driver.quit();
    }

    @Test
    public void testClearSelectedToCountry() {


        homePage = new HomePage(driver);
        homePage.selectToCountry("Germany");
        homePage.clearSelectedCountry();
        Assert.assertFalse(homePage.isCountryInList("Germany"));

        driver.quit();
    }*/

}
