package com.hooks;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.driver.factory.DriverFactory;
import com.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;

	@Before(order = 0)
	public void launchBrowser() throws IOException {
		String browserName = ConfigReader.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@After(order = 0)
    public void quitBrowser() {
        if (driver != null) {
            driver.quit();
        }
	}
        
	@After(order = 1)
	public void tearDown(Scenario scenario) {
	    if (scenario.isFailed()) {
	        try {
	            // Take screenshot as Base64 for easier embedding in Extent
	            String screenshotName = scenario.getName().replaceAll(" ", "_");
	            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	            scenario.attach(sourcePath, "image/png", screenshotName);
	            
	            System.out.println("Screenshot attached for scenario: " + scenario.getName());
	        } catch (Exception e) {
	            System.err.println("Failed to take screenshot: " + e.getMessage());
	        }
	    }
	}
    }

