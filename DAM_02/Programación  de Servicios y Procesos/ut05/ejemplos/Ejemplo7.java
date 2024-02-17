package ut05.ejemplos;

import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejemplo7 {
    public static void main(String[] args) {
       try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
           //Inicialización del generador
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            keyPairGenerator.initialize(1024, secureRandom);

            //Creación de las claves
            KeyPair keyPar = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPar.getPrivate();
            PublicKey publicKey = keyPar.getPublic();

            //Firma con clave privada
            Signature signature = Signature.getInstance("SHA1withDSA");
            signature.initSign(privateKey);
            String mensaje = "Este es un mensaje firmado";
            signature.update(mensaje.getBytes());

            byte[] firma = signature.sign();

            System.out.println("Firma: "+firma.toString());
            System.out.println("Firma hexadecimal: "+Hexadecimal(firma));
            //
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Ejemplo7.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(Ejemplo7.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     static String Hexadecimal(byte[] resumen){
        String hex="";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i]& 0xFF);
            if(h.length()== 1) hex +="0";
            hex +=h;
        }
        return hex.toUpperCase();
    }
}
