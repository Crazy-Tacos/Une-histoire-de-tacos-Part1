package rpgcommercial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Jeu {
    private Personnage personnage;
    private int avancement;
    private Joueur joueur;
    
    public Jeu(){
        this.personnage=null;
        this.avancement=0;
        this.joueur=new Joueur();
    }
    
    public Joueur getJoueur(){
        return this.joueur;
    }  
    
    public boolean chargerPartie(Vue vue) {        
        vue.addChaine("Chargement ...");
        vue.draw();
        
        this.charger(vue);
        this.lancerAventure(vue);        
        return true;
    }   
    
     public boolean nouvellePartie(Vue vue){ 
        
        boolean arret = false;

        vue.addChaine("\n--- Choisi une classe ---");
        vue.addChaine("Le Boulanger : tapez 1");
        vue.addChaine("Le Cuisinier : tapez 2");
        vue.addChaine("Le SaV : tapez 3");
        vue.addChaine("Le Pharmacien : tapez 4");
        vue.addChaine("Retour : tapez 5");
        
        
        switch(this.joueur.lireChoix(vue, 5)){
                case 1 : this.personnage=new Boulanger("Connard"); break;
                case 2 : this.personnage=new Cuisinier("Connard"); break;
                case 3 : this.personnage=new Sav("Connard"); break;
                case 4 : this.personnage=new Pharmacien("Connard"); break;
                case 9 : arret = true; break;
                default : System.out.println("Fatal Error"); return true ;
        }
        
        if(!arret)
        {
            vue.addChaine("\n--- Choisi un nom ---");
            this.joueur.lireString(vue);

            vue.addChaine("As-tu choisis le nom Connard?");
            vue.addChaine("oui : tapez 1");
            vue.addChaine("non : tapez 2");
            this.joueur.lireChoix(vue, 2);

            vue.addChaine("Super tu seras donc Connard!!");
            this.joueur.pause(vue);
            
            this.lancerAventure(vue);
        }
        
        return true;
    }
     
    public Personnage chargerPersonnage(BufferedReader br){
        String lecture[];
        try{           
            String ligne=br.readLine();
            lecture=ligne.split("=");
            
            Personnage perso = null;
            
            if (null != lecture[1])switch (lecture[1]) {
                case "Boulanger":
                    perso= new Boulanger(br);
                    break;
                case "Cuisinier":
                    perso= new Cuisinier(br);
                    break;
                case "Pharmacien":
                    perso= new Pharmacien(br);
                    break;
                case "SAV":
                    perso = new Sav(br);
                    break;
                default:
                    perso = null;
            }
            return perso;
        }
        catch (Exception e){
                System.out.println(e.toString());
        }
        return null;
    }
    
    public Personnage chargerEnnemi(int id, int avancement){
        String fichier ="chapitre" + avancement + ".txt";        
        Personnage perso;        
        
        try{                
                InputStream ips=new FileInputStream(fichier); 
                InputStreamReader ipsr=new InputStreamReader(ips);
                BufferedReader br=new BufferedReader(ipsr);
                                                   
                String ligne;
                String recherche="id=" + id;
                ligne=br.readLine();                
                    
                while (ligne.compareTo(recherche) !=0 && ligne.compareTo("fin") != 0){
                                       
                    for(int i=0; i<6; i++)
                        br.readLine();
                    
                    ligne=br.readLine();
                }
                    
                if(ligne.equals("fin")){
                    perso=null;
                }
                else
                {   
                    perso=new Personnage(br);
                }
                br.close();
                return perso;
        }		
        catch (Exception e){
                System.out.println(e.toString());
        }
        return null;
    }
    
    public void sauvegarder(Vue vue){
        String fichier ="sauvegarde.txt";
        
        try{
            FileWriter fw = new FileWriter(fichier);
            BufferedWriter bw = new BufferedWriter(fw);
            try (PrintWriter fichierSortie = new PrintWriter(bw)) {
                fichierSortie.println("Avancement="+avancement);
                if (personnage != null){
                    personnage.sauvegarder(fichierSortie);
                }
                fichierSortie.println("fin");
            }
        }
        catch (Exception e){
                System.out.println(e.toString());
        }
        
        vue.addChaine("La Partie a étée enregistrée!!");
        this.joueur.pause(vue);
    }
    
    public void charger(Vue vue){
        String fichier="sauvegarde.txt";

        try{
            InputStream ips=new FileInputStream(fichier); 
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            
            String ligne=br.readLine();
            String lecture[];
            lecture=ligne.split("=");
            
            this.avancement=Integer.parseInt(lecture[1]);
            
            //Chargement Personnage
            this.personnage = chargerPersonnage(br);
        }		
        catch (IOException | NumberFormatException e){
                System.out.println(e.toString());
        }
        personnage.drawPersonnage(vue);
        vue.draw();
    }
    
    public boolean choisirChapitre(Vue vue){
        
        int choix;
        
        vue.addChaine("-- CRAZY TACOS --");
        vue.addChaine("Votre Boutique : tapez 1");
        vue.addChaine("Magasin de chaussure : tapez 2");
        vue.addChaine("McDonald's : tapez 3");
        vue.addChaine("Retour : tapez 4");             
        choix =joueur.lireChoix(vue,4);

        while(choix-1 > this.avancement && choix < 3){
            vue.addChaine("Attention!!!");
            vue.addChaine("Cette zone n'est pas encore débloquée.");
            joueur.pause(vue);

            vue.addChaine("-- CRAZY TACOS --");
            vue.addChaine("Votre Boutique : tapez 1");
            vue.addChaine("Magasin de chaussure : tapez 2");
            vue.addChaine("McDonald's : tapez 3");
            vue.addChaine("Retour : tapez 4");            
            choix =joueur.lireChoix(vue,4);
        }

        switch(choix)
        {
                case 1 : lancerChapitre(vue,0); break;
                case 2 : lancerChapitre(vue,1);  break;
                case 3 : lancerChapitre(vue,2); break;
                case 4 : return true;
                default : System.out.println("\nFatal Error"); return true ;
        }     
        return true;
    }
    
    public boolean lancerAventure(Vue vue){
        
        boolean arret=false;
        
        while (!arret)
        {                
            vue.addChaine("-- CRAZY TACOS --");
            vue.addChaine("Choisir un Chapitre : tapez 1");
            vue.addChaine("Voir les informations du personnage : tapez 2");
            vue.addChaine("Sauvegarder la partie : tapez 3");
            vue.addChaine("Retour au menu principale : tapez 4");
            
            switch(joueur.lireChoix(vue,4))
            {
                    case 1 : choisirChapitre(vue); break;
                    case 2 : afficherInfo(vue);  break;
                    case 3 : sauvegarder(vue); break;
                    case 4 : arret = quitterPartie(vue); break;
                    default : System.out.println("\nFatal Error"); return true ;
            }
        }        
        return true;
    }
    public void afficherInfo(Vue vue){
        personnage.drawPersonnage(vue);
        joueur.pause(vue);
        for(int i=1; i<5; i++){            
            personnage.getInventaire().drawInventaire(vue, i);
            joueur.pause(vue);
        }
    }
    
    public boolean quitterPartie(Vue vue){        
        vue.addChaine("Tout avancement non sauvegardé sera perdu!!");
        vue.addChaine("Vouez-vous vraiment quitter?");
        vue.addChaine("oui : tapez 1");
        vue.addChaine("non : tapez 2");
        
        switch(joueur.lireChoix(vue,2))
        {
            case 1 : return true;
            case 2 : return false;
            default : System.out.println("\nFatal Error"); return true ;
        }
    }
            
    public boolean lancerChapitre(Vue vue, int avancement){        
        int id=0;
        boolean vivant=true;
        Personnage ennemi = chargerEnnemi(id, avancement);        
        
        lireHistoire(vue, avancement);
        
        while(ennemi != null && vivant)
        {
            vue.addChaine("Un mechant sauvage apparait!!");
            joueur.pause(vue);
                                   
            Combat combat =new Combat(joueur,vue, personnage, ennemi);
            if(combat.doCombat()){                
                vue.addChaine("Vous avez battu " + ennemi.getNom() + " !");
                joueur.pause(vue);
                
                combat.gagnerRecompenses();
                
                id++;
                ennemi = chargerEnnemi(id, avancement); 
            }
            else{                
                vue.addChaine("Vous avez perdu le combat face à " + ennemi.getNom());
                joueur.pause(vue);
                
                personnage.regen();
                
                vivant=false;
            }
        }
        
        if(vivant){            
            vue.addChaine("Tout les méchants ont mouru!");
            joueur.pause(vue);
        }
        
        if(this.avancement==avancement && vivant){
            this.avancement++;
            vue.addChaine("Félicitation, vous avez débloquer le chapitre suivant!!");
            joueur.pause(vue);
        }
        
        return true;
    }    
    
    public void lireHistoire(Vue vue, int avancement){
        String fichier ="chapitre" + avancement + "_histoire.txt";
        
         try{                
            InputStream ips=new FileInputStream(fichier); 
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);

            String ligne;                

            while ((ligne=br.readLine()) !=null){
                vue.addChaine(ligne);
            }
            joueur.pause(vue); 
         }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
