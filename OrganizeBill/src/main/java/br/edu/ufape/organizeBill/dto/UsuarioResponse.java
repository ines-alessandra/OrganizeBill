package br.edu.ufape.organizeBill.dto;

import java.util.*;
import java.math.*;
import br.edu.ufape.organizeBill.model.*;


public  class UsuarioResponse  {
	private String cpfUsuario;
	private Date dataRegistro;
	private String senha;
	private String nome;
	private String email;
	private List<DespesasResponse> despesas; 
	private List<CategoriaResponse> categoria; 
	private List<ObjetivoFinanceiroResponse> objetivoFinanceiro; 
	private List<ReceitaResponse> receita; 



	public UsuarioResponse(Usuario obj) {
		this.cpfUsuario = obj.getCpfUsuario();
		this.dataRegistro = obj.getDataRegistro();
		this.senha = obj.getSenha();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.despesas = obj.getDespesas()
			.stream()
			.map(DespesasResponse::new)
			.toList();	
		this.categoria = obj.getCategoria()
			.stream()
			.map(CategoriaResponse::new)
			.toList();	
		this.objetivoFinanceiro = obj.getObjetivoFinanceiro()
			.stream()
			.map(ObjetivoFinanceiroResponse::new)
			.toList();	
		this.receita = obj.getReceita()
			.stream()
			.map(ReceitaResponse::new)
			.toList();	

	}

	
	public 	String getCpfUsuario () {
		return this.cpfUsuario;
	}

	public 	Date getDataRegistro () {
		return this.dataRegistro;
	}

	public 	String getSenha () {
		return this.senha;
	}

	public 	String getNome () {
		return this.nome;
	}

	public 	String getEmail () {
		return this.email;
	}

	public List<DespesasResponse> getDespesas () {
		return this.despesas;
	}
	public List<CategoriaResponse> getCategoria () {
		return this.categoria;
	}
	public List<ObjetivoFinanceiroResponse> getObjetivoFinanceiro () {
		return this.objetivoFinanceiro;
	}
	public List<ReceitaResponse> getReceita () {
		return this.receita;
	}
}
