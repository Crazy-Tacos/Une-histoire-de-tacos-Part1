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
    
    public void drawInventaire(Vue v){
        if(armePrincipale != null){
            v.addChaine("Arme principale :");
            armePrincipale.drawArme(v);
        }
        
        if(armeSecondaire != null){
            v.addChaine("Arme secondaire :");
            armeSecondaire.drawArme(v);
        }
        if(armure != null){
            v.addChaine("Armure :");
            
        }
        if(consommable != null){
            v.addChaine("Consommable :");
            
        }
    }
    
    public void sauvegarder(PrintWriter fichierSortie){
        fichierSortie.print("armePrincipale=");
        if (armePrincipale != null){
            armePrincipale.sauvegarder(fichierSortie);
        }
        else{
            fichierSortie.println();
        }
        
        fichierSortie.print("armeSecondaire=");
        if (armeSecondaire != null){
            armeSecondaire.sauvegarder(fichierSortie);
        }
        else{
            fichierSortie.println();
        }

        fichierSortie.print("armure=");
        if (armure != null){
            armure.sauvegarder(fichierSortie);
        }
        else{
            fichierSortie.println();
        }

        fichierSortie.print("consommable=");
        if (consommable != null){
            consommable.sauvegarder(fichierSortie);
        }
        else{
            fichierSortie.println();
        }
    }
    
}
