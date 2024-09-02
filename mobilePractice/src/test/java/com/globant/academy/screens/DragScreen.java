package com.globant.academy.screens;

import com.globant.academy.utils.screen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DragScreen extends BaseScreen {
    
    private static final String TITLE_TXT = ".textContains(\"Drop\")";
    private static final String PARTS_BOARD =
            "new UiSelector().className(\"android.widget.ImageView\").instance" + "(0)";
    @AndroidFindBy(uiAutomator = TITLE_TXT)
    private WebElement titleTxt;
    @AndroidFindBy(uiAutomator = PARTS_BOARD)
    private WebElement board;
    
    public DragScreen(AndroidDriver driver) {
        super(driver);
    }
    
    public boolean isTitlePresent() {
        return isElementDisplayed(titleTxt);
    }
    
    public boolean isBoardPresent() {
        return isElementDisplayed(board);
    }
    
    public boolean arePrincipalElementsPresentInTheDragScreen() {
        Map<String, Supplier<Boolean>> elementsToCheck = new HashMap<>();
        elementsToCheck.put("Title", this::isTitlePresent);
        elementsToCheck.put("Board", this::isBoardPresent);
        return arePrincipalElementsPresent(elementsToCheck);
    }
}
