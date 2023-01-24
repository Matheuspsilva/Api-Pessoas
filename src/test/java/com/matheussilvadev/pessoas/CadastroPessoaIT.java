package com.matheussilvadev.pessoas;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.matheussilvadev.pessoas.domain.exception.PessoaNaoEncontradaException;
import com.matheussilvadev.pessoas.domain.repository.CidadeRepository;
import com.matheussilvadev.pessoas.domain.service.CadastroPessoaService;
import com.matheussilvadev.pessoas.model.Cidade;
import com.matheussilvadev.pessoas.model.Endereco;
import com.matheussilvadev.pessoas.model.Pessoa;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource({"/application-test.properties"})
public class CadastroPessoaIT {

	@Autowired
	private CadastroPessoaService cadastroPessoaService;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Test
	public void testarCadastroPessoaComSucesso() {

		Cidade cidade = cidadeRepository.findById(1L).get();

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

		novaPessoa = cadastroPessoaService.salvar(novaPessoa);

		assertThat(novaPessoa).isNotNull();
		assertThat(novaPessoa.getId()).isNotNull();
	}

	@Test(expected = ConstraintViolationException.class)
	public void testarCadastroPessoaSemNome() {

		Cidade cidade = cidadeRepository.findById(1L).get();

		Pessoa novaPessoa = new Pessoa();
		novaPessoa.setNome("");
		novaPessoa.setDataNascimento(LocalDateTime.now());
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
    

}
