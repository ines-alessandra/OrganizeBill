package br.edu.ufape.organizeBill.service;

import java.util.List;

import br.edu.ufape.organizeBill.model.ObjetivoFinanceiro;

public interface ObjetivoFinanceiroServiceInterface {
	ObjetivoFinanceiro saveObjetivoFinanceiro(ObjetivoFinanceiro o);
	ObjetivoFinanceiro findObjetivoFinanceiroById(long id);
	ObjetivoFinanceiro updateObjetivoFinanceiro(ObjetivoFinanceiro u);
	void deleteObjetivoFinanceiro(ObjetivoFinanceiro u);
	void deleteObjetivoFinanceiro(long id);
	List<ObjetivoFinanceiro> getAllObjetivoFinanceiro(String cpf);
    
    

    
}