package br.edu.ufape.organizeBill.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ufape.organizeBill.model.Categoria;
import br.edu.ufape.organizeBill.model.Despesas;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long> {

	List<Despesas> findByCategoriaOrderByData(Categoria categoria);
	List<Despesas> findDespesasByDataBetweenAndUsuarioCpfOrderByData(LocalDate startDate,LocalDate endDate, String cpf);
    List<Despesas> findDespesasByDataBetweenAndUsuarioCpfAndFixoIsTrueOrderByData(LocalDate startDate,LocalDate endDate, String cpf);
    List<Despesas> findDespesasByDataBetweenAndCategoriaCodCategoriaOrderByData(LocalDate startDate,LocalDate endDate, long codCategoria);

    @Query(value = """
    WITH meses AS (
        SELECT DATE_TRUNC('month', data) AS mes
        FROM despesas
        WHERE usuario_cpf = :usuarioCpf AND data BETWEEN :startDate AND :endDate
        GROUP BY DATE_TRUNC('month', data)
    ),
    total_despesas AS (
        SELECT DATE_TRUNC('month', data) AS mes, SUM(valor) AS total_despesas
        FROM despesas
        WHERE usuario_cpf = :usuarioCpf AND data BETWEEN :startDate AND :endDate
        GROUP BY DATE_TRUNC('month', data)
    )
    SELECT
        TO_CHAR(meses.mes, 'YYYY-MM') AS mes,
        total_despesas.total_despesas AS total_despesas
    FROM meses
    LEFT JOIN total_despesas ON meses.mes = total_despesas.mes
    ORDER BY meses.mes ASC
    """, nativeQuery = true)
    List<Object[]> findResumoDespesasParaUsuarioEIntervalo(@Param("usuarioCpf") String usuarioCpf, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}