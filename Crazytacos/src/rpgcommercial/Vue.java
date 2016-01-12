package rpgcommercial;


public class Vue {
    private String[] ecran;
    
    public Vue(){
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
    
    public void drawCalibrate(Joueur control){
        control.tailleFenetre(this);
    }
    
    public void drawPause(Joueur control){
        control.pause(this);
    }
    
    public void drawChoix(Joueur control, int max){
        control.lireChoix(this,max);
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
    
    public void concatLastLigne(String str){
        int i = getNextLigne() - 1;
        if(i <20 && i >=0){
            ecran[i] += str;
        }
    }
    
    public void addChaineFin(String str){
        int i = 19;
        while(ecran[i] != null && i>0){
            i--;
        }
        for (int j= i; j<19; j++){
            ecran[j] = ecran[j+1];
        }
        ecran[19]=str;
    }
    
    public int getNextLigne(){
        int i = 0;
        while(ecran[i] != null && i<20){
            i++;
        }
        return i;
    }
}
