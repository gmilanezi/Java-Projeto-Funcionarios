package com.ghxm.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghxm.api.model.Funcionario;
import com.ghxm.api.service.FuncionarioService;

@RestController
@RequestMapping("/api")
public class FuncionarioController {
	
	@Autowired
	FuncionarioService service;

	@GetMapping("/funcionario/{id}")
	public ResponseEntity<Funcionario> getFuncionario(@PathVariable("id") long id) {
		return service.getFuncionario(id);
	}
	
	@DeleteMapping("/funcionario/{id}")
	public ResponseEntity<Funcionario> deletar(@PathVariable("id") long id) {
		return service.deletar(id);
	}
	
	@PutMapping("/funcionario/{id}")
	public ResponseEntity<Funcionario> alterar(@PathVariable("id") long id, @RequestBody Funcionario funcionario) {
		funcionario.setId_funcionario(id);
		return service.alterar(funcionario);
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> inserir(@RequestBody Funcionario funcionario) {
		return service.inserir(funcionario);
	}
	
}
