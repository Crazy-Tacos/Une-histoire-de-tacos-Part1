package crazytacos;

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
}
