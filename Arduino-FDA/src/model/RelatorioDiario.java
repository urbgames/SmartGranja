package model;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	//método para mostrar apenas a data dd/mm/yyyy em forma de string no comboBox
	public String toString() {
		return new SimpleDateFormat("dd/MM/yyyy").format(this.data);
	}
	
}
