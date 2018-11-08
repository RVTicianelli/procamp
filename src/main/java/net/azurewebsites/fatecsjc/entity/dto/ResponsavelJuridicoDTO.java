package net.azurewebsites.fatecsjc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsavelJuridicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;	
	private String cnpj;	
	private String razaoSocial;	
	private Integer numero;	
	private String complemento;	
	private Integer tipoResponsavel;	
	private Integer	cep; 
	
	public ResponsavelJuridicoDTO(String nome, String cnpj, String razaoSocial, Integer numero, String complemento, 
			Integer tipoResponsavel, Integer cep) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.numero = numero;
		this.complemento = complemento;
		this.tipoResponsavel = tipoResponsavel;
		this.cep = cep;
	}

}
