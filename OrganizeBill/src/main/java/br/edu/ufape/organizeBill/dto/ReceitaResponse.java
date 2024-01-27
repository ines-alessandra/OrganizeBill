package br.edu.ufape.organizeBill.dto;

import java.util.*;
import java.math.*;
import br.edu.ufape.organizeBill.model.*;


public  class ReceitaResponse  {
	private long codReceita;
	private String descricao;
	private String tipoReceita;
	private Double valor;
	private Date data;



	public ReceitaResponse(Receita obj) {
		this.codReceita = obj.getCodReceita();
		this.descricao = obj.getDescricao();
		this.tipoReceita = obj.getTipoReceita();
		this.valor = obj.getValor();
		this.data = obj.getData();

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
