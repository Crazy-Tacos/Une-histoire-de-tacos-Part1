package rpgcommercial;

import java.util.Scanner;

public class Combat {
    private Personnage joueur;
    private Personnage ennemi;
    private Vue j;
    
    public Combat (Vue j,Personnage joueur, Personnage ennemi) {
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
            int degats;
            if (tourJ){
                // Le joueur choisi son action
                do{
                    action = in.nextInt();
                    //degats = joueur.attaquer(j,action);
                } while (degats == -1);
                ennemi.infligerDegats(j,degats);
            }
            else{
                // L'ennemi auto attaque (super IA !!!)
                joueur.infligerDegats(j,ennemi.attaquer(j,1));
            }
            
            if(!tourJ) {
                drawCombat();
                j.draw();
            }
            else
            {
                
            }
            tourJ = !tourJ;
        }
        
        if (joueur.getVie()>0){
            j.addChaine("Vous avez battu " + ennemi.getNom() + " !");
            joueur.gagnerXP(j,ennemi.getXP());
            joueur.gagnerArgent(j,ennemi.getArgent());
            if(ennemi.getArme() != null){
                j.addChaine("Vous possèdez : ");
                if(joueur.getArme() != null){
                    joueur.getArme().drawArme();
                }
                else {
                    j.addChaine("Aucune arme");
                }
                j.addChaine("");
                j.addChaine("Remplacer par l'arme de " + ennemi.getNom() + " :");
                ennemi.getArme().drawArme();
                j.addChaine(" 1.Oui");
                j.addChaine(" 2.Non");
                j.draw();
                do{
                    action = in.nextInt();
                } while (action < 1 || action >2);
                if (action == 1){
                    joueur.equipeArme(ennemi.getArme());
                }
            }
        }
        else {
            j.addChaine("Vous avez perdu le combat face à " + ennemi.getNom());
            joueur.regen();
        }
        
    }
    
}
