package com.globant.academy.screens;

import com.globant.academy.utils.screen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FormsScreen extends BaseScreen {
    
    private static final String INPUT_FIELD = "text-input";
    private static final String DROPDOWN = ".resourceId(\"text_input\")"; //CAMBAIR POR ID
    private static final String ACTIVE_BTN = "button-Active";
    private static final String INACTIVE_BTN = "button-Inactive";
    private static final String SWIPE_OPTION_BTN = ".text(\"Swipe\")";
    @AndroidFindBy(accessibility = INPUT_FIELD)
    private WebElement inputFieldTxt;
    @AndroidFindBy(accessibility = DROPDOWN)
    private WebElement dropdown;
    @AndroidFindBy(accessibility = ACTIVE_BTN)
    private WebElement activeBtn;
    @AndroidFindBy(accessibility = INACTIVE_BTN)
    private WebElement inactiveBtn;
    @AndroidFindBy(uiAutomator = SWIPE_OPTION_BTN)
    private WebElement swipeMenuOption;
    
    public FormsScreen(AndroidDriver driver) {
        super(driver);
    }
    
    public boolean isInputFieldPresent() {
        return isElementClickable(inputFieldTxt);
    }
    
    public boolean isDropdownEnable() {
        return Boolean.parseBoolean(inputFieldTxt.getAttribute("focusable")) && inputFieldTxt.isDisplayed();
    }
    
    public boolean isActiveBtnEnable() {
        return isElementEnable(activeBtn);
    }
    
    public boolean isInactiveBtnDisplayed() {
        return isElementDisplayed(inactiveBtn);
    }
    
    public boolean arePrincipalElementsPresentInTheFormsScreen() {
        Map<String, Supplier<Boolean>> elementsToCheck = new HashMap<>();
        elementsToCheck.put("Input field", this::isInputFieldPresent);
        elementsToCheck.put("Dropdown", this::isDropdownEnable);
        elementsToCheck.put("Active Button", this::isActiveBtnEnable);
        elementsToCheck.put("Inactive Button", this::isInactiveBtnDisplayed);
        return super.arePrincipalElementsPresent(elementsToCheck);
    }
    
}
