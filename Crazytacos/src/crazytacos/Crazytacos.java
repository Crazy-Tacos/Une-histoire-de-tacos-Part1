package crazytacos;

import java.io.File;
import java.util.Scanner;

public class Crazytacos {
    
    
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
    	int choix = 0;
        boolean arret = false;
        
        Jeu jeu  = new Jeu();
         
        while (!arret)
        {    
            jeu.addChaine("Aller sur le Menu : tapez 1");
	    jeu.addChaine("Tester un Combat : tapez 2");
            jeu.addChaine("Tester lecture fichier : tapez 3");
	    jeu.addChaine("Quitter : tapez 4");
            jeu.draw();
            do{
                    choix = scan.nextInt();
	        }while(choix<1 || choix>4);
            
            switch(choix)
	    {
                case 1 : menu(jeu);  break;
	        case 2 : testCombat(jeu); break;
                case 3 : testFichier(jeu); break;                
	        case 4 : arret = true; break;
	        default : System.out.println("\nFatal Error"); return ;
            }
        }
    }

    public static void menu(Jeu jeu){
        Scanner scan=new Scanner(System.in);
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
    
    public static boolean chargerPartie(Jeu jeu) {
    	jeu.addChaine("Chargement ...");
        jeu.draw();
        return true;
    }
    
    public static boolean nouvellePartie(Jeu jeu){        
        Scanner scan=new Scanner(System.in);
    	int choix = 0;
        boolean arret = false;
        
        String nom;
        Personnage player = null;

        jeu.addChaine("\n--- Choisi une classe ---");
        jeu.addChaine("Le Boulanger : tapez 1");
        jeu.addChaine("Le Cuisinier : tapez 2");
        jeu.addChaine("Le SaV : tapez 3");
        jeu.addChaine("Le Pharmacien : tapez 4");
        jeu.addChaine("Retour : tapez 5");
        jeu.draw();

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
            jeu.addChaine("\n--- Choisi un nom ---");
            jeu.draw();
            scan.nextLine();
            nom=scan.nextLine();

            jeu.addChaine("As-tu choisis le nom Tacos?");
            jeu.addChaine("oui : tapez 1");
            jeu.addChaine("non : tapez 2");
            jeu.draw();

            do{
                choix = scan.nextInt();
            }while(choix<1 || choix>2);

            jeu.addChaine("Super tu seras dons Tacos!!");
            jeu.addChaine("tapez sur une touche pour lancer l'aventure");
            jeu.draw();            
            scan.nextLine();
            nom=scan.nextLine();
            
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
    
    public static void testCombat(Jeu jeu){
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
    
    public static void testFichier(Jeu jeu){
        File f = new File("test.txt");
        
        System.out.println("Chemin absolu du fichier : " + f.getAbsolutePath());
        System.out.println("Nom du fichier : " + f.getName());
        System.out.println("Est-ce qu'il existe ? " + f.exists());
        System.out.println("Est-ce un répertoire ? " + f.isDirectory());
        System.out.println("Est-ce un fichier ? " + f.isFile());
    }
}
