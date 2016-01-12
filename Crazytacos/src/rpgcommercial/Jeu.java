package rpgcommercial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
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
            Personnage perso= new Personnage(lecture[0],lecture[1]);

            ligne=br.readLine();
            lecture=ligne.split("=");
            if(lecture[1] != null)
                perso.getInventaire().setArmePrincipale(new Arme(lecture[0],lecture[1]));

            ligne=br.readLine();
            lecture=ligne.split("=");
            if(lecture[1] != null)                    
                perso.getInventaire().setArmeSecondaire(new Arme(lecture[0],lecture[1]));

            ligne=br.readLine();
            lecture=ligne.split("=");
            if(lecture[1] != null)                    
                perso.getInventaire().setArmure(new Armure(lecture[0],lecture[1]));

            ligne=br.readLine();
            lecture=ligne.split("=");
            if(lecture[1] != null)                    
                perso.getInventaire().setConsommable(new Consommable(lecture[0],lecture[1]));                   

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
                FileWriter fw = new FileWriter (fichier);
                BufferedWriter bw = new BufferedWriter (fw);
                PrintWriter fichierSortie = new PrintWriter (bw);
                
                fichierSortie.println ("Avacncement="+avancement);
                personnage.sauvegarder(fichierSortie);
                fichierSortie.println("fin");
                
                fichierSortie.close();
        }
        catch (Exception e){
                System.out.println(e.toString());
        }        
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
            chargerPersonnage(br);
        }		
        catch (Exception e){
                System.out.println(e.toString());
        }
    }
    
    public boolean lancerAventure(Vue vue){
         vue.addChaine("-- CRAZY TACOS --");
                vue.addChaine("Chapitre 1 : tapez 1");
	        vue.addChaine("Chapitre 2 : tapez 2");
                vue.addChaine("Sauvegarder la partie : tapez 1");
	        vue.addChaine("Quitter : tapez 4");
	        vue.draw();
                
	        switch(joueur.lireChoix(3))
	        {
	                case 1 : lancerChapitre(vue,1); break;
	                case 2 : lancerChapitre(vue,2);  break;
                        case 3 : sauvegarder(vue); break;
	                case 4 : break;
	                default : System.out.println("\nFatal Error"); return true ;
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
            vue.draw();
            joueur.pause();
                                   
            Combat combat =new Combat(joueur,vue, personnage, ennemi);
            combat.doCombat();
        }
        
        this.avancement++;
        return true;
    }    
    
}
