package crazytacos;

import java.util.Scanner;

public class Combat {
    private Personnage joueur;
    private Personnage ennemi;
    private Jeu j;
    
    public Combat (Jeu j,Personnage joueur, Personnage ennemi) {
        this.joueur = joueur;
        this.ennemi = ennemi;
        this.j = j;
    }
    
    public Personnage getJoueur(){
        return joueur;
    }
    
    public Personnage getEnnemi(){
        return ennemi;
    }
    
    public void drawCombat(){
        j.addChaine("--- COMBAT ---");
        j.addChaine(joueur.getNom() + " VS " + ennemi.getNom());
        int i = j.getNextLigne();
        joueur.drawPersonnage(j);
        ennemi.drawPersonnage(j,i, 30);
    }
    
    public void doCombat(){
        drawCombat();
        boolean tourJ;
        int action;
        Scanner in = new Scanner(System.in);
        j.addChaine("Le plus intelligent et le plus agile commence : ");
        if ((joueur.getDext() + joueur.getIntell()) >= (ennemi.getDext() + ennemi.getIntell())){
            tourJ = true;
            j.concatLasLigne(joueur.getNom());
        }
        else {
            tourJ = false;
            j.concatLasLigne(ennemi.getNom());
        }
        j.draw();
        
        while(joueur.getVie() != 0 && ennemi.getVie() !=0){
            if (tourJ){
                // Le joueur choisi son action
                do{
                    action = in.nextInt();
                } while (joueur.attaquer(action) == -1);
                ennemi.infligerDegats(joueur.attaquer(action));
            }
            else{
                // L'ennemi auto attaque (super IA !!!)
                joueur.infligerDegats(ennemi.attaquer(1));
            }
            drawCombat();
            if(!tourJ) {
                j.draw();
            }
            else
            {
                j.initEcran();
            }
            tourJ = !tourJ;
        }
        
        if (joueur.getVie()>0){
            System.out.println("Vous avez battu " + ennemi.getNom() + " !");
            joueur.gagnerXP(ennemi.getXP());
            joueur.gagnerArgent(ennemi.getArgent());
            if(ennemi.getArme() != null){
                System.out.println("Vous possèdez : ");
                if(joueur.getArme() != null){
                    joueur.getArme().drawArme();
                }
                else {
                    System.out.println("Aucune arme");
                }
                System.out.println();
                System.out.println("Remplacer par l'arme de " + ennemi.getNom() + " :");
                ennemi.getArme().drawArme();
                System.out.println(" 1.Oui");
                System.out.println(" 2.Non");
                do{
                    action = in.nextInt();
                } while (action < 1 || action >2);
                if (action == 1){
                    joueur.equipeArme(ennemi.getArme());
                }
            }
        }
        else {
            System.out.println("Vous avez perdu le combat face à " + ennemi.getNom());
            joueur.revivre();
        }
        
    }
    
    public void gagnerCombat(){
        
    }
}
