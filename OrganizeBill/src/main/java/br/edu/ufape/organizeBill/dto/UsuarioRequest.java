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

	}

	

}
