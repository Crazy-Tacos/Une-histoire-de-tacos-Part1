package rpgcommercial;

import java.util.Scanner;

public class RpgCommercial {
    
    
    public static void main(String[] args){
        
        
        Scanner scan=new Scanner(System.in);
    	int choix = 0;
        boolean arret = false;
        
        Vue vue  = new Vue();
         
        while (!arret)
        {    
            vue.addChaine("Aller sur le Menu : tapez 1");
	    vue.addChaine("Tester un Combat : tapez 2");
	    vue.addChaine("Quitter : tapez 3");
            vue.draw();
            do{
                    choix = scan.nextInt();
	        }while(choix<1 || choix>3);
            
            switch(choix)
	    {
                case 1 : menu(vue, scan);  break;
	        case 2 : testCombat(vue); break;              
	        case 3 : arret = true; break;
	        default : System.out.println("\nFatal Error"); return ;
            }
        }
        
    }
    
    public static void testCombat(Vue jeu){
        Joueur control = new Joueur();
        Personnage player = new Boulanger("Fred");
        
        jeu.drawCalibrate(control);
        
        Personnage ennemi = new Personnage("Rat", 1, 11, 50, 20, 10, 10, 10);

        Combat combat = new Combat(control,jeu,player, ennemi);
        combat.doCombat();
        
        player.drawPersonnage(jeu);
        control.pause(jeu);
        
        ennemi = new Personnage("Planche", 2, 15, 0, 10, 12, 12, 12);
        ennemi.getInventaire().setArmePrincipale(new Arme("Clou", 2,10,5,2));
        
        combat = new Combat(control,jeu, player, ennemi);
        combat.doCombat();
        
        player.drawPersonnage(jeu);
        jeu.drawPause(control);
    }
    
    public static void menu(Vue jeu, Scanner scan){
    	int choix = 0;
        boolean arret = false;
        while (!arret)
        {
	        jeu.addChaine("-- CRAZY TACOS --");
	        jeu.addChaine("Nouvelle Partie : tapez 1");
	        jeu.addChaine("Charger Partie : tapez 2");
	        jeu.addChaine("Quitter : tapez 3");
	        jeu.draw();
	        do{
                    choix = scan.nextInt();
	        }while(choix<1 || choix>3);
	         
	        switch(choix)
	        {
	                case 1 :arret = nouvellePartie(jeu); break;
	                case 2 : arret = chargerPartie(jeu);  break;
	                case 3 : arret = true; break;
	                default : System.out.println("\nFatal Error"); return ;
	        }
        }
    }
    
    public static boolean chargerPartie(Vue vue) {
    	Jeu jeu =new Jeu();        
        
        vue.addChaine("Chargement ...");
        vue.draw();
        
        jeu.charger(vue);
        jeu.lancerAventure(vue);
        
        return true;
    }
    
     public static boolean nouvellePartie(Vue vue){        
        Scanner scan=new Scanner(System.in);
    	int choix = 0;
        boolean arret = false;        
        
        String nom;
        Jeu jeu=new Jeu();

        vue.addChaine("\n--- Choisi une classe ---");
        vue.addChaine("Le Boulanger : tapez 1");
        vue.addChaine("Le Cuisinier : tapez 2");
        vue.addChaine("Le SaV : tapez 3");
        vue.addChaine("Le Pharmacien : tapez 4");
        vue.addChaine("Retour : tapez 5");
        vue.draw();

        do{
            choix = scan.nextInt();
         }while(choix<1 || choix>5);

        switch(choix){
                case 1 : jeu.setPersonnage(new Boulanger("Connard")); break;
                case 2 : jeu.setPersonnage(new Cuisinier("Connard")); break;
                case 3 : jeu.setPersonnage(new Sav("Connard")); break;
                case 4 : jeu.setPersonnage(new Pharmacien("Connard")); break;
                case 9 : arret = true; break;
                default : System.out.println("Fatal Error"); return true ;
        }
        
        if(!arret)
        {
            vue.addChaine("\n--- Choisi un nom ---");// ATTENTION NOM CONTROLLEUR
            vue.draw();
            scan.nextLine();
            nom=scan.nextLine();

            vue.addChaine("As-tu choisis le nom Connard?");
            vue.addChaine("oui : tapez 1");
            vue.addChaine("non : tapez 2");
            vue.draw();

            do{
                choix = scan.nextInt();
            }while(choix<1 || choix>2);

            vue.addChaine("Super tu seras donc Connard!!");
            jeu.getJoueur().pause(vue);
            
            jeu.lancerAventure(vue);
        }
        
        return true;
    }
}