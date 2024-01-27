package br.edu.ufape.organizeBill.service;

import java.util.List;

import br.edu.ufape.organizeBill.model.Categoria;

public interface CategoriaServiceInterface {
	Categoria saveCategoria(Categoria o);
	Categoria findCategoriaById(long id);
	Categoria updateCategoria(Categoria u);
	void deleteCategoria(Categoria u);
	void deleteCategoria(long id);
	List<Categoria> getAllCategoria();
    
    

    
}