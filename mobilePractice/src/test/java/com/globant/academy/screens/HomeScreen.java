package com.globant.academy.screens;

import com.globant.academy.utils.screen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class HomeScreen extends BaseScreen {
    
    public static final String TITLE_TXT = "new UiSelector().text(\"WEBDRIVER\")";
    public static final String BOT_IMG = ".className(\"android.widget.ImageView\").instance(0)";
    public static final String DESCRIPTION_TXT = "new UiSelector().text(\"Demo app for the appium-boilerplate\")";
    public static final String APPLE_ICON_IMG = "new UiSelector().className(\"android.widget.TextView\").text" +
                                                "(\"\uDB80\uDC35\")";
    public static final String WEBVIEW_BTN= "new UiSelector().text(\"Webview\")";
    
    private static final Logger log = LoggerFactory.getLogger(HomeScreen.class);
    
    @AndroidFindBy(uiAutomator = TITLE_TXT)
    private WebElement titleTxt;
    @AndroidFindBy(uiAutomator = BOT_IMG)
    private WebElement botImg;
    @AndroidFindBy(uiAutomator = DESCRIPTION_TXT)
    private WebElement descriptionTxt;
    @AndroidFindBy(uiAutomator = APPLE_ICON_IMG)
    private WebElement appleIcon;
    @AndroidFindBy(uiAutomator = WEBVIEW_BTN)
    private WebElement webViewBtn;
    
    public HomeScreen(AndroidDriver driver) {
        super(driver);
    }
    
    public boolean isTitleDisplayed() {
        return isElementDisplayed(titleTxt);
    }
    
    public boolean isBotImgEnable() {
        return isElementEnable(botImg);
    }
    
    public boolean isDescriptionPresent() {
        return isElementDisplayed(descriptionTxt);
    }
    
    public boolean isAppleIconPresent() {
        return isElementDisplayed(appleIcon);
    }
    
    public boolean arePrincipalElementsPresentInTheHomeScreen() {
        Map<String, Supplier<Boolean>> elementsToCheck = new HashMap<>();
        elementsToCheck.put("Title", this::isTitleDisplayed);
        elementsToCheck.put("Description", this::isDescriptionPresent);
        elementsToCheck.put("Bot Image", this::isBotImgEnable);
        elementsToCheck.put("Apple Icon", this::isAppleIconPresent);
        return super.arePrincipalElementsPresent(elementsToCheck);
    }
    
    public WebViewScreen clickOnWebViewOption() {
        webViewBtn.click();
        return new WebViewScreen(driver);
    }
}
