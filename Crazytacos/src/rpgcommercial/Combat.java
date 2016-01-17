package rpgcommercial;


public class Combat {
    private Personnage joueur;
    private Personnage ennemi;
    private Vue j;
    private Joueur control;
    private Ia iaControl;
    
    public Combat (Joueur control, Vue j,Personnage joueur, Personnage ennemi) {
        this.joueur = joueur;
        this.ennemi = ennemi;
        this.j = j;
        this.control = control;
        this.iaControl=new Ia();
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
        joueur.drawPersonnageCombat(j);
        ennemi.drawPersonnageCombat(j,i, 30);
    }
    
    public boolean doCombat(){
        boolean tourJ;
        int degats;
        int nbchoix;
        drawCombat();
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
        
        while(joueur.getVie() != 0 && ennemi.getVie() !=0){

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
        
        if (joueur.getVie()>0){
            return true;
        }
        else {
            return false;
        }
                   
        
    }
    
    public void gagnerRecompenses(){
        joueur.gagnerXP(j,ennemi.getXP());
        joueur.gagnerArgent(j,ennemi.getArgent());
        j.drawPause(control);
        if(!"Mains nues".equals(ennemi.getInventaire().getArmePrincipale().getNom())){
            gagnerArmePrincipale(); 
        }
        if(ennemi.getInventaire().getArmeSecondaire() != null){
            gagnerArmeSecondaire();
        }
        if(ennemi.getInventaire().getArmure() != null){
            gagnerArmure();
        }
    }
    
    public void gagnerArmePrincipale(){
        int choix;
        j.addChaine("Vous possèdez : ");
        if(joueur.getInventaire().getArmePrincipale() != null){
            joueur.getInventaire().getArmePrincipale().drawArme(j);
        }
        else {
            j.addChaine("Aucune arme principale");
        }
        j.addChaine("");
        j.addChaine("Remplacer par l'arme de " + ennemi.getNom() + " :");
        ennemi.getInventaire().getArmePrincipale().drawArme(j);
        j.addChaine("");
        j.addChaine(" 1.Oui");
        j.addChaine(" 2.Non");
        choix = control.lireChoix(j,2);
        if (choix == 1){
            joueur.getInventaire().setArmePrincipale((ennemi.getInventaire().getArmePrincipale()));
        }
    }
    
    public void gagnerArmeSecondaire(){
        int choix;
        j.addChaine("Vous possèdez : ");
        if(joueur.getInventaire().getArmeSecondaire() != null){
            joueur.getInventaire().getArmeSecondaire().drawArme(j);
        }
        else {
            j.addChaine("Aucune arme secondaire");
        }
        j.addChaine("");
        j.addChaine("Remplacer par l'arme de " + ennemi.getNom() + " :");
        ennemi.getInventaire().getArmeSecondaire().drawArme(j);
        j.addChaine("");
        j.addChaine(" 1.Oui");
        j.addChaine(" 2.Non");
        choix = control.lireChoix(j,2);
        if (choix == 1){
            joueur.getInventaire().setArmeSecondaire((ennemi.getInventaire().getArmeSecondaire()));
        }
    }
    
    public void gagnerArmure(){
        int choix;
        j.addChaine("Vous possèdez : ");
        if(joueur.getInventaire().getArmure() != null){
            joueur.getInventaire().getArmure().drawArmure(j);
        }
        else {
            j.addChaine("Aucune armure");
        }
        j.addChaine("");
        j.addChaine("Remplacer par l'armure de " + ennemi.getNom() + " :");
        ennemi.getInventaire().getArmure().drawArmure(j);
        j.addChaine("");
        j.addChaine(" 1.Oui");
        j.addChaine(" 2.Non");
        choix = control.lireChoix(j,2);
        if (choix == 1){
            joueur.getInventaire().setArmure((ennemi.getInventaire().getArmure()));
            joueur.soigner(joueur.getInventaire().getArmure().getVitalite());
        }
    }
}
