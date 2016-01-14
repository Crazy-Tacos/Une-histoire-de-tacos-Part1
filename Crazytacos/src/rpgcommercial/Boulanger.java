package rpgcommercial;

import java.io.BufferedReader;


public class Boulanger extends Personnage {

    public Boulanger(String nom) {
        super(nom);
        setClasse("Boulanger");
    }
    
    public Boulanger(BufferedReader br) {
        super(br);
        setClasse("Boulanger");
    }
    
    
}
