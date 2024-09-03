package com.globant.academy.screens;

import com.globant.academy.utils.screen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SwipeScreen extends BaseScreen {
    
    private static final String CARD = "new UiSelector().resourceId(\"__CAROUSEL_ITEM_0_READY__\")";
    private static final String TITLE = ".textContains(\"Swipe horizontal\")";
    private static final String DESCRIPTION_CARD =
            "new UiSelector().textContains(\"WebdriverIO is fully\")";
    private static final String YOU_FIND_ME_MGS = "new UiSelector().text(\"You found me!!!\")";
    private static final String FIRST_CARD_ICON = "new UiSelector().text(\"\uDB80\uDEA4\")";
    private static final String LIST_CARDS_DISPLAYED = "new UiSelector().resourceIdMatches(\".*CAROUSEL.*\")";
    
    @AndroidFindBy(uiAutomator = CARD)
    private WebElement firstCard;
    @AndroidFindBy(uiAutomator = TITLE)
    private WebElement titleTxt;
    @AndroidFindBy(uiAutomator = DESCRIPTION_CARD)
    private WebElement descriptionTxt;
    @AndroidFindBy(uiAutomator = YOU_FIND_ME_MGS)
    private WebElement youFindMeMsg;
    @AndroidFindBy(uiAutomator = FIRST_CARD_ICON)
    private WebElement cardIcon;
    @AndroidFindBy(uiAutomator = LIST_CARDS_DISPLAYED)
    private List<WebElement> cardsDisplayedList;
    
    public SwipeScreen(AndroidDriver driver) {
        super(driver);
    }
    
    public boolean isCardPresent() {
        return isElementEnable(firstCard);
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
    
    public void swipeRightToLeft() {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        int startX = 1243;
        int startY = 2008;
        int endX = 205;
        int endY = 2008;
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(
                finger.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }
    
    public boolean isOldCardIsHidden() {
        return !isElementOnScreen(cardIcon, 3);
    }
    
    public boolean isTheLastCardTheOnlyOneVisible() {
        try {
            while(cardsDisplayedList.size() > 1) {
                swipeRightToLeft();
            }
            return cardsDisplayedList.size() == 1;
        }
        catch(Exception e) {
            System.err.println("Error al verificar la última tarjeta visible: " + e.getMessage());
        }
        return false;
    }
    
    public void swipeUp() {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        int startX = 634;
        int startY = 2704;
        int endX = 634;
        int endY = 503;
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(
                finger.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }
    
    public boolean isYouFindMeMsgPresent(String message) {
        int attempts = 3;
        while(!isElementOnScreen(youFindMeMsg, 2) && attempts > 0) {
            swipeUp();
            attempts--;
        }
        return youFindMeMsg.getText().equalsIgnoreCase(message);
    }
}
