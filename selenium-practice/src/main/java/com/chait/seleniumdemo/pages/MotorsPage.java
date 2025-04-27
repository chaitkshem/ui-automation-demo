package com.chait.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MotorsPage {

    WebDriver driver;
    private By browseButton = By.xpath("//button[contains(@class, 'global-browse-button') and contains(., 'Browse')]");
    private By motorsLink = By.linkText("Motors");
    private By allCarsButton = By.xpath("//button[contains(., 'All cars')]");
    private By usedOption = By.xpath("//tg-row[1]/tg-col[1]//tg-checkbox[2]/label/div/div/span");
    private By makeDropdown = By.name("selectedMake");
}
