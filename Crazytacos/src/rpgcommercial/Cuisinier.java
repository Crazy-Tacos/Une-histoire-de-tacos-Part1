package rpgcommercial;

import java.io.BufferedReader;


public class Cuisinier extends Personnage {

    public Cuisinier(String nom) {
        super(nom);
        setClasse("Cuisinier");        
    }
    
    public Cuisinier(BufferedReader br) {
        super(br);
        setClasse("Cuisinier");        
    }

}
