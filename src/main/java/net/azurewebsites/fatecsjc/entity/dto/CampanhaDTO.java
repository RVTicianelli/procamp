package net.azurewebsites.fatecsjc.entity.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CampanhaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;

	private String dataInicio;
	
	private String dataFim;
	
	private String dataCadastro;
	
	private String descricao;
	
	private Integer responsavel;
	
	private Integer tipoCampanha;	
	
	private List<Integer> localidades;
	
	private List<Integer> preferencias;

}
