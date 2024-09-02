package com.globant.academy.screens;

import com.globant.academy.utils.screen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class LoginScreen extends BaseScreen {
    
    private static final String EMAIL_INPUT = "input-email";
    private static final String PASSWORD_INPUT = "input-password";
    private static final String LOGIN_BTN = "new UiSelector().className(\"android.view.ViewGroup\").instance(16)";
    private static final String FORMS_MENU_OPTION = ".text(\"Forms\")";
    
    @AndroidFindBy(accessibility = EMAIL_INPUT)
    private WebElement emailTxt;
    @AndroidFindBy(accessibility = PASSWORD_INPUT)
    private WebElement passwordTxt;
    @AndroidFindBy(uiAutomator = LOGIN_BTN)
    private WebElement loginBtn;
    @AndroidFindBy(uiAutomator = FORMS_MENU_OPTION)
    private WebElement formsMenuOpt;
    
    public LoginScreen(AndroidDriver driver) {
        super(driver);
    }
    
    public boolean isEmailTxtClickable() {
        return isElementClickable(emailTxt);
    }
    
    public boolean isPasswordTxtPresent() {
        return isElementDisplayed(passwordTxt);
    }
    
    public boolean isLoginBtnDisplayed() {
        return isElementDisplayed(loginBtn);
    }
    
    public boolean arePrincipalElementsPresentInTheLoginScreen() {
        Map<String, Supplier<Boolean>> elementsToCheck = new HashMap<>();
        elementsToCheck.put("Email", this::isEmailTxtClickable);
        elementsToCheck.put("password", this::isPasswordTxtPresent);
        elementsToCheck.put("Login Button", this::isLoginBtnDisplayed);
        return arePrincipalElementsPresent(elementsToCheck);
    }
    
    public FormsScreen clickOnFormsOption() {
        formsMenuOpt.click();
        return new FormsScreen(driver);
    }
    
    public void clickOnSingUpOption() {
    
    }
    
    public void signUp(String email, String password){
    
    }
}
