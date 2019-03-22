package Client;

import ClientThread.ClientThread;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author ALUNO
 */
public class Client {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        new Client("127.0.0.1", 12345).executarCliente();
    }
            private int porta;
            private String host;
              
    public Client (String host, int porta) {
        this.host = host;
        this.porta = porta;
    }
	
    public void executarCliente() throws UnknownHostException, IOException {
                
                Socket client = new Socket(this.host, this.porta);       
                System.out.println("O cliente se conectou ao servidor!");   

                
                // thread para receber mensagens do servidor
                ClientThread receberMsg = new ClientThread(client.getInputStream());
                new Thread(receberMsg).start();

                
                Scanner teclado = new Scanner(System.in);
                PrintStream saida = new PrintStream(client.getOutputStream());
                // lê msgs do teclado e manda pro servidor
                while (teclado.hasNextLine()) {
                    saida.println(teclado.nextLine());
                }
                
                saida.close();
                teclado.close();
                client.close(); 
                

            }
    

}