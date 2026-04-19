package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.util.HighlightUtil;

public class BasePage {

    protected WebDriver driver;
    protected HighlightUtil highlighter;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.highlighter = new HighlightUtil(driver);
    }

    protected WebElement getElement(By locator) {
        highlighter.highlightElement(locator); // 👈 automatic highlight
        return driver.findElement(locator);
    }
}