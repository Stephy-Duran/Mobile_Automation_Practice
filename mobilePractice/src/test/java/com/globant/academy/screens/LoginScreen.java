package com.globant.academy.screens;

import com.fasterxml.jackson.databind.SerializationFeature;
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
    private static final String SIGN_UP_BTN = "button-SIGN UP";
    private static final String EMAIL_JSON_FILE_PATH = "src/test/java/com/globant/academy/data/registered_emails.json";
    private static final String SUCCESSFUL_MESSAGE = "android:id/alertTitle";
    private static final String OK_SUCCESSFUL_MSG_BTN = "android:id/button1";
    private static final String LOG_IN_SECTION = "new UiSelector().description(\"button-login-container\")";
    private static final String SUCCESSFUL_LOGIN_MSG = "android:id/message";
    private static final String LOG_IN_BTN = "button-LOGIN";
    
    
    private static final Logger log = LogManager.getLogger(LoginScreen.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @AndroidFindBy(accessibility = EMAIL_INPUT)
    private WebElement emailInput;
    @AndroidFindBy(accessibility = PASSWORD_INPUT)
    private WebElement passwordInput;
    @AndroidFindBy(accessibility = SIGN_UP_BTN)
    private WebElement signUpBtn;
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
    @AndroidFindBy(accessibility = LOG_IN_BTN)
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
        return isElementDisplayed(loginBtn);
    }
    
    
    /**
     * Checks if the principal elements on the Login screen are visible.
     * This method verifies the visibility of essential UI components on the Home screen.
     *
     * @return boolean True if all specified elements are visible on the screen; otherwise, false.
     * @author Stephany Duran
     */
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
    
    /**
     * Logs in with the credentials of the most recently registered user.
     * Retrieves the last user's email and password from the stored credentials
     * and uses them to fill in the login form fields before clicking the login button.
     *
     * @author Stephany Duran
     */
    public void logInWithExistingUser(){
        Map<String, String> lastCredentials = getLastUserCredentials();
        String email = lastCredentials.get("email");
        String password= lastCredentials.get("password");
        
        customSendKeys(emailInput, email);
        customSendKeys(passwordInput, password);
        loginBtn.click();
    }
    
    /**
     * Checks if the displayed message matches the expected message.
     *
     * @param message The expected message to verify.
     * @return boolean True if the current message matches; otherwise, false.
     * @author Stephany Duran
     */
    public boolean isSuccessfulLoginMsgShowed(String message){
        waitElementVisibility(successfulLoginMsg);
        return successfulLoginMsg.getText().equalsIgnoreCase(message); //You are logged in!
    }
    
    /**
     * Signs up a user with randomly generated credentials.
     * This method calls another method to generate random email and password credentials,
     * then fills in the sign-up form with these credentials and submits the form.
     *
     * @author Stephany Duran
     */
    public void signUpWithRandomCredentials() {
        Map<String, String> credentials = getRandomEmailAndPassword();
        String email = credentials.get("email");
        String password = credentials.get("password");
        
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPasswordTxt.sendKeys(password);
        signUpBtn.click();
    }
    
    /**
     * Generates a random email and password, updates the JSON file with these credentials, and returns them.
     *
     * @return A map containing the newly generated email and password.
     * @author Stephany Duran
     */
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
    
    /**
     * Reads credentials from a JSON file and stores them in a list of maps.
     *
     * @return List of maps containing user credentials as key-value pairs. If there is an error, returns an empty list.
     * @author Stephany Duran
     */
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
    
    /**
     * Writes a list of user credentials to a JSON file.
     *
     * @param credentialsList List of maps containing user credentials to be written to the JSON file.
     * @author Stephany Duran
     */
    private void writeCredentialsToJson(List<Map<String, String>> credentialsList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(EMAIL_JSON_FILE_PATH), credentialsList);
        } catch (IOException e) {
            log.error("Error writing credentials to JSON file: {}", e.getMessage());
        }
    }
    
    /**
     * Retrieves the last set of user credentials from the JSON file.
     *
     * @return A map containing the last set of user credentials, or an empty map if an error occurs or no credentials are found.
     * @author Stephany Duran
     */
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
    /**
     * Generates a random string of the specified length.
     * The string consists of alphanumeric characters (both uppercase and lowercase).
     *
     * @param length The length of the random string to generate.
     * @return A random string of the specified length.
     * @author Stephany Duran
     */
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
