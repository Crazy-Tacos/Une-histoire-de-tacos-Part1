package rpgcommercial;

import java.io.PrintWriter;

public class ArmeSecondaire extends Arme {
    private int munitions;

    public ArmeSecondaire(String[] lecture) {
        super(lecture);
        this.munitions = Integer.parseInt(lecture[5]);
    }
    
    public int getMunitions(){
        return munitions;
    }
    
    public boolean utiliserMunition(){
        // Return vrai si l'arme n'a plus de munitions
        munitions--;
        if (munitions <= 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void drawArme(Vue v){
        super.drawArme(v);
        v.addChaine("  Munitions restantes : "+ munitions);
    }
    
    public void sauvegarder(PrintWriter fichierSortie){
        if (this != null){
            fichierSortie.println(this.getNom().replace(" ", "_") + " " + getDmin() + " "+ getDmax() + " " + getRatio() + " " + getCarac() + " " + munitions );
        }
        else{
            fichierSortie.println();
        }
    }
    
}
