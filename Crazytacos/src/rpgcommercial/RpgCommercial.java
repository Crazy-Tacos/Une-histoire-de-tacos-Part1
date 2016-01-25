package rpgcommercial;

import java.io.File;


public class RpgCommercial {
    
    
    public static void main(String[] args){
       
        Game jeu = new Game();
        View vue = new View();
                
        boolean arret = false;
        
        jeu.getPlayer().calibrateWindow(vue);
                
        while (!arret)
        {      
            vue.addString("-- Panique au Centre Commecial --");
            vue.addString("Nouvelle Partie : tapez 1");
            if (new File("sauvegarde.txt").exists()){
                vue.addString("Charger Partie : tapez 2");
            }
            if (new File("score.txt").exists()){
                vue.addString("Voir scores : tapez 3");
            }
            vue.addString("Quitter : tapez 4");

            switch(jeu.getPlayer().readChose(vue, 4))
            {
                    case 1 :    jeu.newGame(vue); break;
                    case 2 :    if (new File("sauvegarde.txt").exists()){jeu.loadGame(vue);}  break;
                    case 3 :    if (new File("score.txt").exists()){
                        jeu.loadScore(vue);
                        jeu.getPlayer().pause(vue);
                    }  break; 
                    case 4 :    arret = true; break;
                    default : System.out.println("\nFatal Error"); return ;
            }
        }
        
    }
}