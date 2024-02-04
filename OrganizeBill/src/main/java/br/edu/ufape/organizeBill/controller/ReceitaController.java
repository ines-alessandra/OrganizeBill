package br.edu.ufape.organizeBill.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import br.edu.ufape.organizeBill.model.Receita;
import br.edu.ufape.organizeBill.facade.Facade;
import br.edu.ufape.organizeBill.dto.*;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class ReceitaController {
	@Autowired
	private Facade facade;
	
	@GetMapping("receita")
	public List<ReceitaResponse> getAllReceita() {
		return facade.getAllReceita()
			.stream()
			.map(ReceitaResponse::new)
			.toList();
	}
	
	@PostMapping("receita")
	public ReceitaResponse createReceita(@Valid @RequestBody ReceitaRequest newObj) {
		return new ReceitaResponse(facade.saveReceita(newObj.toReceita()));
	}
	
	@GetMapping("receita/{id}")
	public ReceitaResponse getReceitaById(@PathVariable Long id) {
		try {
			return new ReceitaResponse(facade.findReceitaById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita " + id + " not found.");
		}
	}
	
	@PostMapping("receita/{id}")
	public ReceitaResponse updateReceita(@PathVariable Long id, @Valid @RequestBody ReceitaRequest obj) {
		try {
			Receita o = obj.toReceita();
			o.setCodReceita(id);
			return new ReceitaResponse(facade.updateReceita(o));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita " + id + " not found.");
		}
		
	}
	
	@DeleteMapping("receita/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		try {
			facade.deleteReceita(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita " + id + " not found.");
		}
		
	}
	

}
