package pages;

import data.PageUrlPaths;
import data.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.PropertiesUtils;

public class LoginPage extends BasePageClass{




    public final String LOGIN_PAGE = getPageUrl(PageUrlPaths.LOGIN_PAGE) ;

    //Locators

    //private final By usernameTextFieldLocator = By.id("user-name");

    @FindBy(id="user-name")
    private WebElement usernameTextField;



   // private final By passwordTextFieldLocator = By.xpath("//*[@id=\"password\"]");
    @FindBy(xpath="//*[@id=\"password\"]")
    private WebElement passwordTextField;
   // private final By loginButtonFieldLocator = By.cssSelector("#login-button");
    @FindBy(css = "#login-button")
    private WebElement loginButtonField;
    //private final By errorMessageLocator = By.xpath("//h3[@data-test='error']");
    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    //Constuctor
    public LoginPage(WebDriver driver) {
       super(driver);


    }

    public LoginPage open() {
     return open(true);
    }
    public LoginPage open(boolean bVerify) {
        openUrl(LOGIN_PAGE);
        if(bVerify)
        {verifyPage();}
        return this;
    }
    public LoginPage verifyPage(){
     waitForUrlChangeToExactUrl(LOGIN_PAGE,Time.SHORTER);
     waitUntilPageIsReady(Time.SHORTER);
     return this;
    }
    public boolean isUsernameTextFieldDisplayed(){
        return isWebElementDisplayed(usernameTextField);

    }

    public boolean isUsernameTextFieldEnabled(){
        Assert.assertTrue(isUsernameTextFieldDisplayed(),"UsernameTextField is NOT displayed");
       //WebElement element=getWebElement(usernameTextFieldLocator);
        return isWebElementEnabled(usernameTextField);
    }
    public boolean isPasswordTextFieldDisplayed(){
        return isWebElementDisplayed(passwordTextField);

    }

    public boolean isPasswordTextFieldEnabled(){
        Assert.assertTrue(isPasswordTextFieldDisplayed(),"PasswordTextField is NOT displayed");
       // WebElement element=getWebElement(passwordTextFieldLocator);
        return isWebElementEnabled(passwordTextField);
    }

    public boolean isLoginButtonDisplayed(){
        return isWebElementDisplayed(loginButtonField);

    }
    public boolean isLoginButtonFieldEnabled(){
        Assert.assertTrue(isLoginButtonDisplayed(),"Login button field field is NOT displayed");
      //  WebElement element=getWebElement(loginButtonFieldLocator);
        return isWebElementEnabled(loginButtonField,Time.SHORTER);
    }

    public LoginPage typeUsername(String username) {
        Assert.assertTrue(isUsernameTextFieldEnabled(),"Username text field is NOT enabled");
       // WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        clearAndTypeTextToWebElement(usernameTextField,username);
       return this;
    }
    public String getUsername(){
        Assert.assertTrue(isUsernameTextFieldDisplayed(),"Username text Field is NOT displayed");
       // WebElement element=getWebElement(usernameTextFieldLocator);
        return getValueAttributeFromWebElementJavaScript(usernameTextField,"value");
    }
    public String getUsernamePlaceholder(){
        Assert.assertTrue(isUsernameTextFieldDisplayed(),"Username text Field is NOT displayed");
       // WebElement element=getWebElement(usernameTextFieldLocator);
        return getPlaceholderAttributeFromWebElement(usernameTextField,"value");
    }
    public LoginPage typePassword(String password) {
        Assert.assertTrue(isPasswordTextFieldEnabled(),"Password text field is NOT enabled");
       // WebElement passwordTextField = getWebElement(passwordTextFieldLocator);
        clearAndTypeTextToWebElement(passwordTextField,password);
        return this;
    }
   public void clickLoginButtonNoVerification(){
       Assert.assertTrue(isLoginButtonFieldEnabled(),"Login button is NOT enabled");
       //WebElement loginButton = getWebElement(loginButtonFieldLocator);
       clickOnWebElement(loginButtonField);

   }
    public InventoryPage clickLoginButton() {
     clickLoginButtonNoVerification();
        InventoryPage inventoryPage=new InventoryPage(driver);
        return inventoryPage.verifyPage();
    }
    public LoginPage clickLoginButtonNoProgress(){
      clickLoginButtonNoVerification();
        LoginPage loginPage=new LoginPage(driver);
        return loginPage.verifyPage();
    }

    public String getUsernameText() {
       // WebElement usernameTextField = getWebElement(usernameTextFieldLocator);
        return usernameTextField.getText();
    }

    public Boolean isErrorMessageDisplayed() {
        return isWebElementDisplayed(errorMessage,Time.SHORTER);

    }

    public String getErrorMessage() {
        Assert.assertTrue(isErrorMessageDisplayed(),"Error messafe is NOT displayed");
       // WebElement errorMessage = getWebElement(errorMessageLocator);
        return getTextFromWebElement(errorMessage);
    }


    public InventoryPage login(String username,String password){
        typeUsername(username);
        typePassword(password);
        return clickLoginButton();

    }
}