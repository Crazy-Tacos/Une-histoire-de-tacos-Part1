package crazytacos;


public class Cuisinier extends Personnage {

    public Cuisinier(String nom) {
        super(nom);
        
    }
    
    public void levelUp(int nbniveau){
        for (int i =0; i<nbniveau;i++){
            super.levelUp(20, 1, 3, 1, 1);
        }
    }
}
