package rpgcommercial;

import java.io.BufferedReader;


public class Sav extends Personnage {

    public Sav(String nom) {
        super(nom);
        setClasse("SAV");
    }
    
    public Sav(BufferedReader br) {
        super(br);
        setClasse("SAV");
    }

}
