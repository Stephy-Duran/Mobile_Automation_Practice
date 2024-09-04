package com.globant.academy.screens;

import com.globant.academy.utils.screen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SwipeScreen extends BaseScreen {
    
    private static final String CARD = "new UiSelector().resourceId(\"__CAROUSEL_ITEM_0_READY__\")";
    private static final String TITLE = ".textContains(\"Swipe horizontal\")";
    private static final String DESCRIPTION_CARD = "new UiSelector().textContains(\"WebdriverIO is fully\")";
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
    private WebElement youFindMeTxt;
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
    
    /**
     * Checks if the principal elements on the Swipe screen are visible.
     * This method verifies the visibility of essential UI components on the Swipe screen.
     *
     * @return boolean True if all specified elements are visible on the screen; otherwise, false.
     * @author Stephany Duran
     */
    public boolean arePrincipalElementsPresentInTheSwipeScreen() {
        Map<String, Supplier<Boolean>> elementsToCheck = new HashMap<>();
        elementsToCheck.put("Card", this::isCardPresent);
        elementsToCheck.put("Description", this::isDescriptionTxtDisplayed);
        elementsToCheck.put("Title", this::isTitleTxtEnable);
        return super.arePrincipalElementsPresent(elementsToCheck);
    }
    /**
     * Performs a swipe gesture from right to left by calculating the coordinates based on the
     * screen size.
     *
     * @author Stephany Duran
     */
    public void swipeRightToLeft() {
        Dimension screenSize = driver.manage().window().getSize();
        int screenWidth = screenSize.getWidth();
        int screenHeight = screenSize.getHeight();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        int coordinateY = (int) (screenHeight * 0.68);
        int startX = (int) (screenWidth * 0.67);
        int endX = (int) (screenWidth * 0.1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, coordinateY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(
                finger.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), endX, coordinateY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }
    
    /**
     * Checks if the previous card element is hidden
     *
     * @return boolean True if old card element is hidden; otherwise, false.
     * @author Stephany Duran
     */
    public boolean isOldCardIsHidden() {
        return !isElementOnScreen(cardIcon, 3);
    }
    
    /**
     * Swipes through cards from right to left until the last card is displayed.
     * If an error occurs during the swipe actions, it is logged to the error output.
     *
     * @author Stephany Duran
     */
    public void swipeThroughCardsUntilLast() {
        try {
            while(cardsDisplayedList.size() > 1) {
                swipeRightToLeft();
            }
        }
        catch(Exception e) {
            System.err.println("Error when swiping to the last card " + e.getMessage());
        }
    }
    
    
    public boolean isTheLastCardTheOnlyOneVisible() {
        return cardsDisplayedList.size() == 1;
    }
    
    /**
     * performs a swipe gesture from bottom to top calculating the coordinates
     * according to the screen size.
     *
     * @author Stephany Duran
     */
    public void swipeUp() {
        Dimension screenSize = driver.manage().window().getSize();
        int screenWidth = screenSize.getWidth();
        int screenHeight = screenSize.getHeight();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        int coordinateX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.88);
        int endY = (int) (screenHeight * 0.5);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), coordinateX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(
                finger.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), coordinateX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }
    
    /**
     * Swipes up until the element with text "You Find Me" is visible or until the maximum number
     * of attempts is reached.
     *
     * @author Stephany Duran
     */
    public void swipeUpUntilYouFindMeText() {
        int attempts = 3;
        while(!isElementOnScreen(youFindMeTxt, 2) && attempts > 0) {
            swipeUp();
            attempts--;
        }
    }
    
    /**
     * Verifies if the text of the 'You find me' message element matches the expected message.
     *
     * @param message The expected message to compare against.
     * @return boolean True if the text of the `youFindMeTxt` element matches the expected message; otherwise, false.
     * @author Stephany Duran
     */
    public boolean isYouFindMeMsgPresent(String message) {
        return youFindMeTxt.getText().equalsIgnoreCase(message);
    }
}
