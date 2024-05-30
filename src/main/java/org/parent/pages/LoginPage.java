package org.parent.pages;

import com.github.javafaker.Faker;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.parent.utils.Log.Log;

public class LoginPage extends MainPage {
    WebDriver driver;

    // invoke parent's constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(xpath = "//*[ contains(@resource-id,  'xtEmail')]")
    public WebElement emailTxb;
    @FindBy(xpath = "//*[contains(@resource-id,'xtPassword')]")
    public WebElement passwordTxt;
    @FindBy(xpath = "//*[contains(@resource-id,'submitBtn')]")
    public WebElement signIn;

    public void appLogin(String email, String password) {


    }
}