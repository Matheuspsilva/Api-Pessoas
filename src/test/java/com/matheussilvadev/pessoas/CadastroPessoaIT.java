package com.matheussilvadev.pessoas;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.matheussilvadev.pessoas.domain.exception.PessoaNaoEncontradaException;
import com.matheussilvadev.pessoas.domain.model.Cidade;
import com.matheussilvadev.pessoas.domain.model.Endereco;
import com.matheussilvadev.pessoas.domain.model.Estado;
import com.matheussilvadev.pessoas.domain.model.Pessoa;
import com.matheussilvadev.pessoas.domain.repository.CidadeRepository;
import com.matheussilvadev.pessoas.domain.repository.EstadoRepository;
import com.matheussilvadev.pessoas.domain.service.CadastroPessoaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource({"/application-test.properties"})
public class CadastroPessoaIT {

	@Autowired
	private CadastroPessoaService cadastroPessoaService;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Before
	public void setUp() {
		
		prepararDados();
	}

	@Test
	public void testarCadastroPessoaComSucesso() {

		Cidade cidade = cidadeRepository.findById(1L).get();

		Pessoa novaPessoa = new Pessoa();
		novaPessoa.setNome("Paulo");
		novaPessoa.setDataNascimento(OffsetDateTime.now());
		novaPessoa.setId(1L);

		Endereco endereco = new Endereco();
		endereco.setCidade(cidade);
		endereco.setCep("65309432");
		endereco.setLogradouro("Rua x");
		endereco.setNumero("32");

		novaPessoa.setEndereco(endereco);

		novaPessoa = cadastroPessoaService.salvar(novaPessoa);

		assertThat(novaPessoa).isNotNull();
		assertThat(novaPessoa.getId()).isNotNull();
	}

	@Test(expected = ConstraintViolationException.class)
	public void testarCadastroPessoaSemNome() {

		Cidade cidade = cidadeRepository.findById(1L).get();

		Pessoa novaPessoa = new Pessoa();
		novaPessoa.setNome("");
		novaPessoa.setDataNascimento(OffsetDateTime.now());
		novaPessoa.setId(2L);

		Endereco endereco = new Endereco();
		endereco.setCidade(cidade);
		endereco.setCep("65309432");
		endereco.setLogradouro("Rua x");
		endereco.setNumero("32");

		novaPessoa.setEndereco(endereco);
	
		novaPessoa = cadastroPessoaService.salvar(novaPessoa);

	}
	
    @Test(expected = PessoaNaoEncontradaException.class)
    public void deveFalhar_QuandoExcluirPessoaInexistente() {
        cadastroPessoaService.excluir(100L);
    }       
    
	
	private void prepararDados() {
		
		Estado estado = new Estado();
		estado.setNome("Paran??");
		
		estado = estadoRepository.save(estado);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Maring??");
		cidade.setEstado(estado);
		
		cidade = cidadeRepository.save(cidade);
	}
	

}
