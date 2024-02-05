package br.edu.ufape.organizeBill.dto;

import java.time.LocalDate;
import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public  class DespesasRequest  {
	private long codDespesa;
	private String descricao;
	private Double valor;
	private LocalDate data;
	private CategoriaRequest categoria; 
	private boolean fixo;



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
		obj.setFixo(isFixo());
	}


}
