
package ut04.propuestos.ejercicio03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class ClienteFTP {
    
        
    public static void main(String[] args) {
        
        String SERVER = "test.rebex.net";
        int PORT = 21;
        String USER = "demo";
        String PASS = "password";
        
        ClienteFTP cliente = new ClienteFTP(SERVER, PORT, USER, PASS);

        cliente.connect();
        cliente. login();
        
//        cliente.cd("/");
//
//        cliente.dir();
//
//        cliente.upload("archivo.txt", "archivo_remoto.txt");
//
//        cliente.download("archivo_descargado.txt","archivo_remoto.txt");
//
//        cliente.mkdir("nuevo_directorio");

        cliente.logout();
        cliente.disconnect();
    }
    
    private final String server;
    private final int port;
    private final String user;
    private final String pass;
    private final FTPClient FTPclient;
    
    ClienteFTP(String server, int port, String user, String pass){
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.FTPclient = new FTPClient();
    }

    public void disconnect() {
        try {
            FTPclient.disconnect();
            System.out.println("Desconectado");
        } catch (IOException ex) {
            System.out.println("Error de Disconnect");
        }
    }

    public void logout() {
        try {
            if (FTPclient.logout()){
                System.out.println("Sesion terminada correctamente");
            } else {
                System.out.println("Sesion NO terminada");
            }
        } catch (IOException ex) {
            System.out.println("Error de Logout");
        }
    }

    public void login() {
        try {
            if (FTPclient.login(user, pass)){
                System.out.println("Sesion iniciada correctamente");
                System.out.println(FTPclient.printWorkingDirectory());
            } else {
                System.out.println("Las credenciales son inválidas");
            }
        } catch (IOException ex) {
            System.out.println("Error de Login");
        }
    }

    public void connect() {
        try {
            FTPclient.connect(server, port);
            int replyCode = FTPclient.getReplyCode();

            if (FTPReply.isPositiveCompletion(replyCode)){
                System.out.println("Conectado");
            } else {
                System.out.println("Reply Code: " + replyCode);
            }
        } catch (IOException ex) {
            System.out.println("Error de Connect");
        }
    }

    public void mkdir(String nameNewDir){
        try {
            FTPclient.makeDirectory(nameNewDir);
            System.out.println("Directorio creado con éxito.");
        } catch (IOException ex) {
            System.out.println("Error de mkdir");
        }
    }

    public void download(String downloadFile, String remoteFile){
        try (
            OutputStream outputStream = new FileOutputStream(downloadFile);
        ){
            FTPclient.retrieveFile(remoteFile, outputStream);
            System.out.println("Archivo descargado con éxito.");
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo remoto no existente");
        } catch (IOException ex) {
            System.out.println("Error de download");
        }
    }

    public void upload(String uploadFile, String remoteFile){
        try (
            InputStream inputStream = new FileInputStream(uploadFile);
        ){
            FTPclient.storeFile(remoteFile, inputStream);
            System.out.println("Archivo subido con éxito.");
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo local no existente");
        } catch (IOException ex) {
            System.out.println("Error de upload");
        }
    }

    public void dir() {
        try {
            FTPFile[] files = FTPclient.listFiles();
            
            String tipos[] = {"Fichero","Directorio","Enlace"};

            for (FTPFile file : files) 
            {
                System.out.println(file.getName()+ " => " + tipos[file.getType()]);
            }
        } catch (IOException ex) {
            System.out.println("Error de dir");
        }
        
    }

    public void cd(String remoteDir){
        try {
            FTPclient.changeWorkingDirectory(remoteDir);
        } catch (IOException ex) {
            System.out.println("Error de cd");
        }
    }
}

