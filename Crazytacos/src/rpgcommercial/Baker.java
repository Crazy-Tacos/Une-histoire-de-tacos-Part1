package rpgcommercial;

import java.io.BufferedReader;


public class Baker extends Caracter {

    public Baker(String nom) {
        super(nom);
        classe="Boulanger";
        carac.replace(Caracteristique.FORCE, 20);
    }
    
    public Baker(BufferedReader br) {
        super(br);
        classe="Boulanger";
        carac.replace(Caracteristique.FORCE, 20);
    }
    
    
}
