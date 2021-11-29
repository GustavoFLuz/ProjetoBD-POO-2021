package project.inimigos;

import project.utils.Cenarios;

import java.util.ArrayList;
import java.util.Scanner;

public class InimigoAcoes implements project.utils.CrudBasics{

    public static Inimigo selectInimigo(){

        ArrayList<Inimigo> inimigos = InimigoBD.getInimigos();
        for(int i=0; i < inimigos.size();i++) {
            int aux = i+1;
            System.out.println(aux + " - " + inimigos.get(i).getNome() + " (" + inimigos.get(i).getVida() + " de vida, " + inimigos.get(i).getDano() + " de dano e " + inimigos.get(i).getDefesa() + " de defesa");
        }

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        return inimigos.get(op-1);

    }

    public static void add() {
        Scanner sc = new Scanner(System.in);
        Inimigo inimigo = null;
        Cenarios.clearLines();
        System.out.println("---Criação de Inimigos---");
        System.out.println("");
        System.out.println("---Digite seus dados:");
        System.out.println(" Nome : ");
        System.out.println(" Vida, dano e defesa: ");
        System.out.println(" Recompensas mínima e máxima de ouro ao derrotá-lo");
        System.out.println("");
        System.out.println("");

        String nome = sc.nextLine();
        int vida = sc.nextInt();
        int dano = sc.nextInt();
        int defesa = sc.nextInt();
        int recompensamin = sc.nextInt();
        int recompensamax = sc.nextInt();

        inimigo = new Inimigo(nome, vida, dano, defesa, recompensamin, recompensamax);

        InimigoBD.addInimigo(inimigo);
    }

    public static void del() {
        Cenarios.clearLines();
        System.out.println("---Escolha qual deseja deletar---");
        System.out.println("");
        Inimigo inimigo = selectInimigo();
        InimigoBD.deleteInimigo(inimigo.getNome());

    }

    public static void edit() {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        Cenarios.clearLines();
        System.out.println("---Escolha qual deseja editar---");
        System.out.println("");
        Inimigo inimigo = selectInimigo();

        while(flag){
            Cenarios.clearLines();
            System.out.println("---Escolha o que deseja editar---");
            System.out.println("");
            System.out.println("1 - Nome");
            System.out.println("2 - Atributos");
            System.out.println("3 - Recompensas");
            System.out.println("4 - Sair");
            System.out.println("");
            System.out.println("");
            int op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 -> {
                    System.out.println("Digite o novo nome: ");
                    String nome = sc.nextLine();
                    InimigoBD.mudarNome(inimigo.getNome(), nome);
                    inimigo.setNome(nome);
                }
                case 2 -> {
                    System.out.println("Digite os novos atributos:");
                    System.out.println("Vida / dano / defesa");
                    int vida = sc.nextInt();
                    int dano = sc.nextInt();
                    int defesa = sc.nextInt();
                    InimigoBD.mudarAtributos(inimigo.getNome(), vida, dano, defesa);
                }
                case 3 -> {
                    System.out.println("Digite os novos limites de valor das recompensas");
                    int recMin = sc.nextInt();
                    int recMax = sc.nextInt();
                    InimigoBD.mudarRecompensas(inimigo.getNome(), recMin, recMax);
                }
                case 4 -> flag = false;
                default -> {
                }
            }
        }
    }

}
