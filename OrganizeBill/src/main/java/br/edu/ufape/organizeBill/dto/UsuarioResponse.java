package br.edu.ufape.organizeBill.dto;

import java.time.LocalDate;
import br.edu.ufape.organizeBill.model.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public  class UsuarioResponse  {
	private String cpf;
	private LocalDate dataRegistro;
	private String senha;
	private String nome;
	private String email;




	public UsuarioResponse(Usuario obj) {
		this.cpf = obj.getCpf();
		this.dataRegistro = obj.getDataRegistro();
		this.senha = obj.getSenha();
		this.nome = obj.getNome();
		this.email = obj.getEmail();


	}
}
