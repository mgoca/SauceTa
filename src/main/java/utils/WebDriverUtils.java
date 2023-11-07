package utils;

import data.Time;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.URL;
import java.time.Duration;

public class WebDriverUtils extends LoggerUtils {

    public static WebDriver setUpDriver() {

        String sBrowser=PropertiesUtils.getBrowser();
        Boolean bRemote=PropertiesUtils.getRemote();
        Boolean bHeadless=PropertiesUtils.getHeadless();
        String sHubUrl=PropertiesUtils.getHubUrl();


        //ne trebaju putanje kada koristimo WebDriverManager
        String sChromeDriverPath="D:\\DrajveriZaTestiranje\\chromedriver.exe";
        String sFirefoxDriverPath="D:\\DrajveriZaTestiranje\\geckodriver.exe";
        String sEdgeDriverPath="D:\\DrajveriZaTestiranje\\msedgedriver.exe";
        WebDriver driver=null;
        log.info("setUpDriver(Browser: "+sBrowser+" ,Remote: "+bRemote+" Headles: "+bHeadless);
       try{
        switch(sBrowser){

            case "chrome":{
                ChromeOptions options=new ChromeOptions();
               if(bHeadless){
                options.addArguments("--window-size=1600x900pix");
                options.addArguments("--headless");
               }
              // WebDriverManager.chromedriver().setup();
                if(bRemote){
                   // driver=WebDriverManager.chromedriver().capabilities(options).remoteAddress(sHubUrl).create();
                    RemoteWebDriver remoteDriver=new RemoteWebDriver(new URL(sHubUrl),options);
                    remoteDriver.setFileDetector(new LocalFileDetector());
                    driver=remoteDriver;// morali smo da ga kastujemo da bi radilo

                }
                else{
                   // System.setProperty("webdriver.chrome.driver",sChromeDriverPath);
                    driver=new ChromeDriver(options);
                }

                break;}
            case "firefox":{
                FirefoxOptions options=new FirefoxOptions();
                if(bHeadless){
                    options.addArguments("--window-size=1600x900pix");
                    options.addArguments("--headless");
                }
               // WebDriverManager.firefoxdriver().setup();
                if(bRemote){
                   // driver=WebDriverManager.firefoxdriver().capabilities(options).remoteAddress(sHubUrl).create();
                    RemoteWebDriver remoteDriver=new RemoteWebDriver(new URL(sHubUrl),options);
                    remoteDriver.setFileDetector(new LocalFileDetector());
                    driver=remoteDriver;// morali smo da ga kastujemo da bi radilo

                }
                else{
                   // System.setProperty("webdriver.gecko.driver",sFirefoxDriverPath);
                    driver=new FirefoxDriver(options);
                }

                break;}
            case "edge":{
                EdgeOptions options=new EdgeOptions();
                if(bHeadless){
                    options.addArguments("--window-size=1600x900pix");
                    options.addArguments("--headless");}
               //WebDriverManager.edgedriver().setup();
                if(bRemote){
                   // driver=WebDriverManager.edgedriver().capabilities(options).remoteAddress(sHubUrl).create();
                    RemoteWebDriver remoteDriver=new RemoteWebDriver(new URL(sHubUrl),options);
                    remoteDriver.setFileDetector(new LocalFileDetector());
                    driver=remoteDriver;// morali smo da ga kastujemo da bi radilo

                }
                else{
                  //  System.setProperty("webdriver.edge.driver",sEdgeDriverPath);
                    driver=new EdgeDriver(options);
                }

                break;}
            default:
                Assert.fail("Browser is not recognized");
        }}
       catch(Exception e){
           Assert.fail("Can not create driver! Message: " +e.getMessage());
        }
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Time.IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Time.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Time.SHORT));


        return driver;
    }

    public static void quitDriver(WebDriver driver){

        if(driver!=null)
        {driver.quit();}



    }
    public static void setImplicitWait(WebDriver driver, int timeout){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }
}
