
package rpgcommercial;

public class FightLimit extends Fight{
    private int nbTour;
    
    public FightLimit(PlayerControl control, View j, Caracter joueur, Caracter ennemi, int nbTour) {
        super(control, j, joueur, ennemi);
        this.nbTour=nbTour;
    }
    
    public int getNbTour(){
        return nbTour;
    }
    
    public void drawFight(){
        j.addString("--- COMBAT LIMITE ---");
        j.addString("Nombre de Tour restants : " + (nbTour - 1));
        j.addString(joueur.getName() + " VS " + ennemi.getName());        
        int i = j.getNextLigne();
        joueur.drawCaracterFight(j);
        ennemi.drawCaracterFight(j,i, 30);
    }
    
    public boolean doFight(){        
        boolean tourJ;
        int degats;
        int nbchoix;
        this.drawFight();
        j.addString("");
        j.addString("Le plus intelligent et le plus agile commence : ");
        
        if ((joueur.getDextTotale() + joueur.getIntellTotale()) >= (ennemi.getDextTotale() + ennemi.getIntellTotale())){
            tourJ = true;
            j.concatLastLigne(joueur.getName());
        }
        else {
            tourJ = false;
            j.concatLastLigne(ennemi.getName());
        }
        j.drawPause(control);
        
        while(joueur.getVie() > 0 && ennemi.getVie() > 0 && nbTour > 0){

            if (tourJ){
                //le joueur fait une action
                drawFight();
                j.addString("");
                nbchoix = joueur.drawActions(j);
                degats =joueur.attack(j,control.readChose(j,nbchoix));
                if (degats >= 0){
                    ennemi.takeDamages(j, degats);
                }
                else{
                    joueur.useHeal(j, -degats);
                }
                nbTour--;
            }
            else{
                // L'ennemi attaque avec son IA 
                degats = ennemi.attack(j,iaControl.choixAttaque(ennemi, joueur));
                if (degats >= 0){
                    joueur.takeDamages(j, degats);
                }
                else{
                    ennemi.useHeal(j, -degats);
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

