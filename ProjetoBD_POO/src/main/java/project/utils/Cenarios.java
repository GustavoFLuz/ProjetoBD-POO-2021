package project.utils;

import project.heroi.Heroi;
import project.heroi.Personagem;
import project.inimigos.InimigoAcoes;

import java.util.Scanner;

public class Cenarios {

    private static Scanner sc = new Scanner(System.in);

    public static void clearLines(){
        for(int i=0;i<20;i++){
            System.out.println("");
        }
    }

    public static void menu(){
        boolean flag = true;
        while(flag) {
            clearLines();
            System.out.println("---Menu Principal---");
            System.out.println("");
            System.out.println("1 - Jogar");
            System.out.println("2 - Editar Personagens");
            System.out.println("3 - Editar Inimigos");
            System.out.println("4 - Sair");
            System.out.println("");
            System.out.println("");
            int op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 -> jogar();
                case 2 -> personagem();
                case 3 -> inimigo();
                case 4 -> flag = false;
                default -> {
                    clearLines();
                    System.out.println("Opção Inválida");
                    System.out.println("");
                }
            }
        }
    }

    public static void personagem(){
        boolean flag = true;
        while (flag) {
            clearLines();
            System.out.println("---Personagens---");
            System.out.println("");
            System.out.println("---Selecione sua opção");
            System.out.println("");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Editar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Voltar");

            int op = sc.nextInt();

            switch (op) {
                case 1 -> Personagem.add();
                case 2 -> Personagem.edit();
                case 3 -> Personagem.del();
                case 4 -> flag = false;
                default -> {
                }
            }
        }
    }

    public static void inimigo(){
        boolean flag = true;
        while(flag) {
            clearLines();
            System.out.println("---Inimigos---");
            System.out.println("");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Remover");
            System.out.println("3 - Editar");
            System.out.println("4 - Sair");
            System.out.println("");
            System.out.println("");
            int op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 -> InimigoAcoes.add();
                case 2 -> InimigoAcoes.del();
                case 3 -> InimigoAcoes.edit();
                case 4 -> flag = false;
                default -> {
                }
            }
        }
    }

    public static void jogar(){
        clearLines();
        System.out.println("---Selecione seu herói");
        Heroi heroi = Personagem.selectPersonagem();
        if(heroi != null){
            Jogar.setHeroi(heroi);
            Jogar.jogar();
        }
        else {
            System.out.println("Nenhum herói disponivel");
            return;
        }
    }

}
