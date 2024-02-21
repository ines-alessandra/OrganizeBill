package br.edu.ufape.organizeBill.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.organizeBill.model.Categoria;
import br.edu.ufape.organizeBill.model.Despesas;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long> {

	List<Despesas> findByCategoriaOrderByData(Categoria categoria);
	List<Despesas> findDespesasByDataBetweenAndUsuarioCpfOrderByData(LocalDate startDate,LocalDate endDate, String cpf);
    List<Despesas> findDespesasByDataBetweenAndUsuarioCpfAndFixoIsTrueOrderByData(LocalDate startDate,LocalDate endDate, String cpf);
    List<Despesas> findDespesasByDataBetweenAndCategoriaCodCategoriaOrderByData(LocalDate startDate,LocalDate endDate, long codCategoria);
	
}