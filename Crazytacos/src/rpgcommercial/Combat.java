package rpgcommercial;


/*
public int attaquer(Vue j, int choix){
        int max, min;
        Random rand = new Random();
        if (choix == 1){
            if(inventaire.getArmePrincipale() == null){
                choix++;
            }
            else {
                max = getDmaxArme(inventaire.getArmePrincipale());
                min = getDminArme(inventaire.getArmePrincipale());
                j.addChaine(nom + " utilise l'arme " + inventaire.getArmePrincipale().getNom());
                return rand.nextInt(max - min + 1) + min;
            }
        }
        if (choix == 2){
            if(inventaire.getArmeSecondaire() == null){
                choix++;
            }
            else {
                max = getDmaxArme(inventaire.getArmeSecondaire());
                min = getDminArme(inventaire.getArmeSecondaire());
                j.addChaine(nom + " utilise l'arme " + inventaire.getArmeSecondaire().getNom());
                return rand.nextInt(max - min + 1) + min;
            }
        }
        
        return 0;
    }
*/

public class Combat {
    private Personnage joueur;
    private Personnage ennemi;
    private Vue j;
    private Joueur control;
    
    public Combat (Joueur control, Vue j,Personnage joueur, Personnage ennemi) {
        this.joueur = joueur;
        this.ennemi = ennemi;
        this.j = j;
        this.control = control;
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
        boolean tourJ;
        int degats;
        int nbchoix;
        drawCombat();
        j.addChaine("Le plus intelligent et le plus agile commence : ");
        if ((joueur.getDext() + joueur.getIntell()) >= (ennemi.getDext() + ennemi.getIntell())){
            tourJ = true;
            j.concatLasLigne(joueur.getNom());
        }
        else {
            tourJ = false;
            j.concatLasLigne(ennemi.getNom());
        }
        
        while(joueur.getVie() != 0 && ennemi.getVie() !=0){

            if (tourJ){
                //le joueur fait une action
                nbchoix = joueur.drawActions(j);
                j.draw();
                degats =joueur.attaquer(j,control.lireChoix(nbchoix));
                ennemi.infligerDegats(j, degats);
            }
            else{
                // L'ennemi auto attaque (super IA !!!)
                degats = ennemi.attaquer(j,1);
                joueur.infligerDegats(j, degats);
                j.draw();
                control.pause();
            }
            
            if(!tourJ) {
                drawCombat();
            }
            else
            {
                
            }
            tourJ = !tourJ;
        }
        
        if (joueur.getVie()>0){
            j.addChaine("Vous avez battu " + ennemi.getNom() + " !");
            gagnerRecompenses();
        }
        else {
            j.addChaine("Vous avez perdu le combat face à " + ennemi.getNom());
            joueur.regen();
        }
                   
        
    }
    
    public void gagnerRecompenses(){
        joueur.gagnerXP(j,ennemi.getXP());
        joueur.gagnerArgent(j,ennemi.getArgent());
        if(ennemi.getInventaire().getArmePrincipale().getNom() != "Mains nues"){
            gagnerArmePrincipale(); 
        }
        if(ennemi.getInventaire().getArmeSecondaire() != null){
            gagnerArmeSecondaire();
        }
        
    }
    
    public void gagnerArmePrincipale(){
        int choix;
        j.addChaine("Vous possèdez : ");
        if(joueur.getInventaire().getArmePrincipale() != null){
            joueur.getInventaire().getArmePrincipale().drawArme();
        }
        else {
            j.addChaine("Aucune arme principale");
        }
        j.addChaine("");
        j.addChaine("Remplacer par l'arme de " + ennemi.getNom() + " :");
        ennemi.getInventaire().getArmePrincipale().drawArme();
        j.addChaine(" 1.Oui");
        j.addChaine(" 2.Non");
        j.draw();
        choix = control.lireChoix(2);
        if (choix == 1){
            joueur.getInventaire().setArmePrincipale((ennemi.getInventaire().getArmePrincipale()));
        }
    }
    
    public void gagnerArmeSecondaire(){
        int choix;
        j.addChaine("Vous possèdez : ");
        if(joueur.getInventaire().getArmeSecondaire() != null){
            joueur.getInventaire().getArmeSecondaire().drawArme();
        }
        else {
            j.addChaine("Aucune arme secondaire");
        }
        j.addChaine("");
        j.addChaine("Remplacer par l'arme de " + ennemi.getNom() + " :");
        ennemi.getInventaire().getArmeSecondaire().drawArme();
        j.addChaine(" 1.Oui");
        j.addChaine(" 2.Non");
        j.draw();
        choix = control.lireChoix(2);
        if (choix == 1){
            joueur.getInventaire().setArmeSecondaire((ennemi.getInventaire().getArmeSecondaire()));
        }
    }
    
}
