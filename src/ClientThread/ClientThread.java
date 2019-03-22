package ClientThread;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author ALUNO
 */
public class ClientThread implements Runnable {

        private InputStream servidor;

        public ClientThread(InputStream servidor) {
            this.servidor = servidor;
        }

        public void run() {
        // recebe msgs do servidor e imprime na tela
            Scanner saidaTela = new Scanner(this.servidor);
            while (saidaTela.hasNextLine()) {
                //request.setCharacterEncoding("UTF8");
                System.out.println(saidaTela.nextLine());
            }
    }
    
}