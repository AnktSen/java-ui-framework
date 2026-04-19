package com.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaptopOrderPage {

    WebDriver driver;

    public LaptopOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By laptopCategory = By.xpath("//a[text()='Laptops']");
    private By specificLaptop = By.xpath("//a[text()='Sony vaio i5']");

    // Unified selection method to ensure the flow is stable
    public void laptopSelection() {
        // 1. Wait for Category and Click
        WebElement cat = expliciteWait().until(ExpectedConditions.elementToBeClickable(laptopCategory));
        cat.click();

        // 2. Wait for the specific laptop to appear after the category filter refreshes the list
        WebElement laptop = expliciteWait().until(ExpectedConditions.visibilityOfElementLocated(specificLaptop));
        
        // 3. Scroll into view to ensure it's not hidden behind the footer/header
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", laptop);
        
        // 4. Click the specific laptop
        expliciteWait().until(ExpectedConditions.elementToBeClickable(laptop)).click();
    }

    public WebDriverWait expliciteWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased to 15s for slower CI environments
    }
}