package com.xifin.training.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.xifin.LogoConfiguration.LogoConfiguration;

public class LogoConfigurationTest {
	private WebDriver  webDriver;
	private DesiredCapabilities desiredCapabilities;
	private WebDriverWait  webDriverWait;
	
//	protected RemoteWebDriver driver = null;
	String brower = "firefox";
//	String version = "53.0.3";
//	String brower = "chrome";
	String version = "ANY";
//	String nodeUrl="http://10.20.29.110:5566/wd/hub";
	String nodeUrl="http://10.20.29.126:5555/wd/hub";
	String baseUrl = "http://10.20.29.136:8100/xifinportal/filemaint/filemaintlogoconfig.html";
	protected Logger logger = Logger.getLogger(this.getClass().getName() + "],[" + webDriver);
	
	@BeforeTest
	public void setUp()throws Exception{
		desiredCapabilities = DesiredCapabilities.firefox();
		desiredCapabilities.setBrowserName("firefox");
//		desiredCapabilities = DesiredCapabilities.chrome();
//		desiredCapabilities.setBrowserName("chrome");
		webDriver = new RemoteWebDriver(new URL(nodeUrl),desiredCapabilities);
		webDriverWait = new WebDriverWait(webDriver, 20);
		webDriver.manage().window().maximize();
		logger = Logger.getLogger(this.getClass().getName() + "],[" + webDriver);
	}
	@Test
	public void testLogoConfiguration() throws InterruptedException{
		logger.info("==== Testing -testXPR_10223 ====");
		webDriver.get(baseUrl);
		LogoConfiguration logoConfiguration = new LogoConfiguration(webDriver);
		
		System.out.println("*** Type user and Password for logging in the page.");
		logoConfiguration.logoConfigurationUserInput().sendKeys("webservicetest_thanh");
		logoConfiguration.logoConfigurationPasswordInput().sendKeys("webservicetest_thanh");
		logoConfiguration.logoConfigurationLoginButton().click();
		
		System.out.println("*** Step 1 Expected results:User login successful and Logo Configuration page is diplayed. ");
		verifyAccessfulPageIsDisplayed();
		
		System.out.println("*** Step 2 Click on header help icon. Expected result: A help page will be displayed");
//		waitForPageLoaded() ;
		Thread.sleep(10000);
		logoConfiguration.logoConfigurationHeaderHelpIcon().click();
		verifyHelpPageOnClickHelpIcon("help/file_maintenance_tab/system_management_menu/p_logo_configuration_header.htm");
		
		System.out.println("*** Step 3 Select Document type. Expected Result: Logo Configuration page is displayed");
		logoConfiguration.DocumentTypeSelection().click();
		logoConfiguration.documentTyprOptionSelected().click();
		webDriverWait.until(ExpectedConditions.visibilityOfAllElements(logoConfiguration.facilityIdInput()));
		logoConfiguration.facilityIdInput().sendKeys("1024");
		logoConfiguration.facilityIdInput().sendKeys(Keys.ENTER);
		
		System.out.println("*** Step 4: Click help icon at the header bar. ");
		logoConfiguration.logoConfigurationHeaderHelpIcon().click();
		verifyHelpPageOnClickHelpIcon("help/file_maintenance_tab/system_management_menu/p_logo_configuration_header.htm");
		
		System.out.println("*** Step 5: Click help at the Clien Statement group. ");
		logoConfiguration.logoConfigurationClienStatementLogoHelpIcon().click();
		verifyHelpPageOnClickHelpIcon("/help/file_maintenance_tab/system_management_menu/p_logo_configuration_client_statement_logo.htm");		
		
		System.out.println("*** Step 6: Click help icon at the Current System Logo section. ");
		logoConfiguration.logoConfigurationCurrentSystemLogosHelpIcon().click();
		verifyHelpPageOnClickHelpIcon("help/file_maintenance_tab/system_management_menu/p_logo_configuration_current_system_logos.htm");
		
		webDriver.close();
		webDriver.quit();
	}

	private void verifyAccessfulPageIsDisplayed(){
		LogoConfiguration logoConfiguration = new LogoConfiguration(webDriver);
		webDriverWait.until(ExpectedConditions.urlMatches("http://10.20.29.136:8100/xifinportal/filemaint/filemaintlogoconfig.html"));
		assertEquals(logoConfiguration.logoConfigurationTitle().getText(), "Logo Configuration");
	}
	private void verifyHelpPageOnClickHelpIcon(String helpURL){
		String activeWindow = webDriver.getWindowHandle();
		for(String window : webDriver.getWindowHandles()){
			if(!window.equals(webDriver.getWindowHandle())){				
				assertTrue(webDriver.switchTo().window(window).getCurrentUrl().contains(helpURL));
				webDriver.switchTo().window(window).close();
			}
		}
		webDriver.switchTo().window(activeWindow);
	};
	private void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(webDriver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}
