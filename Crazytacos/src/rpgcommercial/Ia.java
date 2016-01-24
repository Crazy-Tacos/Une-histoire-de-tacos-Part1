package rpgcommercial;

public class Ia implements Controlor{
    
    public Ia (){
        
    }
    
    public int choixAttaque(Caracter monstre, Caracter player){//Gerer les consos id(ennemi.hp <= degat, taper!!! sinon si monstre.hp < blaba -> heal
        Inventory inventaire = monstre.getInventory();
        Weapon armeSecondaire = inventaire.getSecondWeapon();
        Weapon armePrincipale = inventaire.getMainWeapon();
        Spell conso = inventaire.getSpell();
        
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
