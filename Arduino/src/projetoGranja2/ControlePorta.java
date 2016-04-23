package projetoGranja2;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ControlePorta implements SerialPortEventListener {

	private OutputStream serialOut;
	private String portaCOM;
	private int taxa;
	private BufferedReader input;
	private SerialPort serialPort;
	private SensoriamentoDAO sensoriamentoDAO;

	public ControlePorta(String portaCOM, int taxa) {

		sensoriamentoDAO = new SensoriamentoDAO();

		this.portaCOM = portaCOM;
		this.taxa = taxa;
		
		Thread thread = new Thread();
		
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
				
				String inputLine = input.readLine();
				String [] entradas = inputLine.split("/");
				
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					
				SensoriamentoVO sensoriamentoVO = new SensoriamentoVO();
				sensoriamentoVO.umidade= Float.parseFloat(entradas[0]);
				sensoriamentoVO.temperatura= Float.parseFloat(entradas[1]);
				sensoriamentoVO.luminosidade= Float.parseFloat(entradas[2]);
				sensoriamentoVO.dataFinalizacao = timestamp;
								
				sensoriamentoDAO.inserirSensoriamento(sensoriamentoVO);
				
				System.out.print("Umidade: " + entradas[0] + "\t");
				System.out.print("Temperatura: " + entradas[1] + "\t");
				System.out.println("Luminosidade: " + entradas[2]);
				
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}
}
