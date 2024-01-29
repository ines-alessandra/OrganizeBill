package br.edu.ufape.organizeBill.dto;

import java.util.*;
import java.math.*;
import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
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

	


}
