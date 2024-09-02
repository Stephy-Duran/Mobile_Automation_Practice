package com.globant.academy.utils.screen;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BaseScreen {
    
    protected AndroidDriver driver;
    
    public BaseScreen(AndroidDriver driver){
        this.driver = driver;
        PageFactory.initElements( new AppiumFieldDecorator(driver), this);
    }
    
    

}
