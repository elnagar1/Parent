package org.parent.pages;

import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.ImmutableList;
import com.shaft.gui.element.AlertActions;
import com.shaft.gui.element.TouchActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.parent.constants.GeneralConstants;
import org.parent.utils.Log;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class MainPage {
    public static String driverType;
    public static String platform;
    public static ExtentTest extantTest;
    // Initialize driver
    public WebDriver driver;

    protected int globalTimeOut = GeneralConstants.GLOBAL_TIME_OUT;

    By backBtnXpath = By.xpath("//XCUIElementTypeButton[@name=\"headerLeftButton\"]");
    @FindBy(xpath = "//XCUIElementTypeButton[@name='ic']")
    public WebElement backIcon;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Cancel'] | //*[contains(@resource-id,'tv_cancel')]")
    public WebElement quickPayCancelBtn;

    public WebElement getBackBtn() {
        return driver.findElement(backBtnXpath);
    }


    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    public boolean isAndroid() {
        return platform.equalsIgnoreCase("android");
    }

    public void assertTowExpectedResult(String actualResult, String expectedResult1, String expectedResult2) {
        new SoftAssert().assertTrue(actualResult.equals(expectedResult1) || actualResult.equals(expectedResult2));
    }

    public void assertContainTowExpectedResult(String actualResult, String expectedResult1, String expectedResult2) {

        new SoftAssert().assertTrue(actualResult.contains(expectedResult1) || actualResult.contains(expectedResult2));
    }

    public boolean verifyElementClickable(WebElement element) {

        by(element, "Verify Element Present");
        try {
            waitForElementPresent(element);
        } catch (Exception e) {

        }

        try {
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }


    }


    public String getText(WebElement element, Long second) {

        by(element, "Get Text");
        int count = 0;
        //It will try 4 times to find same element using name.
        while (count < 2) {
            try {
                if (second == null) {
                    waitUntilElementVisible(element);
                } else {
                    waitUntilElementVisible(element, second.intValue());
                }

                return element.getText();
            } catch (StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + "\u001B[31m");
                System.out.print(e.getMessage() + "\u001B[0m");
                count++;
            } catch (TimeoutException e) {

                count++;
                if (count == 2) {
                    Assert.fail("Waiting for "+by(element)+" Element but not exist");
                }
                if (displayed(quickPayCancelBtn, 0)) {
                    quickPayCancelBtn.click();
                }
            }
        }
        return null;


    }


    public String getText(WebElement element) {
        String text;

        text = getText(element, null);

        return text;
    }


    public void click(WebElement button, Long second) {


        int count = 0;
        //It will try 3 times to find same element using name.
        by(button, "Clicking");
        while (count < 2) {
            try {

                if (second == null) {
                    waitUntilElementToBeClickable(button);
                } else {
                    waitUntilElementToBeClickable(button, second.intValue());
                }

                break;
            } catch (StaleElementReferenceException e) {

                System.out.println(">>> Trying to recover from a stale element :" + "\u001B[31m");
                count++;
            } catch (InvalidSelectorException e) {

                System.out.println(">>> Trying to recover from a InvalidSelector :" + "\u001B[31m");
                count++;
            } catch (TimeoutException e) {

                count++;
                if (count == 2) {
                    Assert.assertTrue(false, "Waiting for Element but not exist");
                }

                if (displayed(quickPayCancelBtn, 0)) {
                    quickPayCancelBtn.click();
                }
            }

        }
    }

    public void clickWithoutWait(WebElement button) {

        by(button, "Click");
        int count = 0;
        while (count < 2) {
            try {

                button.click();

                break;
            } catch (StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + "\u001B[31m");
                System.out.println(e.getMessage() + "\u001B[0m");
                count++;
            }

        }
    }

    public void click(WebElement button) {
        click(button, null);
    }



    public void tap(WebElement button) {

        click(button, null);

    }


    public void setText(WebElement element, String data, Long second) {

        int count = 0;
        //It will try 4 times to find same element using name.
        while (count < 2) {
            try {
                if (second == null) {
                    waitForElementPresent(element);
                } else {
                    waitForElementPresent(element, second.intValue());
                }
                by(element, "Set Text");
                element.sendKeys(data);
//                Log.info("***** Set Text on " + element.getAccessibleName() + " TextBox *****");
                break;
            } catch (StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element :" + "\u001B[31m");
                System.out.println(e.getMessage() + "\u001B[0m");
                count++;

            } catch (TimeoutException e) {

                count++;
                if (count == 2) {
                    Assert.fail("Waiting for Element but not exist");
                }
                if (displayed(quickPayCancelBtn, 0)) {
                    quickPayCancelBtn.click();
                }
            }
        }

    }

    public void setText(WebElement element, String data) {
            setText(element, data, null);
    }


    public void waitForElementPresent(WebElement element) {

            waitUntilElementVisible(element, globalTimeOut);
    }

    public void setImplicitWait() {
        driver.manage().timeouts().implicitlyWait(globalTimeOut, TimeUnit.SECONDS);
    }

    public void waitForElementPresent(WebElement element, int time) {
            waitUntilElementVisible(element, time);


    }

    public void waitUntilElementVisible(WebElement element) {
        waitUntilElementVisible(element, globalTimeOut);
    }

    public void waitUntilElementToBeClickable(WebElement element, int time) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.pollingEvery(Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    public void waitUntilElementToBeClickable(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GeneralConstants.GLOBAL_TIME_OUT));
        wait
                .pollingEvery(Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element))
                .click();

    }

    public WebElement waitUntilElementWithCondition(WebElement element, ExpectedConditions conditions) {

        return new WebDriverWait(driver, Duration.ofSeconds(GeneralConstants.GLOBAL_TIME_OUT))
                .until(conditions.presenceOfElementLocated(by(element)));
    }

    public WebElement waitUntilElementWithCondition(WebElement element, ExpectedConditions conditions, int time) {

        return new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(conditions.presenceOfElementLocated(by(element)));
    }

    public void waitUntilElementVisible(WebElement element, int time) {


        new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions
                        .visibilityOf(element));


    }

    public void waitUntilElementToClick(int time, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(time)).pollingEvery(Duration.ofMillis(500)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntil(BooleanSupplier condition, int seconds) {

        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500)).until((WebDriver driver) -> condition.getAsBoolean());


    }

    public void waitUntil(BooleanSupplier condition) {
        waitUntil(condition, globalTimeOut);
    }


    public boolean displayed(WebElement element, int time) {


        try {
            new WebDriverWait(driver, Duration.ofSeconds(time))
                    .until(ExpectedConditions
                            .visibilityOf(element));
            boolean x = element.isDisplayed();

            return x;

        } catch (TimeoutException e) {

            return false;


        }

    }


    public boolean isDisplayed(WebElement element, int time) {


            try {
                waitForElementPresent(element, time);
                boolean x = element.isDisplayed();

                if (x == false) {

                }
                return x;

            } catch (TimeoutException e) {

                return false;
            }



        }



    public boolean isDisplayed(WebElement element) {

            by(element,"IsDisplay");
            try {
                waitForElementPresent(element);
                boolean x = element.isDisplayed();

                if (x == false) {

                }
                return x;

            } catch (TimeoutException e) {
                if (displayed(quickPayCancelBtn, 0)) {
                    quickPayCancelBtn.click();
                }
                return false;
            }

    }


    public void scrollDown() {


        int pressX = driver.manage().window().getSize().width / 2;
        // 4/5 of the screen as the bottom finger-press point
        int bottomY = driver.manage().window().getSize().height / 4 * 3;
        // just non zero point, as it didn't scroll to zero normally
        int topY = driver.manage().window().getSize().height / 2;


        //scroll with TouchAction by itself
        TouchAction action = new TouchAction((PerformsTouchActions) driver);


        if (driverType == null) {


            action
                    .longPress(PointOption.point(pressX, bottomY))
                    .moveTo(PointOption.point(pressX, topY - 100))
                    .release()
                    .perform();
        } else {


            action
                    .longPress(PointOption.point(pressX, bottomY))
                    .moveTo(PointOption.point(pressX, topY + 100))
                    .release()
                    .perform();
        }
    }

    public By by(WebElement element, String Action) {
        String locator;
        String text = element.toString();


        text = text.substring(0, text.length() - 1);
        if (text.contains("xpath")) {
            locator = text.split("xpath: ")[1];
            if (extantTest == null) {

            } else {

                extantTest.info(Action + ": " + locator);
            }
            Log.info(Action + ": " + locator);
            return By.xpath(locator);
        } else if (text.contains("id")) {
            locator = text.split("id: ")[1];
            return By.id(locator);
        } else if (text.contains("className")) {
            locator = text.split("className: ")[1];
            return By.className(locator);
        } else {
            return null;
        }
    }

    public By by(WebElement element) {
        String locator;
        String text = element.toString();

        text = text.substring(0, text.length() - 1);
        if (text.contains("xpath")) {
            locator = text.split("xpath: ")[1];
            return By.xpath(locator);
        } else if (text.contains("id")) {
            locator = text.split("id: ")[1];
            return By.id(locator);
        } else if (text.contains("className")) {
            locator = text.split("className: ")[1];
            return By.className(locator);
        } else {
            return null;
        }
    }


    private final static PointerInput FINGER = new PointerInput(TOUCH, "finger");

    public void doSwipe(AppiumDriver driver, Point start, Point end, int duration) {
        Sequence swipe = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
                .addAction(FINGER.createPointerMove(ofMillis(duration), viewport(), end.getX(), end.getY()))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    public void doTap(AppiumDriver driver, Point point, int duration) {
        Sequence tap = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(ofMillis(0), viewport(), point.getX(), point.getY()))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
                .addAction(new Pause(FINGER, ofMillis(duration)))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }

    public void typeRandomNumber(WebElement element, int data) {
        Random random = new Random();
        int rn = random.nextInt(100);
        String randomNumber = Integer.toString(rn);
        element.sendKeys(randomNumber);
    }

    public boolean VerifyTextEquals(WebElement element, String expected) {
        boolean flag = false;
        if (element.getText().equals(expected))
            return flag = true;
        return flag;
    }

    public boolean isSelected(WebElement element) {


        try {
            waitForElementPresent(element);
            boolean x = element.isSelected();

            if (x == false) {
                System.out.println("***** Element  is not Displayed *****  ");

            }
            return x;
        } catch (Exception e) {
            System.out.println("***** Element  is not Displayed *****  " + e.getMessage());

            return false;
        }

    }

    public void selectCheckBox(WebElement element) {
        if (!isSelected(element))
            element.click();
    }

    public void deSelectCheckbox(WebElement element) {
        if (isSelected(element))
            element.click();
    }

    public void selectRadioButton(WebElement element) {
        if (!isSelected(element))
            element.click();
    }

    public void clickBack() {

        if (platform.equalsIgnoreCase("Android")) {
            driver.navigate().back();
        } else {
            click(backIcon);
          /*  TouchAction action = new TouchAction((PerformsTouchActions) driver);
            Dimension screenSize = driver.manage().window().getSize();
            // Calculate the back button location.
            int backButtonX = (int) (screenSize.width * 0.1);
            int backButtonY = (int) (screenSize.height * 0.07);
            Point point = new Point(backButtonX, backButtonY);
            action.tap(PointOption.point(point))
                    .release()
                    .perform();
*/
            // Return the back button location.
        }
    }

    public void deSelectRadioButton(WebElement element) {
        if (isSelected(element))
            element.click();
    }
/*
	public void scrollUpUntilElementLocated(WebElement element) {

		int width = driver.manage().window().getSize().width / 2;
		int height = driver.manage().window().getSize().height;
		TouchAction touchAction = new TouchAction();

		while (!isDisplayed(element)) {

			touchAction
					.click(PointOption.point(width, height - (height / 4)))
					.waitAction(WaitOptions.waitOptions(ofMillis(300)))
					.moveTo(PointOption.point(width, height - (height / 2)))
					.release()
					.perform();
		}
	}

	public void scrollDownUntilElementLocated(MobileElement element) {
		int width = appiumDriver.manage().window().getSize().width / 2;
		int height = appiumDriver.manage().window().getSize().height / 2;
		io.appium.java_client.TouchAction touchAction = new io.appium.java_client.TouchAction(appiumDriver);
		while (!isDisplayed(element)) {
			touchAction
					.press(PointOption.point(width, height))
					.waitAction(WaitOptions.waitOptions(ofMillis(400)))
					.moveTo(PointOption.point(width, height - (height / 2)))
					.release()
					.perform();
		}
	}

	public void scrollDown(MobileElement element) {
		int width = appiumDriver.manage().window().getSize().width / 2;
		int height = appiumDriver.manage().window().getSize().height;
		io.appium.java_client.TouchAction touchAction = new io.appium.java_client.TouchAction(appiumDriver);
		touchAction.press(PointOption.point(width, 2000))
				.waitAction(WaitOptions.waitOptions(ofMillis(400)))
				.moveTo(PointOption.point(width, 500))
				.release()
				.perform();

	}

	public void scrollUp() {
		int width = appiumDriver.manage().window().getSize().width / 2;
		int height = appiumDriver.manage().window().getSize().height / 3;

		new io.appium.java_client.TouchAction(appiumDriver)
				.press(PointOption.point(width, height * 2))
				.waitAction(WaitOptions.waitOptions(ofMillis(1000)))
				.moveTo(PointOption.point(width, 20))
				.release()
				.perform();
	}

	public void scrollDown() {
		int width = appiumDriver.manage().window().getSize().width / 2;
		int height = appiumDriver.manage().window().getSize().height / 3;

		new io.appium.java_client.TouchAction(appiumDriver)
				.press(PointOption.point(width, 20))
				.waitAction(WaitOptions.waitOptions(ofMillis(400)))
				.moveTo(PointOption.point(width, height * 2))
				.release()
				.perform();
	}
	*/


    /**
     * the method swipeFromTo take two elements
     *
     * @param destele  the element shall be appeared after swiped ,it is a destination element(swipe From)
     * @param startele the start element where the method  swiping to
     */
    public void swipeFromTo(WebElement destele, WebElement startele) {
//    driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[ contains(@name,\"Quick Pay\")]")).click();
//        try {
//            Thread.sleep(9000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        Map<String, Object> params = new HashMap<>();
        params.put("duration", 1.0);
        params.put("toX", startele.getLocation().x);

        params.put("toY", startele.getLocation().y);

        params.put("fromX", destele.getLocation().x);

        params.put("fromY", destele.getLocation().y);

        ((AppiumDriver) driver).executeScript("mobile: dragFromToForDuration", params);

    }

    public void swipeLeft(WebElement element) {

        Map<String, Object> params = new HashMap<>();
        params.put("direction", "left");
        params.put("element", element);
        ((AppiumDriver) driver).executeScript("mobile:swipe", params);


    }

    public void dynamicHorizontalSwipe(List<WebElement> elements) {
        int y = elements.get(0).getLocation().getY();
        int startx = (driver.manage().window().getSize().width) * 3 / 4;
        int endx = elements.get(0).getLocation().getX() * 0;
        new io.appium.java_client.TouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(startx, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(endx, y)).release().perform();
    }

    public void swipeHorzontalFromContainer(By containerBy, String expected) {
        WebElement containerele = driver.findElement(containerBy);
        System.out.println("height" + containerele.getSize().height + " the width " + containerele.getSize().width);

    }

    public boolean isFullyVisible(WebElement element) {
        Boolean isElementFullyVisible = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var element = arguments[0];" +
                        "var rect = element.getBoundingClientRect();" +
                        "var viewportHeight = window.innerHeight || document.documentElement.clientHeight;" +
                        "var viewportWidth = window.innerWidth || document.documentElement.clientWidth;" +
                        "return (" +
                        "   rect.top >= 0 &&" +
                        "   rect.left >= 0 && " +
                        "   rect.bottom <= viewportHeight &&" +
                        "   rect.right <= viewportWidth" +
                        ");",
                element // Pass the WebElement you want to check
        );
        System.out.println("inside JS  status is false or true $$$$$$$" + isElementFullyVisible);

        return isElementFullyVisible;
    }

    public boolean isFullyVisibleDim(WebElement element) {
        boolean isElementFullyVisible = false;
        System.out.println("The size of element  location " + element.getLocation());
        Point targetelepoint = element.getLocation();
        if (targetelepoint.getY() > 0 && targetelepoint.getY() < (0.5 * driver.manage().window().getSize().height)) {

            isElementFullyVisible = true;
        }
        return isElementFullyVisible;
    }

    public boolean isElementVisibleinViewPort(WebElement ele) {
        Boolean isElementFullyVisible = (Boolean) ((JavascriptExecutor) driver).executeScript(
                " var  element  = arguments[0];" +
                        "var rect = element.getBoundingClientRect();" +
                        "return(isElementInViewPort(rect));", ele
        );
        return isElementFullyVisible;

    }

    /**
     * selectElementFromWithinListRefresh : this method specifically for elements in slider at HomePage only
     * as it refresh the page source
     *
     * @param elementsList
     * @param expectedText
     * @return
     */
    public boolean selectElementFromWithinListRefresh(List<WebElement> elementsList, String expectedText) {
        try {
            String actualText = "";
            boolean found = false;
            while (true) {
                for (WebElement element : elementsList) {

                    System.out.println("###########show the element ######## " + element.getAttribute("name"));
                    actualText = element.getText().trim();

                    boolean isStale = ExpectedConditions.stalenessOf(element).apply(driver);
                    System.out.println("The flag is " + isStale + "for below element has path" + getLocator(element));

                    if (actualText.contains(expectedText) && element.getAttribute("visible").equals("true")) {
                        found = true;
                        element.click();
                        System.out.println("Width of Screen: " + driver.manage().window().getSize().width);
                        System.out.println("Height of Screen: " + driver.manage().window().getSize().height);
                        return true;
                    }
                }
//
//                System.out.println("******************************************"+"\n");
//                System.out.println(driver.getPageSource());
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
//                System.out.println("******************************************"+"\n");

                if (found == true) {
                    break;
                } else {
                    Log.info("Swiping ...");
                    dynamicHorizontalSwipe(elementsList);
                    if (expectedText.contains("Fawry Pay (") || expectedText.contains("Quick Pay")) {
                        System.out.println("before workaroundtoRefreshAtHomeScreen ");
                        workaroundtoRefreshAtHomeScreen();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("The Exception is " + e.toString());
            Log.info("***** Element with String : " + expectedText + " Is not found in the list" + " *****");

        }
        return false;

    }

    public void selectFromListRefreshPageSource(List<WebElement> sliderBtns, String expectedText) {
        boolean r = false;
        while (!r) {
            if (sliderBtns.size() > 0) {

                r = selectElementFromWithinListRefresh(sliderBtns, expectedText);
                System.out.println(r);
            }

        }
    }

    public void selectSpecificElementFromWithinList(List<WebElement> elementsList, String expectedText) {

        try {
            String actualText = "";
            boolean found = false;
            while (true) {
                for (WebElement element : elementsList) {

                    System.out.println("###########show the element ######## " + element.getAttribute("name"));
                    actualText = element.getText().trim();

                    boolean isStale = ExpectedConditions.stalenessOf(element).apply(driver);
                    System.out.println("The flag is " + isStale + "for below element has path" + getLocator(element));

                    if (actualText.contains(expectedText) && element.getAttribute("visible").equals("true")) {
                        found = true;
                        element.click();
                        System.out.println("Width of Screen: " + driver.manage().window().getSize().width);
                        System.out.println("Height of Screen: " + driver.manage().window().getSize().height);


                        break;
                    }
                }


                if (found == true) {
                    break;
                } else {
                    Log.info("Swiping ...");

                    dynamicHorizontalSwipe(elementsList);
                }
            }
        } catch (Exception e) {
            Log.info("***** Element with String : " + expectedText + " Is not found in the list" + " *****");

        }
    }

    public void selectElementFromMany(String selectedtext, By listlements, By container) {
        List<WebElement> elements = driver.findElements(listlements);

        int i = 0;
        int requiredindex = 0;
        boolean IsFound = false;
        while (!IsFound) {
            // providers=driver.findElements(providers_xpath); // not updated  after swipe
            do {
                System.out.println(elements.get(i).getAttribute("name"));
                String actual = elements.get(i).getAttribute("name");
                if (selectedtext.equalsIgnoreCase(actual) && isFullyVisible(elements.get(i))) {
                    IsFound = true;
                    requiredindex = i;
                }
                i++;
            } while (i < elements.size());
            if (IsFound) {
                System.out.println("Found in  " + requiredindex + "index ");
                click(elements.get(requiredindex));
            } else {
                swipeLeft(driver.findElement(container));
                //wait untill eleements are staless
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                //wait.until(ExpectedConditions.stalenessOf(vouchers.get(0)));
                i = 0;
                elements = driver.findElements(listlements);
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
    }

    public String getTextofElement(WebElement ele) {
        return ele.getAttribute("name");
    }

    public void verticalSwipe() {
        Point dim = driver.manage().window().getPosition();
        int h = dim.y;
        int w = dim.x;
        int x = w / 2;
        int starty = (int) (h * 0.80);
        int endy = (int) (h * 0.6);
        TouchAction action = new TouchAction((PerformsTouchActions) driver);
        action.press(PointOption.point(x, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x, endy)).release().perform()
        ;

    }

    /**
     * Method : verticalSwipeTillElement
     * input : MobileElemnet
     */
    public void verticalSwipeTillElement(WebElement mobele) {
        // !mobele.isDisplayed()!Boolean.valueOf(mobele.getAttribute("visible"))
        while (!isDisplayed(mobele)) {

            verticalSwipe();

        }


    }

    /***
     * This method handles the issue [ element has attribute visible = true  however it is hidden the viewport ]
     * @param mobele
     */

    public void verticalSwipeTillElementDim(WebElement mobele) {
        while (!isFullyVisibleDim(mobele) && mobele.isDisplayed()) {
            verticalSwipe();
        }


    }

    /**
     * Desription: the method takes a string parameter to scroll to it in the same page
     *
     * @param str
     */
    public void scrollTo(String str) {
        HashMap<String, Object> scrolobj = new HashMap<>();
        scrolobj.put("predicateString", "name =='" + str + "'");
        ((AppiumDriver) driver).executeScript("mobile: scroll", scrolobj);
        System.out.println("inside the function scrollTo");
    }

    public void horizontalSwipe() {
        Dimension dim = driver.manage().window().getSize();
        int h = dim.height;
        int w = dim.width;
        int startx = (int) (w * 0.8);
        int endx = (int) (w * 0.6);
        int y = h / 2;

    }

    public String getLocator(WebElement element) {
        String locator;
        String text = element.toString();
        text = text.substring(0, text.length() - 1);
        if (text.contains("xpath")) {
            locator = text.split("xpath: ")[1];
            return locator;
        } else if (text.contains("id")) {
            locator = text.split("id: ")[1];
            return locator;
        } else if (text.contains("className")) {
            locator = text.split("className: ")[1];
            return locator;
        } else {
            return null;
        }
    }


    public String getDigitFromEntryPoint(WebElement ele) {
        String digits = "";
        String[] listwords = ele.getAttribute("name").split("\\(|\\)");
        if (listwords.length == 2) {
            digits = listwords[1];
        }
        return digits;
    }

    public enum MoveDirection {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * the  method implements the move gestures in the mobile like scroll and swipe  in different direction
     * respective to viewport
     *
     * @param dir   enter UP, DOWN,LEFT or Right
     * @param ratio double number from 0 and 1
     */
    public void move(MoveDirection dir, double ratio, By containerby) {
        Dimension size = driver.findElement(containerby).getSize();
        Point containerpoint = driver.findElement(containerby).getLocation();

        System.out.println("container point " + containerpoint.getX() + " " + containerpoint.getY());
        System.out.println("size of container " + size.width + "  " + size.height);
        Point midpoint = new Point((int) (containerpoint.getX() + (size.width / 2)), (int) containerpoint.getY() + (size.height / 2));
        // Point midpoint = new Point(200,208);
        System.out.println("the x and y for midpoint :" + midpoint.x + " " + midpoint.y);
        Point start = null, end = null;

        switch (dir) {
            case LEFT:
                // int offset=size.height/2;
                int left = midpoint.x - (int) (midpoint.x * ratio);
                int right = midpoint.x + (int) (midpoint.x * ratio);
                start = new Point(right, midpoint.y);
                end = new Point(left, midpoint.y);
                swipeHorzDyn(start, end, Duration.ofMillis(300));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

                break;

            case RIGHT:
                swipeHorzDyn(start, end, Duration.ofMillis(300));
                break;

            case UP:
                int bottom = midpoint.y + (int) (midpoint.y * ratio);
                int top = midpoint.y - (int) (midpoint.y * ratio);
                start = new Point(midpoint.x, bottom);
                end = new Point(midpoint.x, top);

                break;
            case DOWN:
                break;
        }


    }

    public void swipeHorzDyn(Point start, Point end, Duration duration) {

        System.out.println("Size window Hight  " + driver.manage().window().getSize().height + "Size window width  " + driver.manage().window().getSize().width);
        System.out.println("start x  " + start.getX() + "  Start y  " + start.getY());
        System.out.println("end x  " + end.getX() + "  end y  " + end.getY());

        PointerInput pi = new PointerInput(PointerInput.Kind.TOUCH, "Finger");
        Sequence horzswipe = new Sequence(pi, 0);
        horzswipe.addAction(pi.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y)); // starting from the position of button
        // then move to another point in left createpointerdown means click on
        horzswipe.addAction(pi.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        horzswipe.addAction(new Pause(pi, duration));
        horzswipe.addAction(pi.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), end.x, end.y));
        horzswipe.addAction(pi.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((AppiumDriver) driver).perform(ImmutableList.of(horzswipe));


    }

//    /**
//     * Desription: the method takes a string parameter to scroll to it in the same page
//     * @param str
//     */
//    public void scrollTo(String str)
//    {
//        HashMap<String,Object> scrolobj= new HashMap<>();
//        scrolobj.put("predicateString","name =='"+str+"'");
//        ((AppiumDriver)driver).executeScript("mobile: scroll",scrolobj);
//        System.out.println("the function scrol to ");
//    }

    //    /**
//     * the method swipeFromTo take two elements
//     * @param destele the element shall be appeared after swiped ,it is a destination element(swipe From)
//     * @param startele the start element where the method  swiping to
//     */
//    public  void swipeFromTo(WebElement destele,WebElement startele)
//    {
//
//        Map<String,Object> params= new HashMap<>();
//        params.put("duration",1.0);
//        params.put("toX",startele.getLocation().x);
//        System.out.println("faw x"+startele.getLocation().x);
//
//        params.put("toY",startele.getLocation().y);
//        System.out.println("faw y"+startele.getLocation().y);
//
//        params.put("fromX", destele.getLocation().x);
//        System.out.println("qick x"+destele.getLocation().x);
//
//        params.put("fromY", destele.getLocation().y);
//        System.out.println("quick y "+destele.getLocation().y);
//
//        ((AppiumDriver)driver).executeScript("mobile: dragFromToForDuration",params);
//
//    }
    public void workaroundtoRefreshAtHomeScreen() {
        driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Telecom\"]")).click();
        driver.findElement(backBtnXpath).click();
/*


        // try to refresh the element

        String xpath=getLocator(elementsList);
        System.out.println("Xpath of list is "+ xpath);
       // elementsList= driver.findElements(By.xpath(xpath));
        System.out.println("size of elements is "+ elementsList.size());

 */


    }


}