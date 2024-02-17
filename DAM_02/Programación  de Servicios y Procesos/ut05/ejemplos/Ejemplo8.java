
package ut05.ejemplos;

import java.io.*;
import java.security.*;
import java.security.spec.*;

public class Ejemplo8 {
    public static void main(String[] args) {
        try{
            FileInputStream inpriv = new FileInputStream("clave.privada");
            byte[] bufferPriv = new byte[inpriv.available()];
            inpriv.read(bufferPriv);
            inpriv.close();
            
            PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(bufferPriv);
            KeyFactory keydSA = KeyFactory.getInstance("DSA");
            PrivateKey clavePrivada = keydSA.generatePrivate(clavePrivadaSpec);
            
            
        }catch(Exception e){
            
        }
    }
}
