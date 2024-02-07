package br.edu.ufape.organizeBill.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.organizeBill.repository.DespesasRepository;
import br.edu.ufape.organizeBill.exception.ObjectNotFoundException;
import br.edu.ufape.organizeBill.model.Categoria;
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
	
	public List<Despesas> findByCategoria(Categoria categoria){
		List<Despesas> despesas = repository.findByCategoria(categoria);
		if(despesas.isEmpty()) {
			throw new ObjectNotFoundException("Despesas");
		}
		return despesas;
	} 

	public void deleteDespesas(Despesas persistentObject){
		this.deleteDespesas(persistentObject.getCodDespesa());
		
	}
	
	public void deleteDespesas(long id){
		Despesas obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Despesas with id = " + id));
		repository.delete(obj);
	}	
	
	public List<Despesas> getDespesasByData(String cpf, String data, boolean fixo) {
		LocalDate inicio;
		LocalDate termino;

		switch (data) {
			case "dia":
				inicio = LocalDate.now();
				termino = inicio;
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

		if (fixo)
			return repository.findDespesasByDataBetweenAndUsuarioCpfAndFixoIsTrue (inicio, termino,cpf);
		else
			return repository.findDespesasByDataBetweenAndUsuarioCpf (inicio, termino,cpf);
	}
	
	
}