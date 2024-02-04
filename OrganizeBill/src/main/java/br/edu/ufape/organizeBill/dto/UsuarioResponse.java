package br.edu.ufape.organizeBill.dto;

import java.util.*;
import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public  class UsuarioResponse  {
	private String cpf;
	private Date dataRegistro;
	private String senha;
	private String nome;
	private String email;
	private List<DespesasResponse> despesas; 
	private List<CategoriaResponse> categoria; 
	private List<ObjetivoFinanceiroResponse> objetivoFinanceiro; 
	private List<ReceitaResponse> receita;



	public UsuarioResponse(Usuario obj) {
		this.cpf = obj.getCpf();
		this.dataRegistro = obj.getDataRegistro();
		this.senha = obj.getSenha();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		if(obj.getDespesas()!= null)
			this.despesas = obj.getDespesas()
				.stream()
				.map(DespesasResponse::new)
				.toList();	
		if(obj.getCategoria()!= null)
			this.categoria = obj.getCategoria()
				.stream()
				.map(CategoriaResponse::new)
				.toList();	
		if(obj.getObjetivoFinanceiro()!= null)
			this.objetivoFinanceiro = obj.getObjetivoFinanceiro()
				.stream()
				.map(ObjetivoFinanceiroResponse::new)
				.toList();	
		if(obj.getReceita()!= null)
			this.receita = obj.getReceita()
				.stream()
				.map(ReceitaResponse::new)
				.toList();	

	}
}
