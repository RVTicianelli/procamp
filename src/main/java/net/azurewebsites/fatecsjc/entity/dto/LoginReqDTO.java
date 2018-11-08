package net.azurewebsites.fatecsjc.entity.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginReqDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	private String Senha;

	public LoginReqDTO(String email, String senha) {
		super();
		this.email = email;
		Senha = senha;
	}
}
