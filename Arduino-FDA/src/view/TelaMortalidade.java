package view;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		relatorioDAO = new RelatorioDiarioDAO();
		
		lbData = new JLabel("Data da mortalidade");
		cbDatas = new JComboBox(relatorioDAO.listarDatas());
		lbMortalidade = new JLabel("Mortalidade");
		tfMortalidade = new JTextField(5);
		btCadastar = new JButton("Cadastrar");
		
		add(lbData);
		add(cbDatas);
		add(lbMortalidade);
		add(tfMortalidade);
		add(btCadastar);
		
		
	}
	
	public static void main(String[] args) {
		new TelaMortalidade().setVisible(true);
	}

}
