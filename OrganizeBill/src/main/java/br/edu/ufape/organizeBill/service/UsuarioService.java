package br.edu.ufape.organizeBill.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.organizeBill.repository.UsuarioRepository;
import br.edu.ufape.organizeBill.exception.ObjectNotFoundException;
import br.edu.ufape.organizeBill.model.Receita;
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

	public Usuario findUsuarioByCpf(String cpf) {
		try {
			return repository.findByCpf(cpf);
		}catch (RuntimeException e){
	        throw new ObjectNotFoundException("Usuario");
		}
	}
	
	public List<Receita> getReceitaByData(String cpf, String data, String tipo) {
	    LocalDate inicio;
	    LocalDate termino;

	    switch (data) {
	        case "dia":
	            inicio = LocalDate.now();
	            termino = null;
	            break;
	        case "semana":
	            inicio = LocalDate.now();
	            termino = LocalDate.now().minusDays(7);
	            break;
	        case "mes":
	            inicio = LocalDate.now().withDayOfMonth(1);
	            termino = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
	            break;
	        case "ano":
	            inicio = LocalDate.now().withDayOfYear(1);
	            termino = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());
	            break;
	        default:
	            throw new IllegalArgumentException("Data inválida: " + data);
	    }

	    return switch (tipo) {
	        case "fixo" -> repository.findReceitaByPeriodFixo(cpf, inicio, termino);
	        case "normal" -> repository.findReceitaByPeriod(cpf, inicio, termino);
	        default -> throw new IllegalArgumentException("Tipo inválido: " + tipo);
	    };
	}


	public double calcularTotalReceitasData(String cpf, String data, String tipo) {
	    findUsuarioByCpf(cpf);
	    List<Receita> receitas = this.getReceitaByData(cpf, data , tipo);

	    if (receitas.isEmpty()) {
	        throw new ObjectNotFoundException("Receitas");
	    }

	    return receitas.stream()
	            .mapToDouble(Receita::getValor)
	            .sum();
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