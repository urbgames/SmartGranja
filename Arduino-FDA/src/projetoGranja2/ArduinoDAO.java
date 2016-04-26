package projetoGranja2;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ArduinoDAO implements SerialPortEventListener {

	private OutputStream serialOut;
	private String portaCOM;
	private int taxa;
	private BufferedReader input;
	private SerialPort serialPort;
	private ControlePersistencia controlePersistencia;
	protected String inputLine;

	public ArduinoDAO(String portaCOM, int taxa, ControlePersistencia contPerst) {

		this.controlePersistencia = contPerst;
		this.portaCOM = portaCOM;
		this.taxa = taxa;
		this.initialize();
	}

	public void initialize() {

		try {
			CommPortIdentifier portaId = null;
			try {
				portaId = CommPortIdentifier.getPortIdentifier(portaCOM);
			} catch (Exception e) {
				System.out.println("Porta não foi encontrada: "
						+ e.getMessage());
			}

			serialPort = (SerialPort) portaId.open("Comunicação", taxa);
			serialPort.setSerialPortParams(this.taxa, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			input = new BufferedReader(new InputStreamReader(
					serialPort.getInputStream()));
			serialOut = serialPort.getOutputStream();

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close() {

		try {
			serialPort.removeEventListener();
			serialPort.close();
		} catch (Exception e) {

			System.err.println("Não foi possível fechar a porta: "
					+ e.getMessage());
		}
	}

	public void enviarDados(int opcao) {

		try {
			serialOut.write(opcao);
		} catch (Exception e) {
			System.err.println("Não foi possível enviar dados: "
					+ e.getMessage());
		}

	}

	@Override
	public void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				
				inputLine = input.readLine();
				
				// Controle não pode ser chamado pelo DAO, o inputLine deve retornar os dados ao controle
				// a facilidade aqui é que a persistencia é chamada assim q o evento dispara..
				
				//controlePersistencia.persistirDados(inputLine);		
				
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}
}
