package rpgcommercial;

public class Ia implements Controleur{
    
    public Ia (){
        
    }
    
    public int choixAttaque(Personnage monstre, Personnage player){//Gerer les consos id(ennemi.hp <= degat, taper!!! sinon si monstre.hp < blaba -> heal
        Inventaire inventaire = monstre.getInventaire();
        Arme armeSecondaire = inventaire.getArmeSecondaire();
        Arme armePrincipale = inventaire.getArmePrincipale();
        Competence conso = inventaire.getCompetence();
        
        if (armeSecondaire == null)
        {
            return 1;
        }
        else if(armeSecondaire != null)
        {
            if(armePrincipale.dmin + armePrincipale.dmax > armeSecondaire.dmin + armeSecondaire.dmax){
                return 1;
            }
            else{
                return 2;
            }
        }
        
        return 1;
    }       
}
