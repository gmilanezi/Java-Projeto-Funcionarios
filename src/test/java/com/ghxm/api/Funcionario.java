package com.ghxm.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class Funcionario {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	void Produto() throws Exception{
		
	    String funcionarioJson = "{ "
	    		+ "\"nm_funcionario\": \"Maria\", "
	    		+ "\"en_funcionario\": \"Rua x, y\", "
	    		+ "\"ds_funcionario\": \"JUNIT\", "
	    		+ "\"fn_funcionario\": \"1234\" }";
	    
	    /*
	     *    Inclusão do Registro
	     */
		
	    ResultActions result = mockMvc.perform(post("/api")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(funcionarioJson))
	            .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id_funcionario").exists())
	            .andExpect(jsonPath("$.fn_funcionario").value("1234"))
	            .andExpect(jsonPath("$.en_funcionario").value("Rua x, y"))
	            .andExpect(jsonPath("$.nm_funcionario").value("Maria"))
	            .andExpect(jsonPath("$.ds_funcionario").value("JUNIT"));
	    
	    String responseContent = result.andReturn().getResponse().getContentAsString();
        Long idCriado = Long.parseLong(JsonPath.read(responseContent, "$.id_funcionario").toString());
        
        /*
         *  Consulta do Registro no banco
         */

		mockMvc.perform(get("/api/funcionario/{id}", idCriado+1))
        .andExpect(status().isNotFound());
		
		mockMvc.perform(get("/api/funcionario/{id}", idCriado))
        .andExpect(status().isOk());
		
		/*
		 *   Alteracao do Registro
		 */
		
		funcionarioJson = "{ \"nm_funcionario\": \"Maria Maria\", \"ds_funcionario\": \"JUNIT JUNIT\" }";
		
		mockMvc.perform(put("/api/funcionario/{id}", idCriado+1)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(funcionarioJson))
	            .andExpect(status().isNotFound());
		
		mockMvc.perform(put("/api/funcionario/{id}", idCriado)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(funcionarioJson))
	            .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id_funcionario").exists())
	            .andExpect(jsonPath("$.nm_funcionario").value("Maria Maria"))
	            .andExpect(jsonPath("$.ds_funcionario").value("JUNIT JUNIT"));
		
		/*
		 *   Por fim, excluímos para deixar o banco limpo do registro usado para teste
		 * 
		 */
		
		mockMvc.perform(delete("/api/funcionario/{id}",idCriado+1))
        .andExpect(status().isNotFound());
		
		mockMvc.perform(delete("/api/funcionario/{id}",idCriado))
        .andExpect(status().isOk());
	}

}
