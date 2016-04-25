package projetoGranja2;

import javax.swing.JFrame;

public class Tela extends JFrame {

	Arduino arduino;

	public Tela() {

		arduino = new Arduino();
		
		//testando
		
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public static void main(String[] args) {
		new Tela();
	}
	
}
