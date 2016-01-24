package rpgcommercial;


public class Fight {
    protected Caracter joueur;
    protected Caracter ennemi;
    protected View j;
    protected PlayerControl control;
    protected Ia iaControl;
    
    public Fight (PlayerControl control, View j,Caracter joueur, Caracter ennemi) {
        this.joueur = joueur;
        this.ennemi = ennemi;
        this.j = j;
        this.control = control;
        this.iaControl=new Ia();
    }
    
    public Caracter getJoueur(){
        return joueur;
    }
    
    public Caracter getEnnemi(){
        return ennemi;
    }
    
    public void drawFight(){
        j.addString("--- COMBAT ---");
        j.addString(joueur.getName() + " VS " + ennemi.getName());
        int i = j.getNextLigne();
        joueur.drawCaracterFight(j);
        ennemi.drawCaracterFight(j,i, 30);
    }
    
    public boolean doFight(){
        boolean tourJ;
        int degats;
        int nbchoix;
        drawFight();
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
        
        while(joueur.getVie() != 0 && ennemi.getVie() !=0){

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
                    System.out.println("degats : " + degats);
                    System.out.println("degats : " + degats);
                    joueur.useHeal(j, -degats);
                }
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
        
        if (joueur.getVie()>0){
            return true;
        }
        else {
            return false;
        }
                   
        
    }
    
    public void winStuff(){
        joueur.winXP(j,ennemi.getXP());
        joueur.winMoney(j,ennemi.getMoney());
        j.drawPause(control);
        if(!"Mains nues".equals(ennemi.getInventory().getMainWeapon().nom)){
            winMainWeapon(); 
        }
        if(ennemi.getInventory().getSecondWeapon() != null){
            winSecondWeapon();
        }
        if(ennemi.getInventory().getArmor() != null){
            winArmor();
        }
    }
    
    public void winMainWeapon(){
        int choix;
        j.addString("Vous possèdez : ");
        if(joueur.getInventory().getMainWeapon() != null){
            joueur.getInventory().getMainWeapon().drawWeapon(j);
        }
        else {
            j.addString("Aucune arme principale");
        }
        j.addString("");
        j.addString("Remplacer par l'arme de " + ennemi.getName() + " :");
        ennemi.getInventory().getMainWeapon().drawWeapon(j);
        j.addString("");
        j.addString(" 1.Oui");
        j.addString(" 2.Non");
        choix = control.readChose(j,2);
        if (choix == 1){
            joueur.getInventory().setMainWeapon((ennemi.getInventory().getMainWeapon()));
        }
    }
    
    public void winSecondWeapon(){
        int choix;
        j.addString("Vous possèdez : ");
        if(joueur.getInventory().getSecondWeapon() != null){
            joueur.getInventory().getSecondWeapon().drawWeapon(j);
        }
        else {
            j.addString("Aucune arme secondaire");
        }
        j.addString("");
        j.addString("Remplacer par l'arme de " + ennemi.getName() + " :");
        ennemi.getInventory().getSecondWeapon().drawWeapon(j);
        j.addString("");
        j.addString(" 1.Oui");
        j.addString(" 2.Non");
        choix = control.readChose(j,2);
        if (choix == 1){
            joueur.getInventory().setSecondWeapon((ennemi.getInventory().getSecondWeapon()));
        }
    }
    
    public void winArmor(){
        int choix;
        j.addString("Vous possèdez : ");
        if(joueur.getInventory().getArmor() != null){
            joueur.getInventory().getArmor().drawArmor(j);
        }
        else {
            j.addString("Aucune armure");
        }
        j.addString("");
        j.addString("Remplacer par l'armure de " + ennemi.getName() + " :");
        ennemi.getInventory().getArmor().drawArmor(j);
        j.addString("");
        j.addString(" 1.Oui");
        j.addString(" 2.Non");
        choix = control.readChose(j,2);
        if (choix == 1){
            joueur.getInventory().setArmor((ennemi.getInventory().getArmor()));
            joueur.heal(joueur.getInventory().getArmor().getVitalite());
        }
    }
}
