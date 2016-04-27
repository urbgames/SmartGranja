package model;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RelatorioDiario {

	@Id
	@GeneratedValue
	private int id;
	private Date data;
	private int mortalidade;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date string) {
		this.data = string;
	}
	public int getMortalidade() {
		return mortalidade;
	}
	public void setMortalidade(int mortalidade) {
		this.mortalidade = mortalidade;
	}
	
	
}
