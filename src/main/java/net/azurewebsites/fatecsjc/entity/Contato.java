package net.azurewebsites.fatecsjc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Contato implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String contato;
	
	private String valor;
	
	@ManyToOne 
	@JoinColumn(name = "TIPOCONTATO_ID") 
	private TipoContato tipoContato;
	
	@ManyToOne 
	@JoinColumn(name = "RESPONSAVEL_ID") 
	private Responsavel responsavel;
	
	@ManyToOne 
	@JoinColumn(name = "LOCALIDADE_ID") 
	private Localidade localidade;
	
	public Contato(Integer id, String contato, String valor, TipoContato tipoContato, Localidade localidade) {
		super();
		this.id = id;
		this.contato = contato;
		this.valor = valor;
		this.tipoContato = tipoContato;
		this.localidade = localidade;
	}
	
	public Contato(Integer id, String contato, String valor, TipoContato tipoContato, Responsavel responsavel) {
		super();
		this.id = id;
		this.contato = contato;
		this.valor = valor;
		this.tipoContato = tipoContato;
		this.responsavel = responsavel;
	}

}
