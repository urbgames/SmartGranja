package projetoGranja2;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;


import modelo.LeituraSensores;
import modelo.LeituraSensoresDAO;
import modelo.RelatorioDiario;
import modelo.RelatorioDiarioDAO;


public class ControlePersistencia implements Observer {
	
	private int quantidade = 0;
	private int delay = 10; //Considerando o sleep do arduino de 1 seg o delay = 1 minuto
	private LeituraSensoresDAO leituraDAO;
	private RelatorioDiarioDAO relatorioDAO; 
	private Tela tela;
	private ArduinoDAO controlePorta;
	
	public ControlePersistencia(Tela tela) {
		
		this.leituraDAO = new LeituraSensoresDAO();
		this.relatorioDAO = new RelatorioDiarioDAO();
		this.tela = tela;
		this.controlePorta = new ArduinoDAO("COM5", 9600, this);
		this.controlePorta.addObserver(this);
		
	}
	
	public void persistirDados(String inputLine) {
		
		quantidade++;
		System.out.println(quantidade);
		String [] entradas = inputLine.split("/");
		
		
		if(quantidade >= delay) {
			
			LeituraSensores leitura = new LeituraSensores();
			RelatorioDiario relatorio = relatorioDAO.getById(1);
			
			Date time = new Date(System.currentTimeMillis());
			
			leitura.setUmidade(Float.parseFloat(entradas[0]));
			leitura.setTemperatura(Float.parseFloat(entradas[1]));
			leitura.setLuminosidade(Float.parseFloat(entradas[2]));
			leitura.setInstante(time);
			leitura.setRelatorio(relatorio);
		
			//Mostra os resultados na tela
			tela.atualizarResultados(leitura);
			
			System.out.print("Umidade: " + entradas[0] + "\t");
			System.out.print("Temperatura: " + entradas[1] + "\t");
			System.out.println("Luminosidade: " + entradas[2]);
			
			leituraDAO.inserirLeitura(leitura);
			quantidade = 0;
		}
			
		
	}
	
	public void update(Observable arg0, Object arg1) {
		persistirDados(controlePorta.getInputLine());
		System.out.println(controlePorta.getInputLine());
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	

}