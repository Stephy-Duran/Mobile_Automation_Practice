package com.globant.academy.tests;

import com.globant.academy.screens.HomeScreen;
import com.globant.academy.screens.LoginScreen;
import com.globant.academy.utils.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SuccessfulLoginTest extends BaseTest {
    
    @Test
    @Parameters({"successfulLoginMsg", "successfulSignUpMsg"})
    public void verifySuccessfulLogin(String successfulLoginMsg, String successfulSingUpMsg ){
        HomeScreen home = openHomeScreen();
        LoginScreen loginScreen = home.clickOnLoginOption();
        
        loginScreen.clickOnSingUpOption();
        loginScreen.signUpWithRandomCredentials();
        Assert.assertTrue(loginScreen.isSuccessfulSignUpMessageDisplayed(successfulSingUpMsg));
        loginScreen.clickOnOkSuccessfulMessageBtn();
        
        loginScreen.clickOnLoginSection();
        loginScreen.logInWithExistingUser();
        Assert.assertTrue(loginScreen.isSuccessfulLoginMsgShowed(successfulLoginMsg));
    }
    

}
