package rpgcommercial;

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
    private Inventaire inventaire;

    // Constructeur du joueur
    public Personnage(String nom) {
        this.nom = nom;
        this.classe = "";
        this.niveau = 1;
        this.experience = 0;
        this.argent = 0;
        this.carac = new EnumMap<>(Caracteristique.class);
        carac.put(Caracteristique.VITALITE, 50);
        this.vie = 50;
        carac.put(Caracteristique.FORCE, 10);
        carac.put(Caracteristique.DEXTERITE, 10);
        carac.put(Caracteristique.INTELLIGENCE, 10);
        this.inventaire = new Inventaire();
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
        carac.put(Caracteristique.FORCE, force);
        carac.put(Caracteristique.DEXTERITE, dext);
        carac.put(Caracteristique.INTELLIGENCE, intell);
        this.inventaire = new Inventaire();
    }
    
    
    public void drawPersonnage(Vue j){
        j.addChaine(nom);
        j.addChaine("Niveau : " + niveau);
        j.addChaine("XP : " + experience);
        j.addChaine("Argent : " + argent);
        j.addChaine("Vie : " + vie + "/" + carac.get(Caracteristique.VITALITE));
        j.addChaine("Force : " + carac.get(Caracteristique.FORCE));
        j.addChaine("Dexterite : " + carac.get(Caracteristique.DEXTERITE));
        j.addChaine("Intelligence : " + carac.get(Caracteristique.INTELLIGENCE));
    }
    
    public void drawPersonnage(Vue j, int ligne, int decalage){
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
    }
    
    public void levelUp(Vue j){
        this.niveau++;
        j.addChaine("Vous êtes montés au niveau " + this.niveau + " !!!");
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
        regen();
    }
    
    public void gagnerXP(Vue j,int xp){
        experience += xp;
        j.addChaine("Vous avez gagné " + xp + " points d'expérience !");
        while (experience >= 5 * pow(2,niveau)){
            experience -= 5 * pow(2,niveau);
            levelUp(j);
        }
    }
    
    public String getNom(){
        return nom;
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
    
    public int getDminArme(Arme c){
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
    
    public int getDmaxArme(Arme c){
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
    
    public int attaquer(Vue j){
        return 0;
    }
    
    public void infligerDegats(Vue j,int deg){
        vie -= deg;
        if (vie <0){
            vie = 0;
        }
        j.addChaine(nom + " a subit " + deg + " dégats.");
    }
    
    public void regen(){
        vie = carac.get(Caracteristique.VITALITE);
    }
    
    public int getXP(){
        return experience;
    }
    
    public void gagnerArgent(Vue j,int sous){
        argent += sous;
        j.addChaine("Vous avez gagné " + sous + " euros !");
    }
    
    public int getArgent(){
        return argent;
    }
    
    public Inventaire getInventaire(){
        return inventaire;
    }
    
    public int drawActions(Vue j){
        int nb = 0;
        String str;
        if (inventaire.getArmePrincipale()!=null){
            nb++;
            str = " " + nb + ". " + inventaire.getArmePrincipale().getNom() + " : ";
            str += inventaire.getArmePrincipale().getDmin() + "-";
            str+= inventaire.getArmePrincipale().getDmax() + " dégats";
            j.addChaine(str);
        }
        if (inventaire.getArmeSecondaire()!=null){
            nb++;
            str = " " + nb + ". " + inventaire.getArmeSecondaire().getNom() + " : ";
            str += inventaire.getArmeSecondaire().getDmin() + "-";
            str+= inventaire.getArmeSecondaire().getDmax() + " dégats";
            j.addChaine(str);
        }
        return nb;
    }
}