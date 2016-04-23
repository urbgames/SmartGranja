package projetoGranja2;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SensoriamentoVO {
	
	@Id
	@GeneratedValue
	public int id;
	public float temperatura;
	public float umidade;
	public float luminosidade;
	public Timestamp dataFinalizacao;
		
}