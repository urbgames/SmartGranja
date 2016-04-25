package projetoGranja2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import modelo.LeituraSensores;

public class Tela extends JFrame {

	private ControlePersistencia controlePersistencia;
	private JLabel lbTemperatura, lbUmidade, lbLuminosidade;
	private JLabel lbValorTemp, lbValorUmid, lbValorLum;
	private JPanel painel1, painel2;
	private JButton jbIniciarSensoriamento, jbConfigurarInsercao, jbInserirMortalidade;
	private Tela tela;


	public Tela() {

		super("MONITORAMENTO DA GRANJA");
		setLayout(new FlowLayout());
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela = this;
		
		painel1 = new JPanel(new GridLayout(2, 3, 50, 0));
		painel2 = new JPanel(new FlowLayout(0, 10, 30));
		add(painel1);
		add(painel2);
		
		Font fonteGrande = new Font("arial", Font.BOLD, 35);

		lbTemperatura = new JLabel("Temperatura:");
		lbValorTemp = new JLabel("00");
		lbValorTemp.setFont(fonteGrande);
		
		lbUmidade = new JLabel("Umidade:");
		lbValorUmid = new JLabel("00");
		lbValorUmid.setFont(fonteGrande);
		
		lbLuminosidade = new JLabel("Luminosidade:");
		lbValorLum = new JLabel("00");
		lbValorLum.setFont(fonteGrande);
		
		painel1.add(lbTemperatura);
		painel1.add(lbUmidade);
		painel1.add(lbLuminosidade);
		painel1.add(lbValorTemp);
		painel1.add(lbValorUmid);
		painel1.add(lbValorLum);
		
		jbIniciarSensoriamento = new JButton("Iniciar Sensoriamento");
		jbConfigurarInsercao = new JButton("Configurar Inserão");
		jbInserirMortalidade = new JButton("Inserir Mortalidade");
		
		painel2.add(jbIniciarSensoriamento);
		painel2.add(jbInserirMortalidade);
		painel2.add(jbConfigurarInsercao);
		
		jbIniciarSensoriamento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlePersistencia = new ControlePersistencia(tela);
				
			}
		});
		
		
	}
	
	public void atualizarResultados(LeituraSensores leitura) {
		
		lbValorTemp.setText("" + leitura.getTemperatura());
		lbValorUmid.setText("" + leitura.getUmidade());
		lbValorLum.setText("" + leitura.getLuminosidade());
	}
	

	public static void main(String[] args) {
		new Tela();
	}

}
