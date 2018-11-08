package net.azurewebsites.fatecsjc.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.azurewebsites.fatecsjc.entity.Usuario;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginRespDTO {
	String token;
	Usuario usuario;
	
	public LoginRespDTO(String token, Usuario usuario) {
		super();
		this.token = token;
		this.usuario = usuario;
	}
	
	

}
