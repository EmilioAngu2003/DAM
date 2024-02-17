
package ut02.monitores_v2;

class Puente {
    
    private final Barrera bIzq;
    private final Barrera bDer;
    private final int maxCoches;
    private final int millis;
    
    public Puente(int maxCoches, int millis){
        this.maxCoches = maxCoches;
        this.millis = millis;
        this.bIzq = new Barrera(maxCoches);
        this.bDer = new Barrera(maxCoches);
    }
    
    public void pasar(Coche c) {
        
        Barrera b = switch(c.getDireccion()){
            case "->" -> bDer;
            case "<-" -> bIzq;
            default -> null;
        };
        
        b.add(c);
        
        cruzando(b.get());
        
        b.update();
    }
    
    private void cruzando(Coche c){

        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {}
        
        System.out.println( c.toString() + " | CRUZO");
    }
}