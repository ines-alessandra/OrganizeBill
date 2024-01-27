package br.edu.ufape.organizeBill.service;

import java.util.List;

import br.edu.ufape.organizeBill.model.Despesas;

public interface DespesasServiceInterface {
	Despesas saveDespesas(Despesas o);
	Despesas findDespesasById(long id);
	Despesas updateDespesas(Despesas u);
	void deleteDespesas(Despesas u);
	void deleteDespesas(long id);
	List<Despesas> getAllDespesas();
    
    

    
}