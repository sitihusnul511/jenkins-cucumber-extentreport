package com.juaracoding.cucumber4.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.juaracoding.cucumber4.drivers.DriverSingleton;

public class BookingPage {

private WebDriver driver;
	
	public BookingPage() {
		this.driver = DriverSingleton.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "#fadein > div.sidebar-nav > div > div.sidebar-menu-wrap > ul > li")
	List<WebElement> menuMyBooking;
	
	public void goToMenuMyBooking() {
		menuMyBooking.get(1).click();
	}
	
	public String getTxtTitleBookingPage() {
		return driver.getTitle();
	}
	
	
}
