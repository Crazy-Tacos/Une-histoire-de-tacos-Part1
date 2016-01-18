package rpgcommercial;

import java.io.BufferedReader;


public class Cuisinier extends Personnage {

    public Cuisinier(String nom) {
        super(nom);
        classe="Cuisinier";
        carac.replace(Caracteristique.DEXTERITE, 20);
    }
    
    public Cuisinier(BufferedReader br) {
        super(br);
        classe="Cuisinier";
        carac.replace(Caracteristique.DEXTERITE, 20);
    }

}
