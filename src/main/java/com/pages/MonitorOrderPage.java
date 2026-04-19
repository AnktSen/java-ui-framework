package com.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MonitorOrderPage {

    WebDriver driver;

    public MonitorOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By monitorsCategory = By.xpath("//a[text()='Monitors']");
    private By specificMonitor = By.xpath("//a[text()='Apple monitor 24']");

    // Unified selection method to ensure the flow is stable
    public void monitorSelection() {
        // 1. Wait for Category and Click
        WebElement cat = expliciteWait().until(ExpectedConditions.elementToBeClickable(monitorsCategory));
        cat.click();

        // 2. Wait for the specific laptop to appear after the category filter refreshes the list
        WebElement monitor = expliciteWait().until(ExpectedConditions.visibilityOfElementLocated(specificMonitor));
        
        // 3. Scroll into view to ensure it's not hidden behind the footer/header
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", monitor);
        
        // 4. Click the specific laptop
        expliciteWait().until(ExpectedConditions.elementToBeClickable(monitor)).click();
    }
    
    public WebDriverWait expliciteWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)); // Increased to 15s for slower CI environments
    }
}