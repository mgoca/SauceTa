package utils;

import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Stream;

public class ProbaProperties {
    private static final String sPropertiesPathFile="test.properties";
    private static Properties testProperties=readPropertiesFile(sPropertiesPathFile);
    public static Properties readPropertiesFile(String relativePat){
        InputStream stream= ProbaProperties.class.getClassLoader().getResourceAsStream(relativePat);
        Properties properties=new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            Assert.fail("Greska");
        }
return  properties;
    }
    private static String getTestProperty(String sProperty){
       String sResult= testProperties.getProperty(sProperty);
        Assert.assertNotNull(sProperty,"Can not find " +sProperty+" in  property file " + sPropertiesPathFile);
       return  sResult;
    }
    private static String getBrowser(){
        return getTestProperty("browser");

    }
    public static String getEnvironment(){
        return getTestProperty("environment");

    }
    private static String getLocalBaseUrl(){
        return getTestProperty("localBaseUrl");

    }
    private static String getTestBaseUrl(){
        return getTestProperty("testBaseUrl");

    }
    private static String getProdBaseUrl(){
        return getTestProperty("prodBaseUrl");

    }
    public static String getBaseUrl(){
        String sEnvironment=getEnvironment();
        String sBaseUrl=null;
        switch(sEnvironment){
            case "local":
                sBaseUrl=getLocalBaseUrl();
                break;
            case "test":
                sBaseUrl=getTestBaseUrl();
                break;
            case "prod":
                sBaseUrl=getProdBaseUrl();
                break;
            default:
                Assert.fail("Can not get baseUrl.Environment " +sEnvironment + " is not recognized!");

        }
        return sBaseUrl;
    }
}
