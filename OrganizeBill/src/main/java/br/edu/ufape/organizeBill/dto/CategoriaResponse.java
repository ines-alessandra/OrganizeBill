package br.edu.ufape.organizeBill.dto;

import java.util.*;
import java.math.*;
import br.edu.ufape.organizeBill.model.*;


public  class CategoriaResponse  {
	private long codCategoria;
	private String nome;
	private String descricao;
	private Double gastoMedio;
	private Date dataMensal;



	public CategoriaResponse(Categoria obj) {
		this.codCategoria = obj.getCodCategoria();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
		this.gastoMedio = obj.getGastoMedio();
		this.dataMensal = obj.getDataMensal();

	}

	
	public 	long getCodCategoria () {
		return this.codCategoria;
	}

	public 	String getNome () {
		return this.nome;
	}

	public 	String getDescricao () {
		return this.descricao;
	}

	public 	Double getGastoMedio () {
		return this.gastoMedio;
	}

	public 	Date getDataMensal () {
		return this.dataMensal;
	}

}
