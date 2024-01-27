package br.edu.ufape.organizeBill.dto;

import java.util.*;
import java.math.*;
import br.edu.ufape.organizeBill.model.*;


public  class DespesasRequest  {
	private long codDespesa;
	private String descricao;
	private Double valor;
	private Date data;
	private CategoriaRequest categoria; 


	public Despesas toDespesas() {
		Despesas obj = new Despesas();
		fillDespesas(obj);
		return obj;
	}
	protected void fillDespesas(Despesas obj) {
		obj.setCodDespesa(getCodDespesa());
		obj.setDescricao(getDescricao());
		obj.setValor(getValor());
		obj.setData(getData());
		obj.setCategoria(getCategoria().toCategoria());	
	}

	public DespesasRequest () {
		
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

	public CategoriaRequest getCategoria () {
		return this.categoria;
	}
}
