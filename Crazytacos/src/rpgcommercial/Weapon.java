package rpgcommercial;

import java.io.PrintWriter;

public class Weapon extends Item {
    protected int dmin;
    protected int dmax;
    protected int ratio;
    protected int carac;
    
    // Carac : A = VITA, 2 = FORCE, 3 = DEXT, 4 = VITA
    // Ratio : Pourcentage de la caractéristique
    public Weapon(String nom, int dmin, int dmax, int ratio, int carac){
        super(nom);
        this.dmin = dmin;
        this.dmax = dmax;
        this.ratio = ratio;
        this.carac = carac;
    }
    
    public Weapon(String[] lecture){
        super(lecture[0].replace("_", " "));
        
        this.dmin = Integer.parseInt(lecture[1]);
        this.dmax = Integer.parseInt(lecture[2]);
        this.ratio = Integer.parseInt(lecture[3]);
        this.carac = Integer.parseInt(lecture[4]);        
    }
    
    
    public void drawWeapon(View v){
        v.addString("  " +nom);
        v.addString("  Dégats : "+ dmin + "-" + dmax);
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
    
    public void save(PrintWriter fichierSortie){
        if (this != null){
            fichierSortie.println(this.nom.replace(" ", "_") + " " + dmin + " "+ dmax+ " " + ratio+ " " + carac);
        }
        else{
            fichierSortie.println();
        }
    }
}
