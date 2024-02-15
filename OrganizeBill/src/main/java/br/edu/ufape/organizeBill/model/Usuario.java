package br.edu.ufape.organizeBill.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Inheritance(strategy = InheritanceType.JOINED)@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public  class Usuario  {
	@Id
	@Column(name = "cpf", unique = true, nullable = false, length = 14)
	private String cpf;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataRegistro;
	private String senha;
	private String nome;
	private String email;



}