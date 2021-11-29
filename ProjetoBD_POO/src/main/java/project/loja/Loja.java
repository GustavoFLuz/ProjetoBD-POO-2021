package project.loja;

import project.heroi.Arma;
import project.heroi.Heroi;
import project.heroi.PersonagemBD;
import project.utils.Cenarios;

import java.util.Random;
import java.util.Scanner;

public class Loja {

    public static boolean saldoInsuficiente;
    public static boolean compraRealizada;

    public static void loja(Heroi heroi){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        saldoInsuficiente = false;
        compraRealizada = false;

        int precoP1 = 10+random.nextInt(10);
        int precoP2 = 15+random.nextInt(10);
        int precoP3 = 15+random.nextInt(10);

        int raridadeArma = random.nextInt(1,5);
        String raridade = Arma.defRaridade(raridadeArma);
        int dano = Arma.danoArma(raridadeArma);
        int precoArma = Arma.defPrecoArma(dano);
        Arma arma = new Arma(dano,raridade);

        boolean flag = true;

        while(flag) {
            Cenarios.clearLines();

            if(compraRealizada) System.out.println("Compra realizada com sucesso\n");
            compraRealizada = false;

            if(saldoInsuficiente) System.out.println("Saldo insuficiente\n");
            saldoInsuficiente = false;

            System.out.println("-----                Loja                -----\n");
            System.out.println("                              Saldo Disponível:" + heroi.getOuro());
            System.out.println("1 - Poção de vida:   " + precoP1 + " moedas de ouro");
            System.out.println("2 - Poção de dano:   " + precoP2 + " moedas de ouro");
            System.out.println("3 - Poção de defesa: " + precoP3 + " moedas de ouro");
            System.out.println("4 - Arma " + arma.getRaridade() + " com " + arma.getDano() + " de dano : " + precoArma + " moedas de ouro");
            System.out.println("\n5- Voltar");

            int op = sc.nextInt();
            switch (op){
                case 1->comprarPot(heroi, 1, precoP1);
                case 2->comprarPot(heroi, 2, precoP2);
                case 3->comprarPot(heroi, 3, precoP3);
                case 4->comprarArma(heroi, arma, precoArma);
                case 5->flag = false;
                default -> {}
            }
            PersonagemBD.salvar(heroi);
        }
    }

    public static void comprarPot(Heroi heroi, int id, int preco){
        if(heroi.getOuro()<preco){
            saldoInsuficiente = true;
            return;
        }
        heroi.addPot(id);
        heroi.setOuro(heroi.getOuro()-preco);
        compraRealizada = true;
    }
    public static void comprarArma(Heroi heroi, Arma arma, int preco){
        if(heroi.getOuro()<preco){
            saldoInsuficiente = true;
            return;
        }
        heroi.setArma(arma);
        heroi.setOuro(heroi.getOuro()-preco);
        compraRealizada = true;
    }

}
