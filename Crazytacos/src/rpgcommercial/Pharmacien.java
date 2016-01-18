package rpgcommercial;

import java.io.BufferedReader;


public class Pharmacien extends Personnage {

    public Pharmacien(String nom) {
        super(nom);
        classe="Pharmacien";
        carac.replace(Caracteristique.INTELLIGENCE, 20);
    }
    
    public Pharmacien(BufferedReader br) {
        super(br);
        classe="Pharmacien";
        carac.replace(Caracteristique.INTELLIGENCE, 20);
    }

}
