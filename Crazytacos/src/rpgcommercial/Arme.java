package rpgcommercial;

import java.io.PrintWriter;

public class Arme extends Item {
    private int dmin;
    private int dmax;
    private int ratio;
    private int carac;
    
    // Carac : A = VITA, 2 = FORCE, 3 = DEXT, 4 = VITA
    // Ratio : Pourcentage de la caractéristique
    public Arme(String nom, int dmin, int dmax, int ratio, int carac){
        super(nom);
        this.dmin = dmin;
        this.dmax = dmax;
        this.ratio = ratio;
        this.carac = carac;
    }
    
    public Arme(String[] lecture){
        super(lecture[0].replace("_", " "));
        
        this.dmin = Integer.parseInt(lecture[1]);
        this.dmax = Integer.parseInt(lecture[2]);
        this.ratio = Integer.parseInt(lecture[3]);
        this.carac = Integer.parseInt(lecture[4]);        
    }
    
    public int getDmin() {
        return dmin;
    }
    public int getDmax() {
        return dmax;
    }
    public int getRatio() {
        return ratio;
    }
    public int getCarac() {
        return carac;
    }
    
    public void drawArme(Vue v){
        v.addChaine("  " +getNom());
        v.addChaine("  Dégats : "+ dmin + "-" + dmax);
        String car = "";
        if (carac == 1){ // Vitalite
            car = "% de la VITALITE";
        }
        else if (carac == 2){ // Force
            car = "% de la FORCE";
        }
        else if (carac == 3){ // Dexterite
            car = "% de la DEXTERITE";
        }
        else if (carac == 4){ // Intelligence
            car = "% de l'INTELLIGENCE";
        }
        v.concatLastLigne(" + "+ ratio + car);
    }
    
    public void sauvegarder(PrintWriter fichierSortie){
        if (this != null){
            fichierSortie.println(this.getNom().replace(" ", "_") + " " + dmin + " "+ dmax+ " " + ratio+ " " + carac);
        }
        else{
            fichierSortie.println();
        }
    }
}
