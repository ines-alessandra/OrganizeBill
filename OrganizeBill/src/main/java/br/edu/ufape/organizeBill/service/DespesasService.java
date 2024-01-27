package br.edu.ufape.organizeBill.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.organizeBill.repository.DespesasRepository;
import br.edu.ufape.organizeBill.model.Despesas;

@Service
public class DespesasService implements DespesasServiceInterface {
	@Autowired
	DespesasRepository repository;

	public DespesasService(DespesasRepository repository) {
		this.repository = repository;
	}

	public DespesasService() {
	}
	
	public DespesasRepository getRepository() {
		return repository;
	}

	public void setDao(DespesasRepository repository) {
		this.repository = repository;
	}	

	public Despesas saveDespesas(Despesas newInstance) {
		return repository.save(newInstance);
	}

	public Despesas updateDespesas(Despesas transientObject) {
		return repository.save(transientObject);
	}

	public Despesas findDespesasById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Despesas with id = " + id));
	}

	public List<Despesas> getAllDespesas(){
		return repository.findAll();
	}

	public void deleteDespesas(Despesas persistentObject){
		this.deleteDespesas(persistentObject.getId());
		
	}
	
	public void deleteDespesas(long id){
		Despesas obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Despesas with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}