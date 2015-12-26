package crazytacos;


public class Pharmacien extends Personnage {

    public Pharmacien(String nom) {
        super(nom);
        addCapacite(new Capacite("Stéroide", 2));
        addCapacite(new Capacite("Bandage", 2));
    }
    public void levelUp(int nbniveau){
        for (int i =0; i<nbniveau;i++){
            super.levelUp(20, 1, 1, 1, 3);
        }
    }
    
}
