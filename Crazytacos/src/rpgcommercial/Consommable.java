package rpgcommercial;


public class Consommable extends Item {
    
    public Consommable(String nom){
        super(nom);
    }
    
    public Consommable(String nom, String ligne){
        super(nom);
        
        String lecture[];        
        lecture=ligne.split(" ");
        
        /*this.dmin = Integer.parseInt(lecture[0]);
        this.dmax = Integer.parseInt(lecture[1]);
        this.ratio = Integer.parseInt(lecture[3]);
        this.carac = Integer.parseInt(lecture[4]); */       
    }
}
