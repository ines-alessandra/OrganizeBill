package br.edu.ufape.organizeBill.dto;

import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public  class CategoriaResponse  {
	private long codCategoria;
	private String nome;
	private String descricao;
	private Double gastoMedio;
	private UsuarioResponse usuario;



	public CategoriaResponse(Categoria obj) {
		this.codCategoria = obj.getCodCategoria();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
		this.gastoMedio = obj.getGastoMedio();
		this.usuario = new UsuarioResponse(obj.getUsuario());

	}

	


}
