package org.parent.pages.Web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.parent.pages.MainPage;

public class HomePage  extends MainPage {
    WebDriver driver;

    // invoke parent's constructor
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[text()='Demo company Canada']")
    public WebElement instituteCard;
    @FindBy(id = "calendarTab")
    public WebElement calendarTab;
    @FindBy(id = "createEventBtn")
    public WebElement createEventBtn;


    public void createEvent() {
        click(instituteCard);
        click(calendarTab);
        click(createEventBtn);

    }
}
