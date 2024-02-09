package br.edu.ufape.organizeBill.dto;

import java.time.LocalDate;
import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public  class ObjetivoFinanceiroResponse  {
	private LocalDate dataCriacao;
	private String nome;
	private long codObjetivo;
	private Double valorTransitorio;
	private LocalDate dataLimite;
	private Double valorMeta;
	private Double valor;
	private String descricao;
	private UsuarioResponse usuario;
	private CategoriaResponse categoria;


	public ObjetivoFinanceiroResponse(ObjetivoFinanceiro obj) {
		this.dataCriacao = obj.getDataCriacao();
		this.codObjetivo = obj.getCodObjetivo();
		this.nome = obj.getNome();
		this.valorTransitorio = obj.getValorTransitorio();
		this.dataLimite = obj.getDataLimite();
		this.valorMeta = obj.getValorMeta();
		this.categoria = new CategoriaResponse(obj.getCategoria());
		this.valor = obj.getValor();
		this.descricao = obj.getDescricao();
		this.usuario = new UsuarioResponse(obj.getUsuario());

	}

	


}
