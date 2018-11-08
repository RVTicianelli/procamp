package net.azurewebsites.fatecsjc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContatoResponsavelDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String contato;
	
	private String valor;
	
	private Integer tipoContato;
	
	private Integer responsavel;
	
	public ContatoResponsavelDTO(String contato, String valor, Integer tipoContato, Integer responsavel) {
		super();
		this.contato = contato;
		this.valor = valor;
		this.tipoContato = tipoContato;
		this.responsavel = responsavel;
	}
	
}
