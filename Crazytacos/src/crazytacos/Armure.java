package crazytacos;

import java.util.EnumMap;

public class Armure extends Item {
    private EnumMap<Caracteristique, Integer> carac;
    
    public Armure(String nom, String classe, int vie, int force, int dext, int intell){
        super(nom, classe);
        this.carac = new EnumMap<>(Caracteristique.class);
        carac.put(Caracteristique.VITALITE, vie);
        carac.put(Caracteristique.FORCE, force);
        carac.put(Caracteristique.DEXTERITE, dext);
        carac.put(Caracteristique.INTELLIGENCE, intell);
    }
    
    public Armure(String nom, int vie, int force, int dext, int intell){
        super(nom);
        this.carac = new EnumMap<>(Caracteristique.class);
        carac.put(Caracteristique.VITALITE, vie);
        carac.put(Caracteristique.FORCE, force);
        carac.put(Caracteristique.DEXTERITE, dext);
        carac.put(Caracteristique.INTELLIGENCE, intell);
    }
}
