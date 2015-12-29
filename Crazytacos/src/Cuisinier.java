package crazytacos;


public class Cuisinier extends Personnage {

    public Cuisinier(String nom) {
        super(nom);
        setClasse("Cuisinier");
        addCapacite(new Capacite("Lancer de tacos"));
        
    }

}
