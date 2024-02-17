package ut03.propuestos.ejercicio11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandExecutor implements RemoteCommandExecutor {
    
    String[] comands = {"cmd.exe", "/c"};
    
    @Override
    public String executeCommand(String command) throws RemoteException {
        try {
            List<String> cmd = new ArrayList<>();
            cmd.addAll(Arrays.asList(comands));
            cmd.addAll(Arrays.asList(command.split(" ")));
            ProcessBuilder pb = new ProcessBuilder(cmd);
            Process proceso = pb.start();
            StringBuilder sb = new StringBuilder();
            try (
                InputStream is = proceso.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
            ) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    sb.append(linea).append("\n");
                }
            }

            if (proceso.waitFor() == 0) {
                return sb.toString();
            } else {
                return "Error al ejecutar el comando";
            }
        } catch (IOException | InterruptedException e) {
            return "Error al ejecutar el comando " + e.getMessage();
        }
    }
}
