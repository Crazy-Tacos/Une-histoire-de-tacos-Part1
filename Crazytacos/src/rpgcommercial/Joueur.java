package rpgcommercial;

import java.util.Scanner;

public class Joueur implements Controleur{
    
    private Scanner in;
    
    public Joueur(){
        in = new Scanner(System.in);
    }
    
    public void pause(Vue v){
        v.addChaineFin("Appuyer sur Entrée pour continuer");
        v.draw();
        in.nextLine();
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
    
    public int lireChoix(Vue v,int max){
        int choix;
        v.addChaineFin("Votre choix :");
        v.draw();
        do {
            choix = lireEntier();
        } while (choix < 1 || choix > max);
        return choix;
    }
    
    public void tailleFenetre(Vue v){
        v.initEcran();
        v.addChaine("HAUT");
        v.addChaine("");
        v.addChaine("Ajuster la taille de la fenêtre pour que haut soit");
        v.addChaine("en haut de l'écran et le curseur sous 'Appuyer sur Entrée'");
        v.addChaine("en bas de l'écran !");
        pause(v);
    }
    
    public String lireString(){
        String str;
        do{
            str = in.nextLine();
        } while(str.length() == 0);
        return str;
    }
}
