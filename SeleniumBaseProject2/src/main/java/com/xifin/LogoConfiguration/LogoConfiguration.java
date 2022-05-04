package com.xifin.LogoConfiguration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoConfiguration {
	private WebDriver driver;

	public LogoConfiguration(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement logoConfigurationUserInput(){
		return driver.findElement(By.id("username"));
	}
	public WebElement logoConfigurationPasswordInput(){
		return driver.findElement(By.xpath("//input[@id='pw']"));
	}
	public WebElement logoConfigurationLoginButton(){
		return driver.findElement(By.xpath("//button[@type='submit']"));
	}
	
	public WebElement logoConfigurationTitle(){
		return driver.findElement(By.xpath("//span[@class='platormPageTitle']"));
	}
	public WebElement DocumentTypeSelection() {
		return driver.findElement(By.xpath("//div[@id='s2id_logoType']"));
	}
	public WebElement documentTyprOptionSelected() {
		return driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li[4]"));		
	}
	public WebElement facilityIdInput() {
		return driver.findElement(By.xpath("//input[@id='facilityAbbrev']"));
	}
	public WebElement logoConfigurationHeaderHelpIcon() {
		return driver.findElement(By.xpath("//*[@data-help-id='p_logo_configuration_header']"));
//		return driver.findElement(By.xpath("//div[@class='sectionHelpContainer helpPage']"));
	}
	public WebElement logoConfiguationGroupHelpIcon() {
		return driver.findElement(By.xpath("//*[@id='fileMaintLogoConfigForm']//div[@class='sectionHelpContainer']//a"));
	}
	public WebElement logoConfigurationClienStatementLogoHelpIcon() {
		return driver.findElement(By.xpath("//*[@id='mainSections']/div[2]/div/div/section/div/div[1]/a"));
	}
	public WebElement logoConfigurationCurrentSystemLogosHelpIcon() {
		return driver.findElement(By.xpath("//*[@id='mainSections']/div[3]/div/div/section/div/div[1]/a"));
	}
	

}
