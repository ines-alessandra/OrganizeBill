package br.edu.ufape.organizeBill.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.organizeBill.repository.ObjetivoFinanceiroRepository;
import br.edu.ufape.organizeBill.model.ObjetivoFinanceiro;

@Service
public class ObjetivoFinanceiroService implements ObjetivoFinanceiroServiceInterface {
	@Autowired
	ObjetivoFinanceiroRepository repository;

	public ObjetivoFinanceiroService(ObjetivoFinanceiroRepository repository) {
		this.repository = repository;
	}

	public ObjetivoFinanceiroService() {
	}
	
	public ObjetivoFinanceiroRepository getRepository() {
		return repository;
	}

	public void setDao(ObjetivoFinanceiroRepository repository) {
		this.repository = repository;
	}	

	public ObjetivoFinanceiro saveObjetivoFinanceiro(ObjetivoFinanceiro newInstance) {
		if (newInstance.getDataLimite().isEqual(newInstance.getDataCriacao())) {
            throw new IllegalArgumentException("A data limite não pode ser igual à data de início.");
        }

        if (newInstance.getDataLimite().isBefore(newInstance.getDataCriacao())) {
            throw new IllegalArgumentException("A data limite deve ser posterior à data de início.");
        }
		return repository.save(newInstance);
	}

	public ObjetivoFinanceiro updateObjetivoFinanceiro(ObjetivoFinanceiro transientObject) {
		return repository.save(transientObject);
	}

	public ObjetivoFinanceiro findObjetivoFinanceiroById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist ObjetivoFinanceiro with id = " + id));
	}

	public List<ObjetivoFinanceiro> getAllObjetivoFinanceiro(String cpf){
		return repository.findByUsuarioCpf(cpf);
	}

	public void deleteObjetivoFinanceiro(ObjetivoFinanceiro persistentObject){
		this.deleteObjetivoFinanceiro(persistentObject.getCodObjetivo());
		
	}
	
	public void deleteObjetivoFinanceiro(long id){
		ObjetivoFinanceiro obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist ObjetivoFinanceiro with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}