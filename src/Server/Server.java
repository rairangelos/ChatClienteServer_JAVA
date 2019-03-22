package Server;

import Client.Client;
import ServerThread.ServerThread;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ALUNO
 */
public class Server {
    
    public static void main(String[] args) throws IOException {
        new Server(12345).executarSever();
    }
         
    private int porta;
    private List<PrintStream> listaMsg;
         
    public Server (int porta) {
        this.porta = porta;
        this.listaMsg = new ArrayList<PrintStream>();
    }
            
    public void executarSever() throws IOException {	
        ServerSocket Servidor = new ServerSocket(this.porta);
        System.out.println("Servidor iniciado na porta "+porta);
		
        while(true){
        Socket client = Servidor.accept();
        System.out.println("Nova conexão com o cliente "+client.getInetAddress().getHostAddress());                
                            
        //Receber mensagem e salvar na lista
        PrintStream novaMsg = new PrintStream(client.getOutputStream());
        this.listaMsg.add(novaMsg);
                    
        // Criar thread
        ServerThread conversa;
        conversa = new ServerThread(client.getInputStream(), this);
        new Thread(conversa).start();
        }
	
    }
    
    public void distribuiMensagem(String novaMsg) {

        for (PrintStream distMsg : this.listaMsg) {
            distMsg.println(novaMsg);
        }
    }
    
    
}