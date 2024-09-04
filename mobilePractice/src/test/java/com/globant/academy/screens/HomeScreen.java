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
    public static final String BOT_IMG = ".className(\"android.view.ViewGroup\").childSelector(.className(\"android.widget.ImageView\"))\n";
    public static final String DESCRIPTION_TXT = "new UiSelector().text(\"Demo app for the appium-boilerplate\")";
    public static final String APPLE_ICON_IMG = "new UiSelector().className(\"android.widget.TextView\").text" +
                                                "(\"\uDB80\uDC35\")";

    
    private static final Logger log = LoggerFactory.getLogger(HomeScreen.class);
    
    @AndroidFindBy(uiAutomator = TITLE_TXT)
    private WebElement titleTxt;
    @AndroidFindBy(uiAutomator = BOT_IMG)
    private WebElement botImg;
    @AndroidFindBy(uiAutomator = DESCRIPTION_TXT)
    private WebElement descriptionTxt;
    @AndroidFindBy(uiAutomator = APPLE_ICON_IMG)
    private WebElement appleIcon;
    
    public HomeScreen(AndroidDriver driver) {
        super(driver);
    }
    
    /**
     * Verify if the Title element is visible.
     *
     * @return boolean True if the element are displayed and focusable on the screen; otherwise, false.
     * @author Stephany Duran
     */
    public boolean isTitleDisplayed() {
        return isElementDisplayed(titleTxt);
    }
    
    public boolean isBotImgEnable() {
        return isElementEnable(botImg);
    }
    
    /**
     * Verifies if the description element on the home screen is visible.
     *
     * @return boolean True if the element are displayed and focusable on the screen; otherwise, false.
     * @author Stephany Duran
     */
    public boolean isDescriptionPresent() {
        return isElementDisplayed(descriptionTxt);
    }
    
    public boolean isAppleIconPresent() {
        return isElementDisplayed(appleIcon);
    }
    
    /**
     * Checks if the principal elements on the Home screen are visible.
     * This method verifies the visibility of essential UI components on the Home screen.
     *
     * @return boolean True if all specified elements are visible on the screen; otherwise, false.
     * @author Stephany Duran
     */
    public boolean arePrincipalElementsPresentInTheHomeScreen() {
        Map<String, Supplier<Boolean>> elementsToCheck = new HashMap<>();
        elementsToCheck.put("Title", this::isTitleDisplayed);
        elementsToCheck.put("Description", this::isDescriptionPresent);
        elementsToCheck.put("Bot Image", this::isBotImgEnable);
        elementsToCheck.put("Apple Icon", this::isAppleIconPresent);
        return super.arePrincipalElementsPresent(elementsToCheck);
    }
    
    
}
