package pages;

import data.Time;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LoggerUtils;
import utils.PropertiesUtils;
import utils.WebDriverUtils;

import java.time.Duration;
import java.util.function.Function;

public abstract class BasePageClass extends LoggerUtils {

    protected WebDriver driver;

    public BasePageClass(WebDriver driver){
       this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private WebDriverWait getWebDriverWaitInstance(int timeout){
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    protected void openUrl(String url){
        driver.get(url);
    }
    protected String getPageUrl(String sPagePath){
        return PropertiesUtils.getBaseUrl()+sPagePath;
    }
    protected WebElement getWebElement( By locator){
    return driver.findElement(locator);

    }
protected WebElement getWebElement(By locator,int timeout ){
    WebDriverWait wait=getWebDriverWaitInstance(timeout);
    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}
protected WebElement getWebElement(By locator,int timeout, int pollingInterval){
    Wait<WebDriver>wait= new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(timeout))
            .pollingEvery(Duration.ofSeconds(pollingInterval))
            .ignoring(NoSuchElementException.class);
    WebElement element=wait.until(new Function<WebDriver, WebElement>() {
        @Override
        public WebElement apply(WebDriver webDriver) {
            return driver.findElement(locator);
        }
    });
    return  element;
}

protected boolean isWebElementDisplayed(By locator){
        try{
        WebElement element=getWebElement(locator);
        return element.isDisplayed();}
        catch(NoSuchElementException e){
            return false;
        }
}

    protected boolean isWebElementDisplayed(WebElement element){
        try{
        return element.isDisplayed();}

      catch(NoSuchElementException e){
        return false;
    }}
    protected boolean isWebElementDisplayed(By locator,int timeout){
        try{
            WebElement element=getWebElement(locator,timeout);
            return element.isDisplayed();}
        catch(NoSuchElementException  | TimeoutException  e){ // najbolje svuda Eception
            return false;
        }
    }
    protected boolean isWebElementDisplayed(WebElement element, int timeout){
        try{
            WebDriverUtils.setImplicitWait(driver,timeout);
            return element.isDisplayed();}

        catch(NoSuchElementException e){
            return false;
        }
        finally {
            WebDriverUtils.setImplicitWait(driver,Time.IMPLICIT_WAIT);
        }

    }
    protected boolean isWebElementEnabled(WebElement element){
        try {
            return element.isEnabled();
        }
        catch (NoSuchElementException e){
            return false;
        }
    }
  protected  boolean isWebElementEnabled(WebElement element,int timeout){
        try{
        WebElement webElement=    waitForeWebElementToBeClickable(element,timeout);
        return webElement!=null;
        }
        catch(NoSuchElementException | TimeoutException e){
            return false;
        }

  }
    protected WebElement waitForeWebElementToBeClickable(WebElement element,int timeout){
        WebDriverWait wait=getWebDriverWaitInstance(timeout);
        WebElement until = wait.until(ExpectedConditions.elementToBeClickable(element));
        return until;
    }
    protected void clickOnWebElement(WebElement element, int timeout){
        Assert.assertTrue(isWebElementEnabled(element,timeout));
        element.click();
    }
    protected void clickOnWebElementJS(WebElement element){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript(" argument[0].click()",element);
    }
    protected void clickOnWebElement(WebElement element){
        element.click();
    }

    protected void typeTextToWebElement(WebElement element,String text){
        element.sendKeys(text);

    }
    protected  void clearText(WebElement element){
        element.clear();
    }
    protected void clearAndTypeTextToWebElement(WebElement element,String text){
     clearText(element);
        typeTextToWebElement(element,text);
    }
    protected String getTextFromWebElement(WebElement element){
        return element.getText();
    }
    protected String getAttributeFromWebElement(WebElement element,String attribute){
        return element.getAttribute(attribute);
    }
    protected String getValueAttributeFromWebElement(WebElement element,String attribute){
        return  getAttributeFromWebElement(element,"value");
    }
    protected String getValueAttributeFromWebElementJavaScript(WebElement element,String attribute){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        String sResult=(String) js.executeScript("return arguments[0].value",element);
       return sResult;
    }
    protected String getPlaceholderAttributeFromWebElement(WebElement element,String attribute){
        return  getAttributeFromWebElement(element,"placeholder");
    }
    protected boolean waitForUrlChange(String url, int timeout){
        WebDriverWait wait = getWebDriverWaitInstance(timeout);
       return wait.until(ExpectedConditions.urlContains(url));
    }
    protected  boolean waitForUrlChangeToExactUrl(String url, int timeout){
        WebDriverWait wait = getWebDriverWaitInstance(timeout);
        return wait.until(ExpectedConditions.urlContains(url));
    }
    protected boolean isWebPageReady(){
    JavascriptExecutor js=(JavascriptExecutor) driver;
    return js.executeScript("return document.readyState").equals("complete");
    }
    protected  boolean waitUntilPageIsReady(int timeout){
        WebDriverWait wait=getWebDriverWaitInstance(timeout);
       return  wait.until(driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
    }
    protected  boolean waitUntilPageIsReadyFluentWait(int timeout){
        WebDriverWait wait=getWebDriverWaitInstance(timeout);
        return  wait.until(webDriver -> {
            JavascriptExecutor js=(JavascriptExecutor) driver;
            return js.executeScript("return document.readyState").equals("complete");
        });
    }

}
