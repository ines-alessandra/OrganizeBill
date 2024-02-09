package br.edu.ufape.organizeBill.dto;

import java.time.LocalDate;
import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public  class ReceitaRequest  {
	private long codReceita;
	private String descricao;
	private Double valor;
	private LocalDate data;
	private boolean fixo;
	private UsuarioRequest usuario;



	public Receita toReceita() {
		Receita obj = new Receita();
		fillReceita(obj);
		return obj;
	}
	protected void fillReceita(Receita obj) {
		obj.setCodReceita(getCodReceita());
		obj.setDescricao(getDescricao());
		obj.setValor(getValor());
		obj.setData(getData());
		obj.setFixo(isFixo());
		obj.setUsuario(getUsuario().toUsuario());
	}



}
