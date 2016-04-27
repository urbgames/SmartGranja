package view;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.RelatorioDiario;
import model.RelatorioDiarioDAO;

public class TelaMortalidade extends JFrame {

	private JLabel lbData, lbMortalidade;
	private JComboBox cbDatas;
	private JTextField tfMortalidade;
	private JButton btCadastar;
	private RelatorioDiarioDAO relatorioDAO;
	private List<RelatorioDiario> listRelatorioDiario;
	private String relatorioDiarioSelecionado;

	public TelaMortalidade() {
		
		super("CADASTRO DE MORTALIDADE");
		setLayout(new FlowLayout(0, 10, 10));
		setLocationRelativeTo(null);
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		lbData = new JLabel("Data da mortalidade");
		cbDatas = new JComboBox();
		lbMortalidade = new JLabel("Mortalidade");
		tfMortalidade = new JTextField(5);
		btCadastar = new JButton("Cadastrar");
		
		btCadastar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				RelatorioDiario relatorioSelecionado = obterChavePorValor(listRelatorioDiario, cbDatas.getSelectedItem());
				relatorioSelecionado.setMortalidade(Integer.parseInt(tfMortalidade.getText()));
				relatorioDAO.atualizarRelatorio(relatorioSelecionado);
				dispose();
				
			}
		});
		
		relatorioDAO = new RelatorioDiarioDAO();
		
		alimentarComboMortalidade();
		
		add(lbData);
		add(cbDatas);
		add(lbMortalidade);
		add(tfMortalidade);
		add(btCadastar);
		
		
	}

	private void alimentarComboMortalidade() {

		listRelatorioDiario = relatorioDAO.listarRelatorioDiario();
		for (RelatorioDiario relatorioDiario : listRelatorioDiario) {
			cbDatas.addItem(new SimpleDateFormat("dd/MM/yyyy").format(relatorioDiario.getData()));
		}
	}

	public static RelatorioDiario obterChavePorValor(List<RelatorioDiario> objetos, Object valor) {
		for (RelatorioDiario objeto : objetos) {
			if (new SimpleDateFormat("dd/MM/yyyy").format(objeto.getData()).equals(valor.toString())) {
				return objeto;
			}
		}
		return null;
	}
}
