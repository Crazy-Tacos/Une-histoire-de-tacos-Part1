package rpgcommercial;


public class View {
    private String[] ecran;
    
    public View(){
        ecran = new String[20];
        initScreen();
    }
    
    public void initScreen(){
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
        initScreen();
    }
    
    public void drawCalibrate(PlayerControl control){
        control.calibrateWindow(this);
    }
    
    public void drawPause(PlayerControl control){
        control.pause(this);
    }
    
    public int drawChoix(PlayerControl control, int max){
        return control.readChose(this,max);
    }
    
    public void addString(String str, int ligne, int decalage){
        if(ligne <20){
            while(ecran[ligne].length()<decalage){
                ecran[ligne] += " ";
            }
            ecran[ligne] += str;
        }
    }
    
    public void addString(String str){
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
    
    public void addStringEnd(String str){
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
