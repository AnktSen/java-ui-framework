package com.driver.factory;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.util.ConfigReader;

public class DriverFactory {

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver init_driver(String browser) throws IOException {
	    // 1. Get the headless flag from System (Maven command) or Config file as backup
	    String headlessFlag = System.getProperty("headless");
	    if (headlessFlag == null) {
	        headlessFlag = ConfigReader.getProperty("headless"); // Fallback
	    }
	    boolean isHeadless = Boolean.parseBoolean(headlessFlag);

	    if (browser.equalsIgnoreCase("chrome")) {
	        ChromeOptions options = new ChromeOptions();
	        if (isHeadless) {
	            options.addArguments("--headless=new");
	            options.addArguments("--window-size=1920,1080");
	            options.addArguments("--no-sandbox");
	            options.addArguments("--disable-dev-shm-usage");
	        }
	        tlDriver.set(new ChromeDriver(options));

	    } else if (browser.equalsIgnoreCase("edge")) {
	        EdgeOptions options = new EdgeOptions();
	        if (isHeadless) {
	            options.addArguments("--headless=new");
	            options.addArguments("--window-size=1920,1080");
	        }
	        tlDriver.set(new EdgeDriver(options));
	    }
	    
	    // Standard driver setup
	    getDriver().manage().deleteAllCookies();
	    if (!isHeadless) {
	        getDriver().manage().window().maximize();
	    }
	    return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}