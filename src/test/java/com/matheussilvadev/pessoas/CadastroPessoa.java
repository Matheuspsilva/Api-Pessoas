package com.matheussilvadev.pessoas;

import static org.hamcrest.CoreMatchers.equalTo;

import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.matheussilvadev.pessoas.domain.model.Cidade;
import com.matheussilvadev.pessoas.domain.model.Endereco;
import com.matheussilvadev.pessoas.domain.model.Estado;
import com.matheussilvadev.pessoas.domain.model.Pessoa;
import com.matheussilvadev.pessoas.domain.repository.CidadeRepository;
import com.matheussilvadev.pessoas.domain.repository.EstadoRepository;
import com.matheussilvadev.pessoas.domain.service.CadastroPessoaService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource({"/application-test.properties"})
public class CadastroPessoa {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/pessoas/";
		
		prepararDados();
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
			.body("nome", Matchers.hasItem("Paulo"));

	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarPessoaExistente() {
		RestAssured.given()
			.pathParam("pessoaId", 1)
			.accept(ContentType.JSON)
		.when()
			.get("{pessoaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Paulo"));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarPessoaInexistente() {
		RestAssured.given()
			.pathParam("pessoaId", 13)
			.accept(ContentType.JSON)
		.when()
			.get("/{pessoaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
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
	
	@Test
	public void deveRetornarStatus200_QuandoAtualizarPessoa() {
		RestAssured.given()
		.pathParam("pessoaId", 1)
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
			.put("{pessoaId}")
		.then()
			.statusCode(HttpStatus.OK.value());

	}
	
	@Test
	public void deveRetornarStatus404_QuandoAtualizarPessoaInexistente() {
		RestAssured.given()
		.pathParam("pessoaId", 22)
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
			.put("{pessoaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());

	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoDeletarPessoaExistente() {
		RestAssured.given()
			.pathParam("pessoaId", 1)
			.accept(ContentType.JSON)
		.when()
			.delete("{pessoaId}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatus404_QuandoDeletarPessoaInexistente() {
		RestAssured.given()
			.pathParam("pessoaId", 13)
			.accept(ContentType.JSON)
		.when()
			.delete("{pessoaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	
	private void prepararDados() {
		
		Estado estado = new Estado();
		estado.setNome("Paraná");
		
		estado = estadoRepository.save(estado);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Maringá");
		cidade.setEstado(estado);
		
		cidade = cidadeRepository.save(cidade);
		
		Pessoa novaPessoa = new Pessoa();
		novaPessoa.setNome("Paulo");
		novaPessoa.setDataNascimento(LocalDateTime.now());
		novaPessoa.setId(1L);

		Endereco endereco = new Endereco();
		endereco.setCidade(cidade);
		endereco.setCep("65309432");
		endereco.setLogradouro("Rua x");
		endereco.setNumero("32");

		novaPessoa.setEndereco(endereco);
		
		novaPessoa.getEndereco();

		novaPessoa = cadastroPessoaService.salvar(novaPessoa);
	}
	

}
