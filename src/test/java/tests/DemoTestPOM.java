package tests;

import data.CommonStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DateTimeUtils;
import utils.PropertiesUtils;
import utils.WebDriverUtils;





public class DemoTestPOM {

    private static final Logger log= LogManager.getLogger(DemoTestPOM.class);
    @Test
    public void testSuccessfulLogin() {



        WebDriver driver = null;
        String sUsername = PropertiesUtils.getUsername();
        String sPassword = PropertiesUtils.getPassword();
        try {
            log.info("Starting test 'SuccessfulLogin'");
            driver = WebDriverUtils.setUpDriver();
            DateTimeUtils.wait(3);

            //String sBaseUrl = PropertiesUtils.getBaseUrl();
            //String sLoginPage=sBaseUrl+PageUrlPaths.LOGIN_PAGE;

            log.trace("Open login page");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            System.out.println("Username text field before typing Username: " + loginPage.getUsername());
            System.out.println("Username placeholder  before typing Username: " + loginPage.getUsernamePlaceholder());

          /*  InventoryPage inventoryPage = loginPage
                    .typeUsername(sUsername).
                    typePassword(sPassword)
                    .clickLoginButton();

*/
            log.trace("Type username");
            loginPage.typeUsername(sUsername);

            //System.out.println("Username text field after typing Username: "+ loginPage.getUsername());
            // System.out.println("Username placeholder  after typing Username: "+ loginPage.getUsernamePlaceholder());
            //1 loginPage.typeUsername(sUsername);
            // By usernameTextFieldLocator = By.id("user-name");
            // WebElement usernameTextField = driver.findElement(usernameTextFieldLocator);
            //loginPage.typePassword(sPassword);


            // usernameTextField.sendKeys(sUsername);
            // DateTimeUtils.wait(Time.SHORTEST);
            log.trace("Type password");
             loginPage.typePassword(sPassword);
            // By passwordTextFieldLocator = By.xpath("//*[@id=\"password\"]");
            // WebElement passwordTextField = driver.findElement(passwordTextFieldLocator);
            // passwordTextField.sendKeys(sPassword);


            log.trace("Click login button");
            InventoryPage inventoryPage=loginPage.clickLoginButton();
            //By loginButtonFieldLocator = By.cssSelector("#login-button");
            //WebElement loginButtonField = driver.findElement(loginButtonFieldLocator);
            //loginButtonField.click();

            // InventoryPage inventoryPage=  loginPage.clickLoginButton();

            // String actualUrl = driver.getCurrentUrl();
            //String expectedUrl = inventoryPage.INVENTORY_PAGE_URL;
            // Assert.assertEquals(actualUrl, expectedUrl);

            log.trace("Verify inventory page");
            String sActualInventoryPageTitle = inventoryPage.getInventoryPageTitle();
            String sExpectedInventoryTitle = CommonStrings.getInventoryPageTitle();
            Assert.assertEquals(sActualInventoryPageTitle, sExpectedInventoryTitle);

        } finally {
            log.info("End test 'Successful login'");
            WebDriverUtils.quitDriver(driver);
        }

    }
    @Test
    public void unsuccessfulLoginWrongPassword() {

        WebDriver driver = null;

        try {
            driver = WebDriverUtils.setUpDriver();
            DateTimeUtils.wait(3);
            //  ne treba više jer smo odradili preko LOGIN_PAGE
            //String sBaseUrl = PropertiesUtils.getBaseUrl();
            //String sLoginPage=sBaseUrl+PageUrlPaths.LOGIN_PAGE;


            String sUsername = PropertiesUtils.getUsername();
            String sPassword = PropertiesUtils.getPassword() + "!";

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


        } finally {
            WebDriverUtils.quitDriver(driver);
        }
    }
}