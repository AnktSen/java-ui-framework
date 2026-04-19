

package com.stepDefinition;

import com.driver.factory.DriverFactory;
import com.pages.MonitorOrderPage;

import io.cucumber.java.en.When;

public class MonitorOrderStepDefinition {
    private MonitorOrderPage monitorPage = new MonitorOrderPage(DriverFactory.getDriver());

    @When("user clicks on a Monitor")
    public void user_clicks_on_a_monitot() {
    	monitorPage.monitorSelection();
    }
}