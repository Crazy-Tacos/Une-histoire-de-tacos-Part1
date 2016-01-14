package rpgcommercial;

import java.io.BufferedReader;


public class Pharmacien extends Personnage {

    public Pharmacien(String nom) {
        super(nom);
        setClasse("Pharmacien");
    }
    
    public Pharmacien(BufferedReader br) {
        super(br);
        setClasse("Pharmacien");
    }

}
