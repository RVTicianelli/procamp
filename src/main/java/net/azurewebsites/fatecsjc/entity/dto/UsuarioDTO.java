package net.azurewebsites.fatecsjc.entity.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String dataNascimento;
	private String cpf;
	private String sexo;
	private String email;
	private String senha;
	private String ultimoLogin;
		
	private List<Integer> tipoCampanha;
	
	private Set<Integer> perfis = new HashSet<>();
}
