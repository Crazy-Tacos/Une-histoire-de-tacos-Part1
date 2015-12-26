package crazytacos;


public class Crazytacos {
    
    
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        Personnage player = new Boulanger("Tacos");
        Personnage ennemi = new Personnage("Rat", 1, 10, 50, 100, 10, 10, 10, 2);
        
        Combat rat = new Combat(player, ennemi);
        rat.drawCombat(jeu, 0);
        jeu.draw();
        
    }
    
}
