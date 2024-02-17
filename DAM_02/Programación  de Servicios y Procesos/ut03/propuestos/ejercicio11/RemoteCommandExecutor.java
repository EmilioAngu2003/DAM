
package ut03.propuestos.ejercicio11;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCommandExecutor extends Remote {
    String executeCommand(String command) throws RemoteException;
}
