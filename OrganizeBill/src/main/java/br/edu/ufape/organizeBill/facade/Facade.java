package br.edu.ufape.organizeBill.facade;

import java.time.LocalDate;
import java.util.List;

import br.edu.ufape.organizeBill.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ufape.organizeBill.model.*;
import br.edu.ufape.organizeBill.service.*;

@Service
public class Facade {
	
	 @Scheduled(
			 cron = "0 0 0 1 * *"
			//fixedRate = 10000
			 ) // Executer à meia-noite no primer dia de cada mês
	    public void verificarMudancaDeMes() {
		 	receitasContinuas();
	 		despesasContinuas();
	 		valorMetaContinuo();
	 		
	    }

	//Relatorio--------------------------------------------------------------

	public List<Object[]> findRelatorioTotalMeses(String cpf, int qntMeses){
		 return receitaService.findRelatorioTotalMeses(cpf,qntMeses);
	}
	public List<Object[]> findRelatorioReceitaTotalMeses(String cpf, int qntMeses){
		return receitaService.findRelatorioReceitaTotalMeses(cpf,qntMeses);
	}

	public List<Object[]> findRelatorioDespesaTotalMeses(String cpf, int qntMeses){
		return despesasService.findRelatorioDespesasTotalMeses(cpf,qntMeses);
	}

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
	
	public void despesasContinuas() {
		List<Usuario> usuarios = getAllUsuario(); 
		usuarios.stream().forEach(usuario->
		{
			List<Despesas> listaDespesas = getDespesasByData(usuario.getCpf(), "mesPassado", true);
			listaDespesas.stream().forEach(despesa -> {
				if(despesa.isFixo()) {
					Despesas newDespesa = new Despesas(0,despesa.getDescricao(),despesa.getValor(),
							LocalDate.now().withDayOfMonth(1),despesa.getCategoria(),despesa.getUsuario(),true);
					saveDespesas(newDespesa);
				}
			});
		});
	}
	
	public List<Despesas> getDespesasByData(String cpf, String data, boolean fixo) {
		return despesasService.getDespesasByData(cpf,data,fixo);
	}



	public double calcularTotalDespesasData(String cpf, String data, boolean fixo) {
		findUsuarioByCpf(cpf);
		List<Despesas> despesas = this.getDespesasByData(cpf, data , fixo);

		if (despesas.isEmpty()) {
			return 0;
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

	public Usuario loginUsuario(String email, String senha){
		return usuarioService.login(email,senha);
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
			return  0;
		}

		return receitas.stream()
				.mapToDouble(Receita::getValor)
				.sum();
	}	
	
	public void receitasContinuas() {
		List<Usuario> usuarios = getAllUsuario(); 
		usuarios.stream().forEach(usuario->
		{
			List<Receita> listaReceitas = getReceitaByData(usuario.getCpf(), "mesPassado", true);
			listaReceitas.stream().forEach(receita -> {
				if(receita.isFixo()) {
					Receita newReceita = new Receita(0,receita.getDescricao(),receita.getValor(),
							LocalDate.now().withDayOfMonth(1),receita.getUsuario(),true);
					saveReceita(newReceita);
				}
			});
		});
	}

	//ObjetivoFinanceiro--------------------------------------------------------------
	@Autowired
	private ObjetivoFinanceiroService  objetivoFinanceiroService;
		
	public ObjetivoFinanceiro saveObjetivoFinanceiro(ObjetivoFinanceiro newInstance) {
		Categoria categoria = new Categoria(0,newInstance.getNome(),newInstance.getDescricao(),newInstance.getValorTransitorio()
				, newInstance.getUsuario());
		saveCategoria(categoria);
		newInstance.setCategoria(categoria);
		
		return objetivoFinanceiroService.saveObjetivoFinanceiro(newInstance);
	}

	public ObjetivoFinanceiro updateObjetivoFinanceiro(ObjetivoFinanceiro transientObject) {
		return objetivoFinanceiroService.updateObjetivoFinanceiro(transientObject);
	}

	public ObjetivoFinanceiro findObjetivoFinanceiroById(long id) {
		return objetivoFinanceiroService.findObjetivoFinanceiroById(id);
	}

	public List<ObjetivoFinanceiro> getAllObjetivoFinanceiro(String cpf) {
		return objetivoFinanceiroService.getAllObjetivoFinanceiro(cpf);
	}

	public void deleteObjetivoFinanceiro(ObjetivoFinanceiro persistentObject) {
		objetivoFinanceiroService.deleteObjetivoFinanceiro(persistentObject);
	}

	public void deleteObjetivoFinanceiro(long id) {
		objetivoFinanceiroService.deleteObjetivoFinanceiro(id);
	}
	
	public void valorMetaContinuo() {
		List<Usuario> usuarios = getAllUsuario(); 
		usuarios.stream().forEach(usuario->
		{
			double receita = calcularTotalReceitasData(usuario.getCpf(),"mes" , false);
			double despesas = calcularTotalDespesasData(usuario.getCpf(),"mes" , false);
			final double[] valor = {0}; // Usando um array de double

			List<ObjetivoFinanceiro> listaObjetivos = getAllObjetivoFinanceiro(usuario.getCpf());
			listaObjetivos.stream().forEach(objetivo -> {
			    double saldoDisponivel = (receita - despesas) - (valor[0] + objetivo.getValorTransitorio());
			    if (saldoDisponivel >= 0) {
			    	objetivo.setValor(objetivo.getValor() + objetivo.getValorTransitorio());
			    	Despesas despesa = new Despesas(0,("Transacao para " + objetivo.getNome()+ "no valor de" +
			    	objetivo.getValorTransitorio()), objetivo.getValorTransitorio(),LocalDate.now(),objetivo.getCategoria(),
			    			objetivo.getUsuario(), false);
			    	saveDespesas(despesa);
			    	updateObjetivoFinanceiro(objetivo);
			    	
			        valor[0] += objetivo.getValorTransitorio(); // Atualizando o valor no array
			    }
			});
			
			
		});
	}
	
	public ObjetivoFinanceiro addValorObjetivoMeta(double valor, long codObjetivo) {
		ObjetivoFinanceiro objetivo = findObjetivoFinanceiroById(codObjetivo);
		double receita = calcularTotalReceitasData(objetivo.getUsuario().getCpf(),"mes" , false);
		double despesas = calcularTotalDespesasData(objetivo.getUsuario().getCpf(),"mes" , false);
		
		if((receita - despesas) - valor >= 0) {
			objetivo.setValor(objetivo.getValor() + valor);
	    	Despesas despesa = new Despesas(0,("Transacao para " + objetivo.getNome()+ "no valor de" +
	    	valor), valor,LocalDate.now(),objetivo.getCategoria(),
	    			objetivo.getUsuario(), false);
	    	saveDespesas(despesa);
	    	updateObjetivoFinanceiro(objetivo);
		}
		else {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "You don't have this value to make this transaction: " + valor);
		}
		
		return objetivo;
		
	}

}