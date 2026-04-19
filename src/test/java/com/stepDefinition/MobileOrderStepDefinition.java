package com.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import com.driver.factory.DriverFactory;
import com.pages.LaptopOrderPage;
import com.pages.MobileOrder;
import com.util.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MobileOrderStepDefinition {

	private MobileOrder loginPage;	
	
	@Given("user is on login page")
	public void user_is_on_login_page() throws IOException {
		loginPage = new MobileOrder(DriverFactory.getDriver());
	    DriverFactory.getDriver().get(ConfigReader.getProperty("url"));	    
	    loginPage.implicitlyWaitDuration();
		loginPage.clickLoginLink();		
	}

	@Given("user enters {string} and {string}")
	public void user_enters_and(String uName, String uPassword) {
		loginPage.enterUsername(uName);
		loginPage.enterPassword(uPassword);
	}

	@Given("user clicks on login button")
	public void user_clicks_on_login_button() {
		loginPage.clickLoginButton();
	}

	@Then("user is redirected to home page")
	public void user_is_redirected_to_home_page() {
		String profileName = "Welcome Pawan Pankaj";
		Assert.assertEquals("Welcome Pawan Pankaj", profileName);
	}

	@Given("user is on home page")
	public void user_is_on_home_page() {
		String profileName = "Welcome Pawan Pankaj";
		Assert.assertEquals("Welcome Pawan Pankaj", profileName);
	}

	@Given("user clicks on a mobile phone")
	public void user_clicks_on_a_mobile_phone() throws InterruptedException {
		loginPage.clickOnDesiredMobile();
	}

	@Given("user clicks on Add to cart button")
	public void user_clicks_on_button() throws InterruptedException {
		loginPage.addTocart();
	}

	@Then("user accepts the success pop-up")
	public void user_accepts_the_success_pop_up() {
		System.out.println("Missed Alert");
		loginPage.acceptAlert();
	}

	@Then("user clicks on cart tab")
	public void user_clicks_on_cart_tab() {		
		loginPage.clickOnCart();
	}

	@Given("user is on cart page")
	public void user_is_on_cart_page() {
		String cartPageValidation = "Products";
		Assert.assertEquals(loginPage.productsPage(), cartPageValidation);
	}

	@Given("User clicks on place order button")
	public void user_clicks_on_place_order_button() {
		loginPage.placeOrder();
	}

	@Given("user fills {string} {string} {string} {string} {string} {string} in the place order form")
	public void user_fills_in_the_place_order_form(String Name, String Country, String City, String Credit_Card, String Months, String Year){
	    loginPage.productForm(Name, Country, City, Credit_Card, Months, Year);
	}

	@Given("user clicks on Purchase button")
	public void user_clicks_on_purchase_button() {
		loginPage.purchaseProduct();
	}

	@Then("a {string} pop-up should be displayed")
	public void a_pop_up_should_be_displayed(String msg) {
		String successMsg = "Thank you for your purchase";
		Assert.assertEquals(msg, successMsg);
	}
}
