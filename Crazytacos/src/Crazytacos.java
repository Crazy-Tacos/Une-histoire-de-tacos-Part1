package crazytacos;


public class Crazytacos {
    
    
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        Personnage player = new Cuisinier("Tacos");
        Personnage ennemi = new Personnage("Rat", 1, 11, 50, 20, 10, 10, 10);

        Combat combat = new Combat(player, ennemi);
        combat.doCombat(jeu);
        
        player.drawPersonnage(jeu, 0);
        jeu.draw();
        
        ennemi = new Personnage("Planche", 2, 15, 0, 10, 12, 12, 12);
        ennemi.equipeArme(new Arme("Clou", 2,10,5,2));
        
        combat = new Combat(player, ennemi);
        combat.doCombat(jeu);
        
        player.drawPersonnage(jeu, 0);
        jeu.draw();
    }
    
}
