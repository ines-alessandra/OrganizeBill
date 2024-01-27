package br.edu.ufape.organizeBill.dto;

import java.util.*;
import java.math.*;
import br.edu.ufape.organizeBill.model.*;


public  class CategoriaRequest  {
	private long codCategoria;
	private String nome;
	private String descricao;
	private Double gastoMedio;
	private Date dataMensal;


	public Categoria toCategoria() {
		Categoria obj = new Categoria();
		fillCategoria(obj);
		return obj;
	}
	protected void fillCategoria(Categoria obj) {
		obj.setCodCategoria(getCodCategoria());
		obj.setNome(getNome());
		obj.setDescricao(getDescricao());
		obj.setGastoMedio(getGastoMedio());
		obj.setDataMensal(getDataMensal());
	}

	public CategoriaRequest () {
		
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
