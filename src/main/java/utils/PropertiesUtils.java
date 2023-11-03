package utils;

import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    private static final String sPropertiesPathFile = "test.properties";
    private static Properties testProperties = readPropertiesFile(sPropertiesPathFile);

    public static Properties readPropertiesFile(String relativePathToFile) {

        InputStream stream = PropertiesUtils.class.getClassLoader().getResourceAsStream(relativePathToFile);
        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            Assert.fail("Error reading properties file" + relativePathToFile + "! Message: " + e.getMessage());
        }
        return properties;
    }

    private static String getTestProperty(String sProperty) {
        String sResult = testProperties.getProperty(sProperty);
        Assert.assertNotNull(sProperty, "Can not find " + sProperty + " in  property file " + sPropertiesPathFile);
        return sResult;

    }

    public static String getBrowser() {
        return getTestProperty("browser");
    }public static String getEnvironment() {
        return getTestProperty("environment");}
    private static String getLocalBaseUrl() {
        return getTestProperty("localBaseUrl");}
    private static String getTestBaseUrl() {
        return getTestProperty("testBaseUrl");}
    private static String getProdBaseUrl() {
        return getTestProperty("prodBaseUrl");
    }
    public static String getBaseUrl() {
        String sEnvironment = getEnvironment();
        String sBaseUrl = null;
        switch (sEnvironment) {

            case "local":
                sBaseUrl = getLocalBaseUrl();
                break;
            case "test":
                sBaseUrl = getTestBaseUrl();
                break;
            case "prod":
                sBaseUrl = getProdBaseUrl();
                break;
            default:
                Assert.fail("Can not get baseUrl.Environment " + sEnvironment + " is not recognized!");
        }
        return sBaseUrl;
    }
    public static String getUsername() {
        return getTestProperty("userUsername");
    }
    public static String getPassword() {
        return getTestProperty("userPassword");
    }
    public static Boolean getRemote() {
        String sRemote = getTestProperty("remote");
        sRemote.toLowerCase();
        if (!(sRemote.equals("true") || sRemote.equals("false"))) {
            Assert.fail("Can not convert property value" + sRemote + "to boolean");
        }
        return Boolean.parseBoolean(sRemote);
    }
    public static Boolean getHeadless(){
        String sHeadless=getTestProperty("headless");
        sHeadless.toLowerCase();
        if(!(sHeadless.equals("true") || sHeadless.equals("false"))){
            Assert.fail("Can not convert property value" + sHeadless + "to boolean");
        }
        return Boolean.parseBoolean(sHeadless);
    }
    public static String getHubUrl(){
        return getTestProperty("hubUrl");
    }
    public static String getLocale(){
        return getTestProperty("locale");
    }
}
