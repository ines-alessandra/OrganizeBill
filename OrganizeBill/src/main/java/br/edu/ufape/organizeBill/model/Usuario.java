package br.edu.ufape.organizeBill.model;

import java.util.*;
import jakarta.persistence.*;
import java.math.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class Usuario  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String cpfUsuario;
	private Date dataRegistro;
	private String senha;
	private String nome;
	private String email;
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Despesas> despesas; 
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Categoria> categoria; 
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<ObjetivoFinanceiro> objetivoFinanceiro; 
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Receita> receita; 

	public Usuario () {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public 	String getCpfUsuario () {
		return this.cpfUsuario;
	}
	
	public void setCpfUsuario (String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
	
	public 	Date getDataRegistro () {
		return this.dataRegistro;
	}
	
	public void setDataRegistro (Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	public 	String getSenha () {
		return this.senha;
	}
	
	public void setSenha (String senha) {
		this.senha = senha;
	}
	
	public 	String getNome () {
		return this.nome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public 	String getEmail () {
		return this.email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public  List<Despesas>  getDespesas () {
		return this.despesas;
	}
	
	public void setDespesas ( List<Despesas>  despesas) {
		this.despesas = despesas;
	}
	public  List<Categoria>  getCategoria () {
		return this.categoria;
	}
	
	public void setCategoria ( List<Categoria>  categoria) {
		this.categoria = categoria;
	}
	public  List<ObjetivoFinanceiro>  getObjetivoFinanceiro () {
		return this.objetivoFinanceiro;
	}
	
	public void setObjetivoFinanceiro ( List<ObjetivoFinanceiro>  objetivoFinanceiro) {
		this.objetivoFinanceiro = objetivoFinanceiro;
	}
	public  List<Receita>  getReceita () {
		return this.receita;
	}
	
	public void setReceita ( List<Receita>  receita) {
		this.receita = receita;
	}
}