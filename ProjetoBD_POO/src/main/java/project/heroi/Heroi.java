package project.heroi;

import lombok.Data;

@Data

public abstract class Heroi {

    private int id;
    private String nome;
    private final int vidaMax = 150;
    private int vida;
    private double ataque;
    private double defesa;
    private int ouro;
    protected Inventario inventario;
    protected Arma arma;
    private int classe;


    public String returnClasse(int c){
        String g = "Guerreiro";
        String m = "Mago";
        String a = "Arqueiro";
        if(c == 1) {
            return g;
        } else if(c == 2) {
            return m;
        } else if(c == 3) {
            return a;
        }
        return null;
    }

    public boolean usarItem(int id){
        switch (id){
            case 1 ->{
                if(inventario.getPocoesVida()>0){
                    inventario.beberPocao(id);
                    return true;
                }
            }
            case 2 ->{
                if(inventario.getPocoesDano()>0){
                    inventario.beberPocao(id);
                    return true;
                }
            }
            case 3 ->{
                if(inventario.getPocoesDefesa()>0){
                    inventario.beberPocao(id);
                    return true;
                }
            }
            default -> {}
        }
        return false;
    }

    public int getDanoArma(){
        return arma.getDano();
    }
    public String getRaridadeArma(){
        return arma.getRaridade();
    }

    public void addPot(int id){
        inventario.addPot(id);
    }

    public void mostraInfo(){
        System.out.println("ID : " + id);
        System.out.println("Nome : "+ nome);
        System.out.println("Vida: " + vida + "  Dano:" + ataque + "  Defesa:" + defesa);
        System.out.println(classe);
        inventario.mostraInfo();
    }

}
