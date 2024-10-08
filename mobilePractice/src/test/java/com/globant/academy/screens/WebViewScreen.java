package com.globant.academy.screens;

import com.globant.academy.utils.screen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class WebViewScreen extends BaseScreen {
    
    private static final String BOT_IMG = "new UiSelector().text(\"WebdriverIO\")";
    private static final String TEXT_DESCRIPTION = "new UiSelector().text(\"Next-gen browser and mobile automation test framework for Node.js\")";
    private static final String BURGER_MENU = "new UiSelector().text(\"Toggle navigation bar\")";

    
    @AndroidFindBy(uiAutomator = BOT_IMG)
    private WebElement botImg;
    @AndroidFindBy(uiAutomator = TEXT_DESCRIPTION)
    private WebElement descriptionTxt;
    @AndroidFindBy(uiAutomator = BURGER_MENU)
    private WebElement burgerMenu;
    
    
    public WebViewScreen(AndroidDriver driver) {
        super(driver);
    }
    
    public boolean isBotPresent() {
        return isElementDisplayed(botImg);
    }
    
    public boolean isDescriptionEnable() {
        return isElementEnable(descriptionTxt);
    }
    
    public boolean isBurgerMenuDisplayedAndContainsText() {
        return isElementDisplayedAndContainText(burgerMenu, "Toggle navigation bar");
    }
    
    /**
     * Checks if the principal elements on the Web View screen are visible.
     * This method verifies the visibility of essential UI components on the Web View screen.
     *
     * @return boolean True if all specified elements are visible on the screen; otherwise, false.
     * @author Stephany Duran
     */
    public boolean arePrincipalElementsPresentInTheWebViewScreen() {
        Map<String, Supplier<Boolean>> elementsToCheck = new HashMap<>();
        elementsToCheck.put("Bot Image", this::isBotPresent);
        elementsToCheck.put("Description", this::isDescriptionEnable);
        elementsToCheck.put("Burger Menu", this::isBurgerMenuDisplayedAndContainsText);
        return super.arePrincipalElementsPresent(elementsToCheck);
    }
    
}
