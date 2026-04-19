package com.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighlightUtil {

    private WebDriver driver;

    // Constructor to initialize the driver
    public HighlightUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void highlightElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Apply a solid red border and yellow background
            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);
            
            // Wait slightly so the screenshot captures the color change
            Thread.sleep(500); 
        } catch (Exception e) {
            System.out.println("Highlighting skipped: " + e.getMessage());
        }
    }
}