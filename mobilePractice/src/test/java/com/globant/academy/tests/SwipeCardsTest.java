package com.globant.academy.tests;

import com.globant.academy.screens.HomeScreen;
import com.globant.academy.screens.SwipeScreen;
import com.globant.academy.utils.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SwipeCardsTest extends BaseTest {
    
    @Test
    @Parameters({"foundMeMsg"})
    public void testSwipeCards(String foundMeMsg) {
        HomeScreen homeScreen = openHomeScreen();
        SwipeScreen swipeScreen = homeScreen.clickOnSwipeOption();
        swipeScreen.swipeRightToLeft();
        Assert.assertTrue(swipeScreen.isOldCardIsHidden());
        Assert.assertTrue(swipeScreen.isTheLastCardTheOnlyOneVisible());
        Assert.assertTrue(swipeScreen.isYouFindMeMsgPresent(foundMeMsg));
    }
    
}
