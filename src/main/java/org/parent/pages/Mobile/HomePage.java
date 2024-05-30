package org.parent.pages.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.parent.pages.MainPage;

public class HomePage extends MainPage {
    WebDriver driver;

    // invoke parent's constructor
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@text,'eutsche Kita test')]")
    public WebElement instituteCard;
    @FindBy(xpath = "//*[contains(@resource-id,'calendar')]")
    public WebElement calendarTab;

    @FindBy(xpath = "//*[contains(@resource-id,'add_button')]/android.widget.ImageButton")
    public WebElement addBtn;

    @FindBy(xpath = "//*[contains(@text,'Create event')]")
    public WebElement createEventBtn;


    public void createEvent() {
        click(instituteCard);
        click(calendarTab);
        click(addBtn);
        click(createEventBtn);

    }
}
