package crazytacos;


public class Pharmacien extends Personnage {

    public Pharmacien(String nom) {
        super(nom);
        setClasse("Pharmacien");
        addCapacite(new Capacite("Stéroide"));
        addCapacite(new Capacite("Bandage"));
    }

}
