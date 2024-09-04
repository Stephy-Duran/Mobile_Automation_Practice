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
    private static final String PARTS_BOARD = ".description(\"Drag-drop-screen\").childSelector(.className(\"android.widget.ImageView\"));";
    @AndroidFindBy(uiAutomator = TITLE_TXT)
    private WebElement titleTxt;
    @AndroidFindBy(uiAutomator = PARTS_BOARD)
    private WebElement board;
    
    public DragScreen(AndroidDriver driver) {
        super(driver);
    }
    
    
    /**
     * Verifies if the title element is displayed on the screen.
     *
     * @return boolean True if the title element is displayed; otherwise, false.
     * @author Stephany Duran
     */
    public boolean isTitlePresent() {
        return isElementDisplayed(titleTxt);
    }
    
    /**
     * Checks if the piece board element is displayed on the screen.
     *
     * @return boolean True if the board element is displayed; otherwise, false.
     * @author Stephany Duran
     */
    public boolean isBoardPresent() {
        return isElementDisplayed(board);
    }
    
    /**
     * Checks if the dropdown element is visible and focusable on the screen.
     *
     * @return boolean True if the dropdown element is both displayed and focusable; otherwise, false.
     * @author Stephany Duran
     */
    public boolean arePrincipalElementsPresentInTheDragScreen() {
        Map<String, Supplier<Boolean>> elementsToCheck = new HashMap<>();
        elementsToCheck.put("Title", this::isTitlePresent);
        elementsToCheck.put("Board", this::isBoardPresent);
        return arePrincipalElementsPresent(elementsToCheck);
    }
}
