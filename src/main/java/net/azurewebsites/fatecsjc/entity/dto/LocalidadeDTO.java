package net.azurewebsites.fatecsjc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocalidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String pontoReferencia;
	private Integer numero;
	private String complemento;
	private Integer tipoLocalidade;
	private Integer	cep;

}
