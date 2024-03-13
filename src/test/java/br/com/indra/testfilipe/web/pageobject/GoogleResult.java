package br.com.indra.testfilipe.web.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.indra.testfilipe.web.selenium.SeleniumUtil;

public class GoogleResult {
	private SeleniumUtil seleniumUtil;

	public GoogleResult(WebDriver webDriver) {
		seleniumUtil = new SeleniumUtil(webDriver);
	}
	
	public WebElement totalResults() throws Exception {
		return seleniumUtil.findXPath("//div[@id='result-stats']","Quantidade de Resultados");
	}
	
}
