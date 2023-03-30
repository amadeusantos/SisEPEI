package br.upe.sisepei.sisepei.core.edital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upe.sisepei.sisepei.base.exception.NaoEncontradoException;
import br.upe.sisepei.sisepei.core.edital.EditalServico;
import br.upe.sisepei.sisepei.core.edital.modelo.Edital;
import br.upe.sisepei.sisepei.core.edital.modelo.EditalDTO;

@RestController
@RequestMapping("edital")
public class EditalController {
	
	@Autowired
	private EditalServico editalServico;

	@GetMapping
	public ResponseEntity<List<Edital>> listarEditais(){
		return ResponseEntity.ok(editalServico.listarEditais());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarEdital(@PathVariable Long id){
		try {
			return ResponseEntity.ok(editalServico.buscarEdital(id));
		} catch (NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> criarEdital(@RequestBody EditalDTO edital){
			Edital result = editalServico.criarEdital(edital);			
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarEdital(@PathVariable Long id){
		try {
			editalServico.removerEdital(id);
		} catch(NaoEncontradoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	


}
