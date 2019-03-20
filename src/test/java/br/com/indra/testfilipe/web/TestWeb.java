package br.com.indra.testfilipe.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.indra.testfilipe.web.filme.Filme;
import br.com.indra.testfilipe.web.pageobject.GoogleMain;
import br.com.indra.testfilipe.web.pageobject.GoogleResult;
import br.com.indra.testfilipe.web.selenium.SeleniumUtil;


public class TestWeb {

	private static WebDriver webDriver;
	
	@BeforeClass
	public static void initiate() {
		
		//Se deseja headless, informar no segundo parametro o booleano
		webDriver = SeleniumUtil.getWebDriver("http://www.google.com/", true);
		
	}
	
	@AfterClass
	public static void tearDown() {
		SeleniumUtil.closeDriver(webDriver);
		
	}
	
	@Test
	public void pesquisaGoogle() {

		List<Filme> listFilmes = new ArrayList<Filme>();	
		
		Filme filme;
		
		filme= new Filme("WiFi Ralph", "2019", "Rich Moore", "10/05/1963");
		listFilmes.add(filme);
		
		filme= new Filme("Detona Ralph", "2013", "Rich Moore", "10/05/1963");
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		

	}
}
