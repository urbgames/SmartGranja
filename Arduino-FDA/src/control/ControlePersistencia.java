package control;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;


import model.LeituraSensores;
import model.LeituraSensoresDAO;
import model.RelatorioDiario;
import model.RelatorioDiarioDAO;
import model.ArduinoDAO;
import view.TelaPrincipal;

public class ControlePersistencia implements Observer {
	
	private int quantidade = 0;
	private int delay = 10; //Considerando o sleep do arduino de 1 seg o delay = 10 seg
	private LeituraSensoresDAO leituraDAO;
	private RelatorioDiarioDAO relatorioDAO; 
	private TelaPrincipal tela;
	private ArduinoDAO arduinoDAO;
	private LeituraSensores leitura;
	
	public ControlePersistencia(TelaPrincipal tela) {
		
		this.tela = tela;
		this.leitura = new LeituraSensores();
		this.leituraDAO = new LeituraSensoresDAO();
		this.relatorioDAO = new RelatorioDiarioDAO();
		this.arduinoDAO = new ArduinoDAO("COM3", 9600);
		this.arduinoDAO.addObserver(this);
	}
	
	public void persistirDados(String inputLine) {
		
		quantidade++;
		System.out.println(quantidade);
		String [] entradas = inputLine.split("/");
		
		leitura.setUmidade(Float.parseFloat(entradas[0]));
		leitura.setTemperatura(Float.parseFloat(entradas[1]));
		leitura.setLuminosidade(Float.parseFloat(entradas[2]));
		//Mostra os resultados na tela
		tela.atualizarResultados(leitura);
		
		if(quantidade >= delay) {
			
			//Preenche o restante dos dados de "leitura" necessários para a persistencia no BD
			RelatorioDiario relatorio = relatorioDAO.getById(1);
			Date time = new Date(System.currentTimeMillis());
			leitura.setInstante(time);
			leitura.setRelatorio(relatorio);
			
			leituraDAO.inserirLeitura(leitura);
			quantidade = 0;
			
			System.out.print("Umidade: " + entradas[0] + "\t");
			System.out.print("Temperatura: " + entradas[1] + "\t");
			System.out.println("Luminosidade: " + entradas[2]);
			
		}
	}
	
	public void update(Observable arg0, Object arg1) {
		persistirDados(arduinoDAO.getInputLine());
		
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	

}