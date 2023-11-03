package data;

import org.testng.Assert;
import utils.PropertiesUtils;

import java.util.Properties;

public final class CommonStrings {
 //prvo napravimo optimizovanu promenljivu za locale
    private static final String sLocaleFile="locale_" + PropertiesUtils.getLocale() + ".loc";
    //zatim putanju do promenljive
    private static final String sLocalePath="\\locale\\" +sLocaleFile;
   //zatim promenljivu gde učitavamo taj fajl
    private static Properties locale=PropertiesUtils.readPropertiesFile(sLocalePath);
    // i ovde dohvatamo tu vrednost iz fajla, tako što u parametar stavimo tačan naziv ključa koji mora biti isti u svim fajlovima
    private static String getLocaleString(String sTitle ){
        String sText=locale.getProperty(sTitle);
        Assert.assertNotNull(sText,"String " +sTitle+" doesn't exist in file " + sLocaleFile );
        return sText;
    }

    public static final String getLoginErrorWrongCredentials(){
       return getLocaleString("LOGIN_ERROR_WRONG_CREDENTIALS");

    }
    public static final String getInventoryPageTitle(){
        return getLocaleString("INVENTORY_PAGE_TITLE");
    }



    //ne treba više
 // public static final String LOGIN_ERROR_WRONG_CREDENTIALS="Epic sadfasce: Username and password do not match any user in this service"  ;
  //public static final String LOGIN_ERROR_LOCKED_OUT_USER="Epic sadface:Sorry this user has been locked out";

}
