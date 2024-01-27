package br.edu.ufape.organizeBill.model;

import java.util.*;
import jakarta.persistence.*;
import java.math.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class Receita  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long codReceita;
	private String descricao;
	private String tipoReceita;
	private Double valor;
	private Date data;

	public Receita () {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public 	long getCodReceita () {
		return this.codReceita;
	}
	
	public void setCodReceita (long codReceita) {
		this.codReceita = codReceita;
	}
	
	public 	String getDescricao () {
		return this.descricao;
	}
	
	public void setDescricao (String descricao) {
		this.descricao = descricao;
	}
	
	public 	String getTipoReceita () {
		return this.tipoReceita;
	}
	
	public void setTipoReceita (String tipoReceita) {
		this.tipoReceita = tipoReceita;
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
	
}