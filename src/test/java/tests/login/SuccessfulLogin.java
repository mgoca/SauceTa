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
import utils.DateTimeUtils;
import utils.PropertiesUtils;
import utils.ScreenshotUtils;
import utils.WebDriverUtils;

public class SuccessfulLogin extends BaseTestClass {
    private final String sTestName = this.getClass().getName();
    private WebDriver driver;
    private final String sUsername = PropertiesUtils.getUsername();
    private final String sPassword = PropertiesUtils.getPassword();
    private final String sExpectedInventoryTitle = CommonStrings.getInventoryPageTitle();
    // ovo je samo teoretski ovde a koristi se u after metodi za rollback

    boolean bChanged = false;
    boolean bUploaded = false;

    @BeforeMethod
    public void setUpTest(ITestContext testContext) {
        //primer kako moze biti koristan za povezivanje sa jirom
        // testContext.setAttribute(sTestName,"JIRAID0004");


        log.info("SETUP TEST: " + sTestName);
        driver = setUpDriver();


        // ukoliko ne koristimo posojeceg usera vec treba da ubacimo novog usera ici ce preko rest apija ovde
        //RestApi.postUser("klajdl");
        // Establish email connection ---ako se testiraju emailovi
    }

    @Test
    public void testSuccessfulLogin() {

        log.debug("START TEST: " + sTestName);

        String sTestName = "testSuccessfulLogin";

        log.info("Starting test " + sTestName);

        DateTimeUtils.wait(3);

        // -> Settings change
        bChanged = true;

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        System.out.println("Username text field before typing Username: " + loginPage.getUsername());
        System.out.println("Username placeholder  before typing Username: " + loginPage.getUsernamePlaceholder());

        loginPage.typeUsername(sUsername);
        loginPage.typePassword(sPassword);
        InventoryPage inventoryPage = loginPage.clickLoginButton();

        // -> Documet Uploaded
        bUploaded = true;
        String sActualInventoryPageTitle = inventoryPage.getInventoryPageTitle();

        Assert.assertEquals(sActualInventoryPageTitle, sExpectedInventoryTitle);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult testResult) {
        log.info("END TEST " + sTestName);
        tearDown(driver, testResult);
        //zatim rollbackujemo rezultate
        cleanUp();

    }

    //rollbackovanje
    public void cleanUp() {
        log.debug("Clean up");


        try {
            if (!bChanged) {
                //rollback settings
                if (bUploaded) {
                    //delete document

                }
            }

        } catch (Exception | AssertionError e) {
            log.warn("Clean up failed " + e.getMessage());

        }

    }
}