package net.azurewebsites.fatecsjc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContatoLocalidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String contato;
	
	private String valor;
	
	private Integer tipoContato;
	
	private Integer localidade;
	
	public ContatoLocalidadeDTO(String contato, String valor, Integer tipoContato, Integer localidade) {
		super();
		this.contato = contato;
		this.valor = valor;
		this.tipoContato = tipoContato;
		this.localidade = localidade;
	}
}
