package crazytacos;


public class Capacite {
    String nom;
    int dmin;
    int dmax;
    int ratio;
    int carac;
    
    public Capacite(String nom){
        this.nom = nom;
        this.dmin = 0;
        this.dmax = 0;
        this.ratio = 0;
        this.carac = 0;
    }
    
    // 1 : VITALITE, 2 : FORCE, 3 : DEXTERITE, 4 : INTELLIGENCE
    public Capacite(){
        this.nom = "Main nue";
        this.dmin = 1;
        this.dmax = 3;
        this.ratio = 5;
        this.carac = 2;
        // 1 à 3 dégats + 5% de la force
    }
    
    public Capacite(String nom, int dmin, int dmax, int ratio, int carac){
        this.nom = nom;
        this.dmin = dmin;
        this.dmax = dmax;
        this.ratio = ratio;
        this.carac = carac;
    }
    
    public String stringCapacite(){
        return nom;
    }
    
    public int getDmin(){
        return dmin;
    }
    
    public int getDmax(){
        return dmax;
    }
    
    public int getRatio(){
        return ratio;
    }
    
    public int getCarac(){
        return carac;
    }
}
