package rpgcommercial;

import java.io.BufferedReader;
import java.io.PrintWriter;
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
        carac.put(Caracteristique.FORCE, 10);
        carac.put(Caracteristique.DEXTERITE, 10);
        carac.put(Caracteristique.INTELLIGENCE, 10);
        this.inventaire = new Inventaire();
        regen();
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
        carac.put(Caracteristique.FORCE, force);
        carac.put(Caracteristique.DEXTERITE, dext);
        carac.put(Caracteristique.INTELLIGENCE, intell);
        this.inventaire = new Inventaire();
        regen();
    }
    
    public Personnage(BufferedReader br){
        String lecture[];
        try{
            String ligne=br.readLine();
            lecture=ligne.split("=");
            lecture=lecture[1].split(" ");
            
            this.nom = lecture[0].replace("_", " ");

            this.classe = "";
            this.niveau = Integer.parseInt(lecture[1]);
            this.experience = Integer.parseInt(lecture[2]);
            this.argent = Integer.parseInt(lecture[3]);

            this.carac = new EnumMap<>(Caracteristique.class);

            carac.put(Caracteristique.VITALITE, Integer.parseInt(lecture[4]));
            carac.put(Caracteristique.FORCE, Integer.parseInt(lecture[5]));
            carac.put(Caracteristique.DEXTERITE, Integer.parseInt(lecture[6]));
            carac.put(Caracteristique.INTELLIGENCE, Integer.parseInt(lecture[7]));
            this.inventaire = new Inventaire(br);
            regen();
        }
        catch (Exception e){
                System.out.println(e.toString());
        }
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
    
    public void drawPersonnageCombat(Vue j, int ligne, int decalage){
        j.addChaine(nom, ligne, decalage);
        ligne++;
        j.addChaine("Niveau : " + niveau, ligne, decalage);
        ligne++;
        j.addChaine("Vie : " + vie + "/" + getVitaliteTotale(), ligne, decalage);
        ligne++;
        j.addChaine("Force : " + getForceTotale(), ligne, decalage);
        ligne++;
        j.addChaine("Dexterite : " + getDextTotale(), ligne, decalage);
        ligne++;
        j.addChaine("Intelligence : " + getIntellTotale(), ligne, decalage);
        ligne++;
    }
    
    public void drawPersonnageCombat(Vue j){
        j.addChaine(nom);
        j.addChaine("Niveau : " + niveau);
        j.addChaine("Vie : " + vie + "/" + getVitaliteTotale());
        j.addChaine("Force : " + carac.get(Caracteristique.FORCE));
        j.addChaine("Dexterite : " + carac.get(Caracteristique.DEXTERITE));
        j.addChaine("Intelligence : " + carac.get(Caracteristique.INTELLIGENCE));
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
    
    public int getDextTotale(){
        int val = carac.get(Caracteristique.DEXTERITE);
        if (inventaire.getArmure() != null){
            val += inventaire.getArmure().getDexterite();
        }
        return val;
    }
    
    public int getIntellTotale(){
        int val =carac.get(Caracteristique.INTELLIGENCE);
        if (inventaire.getArmure() != null){
            val += inventaire.getArmure().getIntell();
        }
        return val;
    }
    
    public void soigner(int vie){
        this.vie += vie;
        if (this.vie > getVitaliteTotale()){
            regen();
        }
    }
    
    public int getVitaliteTotale(){
        int val =carac.get(Caracteristique.VITALITE);
        if (inventaire.getArmure() != null){
            val += inventaire.getArmure().getVitalite();
        }
        return val;
    }
    
    public int getForceTotale(){
        int val =carac.get(Caracteristique.FORCE);
        if (inventaire.getArmure() != null){
            val += inventaire.getArmure().getForce();
        }
        return val;
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
            car = getVitaliteTotale();
        }
        else if (c.getCarac() == 2){ // Force
            car = getForceTotale();
        }
        else if (c.getCarac() == 3){ // Dexterite
            car = getDextTotale();
        }
        else if (c.getCarac() == 4){ // Intelligence
            car = getIntellTotale();
        }
        
        return c.getDmax() + c.getRatio() * car / 100;
    }
    
    public int attaquer(Vue j, int choix){
        int max, min;
        Random rand = new Random();
        if (choix == 1){
            if(inventaire.getArmePrincipale() == null){
                choix++;
            }
            else {
                max = getDmaxArme(inventaire.getArmePrincipale());
                min = getDminArme(inventaire.getArmePrincipale());
                j.addChaine(nom + " utilise l'arme " + inventaire.getArmePrincipale().getNom());
                return rand.nextInt(max - min + 1) + min;
            }
        }
        if (choix == 2){
            if(inventaire.getArmeSecondaire() == null){
                choix++;
            }
            else {
                max = getDmaxArme(inventaire.getArmeSecondaire());
                min = getDminArme(inventaire.getArmeSecondaire());
                j.addChaine(nom + " utilise l'arme " + inventaire.getArmeSecondaire().getNom());
                if (inventaire.getArmeSecondaire().utiliserMunition()){
                    j.addChaine("L'arme "+inventaire.getArmeSecondaire().getNom() + " n'a plus de munitions");
                    inventaire.setArmeSecondaire(null);
                }
                return rand.nextInt(max - min + 1) + min;
            }
        }
        
        return 0;
    }
    
    public void infligerDegats(Vue j,int deg){
        vie -= deg;
        if (vie <0){
            vie = 0;
        }
        j.addChaine(nom + " a subit " + deg + " dégats.");
        if (inventaire.getArmure() != null && inventaire.getArmure().endommagerArmure(deg)){
            j.addChaine("L'armure "+inventaire.getArmure().getNom() + " est cassée");
            inventaire.setArmure(null);
            if (vie > getVitaliteTotale()){
                regen();
            }
        }
        j.addChaine("");
    }
    
    public void regen(){
        vie = getVitaliteTotale();
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
            str += inventaire.getArmeSecondaire().getDmax() + " dégats et ";
            str += inventaire.getArmeSecondaire().getMunitions() + " munitions";
            j.addChaine(str);
        }
        return nb;
    }
      
    public void sauvegarder(PrintWriter fichierSortie){        
        fichierSortie.println ("classe=" + classe.replace(" ", "_"));
        
        String personnage = "Personnage=" + nom.replace(" ", "_") + " " + niveau + " "+ experience+ " " + argent+ " ";
        personnage += carac.get(Caracteristique.VITALITE)+ " "; 
        personnage += carac.get(Caracteristique.FORCE)+ " ";
        personnage += carac.get(Caracteristique.DEXTERITE)+ " "; 
        personnage += carac.get(Caracteristique.INTELLIGENCE);

        fichierSortie.println (personnage);      
        if (inventaire != null){
            inventaire.sauvegarder(fichierSortie); 
        } 
    }
}