package com.chait.seleniumdemo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Reuse the wait instance here
    }

    @Test
    public void testPropertySearchInRemuera() {

        int attempts = 0;
        // Click Property Link
        {
            driver.get("https://www.trademe.co.nz");

            // Click the Property link AFTER it's clickable
            WebElement propertyLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Property")));
            propertyLink.click();
            System.out.println("Clicked 'Property' link");
        }

        // Enter suburb name in search box and hit enter
        {
            // Wait for page load by waiting for the new search box to be present
            By searchBoxLocator = By.id("search");

            // Step 1: Wait for presence in DOM
            wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxLocator));
            System.out.println("Search box present in DOM");

            // Step 2: Wait for visibility
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxLocator));
            System.out.println("Search box visible");

            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBoxLocator));

            while (attempts < 3) {
                try {
                    // Step 3: Wait for clickability
                    searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBoxLocator));
                    System.out.println("Search box clickable");
                    // Now use it
                    searchBox.sendKeys("Remuera");
                    System.out.println("Sent keys to search box successfully");
                    break; // success
                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    if (attempts < 2) {
                        System.out.println("Caught StaleElementReferenceException while populating search box. trying again");
                    }
                }
                attempts++;
            }

            // Step 1: Wait for presence in DOM
            wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxLocator));
            System.out.println("Search box present in DOM");

            // Step 2: Wait for visibility
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxLocator));
            System.out.println("Search box visible");

            // Step 3: Wait for clickability
            wait.until(ExpectedConditions.elementToBeClickable(searchBoxLocator));
            System.out.println("Search box clickable");
            attempts = 0;
            while (attempts < 3) {
                try {
                    searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBoxLocator));
                    // Now hit enter/return
                    searchBox.sendKeys(Keys.RETURN);
                    System.out.println("Pressed enter");
                    break; //success
                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    if (attempts < 2) {
                        System.out.println("Caught StaleElementReferenceException while pressing enter in the search box. trying again");
                    }
                }
                attempts++;
            }
        }

        // Clicking on last page of the search results

        {


            By lastPageLocator;

            lastPageLocator = By.xpath("(//tg-pagination-link/a)[last()-1]");


            // Step 1: Wait for presence in DOM
            wait.until(ExpectedConditions.presenceOfElementLocated(lastPageLocator));
            System.out.println("Last page link present in DOM");

            // Step 2: Wait for visibility
            wait.until(ExpectedConditions.visibilityOfElementLocated(lastPageLocator));
            System.out.println("Last page link visible");


            // Step 3: Wait for clickability
            wait.until(ExpectedConditions.elementToBeClickable(lastPageLocator));

            WebElement lastPage = driver.findElement(lastPageLocator);
            // Step 4: Relocate the last page link and click it.
            attempts = 0;
            while (attempts < 3) {
                try {
                    lastPage = wait.until(ExpectedConditions.elementToBeClickable(lastPageLocator));
                    WebElement lastPageLink = driver.findElement(lastPageLocator);
                    lastPageLink.click();
                    break;
                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    if (attempts < 2) {
                        System.out.println("StaleElementReferenceException encountered while clicking last page link.. trying again");
                    }
                }
                attempts++;
            }
        }

        // Clicking on the first property on the last page

        {
            By firstSearchResultLocator = By.xpath("//tm-property-search-card-listing-title[1]");
            WebElement firstSearchResult = wait.until(ExpectedConditions.elementToBeClickable(firstSearchResultLocator));
            wait.until(ExpectedConditions.presenceOfElementLocated(firstSearchResultLocator));
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstSearchResultLocator));
            attempts = 0;
            while (attempts < 3) {
                try {
                    firstSearchResult = driver.findElement(firstSearchResultLocator);

                    firstSearchResult = wait.until(ExpectedConditions.elementToBeClickable(firstSearchResultLocator));
                    firstSearchResult = driver.findElement(firstSearchResultLocator);
                    firstSearchResult.click();
                    break;
                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    if (attempts < 2) {
                        System.out.println("StaleElementReferenceException encountered while clicking first search result.. trying again");
                    }
                }
                attempts++;
            }

        }



        // Clicking the Browse button and selecting Motors option

        {
            By browseButtonLocator = By.xpath("//button[contains(@class, 'global-browse-button') and contains(., 'Browse')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(browseButtonLocator));
            WebElement browseButton = wait.until(ExpectedConditions.elementToBeClickable(browseButtonLocator));
            browseButton.click();

            By motorsLinkLocator = By.linkText("Motors");
            wait.until(ExpectedConditions.visibilityOfElementLocated(motorsLinkLocator));
            WebElement motorsLink = wait.until(ExpectedConditions.elementToBeClickable(motorsLinkLocator));
            motorsLink.click();

        }

        // Selecting All Cars dropdown and Used option in it

        {
            By allCarsButtonLocator = By.xpath("//button[contains(@class, 'o-transparent-button2 o-dropdown__trigger') and contains(., 'All cars')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(allCarsButtonLocator));
            WebElement allCarsButton = wait.until(ExpectedConditions.elementToBeClickable(allCarsButtonLocator));
            allCarsButton.click();

            By usedOptionLocator = By.xpath("//tg-row[1]/tg-col[1]//tg-checkbox[2]/label/div/div/span");
            wait.until(ExpectedConditions.visibilityOfElementLocated(usedOptionLocator));
            WebElement usedOption = wait.until(ExpectedConditions.elementToBeClickable(usedOptionLocator));
            usedOption.click();

            List<WebElement> allOptions = driver.findElements(By.xpath("//tg-dropdown-content//tg-checkbox//span"));
            int countAllOptions = allOptions.size();
            System.out.println("Total options found: " + countAllOptions);
            Assert.assertEquals(countAllOptions, 5, "Total 5 options expected but not found");

            for (WebElement option : allOptions) {
                System.out.println(option.getText());
            }

            WebElement makeDropdown = driver.findElement(By.name("selectedMake"));
            Select select = new Select(makeDropdown);

            List <WebElement> makeDropdownList = select.getOptions();
            System.out.println("Total makes available in the dropdown - " + makeDropdownList.size());

            for (WebElement option : makeDropdownList) {
                System.out.println(option.getText());
            }

            select.selectByVisibleText("Volvo");
        }
    }

    ///html/body/tm-root/div[1]/main/div/tm-motors-home-page/tm-motors-home-page-header/div/div/tm-motors-search-form/form/tm-motors-used-cars-large/tg-row[2]/tg-col[3]/tg-dropdown/div[2]/div/tg-dropdown-content/tg-checkbox[1]/label/div/div/span

    @AfterMethod
    public void tearDown(){
//        if (driver != null)
//            driver.quit();  // Ensure driver quits after test
    }
}
