package br.edu.ufape.organizeBill.dto;

import java.util.*;
import java.math.*;
import br.edu.ufape.organizeBill.model.*;


public  class ObjetivoFinanceiroResponse  {
	private String dataCriacao;
	private long codObjetivo;
	private Double valorTransitorio;
	private Date dataLimite;
	private Double valorMeta;
	private Double valor;
	private String descricao;



	public ObjetivoFinanceiroResponse(ObjetivoFinanceiro obj) {
		this.dataCriacao = obj.getDataCriacao();
		this.codObjetivo = obj.getCodObjetivo();
		this.valorTransitorio = obj.getValorTransitorio();
		this.dataLimite = obj.getDataLimite();
		this.valorMeta = obj.getValorMeta();
		this.valor = obj.getValor();
		this.descricao = obj.getDescricao();

	}

	
	public 	String getDataCriacao () {
		return this.dataCriacao;
	}

	public 	long getCodObjetivo () {
		return this.codObjetivo;
	}

	public 	Double getValorTransitorio () {
		return this.valorTransitorio;
	}

	public 	Date getDataLimite () {
		return this.dataLimite;
	}

	public 	Double getValorMeta () {
		return this.valorMeta;
	}

	public 	Double getValor () {
		return this.valor;
	}

	public 	String getDescricao () {
		return this.descricao;
	}

}
