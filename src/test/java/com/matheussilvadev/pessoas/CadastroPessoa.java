package com.matheussilvadev.pessoas;

import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({"/application-test.properties"})
public class CadastroPessoa {

	@Autowired
	private Flyway flyway;
	
	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/pessoas/";
		
		flyway.migrate();
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarPessoa() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());

	}
	
	@Test
	public void deveConter1Pessoa_QuandoConsultarPessoa() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("nome", Matchers.hasSize(1))
			.body("nome", Matchers.hasItem("Matheus Pereira da Silva"));

	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarPessoa() {
		RestAssured.given()
		.body("{\n"
				+ "    \"nome\": \"Pedro\",\n"
				+ "    \"dataNascimento\": \"2011-12-03T10:15:30\",\n"
				+ "    \"endereco\":{\n"
				+ "        \"cep\": \"65058500\",\n"
				+ "        \"numero\": \"100\",\n"
				+ "        \"logradouro\": \"Rua dos pardais\",\n"
				+ "        \"cidade\": {\n"
				+ "            \"id\": 1\n"
				+ "        }\n"
				+ "    }\n"
				+ "\n"
				+ "    \n"
				+ "}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());

	}
	

}
