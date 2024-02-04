package br.edu.ufape.organizeBill.repository;

import java.util.List;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.organizeBill.model.Categoria;
import br.edu.ufape.organizeBill.model.Despesas;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long> {

	List<Despesas> findByCategoria(Categoria categoria);
	

}