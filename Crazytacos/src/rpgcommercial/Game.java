package rpgcommercial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Game {
    private Caracter personnage;
    private int avancement;
    private PlayerControl joueur;
    
    public Game(){
        this.personnage=null;
        this.avancement=0;
        this.joueur=new PlayerControl();
    }
    
    public PlayerControl getPlayer(){
        return this.joueur;
    }  
    
    public boolean loadGame(View vue) {        
        vue.addString("Chargement ...");
        vue.draw();
        
        this.load(vue);
        this.runAdventure(vue);        
        return true;
    }   
    
      public boolean newGame(View vue){ 
        
        boolean arret = false;
        vue.addString("Je vais vous raconter mon histoire");
        vue.addString("");
        vue.addString("Tout à commencer un mardi matin, j'étais dans ma boutique de...");
        vue.addString("heu...");
        vue.addString("Quel-était mon metier de l'époque?");
        
        joueur.pause(vue);
        
        vue.addString("--- Choisi une profession ---");
        vue.addString("Le Boulanger : tapez 1");
        vue.addString("Le Cuisinier : tapez 2");
        vue.addString("Le relou du SaV de Darty : tapez 3");
        vue.addString("Le Pharmacien : tapez 4");
        vue.addString("Retour : tapez 5");
        int classe = this.joueur.readChose(vue, 5);
        
        if (classe == 5){
            arret = true;
        }
        
        if(!arret)
        {
            vue.addString("Je vais vous avouer que j'ai quelques trous de mémoire depuis ce jour maudit.");
            vue.addString("Pourriez-vous également me rappeler mon nom du coup?");
            joueur.pause(vue);
            String nom;
            do{
                vue.addString("--- Choisi un nom ---");
                nom =this.joueur.readString(vue);

                vue.addString("As-tu choisis le nom " + nom + " ?");
                vue.addString("oui : tapez 1");
                vue.addString("non : tapez 2");
            } while(this.joueur.readChose(vue, 2) != 1);
            
            switch(classe){
                case 1 :    vue.addString("Ha oui voilà je suis " + nom + " et j'étais dans ma boulangerie.");
                            this.personnage=new Baker(nom); break;
                case 2 :    vue.addString("Ha oui voilà je suis " + nom + " et j'étais dans ma cuisine.");
                            this.personnage=new Cook(nom); break;
                case 3 :    vue.addString("Ha oui voilà je suis " + nom + " et j'étais au SaV de Darty.");
                            this.personnage=new Sav(nom); break;
                case 4 :    vue.addString("Ha oui voilà je suis " + nom + " et j'étais dans ma pharmacie.");
                            this.personnage=new Pharmacien(nom); break;
                default : System.out.println("Fatal Error"); return true ;
            }
            
            vue.addString("Quand soudainement j'entendis des hurlements venant du hall...");
            this.joueur.pause(vue);
            
            save(vue);
            
            this.runAdventure(vue);
        }
        
        return true;
    }
     
    public Caracter loadCaracter(BufferedReader br){
        String lecture[];
        try{           
            String ligne=br.readLine();
            lecture=ligne.split("=");
            
            Caracter perso = null;
            
            if (null != lecture[1])switch (lecture[1]) {
                case "Boulanger":
                    perso= new Baker(br);
                    break;
                case "Cuisinier":
                    perso= new Cook(br);
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
    
    public Fight loadFight(int id, int avancement, View vue){
        String fichier ="CentreCommercial/chapitre" + avancement + ".txt";
        Fight combat;
        Caracter perso;
        
        try{ 
                InputStream ips=new FileInputStream(fichier); 
                InputStreamReader ipsr=new InputStreamReader(ips);
                BufferedReader br=new BufferedReader(ipsr);
                                                   
                String ligne;
                String recherche="id=" + id;
                ligne=br.readLine();
                    
                while (ligne.compareTo(recherche) !=0 && ligne.compareTo("fin") != 0){

                    for(int i=0; i<7; i++)
                        br.readLine();
                    
                    ligne=br.readLine();
                }
                    
                if(ligne.equals("fin")){
                    combat=null;
                }
                else
                {                       
                    perso=new Caracter(br);
                    ligne=br.readLine();
                    String lecture[];
                    lecture=ligne.split("=");
                    int nbTour=Integer.parseInt(lecture[1]);
                    
                
                    if(nbTour<=0)
                        combat= new Fight(joueur, vue, personnage, perso);
                    else
                        combat= new FightLimit(joueur, vue, personnage, perso,nbTour);
                }
                br.close();
                return combat;
        }		
        catch (Exception e){
                System.out.println(e.toString());
        }
        return null;
    }
    
    public void save(View vue){
        String fichier ="sauvegarde.txt";
        
        try{
            FileWriter fw = new FileWriter(fichier);
            BufferedWriter bw = new BufferedWriter(fw);
            try (PrintWriter fichierSortie = new PrintWriter(bw)) {
                fichierSortie.println("Avancement="+avancement);
                if (personnage != null){
                    personnage.save(fichierSortie);
                }
                fichierSortie.println("fin");
            }
        }
        catch (Exception e){
                System.out.println(e.toString());
        }
        
        vue.addString("La Partie a été enregistrée!!");
        this.joueur.pause(vue);
    }
    
    public void load(View vue){
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
            this.personnage = loadCaracter(br);
        }		
        catch (IOException | NumberFormatException e){
                System.out.println(e.toString());
        }
    }
    
    public boolean chooseChapitre(View vue){
        
        int choix;
        
        vue.addString("-- Panique au Centre Commercial --");
        vue.addString("Votre Boutique : tapez 1");
        vue.addString("Magasin de chaussure : tapez 2");
        vue.addString("McDonald's : tapez 3");
        vue.addString("Retour : tapez 4");             
        choix =joueur.readChose(vue,4);

        while(choix-1 > this.avancement && choix <= 3){
            vue.addString("Attention!!!");
            vue.addString("Cette zone n'est pas encore débloquée.");
            joueur.pause(vue);

            vue.addString("-- Panique au Centre Commercial  --");
            vue.addString("Votre Boutique : tapez 1");
            vue.addString("Magasin de chaussure : tapez 2");
            vue.addString("McDonald's : tapez 3");
            vue.addString("Retour : tapez 4");            
            choix =joueur.readChose(vue,4);
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
    
    public boolean runAdventure(View vue){
        
        boolean arret=false;
        
        while (!arret)
        {                
            vue.addString("-- Panique au Centre Commercial  --");
            vue.addString("Choisir un Chapitre : tapez 1");
            vue.addString("Voir les informations du personnage : tapez 2");
            vue.addString("Sauvegarder la partie : tapez 3");
            vue.addString("Retour au menu principal : tapez 4");
            
            switch(joueur.readChose(vue,4))
            {
                    case 1 : chooseChapitre(vue); break;
                    case 2 : drawInfo(vue);  break;
                    case 3 : save(vue); break;
                    case 4 : arret = leaveGame(vue); break;
                    default : System.out.println("\nFatal Error"); return true ;
            }
        }        
        return true;
    }
    
    public void drawInfo(View vue){
        personnage.drawCaracter(vue);
        joueur.pause(vue);
        for(int i=1; i<5; i++){            
            personnage.getInventory().drawInventory(vue, i);
            joueur.pause(vue);
        }
    }
    
    public boolean leaveGame(View vue){        
        vue.addString("Tout avancement non sauvegardé sera perdu!!");
        vue.addString("Vouez-vous vraiment quitter?");
        vue.addString("oui : tapez 1");
        vue.addString("non : tapez 2");
        
        switch(joueur.readChose(vue,2))
        {
            case 1 : return true;
            case 2 : return false;
            default : System.out.println("\nFatal Error"); return true ;
        }
    }
            
    public boolean lancerChapitre(View vue, int avancement){        
        int id=0;
        boolean vivant=true;
        Fight combat;         
        
        if (avancement == this.avancement){
            lireHistoire(vue, avancement);
        }
        
        combat = loadFight(id, avancement, vue);
        
        while(combat != null && vivant)
        {
            vue.addString("Un mechant sauvage apparait!!");
            joueur.pause(vue);
            
            if(combat.doFight()){                
                vue.addString("J'ai réussi à battre " + combat.getEnnemi().getName() + " !");
                joueur.pause(vue);
                
                combat.winStuff();
                
                id++;
                combat = loadFight(id, avancement, vue); 
            }
            else{                
                vue.addString("J'ai pris une raclé par " + combat.getEnnemi().getName());
                personnage.loseFight(vue);
                joueur.pause(vue);
                
                vivant=false;
            }
        }
        
        if(vivant){            
            vue.addString("Tout les méchants ont mouru!");
            vue.addString("");
            personnage.regen();
            save(vue);
        }
        
        if(this.avancement==avancement && vivant){
            this.avancement++;
            vue.addString("Félicitation, vous avez débloquer le chapitre suivant!!");
            joueur.pause(vue);
        }
        
        return true;
    }    
    
    public void lireHistoire(View vue, int avancement){
        String fichier ="CentreCommercial/chapitre" + avancement + "_histoire.txt";
        
         try{                
            InputStream ips=new FileInputStream(fichier); 
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);

            String ligne;                

            while ((ligne=br.readLine()) !=null){
                if("[PAUSE]".equals(ligne)){
                    joueur.pause(vue); 
                } 
                else {
                    vue.addString(ligne);
                }
            }
            joueur.pause(vue); 
         }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
