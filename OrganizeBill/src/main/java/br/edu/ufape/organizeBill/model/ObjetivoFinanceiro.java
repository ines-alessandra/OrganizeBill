package br.edu.ufape.organizeBill.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public  class ObjetivoFinanceiro  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codObjetivo;
	private String nome;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCriacao;
	private Double valorTransitorio;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLimite;
	private Double valorMeta;
	private Double valor;
	private String descricao;
	@OneToOne
	private Categoria categoria;

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	
}