
package rpgcommercial;

public class CombatLimite extends Combat{
    private int nbTour;
    
    public CombatLimite(Joueur control, Vue j, Personnage joueur, Personnage ennemi, int nbTour) {
        super(control, j, joueur, ennemi);
        this.nbTour=nbTour;
    }
    
}
