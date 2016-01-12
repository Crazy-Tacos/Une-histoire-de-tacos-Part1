package rpgcommercial;

import java.util.Scanner;

public class Joueur implements Controleur{
    
    private Scanner in;
    
    public Joueur(){
        in = new Scanner(System.in);
    }
    
    public int lireEntier(){
        int choix = -1;
        String str;
        boolean chiffres = true;
        char c;
        do{
            str = lireString();
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
    
    public int lireChoix(int max){
        int choix;
        do {
            choix = lireEntier();
        } while (choix < 1 || choix > max);
        return choix;
    }
    
    public String lireString(){
        return in.nextLine();
    }
}
