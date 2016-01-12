package rpgcommercial;

public class Inventaire {
    private Arme armePrincipale;
    private Arme armeSecondaire;
    private Armure armure;
    private Consommable consommable;
    
    public Inventaire(){
        this.armePrincipale =null;
        this.armeSecondaire = null;
        this.armure = null;
        this.consommable = null;
    }
    
    public void drawInventaire(Vue v){
        System.out.println("ooo");
    }
    
    public void drawInventaire(Vue v, int ligne, int decalage){
        System.out.println("ooo");
    }

    public void drawInventaireCombat(Vue v){
        System.out.println("ooo");
    }

}
