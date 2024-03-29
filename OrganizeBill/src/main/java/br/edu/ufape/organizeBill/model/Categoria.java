package br.edu.ufape.organizeBill.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public  class Categoria  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codCategoria;
	private String nome;
	private String descricao;
	private Double gastoMedio;

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
}