package br.edu.ufape.organizeBill.dto;

import java.util.*;
import java.math.*;
import br.edu.ufape.organizeBill.model.*;


public  class ReceitaRequest  {
	private long codReceita;
	private String descricao;
	private String tipoReceita;
	private Double valor;
	private Date data;


	public Receita toReceita() {
		Receita obj = new Receita();
		fillReceita(obj);
		return obj;
	}
	protected void fillReceita(Receita obj) {
		obj.setCodReceita(getCodReceita());
		obj.setDescricao(getDescricao());
		obj.setTipoReceita(getTipoReceita());
		obj.setValor(getValor());
		obj.setData(getData());
	}

	public ReceitaRequest () {
		
	}
	
	public 	long getCodReceita () {
		return this.codReceita;
	}

	public 	String getDescricao () {
		return this.descricao;
	}

	public 	String getTipoReceita () {
		return this.tipoReceita;
	}

	public 	Double getValor () {
		return this.valor;
	}

	public 	Date getData () {
		return this.data;
	}

}
