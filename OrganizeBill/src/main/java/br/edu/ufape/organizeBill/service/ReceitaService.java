package br.edu.ufape.organizeBill.service;

import java.time.LocalDate;
import java.util.List;

import br.edu.ufape.organizeBill.exception.ObjectNotFoundException;
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
		this.deleteReceita(persistentObject.getCodReceita());
		
	}
	
	public void deleteReceita(long id){
		Receita obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Receita with id = " + id));
		repository.delete(obj);
	}

	public List<Receita> getReceitaByData(String cpf, String data, boolean fixo) {
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
			return repository.findReceitasByDataBetweenAndUsuarioCpfAndFixoIsTrue (inicio, termino,cpf);
		else
			return repository.findReceitasByDataBetweenAndUsuarioCpf (inicio, termino,cpf);


	}



	
	
	
}