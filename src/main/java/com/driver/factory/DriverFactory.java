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
		if(ConfigReader.getProperty("mode").equalsIgnoreCase("true")) {
			System.out.println(ConfigReader.getProperty("mode"));			
		
		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Running on Chrome");
			ChromeOptions options = new ChromeOptions();
			if (Boolean.parseBoolean(System.getProperty("headless"))) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
			}
			tlDriver.set(new ChromeDriver(options));

		} else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();

			if (Boolean.parseBoolean(System.getProperty("headless"))) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
			}
			tlDriver.set(new EdgeDriver(options));
		} else {
			System.out.println("Please pass a correct browser value: " + browser);
			return null;
		}
		
		}else {
			
			if(browser.equalsIgnoreCase("chrome")) {
				tlDriver.set(new ChromeDriver());
			}else if(browser.equalsIgnoreCase("edge")) {
				tlDriver.set(new EdgeDriver());
			}else {
				System.out.println("Please pass a correct browser value: " + browser);
				return null;
			}			
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}