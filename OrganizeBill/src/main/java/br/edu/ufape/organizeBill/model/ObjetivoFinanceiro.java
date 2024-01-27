package br.edu.ufape.organizeBill.model;

import java.util.*;
import jakarta.persistence.*;
import java.math.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class ObjetivoFinanceiro  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String dataCriacao;
	private long codObjetivo;
	private Double valorTransitorio;
	private Date dataLimite;
	private Double valorMeta;
	private Double valor;
	private String descricao;

	public ObjetivoFinanceiro () {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public 	String getDataCriacao () {
		return this.dataCriacao;
	}
	
	public void setDataCriacao (String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public 	long getCodObjetivo () {
		return this.codObjetivo;
	}
	
	public void setCodObjetivo (long codObjetivo) {
		this.codObjetivo = codObjetivo;
	}
	
	public 	Double getValorTransitorio () {
		return this.valorTransitorio;
	}
	
	public void setValorTransitorio (Double valorTransitorio) {
		this.valorTransitorio = valorTransitorio;
	}
	
	public 	Date getDataLimite () {
		return this.dataLimite;
	}
	
	public void setDataLimite (Date dataLimite) {
		this.dataLimite = dataLimite;
	}
	
	public 	Double getValorMeta () {
		return this.valorMeta;
	}
	
	public void setValorMeta (Double valorMeta) {
		this.valorMeta = valorMeta;
	}
	
	public 	Double getValor () {
		return this.valor;
	}
	
	public void setValor (Double valor) {
		this.valor = valor;
	}
	
	public 	String getDescricao () {
		return this.descricao;
	}
	
	public void setDescricao (String descricao) {
		this.descricao = descricao;
	}
	
}