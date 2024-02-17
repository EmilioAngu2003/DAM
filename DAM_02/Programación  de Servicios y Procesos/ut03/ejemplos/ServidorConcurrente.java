/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ut03.ejemplos;

/**
 *
 * @author Fran Huertas
 */
import java.io.*;
import java.net.*;





public class ServidorConcurrente {

    public static void main(String[] args) {

        ServerSocket servicio = null;
        Socket socketServicio = null;

        int i = 0;


        try{
                // Crear socket servidor
                servicio= new ServerSocket(9999);

        } catch (IOException e) {
                System.out.println(e);
        }
        try {
            System.out.println("Esperando clientes: ");
            while (true) {
                // Aceptar un cliente
                socketServicio= servicio.accept();
                System.out.println("Cliente " + i);
                i++;
                new GestionServicio(socketServicio).start();
                socketServicio = null;

            }


        } catch (IOException e) {
            System.out.println(e);
            System.exit(-1);
        }
    }
}

