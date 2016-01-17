
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
        Vue vue =new Vue();
        
        vue.addChaine("--- COMBAT LIMITE ---");
        vue.addChaine("Nombre de Tour : " + nbTour);
        vue.addChaine(joueur.getNom() + " VS " + ennemi.getNom());        
        int i = vue.getNextLigne();
        joueur.drawPersonnageCombat(vue);
        ennemi.drawPersonnageCombat(vue,i, 30);
    }
    
    public boolean doCombat(){
        Vue vue =new Vue();
        
        boolean tourJ;
        int degats;
        int nbchoix;
        this.drawCombat();
        vue.addChaine("");
        vue.addChaine("Le plus intelligent et le plus agile commence : Combat limite - - - ");
        
        if ((joueur.getDextTotale() + joueur.getIntellTotale()) >= (ennemi.getDextTotale() + ennemi.getIntellTotale())){
            tourJ = true;
            vue.concatLastLigne(joueur.getNom());
        }
        else {
            tourJ = false;
            vue.concatLastLigne(ennemi.getNom());
        }
        vue.drawPause(control);
        
        while(joueur.getVie() != 0 && ennemi.getVie() !=0 && nbTour != 0){

            if (tourJ){
                //le joueur fait une action
                drawCombat();
                vue.addChaine("");
                nbchoix = joueur.drawActions(vue);
                degats =joueur.attaquer(vue,control.lireChoix(j,nbchoix));
                ennemi.infligerDegats(vue, degats);
            }
            else{
                // L'ennemi attaque avec son IA 
                degats = ennemi.attaquer(vue,iaControl.choixAttaque(ennemi, joueur));
                joueur.infligerDegats(vue, degats);
                control.pause(vue);
            }
            tourJ = !tourJ;
            nbTour--;
        }
        
        if (joueur.getVie()>0 || nbTour == 0){
            return true;
        }
        else {
            return false;
        }    
    }
}

