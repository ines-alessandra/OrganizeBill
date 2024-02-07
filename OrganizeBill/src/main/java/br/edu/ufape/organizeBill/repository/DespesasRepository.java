package br.edu.ufape.organizeBill.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.organizeBill.model.Categoria;
import br.edu.ufape.organizeBill.model.Despesas;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long> {

	List<Despesas> findByCategoria(Categoria categoria);
	List<Despesas> findDespesasByDataBetweenAndUsuarioCpf(LocalDate startDate,LocalDate endDate, String cpf);
    List<Despesas> findDespesasByDataBetweenAndUsuarioCpfAndFixoIsTrue(LocalDate startDate,LocalDate endDate, String cpf);
    List<Despesas> findDespesasByDataBetweenAndCategoriaCodCategoria(LocalDate startDate,LocalDate endDate, long codCategoria);
	
}