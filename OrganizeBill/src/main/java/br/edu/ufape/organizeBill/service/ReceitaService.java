package br.edu.ufape.organizeBill.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.organizeBill.repository.ReceitaRepository;
import br.edu.ufape.organizeBill.model.Receita;

@Service
public class ReceitaService implements ReceitaServiceInterface {
	@Autowired
	ReceitaRepository repository;

	public ReceitaService(ReceitaRepository repository) {
		this.repository = repository;
	}

	public ReceitaService() {
	}
	
	public ReceitaRepository getRepository() {
		return repository;
	}

	public void setDao(ReceitaRepository repository) {
		this.repository = repository;
	}	

	public Receita saveReceita(Receita newInstance) {
		return repository.save(newInstance);
	}

	public Receita updateReceita(Receita transientObject) {
		return repository.save(transientObject);
	}

	public Receita findReceitaById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Receita with id = " + id));
	}

	public List<Receita> getAllReceita(){
		return repository.findAll();
	}

	public void deleteReceita(Receita persistentObject){
		this.deleteReceita(persistentObject.getId());
		
	}
	
	public void deleteReceita(long id){
		Receita obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Receita with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}