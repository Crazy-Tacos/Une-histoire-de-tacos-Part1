package rpgcommercial;//Test

import java.util.EnumMap;

public class Armure extends Item {
    private EnumMap<Caracteristique, Integer> carac;
        
    public Armure(String nom, int vie, int force, int dext, int intell){
        super(nom);
        this.carac = new EnumMap<>(Caracteristique.class);
        carac.put(Caracteristique.VITALITE, vie);
        carac.put(Caracteristique.FORCE, force);
        carac.put(Caracteristique.DEXTERITE, dext);
        carac.put(Caracteristique.INTELLIGENCE, intell);
    }
    
    public Armure(String nom, String ligne){
        super(nom);
        
        String lecture[];        
        lecture=ligne.split(" ");
        
        carac.put(Caracteristique.VITALITE, Integer.parseInt(lecture[0]));
        carac.put(Caracteristique.FORCE, Integer.parseInt(lecture[1]));
        carac.put(Caracteristique.DEXTERITE, Integer.parseInt(lecture[2]));
        carac.put(Caracteristique.INTELLIGENCE, Integer.parseInt(lecture[3]));        
    }
}
