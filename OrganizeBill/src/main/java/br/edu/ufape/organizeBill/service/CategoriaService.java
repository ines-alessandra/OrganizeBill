package br.edu.ufape.organizeBill.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.organizeBill.repository.CategoriaRepository;
import br.edu.ufape.organizeBill.model.Categoria;

@Service
public class CategoriaService implements CategoriaServiceInterface {
	@Autowired
	CategoriaRepository repository;

	public CategoriaService(CategoriaRepository repository) {
		this.repository = repository;
	}

	public CategoriaService() {
	}
	
	public CategoriaRepository getRepository() {
		return repository;
	}

	public void setDao(CategoriaRepository repository) {
		this.repository = repository;
	}	

	public Categoria saveCategoria(Categoria newInstance) {
		return repository.save(newInstance);
	}

	public Categoria updateCategoria(Categoria transientObject) {
		return repository.save(transientObject);
	}

	public Categoria findCategoriaById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Categoria with id = " + id));
	}

	public List<Categoria> getAllCategoria(){
		return repository.findAll();
	}

	public void deleteCategoria(Categoria persistentObject){
		this.deleteCategoria(persistentObject.getId());
		
	}
	
	public void deleteCategoria(long id){
		Categoria obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Categoria with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}