package net.azurewebsites.fatecsjc.enums;
import lombok.Getter;

@Getter
public enum Perfil {
	ADMIN(1, "ROLE_ADM"),
	CLIENTE(2, "ROLE_CLI");
	
	private int id;
	private String descricao;
	
	private Perfil(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public static Perfil toEnum(Integer id) { 
		if(id == null) {
			return null;
		}
		
		for (Perfil x : Perfil.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}
}
