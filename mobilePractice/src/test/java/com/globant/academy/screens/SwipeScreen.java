package com.globant.academy.screens;

import com.globant.academy.utils.screen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SwipeScreen extends BaseScreen {
    
    private static final String CARD = "new UiSelector().description(\"card\").instance(0)";
    private static final String TITLE = ".textContains(\"Swipe horizontal\")";
    private static final String DESCRIPTION_CARD =
            "new UiSelector().description(\"slideTextContainer\")" + ".instance(0)";
    private static final String DRAG_MENU_OPTION = "//android.widget.TextView[@text=\"Drag\"]";
    @AndroidFindBy(uiAutomator = CARD)
    private WebElement card;
    @AndroidFindBy(uiAutomator = TITLE)
    private WebElement titleTxt;
    @AndroidFindBy(uiAutomator = DESCRIPTION_CARD)
    private WebElement descriptionTxt;
    @AndroidFindBy(xpath = DRAG_MENU_OPTION)
    private WebElement dragMenuOpt;
    
    public SwipeScreen(AndroidDriver driver) {
        super(driver);
    }
    
    public boolean isCardPresent() {
        return isElementEnable(card);
    }
    
    public boolean isTitleTxtEnable() {
        return isElementDisplayedAndContainText(titleTxt, "Swipe horizontal");
    }
    
    public boolean isDescriptionTxtDisplayed() {
        return isElementDisplayed(descriptionTxt);
    }
    
    public boolean arePrincipalElementsPresentInTheSwipeScreen() {
        Map<String, Supplier<Boolean>> elementsToCheck = new HashMap<>();
        elementsToCheck.put("Card", this::isCardPresent);
        elementsToCheck.put("Description", this::isDescriptionTxtDisplayed);
        elementsToCheck.put("Title", this::isTitleTxtEnable);
        return super.arePrincipalElementsPresent(elementsToCheck);
    }
    
    public DragScreen openDragScreen() {
        dragMenuOpt.click();
        return new DragScreen(driver);
    }
}
