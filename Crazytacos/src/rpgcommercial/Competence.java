package rpgcommercial;

import java.io.PrintWriter;


public class Competence extends Arme {
    private boolean soin;
    
    public Competence(String nom, int dmin, int dmax, int ratio, int carac, boolean soin){
        super(nom,dmin,dmax,ratio,carac);
        this. soin = soin;
    }
    
    public Competence(String[] lecture){
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
        v.addChaine("  " +nom);
        if (soin){
            v.addChaine("  Soins : ");
        }
        else {
            v.addChaine("  Dégats : ");
        }
        v.concatLastLigne(dmin + "-" + dmax);
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
            String str = this.nom.replace(" ", "_") + " " + dmin + " "+ dmax+ " " + ratio+ " " + carac;
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

