package crazytacos;

import java.util.Scanner;

public class Crazytacos {
    
    
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
    	int choix = 0;
        boolean arret = false;
         
        while (!arret)
        {    
            System.out.println("Aller sur le Menu : tapez 1");
	    System.out.println("Tester un Combat : tapez 2");
	    System.out.println("Quitter : tapez 3");
            do{
                    choix = scan.nextInt();
	        }while(choix<1 || choix>3);
            
            switch(choix)
	    {
                case 1 : menu();  break;
	        case 2 : testCombat(); break;                
	        case 3 : arret = true; break;
	        default : System.out.println("\nFatal Error"); return ;
            }
        }
    }

    public static void menu(){
        Scanner scan=new Scanner(System.in);
    	int choix = 0;
        boolean arret = false;
        
        while (!arret)
        {
	        System.out.println("-- CRAZY TACOS --");
	        System.out.println("Nouvelle Partie : tapez 1");
	        System.out.println("Charger Partie : tapez 2");
	        System.out.println("Quitter : tapez 3");
	        
	        do{
                    choix = scan.nextInt();
	        }while(choix<1 || choix>3);
	         
	        switch(choix)
	        {
	                case 1 :arret = nouvellePartie(); break;
	                case 2 : arret = chargerPartie();  break;
	                case 3 : arret = true; break;
	                default : System.out.println("\nFatal Error"); return ;
	        }
        }
    }
    
    public static boolean chargerPartie() {
    	System.out.println("Chargement ...");
        return true;
    }
    
    public static boolean nouvellePartie(){        
        Scanner scan=new Scanner(System.in);
    	int choix = 0;
        boolean arret = false;
        
        String nom;
        Personnage player = null;
        Jeu jeu = new Jeu();

        System.out.println("\n--- Choisi une classe ---");
        System.out.println("Le Boulanger : tapez 1");
        System.out.println("Le Cuisinier : tapez 2");
        System.out.println("Le SaV : tapez 3");
        System.out.println("Le Pharmacien : tapez 4");
        System.out.println("Retour : tapez 5");

        do{
            choix = scan.nextInt();
         }while(choix<1 || choix>5);

        switch(choix){
                case 1 : player = new Boulanger("Tacos"); break;
                case 2 : player = new Cuisinier("Tacos"); break;
                case 3 : player = new Sav("Tacos"); break;
                case 4 : player = new Pharmacien("Tacos"); break;
                case 9 : arret = true; break;
                default : System.out.println("Fatal Error"); return true ;
        }
        
        if(!arret)
        {
            System.out.println("\n--- Choisi un nom ---");
            scan.nextLine();
            nom=scan.nextLine();

            System.out.println("As-tu choisis le nom Tacos?");
            System.out.println("oui : tapez 1");
            System.out.println("non : tapez 2");

            do{
                choix = scan.nextInt();
            }while(choix<1 || choix>2);

            System.out.println("Super tu seras dons Tacos!!");                                                
            lancerAventure(jeu, player);
        }
        
        return true;
    }
            
    public static void lancerAventure(Jeu jeu, Personnage player){
        Personnage ennemi = new Personnage("Rat", 1, 11, 50, 20, 10, 10, 10);

        Combat combat = new Combat(jeu,player, ennemi);
        combat.doCombat();
        
        player.drawPersonnage(jeu);
        jeu.draw();
        
        ennemi = new Personnage("Planche", 2, 15, 0, 10, 12, 12, 12);
        ennemi.equipeArme(new Arme("Clou", 2,10,5,2));
        
        combat = new Combat(jeu, player, ennemi);
        combat.doCombat();
        
        player.drawPersonnage(jeu);
        jeu.draw();
    }
    
    public static void testCombat(){
        Jeu jeu = new Jeu();
        Personnage player = new Cuisinier("Tacos");
        Personnage ennemi = new Personnage("Rat", 1, 11, 50, 20, 10, 10, 10);

        Combat combat = new Combat(jeu,player, ennemi);
        combat.doCombat();

        player.drawPersonnage(jeu);
        jeu.draw();

        ennemi = new Personnage("Planche", 2, 15, 0, 10, 12, 12, 12);
        ennemi.equipeArme(new Arme("Clou", 2,10,5,2));

        combat = new Combat(jeu,player, ennemi);
        combat.doCombat();

        player.drawPersonnage(jeu);
        jeu.draw();
    }
    
}
