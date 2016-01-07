package crazytacos;


public class Jeu {
    private String[] ecran;
    
    public Jeu(){
        ecran = new String[20];
        initEcran();
    }
    
    public void initEcran(){
        for (int i = 0; i<20; i++){
            ecran[i] = null;
        }
    }
    
    public void draw(){
        for (int i = 0; i<20; i++){
            if (ecran[i] != null){
                System.out.println(ecran[i]);
            }
            else {
                System.out.println();
            }
        }
        initEcran();
    }
    
    public void addChaine(String str, int ligne, int decalage){
        if(ligne <20){
            while(ecran[ligne].length()<decalage){
                ecran[ligne] += " ";
            }
            ecran[ligne] += str;
        }
    }
    
    public void addChaine(String str){
        int i = getNextLigne();
        if(i <20){
            ecran[i] = str;
        }
    }
    
    public int getNextLigne(){
        int i = 0;
        while(ecran[i] != null && i<20){
            i++;
        }
        return i;
    }
}
