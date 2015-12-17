package crazytacos;

import java.*;
import static java.lang.Math.pow;
import java.util.EnumMap;


public class Personnage {

    private String nom;
    private String image;
    private int niveau;
    private int experience;
    private int argent;
    private EnumMap<Caracteristique, Integer> carac;
    //private Caracteristique carac;

    public Personnage(String nom) {
        this.nom = nom;
        this.niveau = 1;
        this.experience = 0;
        this.argent = 0;
        this.carac = new EnumMap<>(Caracteristique.class);
        carac.put(Caracteristique.VITALITE, 100);
        carac.put(Caracteristique.FORCE, 10);
        carac.put(Caracteristique.DEXTERITE, 10);
        carac.put(Caracteristique.INTELLIGENCE, 10);
        carac.put(Caracteristique.PA, 2);
    }
    
    public void drawPersonnage(){
        System.out.println(nom);
        System.out.println("Niveau : " + niveau);
        System.out.println("XP : " + experience);
        System.out.println("Argent : " + argent);
        System.out.println("Vitalité : " + carac.get(Caracteristique.VITALITE));
        System.out.println("Force : " + carac.get(Caracteristique.FORCE));
        System.out.println("Dexterite : " + carac.get(Caracteristique.DEXTERITE));
        System.out.println("Intelligence : " + carac.get(Caracteristique.INTELLIGENCE));
        System.out.println("Points d'action : " + carac.get(Caracteristique.PA));
        System.out.println();
    }
    
    public void levelUp(int vie, int force, int dext, int intel, int pa){
        this.niveau++;
        carac.replace(Caracteristique.VITALITE, carac.get(Caracteristique.VITALITE) + vie);
        carac.replace(Caracteristique.FORCE, carac.get(Caracteristique.FORCE) + force);
        carac.replace(Caracteristique.DEXTERITE, carac.get(Caracteristique.DEXTERITE) + dext);
        carac.replace(Caracteristique.INTELLIGENCE, carac.get(Caracteristique.INTELLIGENCE) + intel);
        carac.replace(Caracteristique.PA, carac.get(Caracteristique.PA) + pa);
    }
    
    public int gagnerxp(int xp){
        experience += xp;
        int up = 0;
        while (experience >= pow(2,niveau + up)){
            experience -= pow(2,niveau + up);
            up ++;
        }
        return up;
    }
}
