package rpgcommercial;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Inventaire {
    private Arme armePrincipale;
    private ArmeSecondaire armeSecondaire;
    private Armure armure;
    private Competence competence;
    
    public Inventaire(){
        this.armePrincipale =new Arme("Mains nues", 1, 3, 5, 2);
        this.armeSecondaire = null;
        this.armure = null;
        this.competence = null;
    }
    
    public Inventaire(BufferedReader br){
        String lecture[];
        try{
            String ligne=br.readLine();
            lecture=ligne.split("=");
            lecture=lecture[1].split(" ");
            this.armePrincipale =new Arme(lecture);
            
            ligne=br.readLine();
            lecture=ligne.split("=");
            if(lecture.length >1){
                lecture=lecture[1].split(" ");
                this.armeSecondaire =new ArmeSecondaire(lecture);
            }
            else{
                this.armeSecondaire = null;
            }
            
            ligne=br.readLine();
            lecture=ligne.split("=");
            if(lecture.length >1){
                lecture=lecture[1].split(" ");
                this.armure =new Armure(lecture);
            }
            else{
                this.armure = null;
            }
            
            ligne=br.readLine();
            lecture=ligne.split("=");
            if(lecture.length >1){
                lecture=lecture[1].split(" ");
                this.competence = null;
            }
            else{
                this.competence = null;
            }
        }
        catch (Exception e){
                System.out.println(e.toString());
        }
        
        
    }
    
    public void setArmePrincipale(Arme a){
        this.armePrincipale = a;
    }
    
    public void setArmeSecondaire(ArmeSecondaire a){
        this.armeSecondaire = a;
    }
    
    public void setArmure(Armure a){
        this.armure = a;
    }
    
    public void setCompetence(Competence c){
        this.competence = c;
    }
    
    public Arme getArmePrincipale(){
        return armePrincipale;
    }
    
    public ArmeSecondaire getArmeSecondaire(){
        return armeSecondaire;
    }
    
    public Armure getArmure(){
        return armure;
    }
    
    public Competence getCompetence(){
        return competence;
    }
    
    public void drawInventaire(Vue v, int affiche){
        String car;
        
        switch(affiche)
        {
            case 1 : v.addChaine("Arme principale :");
                if(armePrincipale != null){
                v.addChaine("Nom : "+ armePrincipale.getNom());
                v.addChaine("Dégats d'arme : " + armePrincipale.getDmin() + " - " + armePrincipale.getDmax());
                v.addChaine("Ratio : ");

                switch(armePrincipale.getCarac())
                {
                        case 1 : car = "% de la VITALITE"; break;
                        case 2 : car = "% de la FORCE";  break;
                        case 3 : car = "% de la DEXTERITE"; break;
                        case 4 : car = "% de l'INTELLIGENCE"; break;
                        default : car = "Error";
                }
                v.concatLastLigne(" + "+ armePrincipale.getRatio() + car);
            }break;

            case 2 : v.addChaine("Arme secondaire :");
                if(armeSecondaire != null){
                v.addChaine("Nom : " + armeSecondaire.getNom());
                v.addChaine("Dégats d'arme : " + armeSecondaire.getDmin() + " - " + armeSecondaire.getDmax());
                v.addChaine("Munitions restantes : " + armeSecondaire.getMunitions());
                v.addChaine("Ratio : ");

                switch(armeSecondaire.getCarac())
                {
                        case 1 : car = "% de la VITALITE"; break;
                        case 2 : car = "% de la FORCE";  break;
                        case 3 : car = "% de la DEXTERITE"; break;
                        case 4 : car = "% de l'INTELLIGENCE"; break;
                        default : car = "Error";
                }
                v.concatLastLigne(" + "+ armeSecondaire.getRatio() + car);
            }break;

            case 3 : v.addChaine("Armure :");
                if(armure != null){
                v.addChaine("Nom : " + armure.getNom());
                v.addChaine("Vitalité : " + armure.getVitalite());
                v.addChaine("Force : " + armure.getForce());
                v.addChaine("Dexterité : " + armure.getDexterite());
                v.addChaine("Intelligence : " + armure.getIntell());
                v.addChaine("Durabilité : " + armure.getDurabilite());
            }break;

            case 4 : v.addChaine("Compétence :");
                if(competence != null){//TODO
                v.addChaine("Nom : " + competence.getNom());
            }break;
        }
    }
    
    public void sauvegarder(PrintWriter fichierSortie){
        fichierSortie.print("armePrincipale=");
        if (armePrincipale != null){
            armePrincipale.sauvegarder(fichierSortie);
        }
        else{
            fichierSortie.println();
        }
        
        fichierSortie.print("armeSecondaire=");
        if (armeSecondaire != null){
            armeSecondaire.sauvegarder(fichierSortie);
        }
        else{
            fichierSortie.println();
        }

        fichierSortie.print("armure=");
        if (armure != null){
            armure.sauvegarder(fichierSortie);
        }
        else{
            fichierSortie.println();
        }

        fichierSortie.print("competence=");
        if (competence != null){
            competence.sauvegarder(fichierSortie);
        }
        else{
            fichierSortie.println();
        }
    }
    
}
