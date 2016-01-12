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
    
    public void chargerEnnemi(Vue vue, int id){
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
                br.close();
        }		
        catch (Exception e){
                System.out.println(e.toString());
        }
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
            
            this.personnage.charger();
        }		
        catch (Exception e){
                System.out.println(e.toString());
        }
    }
    
    public boolean lancerAventure(Vue vue){
         vue.addChaine("-- CRAZY TACOS --");
                vue.addChaine("Chapitre 1 : tapez 1");
	        vue.addChaine("Chapitre 2 : tapez 2");
	        vue.addChaine("Quitter : tapez 3");
	        vue.draw();
                
	        switch(joueur.lireChoix(3))
	        {
	                case 1 : return lancerChapitre(vue,1); break;
	                case 2 : return lancerChapitre(vue,2);  break;
	                case 3 : return true; break;
	                default : System.out.println("\nFatal Error"); return ;
	        }
        
        return true;
    }
    
    public boolean lancerChapitre(Vue vue, int avancement){
        
    }    
    
}
