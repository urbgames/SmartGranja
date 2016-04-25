package projetoGranja2;


public class Arduino {
	
	ControlePorta controlePorta;
	
	public Arduino() {

		controlePorta = new ControlePorta("COM3", 9600);

	}
	
}
