package rpgcommercial;

import java.io.PrintWriter;

public class Inventaire {
    private Arme armePrincipale;
    private Arme armeSecondaire;
    private Armure armure;
    private Consommable consommable;
    
    public Inventaire(){
        this.armePrincipale =new Arme("Mains nues", 1, 3, 5, 2);
        this.armeSecondaire = null;
        this.armure = null;
        this.consommable = null;
    }
    
    public void setArmePrincipale(Arme a){
        this.armePrincipale = a;
    }
    
    public void setArmeSecondaire(Arme a){
        this.armeSecondaire = a;
    }
    
    public void setArmure(Armure a){
        this.armure = a;
    }
    
    public void setConsommable(Consommable c){
        this.consommable = c;
    }
    
    public Arme getArmePrincipale(){
        return armePrincipale;
    }
    
    public Arme getArmeSecondaire(){
        return armeSecondaire;
    }
    
    public Armure getArmure(){
        return armure;
    }
    
    public Consommable getConsommable(){
        return consommable;
    }
    
    public void sauvegarder(PrintWriter fichierSortie){
        fichierSortie.println("armePrincipale=");
        armePrincipale.sauvegarder(fichierSortie);
        
        fichierSortie.println("armeSecondaire=");
        armeSecondaire.sauvegarder(fichierSortie);
        
        fichierSortie.println("armure=");
        armure.sauvegarder(fichierSortie);
        
        fichierSortie.println("consommable=");
        consommable.sauvegarder(fichierSortie);
    }
    
}
