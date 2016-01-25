package rpgcommercial;

public class Score {
    private String nom;
    private String classe;
    private int argent;
    
    public Score(String nom, String classe, int argent){
        this.nom =nom;
        this.classe = classe;
        this.argent = argent;
    }
    
    public int getMoney(){
        return argent;
    }
    
    public void drawScoreConcat(View vue){
        vue.concatLastLigne(nom + " le " + classe+ " : "+ argent+" euros");
    }
}
