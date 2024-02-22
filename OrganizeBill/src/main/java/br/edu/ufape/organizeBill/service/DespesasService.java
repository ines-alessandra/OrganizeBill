package br.edu.ufape.organizeBill.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.organizeBill.repository.DespesasRepository;
import br.edu.ufape.organizeBill.exception.ObjectNotFoundException;
import br.edu.ufape.organizeBill.model.Categoria;
import br.edu.ufape.organizeBill.model.Despesas;

@Service
public class DespesasService implements DespesasServiceInterface {
	@Autowired
	DespesasRepository repository;

	public DespesasService(DespesasRepository repository) {
		this.repository = repository;
	}

	public DespesasService() {
	}
	
	public DespesasRepository getRepository() {
		return repository;
	}

	public void setDao(DespesasRepository repository) {
		this.repository = repository;
	}	

	public Despesas saveDespesas(Despesas newInstance) {
		return repository.save(newInstance);
	}

	public Despesas updateDespesas(Despesas transientObject) {
		return repository.save(transientObject);
	}

	public Despesas findDespesasById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Despesas with id = " + id));
	}

	public List<Despesas> getAllDespesas(){
		return repository.findAll();
	}
	
	public List<Despesas> findByCategoria(Categoria categoria){
		List<Despesas> despesas = repository.findByCategoriaOrderByData(categoria);
		if(despesas.isEmpty()) {
			throw new ObjectNotFoundException("Despesas");
		}
		return despesas;
	} 
	

	public void deleteDespesas(Despesas persistentObject){
		this.deleteDespesas(persistentObject.getCodDespesa());
		
	}
	
	public void deleteDespesas(long id){
		Despesas obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Despesas with id = " + id));
		repository.delete(obj);
	}	
	
	public List<Despesas> getDespesasByData(String cpf, String data, boolean fixo) {
		LocalDate inicio;
		LocalDate termino;

		switch (data) {
			case "dia":
				inicio = LocalDate.now();
				termino = inicio;
				break;
			case "semana":
				inicio = LocalDate.now().minusDays(7);
				termino = LocalDate.now();
				break;
			case "mes":
				inicio = LocalDate.now().withDayOfMonth(1);
				termino = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
				break;
			case "trimestral":
				inicio = LocalDate.now().withDayOfMonth(1).minusMonths(2);
				termino = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
				break;
			case "semestral":
				inicio = LocalDate.now().withDayOfMonth(1).minusMonths(5);
				termino = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
				break;
			case "ano":
				inicio = LocalDate.now().withDayOfYear(1);
				termino = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());
				break;

			default:
				throw new IllegalArgumentException("Data inválida: " + data);
		}

		if (fixo)
			return repository.findDespesasByDataBetweenAndUsuarioCpfAndFixoIsTrueOrderByData (inicio, termino,cpf);
		else
			return repository.findDespesasByDataBetweenAndUsuarioCpfOrderByData (inicio, termino,cpf);
	}
	
	public List<Despesas> getDespesasByCategoriaData(Long codCategoria, String data) {
		LocalDate inicio;
		LocalDate termino;

		switch (data) {
			case "dia":
				inicio = LocalDate.now();
				termino = inicio;
				break;
			case "semana":
				inicio = LocalDate.now().minusDays(7);
				termino = LocalDate.now();
				break;
			case "mes":
				inicio = LocalDate.now().withDayOfMonth(1);
				termino = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
				break;
			case "trimestral":
				inicio = LocalDate.now().withDayOfMonth(1).minusMonths(2);
				termino = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
				break;
			case "semestral":
				inicio = LocalDate.now().withDayOfMonth(1).minusMonths(5);
				termino = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
				break;
			case "ano":
				inicio = LocalDate.now().withDayOfYear(1);
				termino = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());
				break;
			default:
				throw new IllegalArgumentException("Data inválida: " + data);
		}
			return repository.findDespesasByDataBetweenAndCategoriaCodCategoriaOrderByData(inicio, termino, codCategoria);
	}

	public List<Object[]> findRelatorioDespesasTotalMeses(String cpf, int qntMeses) {
		// Calcula a data de início subtraindo a quantidade de meses da data atual
		LocalDate startDate = LocalDate.now().minusMonths(qntMeses);
		// A data de fim é a data atual
		LocalDate endDate = LocalDate.now();

		// Chama o método do repositório passando as datas de início e fim como parâmetros
		return repository.findResumoDespesasParaUsuarioEIntervalo(cpf, startDate, endDate);
	}
	
}