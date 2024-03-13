package pocCICD.web.selenium;

import java.net.URL;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil {
	
	private long TIME_OUT = 10;
	private WebDriver driver;
	private WebDriverWait driverWait;
	
	public static final String USERNAME = "filipesousasanto_od5Juq";
	public static final String AUTOMATE_KEY = "ZUs2pVkuEEJ35VthYgSY";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
	
	public SeleniumUtil(WebDriver driver) {
		this.driver = driver;
		this.driverWait = new WebDriverWait(this.driver, TIME_OUT);
	}

	public SeleniumUtil() {
		
		this.driver = null;
	}

	
	public static WebDriver getWebDriver(String uri,Boolean headless) throws Exception {		

		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setPlatform(Platform.WINDOWS);
		capability.setBrowserName("chrome");
		capability.setVersion("122");
		//capability.setCapability("build", "tst-01.0.0");
		capability.setCapability("name", "Teste Google");
		capability.setCapability("browserstack.debug", "false");
		// Creatng URL object
		
		URL browserStackUrl = new URL(URL);
		RemoteWebDriver temp = new RemoteWebDriver (browserStackUrl, capability);
		temp.manage().window().maximize();
		return temp;
		
		
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
