package com.globant.academy.utils.screen;

import com.globant.academy.screens.HomeScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class BaseScreen {
    
    protected AndroidDriver driver;
    protected WebDriverWait wait;
    private static final Logger log = LoggerFactory.getLogger(HomeScreen.class);
    
    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void waitElementVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public boolean arePrincipalElementsPresent(Map<String, Supplier<Boolean>> elementsToCheck) {
        for(Map.Entry<String, Supplier<Boolean>> entry : elementsToCheck.entrySet()) {
            String elementName = entry.getKey();
            boolean isElementPresent = entry.getValue().get();
            if(!isElementPresent) {
                log.error("The element '{}' is not present on the screen", elementName);
                return false;
            }
        }
        return true;
    }
    
    public boolean isElementEnable(WebElement element) {
        waitElementVisibility(element);
        return element.isEnabled();
    }
    
    public boolean isElementDisplayed(WebElement element) {
        waitElementVisibility(element);
        return element.isEnabled();
    }
    
    public boolean isElementDisplayedAndContainText(WebElement element, String text) {
        waitElementVisibility(element);
        return element.getText().equalsIgnoreCase(text) && element.isDisplayed();
    }
    
    public boolean isElementClickable(WebElement element) {
        waitElementVisibility(element);
        return Boolean.parseBoolean(element.getAttribute("clickable"));
    }
    public void waitSomeSeconds(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        }
        catch(InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
