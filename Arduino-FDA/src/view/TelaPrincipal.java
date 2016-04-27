package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.LeituraSensores;
import model.RelatorioDiario;
import model.RelatorioDiarioDAO;
import control.ControlePersistencia;

public class TelaPrincipal extends JFrame {

	private ControlePersistencia controlePersistencia;
	private JLabel lbTemperatura, lbUmidade, lbLuminosidade;
	private JLabel lbValorTemp, lbValorUmid, lbValorLum, lbDelay;
	private JTextField tfDelay;
	private JPanel painel1;
	private JButton jbIniciarSensoriamento, jbModificarDalay, jbInserirMortalidade;

	public TelaPrincipal() {

		super("MONITORAMENTO DA GRANJA");
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		setSize(450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		painel1 = new JPanel(new GridLayout(2, 3, 50, 0));
		add(painel1);

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
		jbInserirMortalidade = new JButton("Inserir Mortalidade");

		lbDelay = new JLabel("Digite o delay (em min): ");
		tfDelay = new JTextField(5);
		jbModificarDalay = new JButton("Definir Delay");

		add(jbIniciarSensoriamento);
		add(jbInserirMortalidade);
		add(lbDelay);
		add(tfDelay);
		add(jbModificarDalay);

		gerarRelarioDiario();

		jbIniciarSensoriamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				controlePersistencia = new ControlePersistencia(TelaPrincipal.this);


			}
		});

		jbInserirMortalidade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new TelaMortalidade().setVisible(true);

			}
		});

		jbModificarDalay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int delayEmSegundos = Integer.parseInt(tfDelay.getText());
				delayEmSegundos = delayEmSegundos*60;
				controlePersistencia.setDelay(delayEmSegundos);
			}
		});

	}

	private void gerarRelarioDiario() {
		
		//Inserir um relatorio da BD apenas p/ testar as inserções da LeituraSensores
		RelatorioDiario relatorio = new RelatorioDiario();
		RelatorioDiarioDAO relDAO = new RelatorioDiarioDAO();
		relatorio.setData(new Date());
		relDAO.inserirRelatorio(relatorio);
		
	}

	public void atualizarResultados(LeituraSensores leitura) {

		lbValorTemp.setText("" + leitura.getTemperatura());
		lbValorUmid.setText("" + leitura.getUmidade());
		lbValorLum.setText("" + leitura.getLuminosidade());
	}


	public static void main(String[] args) {
		new TelaPrincipal().setVisible(true);
	}

}
