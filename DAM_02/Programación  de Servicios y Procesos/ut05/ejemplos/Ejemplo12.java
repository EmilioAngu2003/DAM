
package ut05.ejemplos;

import java.security.*;
import javax.crypto.*;

public class Ejemplo12 {
    public static void main(String[] args) {
        try {
            // Generación de par de claves RSA
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Generación de clave secreta AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();

            // Encriptado de la clave secreta con la clave RSA pública
            Cipher cipherRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipherRSA.init(Cipher.WRAP_MODE, publicKey);
            byte[] wrappedKey = cipherRSA.wrap(secretKey);

            // Cifrado del texto con la clave secreta
            Cipher cipherAES = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipherAES.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] textoPlano = "Esto es Texto Plano".getBytes();
            byte[] textoCifrado = cipherAES.doFinal(textoPlano);

            // Descifrado de la clave secreta con la clave RSA privada
            cipherRSA.init(Cipher.UNWRAP_MODE, privateKey);
            Key unwrappedKey = cipherRSA.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);

            // Descifrado del texto con la clave desencriptada
            cipherAES.init(Cipher.DECRYPT_MODE, unwrappedKey);
            byte[] textoDescifrado = cipherAES.doFinal(textoCifrado);
            
            // Impresión de resultados
            System.out.println("Texto Cifrado: " + new String(textoCifrado));
            System.out.println("Texto Descifrado: " + new String(textoDescifrado));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
