package crazytacos;

import java.util.Scanner;

public class Combat {
    private Personnage joueur;
    private Personnage ennemi;
    
    public Combat (Personnage joueur, Personnage ennemi) {
        this.joueur = joueur;
        this.ennemi = ennemi;
    }
    
    public Personnage getJoueur(){
        return joueur;
    }
    
    public Personnage getEnnemi(){
        return ennemi;
    }
    
    public void drawCombat(Jeu j, int ligne){
        String[] str = new String[20];
        for (int i = 0; i<20; i++){
            str[i] = "";
        }
        if (ligne + 1 <20){
            str[ligne] = "--- COMBAT ---";
            str[ligne + 1] = joueur.getNom() + " VS " + ennemi.getNom();
        }
        j.addChaine(str);
        j.addChaine(joueur.stringPersonnage(2));
        j.addChaine(ennemi.stringPersonnage(2), 30);
    }
    
    public void doCombat(Jeu j){
        
        drawCombat(j,0);
        j.draw();
        boolean tourJ;
        int action;
        Scanner in = new Scanner(System.in);
        System.out.print("Le plus intelligent et le plus agile commence : ");
        if ((joueur.getDext() + joueur.getIntell()) >= (ennemi.getDext() + ennemi.getIntell())){
            tourJ = true;
            System.out.println(joueur.getNom());
        }
        else {
            tourJ = false;
            System.out.println(ennemi.getNom());
        }
        
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
            drawCombat(j,0);
            j.draw();
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
}
