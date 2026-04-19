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
       // Specify the exact path where your report is generated
       File htmlFile = new File("target/SparkReport/ExtentReport.html");
       
       // Check if the file exists before trying to open it
       if (htmlFile.exists()) {
           // This command opens the file in your default system browser
           Desktop.getDesktop().browse(htmlFile.toURI());
       } else {
           System.out.println("Extent Report file not found at: " + htmlFile.getAbsolutePath());
       }
   }
}