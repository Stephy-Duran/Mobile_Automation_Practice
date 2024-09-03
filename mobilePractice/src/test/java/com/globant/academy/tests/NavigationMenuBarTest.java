package com.globant.academy.tests;

import com.globant.academy.screens.*;
import com.globant.academy.utils.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationMenuBarTest extends BaseTest {
    
    @Test
    public void NavigationOnTheBottomMenuBar() {
        HomeScreen homeScreen = openHomeScreen();
        Assert.assertTrue(homeScreen.arePrincipalElementsPresentInTheHomeScreen(),
                          "Check the log report to find out which element is missing.\"");
        
        WebViewScreen webView = homeScreen.clickOnWebViewOption();
        Assert.assertTrue(webView.arePrincipalElementsPresentInTheWebViewScreen());
        
        LoginScreen loginScreen = webView.clickOnLoginOption();
        Assert.assertTrue(loginScreen.arePrincipalElementsPresentInTheLoginScreen());
        
        FormsScreen formsScreen = loginScreen.clickOnFormsOption();
        Assert.assertTrue(formsScreen.arePrincipalElementsPresentInTheFormsScreen());
        
        SwipeScreen swipeScreen = formsScreen.clickOnSwipeOption();
        Assert.assertTrue(swipeScreen.arePrincipalElementsPresentInTheSwipeScreen());
        
        DragScreen dragScreen = swipeScreen.clickOnDragOption();
        Assert.assertTrue(dragScreen.arePrincipalElementsPresentInTheDragScreen());
        
        
        
        
        
        
        
    }
}
