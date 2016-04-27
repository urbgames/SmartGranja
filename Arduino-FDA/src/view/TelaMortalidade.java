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


	public TelaMortalidade() {

		super("CADASTRO DE MORTALIDADE");
		setLayout(new FlowLayout(0, 10, 10));
		setLocationRelativeTo(null);
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		lbData = new JLabel("Data da mortalidade");
		lbMortalidade = new JLabel("Mortalidade");
		tfMortalidade = new JTextField(5);
		btCadastar = new JButton("Cadastrar");

		//Alimentar o comboBox com os relatorios (exibindo apenas as datas)
		relatorioDAO = new RelatorioDiarioDAO();
		Vector<RelatorioDiario> relatorios = relatorioDAO.listarRelatorioDiarioVector();
		cbDatas = new JComboBox(relatorios);

		add(lbData);
		add(cbDatas);
		add(lbMortalidade);
		add(tfMortalidade);
		add(btCadastar);

		btCadastar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				RelatorioDiario relatorioSelecionado = 	(RelatorioDiario) cbDatas.getSelectedItem();
				
				relatorioSelecionado.setMortalidade(Integer.parseInt(tfMortalidade.getText()));
				relatorioDAO.atualizarRelatorio(relatorioSelecionado);
				dispose();

			}
		});

	}

}
