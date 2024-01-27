package br.edu.ufape.organizeBill.dto;

import java.util.*;
import java.math.*;
import br.edu.ufape.organizeBill.model.*;


public  class DespesasResponse  {
	private long codDespesa;
	private String descricao;
	private Double valor;
	private Date data;
	private CategoriaResponse categoria; 



	public DespesasResponse(Despesas obj) {
		this.codDespesa = obj.getCodDespesa();
		this.descricao = obj.getDescricao();
		this.valor = obj.getValor();
		this.data = obj.getData();
		this.categoria = new CategoriaResponse(obj.getCategoria());
	}

	
	public 	long getCodDespesa () {
		return this.codDespesa;
	}

	public 	String getDescricao () {
		return this.descricao;
	}

	public 	Double getValor () {
		return this.valor;
	}

	public 	Date getData () {
		return this.data;
	}

	public CategoriaResponse getCategoria () {
		return this.categoria;
	}
}
