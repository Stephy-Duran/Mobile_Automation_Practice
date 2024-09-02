package com.globant.academy.utils.test;

import com.globant.academy.screens.HomeScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTest {
    
    private static final String PROPERTIES_FILE = "src/test/resources/config.properties";
    private static final Properties properties = new Properties();
    public static AndroidDriver driver;
    
    @BeforeMethod(alwaysRun = true)
    public void environmentSetUp() {
        loadProperties();
        UiAutomator2Options capabilities = new UiAutomator2Options();
        setUpCapabilities(capabilities);
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
        }catch(MalformedURLException exception){
            System.out.println(exception.getMessage());
        }
    }
    
    public void loadProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
            properties.load(fileInputStream);
        }
        catch(IOException e) {
            throw new RuntimeException("Error loading the properties file" + PROPERTIES_FILE);
        }
    }
    
    public static void setUpCapabilities(UiAutomator2Options capabilities) {
        capabilities.setPlatformName(getCapability("platformName"));
        capabilities.setDeviceName(getCapability("deviceName"));
        capabilities.setAppPackage(getCapability("appPackage"));
        capabilities.setAppActivity(getCapability("appActivity"));
    }
    
    public static String getCapability(String name) {
        return properties.getProperty(name);
    }
    
    public HomeScreen openHomeScreen(){
        return new HomeScreen(driver);
    }
    
    
}
