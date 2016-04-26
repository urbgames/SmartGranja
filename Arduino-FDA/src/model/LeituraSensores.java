package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LeituraSensores {

	
	@Id
	@GeneratedValue
	private int id;
	private float temperatura;
	private float umidade;
	private float luminosidade;
	private Date instante;
	@ManyToOne
	@JoinColumn(name= "relatorio_id")
	private RelatorioDiario relatorio;
	
	
	public RelatorioDiario getRelatorio() {
		return relatorio;
	}
	public void setRelatorio(RelatorioDiario relatorio) {
		this.relatorio = relatorio;
	}
	public void setId(int id) {
		this.id = id;
	}
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
