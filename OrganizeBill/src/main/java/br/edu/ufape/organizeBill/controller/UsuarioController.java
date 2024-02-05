package br.edu.ufape.organizeBill.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import br.edu.ufape.organizeBill.model.Usuario;
import br.edu.ufape.organizeBill.facade.Facade;
import br.edu.ufape.organizeBill.dto.*;
import br.edu.ufape.organizeBill.exception.ObjectNotFoundException;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class UsuarioController {
	@Autowired
	private Facade facade;
	
	@GetMapping("usuario")
	public List<UsuarioResponse> getAllUsuario() {
		return facade.getAllUsuario()
			.stream()
			.map(UsuarioResponse::new)
			.toList();
	}
	
	@PostMapping("usuario")
	public UsuarioResponse createUsuario(@Valid @RequestBody UsuarioRequest newObj) {
		return new UsuarioResponse(facade.saveUsuario(newObj.toUsuario()));
	}
	
	@GetMapping("usuario/{cpf}")
	public UsuarioResponse getUsuarioByCpf(@PathVariable String cpf) {
		try {
			return new UsuarioResponse(facade.findUsuarioByCpf(cpf));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario " + cpf + " not found.");
		}
	}
	
	@GetMapping("/usuario/{cpf}/totalReceitas/{data}/{tipo}")
	public ResponseEntity<Double> getUsuarioTotalReceitasMensais(@PathVariable String cpf, @PathVariable String data,@PathVariable String tipo) {
	    try {
	        double totalReceitasMensais = facade.calcularTotalReceitasData(cpf, data, tipo);
	        return ResponseEntity.ok(totalReceitasMensais);
	    } catch (ObjectNotFoundException ex) {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping("usuario/{cpf}")
	public UsuarioResponse updateUsuario(@PathVariable String cpf, @Valid @RequestBody UsuarioRequest obj) {
		try {
			Usuario o = obj.toUsuario();
			o.setCpf(cpf);
			return new UsuarioResponse(facade.updateUsuario(o));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario " + cpf + " not found.");
		}
		
	}
	
	@DeleteMapping("usuario/{cpf}")
	public String deleteEmployee(@PathVariable String cpf) {
		try {
			facade.deleteUsuario(cpf);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario " + cpf + " not found.");
		}
		
	}
	

}
