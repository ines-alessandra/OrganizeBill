package br.edu.ufape.organizeBill.model;

import java.util.*;
import jakarta.persistence.*;
import java.math.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class Despesas  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long codDespesa;
	private String descricao;
	private Double valor;
	private Date data;
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria; 

	public Despesas () {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public 	long getCodDespesa () {
		return this.codDespesa;
	}
	
	public void setCodDespesa (long codDespesa) {
		this.codDespesa = codDespesa;
	}
	
	public 	String getDescricao () {
		return this.descricao;
	}
	
	public void setDescricao (String descricao) {
		this.descricao = descricao;
	}
	
	public 	Double getValor () {
		return this.valor;
	}
	
	public void setValor (Double valor) {
		this.valor = valor;
	}
	
	public 	Date getData () {
		return this.data;
	}
	
	public void setData (Date data) {
		this.data = data;
	}
	
	public Categoria getCategoria () {
		return this.categoria;
	}
	
	public void setCategoria (Categoria categoria) {
		this.categoria = categoria;
	}
}