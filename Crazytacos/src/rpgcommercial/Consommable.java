package rpgcommercial;

import java.io.PrintWriter;


public class Consommable extends Arme {
    private boolean soin;
    
    public Consommable(String[] lecture){
        super(lecture);
        
        if (lecture.length >5 && "soin".equals(lecture[5])){
            soin = true;
        }
        else{
            soin = false;
        }
    }
    
    public boolean isSoin(){
        return soin;
    }
    
    public void drawCompetence(Vue v){
        v.addChaine("  " +getNom());
        if (soin){
            v.addChaine("  Soins : ");
        }
        else {
            v.addChaine("  Dégats : ");
        }
        v.concatLastLigne(getDmin() + "-" + getDmax());
        String car = "";
        if (getCarac() == 1){ // Vitalite
            car = "% de la VITALITE";
        }
        else if (getCarac() == 2){ // Force
            car = "% de la FORCE";
        }
        else if (getCarac() == 3){ // Dexterite
            car = "% de la DEXTERITE";
        }
        else if (getCarac() == 4){ // Intelligence
            car = "% de l'INTELLIGENCE";
        }
        v.concatLastLigne(" + "+ getRatio() + car);
    }
    
    public void sauvegarder(PrintWriter fichierSortie){
        if (this != null){
            String str = this.getNom().replace(" ", "_") + " " + getDmin() + " "+ getDmax()+ " " + getRatio()+ " " + getCarac();
            if (soin){
                str += " soin";
            }
            fichierSortie.println(str);
        }
        else{
            fichierSortie.println();
        }
    }
}

