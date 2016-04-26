package model;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Observable;

import control.ControlePersistencia;

public class ArduinoDAO extends Observable implements SerialPortEventListener {

	private OutputStream serialOut;
	private String portaCOM;
	private int taxa;
	private BufferedReader input;
	private SerialPort serialPort;
	private String inputLine;

	public ArduinoDAO(String portaCOM, int taxa) {

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
				setChanged();
				notifyObservers();

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}

	public String getInputLine() {
		return inputLine;
	}

	public void setInputLine(String inputLine) {
		this.inputLine = inputLine;
	}
}