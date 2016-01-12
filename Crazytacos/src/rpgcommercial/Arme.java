package rpgcommercial;

public class Arme extends Item {
    private int dmin;
    private int dmax;
    private int ratio;
    private int carac;
    
    public Arme(String nom, int dmin, int dmax, int ratio, int carac){
        super(nom);
        this.dmin = dmin;
        this.dmax = dmax;
        this.ratio = ratio;
        this.carac = carac;
    }
    
    public int getDmin() {
        return dmin;
    }
    public int getDmax() {
        return dmax;
    }
    public int getRatio() {
        return ratio;
    }
    public int getCarac() {
        return carac;
    }
    public void drawArme(){
        System.out.println(getNom());
        System.out.print("Dégats : "+ dmin + "-" + dmax);
        String car = "";
        if (carac == 1){ // Vitalite
            car = "% de la VITALITE";
        }
        else if (carac == 2){ // Force
            car = "% de la FORCE";
        }
        else if (carac == 3){ // Dexterite
            car = "% de la DEXTERITE";
        }
        else if (carac == 4){ // Intelligence
            car = "% de l'INTELLIGENCE";
        }
        System.out.println(" + "+ ratio + car);
    }
}
