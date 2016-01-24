package rpgcommercial;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Inventory {
    private Weapon armePrincipale;
    private SecondWeapon armeSecondaire;
    private Armor armure;
    private Spell competence;
    
    public Inventory(){
        this.armePrincipale =new Weapon("Mains nues", 1, 3, 5, 2);
        this.armeSecondaire = null;
        this.armure = null;
        this.competence = null;
    }
    
    public Inventory(BufferedReader br){
        String lecture[];
        try{
            String ligne=br.readLine();
            lecture=ligne.split("=");
            lecture=lecture[1].split(" ");
            this.armePrincipale =new Weapon(lecture);
            
            ligne=br.readLine();
            lecture=ligne.split("=");
            if(lecture.length >1){
                lecture=lecture[1].split(" ");
                this.armeSecondaire =new SecondWeapon(lecture);
            }
            else{
                this.armeSecondaire = null;
            }
            
            ligne=br.readLine();
            lecture=ligne.split("=");
            if(lecture.length >1){
                lecture=lecture[1].split(" ");
                this.armure =new Armor(lecture);
            }
            else{
                this.armure = null;
            }
            
            ligne=br.readLine();
            lecture=ligne.split("=");
            if(lecture.length >1){
                lecture=lecture[1].split(" ");
                this.competence = new Spell(lecture);
            }
            else{
                this.competence = null;
            }
        }
        catch (Exception e){
                System.out.println(e.toString());
        }
        
        
    }
    
    public void setMainWeapon(Weapon a){
        this.armePrincipale = a;
    }
    
    public void setSecondWeapon(SecondWeapon a){
        this.armeSecondaire = a;
    }
    
    public void setArmor(Armor a){
        this.armure = a;
    }
    
    public void setSpell(Spell c){
        this.competence = c;
    }
    
    public Weapon getMainWeapon(){
        return armePrincipale;
    }
    
    public SecondWeapon getSecondWeapon(){
        return armeSecondaire;
    }
    
    public Armor getArmor(){
        return armure;
    }
    
    public Spell getSpell(){
        return competence;
    }
    
    public void drawInventory(View v, int affiche){
        String car;
        
        switch(affiche)
        {
            case 1 : v.addString("Arme principale :");
                if(armePrincipale != null){
                v.addString("Nom : "+ armePrincipale.nom);
                v.addString("Dégats d'arme : " + armePrincipale.dmin + " - " + armePrincipale.dmax);
                v.addString("Ratio : ");

                switch(armePrincipale.carac)
                {
                        case 1 : car = "% de la VITALITE"; break;
                        case 2 : car = "% de la FORCE";  break;
                        case 3 : car = "% de la DEXTERITE"; break;
                        case 4 : car = "% de l'INTELLIGENCE"; break;
                        default : car = "Error";
                }
                v.concatLastLigne(" + "+ armePrincipale.ratio + car);
            }break;

            case 2 : v.addString("Arme secondaire :");
                if(armeSecondaire != null){
                v.addString("Nom : " + armeSecondaire.nom);
                v.addString("Dégats d'arme : " + armeSecondaire.dmin + " - " + armeSecondaire.dmax);
                v.addString("Munitions restantes : " + armeSecondaire.getMunitions());
                v.addString("Ratio : ");

                switch(armeSecondaire.carac)
                {
                        case 1 : car = "% de la VITALITE"; break;
                        case 2 : car = "% de la FORCE";  break;
                        case 3 : car = "% de la DEXTERITE"; break;
                        case 4 : car = "% de l'INTELLIGENCE"; break;
                        default : car = "Error";
                }
                v.concatLastLigne(" + "+ armeSecondaire.ratio + car);
            }break;

            case 3 : v.addString("Armure :");
                if(armure != null){
                v.addString("Nom : " + armure.nom);
                v.addString("Vitalité : " + armure.getVitalite());
                v.addString("Force : " + armure.getForce());
                v.addString("Dexterité : " + armure.getDexterite());
                v.addString("Intelligence : " + armure.getIntell());
                v.addString("Durabilité : " + armure.getDurabilite());
            }break;

            case 4 : v.addString("Compétence :");
                if(competence != null){
                v.addString("Nom : " + competence.nom);
                if (competence.isHeal()){
                    v.addString("Soins : ");
                }
                else{
                    v.addString("Dégats : ");
                }
                v.concatLastLigne(competence.dmin + " - " + competence.dmax);
                v.addString("Ratio : ");

                switch(competence.carac)
                {
                        case 1 : car = "% de la VITALITE"; break;
                        case 2 : car = "% de la FORCE";  break;
                        case 3 : car = "% de la DEXTERITE"; break;
                        case 4 : car = "% de l'INTELLIGENCE"; break;
                        default : car = "Error";
                }
                v.concatLastLigne(" + "+ competence.ratio + car);
            }break;
        }
    }
    
    public void save(PrintWriter fichierSortie){
        fichierSortie.print("armePrincipale=");
        if (armePrincipale != null){
            armePrincipale.save(fichierSortie);
        }
        else{
            fichierSortie.println();
        }
        
        fichierSortie.print("armeSecondaire=");
        if (armeSecondaire != null){
            armeSecondaire.save(fichierSortie);
        }
        else{
            fichierSortie.println();
        }

        fichierSortie.print("armure=");
        if (armure != null){
            armure.save(fichierSortie);
        }
        else{
            fichierSortie.println();
        }

        fichierSortie.print("competence=");
        if (competence != null){
            competence.save(fichierSortie);
        }
        else{
            fichierSortie.println();
        }
    }
    
}
