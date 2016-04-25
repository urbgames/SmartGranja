package projetoGranja2;

import java.util.Date;

import modelo.LeituraSensores;
import modelo.LeituraSensoresDAO;


public class ControlePersistencia {
	
	private int quantidade = 0;
	private int delay = 10; //Considerando o sleep do arduino de 1 seg o delay = 1 minuto
	private LeituraSensoresDAO leituraDAO;
	
	public ControlePersistencia() {
		
		leituraDAO = new LeituraSensoresDAO();
	}
	
	public void persistirDados(String inputLine) {
		
		quantidade++;
		System.out.println(quantidade);
		String [] entradas = inputLine.split("/");
		
		
		if(quantidade >= delay) {
			
			LeituraSensores leitura = new LeituraSensores();

			Date time = new Date(System.currentTimeMillis());
			
			leitura.setUmidade(Float.parseFloat(entradas[0]));
			leitura.setTemperatura(Float.parseFloat(entradas[1]));
			leitura.setLuminosidade(Float.parseFloat(entradas[2]));
			leitura.setInstante(time);
		
			System.out.print("Umidade: " + entradas[0] + "\t");
			System.out.print("Temperatura: " + entradas[1] + "\t");
			System.out.println("Luminosidade: " + entradas[2]);
			
			leituraDAO.inserirLeitura(leitura);
			quantidade = 0;
		}
			
		
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}
