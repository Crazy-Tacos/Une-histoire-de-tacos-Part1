package rpgcommercial;

/**
 *
 * @author Lilian Varrel
 */
public class Ia implements Controleur{
    
    public Ia (){
        
    }
    
    public int choixAttaque(Personnage monstre, Personnage player){//Gerer les consos id(ennemi.hp <= degat, taper!!! sinon si monstre.hp < blaba -> heal
        Inventaire inventaire = monstre.getInventaire();
        Arme armeSecondaire = inventaire.getArmeSecondaire();
        Arme armePrincipale = inventaire.getArmePrincipale();
        Consommable conso = inventaire.getConsommable();
        
        if (armeSecondaire == null)
        {
            return 1;
        }
        else if(armeSecondaire != null)
        {
            if(armePrincipale.getDmin() + armePrincipale.getDmax() > armeSecondaire.getDmin() + armeSecondaire.getDmax()){
                return 1;
            }
            else{
                return 2;
            }
        }
        
        return 1;
    }       
}
