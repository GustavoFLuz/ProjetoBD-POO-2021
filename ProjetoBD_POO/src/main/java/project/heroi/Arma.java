package project.heroi;

import lombok.Data;

import java.util.Random;

@Data
public class Arma {

    private int dano;
    private String raridade;

    public Arma(int dano, String raridade){
        this.raridade = raridade;
        this.dano = dano;
    }

    public static String defRaridade(int aux){
        String raridade = null;
        switch (aux){
            case 1-> raridade = "comum";
            case 2-> raridade = "incomum";
            case 3-> raridade = "rara";
            case 4-> raridade = "épica";
            case 5-> raridade = "lendária";
            default -> {}
        }
        return raridade;
    }

    public static int defPrecoArma(int dano){
        Random random = new Random();
        return dano*random.nextInt(3,6);
    }

    public static int danoArma(int raridade){
        Random random = new Random();
        return raridade* random.nextInt(12,16);
    }

}
