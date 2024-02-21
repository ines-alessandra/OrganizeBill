package br.edu.ufape.organizeBill.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.edu.ufape.organizeBill.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

	List<Receita> findReceitasByDataBetweenAndUsuarioCpfOrderByDataDesc(LocalDate startDate,LocalDate endDate, String cpf);

    List<Receita> findReceitasByDataBetweenAndUsuarioCpfAndFixoIsTrueOrderByDataDesc (LocalDate startDate,LocalDate endDate, String cpf);

    @Query(value = """
    WITH meses AS (
        SELECT DATE_TRUNC('month', data) AS mes
        FROM receita
        WHERE usuario_cpf = :usuarioCpf AND data BETWEEN :startDate AND :endDate
        UNION
        SELECT DATE_TRUNC('month', data)
        FROM despesas
        WHERE usuario_cpf = :usuarioCpf AND data BETWEEN :startDate AND :endDate
    ),
    total_receitas AS (
        SELECT DATE_TRUNC('month', data) AS mes, SUM(valor) AS total_receita
        FROM receita
        WHERE usuario_cpf = :usuarioCpf AND data BETWEEN :startDate AND :endDate
        GROUP BY DATE_TRUNC('month', data)
    ),
    total_despesas AS (
        SELECT DATE_TRUNC('month', data) AS mes, SUM(valor) AS total_despesa
        FROM despesas
        WHERE usuario_cpf = :usuarioCpf AND data BETWEEN :startDate AND :endDate
        GROUP BY DATE_TRUNC('month', data)
    )
    SELECT 
        TO_CHAR(meses.mes, 'YYYY-MM') AS mes,
        COALESCE(total_receitas.total_receita, 0) AS total_receitas,
        COALESCE(total_despesas.total_despesa, 0) AS total_despesas
    FROM meses
    LEFT JOIN total_receitas ON meses.mes = total_receitas.mes
    LEFT JOIN total_despesas ON meses.mes = total_despesas.mes
    ORDER BY meses.mes ASC
    """, nativeQuery = true)
    List<Object[]> findResumoReceitasDespesasParaUsuarioEIntervalo(@Param("usuarioCpf") String usuarioCpf, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
}