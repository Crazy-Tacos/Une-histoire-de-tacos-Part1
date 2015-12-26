package crazytacos;


public class Jeu {
    private String[] ecran;
    
    public Jeu(){
        ecran = new String[20];
        initEcran();
    }
    
    public void initEcran(){
        for (int i = 0; i<20; i++){
            ecran[i] = "";
        }
    }
    
    public void draw(){
        for (int i = 0; i<20; i++){
            if (ecran[i].length() != 0){
                System.out.println(ecran[i]);
            }
        }
        System.out.println();
        System.out.println();
        initEcran();
    }
    
    public void addChaine(String[] str){
        for (int i = 0; i<20; i++){
            ecran[i] += str[i];
        }
    }
    
    public void addChaine(String[] str, int decalage){
        for (int i = 0; i<20; i++){
            while(ecran[i].length()<decalage && str[i].length() != 0){
                ecran[i] += " ";
            }
            ecran[i] += str[i];
        }
    }
}
