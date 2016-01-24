package rpgcommercial;

import java.io.PrintWriter;

public class SecondWeapon extends Weapon {
    private int munitions;

    public SecondWeapon(String[] lecture) {
        super(lecture);
        this.munitions = Integer.parseInt(lecture[5]);
    }
    
    public int getMunitions(){
        return munitions;
    }
    
    public boolean useMunition(){
        // Return vrai si l'arme n'a plus de munitions
        munitions--;
        if (munitions <= 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void drawWeapon(View v){
        super.drawWeapon(v);
        v.addString("  Munitions restantes : "+ munitions);
    }
    
    public void save(PrintWriter fichierSortie){
        if (this != null){
            fichierSortie.println(this.nom.replace(" ", "_") + " " + dmin + " "+ dmax + " " + ratio + " " + carac + " " + munitions );
        }
        else{
            fichierSortie.println();
        }
    }
    
}
