

package com.stepDefinition;

import com.pages.LaptopOrderPage;
import com.driver.factory.DriverFactory;
import io.cucumber.java.en.When;

public class LaptopOrderStepDefinition {
    private LaptopOrderPage laptopPage = new LaptopOrderPage(DriverFactory.getDriver());

    @When("user clicks on a laptop")
    public void user_clicks_on_a_laptop() {
        laptopPage.laptopSelection();
    }
}