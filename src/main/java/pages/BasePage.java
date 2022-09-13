package pages;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static utils.Utils.dotEnv;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Faker faker;
    private static final Logger logger = LogManager.getLogger(BasePage.class.getName());
    private Long waitTime = Long.parseLong(dotEnv().get("EXPLICIT_WAIT"));

    public BasePage(WebDriver driver) {
        faker = new Faker();
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        PageFactory.initElements(driver, this);
    }
    protected WebElement getElement(By locator) {
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            //takeScreenshot(driver);
        }catch (StaleElementReferenceException e){
            logger.warn("StaleElementReferenceException occurred");
            driver.findElement(locator);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch (NoSuchElementException ex) {
            logger.warn("NoSuchElementException occurred");
            ex.printStackTrace();
        }
        return element;
    }

    protected void typeIn(By locator, String text) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void clickOnElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected String getTextFromElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }

    protected boolean isElementDisplayed(By locator) {
        boolean isElementVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        if (isElementVisible) {
            logger.info("PASSED - Element " + locator.toString() + " is visible on page ");
            return true;
        }else {
            logger.error("FAILED - Element " + locator.toString() + " is NOT visible on page ");
        }
        return false;
    }

    protected boolean matchesExpectedText(By locator, String expectedText) {
        WebElement element = getElement(locator);
        if (element.getText().trim().equals(expectedText)) {
            logger.info("PASSED - Text found in element " + element.getText() + " MATCHES expected text [ " + expectedText + " ]");
            return true;
        }else {
            logger.error("FAILED - Text found in element " + element.getText() + " DOES NOT MATCH expected text [ " + expectedText + "] ");
        }
        return false;
    }

    protected double getAmountByRegex(String value) {
        return Double.parseDouble(value.replaceAll("[^\\d.]", " "));
    }

//    protected void takeScreenshot(WebDriver driver) {
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy_hh-mm-ss");
//        String fileName = formatter.format(date);
//
//        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//        try {
//            FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/screenshots/" + fileName + ".png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}//end class
