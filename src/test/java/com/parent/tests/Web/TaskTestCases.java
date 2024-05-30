package com.parent.tests.Web;


import com.parent.tests.BaseTest;
import org.parent.pages.Web.HomePage;
import org.parent.pages.Web.LoginPage;
import org.testng.annotations.*;

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
