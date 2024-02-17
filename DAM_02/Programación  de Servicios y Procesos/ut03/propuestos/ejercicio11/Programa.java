
package ut03.propuestos.ejercicio11;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Programa {
    
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        servidor();
        cliente();
    }

    static void servidor(){
        new Thread(()->{
            try {
                Registry registry = LocateRegistry.createRegistry(5555);
                registry.rebind("CommandExecutor", (RemoteCommandExecutor)UnicastRemoteObject.exportObject(new CommandExecutor(), 0));
                System.out.println("Servidor listo para recibir comandos...");
            } catch (RemoteException e) {
                System.err.println("Error en el servidor: " + e.getMessage());
            }
        }).start();
    }
    
    static void cliente(){
        new Thread(()->{
            try {
                Registry registry = LocateRegistry.getRegistry("localhost", 5555);
                RemoteCommandExecutor commandExecutor = (RemoteCommandExecutor) registry.lookup("CommandExecutor");
                String command;
                do{
                    command = sc.nextLine();
                    if(!command.equalsIgnoreCase("exit"))
                    {
                        System.out.println(commandExecutor.executeCommand(command));
                    }
                }while(!command.equalsIgnoreCase("exit"));
                
            } catch (NotBoundException | RemoteException e) {
                System.err.println("Error en el cliente: " + e.getMessage());
            }
        }).start();
    }
}
