package br.edu.ufape.organizeBill.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import br.edu.ufape.organizeBill.model.Categoria;
import br.edu.ufape.organizeBill.facade.Facade;
import br.edu.ufape.organizeBill.dto.*;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class CategoriaController {
	@Autowired
	private Facade facade;
	
	@GetMapping("categoria")
	public List<CategoriaResponse> getAllCategoria() {
		return facade.getAllCategoria()
			.stream()
			.map(CategoriaResponse::new)
			.toList();
	}
	
	@PostMapping("categoria")
	public CategoriaResponse createCategoria(@Valid @RequestBody CategoriaRequest newObj) {
		return new CategoriaResponse(facade.saveCategoria(newObj.toCategoria()));
	}
	
	@GetMapping("categoria/{id}")
	public CategoriaResponse getCategoriaById(@PathVariable Long id) {
		try {
			return new CategoriaResponse(facade.findCategoriaById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria " + id + " not found.");
		}
	}
	
	@PostMapping("categoria/{id}")
	public CategoriaResponse updateCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaRequest obj) {
		try {
			Categoria o = obj.toCategoria();
			o.setCodCategoria(id);
			return new CategoriaResponse(facade.updateCategoria(o));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria " + id + " not found.");
		}
		
	}
	
	@DeleteMapping("categoria/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		try {
			facade.deleteCategoria(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria " + id + " not found.");
		}
		
	}
	

}
