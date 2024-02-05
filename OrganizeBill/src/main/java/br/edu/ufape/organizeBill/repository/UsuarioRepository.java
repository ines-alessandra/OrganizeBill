package br.edu.ufape.organizeBill.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ufape.organizeBill.model.Receita;
import br.edu.ufape.organizeBill.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	Usuario findByCpf(String cpf);
	
	@Query("SELECT t.receita FROM Usuario t JOIN t.receita WHERE t.cpf = :cpf and t.receita.date BETWEEN :inicio and :termino")
	List<Receita> findReceitaByPeriod(String cpf, LocalDate inicio, LocalDate termino);
	
	@Query("SELECT t.receita FROM Usuario t JOIN t.receita WHERE t.cpf = :cpf and t.receita.fixo = true and t.receita.date BETWEEN :inicio and :termino")
	List<Receita> findReceitaByPeriodFixo(String cpf, LocalDate inicio, LocalDate termino);

	@Query("SELECT t.receita FROM Usuario t JOIN t.receita WHERE t.cpf = :cpf and t.receita.fixo = false and t.receita.date BETWEEN :inicio and :termino")
	List<Receita> findReceitaByPeriodNaoFixo(String cpf, LocalDate inicio, LocalDate termino);

}