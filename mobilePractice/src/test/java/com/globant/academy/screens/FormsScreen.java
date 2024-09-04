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
    private static final String DROPDOWN = "Dropdown";
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
    
    /**
     * Checks if the Dropdown element is visible.
     *
     * @return boolean True if the element are displayed and focusable on the screen; otherwise, false.
     * @author Stephany Duran
     */
    public boolean isDropdownVisible() {
        return Boolean.parseBoolean(dropdown.getAttribute("clickable")) && dropdown.isDisplayed();
    }
    
    /**
     * Verifies if the Active Button element is visible.
     *
     * @return boolean True if the element are displayed and focusable on the screen; otherwise, false.
     * @author Stephany Duran
     */
    public boolean isActiveBtnEnable() {
        return isElementEnable(activeBtn);
    }
    
    /**
     * Verifies if the Inactive Button element is visible.
     *
     * @return boolean True if the element are displayed and focusable on the screen; otherwise, false.
     * @author Stephany Duran
     */
    public boolean isInactiveBtnDisplayed() {
        return isElementDisplayed(inactiveBtn);
    }
    
    /**
     * Checks if the principal elements on the Forms screen are visible.
     *
     * @return boolean True if all specified elements are visible on the screen; otherwise, false.
     * @author Stephany Duran
     */
    public boolean arePrincipalElementsPresentInTheFormsScreen() {
        Map<String, Supplier<Boolean>> elementsToCheck = new HashMap<>();
        elementsToCheck.put("Input field", this::isInputFieldPresent);
        elementsToCheck.put("Dropdown", this::isDropdownVisible);
        elementsToCheck.put("Active Button", this::isActiveBtnEnable);
        elementsToCheck.put("Inactive Button", this::isInactiveBtnDisplayed);
        return super.arePrincipalElementsPresent(elementsToCheck);
    }
    
}
