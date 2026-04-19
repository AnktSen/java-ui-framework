package com.testrunner;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.AfterSuite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.stepDefinition", "com.hooks"},
//        tags = "@LaptopOrder",
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" 
            },
            monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

  /* @Override
    @org.testng.annotations.DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }*/
   
	@AfterSuite
	public void reportGeneration() throws IOException {
	    File htmlFile = new File("target/SparkReport/ExtentReport.html");
	    
	    if (htmlFile.exists()) {
	        // Check if the environment has a display (Monitor)
	        if (!java.awt.GraphicsEnvironment.isHeadless()) {
	            System.out.println("Opening Extent Report in Browser...");
	            Desktop.getDesktop().browse(htmlFile.toURI());
	        } else {
	            // This will print in your Jenkins/Docker logs instead of crashing
	            System.out.println("Headless environment detected. Extent Report generated at: " + htmlFile.getAbsolutePath());
	        }
	    } else {
	        System.out.println("Extent Report file not found at: " + htmlFile.getAbsolutePath());
	    }
	}
}