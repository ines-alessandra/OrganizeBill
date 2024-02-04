package br.edu.ufape.organizeBill.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.util.Date;
import java.util.List;

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
	private Date dataRegistro;
	private String senha;
	private String nome;
	private String email;
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Despesas> despesas;
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Categoria> categoria; 
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<ObjetivoFinanceiro> objetivoFinanceiro; 
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Receita> receita; 


}