package crazytacos;

import java.*;
import static java.lang.Math.pow;
import java.util.EnumMap;


public class Personnage {

    private String nom;
    private int niveau;
    private int experience;
    private int argent;
    private EnumMap<Caracteristique, Integer> carac;
    private Capacite[] capacites;

    // Constructeur du joueur
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
        capacites = new Capacite[4];
        capacites[0] = new Capacite("Auto attaque", 1);
    }
    
    // Constructeur d'un ennemi
    public Personnage(String nom, int niv, int exp, int arg, int vie, int force, int dext, int intell, int pa) {
        this.nom = nom;
        this.niveau = niv;
        this.experience = exp;
        this.argent = arg;
        this.carac = new EnumMap<>(Caracteristique.class);
        carac.put(Caracteristique.VITALITE, vie);
        carac.put(Caracteristique.FORCE, force);
        carac.put(Caracteristique.DEXTERITE, dext);
        carac.put(Caracteristique.INTELLIGENCE, intell);
        carac.put(Caracteristique.PA, pa);
        capacites = new Capacite[4];
        capacites[0] = new Capacite("Auto attaque", 1);
    }
    
    public String[] stringPersonnage(int ligne){
        String[] str = new String[20];
        for (int i = 0; i<20; i++){
            str[i] = "";
        }
        if (ligne + 8 <20){
            str[ligne + 0] = "Personnage : " + nom;
            str[ligne + 1] = "Niveau : " + niveau;
            str[ligne + 2] = "XP : " + experience;
            str[ligne + 3] = "Argent : " + argent;
            str[ligne + 4] = "Vitalité : " + carac.get(Caracteristique.VITALITE);
            str[ligne + 5] = "Force : " + carac.get(Caracteristique.FORCE);
            str[ligne + 6] = "Dexterite : " + carac.get(Caracteristique.DEXTERITE);
            str[ligne + 7] = "Intelligence : " + carac.get(Caracteristique.INTELLIGENCE);
            str[ligne + 8] = "Points d'action : " + carac.get(Caracteristique.PA);
            int j = 0;
            while(capacites[j] != null && j < 4){
                str[ligne + 9 + j] = capacites[j].stringCapacite();
                j++;
            }
        }
        return str;
    }
    
    public void drawPersonnage(Jeu j,int ligne){
        j.addChaine(stringPersonnage(ligne));
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
    
    public String getNom(){
        return nom;
    }
    
    public void addCapacite(Capacite c){
        int i = 0;
        while (capacites[i] != null){
            i++;
        }
        if (i<4){
            capacites[i] = c;
        }
    }
}
