package br.com.indra.testfilipe.web.selenium;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	private long TIME_OUT = 10;
	private WebDriver driver;
	private WebDriverWait driverWait;
	
	public SeleniumUtil(WebDriver driver) {
		this.driver = driver;
		this.driverWait = new WebDriverWait(this.driver, TIME_OUT);
	}

	public SeleniumUtil() {
		
		this.driver = null;
	}

	
	public static WebDriver getWebDriver(String uri,Boolean headless) {
		
		WebDriver tempDriver;
		
		ChromeOptions chromeOptions = new ChromeOptions(); 
		if (headless) {
			chromeOptions.addArguments("--headless"); 
		}
		
		tempDriver = new ChromeDriver(chromeOptions);
 
		return tempDriver;
		
	}
	
	private void webDriverWait(By by) {
		
		driverWait.until(ExpectedConditions.presenceOfElementLocated(by));
		
	}
	
	private WebElement findBy (By by, String label) {
		
		WebElement webElement = null;
		
		try {
			webDriverWait(by);
			webElement = driver.findElement(by);
		} catch (Exception e) {
			Assert.fail(String.format("Objeto %s, com localizador %s, nao encontrado!", label, by.toString()));
		}
		return webElement;
	}
	
	public WebElement findXPath (String xPath, String label){

		return  findBy(By.xpath(xPath), label);
		
	}
	
	public WebElement findName (String name, String label) {
		
		return  findBy(By.name(name), label);
		
	}

	public static void closeDriver(WebDriver webDriver) {
		try {
			webDriver.quit();
			//webDriver.close();
		} catch (Exception e) {
			System.err.println("Não foi possivel encerrar o Chrome / Selenium corretamente!");
		}

		
	}

	public void openPage(String uri) {
		driver.get(uri);
		
	}
}
