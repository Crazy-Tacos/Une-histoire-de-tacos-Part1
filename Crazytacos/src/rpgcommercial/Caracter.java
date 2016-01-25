package rpgcommercial;

import java.io.BufferedReader;
import java.io.PrintWriter;
import static java.lang.Math.pow;
import java.util.EnumMap;
import java.util.Random;


public class Caracter {

    protected String nom;
    protected String classe;
    protected int niveau;
    protected int experience;
    protected int argent;
    protected int vie;
    protected EnumMap<Caracteristique, Integer> carac;
    protected Inventory inventaire;

    // Constructeur du joueur
    public Caracter(String nom) {
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
        this.inventaire = new Inventory();
        regen();
    }
    
    // Constructeur d'un ennemi
    public Caracter(String nom, int niv, int exp, int arg, int vie, int force, int dext, int intell) {
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
        this.inventaire = new Inventory();
        regen();
    }
    
    public Caracter(BufferedReader br){
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
            this.inventaire = new Inventory(br);
            regen();
        }
        catch (Exception e){
                System.out.println(e.toString());
        }
    }
    
    public void drawCaracter(View j){
        j.addString(nom);
        j.addString("Niveau : " + niveau);
        j.addString("XP : " + experience);
        j.addString("Argent : " + argent);
        j.addString("Vie : " + vie + "/" + carac.get(Caracteristique.VITALITE));
        j.addString("Force : " + carac.get(Caracteristique.FORCE));
        j.addString("Dexterite : " + carac.get(Caracteristique.DEXTERITE));
        j.addString("Intelligence : " + carac.get(Caracteristique.INTELLIGENCE));
    }
    
    public void drawCaracterFight(View j, int ligne, int decalage){
        j.addString(nom, ligne, decalage);
        ligne++;
        j.addString("Niveau : " + niveau, ligne, decalage);
        ligne++;
        j.addString("Vie : " + vie + "/" + getVitaliteTotale(), ligne, decalage);
        ligne++;
        j.addString("Force : " + getForceTotale(), ligne, decalage);
        ligne++;
        j.addString("Dexterite : " + getDextTotale(), ligne, decalage);
        ligne++;
        j.addString("Intelligence : " + getIntellTotale(), ligne, decalage);
        ligne++;
    }
    
    public void drawCaracterFight(View j){
        j.addString(nom);
        j.addString("Niveau : " + niveau);
        j.addString("Vie : " + vie + "/" + getVitaliteTotale());
        j.addString("Force : " + getForceTotale());
        j.addString("Dexterite : " + getDextTotale());
        j.addString("Intelligence : " + getIntellTotale());
    }
    
    public void levelUp(View j){
        this.niveau++;
        j.addString("Vous êtes montés au niveau " + this.niveau + " !!!");
        int vie;
        int force;
        int dext;
        int intel;
        switch (classe) {
            case "Boulanger":
                vie = 10;
                force = 10;
                dext = 2;
                intel = 2;
                break;
            case "Cuisinier":
                vie = 10;
                force = 2;
                dext = 10;
                intel = 2;
                break;
            case "SAV":
                vie = 15;
                force = 4;
                dext = 4;
                intel = 4;
                break;
            case "Pharmacien":
                vie = 10;
                force = 2;
                dext = 2;
                intel = 10;
                break;
            default:
                vie = 0;
                force = 0;
                dext = 0;
                intel = 0;
                break;
        }
        carac.replace(Caracteristique.VITALITE, carac.get(Caracteristique.VITALITE) + vie);
        carac.replace(Caracteristique.FORCE, carac.get(Caracteristique.FORCE) + force);
        carac.replace(Caracteristique.DEXTERITE, carac.get(Caracteristique.DEXTERITE) + dext);
        carac.replace(Caracteristique.INTELLIGENCE, carac.get(Caracteristique.INTELLIGENCE) + intel);
        regen();
        if (niveau%5 ==0){
            learnSpell(j);
        }
    }
    
    public void learnSpell(View v){
        Spell c = null;
        switch (classe) {
            case "Boulanger":
                    switch (niveau/5) {
                        case 1:
                            c = new Spell("Lancer de croissant", 3,5, 10,2, false);
                            break;
                        case 2:
                            c = new Spell("Fougasse", 6,10, 20,2, false);
                            break;
                        case 3:
                            c = new Spell("Baguette magique", 9,15, 25,2, false);
                            break;
                        case 4:
                            c = new Spell("Chausson aux pommes", 12,20, 30,2, false);
                            break;
                        default:
                            break;
                    }break;
            case "Cuisinier":
                    switch (niveau/5) {
                        case 1:
                            c = new Spell("Frite bouillante", 3,5, 10,3, false);
                            break;
                        case 2:
                            c = new Spell("Viande de kebab", 6,10, 20,3, false);
                            break;
                        case 3:
                            c = new Spell("Menu tacos", 9,15, 25,3, false);
                            break;
                        case 4:
                            c = new Spell("Jambon", 1000,1000, 30,3, false);
                            break;
                        default:
                            break;
                    }break;
            case "SAV":
                    switch (niveau/5) {
                        case 1:
                            c = new Spell("Pause café", 3,5, 5,1, false);
                            break;
                        case 2:
                            c = new Spell("Tournevis", 6,10, 10,1, false);
                            break;
                        case 3:
                            c = new Spell("Tuto youtube", 9,15, 15,1, false);
                            break;
                        case 4:
                            c = new Spell("Extension de garantie", 12,20, 20,1, false);
                            break;
                        default:
                            break;
                    }break;
            case "Pharmacien":
                    switch (niveau/5) {
                        case 1:
                            c = new Spell("Antibiotique", 3,5, 10,4, true);
                            break;
                        case 2:
                            c = new Spell("Sirop", 6,10, 20,4, true);
                            break;
                        case 3:
                            c = new Spell("Bandage", 9,15, 25,4, true);
                            break;
                        case 4:
                            c = new Spell("Ebola", 1000,1000, 30,4, false);
                            break;
                        default:
                            break;
                    }break;
            default: break;
        }
        if (c != null){
            inventaire.setSpell(c);
            v.addString("Vous avez appris la compétence " + c.nom);
            c.drawSpell(v);
        }
    }
    
    public void winXP(View j,int xp){
        experience += xp;
        j.addString("Vous avez gagné " + xp + " points d'expérience !");
        while (experience >= pow(2,niveau)){
            experience -= pow(2,niveau);
            levelUp(j);
        }
    }
    
    public String getName(){
        return nom;
    }

    
    public int getVie(){
        return vie;
    }
    
    public int getDextTotale(){
        int val = carac.get(Caracteristique.DEXTERITE);
        if (inventaire.getArmor() != null){
            val += inventaire.getArmor().getDexterite();
        }
        return val;
    }
    
    public int getIntellTotale(){
        int val =carac.get(Caracteristique.INTELLIGENCE);
        if (inventaire.getArmor() != null){
            val += inventaire.getArmor().getIntell();
        }
        return val;
    }
    
    public void useHeal(View j,int val){
        heal(val);
        j.addString(nom + " a récupéré " + val + " points de vie.");
        j.addString("");
    }
    
    public void heal(int vie){
        this.vie += vie;
        if (this.vie > getVitaliteTotale()){
            regen();
        }
    }
    
    public int getVitaliteTotale(){
        int val =carac.get(Caracteristique.VITALITE);
        if (inventaire.getArmor() != null){
            val += inventaire.getArmor().getVitalite();
        }
        return val;
    }
    
    public int getForceTotale(){
        int val =carac.get(Caracteristique.FORCE);
        if (inventaire.getArmor() != null){
            val += inventaire.getArmor().getForce();
        }
        return val;
    }
    
    public int getDminArme(Weapon c){
        int car = 0;
        switch (c.carac) {
            case 1: car = carac.get(Caracteristique.VITALITE); break; // Vitalite
            case 2: car = carac.get(Caracteristique.FORCE); break;// Force
            case 3: car = carac.get(Caracteristique.DEXTERITE); break;// Dexterite
            case 4:car = carac.get(Caracteristique.INTELLIGENCE); break;// Intelligence
            default: break;
        }
        
        return c.dmin + (c.ratio * car) / 100;
    }
    
    public int getDmaxArme(Weapon c){
        int car = 0;
        switch (c.carac) {
            case 1: car = getVitaliteTotale();break; // Vitalite
            case 2:car = getForceTotale();break; // Force
            case 3:car = getDextTotale();break; // Dexterite
            case 4:car = getIntellTotale();break; // Intelligence
            default: break;
        }
        return c.dmax + (c.ratio * car) / 100;
    }
    
    public int attack(View j, int choix){
        int max, min;
        Random rand = new Random();
        if (choix == 1){
            if(inventaire.getMainWeapon() == null){
                choix++;
            }
            else {
                max = getDmaxArme(inventaire.getMainWeapon());
                min = getDminArme(inventaire.getMainWeapon());
                if (!"Mains nues".equals(inventaire.getMainWeapon().nom)){
                    j.addString(nom + " utilise l'arme " + inventaire.getMainWeapon().nom);
                }
                else{
                    j.addString(nom + " attaque à mains nues");
                }
                return rand.nextInt(max - min + 1) + min;
            }
        }
        if (choix == 2){
            if(inventaire.getSecondWeapon() == null){
                choix++;
            }
            else {
                max = getDmaxArme(inventaire.getSecondWeapon());
                min = getDminArme(inventaire.getSecondWeapon());
                j.addString(nom + " utilise l'arme " + inventaire.getSecondWeapon().nom);
                if (inventaire.getSecondWeapon().useMunition()){
                    j.addString("L'arme "+inventaire.getSecondWeapon().nom + " n'a plus de munitions");
                    inventaire.setSecondWeapon(null);
                }
                return rand.nextInt(max - min + 1) + min;
            }
        }
        if (choix == 3){
            if(inventaire.getSpell()== null){
                choix++;
            }
            else {
                max = getDmaxArme(inventaire.getSpell());
                min = getDminArme(inventaire.getSpell());
                j.addString(nom + " utilise la compétence " + inventaire.getSpell().nom);
                int val = rand.nextInt(max - min + 1) + min;
                if (inventaire.getSpell().isHeal()){
                    val = -val;
                }
                return val;
            }
        }
        
        return 0;
    }
    
    public void takeDamages(View j,int deg){
        vie -= deg;
        if (vie <0){
            vie = 0;
        }
        j.addString(nom + " a subit " + deg + " dégats.");
        if (inventaire.getArmor() != null && inventaire.getArmor().attackArmor(deg)){
            j.addString("L'armure "+inventaire.getArmor().nom + " est cassée");
            inventaire.setArmor(null);
            if (vie > getVitaliteTotale()){
                regen();
            }
        }
        j.addString("");
    }
    
    public void regen(){
        vie = getVitaliteTotale();
    }
    
    public int getXP(){
        return experience;
    }
    
    public void winMoney(View j,int sous){
        argent += sous;
        j.addString("Vous avez gagné " + sous + " euros !");
    }
    
    public int getMoney(){
        return argent;
    }
    
    public Inventory getInventory(){
        return inventaire;
    }
    
    public int drawActions(View j){
        int nb = 0;
        String str;
        if (inventaire.getMainWeapon()!=null){
            nb++;
            str = " " + nb + ". " + inventaire.getMainWeapon().nom + " : ";
            str += getDminArme(inventaire.getMainWeapon()) + "-";
            str+= getDmaxArme(inventaire.getMainWeapon()) + " dégats";
            j.addString(str);
        }
        if (inventaire.getSecondWeapon()!=null){
            nb++;
            str = " " + nb + ". " + inventaire.getSecondWeapon().nom + " : ";
            str += getDminArme(inventaire.getSecondWeapon()) + "-";
            str += getDmaxArme(inventaire.getSecondWeapon()) + " dégats et ";
            str += inventaire.getSecondWeapon().getMunitions() + " munitions";
            j.addString(str);
        }
        if (inventaire.getSpell()!=null){
            nb++;
            str = " " + nb + ". " + inventaire.getSpell().nom + " : ";
            str += getDminArme(inventaire.getSpell()) + "-";
            str += getDmaxArme(inventaire.getSpell());
            if (inventaire.getSpell().isHeal()){
                str += " soins";
            }
            else {
                str += " dégats";
            }
            j.addString(str);
        }
        return nb;
    }
      
    public void save(PrintWriter fichierSortie){        
        fichierSortie.println ("classe=" + classe.replace(" ", "_"));
        
        String personnage = "Personnage=" + nom.replace(" ", "_") + " " + niveau + " "+ experience+ " " + argent+ " ";
        personnage += carac.get(Caracteristique.VITALITE)+ " "; 
        personnage += carac.get(Caracteristique.FORCE)+ " ";
        personnage += carac.get(Caracteristique.DEXTERITE)+ " "; 
        personnage += carac.get(Caracteristique.INTELLIGENCE);

        fichierSortie.println (personnage);      
        if (inventaire != null){
            inventaire.save(fichierSortie); 
        } 
    }
    
    public void loseFight(View vue){
        regen();
        int perte = argent/2;
        argent -= perte;
        vue.addString("J'avais du m'enfuire.");
        vue.addString("Durant ma course j'ai fait tomber "+perte+" euros.....");
    }
    
    public String getClasse(){
        return this.classe;
    }
}