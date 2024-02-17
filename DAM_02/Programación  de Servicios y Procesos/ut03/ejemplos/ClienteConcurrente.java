/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ejemplos;

/**
 *
 * @author Fran Huertas
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClienteConcurrente {

    public static void main(String[] args) {

        Socket cliente = null;
        DataInputStream entrada;
        DataOutputStream salida;
        String cadena;
        Scanner teclado = new Scanner(System.in);
        try {
            // Crear socket cliente
            cliente = new Socket("localhost", 9999);

            // Obtener los InputStream y/o OutputStream del servidor
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            // Envio el identificador
            salida.writeUTF("ID123");
            do {
                System.out.println("Escribe una cadena: ");
                cadena = teclado.nextLine();
                salida.writeUTF(cadena);
            } while (!cadena.equals("adios"));
            // Cerrar los canales de entrada, salida y el socket cliente
            salida.close();
            entrada.close();
            cliente.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
