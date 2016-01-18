package rpgcommercial;

import java.io.BufferedReader;


public class Boulanger extends Personnage {

    public Boulanger(String nom) {
        super(nom);
        classe="Boulanger";
        carac.replace(Caracteristique.FORCE, 20);
    }
    
    public Boulanger(BufferedReader br) {
        super(br);
        classe="Boulanger";
        carac.replace(Caracteristique.FORCE, 20);
    }
    
    
}
