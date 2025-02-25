package org.parent.pages.Web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.parent.pages.MainPage;

import java.time.Duration;

public class HomePage extends MainPage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By fromCountryInput = By.cssSelector("input.search-input[placeholder='Select Country / Region']");
    private By toCountryInput = By.cssSelector("input.search-input[placeholder='Select Country']");
    private By submitBtn = By.xpath("//*[text()='Take Me To The Website']");

    private By acceptCookiesButton = By.id("onetrust-accept-btn-handler");


    public AppointmentPage clickAcceptCookiesButton() {

        click(acceptCookiesButton);
        driver.findElement(acceptCookiesButton).click();
       return new AppointmentPage(driver);
    }

    public boolean isAcceptCookiesButtonDisplayed() {
        return driver.findElement(acceptCookiesButton).isDisplayed();
    }

    public void selectFromCountry(String country) {
        WebElement element = driver.findElement(fromCountryInput);
        waitForElementPresent(fromCountryInput);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

        waitForElementPresent(fromCountryInput);
        setText(fromCountryInput,country);

        driver.findElement(By.xpath("//*[@class='text' and text()='"+country+"']")).click();
    }

    public void clickSubmitBtn(){
        driver.findElement(submitBtn).click();
    }

    public void selectToCountry(String country) {
        WebElement element = driver.findElement(toCountryInput);
        waitForElementPresent(toCountryInput);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

        setText(toCountryInput,country);
      click(By.xpath("//*[@class='text' and text()='"+country+"']"));
    }


}