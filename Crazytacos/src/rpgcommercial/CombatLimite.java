
package rpgcommercial;

public class CombatLimite extends Combat{
    private int nbTour;
    
    public CombatLimite(Joueur control, Vue j, Personnage joueur, Personnage ennemi, int nbTour) {
        super(control, j, joueur, ennemi);
        this.nbTour=nbTour;
    }
    
    public int getNbTour(){
        return nbTour;
    }
    
    public void drawCombat(){
        j.addChaine("--- COMBAT LIMITE ---");
        j.addChaine("Nombre de Tour restants : " + (nbTour - 1));
        j.addChaine(joueur.getNom() + " VS " + ennemi.getNom());        
        int i = j.getNextLigne();
        joueur.drawPersonnageCombat(j);
        ennemi.drawPersonnageCombat(j,i, 30);
    }
    
    public boolean doCombat(){        
        boolean tourJ;
        int degats;
        int nbchoix;
        this.drawCombat();
        j.addChaine("");
        j.addChaine("Le plus intelligent et le plus agile commence : ");
        
        if ((joueur.getDextTotale() + joueur.getIntellTotale()) >= (ennemi.getDextTotale() + ennemi.getIntellTotale())){
            tourJ = true;
            j.concatLastLigne(joueur.getNom());
        }
        else {
            tourJ = false;
            j.concatLastLigne(ennemi.getNom());
        }
        j.drawPause(control);
        
        while(joueur.getVie() > 0 && ennemi.getVie() > 0 && nbTour > 0){

            if (tourJ){
                //le joueur fait une action
                drawCombat();
                j.addChaine("");
                nbchoix = joueur.drawActions(j);
                degats =joueur.attaquer(j,control.lireChoix(j,nbchoix));
                if (degats >= 0){
                    ennemi.infligerDegats(j, degats);
                }
                else{
                    joueur.utiliserSoin(j, -degats);
                }
                nbTour--;
            }
            else{
                // L'ennemi attaque avec son IA 
                degats = ennemi.attaquer(j,iaControl.choixAttaque(ennemi, joueur));
                if (degats >= 0){
                    joueur.infligerDegats(j, degats);
                }
                else{
                    ennemi.utiliserSoin(j, -degats);
                }
                control.pause(j);
            }
            tourJ = !tourJ;
            
        }
        
        if (joueur.getVie()>0 && nbTour > 0){
            return true;
        }
        else {
            return false;
        }    
    }
}

