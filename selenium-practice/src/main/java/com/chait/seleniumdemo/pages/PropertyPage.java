package com.chait.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PropertyPage {
    WebDriver driver;

    private By searchBox = By.id("search");

    public PropertyPage(WebDriver driver){
        this.driver = driver;
    }

    public void searchSuburb(String suburbName){
        WebElement search = driver.findElement(searchBox);
        search.sendKeys(suburbName);
        search.sendKeys(Keys.RETURN);
    }
}
