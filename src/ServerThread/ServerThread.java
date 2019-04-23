package ServerThread;

import Server.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * @author ALUNO
 */
public class ServerThread implements Runnable {

	private InputStream client;
	private Server servidor;

	public ServerThread(InputStream client, Server servidor) {
		this.client = client;
		this.servidor = servidor;
	}

	public void run() {
		Scanner entradaMsg = new Scanner(this.client);
		while (entradaMsg.hasNextLine()) {
			servidor.distribuiMensagem(entradaMsg.nextLine());
		}
		entradaMsg.close();
	}
}