package com.globant.academy.tests;

import com.globant.academy.screens.HomeScreen;
import com.globant.academy.screens.LoginScreen;
import com.globant.academy.utils.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SuccessfulSignUpTest extends BaseTest {
    
    @Test
    public void VerifySignUp(){
        HomeScreen home = openHomeScreen();
        LoginScreen loginScreen = home.clickOnLoginOption();
        Assert.assertTrue(loginScreen.arePrincipalElementsPresentInTheLoginScreen());
        
        loginScreen.clickOnSingUpOption();
        loginScreen.signUpWithRandomCredentials();
        
        Assert.assertTrue(loginScreen.isSuccessfulSignUpMessageDisplayed("Signed Up!"));
        
        
    }
    
    
}
