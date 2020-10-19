package qa.testcase;

import java.awt.List;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import qa.page.Register;
import qa.util.TestUtil;
import qa.util.WebEventListener;
import selenium.TestBase;

public class RegisterTest extends TestBase {

	Actions act;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Register register;
	
	public RegisterTest() {
		super();
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData("userData");
		return data;
	}

	
	
		@Test(priority = 1)
	    public void testAssertions() throws Exception {
			

			register.LoginAction();
 
	}
		
	
	@BeforeMethod
	public void setUp() {
		initialization();
		act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		register = new Register();
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chrome_Profile = new ChromeOptions();
			chrome_Profile.addArguments("chrome.switches","--disable-extensions"); 
			chrome_Profile.addArguments("--disable-save-password");
			chrome_Profile.addArguments("disable-infobars");
			chrome_Profile.addArguments("--disable-notifications");

			driver = new ChromeDriver(chrome_Profile);
		} else if (browserName.equals("FF")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();  
		}

		 
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
