package com.ghxm.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_seq")
    @SequenceGenerator(name = "funcionario_seq", sequenceName = "funcionario_seq", allocationSize = 1)
   	private long id_funcionario;			// ID	
	private String nm_funcionario;			// Nome
	private String ds_funcionario;			// Designação
	private float sl_funcionario;			// Salário
	private String fn_funcionario;			// Fone
	private String en_funcionario;			// Endereço
	
}

