
package ut03.propuestos.ejercicio10;

import java.rmi.RemoteException;

public class Calculadora implements Calculacion{

    @Override
    public double suma(double a, double b) throws RemoteException {
        double c = a + b;
        System.out.println ("Suma " + a + " + " + + b + " = " + c);
        return c;
    }

    @Override
    public double resta(double a, double b) throws RemoteException {
        double c = a - b;
        System.out.println ("Resta " + a + " . " + + b + " = " + c);
        return c;
    }

    @Override
    public double multiplicacion(double a, double b) throws RemoteException {
        double c = a * b;
        System.out.println ("Multiplicacion " + a + " * " + b + " = " + c);
        return c;
    }

    @Override
    public double division(double a, double b) throws RemoteException {
        double c = a / b;
        System.out.println ("Division " + a + " / " + b + " = " + c);
        return a/b;
    }

    @Override
    public double modulo(double a, double b) throws RemoteException {
        double c = a % b;
        System.out.println ("Modulo " + a + " % " + b + " = " + c);
        return c;
    }

    @Override
    public double potencia(double a, double b) throws RemoteException {
        double c = Math.pow(a, b);
        System.out.println ("Potencia " + a + " ^ " + b + " = " + c);
        return c;
    }
    
    @Override
    public void mostrarOperaciones() throws RemoteException {
        System.out.println("""
                           Operaciones:
                           - Suma
                           - Resta
                           - Multiplicacion
                           - Division
                           - Modulo
                           - Potencia
                           """);
    }

    @Override
    public double seleccionarOperacion(String operacion, double a, double b) throws RemoteException {
        String toLowerCase = operacion.toLowerCase();
        switch(toLowerCase){
            case "suma" -> {
                return suma(a, b);
            }
            case "resta" -> {
                return resta(a, b);
            }
            case "multiplicacion" -> {
                return multiplicacion(a, b);
            }
            case "division" -> {
                return division(a, b);
            }
            case "modulo" -> {
                return modulo(a, b);
            }
            case "potencia" -> {
                return potencia(a, b);
            }
            default -> {
                return 0;
            }
        }
    }
}
