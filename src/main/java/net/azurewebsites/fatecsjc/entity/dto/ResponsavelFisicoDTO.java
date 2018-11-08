package net.azurewebsites.fatecsjc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsavelFisicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String cpf;
		
	private String dataNascimento;
	
	private String sexo;
		
	private Integer numero;
	
	private String complemento; 
	
	private Integer tipoResponsavel;
	
	private Integer	cep; 
	
	public ResponsavelFisicoDTO(String nome, String cpf, String dataNacimento, String sexo, Integer numero, String complemento, 
			Integer tipoResponsavel, Integer cep) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNacimento;
		this.sexo = sexo;
		this.numero = numero;
		this.complemento = complemento;
		this.tipoResponsavel = tipoResponsavel;
		this.cep = cep;
	}
	
}
