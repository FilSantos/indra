package br.com.indra.testfilipe.rest;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRest {
	
	private String uri = "https://jsonplaceholder.typicode.com/todos";
	
	/** Cria conexao com o WS - REST
	 * @author filipesantos
	 * @param dado
	 * @return response
	 */
	private Response createRequest(String dado) {
		RestAssured.baseURI =uri;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get(String.format("/%s", dado));
		return response;
	}
	
	/** Valida o response code era esperado
	 * @author filipesantos
	 * @param response
	 * @param responseCode
	 */
	private void validaResponseCode(Response response, int responseCode) {
		Assert.assertEquals("Response", responseCode, response.getStatusCode());
	}

	/** Valida se o valor encontrado no Body era esperado
	 * @author filipesantos
	 * @param jsonPath
	 * @param dado
	 * @param esperado
	 */
	private void validaBody(JsonPath jsonPath, String dado, Object esperado) {
		Assert.assertEquals("UserID", esperado, jsonPath.get(dado));
	}
	
	
	@Test
	public void testPositivo()
	{
		Response response = createRequest("1");
		validaResponseCode(response,200);
		JsonPath jsonPath = response.body().jsonPath();
		validaResponseCode(response,200);
		validaBody(jsonPath, "userId", 1);
		validaBody(jsonPath, "id", 1);
		validaBody(jsonPath, "title", "delectus aut autem");
		validaBody(jsonPath, "completed", false);

	}
	
	@Test
	public void testNegativoResponse()
	{
		Response response = createRequest("1");
		validaResponseCode(response,400);

	}
	
	@Test
	public void testNegativoDados()
	{
		Response response = createRequest("1");
		validaResponseCode(response,200);
		JsonPath jsonPath = response.body().jsonPath();
		validaBody(jsonPath, "UserId", 2);
	}
	
}
