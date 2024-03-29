package br.edu.ufape.organizeBill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufape.organizeBill.model.ObjetivoFinanceiro;

@Repository
public interface ObjetivoFinanceiroRepository extends JpaRepository<ObjetivoFinanceiro, Long> {

	List<ObjetivoFinanceiro> findByUsuarioCpf(String cpf);

}