package br.edu.ufape.organizeBill.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import br.edu.ufape.organizeBill.model.Despesas;
import br.edu.ufape.organizeBill.facade.Facade;
import br.edu.ufape.organizeBill.dto.*;


@CrossOrigin (origins = "http://localhost:8081/" )
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
	
	@PostMapping("despesas")
	public DespesasResponse createDespesas(@Valid @RequestBody DespesasRequest newObj) {
		return new DespesasResponse(facade.saveDespesas(newObj.toDespesas()));
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
