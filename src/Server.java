/**
 *
 * @author Lucas Camanelli de Souza
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    Socket socketClient;
    ServerSocket serverSocket;
    
    public boolean connect(){
        try{
            socketClient = serverSocket.accept();
            return true;
        }
        catch(IOException e){
            System.out.println("Não fez conexão " + e.getMessage());
            return false;
        }
    }
    
    public static void main(String[] args){
        try{
            Server servidor = new Server();
            servidor.rodaServidor();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void rodaServidor() throws Exception {
        String textoRecebido = "";
        String textoEnviado = "Olá, cliente!";
        
        Scanner input = new Scanner(System.in);
        
        serverSocket = new ServerSocket(9600);
        
        System.out.println("Servidor iniciado: ");
        
        while(true){
            if(connect()){
                textoRecebido = Conexao.receber(socketClient);
                
                System.out.println("Cliente enviou: " + textoRecebido);
                System.out.println("\nDigite a sua mensagem: ");
                
                textoEnviado = input.nextLine();
                
                Conexao.enviar(socketClient, textoEnviado);
                socketClient.close();
            }
        }
    }
}
