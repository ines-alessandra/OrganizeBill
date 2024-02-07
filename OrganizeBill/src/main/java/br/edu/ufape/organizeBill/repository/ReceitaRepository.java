package br.edu.ufape.organizeBill.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufape.organizeBill.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

	List<Receita> findReceitasByDataBetweenAndUsuarioCpf(LocalDate startDate,LocalDate endDate, String cpf);

    List<Receita> findReceitasByDataBetweenAndUsuarioCpfAndFixoIsTrue(LocalDate startDate,LocalDate endDate, String cpf);
	
}