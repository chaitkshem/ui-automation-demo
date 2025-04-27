package com.chait.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PropertySearchResultsPage {
    WebDriver driver;

    private By lastPageLink = By.xpath("(//tg-pagination-link/a)[last()-1]");
    private By firstListing = By.xpath("//tm-property-search-card-listing-title[1]");

    public PropertySearchResultsPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickLastPage(){
        driver.findElement(lastPageLink).click();
    }

    public void clickFirstListing(){
        driver.findElement(firstListing).click();
    }
}
