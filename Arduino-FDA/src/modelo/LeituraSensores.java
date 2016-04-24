package modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LeituraSensores {

	
	@Id
	@GeneratedValue
	public int id;
	public float temperatura;
	public float umidade;
	public float luminosidade;
	public Date instante;
		
}
