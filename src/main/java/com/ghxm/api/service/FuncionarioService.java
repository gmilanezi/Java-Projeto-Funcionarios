/** A princípio o retorno No Content pode parecer fazer sentido, mas o correto nessa situação é 
* retornar 404 (Not Found) por que estamos falando de um recurso (Uniform Resource Locator, lembra?) 
* e quando um recurso não existe o correto é que o status de retorno seja 404. Isso fica muito claro
* quando lemos o RFC7231.
*/

package com.ghxm.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ghxm.api.model.Funcionario;
import com.ghxm.api.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	FuncionarioRepository repository;
	
	public boolean funcionarioExiste(long id) {
        return repository.existsById(id);
    }
	
	public ResponseEntity<Funcionario> getFuncionario(long id) {
		Optional<Funcionario> funcionario = repository.findById(id);
		if(!funcionario.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(funcionario.get(), HttpStatus.OK);
	}
	
	public ResponseEntity<Funcionario> deletar(long id) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId_funcionario(id);
		
		if(funcionarioExiste(id)) {
			repository.delete(funcionario);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	public ResponseEntity<Funcionario> inserir(Funcionario funcionario) {
		repository.save(funcionario);
		return new ResponseEntity<>(funcionario, HttpStatus.OK);
	}
	
	public ResponseEntity<Funcionario> alterar(Funcionario funcionario) {
		if(funcionarioExiste(funcionario.getId_funcionario())) {
			repository.save(funcionario);
			return new ResponseEntity<>(funcionario, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
