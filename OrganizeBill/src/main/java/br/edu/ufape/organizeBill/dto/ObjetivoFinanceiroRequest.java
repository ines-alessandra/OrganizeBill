package br.edu.ufape.organizeBill.dto;

import java.util.*;
import java.math.*;
import br.edu.ufape.organizeBill.model.*;


public  class ObjetivoFinanceiroRequest  {
	private String dataCriacao;
	private long codObjetivo;
	private Double valorTransitorio;
	private Date dataLimite;
	private Double valorMeta;
	private Double valor;
	private String descricao;


	public ObjetivoFinanceiro toObjetivoFinanceiro() {
		ObjetivoFinanceiro obj = new ObjetivoFinanceiro();
		fillObjetivoFinanceiro(obj);
		return obj;
	}
	protected void fillObjetivoFinanceiro(ObjetivoFinanceiro obj) {
		obj.setDataCriacao(getDataCriacao());
		obj.setCodObjetivo(getCodObjetivo());
		obj.setValorTransitorio(getValorTransitorio());
		obj.setDataLimite(getDataLimite());
		obj.setValorMeta(getValorMeta());
		obj.setValor(getValor());
		obj.setDescricao(getDescricao());
	}

	public ObjetivoFinanceiroRequest () {
		
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
