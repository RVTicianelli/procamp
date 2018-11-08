package net.azurewebsites.fatecsjc.entity.dto.mapa;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PontoCampanhaGeo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String latitude;
	private String longitude;
	private String endereco;
	
	public PontoCampanhaGeo(String latitude, String longitude, String endereco) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.endereco = endereco;
	}
	

}
