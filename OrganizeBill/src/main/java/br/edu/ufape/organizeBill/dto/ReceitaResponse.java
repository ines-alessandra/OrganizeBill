package br.edu.ufape.organizeBill.dto;

import java.time.LocalDate;
import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public  class ReceitaResponse  {
	private long codReceita;
	private String descricao;
	private Double valor;
	private LocalDate data;
	private boolean fixo;
	private UsuarioResponse usuario;




	public ReceitaResponse(Receita obj) {
		this.codReceita = obj.getCodReceita();
		this.descricao = obj.getDescricao();
		this.valor = obj.getValor();
		this.data = obj.getData();
		this.fixo = obj.isFixo();
		this.usuario = new UsuarioResponse(obj.getUsuario());

	}

	


}
