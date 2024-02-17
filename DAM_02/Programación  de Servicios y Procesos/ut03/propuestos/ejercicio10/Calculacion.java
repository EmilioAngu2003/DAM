
package ut03.propuestos.ejercicio10;

import java.rmi.RemoteException;
import java.rmi.Remote;

public interface Calculacion extends Remote {
     public double suma (double a, double b) throws RemoteException; 
     public double resta (double a, double b) throws RemoteException; 
     public double multiplicacion (double a, double b) throws RemoteException; 
     public double division (double a, double b) throws RemoteException; 
     public double modulo (double a, double b) throws RemoteException; 
     public double potencia (double a, double b) throws RemoteException;
     public void mostrarOperaciones () throws RemoteException; 
     public double seleccionarOperacion (String operacion, double a, double b) throws RemoteException; 
}

