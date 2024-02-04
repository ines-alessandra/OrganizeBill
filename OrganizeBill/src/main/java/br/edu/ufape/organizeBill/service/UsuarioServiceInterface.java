package br.edu.ufape.organizeBill.service;

import java.util.List;

import br.edu.ufape.organizeBill.model.Usuario;

public interface UsuarioServiceInterface {
	Usuario saveUsuario(Usuario o);
	Usuario findUsuarioByCpf(String cpf);
	Usuario updateUsuario(Usuario u);
	void deleteUsuario(Usuario u);
	void deleteUsuario(String cpf);
	List<Usuario> getAllUsuario();
    
    

    
}