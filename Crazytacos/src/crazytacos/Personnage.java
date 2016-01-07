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
    
    public String[] stringPersonnage(int ligne){
        String[] str = new String[20];
        for (int i = 0; i<20; i++){
            str[i] = "";
        }
        if (ligne + 7 <20){
            str[ligne + 0] = nom + " : " + classe;
            str[ligne + 1] = "Niveau : " + niveau;
            str[ligne + 2] = "XP : " + experience;
            str[ligne + 3] = "Argent : " + argent;
            str[ligne + 4] = "Vie : " + vie + "/" + carac.get(Caracteristique.VITALITE);
            str[ligne + 5] = "Force : " + carac.get(Caracteristique.FORCE);
            str[ligne + 6] = "Dexterite : " + carac.get(Caracteristique.DEXTERITE);
            str[ligne + 7] = "Intelligence : " + carac.get(Caracteristique.INTELLIGENCE);
            int j = 0;
            while(capacites[j] != null && j < 4 && ligne + 8 + j <20){
                str[ligne + 8 + j] = " " + (j + 1) + ". " + capacites[j].stringCapacite();
                str[ligne + 8 + j] += " (" + getDmin(capacites[j]) + "," + getDmax(capacites[j]) + ")";
                j++;
            }
        }
        return str;
    }
    
    public void drawPersonnage(Jeu j,int ligne){
        j.addChaine(stringPersonnage(ligne));
    }
    
    public void levelUp(){
        this.niveau++;
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
        this.vie += vie; // Ou revivre() pour full life !
        carac.replace(Caracteristique.VITALITE, carac.get(Caracteristique.VITALITE) + vie);
        carac.replace(Caracteristique.FORCE, carac.get(Caracteristique.FORCE) + force);
        carac.replace(Caracteristique.DEXTERITE, carac.get(Caracteristique.DEXTERITE) + dext);
        carac.replace(Caracteristique.INTELLIGENCE, carac.get(Caracteristique.INTELLIGENCE) + intel);
    }
    
    public void gagnerXP(int xp){
        experience += xp;
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
