package rpgcommercial;

import java.io.BufferedReader;


public class Cook extends Caracter {

    public Cook(String nom) {
        super(nom);
        classe="Cuisinier";
        carac.replace(Caracteristique.DEXTERITE, 20);
    }
    
    public Cook(BufferedReader br) {
        super(br);
        classe="Cuisinier";
        carac.replace(Caracteristique.DEXTERITE, 20);
    }

}
