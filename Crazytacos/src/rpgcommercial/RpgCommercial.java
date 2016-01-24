package rpgcommercial;


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
	        vue.addString("Charger Partie : tapez 2");
	        vue.addString("Quitter : tapez 3");
	                        
	        switch(jeu.getPlayer().readChose(vue, 3))
	        {
	                case 1 :    jeu.newGame(vue); break;
	                case 2 :    jeu.loadGame(vue);  break;
	                case 3 :    arret = true; break;
	                default : System.out.println("\nFatal Error"); return ;
	        }
        }
        
    }
}