package tests.login;

import data.CommonStrings;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import tests.BaseTestClass;
import utils.PropertiesUtils;

public class UnsuccessfulLoginWrongPassword extends BaseTestClass {
    private final String sTestName = this.getClass().getName();
    private WebDriver driver;
    private final String sUsername = PropertiesUtils.getUsername();
    private final String sPassword = PropertiesUtils.getPassword()+"!";


    @BeforeMethod
    public void setUpTest(ITestContext testContext) {
        log.info("SETUP TEST: " + sTestName);
        driver = setUpDriver();
 }
   @Test
   public void testUnsuccessfulLoginWrongCredentials(){


       log.debug("START TEST: " + sTestName);

       LoginPage loginPage = new LoginPage(driver);
       loginPage.open();
       loginPage.typeUsername(sUsername);
       loginPage.typePassword(sPassword);
       loginPage.clickLoginButtonNoProgress();

       Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed");

       String actualMessage = loginPage.getErrorMessage();
       String expectedMessage = CommonStrings.getLoginErrorWrongCredentials();
       Assert.assertEquals(actualMessage, expectedMessage, "WrongError Messages");
   }
    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.info("END TEST " + sTestName);
        tearDown(driver, testResult);
   }
}
