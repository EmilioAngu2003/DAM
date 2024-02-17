
package ut05.ejemplos;

public class Ejemplo1 {
    public static void main(String[] args) {
        String t[]= {"java.class.path","java.home","java.vendor", "java.version",
                    "os.name","os.vesion","user.dir","user.home","user.name"};
 
        for (int i = 0; i < t.length; i++) {
            System.out.println("Propiedad: "+ t[i]);
            try{
                System.out.println("\t" + System.getProperty(t[i]));
            }catch(Exception e){
                System.out.println(""+e);
            }
            
        }
        }
    
    
}
