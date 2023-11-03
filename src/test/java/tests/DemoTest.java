package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DateTimeUtils;

import java.time.Duration;

public class DemoTest {
    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        WebDriver driver =null;
try {
   // Assert.fail("cvrc");
    driver = new FirefoxDriver();
    String sFirefoxDriverFullPath = "D:\\DrajveriZaTestiranje\\geckodriver.exe";
    //String sFirefoxDriverFullPath = "D:\\QA-Endava video\\SeleniumDriver\\MozzilaDriver\\geckodriver.exe";

    System.setProperty("webdriver.gecko.driver", sFirefoxDriverFullPath);

    driver.manage().window().maximize();

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


    String sBaseUrl = "https://www.saucedemo.com/";
    driver.get(sBaseUrl);
    String title = driver.getTitle();
    System.out.println(title);
//        driver.close();
//

//        By usernameTextFieldLocator=By.id("user-name");
//        WebElement usernameTextField=driver.findElement(usernameTextFieldLocator);
//        usernameTextField.sendKeys("standard_user");
//najbanalnije ide ovako
   // driver.findElement(By.id("user-name")).sendKeys("standard_user");

    By usernameTextFieldLocator=By.id("user-name");
    WebElement usernameTextField=driver.findElement(usernameTextFieldLocator);

    WebDriverWait waiter=new WebDriverWait(driver,Duration.ofSeconds(5));

    waiter.until(ExpectedConditions.presenceOfElementLocated(usernameTextFieldLocator));

    usernameTextField.sendKeys("standard_user");
    DateTimeUtils.wait(5);
    driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
    driver.findElement(By.cssSelector("#login-button")).click();
    Thread.sleep(7);

    String actualUrl = driver.getCurrentUrl();
    String expectedUrl = "https://www.saucedemo.com/inventory.html";
    Assert.assertEquals(actualUrl, expectedUrl);

}
finally {
    if(driver!=null){
    driver.quit();}
}

    }
    @Test
    public void unsuccessfulLoginWrongPassword() throws InterruptedException {

        WebDriver driver =null;

        try{
            driver = new FirefoxDriver();
            String sFirefoxDriverFullPath = "D:\\DrajveriZaTestiranje\\geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", sFirefoxDriverFullPath);

            driver.manage().window().maximize();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


            String sBaseUrl = "https://www.saucedemo.com/";
            driver.get(sBaseUrl);
            By usernameTextFieldLocator=By.id("user-name");
            WebElement usernameTextField=driver.findElement(usernameTextFieldLocator);

            WebDriverWait waiter=new WebDriverWait(driver,Duration.ofSeconds(5));

            waiter.until(ExpectedConditions.presenceOfElementLocated(usernameTextFieldLocator));

            usernameTextField.sendKeys("standard_user");
            DateTimeUtils.wait(5);
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secretffd_sauce");
            driver.findElement(By.cssSelector("#login-button")).click();
            Thread.sleep(7);

           // String actualUrl = driver.getCurrentUrl();
           // String expectedUrl = "https://www.saucedemo.com";
           // Assert.assertEquals(actualUrl, expectedUrl);

            By errorMessageLocator= By.xpath("//h3[@data-test='error']");
            WebElement errorMessage=driver.findElement(errorMessageLocator);
            String actualMessage = errorMessage.getText();
            String expectedMessage="Epic sadface: Username and password do not match any user in this service";
            Assert.assertEquals(actualMessage,expectedMessage,"WrongError Messages");
        }finally{
            if(driver!=null){
                driver.quit();}


        }



    }

}
