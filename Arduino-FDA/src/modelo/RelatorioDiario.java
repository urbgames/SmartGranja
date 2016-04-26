package modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RelatorioDiario {

	@Id
	@GeneratedValue
	private int id;
	private String data;
	private int mortalidade;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String string) {
		this.data = string;
	}
	public int getMortalidade() {
		return mortalidade;
	}
	public void setMortalidade(int mortalidade) {
		this.mortalidade = mortalidade;
	}
	
	
}
