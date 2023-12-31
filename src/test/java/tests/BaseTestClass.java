package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import utils.LoggerUtils;
import utils.ScreenshotUtils;
import utils.WebDriverUtils;

public class BaseTestClass extends LoggerUtils {

    protected WebDriver setUpDriver() {
        return WebDriverUtils.setUpDriver();

    }

    protected void quitDriver(WebDriver driver) {
        WebDriverUtils.quitDriver(driver);

    }

    protected void tearDown(WebDriver driver, ITestResult testResult) {
        String sTestName = testResult.getTestClass().getName();
        try {
            if (testResult.getStatus() == ITestResult.FAILURE) {
                ScreenshotUtils.takeScreenshot(driver, sTestName);
                log.info("Capturing screenshot");
            }
        } catch (Exception | AssertionError e) {
            log.error("Error occurred in tearDown in test " + sTestName + " Message: " + e.getMessage());
        } finally {
            quitDriver(driver);
        }
    }
}
