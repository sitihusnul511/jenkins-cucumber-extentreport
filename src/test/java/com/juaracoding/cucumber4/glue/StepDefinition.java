package com.juaracoding.cucumber4.glue;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.juaracoding.cucumber4.config.AutomationFrameworkConfig;
import com.juaracoding.cucumber4.drivers.DriverSingleton;
import com.juaracoding.cucumber4.pages.BookingPage;
import com.juaracoding.cucumber4.pages.LoginPage;
import com.juaracoding.cucumber4.utlis.ConfigurationProperties;
import com.juaracoding.cucumber4.utlis.Constants;
import com.juaracoding.cucumber4.utlis.TestCases;
import com.juaracoding.cucumber4.utlis.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfig.class)
public class StepDefinition {

	private static WebDriver driver;
	private LoginPage loginPage;
	private BookingPage bookingPage;
	ExtentTest extentTest;
	static ExtentReports reports = new ExtentReports("src/main/resources/TestReport.html");
	
	@Autowired
	ConfigurationProperties configurationProperties;
	
	@Before
	public void initializeObjects() {
		DriverSingleton.getInstance(configurationProperties.getBrowser());
		loginPage = new LoginPage();
		bookingPage = new BookingPage();
		TestCases[] tests = TestCases.values();
		extentTest = reports.startTest(tests[Utils.testCount].getTestName());
		Utils.testCount++;
	}
	
	@After
	public void closeObject() {
		reports.endTest(extentTest);
		reports.flush();
	}
	
	@AfterAll
	public static void closeBrowser() {
		driver.quit();
	}
	
	@Given("Customer mengakses url")
	public void customer_mengakses_url() {
		driver = DriverSingleton.getDriver();
		driver.get(Constants.URL);
		extentTest.log(LogStatus.PASS, "Navigating to "+Constants.URL);
	}
	
	@When("Customer klik login button")
	public void customer_klik_login_button() {
		scroll();
		loginPage.submitLogin(configurationProperties.getEmail(), configurationProperties.getPassword());
		extentTest.log(LogStatus.PASS, "Customer klik login button");
	}
	
	@Then("Customer berhasil login")
	public void customer_berhasil_login() {
		//refresh
		driver.navigate().refresh();
		tunggu();
		assertEquals(configurationProperties.getTxtWelcome(), loginPage.getTxtWelcome());
		extentTest.log(LogStatus.PASS, "Customer berhasil login");
	}
	
	@When("Customer klik menu My Booking")
	public void customer_klik_menu_my_booking() {
		bookingPage.goToMenuMyBooking();
		extentTest.log(LogStatus.PASS, "Customer klik menu My Booking");
	}
	
	@Then("Customer melihat page title")
	public void customer_melihat_page_title() {
		assertEquals(configurationProperties.getTxtTitleBookingPage(), bookingPage.getTxtTitleBookingPage());
		extentTest.log(LogStatus.PASS, "Customer melihat page title");
	}	
	
	public void tunggu() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void scroll() {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollBy(0,500)");
	}
}
