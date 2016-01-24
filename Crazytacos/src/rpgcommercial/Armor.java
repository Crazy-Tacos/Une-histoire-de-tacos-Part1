package rpgcommercial;//Test

import java.io.PrintWriter;
import java.util.EnumMap;

public class Armor extends Item {
    private EnumMap<Caracteristique, Integer> carac;
    private int durabilite;
        
    public Armor(String nom, int vie, int force, int dext, int intell, int dura){
        super(nom);
        this.carac = new EnumMap<>(Caracteristique.class);
        carac.put(Caracteristique.VITALITE, vie);
        carac.put(Caracteristique.FORCE, force);
        carac.put(Caracteristique.DEXTERITE, dext);
        carac.put(Caracteristique.INTELLIGENCE, intell);
        this.durabilite = dura;
    }
     
    public Armor(String[] lecture){
        super(lecture[0].replace("_", " "));
        this.carac = new EnumMap<>(Caracteristique.class);
        carac.put(Caracteristique.VITALITE, Integer.parseInt(lecture[1]));
        carac.put(Caracteristique.FORCE, Integer.parseInt(lecture[2]));
        carac.put(Caracteristique.DEXTERITE, Integer.parseInt(lecture[3]));
        carac.put(Caracteristique.INTELLIGENCE, Integer.parseInt(lecture[4])); 
        this.durabilite = Integer.parseInt(lecture[5]);
    }
    
    public int getVitalite(){
        return carac.get(Caracteristique.VITALITE);
    }
    public int getForce(){
        return carac.get(Caracteristique.FORCE);
    }
    public int getDexterite(){
        return carac.get(Caracteristique.DEXTERITE);
    }
    public int getIntell(){
        return carac.get(Caracteristique.INTELLIGENCE);
    }
    public int getDurabilite(){
        return durabilite;
    }
    
    public void drawArmor(View v){
        v.addString("  "+nom);
        v.addString("  Vitalité : " +carac.get(Caracteristique.VITALITE));
        v.addString("  Force : " +carac.get(Caracteristique.FORCE));
        v.addString("  Dexterité : " +carac.get(Caracteristique.DEXTERITE));
        v.addString("  Intelligence : " +carac.get(Caracteristique.INTELLIGENCE));
        v.addString("  Durabilité : " +durabilite +" points de dégats");
    }
    
    // return true si l'armure est cassée, faux sinon
    public boolean attackArmor(int degats){
        this.durabilite -= degats;
        if (durabilite <=0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void save(PrintWriter fichierSortie){
        if (this != null){
            fichierSortie.println(this.nom.replace(" ", "_") + " "+ carac.get(Caracteristique.VITALITE) + " "+carac.get(Caracteristique.FORCE) +" "+ carac.get(Caracteristique.DEXTERITE) + " "+carac.get(Caracteristique.INTELLIGENCE)+" "+durabilite);        
        }
        else{
            fichierSortie.println();
        }
    }
}
