package crazytacos;


public class Capacite {
    String nom;
    int pa;
    
    public Capacite(String nom, int pa){
        this.nom = nom;
        this.pa = pa;
    }
    
    public String stringCapacite(){
        return nom + " : " + pa + "PA";
    }
}
