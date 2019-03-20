package br.com.indra.testfilipe.web.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.indra.testfilipe.web.selenium.SeleniumUtil;

public class GoogleMain {
	private SeleniumUtil seleniumUtil;

	public GoogleMain(WebDriver webDriver) {
		seleniumUtil = new SeleniumUtil(webDriver);
	}
	
	public void abrePagina() {
		seleniumUtil.openPage("http://www.google.com");
	}
	
	public WebElement searchButton() {
		return seleniumUtil.findName("btnK", "Botão de Pesquisa");
	}
	
	public WebElement searchText() {
		 return seleniumUtil.findName("q","Caixa de texto para pesquisa");
	}
}
