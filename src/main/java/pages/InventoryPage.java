package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class InventoryPage extends BasePageClass{


    //locators

  //  private final By inventoryPageTitleLocator=By.cssSelector(".title");
    @FindBy(css=".title")
    WebElement inventoryPageTitle;

    public final String INVENTORY_PAGE_URL= getPageUrl(PageUrlPaths.INVENTORY_PAGE) ;

    public InventoryPage(WebDriver driver) {
       super(driver);
    }
    public InventoryPage open(){
        return open(true);
    }

    public InventoryPage open(boolean bVerify){
        openUrl(INVENTORY_PAGE_URL);
        if(bVerify){
        verifyPage();}
        return this;
    }
    public InventoryPage verifyPage(){
        waitForUrlChange(INVENTORY_PAGE_URL, Time.SHORTER);
        waitUntilPageIsReady(Time.SHORTER);
        return this;
    }
    public boolean isInventoryPageTitleDisplayed(){
        return isWebElementDisplayed(inventoryPageTitle);
    }
    public String getInventoryPageTitle(){
        Assert.assertTrue(isInventoryPageTitleDisplayed(),"Inventory page title is NOT displayed");
       // WebElement inventoryPageTitle=getWebElement(inventoryPageTitleLocator);
       return getTextFromWebElement(inventoryPageTitle);
    }

}
