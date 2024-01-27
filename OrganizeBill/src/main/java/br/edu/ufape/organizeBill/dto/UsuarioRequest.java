package br.edu.ufape.organizeBill.dto;

import java.util.*;
import java.math.*;
import br.edu.ufape.organizeBill.model.*;


public  class UsuarioRequest  {
	private String cpfUsuario;
	private Date dataRegistro;
	private String senha;
	private String nome;
	private String email;
	private List<DespesasRequest> despesas; 
	private List<CategoriaRequest> categoria; 
	private List<ObjetivoFinanceiroRequest> objetivoFinanceiro; 
	private List<ReceitaRequest> receita; 


	public Usuario toUsuario() {
		Usuario obj = new Usuario();
		fillUsuario(obj);
		return obj;
	}
	protected void fillUsuario(Usuario obj) {
		obj.setCpfUsuario(getCpfUsuario());
		obj.setDataRegistro(getDataRegistro());
		obj.setSenha(getSenha());
		obj.setNome(getNome());
		obj.setEmail(getEmail());
		obj.setDespesas(getDespesas()
			.stream()
			.map(a -> a.toDespesas())
			.toList());	
		obj.setCategoria(getCategoria()
			.stream()
			.map(a -> a.toCategoria())
			.toList());	
		obj.setObjetivoFinanceiro(getObjetivoFinanceiro()
			.stream()
			.map(a -> a.toObjetivoFinanceiro())
			.toList());	
		obj.setReceita(getReceita()
			.stream()
			.map(a -> a.toReceita())
			.toList());	
	}

	public UsuarioRequest () {
		
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

	public List<DespesasRequest> getDespesas () {
		return this.despesas;
	}
	public List<CategoriaRequest> getCategoria () {
		return this.categoria;
	}
	public List<ObjetivoFinanceiroRequest> getObjetivoFinanceiro () {
		return this.objetivoFinanceiro;
	}
	public List<ReceitaRequest> getReceita () {
		return this.receita;
	}
}
