package crazytacos;


public class Boulanger extends Personnage {

    public Boulanger(String nom) {
        super(nom);
        
    }
    
    public void levelUp(int nbniveau){
        for (int i =0; i<nbniveau;i++){
            super.levelUp(20, 3, 1, 1, 1);
        }
    }
}
