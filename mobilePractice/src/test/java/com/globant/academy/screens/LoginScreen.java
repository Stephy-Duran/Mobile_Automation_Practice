package com.globant.academy.screens;

import com.globant.academy.utils.screen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class LoginScreen extends BaseScreen {
    
    private static final String SIGN_UP_SECTION = ".text(\"Sign up\")";
    private static final String EMAIL_INPUT = "input-email";
    private static final String PASSWORD_INPUT = "input-password";
    private static final String CONFIRM_PASSWORD_INPUT = "new UiSelector().description(\"input-repeat-password\");";
    private static final String SIGN_UP_BTN = "new UiSelector().className(\"android.view.ViewGroup\").instance(16)";
    private static final String FORMS_MENU_OPTION = ".text(\"Forms\")";
    private static final String EMAIL_FILE_PATH = "src/test/java/com/globant/academy/data/registered_emails.txt";
    private static final String SUCCESSFUL_MESSAGE = "android:id/alertTitle";
    
    private static final Logger log = LogManager.getLogger(LoginScreen.class);
    @AndroidFindBy(accessibility = EMAIL_INPUT)
    private WebElement emailTxt;
    @AndroidFindBy(accessibility = PASSWORD_INPUT)
    private WebElement passwordTxt;
    @AndroidFindBy(uiAutomator = SIGN_UP_BTN)
    private WebElement signUpBtn;
    @AndroidFindBy(uiAutomator = FORMS_MENU_OPTION)
    private WebElement formsMenuOpt;
    @AndroidFindBy(uiAutomator = CONFIRM_PASSWORD_INPUT)
    private WebElement confirmPasswordTxt;
    @AndroidFindBy(id = SUCCESSFUL_MESSAGE)
    private WebElement successfulMessage;
    @AndroidFindBy(uiAutomator = SIGN_UP_SECTION)
    private WebElement signupSection;
    
    
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
        return isElementDisplayed(signUpBtn);
    }
    
    public boolean arePrincipalElementsPresentInTheLoginScreen() {
        Map<String, Supplier<Boolean>> elementsToCheck = new HashMap<>();
        elementsToCheck.put("Email", this::isEmailTxtClickable);
        elementsToCheck.put("password", this::isPasswordTxtPresent);
        elementsToCheck.put("Login Button", this::isLoginBtnDisplayed);
        return arePrincipalElementsPresent(elementsToCheck);
    }
    
    
    public void clickOnSingUpOption() {
       customClickOnElement(signupSection);
    }
    
    public void signUpWithRandomCredentials(String password) {
        emailTxt.sendKeys(getRandomEmailAddress());
        passwordTxt.sendKeys(password);
        confirmPasswordTxt.sendKeys(password);
        signUpBtn.click();
    }
    
    public String getRandomEmailAddress() {
        String email = "testuser_" + generateRandomString(4) + "@gmail.com";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMAIL_FILE_PATH, true))) {
            writer.write(email + "\n");
        } catch (IOException e) {
            log.error("Error writing email to file: {}", e.getMessage());
        }
        return email;
    }
    
    public boolean isSuccessfulSignUpMessageDisplayed(String message){
        return isElementDisplayed(successfulMessage) && successfulMessage.getText().equalsIgnoreCase(message);
    }
    
    private String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        
        return sb.toString();
    }
    
}
