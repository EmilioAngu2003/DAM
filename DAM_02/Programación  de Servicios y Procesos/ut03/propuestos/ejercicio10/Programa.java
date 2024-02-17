
package ut03.propuestos.ejercicio10;

import java.rmi.registry.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Programa {
    
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        Servidor();
        Cliente();
    }
    
    private static void Servidor(){
        new Thread(()->{
            try{
                Registry reg = LocateRegistry.createRegistry(5555);
                reg.rebind("Operaciones",(Calculacion)UnicastRemoteObject.exportObject(new Calculadora(),0));
            }catch(RemoteException e){
                System.out.println("Exception " + e);
            }
        }).start();
    }
    
    private static void Cliente(){
        new Thread(()->{
            try {
                Registry registry = LocateRegistry.getRegistry("localhost", 5555);
                Calculacion calculacion = (Calculacion)registry.lookup("Operaciones");
                if (calculacion!=null){
                    String input;
                    do{
                        calculacion.mostrarOperaciones();
                        input = input("Elige una operacion ('exit' para terminar): ");
                        if(!input.equalsIgnoreCase("exit"))
                        {
                            double a = inputDouble("Introduce el primer valor: ");
                            double b = inputDouble("Introduce el segundo valor: ");
                            calculacion.seleccionarOperacion(input, a, b);
                        }
                    }while(!input.equalsIgnoreCase("exit"));
                }
            }catch (NotBoundException | RemoteException e){
                System.out.println("RemoteException " + e);
            }
        }).start();
    }
    
    private static double inputDouble(String message){
        do{
            try{
                String value = input(message);
                return Double.parseDouble(value);
            }
            catch (NumberFormatException e) 
            {
                System.out.println("Introduce un numero valido");
            }
        }while(true);
    }
    
    private static String input(String message){
        System.out.print(message);
        return sc.next();
    }
}
