package rpgcommercial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
    
    public Personnage chargerPersonnage(int id){
        String fichier ="chapitre.txt" + avancement;
        
        try{
                InputStream ips=new FileInputStream(fichier); 
                InputStreamReader ipsr=new InputStreamReader(ips);
                BufferedReader br=new BufferedReader(ipsr);
                
                String ligne;
                String recherche="id="+id;
                
                while ((ligne=br.readLine())!=recherche || ligne!="fin"){
                    for(int i=0; i<=6; i++)
                        br.readLine();
                }
                Personnage perso;
                        
                if(ligne != "fin"){                    
                    perso=chargerPersonnage(br);
                }
                else
                {
                    perso=null;
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
        String fichier ="Sauvegarder.txt";
        
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
    }
    
    public void charger(Vue vue){
        String fichier="Sauvegarder.txt";

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
    
    public boolean lancerAventure(Vue vue){
        
        boolean arret=false;
        
        while (!arret)
        {
            vue.addChaine("-- CRAZY TACOS --");
            vue.addChaine("Chapitre 1 : tapez 1");
            vue.addChaine("Chapitre 2 : tapez 2");
            vue.addChaine("Sauvegarder la partie : tapez 3");
            vue.addChaine("Quitter : tapez 4");

            switch(joueur.lireChoix(vue,4))
            {
                    case 1 : lancerChapitre(vue,1); break;
                    case 2 : lancerChapitre(vue,1);  break;
                    case 3 : sauvegarder(vue); break;
                    case 4 : arret = true; break;
                    default : System.out.println("\nFatal Error"); return true ;
            }
        }        
        return true;
    }
    
    public boolean lancerChapitre(Vue vue, int avancement){
        //blablbal ici
        
        int id=0;
        Personnage ennemi=null;
        
        while((ennemi= chargerPersonnage(id)) != null)
        {
            vue.addChaine("un mechant est là!");
            joueur.pause(vue);
                                   
            Combat combat =new Combat(joueur,vue, personnage, ennemi);
            combat.doCombat();
        }
        
        this.avancement++;
        return true;
    }    
    
    public void setPersonnage(Personnage perso){
        this.personnage=perso;
    }
    
    public Joueur getJoueur(){
        return this.joueur;
    }
}
