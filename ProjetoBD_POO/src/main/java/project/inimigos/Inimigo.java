package project.inimigos;

import lombok.Data;

@Data
public class Inimigo {

    private int id;
    private String nome;
    private int vida;
    private int dano;
    private int defesa;
    private int recompensa_min;
    private int recompensa_max;

    public Inimigo(String nome, int vida, int dano, int defesa, int recompensa_min, int recompensa_max) {
        this.nome = nome;
        this.vida = vida;
        this.dano = dano;
        this.defesa = defesa;
        this.recompensa_min = recompensa_min;
        this.recompensa_max = recompensa_max;
    }
    public Inimigo(int id, int vida, int dano, int defesa, int recompensa_min, int recompensa_max) {
        this.id = id;
        this.vida = vida;
        this.dano = dano;
        this.defesa = defesa;
        this.recompensa_min = recompensa_min;
        this.recompensa_max = recompensa_max;
    }

    public void mostraInfo(){
        System.out.println("             " + nome);
        System.out.println("             " + vida + " HP");
        System.out.println("             " + dano + " de dano/" + defesa + " de defesa");
        System.out.println("");
    }
}
