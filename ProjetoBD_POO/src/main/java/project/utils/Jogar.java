package project.utils;

import project.heroi.Heroi;
import project.heroi.Personagem;
import project.heroi.PersonagemBD;
import project.inimigos.Inimigo;
import project.inimigos.InimigoBD;
import project.logs.Logs;
import project.loja.Loja;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jogar {

    private static Heroi heroi;

    public static Heroi getHeroi() {
        return heroi;
    }
    public static void setHeroi(Heroi heroi) {
        Jogar.heroi = heroi;
    }

    public static void jogar(){
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while(flag) {
            Cenarios.clearLines();
            System.out.println("---Jogar---");
            System.out.println("");
            System.out.println("1 - Explorar");
            System.out.println("2 - Loja");
            System.out.println("3 - Logs");
            System.out.println("4 - Sair");
            System.out.println("");
            int op = sc.nextInt();
            sc.nextLine();
            switch (op){
                case 1 -> explorar();
                case 2 -> Loja.loja(heroi);
                case 3 -> Logs.getLogs(heroi);
                case 4 -> flag = false;
                default -> {
                    Cenarios.clearLines();
                    System.out.println("Opção Inválida");
                    System.out.println("");
                }
            }
            if(heroi.getVida()<= 0){
                flag = false;
            }
        }
    }

    public static void explorar(){
        Scanner sc = new Scanner(System.in);
        Cenarios.clearLines();
        Random random = new Random();
        ArrayList<Inimigo> inimigos = InimigoBD.getInimigos();

        if(inimigos.size() <= 1){
            System.out.println("Inimigos insuficientes");
            return;
        }
        boolean flag = true;
        while(flag) {
            int encontro = random.nextInt(200);
            if (encontro < 1) {
                System.out.println("Parabéns você encontrou um tesouro");
                System.out.println("---         + 50 de ouro       ---");
                heroi.setOuro(heroi.getOuro() + 50);
            } else {
                int sorteio = random.nextInt(inimigos.size());
                Inimigo inimigo = inimigos.get(sorteio);
                encontro(inimigo);
                Personagem.salvar(heroi);
            }

            System.out.println("");
            System.out.println("---Deseja Continuar?");
            System.out.println("1 - Continuar");
            System.out.println("2 - Voltar");
            int op = sc.nextInt();
            switch (op){
                case 1->Cenarios.clearLines();
                case 2->{
                    Cenarios.clearLines();
                    flag = false;
                }
                default -> {}
            }
            if(heroi.getVida()<= 0){
                flag = false;
            }

        }
    }

    public static void encontro(Inimigo inimigo){
        Scanner sc = new Scanner(System.in);
        Cenarios.clearLines();

        System.out.println("    Você encontrou um inimigo    \n");
        inimigo.mostraInfo();
        System.out.println("");
        System.out.println("1 - Lutar");
        System.out.println("2 - Tomar poção de dano e lutar");
        System.out.println("3 - Tomar poção de defesa e lutar");
        System.out.println("4 - Tentar fugir");

        int op = sc.nextInt();
        int pot = 0;
        boolean temPot = true;

        switch (op){
            case 1 ->batalha(inimigo, pot, temPot);
            case 2 ->{
                temPot = heroi.usarItem(2);
                if(temPot) pot = 2;
                batalha(inimigo, pot, temPot);
            }
            case 3 ->{
                temPot = heroi.usarItem(3);
                if(temPot) pot = 3;
                batalha(inimigo, pot, temPot);
            }
            case 4 ->{
                if(fugir()){
                    System.out.println("Escapou");
                }
                else {
                    System.out.println("Não conseguiu escapar");
                    sc.nextLine();
                    batalha(inimigo,pot,temPot);
                }
            }
            default -> {}
        }
    }
    public static boolean fugir(){
        Random random = new Random();
        return random.nextBoolean();
    }

    public static void batalha(Inimigo inimigo, int pocao, boolean temPot){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int vidaInicial = inimigo.getVida();
        int vidaInimigo = inimigo.getVida();
        double danoHeroi = heroi.getDanoArma()*heroi.getAtaque();
        double defesaHeroi = heroi.getDefesa();
        int danoInimigo = inimigo.getDano();

        if(pocao == 2){danoHeroi *= 1.5;}
        if(pocao == 3){defesaHeroi *= 1.5;}

        Cenarios.clearLines();

        if(!temPot) System.out.println("Poções insuficientes \n\n");

        boolean flag = true;
        while(flag){
            System.out.println("---Batalha---");
            System.out.println("        " + vidaInimigo + "/" + vidaInicial);
            System.out.println("        " + inimigo.getNome());
            System.out.println("");
            System.out.println("  " + heroi.getNome());
            System.out.println("  " + heroi.getVida() + "/150");
            System.out.println("");
            System.out.println("1 - Atacar");
            System.out.println("2 - Bloquear");
            System.out.println("3 - Usar poção de cura");

            int op = sc.nextInt();
            switch (op){
                case 1->{
                    int danoCausado = (int) ((danoHeroi*random.nextDouble(0.8, 1.2))*((100.0-inimigo.getDefesa())/100));
                    vidaInimigo-= danoCausado;
                    int danoRecebido = (int) (danoInimigo*(1.0/defesaHeroi)*random.nextDouble(0.8, 1.2));
                    heroi.setVida(heroi.getVida()-danoRecebido);
                    Cenarios.clearLines();
                    System.out.println("Você causou " + danoCausado + " de dano ao inimigo\n");
                    System.out.println("Você recebeu " + danoRecebido + " de dano do inimigo \n\n");
                }
                case 2->{
                    int danoRecebido = (int) (danoInimigo*defesaHeroi*random.nextDouble(0.8, 1.2));
                    Cenarios.clearLines();
                    System.out.println("Você bloqueou um ataque de " + danoRecebido + " de dano do inimigo\n\n\n");
                }
                case 3->{
                    boolean usar = heroi.usarItem(1);
                    if(usar){
                        int cura = (int) (50*random.nextDouble(0.8, 1.2));
                        if(heroi.getVida()+cura >= 150){
                            heroi.setVida(150);
                            Cenarios.clearLines();
                            System.out.println("Você curou toda sua vida");
                        }
                        else{

                            heroi.setVida(heroi.getVida()+cura);
                            Cenarios.clearLines();
                            System.out.println("Você se curou em " + cura + " pontos de vida");
                        }
                    }
                    else{
                        Cenarios.clearLines();
                        System.out.println("Sem poções de cura restantes");
                    }
                }
                default -> {}
            }
            if(vidaInimigo<=0){
                flag = false;
                int gold = random.nextInt(inimigo.getRecompensa_min(), inimigo.getRecompensa_max());
                System.out.println("Você derrotou o inimigo");
                System.out.println("--- + " + gold + " de ouro");
                heroi.setOuro(heroi.getOuro()+gold);
                Logs.addLogs(heroi, inimigo, gold);
            }
            if(heroi.getVida()<=0){
                flag = false;
                System.out.println("Você morreu");
                System.out.println("Aqui se encerra sua jornada");
                PersonagemBD.deletePersonagem(heroi.getNome());
            }
        }
    }

}
