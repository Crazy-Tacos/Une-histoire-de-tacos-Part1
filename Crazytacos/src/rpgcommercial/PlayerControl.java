package rpgcommercial;

import java.util.Scanner;

public class PlayerControl implements Controlor{
    
    private Scanner in;
    
    public PlayerControl(){
        in = new Scanner(System.in);
    }
    
    public void pause(View v){
        v.addStringEnd("Appuyer sur Entrée pour continuer");
        v.draw();
        in.nextLine();
    }
    
    public int readInteger(){
        int choix = -1;
        String str;
        boolean chiffres = true;
        char c;
        do{
            str = readString();
            for(int i = 0; i<str.length();i++){
                c = str.toCharArray()[i];
                if(c<'0' || c>'9'){
                    chiffres = false;
                }
            }
            if (chiffres){
                choix = Integer.parseInt(str); 
            }
            chiffres = true;
        } while (choix < 0);
        
        return choix;
    }
    
    public int readChose(View v,int max){
        int choix;
        v.addStringEnd("Votre choix :");
        v.draw();
        do {
            choix = readInteger();
        } while (choix < 1 || choix > max);
        return choix;
    }
    
    public void calibrateWindow(View v){
        v.initScreen();
        v.addString("HAUT");
        v.addString("");
        v.addString("Ajuster la taille de la fenêtre pour que haut soit");
        v.addString("en haut de l'écran et le curseur sous 'Appuyer sur Entrée'");
        v.addString("en bas de l'écran !");
        pause(v);
    }
    
    public String readString(View vue){
        
        String str;
        
        vue.addStringEnd("Votre choix :");
        vue.draw();
        
        do{
            str = in.nextLine();
        } while(str.length() == 0);
        return str;
    }
    
    public String readString(){
        
        String str;
        
        do{
            str = in.nextLine();
        } while(str.length() == 0);
        return str;
    }
}
