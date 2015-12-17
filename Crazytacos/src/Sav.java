package crazytacos;


public class Sav extends Personnage {

    public Sav(String nom) {
        super(nom);
    }
    
    public void levelUp(int nbniveau){
        for (int i =0; i<nbniveau;i++){
            super.levelUp(40, 1, 1, 1, 1);
        }
    }
}
