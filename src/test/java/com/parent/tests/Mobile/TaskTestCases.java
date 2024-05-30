package com.parent.tests.Mobile;


import com.parent.tests.BaseTest;
import org.parent.pages.Mobile.HomePage;
import org.parent.pages.Mobile.LoginPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class TaskTestCases extends BaseTest {

    @Parameters({"platformName", "email", "password"})
    @BeforeTest
    public void BeforeTest2(@Optional(value = "Android") String platformName, @Optional(value = "01020204040") String email, @Optional(value = "Aa@421998") String password) throws IOException, org.json.simple.parser.ParseException, SQLException, ClassNotFoundException {

        new LoginPage(driver).appLogin(email,password);

    }
    @Test(priority = 1)
    public void testCase1()  {


       new HomePage(driver).createEvent();
    }




}
