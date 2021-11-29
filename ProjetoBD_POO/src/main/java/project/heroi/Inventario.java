package project.heroi;

import lombok.Data;

@Data
public class Inventario {

    private int id_heroi;
    private int pocoesVida;
    private int pocoesDano;
    private int pocoesDefesa;

    public Inventario(){
        pocoesVida = 5;
        pocoesDano = 3;
        pocoesDefesa = 3;
    }

    public Inventario(int id, int p1, int p2, int p3){
        id_heroi = id;
        pocoesVida = p1;
        pocoesDano = p2;
        pocoesDefesa = p3;
    }

    public int getId(){ return id_heroi;}

    public void mostraInfo(){
        System.out.println(pocoesVida + " poções de vida");
        System.out.println(pocoesDano + " poções de dano");
        System.out.println(pocoesDefesa + " poções de defesa");

    }

    public void beberPocao (int id) {
        switch (id){
            case 1->{pocoesVida--;}
            case 2->{pocoesDano--;}
            case 3->{pocoesDefesa--;}
            default -> {}
        }
    }

    public void addPot(int id){
        switch (id){
            case 1->pocoesVida++;
            case 2->pocoesDano++;
            case 3->pocoesDefesa++;
            default -> {}
        }
    }

}
