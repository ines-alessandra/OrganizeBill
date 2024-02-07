package br.edu.ufape.organizeBill.facade;

import java.util.List;

import br.edu.ufape.organizeBill.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.organizeBill.model.*;
import br.edu.ufape.organizeBill.service.*;

@Service
public class Facade {
	//Despesas--------------------------------------------------------------
	@Autowired
	private DespesasService  despesasService;
		
	public Despesas saveDespesas(Despesas newInstance) {
		return despesasService.saveDespesas(newInstance);
	}

	public Despesas updateDespesas(Despesas transientObject) {
		return despesasService.updateDespesas(transientObject);
	}

	public Despesas findDespesasById(long id) {
		return despesasService.findDespesasById(id);
	}

	public List<Despesas> getAllDespesas() {
		return despesasService.getAllDespesas();
	}
	
	public List<Despesas> findByCategoria(long CategoriaId) {
		Categoria categoria = findCategoriaById(CategoriaId);
		return despesasService.findByCategoria(categoria);
	}

	public void deleteDespesas(Despesas persistentObject) {
		despesasService.deleteDespesas(persistentObject);
	}

	public void deleteDespesas(long id) {
		despesasService.deleteDespesas(id);
	}
	
	public List<Despesas> getDespesasByData(String cpf, String data, boolean fixo) {
		return despesasService.getDespesasByData(cpf,data,fixo);
	}

	public double calcularTotalDespesasData(String cpf, String data, boolean fixo) {
		findUsuarioByCpf(cpf);
		List<Despesas> despesas = this.getDespesasByData(cpf, data , fixo);

		if (despesas.isEmpty()) {
			throw new ObjectNotFoundException("Despesas");
		}

		return despesas.stream()
				.mapToDouble(Despesas::getValor)
				.sum();
	}
	
	public List<Despesas> getDespesasByCategoriaData(long codCategoria, String data) {
		return despesasService.getDespesasByCategoriaData(codCategoria,data);
	}

	public double calcularTotalDespesasCategoriaData(long codCategoria, String data) {
		findCategoriaById(codCategoria);
		List<Despesas> despesas = this.getDespesasByCategoriaData(codCategoria, data);

		if (despesas.isEmpty()) {
			throw new ObjectNotFoundException("Despesas");
		}

		return despesas.stream()
				.mapToDouble(Despesas::getValor)
				.sum();
	}


	//Usuario--------------------------------------------------------------
	@Autowired
	private UsuarioService  usuarioService;
		
	public Usuario saveUsuario(Usuario newInstance) {
		return usuarioService.saveUsuario(newInstance);
	}

	public Usuario updateUsuario(Usuario transientObject) {
		return usuarioService.updateUsuario(transientObject);
	}

	public Usuario findUsuarioByCpf(String cpf) {
		return usuarioService.findUsuarioByCpf(cpf);
	}

	public List<Usuario> getAllUsuario() {
		return usuarioService.getAllUsuario();
	}

	public void deleteUsuario(Usuario persistentObject) {
		usuarioService.deleteUsuario(persistentObject);
	}

	public void deleteUsuario(String cpf) {
		usuarioService.deleteUsuario(cpf);
	}
	

	//Categoria--------------------------------------------------------------
	@Autowired
	private CategoriaService  categoriaService;
		
	public Categoria saveCategoria(Categoria newInstance) {
		return categoriaService.saveCategoria(newInstance);
	}

	public Categoria updateCategoria(Categoria transientObject) {
		return categoriaService.updateCategoria(transientObject);
	}

	public Categoria findCategoriaById(long id) {
		return categoriaService.findCategoriaById(id);
	}

	public List<Categoria> getAllCategoria(String cpf) {
		return categoriaService.getAllCategoria(cpf);
	}

	public void deleteCategoria(Categoria persistentObject) {
		categoriaService.deleteCategoria(persistentObject);
	}

	public void deleteCategoria(long id) {
		categoriaService.deleteCategoria(id);
	}
	

	//Receita--------------------------------------------------------------
	@Autowired
	private ReceitaService  receitaService;
		
	public Receita saveReceita(Receita newInstance) {
		return receitaService.saveReceita(newInstance);
	}

	public Receita updateReceita(Receita transientObject) {
		return receitaService.updateReceita(transientObject);
	}

	public Receita findReceitaById(long id) {
		return receitaService.findReceitaById(id);
	}

	public List<Receita> getAllReceita() {
		return receitaService.getAllReceita();
	}

	public void deleteReceita(Receita persistentObject) {
		receitaService.deleteReceita(persistentObject);
	}

	public void deleteReceita(long id) {
		receitaService.deleteReceita(id);
	}

	public List<Receita> getReceitaByData(String cpf, String data, boolean fixo) {
		return receitaService.getReceitaByData(cpf,data,fixo);
	}

	public double calcularTotalReceitasData(String cpf, String data, boolean fixo) {
		findUsuarioByCpf(cpf);
		List<Receita> receitas = this.getReceitaByData(cpf, data , fixo);

		if (receitas.isEmpty()) {
			throw new ObjectNotFoundException("Receitas");
		}

		return receitas.stream()
				.mapToDouble(Receita::getValor)
				.sum();
	}
	

	//ObjetivoFinanceiro--------------------------------------------------------------
	@Autowired
	private ObjetivoFinanceiroService  objetivoFinanceiroService;
		
	public ObjetivoFinanceiro saveObjetivoFinanceiro(ObjetivoFinanceiro newInstance) {
		return objetivoFinanceiroService.saveObjetivoFinanceiro(newInstance);
	}

	public ObjetivoFinanceiro updateObjetivoFinanceiro(ObjetivoFinanceiro transientObject) {
		return objetivoFinanceiroService.updateObjetivoFinanceiro(transientObject);
	}

	public ObjetivoFinanceiro findObjetivoFinanceiroById(long id) {
		return objetivoFinanceiroService.findObjetivoFinanceiroById(id);
	}

	public List<ObjetivoFinanceiro> getAllObjetivoFinanceiro() {
		return objetivoFinanceiroService.getAllObjetivoFinanceiro();
	}

	public void deleteObjetivoFinanceiro(ObjetivoFinanceiro persistentObject) {
		objetivoFinanceiroService.deleteObjetivoFinanceiro(persistentObject);
	}

	public void deleteObjetivoFinanceiro(long id) {
		objetivoFinanceiroService.deleteObjetivoFinanceiro(id);
	}
	

}