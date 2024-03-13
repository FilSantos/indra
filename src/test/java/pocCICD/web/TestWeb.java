package pocCICD.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;
import pocCICD.web.filme.Filme;
import pocCICD.web.pageobject.GoogleMain;
import pocCICD.web.pageobject.GoogleResult;
import pocCICD.web.selenium.SeleniumUtil;


public class TestWeb {

	private static WebDriver webDriver;
	
	@BeforeClass
	public static void initiate() throws Exception {
		
		//Se deseja headless, informar no segundo parametro o booleano
		webDriver = SeleniumUtil.getWebDriver("http://www.google.com/", false);
		
	}
	
	@AfterClass
	public static void tearDown() {
		SeleniumUtil.closeDriver(webDriver);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void pesquisaGoogle() {

		List<Filme> listFilmes = new ArrayList<Filme>();	
		
		Filme filme;
		
		filme= new Filme("WiFi Ralph", "2019", "Rich Moore", "10/05/1963");
		listFilmes.add(filme);
		
		GoogleMain googleMain = new GoogleMain(webDriver);
		GoogleResult googleResult  = new GoogleResult(webDriver);
		
		for (Filme itemLista : listFilmes) {
			
			try {
				String textoPesquisa = String.format("%s %s", itemLista.getDiretorNome(), itemLista.getfilmeNome());
				
				googleMain.abrePagina();
				
				WebElement textoParaPesquisa = googleMain.searchText();
				textoParaPesquisa.sendKeys(textoPesquisa);
				try {
					WebElement btnPesquisar = googleMain.searchButton();
					btnPesquisar.click();
				} catch (Exception e) {
					//Se houver erro quando localizar, forcar atraves do ENTER
					textoParaPesquisa.sendKeys(Keys.ENTER);
				}
				String totalResultados = googleResult.totalResults().getText();
				
				System.out.println(String.format("%s - %s", textoPesquisa, totalResultados) );
				
				JavascriptExecutor jse = (JavascriptExecutor) webDriver;
				// Setting the status of test as 'passed' or 'failed' based on the condition; if title of the web page matches 'BrowserStack - Google Search'
			    if (totalResultados.contains("results")) {
			      jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Tem resultados\"}}");
			    }
			    else {
			      jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Nao tem resultados\"}}");
			    }
				
				
				Assert.assertTrue("Texto da pesquisa contem",totalResultados.contains("results"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		

	}
}
