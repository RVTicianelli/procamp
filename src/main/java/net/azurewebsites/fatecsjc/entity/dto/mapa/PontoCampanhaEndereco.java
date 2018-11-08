package net.azurewebsites.fatecsjc.entity.dto.mapa;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PontoCampanhaEndereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<String> listaEnderecos;
	
	public PontoCampanhaEndereco(List<String> listaEnderecos) {
		super();
		this.listaEnderecos = listaEnderecos;
	}

}
