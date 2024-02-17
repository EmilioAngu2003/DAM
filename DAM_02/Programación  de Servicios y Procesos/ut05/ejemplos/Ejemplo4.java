
package ut05.ejemplos;

import java.security.*;
public class Ejemplo4 {
    public static void main(String[] args) {
        MessageDigest messageDigest;
        try{
            messageDigest = MessageDigest.getInstance("MD5");
            String texto = "Esto es un texto plano.";
            byte dataBytes[] = texto.getBytes();
            messageDigest.update(dataBytes);
            byte resume[] = messageDigest.digest(); //Calcula el resumen
            System.out.println("Original: "+ texto);
            System.out.println("Numero de Bytes: "+ messageDigest.getDigestLength());
            System.out.println("Algoritmo: "+messageDigest.getAlgorithm());
            System.out.println("Mensaje resumen: "+ new String(resume));
            System.out.println("Hexadecimal: " + Hexadecimal(resume));
            Provider proveedor = messageDigest.getProvider();
            System.out.println("Proveedor: "+proveedor.toString());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
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
