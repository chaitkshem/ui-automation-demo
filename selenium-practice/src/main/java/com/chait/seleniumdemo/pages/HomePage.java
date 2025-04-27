package com.chait.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    private By propertyLink = By.linkText("Property");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickPropertyLink(){
        driver.findElement(propertyLink).click();
    }
}
