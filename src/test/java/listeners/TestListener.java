package listeners;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTests;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TestListener extends BaseTests implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class.getName());

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("STARTING METHOD: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("TEST METHOD: " + result.getMethod().getMethodName() + " PASSED ");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("TEST METHOD: " + result.getMethod().getMethodName() + " FAILED ");
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTests)testClass).getDriver();
        takeScreenshot(driver);
        Allure.addAttachment(UUID.randomUUID().toString(),
                new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    private void takeScreenshot(WebDriver driver) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy_hh-mm-ss");
        String fileName = formatter.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/screenshots/" + fileName + ".png"));
        }catch (IOException e) {
            e.printStackTrace();
        }

    }



}//end class

