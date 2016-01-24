package rpgcommercial;

import java.io.BufferedReader;


public class Sav extends Caracter {

    public Sav(String nom) {
        super(nom);
        classe="SAV";
        carac.replace(Caracteristique.VITALITE, 65);
    }
    
    public Sav(BufferedReader br) {
        super(br);
        classe="SAV";
        carac.replace(Caracteristique.VITALITE, 65);
    }

}
