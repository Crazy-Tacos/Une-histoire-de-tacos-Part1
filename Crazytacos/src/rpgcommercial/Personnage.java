package rpgcommercial;

import java.io.BufferedReader;
import java.io.PrintWriter;
import static java.lang.Math.pow;
import java.util.EnumMap;
import java.util.Random;


public class Personnage {

    protected String nom;
    protected String classe;
    protected int niveau;
    protected int experience;
    protected int argent;
    protected int vie;
    protected EnumMap<Caracteristique, Integer> carac;
    protected Inventaire inventaire;

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
            vie = 10;
            force = 10; 
            dext = 2;
            intel = 2;
        }
        else if (classe == "Cuisinier"){
            vie = 10;
            force = 2; 
            dext = 10;
            intel = 2;
        }
        else if (classe == "SAV"){
            vie = 15;
            force = 4; 
            dext = 4;
            intel = 4;
        }
        else if (classe == "Pharmacien"){
            vie = 10;
            force = 2; 
            dext = 2;
            intel = 10;
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
        if (niveau%5 ==0){
            apprendreCompetence(j);
        }
    }
    
    public void apprendreCompetence(Vue v){
        Competence c = null;
        if ("Boulanger".equals(classe)){
            if (niveau/5 == 1){
                c = new Competence("Lancer de croissant", 3,5, 10,2, false);
            }
            else if (niveau/5 ==2){
                c = new Competence("Fougasse", 6,10, 20,2, false);
            }
            else if (niveau/5 == 3){
                c = new Competence("Baguette magique", 9,15, 25,2, false);
            }
            else if (niveau/5 ==4){
                c = new Competence("Chausson aux pommes", 12,20, 30,2, false);
            }
        }
        else if ("Cuisinier".equals(classe)){
            if (niveau/5 == 1){
                c = new Competence("Frite bouillante", 3,5, 10,3, false);
            }
            else if (niveau/5 ==2){
                c = new Competence("Viande de kebab", 6,10, 20,3, false);
            }
            else if (niveau/5 == 3){
                c = new Competence("Menu tacos", 9,15, 25,3, false);
            }
            else if (niveau/5 ==4){
                c = new Competence("Jambon", 1000,1000, 30,3, false);
            }
        }
        else if ("SAV".equals(classe)){
            if (niveau/5 == 1){
                c = new Competence("Pause café", 3,5, 5,1, false);
            }
            else if (niveau/5 ==2){
                c = new Competence("Tournevis", 6,10, 10,1, false);
            }
            else if (niveau/5 == 3){
                c = new Competence("Tuto youtube", 9,15, 15,1, false);
            }
            else if (niveau/5 ==4){
                c = new Competence("Extension de garantie", 12,20, 20,1, false);
            }
        }
        else if ("Pharmacien".equals(classe)){
            if (niveau/5 == 1){
                c = new Competence("Antibiotique", 3,5, 10,4, true);
            }
            else if (niveau/5 ==2){
                c = new Competence("Sirop", 6,10, 20,4, true);
            }
            else if (niveau/5 == 3){
                c = new Competence("Bandage", 9,15, 25,4, true);
            }
            else if (niveau/5 ==4){
                c = new Competence("Ebola", 1000,1000, 30,4, false);
            }
        }
        if (c != null){
            inventaire.setCompetence(c);
            v.addChaine("Vous avez appris la compétence " + c.nom);
            c.drawCompetence(v);
        }
    }
    
    public void gagnerXP(Vue j,int xp){
        experience += xp;
        j.addChaine("Vous avez gagné " + xp + " points d'expérience !");
        while (experience >= pow(2,niveau)){
            experience -= pow(2,niveau);
            levelUp(j);
        }
    }
    
    public String getNom(){
        return nom;
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
    
    public void utiliserSoin(Vue j,int val){
        soigner(val);
        j.addChaine(nom + " a récupéré " + val + " points de vie.");
        j.addChaine("");
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
        if (c.carac == 1){ // Vitalite
            car = carac.get(Caracteristique.VITALITE);
        }
        else if (c.carac == 2){ // Force
            car = carac.get(Caracteristique.FORCE);
        }
        else if (c.carac == 3){ // Dexterite
            car = carac.get(Caracteristique.DEXTERITE);
        }
        else if (c.carac == 4){ // Intelligence
            car = carac.get(Caracteristique.INTELLIGENCE);
        }
        
        return c.dmin + (c.ratio * car) / 100;
    }
    
    public int getDmaxArme(Arme c){
        int car = 0;
        if (c.carac == 1){ // Vitalite
            car = getVitaliteTotale();
        }
        else if (c.carac == 2){ // Force
            car = getForceTotale();
        }
        else if (c.carac == 3){ // Dexterite
            car = getDextTotale();
        }
        else if (c.carac == 4){ // Intelligence
            car = getIntellTotale();
        }
        return c.dmax + (c.ratio * car) / 100;
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
                if (!"Mains nues".equals(inventaire.getArmePrincipale().nom)){
                    j.addChaine(nom + " utilise l'arme " + inventaire.getArmePrincipale().nom);
                }
                else{
                    j.addChaine(nom + " attaque à mains nues");
                }
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
                j.addChaine(nom + " utilise l'arme " + inventaire.getArmeSecondaire().nom);
                if (inventaire.getArmeSecondaire().utiliserMunition()){
                    j.addChaine("L'arme "+inventaire.getArmeSecondaire().nom + " n'a plus de munitions");
                    inventaire.setArmeSecondaire(null);
                }
                return rand.nextInt(max - min + 1) + min;
            }
        }
        if (choix == 3){
            if(inventaire.getCompetence()== null){
                choix++;
            }
            else {
                max = getDmaxArme(inventaire.getCompetence());
                min = getDminArme(inventaire.getCompetence());
                j.addChaine(nom + " utilise la compétence " + inventaire.getCompetence().nom);
                int val = rand.nextInt(max - min + 1) + min;
                if (inventaire.getCompetence().isSoin()){
                    val = -val;
                }
                return val;
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
            j.addChaine("L'armure "+inventaire.getArmure().nom + " est cassée");
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
            str = " " + nb + ". " + inventaire.getArmePrincipale().nom + " : ";
            str += getDminArme(inventaire.getArmePrincipale()) + "-";
            str+= getDmaxArme(inventaire.getArmePrincipale()) + " dégats";
            j.addChaine(str);
        }
        if (inventaire.getArmeSecondaire()!=null){
            nb++;
            str = " " + nb + ". " + inventaire.getArmeSecondaire().nom + " : ";
            str += getDminArme(inventaire.getArmeSecondaire()) + "-";
            str += getDmaxArme(inventaire.getArmeSecondaire()) + " dégats et ";
            str += inventaire.getArmeSecondaire().getMunitions() + " munitions";
            j.addChaine(str);
        }
        if (inventaire.getCompetence()!=null){
            nb++;
            str = " " + nb + ". " + inventaire.getCompetence().nom + " : ";
            str += getDminArme(inventaire.getCompetence()) + "-";
            str += getDmaxArme(inventaire.getCompetence());
            if (inventaire.getCompetence().isSoin()){
                str += " soins";
            }
            else {
                str += " dégats";
            }
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
    
    public void perdreCombat(Vue vue){
        regen();
        int perte = argent/2;
        argent -= perte;
        vue.addChaine("Vous parvenez à vous enfuir mais vous laissez");
        vue.addChaine("derrière vous "+perte+" euros.....");
    }
}