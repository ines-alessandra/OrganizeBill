package br.edu.ufape.organizeBill.model;

import java.util.*;
import jakarta.persistence.*;
import java.math.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class Categoria  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long codCategoria;
	private String nome;
	private String descricao;
	private Double gastoMedio;
	private Date dataMensal;

	public Categoria () {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public 	long getCodCategoria () {
		return this.codCategoria;
	}
	
	public void setCodCategoria (long codCategoria) {
		this.codCategoria = codCategoria;
	}
	
	public 	String getNome () {
		return this.nome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public 	String getDescricao () {
		return this.descricao;
	}
	
	public void setDescricao (String descricao) {
		this.descricao = descricao;
	}
	
	public 	Double getGastoMedio () {
		return this.gastoMedio;
	}
	
	public void setGastoMedio (Double gastoMedio) {
		this.gastoMedio = gastoMedio;
	}
	
	public 	Date getDataMensal () {
		return this.dataMensal;
	}
	
	public void setDataMensal (Date dataMensal) {
		this.dataMensal = dataMensal;
	}
	
}