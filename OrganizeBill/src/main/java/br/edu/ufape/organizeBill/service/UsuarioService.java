package br.edu.ufape.organizeBill.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.organizeBill.repository.UsuarioRepository;
import br.edu.ufape.organizeBill.exception.ObjectNotFoundException;
import br.edu.ufape.organizeBill.model.Usuario;

@Service
public class UsuarioService implements UsuarioServiceInterface {
	@Autowired
	UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	public UsuarioService() {
	}
	
	public UsuarioRepository getRepository() {
		return repository;
	}

	public void setDao(UsuarioRepository repository) {
		this.repository = repository;
	}	

	public Usuario saveUsuario(Usuario newInstance) {
		return repository.save(newInstance);
	}

	public Usuario updateUsuario(Usuario transientObject) {
		return repository.save(transientObject);
	}

	public Usuario login(String email, String password)  {
		Usuario usuario = findUsuarioByEmail(email);
		String senha = usuario.getSenha();
		if(!password.equals(senha)){
			throw new RuntimeException("Senha incorreta para o login desse email");
		}
		return usuario;

	}

	public Usuario findUsuarioByEmail(String email) {
		try {
			return repository.findByEmail(email);
		}catch (RuntimeException e){
			throw new ObjectNotFoundException("Usuario");
		}
	}

	public Usuario findUsuarioByCpf(String cpf) {
		try {
			return repository.findByCpf(cpf);
		}catch (RuntimeException e){
	        throw new ObjectNotFoundException("Usuario");
		}
	}
	



	public List<Usuario> getAllUsuario(){
		return repository.findAll();
	}

	public void deleteUsuario(Usuario persistentObject){
		this.deleteUsuario(persistentObject.getCpf());
		
	}
	
	public void deleteUsuario(String cpf){
		try {
			Usuario obj = repository.findByCpf(cpf);
			repository.delete(obj);
		}catch (RuntimeException e){
			throw new RuntimeException("It doesn't exist Usuario with cpf = " + cpf);
		}		
	}	
	
	
	
}