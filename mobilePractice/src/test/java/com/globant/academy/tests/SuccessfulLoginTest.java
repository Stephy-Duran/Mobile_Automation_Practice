package com.globant.academy.tests;

import com.globant.academy.screens.HomeScreen;
import com.globant.academy.screens.LoginScreen;
import com.globant.academy.utils.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SuccessfulLoginTest extends BaseTest {
    
    @Test
    public void verifySuccessfulLogin(){
        HomeScreen home = openHomeScreen();
        LoginScreen loginScreen = home.clickOnLoginOption();
        
        loginScreen.clickOnSingUpOption();
        loginScreen.signUpWithRandomCredentials();
        Assert.assertTrue(loginScreen.isSuccessfulSignUpMessageDisplayed("Signed Up!"));
        loginScreen.clickOnOkSuccessfulMessageBtn();
        
        loginScreen.clickOnLoginSection();
        loginScreen.logInWithExistingUser();
        Assert.assertTrue(loginScreen.isSuccessfulLoginMsgShowed("You are logged in!"));
    }
    

}
