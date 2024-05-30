package org.parent.pages.Web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.parent.pages.MainPage;

public class LoginPage extends MainPage {
    WebDriver driver;

    // invoke parent's constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "txtEmail")
    public WebElement emailTxb;
    @FindBy(id = "txtPassword")
    public WebElement passwordTxt;
    @FindBy(id = "submitBtn")
    public WebElement signIn;


    public void appLogin(String email, String password) {
        setText(emailTxb, email);
        setText(passwordTxt, password);
        click(signIn);
    }
}