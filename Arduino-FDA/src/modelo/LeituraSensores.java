package modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LeituraSensores {

	
	@Id
	@GeneratedValue
	private int id;
	private float temperatura;
	private float umidade;
	private float luminosidade;
	private Date instante;
	
	public float getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}
	public float getUmidade() {
		return umidade;
	}
	public void setUmidade(float umidade) {
		this.umidade = umidade;
	}
	public float getLuminosidade() {
		return luminosidade;
	}
	public void setLuminosidade(float luminosidade) {
		this.luminosidade = luminosidade;
	}
	public Date getInstante() {
		return instante;
	}
	public void setInstante(Date instante) {
		this.instante = instante;
	}
	public int getId() {
		return id;
	}
		
}
