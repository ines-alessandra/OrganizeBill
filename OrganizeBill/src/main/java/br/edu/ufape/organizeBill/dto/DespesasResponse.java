package br.edu.ufape.organizeBill.dto;

import java.time.LocalDate;
import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public  class DespesasResponse  {
	private long codDespesa;
	private String descricao;
	private Double valor;
	private LocalDate data;
	private CategoriaResponse categoria; 
	private boolean fixo;
	private UsuarioResponse usuario;




	public DespesasResponse(Despesas obj) {
		this.codDespesa = obj.getCodDespesa();
		this.descricao = obj.getDescricao();
		this.valor = obj.getValor();
		this.data = obj.getData();
		this.categoria = new CategoriaResponse(obj.getCategoria());
		this.fixo = obj.isFixo();
		this.usuario = new UsuarioResponse(obj.getUsuario());
	}

	

}
