package br.edu.ufape.organizeBill.dto;

import java.time.LocalDate;
import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public  class CategoriaRequest  {
	private long codCategoria;
	private String nome;
	private String descricao;
	private Double gastoMedio;
	private LocalDate dataMensal;
	private UsuarioRequest usuario;


	public Categoria toCategoria() {
		Categoria obj = new Categoria();
		fillCategoria(obj);
		return obj;
	}
	protected void fillCategoria(Categoria obj) {
		obj.setCodCategoria(getCodCategoria());
		obj.setNome(getNome());
		obj.setDescricao(getDescricao());
		obj.setGastoMedio(getGastoMedio());
		obj.setDataMensal(getDataMensal());
		obj.setUsuario(getUsuario().toUsuario());
	}


}
