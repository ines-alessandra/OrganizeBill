package br.edu.ufape.organizeBill.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import br.edu.ufape.organizeBill.model.Despesas;
import br.edu.ufape.organizeBill.facade.Facade;
import br.edu.ufape.organizeBill.dto.*;
import br.edu.ufape.organizeBill.exception.ObjectNotFoundException;


@CrossOrigin (origins = "http://localhost:3000/" )
@RestController
@RequestMapping("/api/v1/")
public class DespesasController {
	@Autowired
	private Facade facade;
	
	@GetMapping("despesas")
	public List<DespesasResponse> getAllDespesas() {
		return facade.getAllDespesas()
			.stream()
			.map(DespesasResponse::new)
			.toList();
	}
	
	@GetMapping("/usuario/{cpf}/totalDespesas/{data}/{tipo}")
	public ResponseEntity<Double> getTotalDespesasMensais(@PathVariable String cpf, @PathVariable String data, @PathVariable boolean tipo) {
		try {
			double totalDespesasMensais = facade.calcularTotalDespesasData(cpf, data, tipo);
			return ResponseEntity.ok(totalDespesasMensais);
		} catch (ObjectNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("despesas")
	public DespesasResponse createDespesas(@Valid @RequestBody DespesasRequest newObj) {
		return new DespesasResponse(facade.saveDespesas(newObj.toDespesas()));
	}
	
	
	@PostMapping("despesasContinua")
	public void despesasContinua() {
		facade.despesasContinuas();
	}
	
	
	@GetMapping("despesas/{id}")
	public DespesasResponse getDespesasById(@PathVariable Long id) {
		try {
			return new DespesasResponse(facade.findDespesasById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesas " + id + " not found.");
		}
	}
	
	@GetMapping("despesas/categoria/{CategoriaId}")
	public List<DespesasResponse> findByCategoria(@PathVariable Long CategoriaId) {
		return facade.findByCategoria(CategoriaId)
			.stream()
			.map(DespesasResponse::new)
			.toList();
	}
	
	@GetMapping("despesas/usuario/{cpf}/{data}/{fixo}")
	public List<DespesasResponse> findDespesasByData(@PathVariable String cpf, @PathVariable String data, @PathVariable boolean fixo) {
		return facade.getDespesasByData(cpf,data,fixo)
			.stream()
			.map(DespesasResponse::new)
			.toList();
	}

	@GetMapping("relatorio/despesa/usuario/{cpf}/{qntMes}")
	public List<Object[]> findRelatorioDespesaByData(@PathVariable String cpf, @PathVariable int qntMes) {
		return facade.findRelatorioDespesaTotalMeses(cpf,qntMes);

	}

	@GetMapping("despesas/categoria/{codCategoria}/{data}/{fixo}")
	public List<DespesasResponse> findDespesasByCategoriaData(@PathVariable long codCategoria, @PathVariable String data, @PathVariable boolean fixo) {
		return facade.getDespesasByCategoriaData(codCategoria,data,fixo)
				.stream()
				.map(DespesasResponse::new)
				.toList();
	}

	@GetMapping("despesas/total/categoria/{codCategoria}")
	public BigDecimal getTotalMensalByCategoria(@PathVariable long codCategoria) {
		return facade.getTotalMensalByCategoria(codCategoria);

	}
	
	@PostMapping("despesas/{id}")
	public DespesasResponse updateDespesas(@PathVariable Long id, @Valid @RequestBody DespesasRequest obj) {
		try {
			Despesas o = obj.toDespesas();
			o.setCodDespesa(id);
			return new DespesasResponse(facade.updateDespesas(o));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesas " + id + " not found.");
		}
		
	}
	
	@DeleteMapping("despesas/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		try {
			facade.deleteDespesas(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesas " + id + " not found.");
		}
		
	}
	

}
