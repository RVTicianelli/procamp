package net.azurewebsites.fatecsjc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TipoContato implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	public TipoContato(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoContato [id=");
		builder.append(id);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append("]");
		return builder.toString();		
	}
}
