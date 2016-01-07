package crazytacos;


public class Item {
    private String nom;
    private String classe;
    
    public Item(String nom, String classe){
        this.nom = nom;
        this.classe = classe;
    }
    public Item(String nom){
        this.nom = nom;
        this.classe = "";
    }
    
    public String getNom(){
        return nom;
    }
}
