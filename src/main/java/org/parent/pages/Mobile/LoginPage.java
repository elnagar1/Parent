package org.parent.pages.Mobile;

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


    @FindBy(xpath = "//*[@class='android.widget.EditText' and contains(@resource-id, 'email_edit_text')]")
    public WebElement emailTxb;
    @FindBy(xpath = "//*[@class='android.widget.EditText' and contains(@resource-id, 'password_edit_text')]")
    public WebElement passwordTxt;
    @FindBy(xpath = "//*[ contains(@resource-id, 'login_button')]")
    public WebElement signIn;



    public void appLogin(String email, String password) {
        setText(emailTxb, email);
        setText(passwordTxt, password);
        click(signIn);
    }
}