package br.edu.ufape.organizeBill.dto;

import java.time.LocalDate;
import java.util.*;
import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public  class UsuarioRequest  {
	private String cpf;
	private LocalDate dataRegistro;
	private String senha;
	private String nome;
	private String email;
	private List<DespesasRequest> despesas; 
	private List<CategoriaRequest> categoria; 
	private List<ObjetivoFinanceiroRequest> objetivoFinanceiro; 
	private List<ReceitaRequest> receita; 


	public Usuario toUsuario() {
		Usuario obj = new Usuario();
		fillUsuario(obj);
		return obj;
	}
	protected void fillUsuario(Usuario obj) {
		obj.setCpf(getCpf());
		obj.setDataRegistro(getDataRegistro());
		obj.setSenha(getSenha());
		obj.setNome(getNome());
		obj.setEmail(getEmail());
		if(getDespesas() != null)
			obj.setDespesas(getDespesas()
				.stream()
				.map(a -> a.toDespesas())
				.toList());	
		if(getCategoria() != null)
			obj.setCategoria(getCategoria()
				.stream()
				.map(a -> a.toCategoria())
				.toList());
		if(getObjetivoFinanceiro() != null)
			obj.setObjetivoFinanceiro(getObjetivoFinanceiro()
				.stream()
				.map(a -> a.toObjetivoFinanceiro())
				.toList());	
		if(getReceita()!= null)
			obj.setReceita(getReceita()
				.stream()
				.map(a -> a.toReceita())
				.toList());	
	}

	

}
