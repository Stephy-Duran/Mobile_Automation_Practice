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
                          "Check the log report to find out which element is missing on the home screen.\"");
        WebViewScreen webView = homeScreen.clickOnWebViewOption();
        Assert.assertTrue(webView.arePrincipalElementsPresentInTheWebViewScreen(),
                          "Check the log report to find out which element is missing on the Web view screen.");
        LoginScreen loginScreen = webView.clickOnLoginOption();
        Assert.assertTrue(loginScreen.arePrincipalElementsPresentInTheLoginScreen(),
                          "Check the log report to find out which element is missing on the Login screen.");
        FormsScreen formsScreen = loginScreen.clickOnFormsOption();
        Assert.assertTrue(formsScreen.arePrincipalElementsPresentInTheFormsScreen(),
                          "Check the log report to find out which element is missing on the Forms Screen.");
        SwipeScreen swipeScreen = formsScreen.clickOnSwipeOption();
        Assert.assertTrue(swipeScreen.arePrincipalElementsPresentInTheSwipeScreen(),
                          "Check the log report to find out which element is missing on the Swipe Screen.");
        DragScreen dragScreen = swipeScreen.clickOnDragOption();
        Assert.assertTrue(dragScreen.arePrincipalElementsPresentInTheDragScreen(),
                          "Check the log report to find out which element is missing on the Drag Screen.");
    }
}
