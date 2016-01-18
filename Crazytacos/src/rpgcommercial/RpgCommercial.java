package rpgcommercial;


public class RpgCommercial {
    
    
    public static void main(String[] args){
       
        Jeu jeu = new Jeu();
        Vue vue = new Vue(); 
                
        boolean arret = false;
        
        jeu.getJoueur().tailleFenetre(vue);
                
        while (!arret)
        {
	        vue.addChaine("-- Panique au Centre Commecial --");
	        vue.addChaine("Nouvelle Partie : tapez 1");
	        vue.addChaine("Charger Partie : tapez 2");
	        vue.addChaine("Quitter : tapez 3");
	                        
	        switch(jeu.getJoueur().lireChoix(vue, 3))
	        {
	                case 1 :    jeu.nouvellePartie(vue); break;
	                case 2 :    jeu.chargerPartie(vue);  break;
	                case 3 :    arret = true; break;
	                default : System.out.println("\nFatal Error"); return ;
	        }
        }
        
    }
}