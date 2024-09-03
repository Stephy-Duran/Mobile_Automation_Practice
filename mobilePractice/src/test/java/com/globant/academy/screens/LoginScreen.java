package com.globant.academy.screens;

import com.globant.academy.utils.screen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginScreen extends BaseScreen {
    
    private static final String SIGN_UP_SECTION = ".text(\"Sign up\")";
    private static final String EMAIL_INPUT = "input-email";
    private static final String PASSWORD_INPUT = "input-password";
    private static final String CONFIRM_PASSWORD_INPUT = "new UiSelector().description(\"input-repeat-password\");";
    private static final String SIGN_UP_BTN = "new UiSelector().className(\"android.view.ViewGroup\").instance(16)";
    private static final String FORMS_MENU_OPTION = ".text(\"Forms\")";
    private static final String EMAIL_JSON_FILE_PATH = "src/test/java/com/globant/academy/data/registered_emails.json";
    private static final String SUCCESSFUL_MESSAGE = "android:id/alertTitle";
    private static final String OK_SUCCESSFUL_MSG_BTN = "android:id/button1";
    private static final String LOG_IN_SECTION = "new UiSelector().text(\"Login\").instance(0)";
    private static final String SUCCESSFUL_LOGIN_MSG = "android:id/message";
    private static final String LOG_IN_BTN = "new UiSelector().text(\"LOGIN\")";
    
    
    private static final Logger log = LogManager.getLogger(LoginScreen.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @AndroidFindBy(accessibility = EMAIL_INPUT)
    private WebElement emailInput;
    @AndroidFindBy(accessibility = PASSWORD_INPUT)
    private WebElement passwordInput;
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
    @AndroidFindBy(id = OK_SUCCESSFUL_MSG_BTN)
    private WebElement okSuccessfulMessageBtn;
    @AndroidFindBy(uiAutomator = LOG_IN_SECTION)
    private WebElement loginSectionBtn;
    @AndroidFindBy(id = SUCCESSFUL_LOGIN_MSG)
    private WebElement successfulLoginMsg;
    @AndroidFindBy(uiAutomator = LOG_IN_BTN)
    private WebElement loginBtn;
    
    
    
    public LoginScreen(AndroidDriver driver) {
        super(driver);
    }
    
    public boolean isEmailTxtClickable() {
        return isElementClickable(emailInput);
    }
    
    public boolean isPasswordTxtPresent() {
        return isElementDisplayed(passwordInput);
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
    
    public void clickOnOkSuccessfulMessageBtn() {
        customClickOnElement(okSuccessfulMessageBtn);
    }
    
    public void clickOnLoginSection() {
        customClickOnElement(loginSectionBtn);
    }
    
    public void logInWithExistingUser(){
        Map<String, String> lastCredentials = getLastUserCredentials();
        String email = lastCredentials.get("email");
        String password= lastCredentials.get("password");
        
        customSendKeys(emailInput, email);
        customSendKeys(passwordInput, password);
        loginBtn.click();
    }
    
    public boolean isSuccessfulLoginMsgShowed(String message){
        waitElementVisibility(successfulLoginMsg);
        return successfulLoginMsg.getText().equalsIgnoreCase(message); //You are logged in!
    }
    
    
    public void signUpWithRandomCredentials() {
        Map<String, String> credentials = getRandomEmailAndPassword();
        String email = credentials.get("email");
        String password = credentials.get("password");
        
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPasswordTxt.sendKeys(password);
        signUpBtn.click();
    }
    
    public Map<String, String> getRandomEmailAndPassword() {
        String email = "testuser_" + generateRandomString(4) + "@gmail.com";
        String password = generateRandomString(8);
        
        List<Map<String, String>> credentialsList = readCredentialsFromJson();
        Map<String, String> newCredentials = new HashMap<>();
        newCredentials.put("email", email);
        newCredentials.put("password", password);
        credentialsList.add(newCredentials);
        writeCredentialsToJson(credentialsList);
        
        return newCredentials;
    }
    
    private List<Map<String, String>> readCredentialsFromJson() {
        try {
            File file = new File(EMAIL_JSON_FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Map<String, String>>>() {});
        } catch (IOException e) {
            log.error("Error reading credentials from JSON file: {}", e.getMessage());
            return new ArrayList<>();
        }
    }
    
    private void writeCredentialsToJson(List<Map<String, String>> credentialsList) {
        try {
            objectMapper.writeValue(new File(EMAIL_JSON_FILE_PATH), credentialsList);
        } catch (IOException e) {
            log.error("Error writing credentials to JSON file: {}", e.getMessage());
        }
    }
    
    private Map<String, String> getLastUserCredentials() {
        try {
            File file = new File(EMAIL_JSON_FILE_PATH);
            if (!file.exists()) {
                return new HashMap<>();
            }
            
            List<Map<String, String>> credentialsList = objectMapper.readValue(file, new TypeReference<List<Map<String, String>>>() {});
            
            return credentialsList.get(credentialsList.size() - 1);
        } catch (IOException e) {
            log.error("[Error] reading credentials from JSON file: {}", e.getMessage());
            return new HashMap<>();
        }
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
