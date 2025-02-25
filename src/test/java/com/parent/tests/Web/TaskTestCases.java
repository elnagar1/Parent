package com.parent.tests.Web;


import com.parent.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.parent.pages.Web.HomePage;
import org.parent.pages.Web.VisaPreparationForm;
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

      /*  homePage = new HomePage(driver);
        homePage.selectFromCountry("Egypt");
        homePage.selectToCountry("Australia");
        homePage.clickSubmitBtn();
        homePage.switchToNewTab();
        homePage
                .clickAcceptCookiesButton()
                .clickBookNowButton()
                .clickViewMore();*/
        driver.navigate().to("https://visa.vfsglobal.com/sau/en/fra/france-visas-assistance");


        VisaPreparationForm visaForm = new VisaPreparationForm(driver);

        visaForm.clickOptionalServiceLink();

        visaForm.switchToNewTab();
        visaForm.selectNationality("India");

        visaForm.selectVisaCenter("USA - New York");

        visaForm.selectCivility("Ms");

        visaForm.enterFirstName("John");

        visaForm.enterLastName("Doe");

        visaForm.enterEmail("john.doe@example.com");

        visaForm.enterPhoneNumber("1234567890");

        visaForm.enterTravelDate("13-02-2026");

        visaForm.selectPurposeOfStay("Tourism");

        visaForm.enterDuration("30");
        Assert.assertEquals("30", driver.findElement(By.id("input_32")).getAttribute("value"));

        visaForm.selectInsurance("Yes");
        Assert.assertTrue(driver.findElement(By.id("input_35_0")).isSelected());

        //   visaForm.submitForm();
    }


}
