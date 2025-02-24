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
    private By countryList = By.cssSelector("ul.section-list");
    private By removeSelectionButton = By.cssSelector("div.remove-selection-button");
    private By submitBtn = By.xpath("//*[text()='Take Me To The Website']");

    public By egypt = By.xpath("//*[@class='text' and text()='Egypt']");

    public void selectFromCountry(String country) {
        WebElement element = driver.findElement(fromCountryInput);
        waitForElementPresent(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        WebElement element2 = driver.findElement(fromCountryInput);
        waitForElementPresent(element2);
        element2.sendKeys(country);

        driver.findElement(By.xpath("//*[@class='text' and text()='"+country+"']")).click();
    }

    public void clickSubmitBtn(){
        driver.findElement(submitBtn).click();
    }

    public void selectToCountry(String country) {
        WebElement element = driver.findElement(toCountryInput);
        waitForElementPresent(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

        driver.findElement(toCountryInput).sendKeys(country);

        driver.findElement(By.xpath("//*[@class='text' and text()='"+country+"']")).click();
    }

    public boolean isCountryInList(String country) {
        return driver.findElement(countryList).getText().contains(country);
    }

    public void clearSelectedCountry() {
        driver.findElement(removeSelectionButton).click();
    }

}