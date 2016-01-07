package crazytacos;

import java.*;
import static java.lang.Math.pow;
import java.util.EnumMap;
import java.util.Random;


public class Personnage {

    private String nom;
    private String classe;
    private int niveau;
    private int experience;
    private int argent;
    private int vie;
    private EnumMap<Caracteristique, Integer> carac;
    private Capacite[] capacites;
    private Arme arme;
    private Armure armure;

    // Constructeur du joueur
    public Personnage(String nom) {
        this.nom = nom;
        this.classe = "";
        this.niveau = 1;
        this.experience = 0;
        this.argent = 0;
        this.arme = null;
        this.armure = null;
        this.carac = new EnumMap<>(Caracteristique.class);
        carac.put(Caracteristique.VITALITE, 50);
        this.vie = 50;
        carac.put(Caracteristique.FORCE, 10);
        carac.put(Caracteristique.DEXTERITE, 10);
        carac.put(Caracteristique.INTELLIGENCE, 10);
        capacites = new Capacite[4];
        addCapacite(new Capacite()); // main nue
    }
    
    // Constructeur d'un ennemi
    public Personnage(String nom, int niv, int exp, int arg, int vie, int force, int dext, int intell) {
        this.nom = nom;
        this.classe = "";
        this.niveau = niv;
        this.experience = exp;
        this.argent = arg;
        this.carac = new EnumMap<>(Caracteristique.class);
        carac.put(Caracteristique.VITALITE, vie);
        this.vie = vie;
        this.arme = null;
        this.armure = null;
        carac.put(Caracteristique.FORCE, force);
        carac.put(Caracteristique.DEXTERITE, dext);
        carac.put(Caracteristique.INTELLIGENCE, intell);
        capacites = new Capacite[4];
        addCapacite(new Capacite());// main nue
    }
    
    
    public void drawPersonnage(Jeu j){
        j.addChaine(nom);
        j.addChaine("Niveau : " + niveau);
        j.addChaine("XP : " + experience);
        j.addChaine("Argent : " + argent);
        j.addChaine("Vie : " + vie + "/" + carac.get(Caracteristique.VITALITE));
        j.addChaine("Force : " + carac.get(Caracteristique.FORCE));
        j.addChaine("Dexterite : " + carac.get(Caracteristique.DEXTERITE));
        j.addChaine("Intelligence : " + carac.get(Caracteristique.INTELLIGENCE));
        int i = 0;
            while(capacites[i] != null && i < 4){
                j.addChaine(" " + (i + 1) + ". " + capacites[i].stringCapacite()
                + " (" + getDmin(capacites[i]) + "," + getDmax(capacites[i]) + ")");
                i++;
            }
    }
    
    public void drawPersonnage(Jeu j, int ligne, int decalage){
        j.addChaine(nom, ligne, decalage);
        ligne++;
        j.addChaine("Niveau : " + niveau, ligne, decalage);
        ligne++;
        j.addChaine("XP : " + experience, ligne, decalage);
        ligne++;
        j.addChaine("Argent : " + argent, ligne, decalage);
        ligne++;
        j.addChaine("Vie : " + vie + "/" + carac.get(Caracteristique.VITALITE), ligne, decalage);
        ligne++;
        j.addChaine("Force : " + carac.get(Caracteristique.FORCE), ligne, decalage);
        ligne++;
        j.addChaine("Dexterite : " + carac.get(Caracteristique.DEXTERITE), ligne, decalage);
        ligne++;
        j.addChaine("Intelligence : " + carac.get(Caracteristique.INTELLIGENCE), ligne, decalage);
        ligne++;
        int i = 0;
            while(capacites[i] != null && i < 4){
                j.addChaine(" " + (i + 1) + ". " + capacites[i].stringCapacite()
                + " (" + getDmin(capacites[i]) + "," + getDmax(capacites[i]) + ")", ligne + i, decalage);
                i++;
            }
        ligne += i;
    }
    
    public void levelUp(){
        this.niveau++;
        System.out.println("Vous êtes montés au niveau " + this.niveau + " !!!");
        int vie;
        int force;
        int dext;
        int intel;
        if (classe == "Boulanger"){
            vie = 3;
            force = 3; 
            dext = 1;
            intel = 1;
        }
        else if (classe == "Cuisinier"){
            vie = 3;
            force = 1; 
            dext = 3;
            intel = 1;
        }
        else if (classe == "SAV"){
            vie = 5;
            force = 1; 
            dext = 1;
            intel = 1;
        }
        else if (classe == "Pharmacien"){
            vie = 3;
            force = 1; 
            dext = 1;
            intel = 3;
        }
        else{
            vie = 0;
            force = 0; 
            dext = 0;
            intel = 0;
        }
        carac.replace(Caracteristique.VITALITE, carac.get(Caracteristique.VITALITE) + vie);
        carac.replace(Caracteristique.FORCE, carac.get(Caracteristique.FORCE) + force);
        carac.replace(Caracteristique.DEXTERITE, carac.get(Caracteristique.DEXTERITE) + dext);
        carac.replace(Caracteristique.INTELLIGENCE, carac.get(Caracteristique.INTELLIGENCE) + intel);
        revivre();
    }
    
    public void gagnerXP(int xp){
        experience += xp;
        System.out.println("Vous avez gagné " + xp + " points d'expérience !");
        while (experience >= 5 * pow(2,niveau)){
            experience -= 5 * pow(2,niveau);
            levelUp();
        }
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
    
    public void setClasse(String c){
        this.classe = c;
    }
    
    public int getVie(){
        return vie;
    }
    
    public int getDext(){
        return carac.get(Caracteristique.DEXTERITE);
    }
    
    public int getIntell(){
        return carac.get(Caracteristique.INTELLIGENCE);
    }
    
    public int getDmin(Capacite c){
        int car = 0;
        if (c.getCarac() == 1){ // Vitalite
            car = carac.get(Caracteristique.VITALITE);
        }
        else if (c.getCarac() == 2){ // Force
            car = carac.get(Caracteristique.FORCE);
        }
        else if (c.getCarac() == 3){ // Dexterite
            car = carac.get(Caracteristique.DEXTERITE);
        }
        else if (c.getCarac() == 4){ // Intelligence
            car = carac.get(Caracteristique.INTELLIGENCE);
        }
        
        return c.getDmin() + c.getRatio() * car / 100;
    }
    
    public int getDmax(Capacite c){
        int car = 0;
        if (c.getCarac() == 1){ // Vitalite
            car = carac.get(Caracteristique.VITALITE);
        }
        else if (c.getCarac() == 2){ // Force
            car = carac.get(Caracteristique.FORCE);
        }
        else if (c.getCarac() == 3){ // Dexterite
            car = carac.get(Caracteristique.DEXTERITE);
        }
        else if (c.getCarac() == 4){ // Intelligence
            car = carac.get(Caracteristique.INTELLIGENCE);
        }
        
        return c.getDmax() + c.getRatio() * car / 100;
    }
    
    public int attaquer(int numCapacite){
        numCapacite -= 1;
        if (numCapacite > 3 || numCapacite <0){
            return -1;
        }
        Capacite c = capacites[numCapacite];
        if (c == null){
            return -1;
        }
        Random rand = new Random();
        int dmin = getDmin(c);
        int dmax = getDmax(c);
        return rand.nextInt(dmax - dmin + 1) + dmin;
    }
    
    public void infligerDegats(int deg){
        vie -= deg;
        if (vie <0){
            vie = 0;
        }
        System.out.println(nom + " a subit " + deg + " dégats.");
    }
    
    public void revivre(){
        vie = carac.get(Caracteristique.VITALITE);
    }
    
    public int getXP(){
        return experience;
    }
    
    public void gagnerArgent(int sous){
        argent += sous;
        System.out.println("Vous avz gagné " + sous + " euros !");
    }
    
    public int getArgent(){
        return argent;
    }
    
    public void equipeArme(Arme a){
        arme = a;
        capacites[0] = new Capacite(a.getNom(),a.getDmin(), a.getDmax(),a.getRatio(), a.getCarac());
    }
    
    public Arme getArme(){
        return arme;
    }
    
    public Armure getArmure(){
        return armure;
    }
}
