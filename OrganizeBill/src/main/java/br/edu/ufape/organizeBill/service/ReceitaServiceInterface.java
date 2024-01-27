package br.edu.ufape.organizeBill.service;

import java.util.List;

import br.edu.ufape.organizeBill.model.Receita;

public interface ReceitaServiceInterface {
	Receita saveReceita(Receita o);
	Receita findReceitaById(long id);
	Receita updateReceita(Receita u);
	void deleteReceita(Receita u);
	void deleteReceita(long id);
	List<Receita> getAllReceita();
    
    

    
}