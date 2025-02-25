package org.parent.pages.Web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.parent.pages.MainPage;

public class AppointmentPage extends MainPage {
    private WebDriver driver;

    private By appointmentHeading = By.id("action-heading-2");
    private By appointmentDescription = By.cssSelector("p.color-62707c.pb-5.w-wrap-anywhere");
    private By bookNowButton = By.id("action-link-2");
    private By viewMoreButton = By.cssSelector("div.viewmore.toggled-icon");


    public AppointmentPage clickViewMore() {
        click(viewMoreButton);
        return this;
    }

    public AppointmentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public AppointmentPage clickBookNowButton() {
        click(bookNowButton);
        return this;

    }
}