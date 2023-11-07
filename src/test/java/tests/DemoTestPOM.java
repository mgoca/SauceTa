package tests;

import data.CommonStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.*;


public class DemoTestPOM extends LoggerUtils {
   // WebDriver driver = null;

   /*    JEDAN NAČIN DA SE ODRADI SCREENSHOT PALIH TESTOVA, ALI DRIVER MORA BITI ISPRED METODA A DRUGI ĆE BITI U OKVIRU TESTA
    @AfterMethod
    public void tearDownTest(ITestResult testResult){
        String sMethodName=testResult.getMethod().getMethodName();
        if(testResult.getStatus()==ITestResult.FAILURE){
            log.info("Capturing screenshot");
            ScreenshotUtils.takeScreenshot(driver,sMethodName);
        }
        WebDriverUtils.quitDriver(driver);
    }
*/
    @Test
    public void testSuccessfulLogin() {


       WebDriver driver = null;
        String sUsername = PropertiesUtils.getUsername();
        String sPassword = PropertiesUtils.getPassword();

        String sTestName = "testSuccessfulLogin";

        boolean bSuccess=false;

        try {
            log.info("Starting test " + sTestName);
            driver = WebDriverUtils.setUpDriver();
            DateTimeUtils.wait(3);

            //String sBaseUrl = PropertiesUtils.getBaseUrl();
            //String sLoginPage=sBaseUrl+PageUrlPaths.LOGIN_PAGE;


            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            System.out.println("Username text field before typing Username: " + loginPage.getUsername());
            System.out.println("Username placeholder  before typing Username: " + loginPage.getUsernamePlaceholder());

          /*  InventoryPage inventoryPage = loginPage
                    .typeUsername(sUsername).
                    typePassword(sPassword)
                    .clickLoginButton();

*/

            loginPage.typeUsername(sUsername);

            //System.out.println("Username text field after typing Username: "+ loginPage.getUsername());
            // System.out.println("Username placeholder  after typing Username: "+ loginPage.getUsernamePlaceholder());
            //1 loginPage.typeUsername(sUsername);
            // By usernameTextFieldLocator = By.id("user-name");
            // WebElement usernameTextField = driver.findElement(usernameTextFieldLocator);
            //loginPage.typePassword(sPassword);


            // usernameTextField.sendKeys(sUsername);
            // DateTimeUtils.wait(Time.SHORTEST);

            loginPage.typePassword(sPassword);
            // By passwordTextFieldLocator = By.xpath("//*[@id=\"password\"]");
            // WebElement passwordTextField = driver.findElement(passwordTextFieldLocator);
            // passwordTextField.sendKeys(sPassword);


            InventoryPage inventoryPage = loginPage.clickLoginButton();
            //By loginButtonFieldLocator = By.cssSelector("#login-button");
            //WebElement loginButtonField = driver.findElement(loginButtonFieldLocator);
            //loginButtonField.click();

            // InventoryPage inventoryPage=  loginPage.clickLoginButton();

            // String actualUrl = driver.getCurrentUrl();
            //String expectedUrl = inventoryPage.INVENTORY_PAGE_URL;
            // Assert.assertEquals(actualUrl, expectedUrl);


            String sActualInventoryPageTitle = inventoryPage.getInventoryPageTitle();
            String sExpectedInventoryTitle = CommonStrings.getInventoryPageTitle();
            Assert.assertEquals(sActualInventoryPageTitle, sExpectedInventoryTitle);
            bSuccess=true;
        } finally {
            log.info("End test " + sTestName);
            if(!bSuccess){
                ScreenshotUtils.takeScreenshot(driver,sTestName);
                log.info("Capturing screenshot");
            }


            WebDriverUtils.quitDriver(driver);
        }

    }

    @Test
    public void unsuccessfulLoginWrongPassword() {

        String sTestName = "unsuccessfulLoginWrongPassword";
        WebDriver driver = null;

        boolean bSuccess=false;

        try {
            log.info("Starting test " + sTestName);
            driver = WebDriverUtils.setUpDriver();
            DateTimeUtils.wait(3);
            //  ne treba više jer smo odradili preko LOGIN_PAGE
            //String sBaseUrl = PropertiesUtils.getBaseUrl();
            //String sLoginPage=sBaseUrl+PageUrlPaths.LOGIN_PAGE;


            String sUsername = PropertiesUtils.getUsername();
             String sPassword = PropertiesUtils.getPassword() +"!";
            // String sPassword = PropertiesUtils.getPassword();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            loginPage.typeUsername(sUsername);
            loginPage.typePassword(sPassword);
            loginPage.clickLoginButtonNoProgress();
            // driver.get(sLoginPage);

//            loginPage.typeUsername(sUsername);
//            loginPage.typePassword(sPassword);
//            loginPage.clickLoginButton();

//            By usernameTextFieldLocator = By.id("user-name");
//            WebElement usernameTextField = driver.findElement(usernameTextFieldLocator);
//
//
//            usernameTextField.sendKeys(sUsername);
//            DateTimeUtils.wait(Time.SHORTEST);
//
//            By passwordTextFieldLocator = By.xpath("//*[@id=\"password\"]");
//            WebElement passwordTextField = driver.findElement(passwordTextFieldLocator);
//            passwordTextField.sendKeys(sPassword);
//
//            By loginButtonFieldLocator = By.cssSelector("#login-button");
//            WebElement loginButtonField = driver.findElement(loginButtonFieldLocator);
//            loginButtonField.click();

            // WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(4));
            //String sExpectedUrl=loginPage.LOGIN_PAGE;
            //wait.until(ExpectedConditions.urlToBe(sExpectedUrl));

            loginPage.verifyPage();
            //By errorMessageLocator= By.xpath("//h3[@data-test='error']");
            //WebElement errorMessage=driver.findElement(errorMessageLocator);

            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed");

            String actualMessage = loginPage.getErrorMessage();
            String expectedMessage = CommonStrings.getLoginErrorWrongCredentials();
            Assert.assertEquals(actualMessage, expectedMessage, "WrongError Messages");
            bSuccess=true;

        } finally {
            log.info("End test " + sTestName);
            if(!bSuccess){
                ScreenshotUtils.takeScreenshot(driver,sTestName);
                log.info("Capturing screenshot");
            }
            WebDriverUtils.quitDriver(driver);
        }
    }
}