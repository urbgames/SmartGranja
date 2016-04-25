package projetoGranja2;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.LeituraSensores;

public class Tela extends JFrame {

	private ControlePersistencia controlePersistencia;
	private JLabel lbSensores;
	private JTable tbResultados;



	public Tela() {

		super("MONITORAENTO DA GRANJA");
		tbResultados = new JTable();
		setLayout(new FlowLayout(0, 10, 10));
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lbSensores = new JLabel("_____________________________  LEITURA DOS SENSORES  _____________________________");
		this.add(lbSensores);

		//Cabeçalho da tabela
		String[] colunas = new String[]{"LEITURA Nº", "HORA", "TEMPERATURA", "UMIDADE", "LUMINOSIDADE"};

		tbResultados = new JTable();
		tbResultados.setEnabled(false);
		tbResultados.setPreferredScrollableViewportSize(new Dimension(560,100));

		//Definindo as colunas da tabela
		DefaultTableModel modelResultado = new DefaultTableModel(null, colunas);
		tbResultados.setModel(modelResultado);

		JScrollPane scrollResultado = new JScrollPane();
		scrollResultado.setViewportView(tbResultados);
		this.add(scrollResultado);
		
		
		controlePersistencia = new ControlePersistencia(this);

	}
	
	public void atualizarTabelaResultados(LeituraSensores leitura) {

		DefaultTableModel modelo =(DefaultTableModel) tbResultados.getModel();  
		modelo.addRow(new Object[]{leitura.getId(),leitura.getInstante(), leitura.getTemperatura(), leitura.getUmidade(), leitura.getLuminosidade()});
	
	}

	public static void main(String[] args) {
		new Tela();
	}

}
