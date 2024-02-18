package ut05.ejercicios;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

public class Ejercicio05 {

    static final String NOMBRE_ARCHIVO = "Notificacion_1703250597141.pdf";
    static final String NOMBRE_ARCHIVO_FIRMADO = "Archivo_Firmado.txt";
    static final String DIR_ARCHIVO = System.getProperty("user.home") + File.separator + "Documents" + File.separator + NOMBRE_ARCHIVO;
    static final String DIR_ARCHIVO_FIRMADO = System.getProperty("user.home") + File.separator + "Documents" + File.separator + NOMBRE_ARCHIVO_FIRMADO;

    public static void main(String[] args) {
        try {
            // generar claves
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024, secureRandom);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey clavePrivada = keyPair.getPrivate();
            PublicKey clavePublica = keyPair.getPublic();

            //firmar datos
            byte[] datosArchivo = Files.readAllBytes(Paths.get(DIR_ARCHIVO));
            Signature firma = Signature.getInstance("SHA256withRSA");
            firma.initSign(clavePrivada);
            firma.update(datosArchivo);
            byte[] datosFirmados = firma.sign();

            //guardar archivo firmado
            FileOutputStream fos = new FileOutputStream(DIR_ARCHIVO_FIRMADO);
            fos.write(datosFirmados);
            fos.close();

            // verificacion
            boolean firmaValida = verificarFirma(DIR_ARCHIVO, DIR_ARCHIVO_FIRMADO, clavePublica);
            System.out.println("La firma es valida: " + firmaValida);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
        }
    }

    private static boolean verificarFirma(String archivo, String archivoFirmado, PublicKey clavePublica) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException {
        byte[] datosArchivo = Files.readAllBytes(Paths.get(archivo));
        byte[] datosFirmados = Files.readAllBytes(Paths.get(archivoFirmado));

        Signature verificacion = Signature.getInstance("SHA256withRSA");
        verificacion.initVerify(clavePublica);
        verificacion.update(datosArchivo);
        return verificacion.verify(datosFirmados);
    }
}
