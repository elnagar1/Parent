package org.parent.pages.Web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.parent.pages.MainPage;

public class VisaPreparationForm extends MainPage {

    private WebDriver driver;
    private By optionalServiceLink = By.cssSelector("a.lets-get-started");


    // Selectors
    private By nationalityDropdown = By.id("input_31");
    private By visaCenterDropdown = By.id("input_33");
    private By civilityRadioMs = By.id("label_input_12_0");
    private By civilityRadioMr = By.id("input_12_1");
    private By firstNameInput = By.id("first_4");
    private By lastNameInput = By.id("last_4");
    private By emailInput = By.id("input_5");
    private By phoneNumberInput = By.id("input_34");
    private By travelDateInput = By.id("lite_mode_23");
    private By purposeOfStayDropdown = By.id("input_47");
    private By durationInput = By.id("input_32");
    private By insuranceRadioYes = By.id("label_input_35_0");
    private By insuranceRadioNo = By.id("label_input_35_1");
    private By insuranceRadioNotSure = By.id("label_input_35_2");
    private By submitButton = By.id("input_2");

    // Constructor
    public VisaPreparationForm(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickOptionalServiceLink() {
       click(optionalServiceLink);
    }

    // Methods to interact with the form
    public void selectNationality(String nationality) {
        waitForElementPresent(nationalityDropdown);
        Select dropdown = new Select(driver.findElement(nationalityDropdown));
        dropdown.selectByVisibleText(nationality);
    }

    public void selectVisaCenter(String visaCenter) {
        waitForElementPresent(visaCenterDropdown);
        Select dropdown = new Select(driver.findElement(visaCenterDropdown));
        dropdown.selectByVisibleText(visaCenter);
    }

    public void selectCivility(String civility) {
        if (civility.equals("Ms")) {
            click(civilityRadioMs);

        } else {
            click(civilityRadioMr);

        }
    }

    public void enterFirstName(String firstName) {
        setText(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        setText(lastNameInput, lastName);
    }

    public void enterEmail(String email) {
        setText(emailInput, email);
    }

    public void enterPhoneNumber(String phoneNumber) {
        setText(phoneNumberInput, phoneNumber);
    }

    public void enterTravelDate(String travelDate) {
        setText(travelDateInput, travelDate);
    }

    public void selectPurposeOfStay(String purpose) {
        waitForElementPresent(purposeOfStayDropdown);
        Select dropdown = new Select(driver.findElement(purposeOfStayDropdown));
        dropdown.selectByVisibleText(purpose);
    }

    public void enterDuration(String duration) {
     setText(durationInput, duration);

    }

    public void selectInsurance(String insurance) {
        if (insurance.equals("Yes")) {
            click(insuranceRadioYes);
        } else if (insurance.equals("No")) {
            click(insuranceRadioNo);
        }
        else {
            click(insuranceRadioNotSure);
        }
    }

    public void submitForm() {
        click(submitButton);
    }

}
