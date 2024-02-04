package br.edu.ufape.organizeBill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufape.organizeBill.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	

}