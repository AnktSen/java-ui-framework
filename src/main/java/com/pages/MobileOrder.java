package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.util.HighlightUtil;

public class MobileOrder {

    WebDriver driver;
    HighlightUtil highLight;
    
    public MobileOrder(WebDriver driver) {
        this.driver = driver;
        this.highLight = new HighlightUtil(driver);
        
    }
    // Locators
    By login = By.id("login2");
    By userName = By.id("loginusername");
    By password = By.id("loginpassword");
    By loginBtn = By.xpath("//button[text()='Log in']");
    By loginModal = By.id("logInModalLabel");
    
    By mobile = By.xpath("//a[text()='Samsung galaxy s6']");
    By profileVali = By.id("nameofuser");
    
    By addToCart = By.xpath("//*[text()='Add to cart']");
    By cart = By.xpath("//a[text()='Cart']");
    By placeOrder = By.xpath("//*[text()='Place Order']");
    
    By productsPage = By.xpath("//*[text()='Products']");
    By purchase  = By.xpath("//*[text()='Purchase']");
    
    By name = By.id("name");
    By country = By.id("country");
    By city = By.id("city");
    By card = By.id("card");
    By month = By.id("month");
    By year = By.id("year");

    
    public void scrollIntoView(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }
    
    public WebDriverWait expliciteWait() {
    	return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void implicitlyWaitDuration() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    // Actions (Methods)

    public void clickLoginLink() {
    	expliciteWait().until(ExpectedConditions.elementToBeClickable(login)).click();
    }

    public void enterUsername(String uname) {
    	expliciteWait().until(ExpectedConditions.visibilityOfElementLocated(userName)).sendKeys(uname);
    }

    public void enterPassword(String pwd) {
    	expliciteWait().until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pwd);
    }

    public void clickLoginButton() {    	
        // 1. Click the button
        expliciteWait().until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        
        // 2. Wait for the Login Modal to become invisible
        // This prevents the 'hang' and ensures the background action is ready
       // The ID for the DemoBlaze login popup
        expliciteWait().until(ExpectedConditions.invisibilityOfElementLocated(loginModal));
        
        // 3. Alternatively, wait for the 'Welcome' text to appear
        expliciteWait().until(ExpectedConditions.visibilityOfElementLocated(profileVali));
    }
    
    public void clickOnDesiredMobile() {
        // 1. Find the element using your explicit wait
        WebElement mobileElement = expliciteWait().until(ExpectedConditions.presenceOfElementLocated(mobile));
        
        // 2. Initialize JavascriptExecutor and execute the scroll script
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mobileElement);
        
        // 3. (Optional but recommended) Wait until it's clickable then click
        expliciteWait().until(ExpectedConditions.elementToBeClickable(mobileElement)).click();
    }
    
    public String nameVlidation() {
    	WebElement ele = expliciteWait().until(ExpectedConditions.visibilityOfElementLocated(profileVali));
    	return ele.getText();
    }
    public void selectingMobile() {
    	driver.findElement(mobile).click();
    }
//    public void addTocart() {
//        // Wait for 'Add to cart' to be clickable
//        expliciteWait().until(ExpectedConditions.elementToBeClickable(addToCart)).click();
//    }
    
    public void addTocart() {
        try {
            expliciteWait().until(ExpectedConditions.elementToBeClickable(addToCart)).click();
        } catch (Exception e) {
            highLight.highlightElement(addToCart); // Use the utility
            throw e;
        }
    }
    public void acceptAlert() {
        // Dynamic wait for the JavaScript alert to pop up
        expliciteWait().until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    public void clickOnCart() {
        expliciteWait().until(ExpectedConditions.elementToBeClickable(cart)).click();
    }
    public void placeOrder() {
        // 1. Wait for any pending navigation to settle
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // 2. Use a 'Stable' wait to ensure the element is actually present in the new DOM
        try {
            wait.until(ExpectedConditions.elementToBeClickable(placeOrder)).click();
        } catch (WebDriverException e) {
            // If 'aborted by navigation' happens, wait 1 second and try one last time
            System.out.println("Navigation aborted, retrying click...");
            staticWait(1000); 
            wait.until(ExpectedConditions.elementToBeClickable(placeOrder)).click();
        }
    }

    // Utility for those rare 'aborted navigation' moments
    public void staticWait(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { e.printStackTrace(); }
    }
    public String productsPage() {
    	return driver.findElement(productsPage).getText();
    }
    public void purchaseProduct() {
        expliciteWait().until(ExpectedConditions.elementToBeClickable(purchase)).click();
    }
    
    public void productForm(String Name, String Country, String City, String Credit_Card, String Months, String Year) {
    	driver.findElement(name).sendKeys(Name);
    	driver.findElement(country).sendKeys(Country);
    	driver.findElement(city).sendKeys(City);
    	driver.findElement(card).sendKeys(Credit_Card);
    	driver.findElement(month).sendKeys(Months);
    	driver.findElement(year).sendKeys(Year);
    }
    
 // Reusable method for all elements in your Page Class
    
    
    
    
    

    

    

    
}