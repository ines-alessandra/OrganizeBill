package br.edu.ufape.organizeBill.dto;

import java.util.*;
import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public  class ObjetivoFinanceiroRequest  {
	private Date dataCriacao;
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


}
