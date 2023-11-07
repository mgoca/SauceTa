package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils extends LoggerUtils {

    private static final String SCREENSHOTS_FOLDER=System.getProperty("user.dir")+PropertiesUtils.getScreenShotFolder();
    private static String createShotScreenPath(String sFileName){
      return SCREENSHOTS_FOLDER +sFileName+".png";//.jpg

    }
    public static String takeScreenshot(WebDriver driver,String sTestName){

        if(driver==null){
            log.warn("SreenShot for test: " +sTestName+" could not be taken.Driver instance has quit.");
             return null;
        }
        String pathToFile=createShotScreenPath(sTestName);

        TakesScreenshot screenshot=(TakesScreenshot) driver;
        File srcFile=screenshot.getScreenshotAs(OutputType.FILE);
        File dstFile=new File(pathToFile);
        try {
            FileUtils.copyFile(srcFile,dstFile);
            log.info("ScreenShot for test  "+ sTestName+" is saved in file "+pathToFile);
        } catch (IOException e) {

            log.warn("ScreenShot for test  "+ sTestName+" could not be save in file  "+pathToFile+"! Message: "+e.getMessage());
            return null;
        }
        return pathToFile;
    }

}
