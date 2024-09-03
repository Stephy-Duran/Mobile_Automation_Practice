package com.globant.academy.tests;

import com.globant.academy.screens.HomeScreen;
import com.globant.academy.screens.LoginScreen;
import com.globant.academy.utils.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SuccessfulSignUpTest extends BaseTest {
    
    @Test
    @Parameters({"successfulSignUpMsg"})
    public void VerifySignUp(String successfulSignUpMsg){
        HomeScreen home = openHomeScreen();
        LoginScreen loginScreen = home.clickOnLoginOption();
        Assert.assertTrue(loginScreen.arePrincipalElementsPresentInTheLoginScreen());
        
        loginScreen.clickOnSingUpOption();
        loginScreen.signUpWithRandomCredentials();
        
        Assert.assertTrue(loginScreen.isSuccessfulSignUpMessageDisplayed(successfulSignUpMsg));
        
        
    }
    
    
}
