package br.edu.ufape.organizeBill.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDate dataCriacao;
	private Double valorTransitorio;
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDate dataLimite;
	private Double valorMeta;
	private Double valor;
	private String descricao;


	
}