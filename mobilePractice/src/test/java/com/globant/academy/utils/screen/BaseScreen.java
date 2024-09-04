package com.globant.academy.utils.screen;

import com.globant.academy.screens.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class BaseScreen {
    
    protected AndroidDriver driver;
    protected WebDriverWait wait;
    private static final Logger log = LoggerFactory.getLogger(BaseScreen.class);
    private static final String HOME_BTN = "new UiSelector().text(\"Home\")";
    private static final String WEB_VIEW_BTN = ".text(\"Webview\")";
    private static final String LOG_IN_BTN = ".text(\"Login\")";
    private static final String FORMS_BTN = "new UiSelector().text(\"Forms\")";
    private static final String SWIPE_BTN = "new UiSelector().text(\"Swipe\")";
    private static final String DRAG_BTN = "//android.widget.TextView[@text=\"Drag\"]";
    @AndroidFindBy(uiAutomator = HOME_BTN)
    private WebElement homeBtn;
    @AndroidFindBy(uiAutomator = WEB_VIEW_BTN)
    private WebElement webViewBtn;
    @AndroidFindBy(uiAutomator = LOG_IN_BTN)
    private WebElement loginBtn;
    @AndroidFindBy(uiAutomator = FORMS_BTN)
    private WebElement formsBtn;
    @AndroidFindBy(uiAutomator = SWIPE_BTN)
    private WebElement swipeBtn;
    @AndroidFindBy(xpath = DRAG_BTN)
    private WebElement dragBtn;
    
    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(18));
    }
    
    public void waitElementVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * Checks if all specified elements are present on the screen.
     * The use of Supplier<Boolean> allows for verifying the state of each element at the time
     * of invocation, ensuring up-to-date checks.
     *
     * @param elementsToCheck A map where the key is the element name and the value is a Supplier
     *                        that returns a boolean indicating if the element is present.
     * @return boolean True if all elements are present; otherwise, false.
     * @author Stephany Duran
     */
    public boolean arePrincipalElementsPresent(Map<String, Supplier<Boolean>> elementsToCheck) {
        for(Map.Entry<String, Supplier<Boolean>> entry : elementsToCheck.entrySet()) {
            String elementName = entry.getKey();
            boolean isElementPresent = entry.getValue().get();
            if(!isElementPresent) {
                log.error("The element '{}' is not present on the screen", elementName);
                return false;
            }
        }
        return true;
    }
    
    public boolean isElementEnable(WebElement element) {
        waitElementVisibility(element);
        return element.isEnabled();
    }
    
    public boolean isElementDisplayed(WebElement element) {
        waitElementVisibility(element);
        return element.isEnabled();
    }
    
    public boolean isElementDisplayedAndContainText(WebElement element, String text) {
        waitElementVisibility(element);
        return element.getText().equalsIgnoreCase(text) && element.isDisplayed();
    }
    
    public boolean isElementClickable(WebElement element) {
        waitElementVisibility(element);
        return Boolean.parseBoolean(element.getAttribute("clickable"));
    }
    
    public void customClickOnElement(WebElement element) {
        waitElementVisibility(element);
        element.click();
    }
    
    public WebDriverWait setUpWait(long time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time));
    }
    
    public void customSendKeys(WebElement element, String key) {
        waitElementVisibility(element);
        element.clear();
        element.sendKeys(key);
    }
    
    public HomeScreen clickOnHomeOption() {
        customClickOnElement(homeBtn);
        return new HomeScreen(driver);
    }
    
    public WebViewScreen clickOnWebViewOption() {
        customClickOnElement(webViewBtn);
        return new WebViewScreen(driver);
    }
    
    public LoginScreen clickOnLoginOption() {
        customClickOnElement(loginBtn);
        return new LoginScreen(driver);
    }
    
    public FormsScreen clickOnFormsOption() {
        customClickOnElement(formsBtn);
        return new FormsScreen(driver);
    }
    
    public SwipeScreen clickOnSwipeOption() {
        customClickOnElement(swipeBtn);
        return new SwipeScreen(driver);
    }
    
    public DragScreen clickOnDragOption() {
        customClickOnElement(dragBtn);
        return new DragScreen(driver);
    }
    
    /**
     * Checks if a web element is visible on the screen within a specified time.
     *
     * @param element The WebElement to check.
     * @param seconds The maximum time in seconds to wait for the element to become visible.
     * @return boolean True if the element is visible on the screen; otherwise, false.
     * @author Stephany Duran
     */
    public boolean isElementOnScreen(WebElement element, long seconds) {
        try {
            setUpWait(seconds).until(ExpectedConditions.visibilityOf(element));
            return true;
        }
        catch(Exception e) {
            log.info("The element is not present on the screen");
            return false;
        }
    }
}
