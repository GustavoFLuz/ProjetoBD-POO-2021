package project.heroi;

import project.utils.Cenarios;

import java.util.ArrayList;
import java.util.Scanner;

public class Personagem implements project.utils.CrudBasics{

    public static void add(){
        Scanner sc = new Scanner(System.in);
        Heroi heroi = null;
        Cenarios.clearLines();
        System.out.println("---Criação de Personagem---");
        System.out.println("");
        System.out.println("---Escolha sua Classe");
        System.out.println("");
        System.out.println("1 - Guerreiro ( Vida máxima = 150 / Ataque = 1x / Defesa = 2 )");
        System.out.println("2 - Mago      ( Vida máxima = 100 / Ataque = 1.5x / Defesa = 1.25x )");
        System.out.println("3 - Arqueiro  ( Vida máxima = 70  / Ataque = 2x / Defesa = 0.75x )");
        System.out.println("");

        int op = sc.nextInt();

        Cenarios.clearLines();
        System.out.println("---Criação de Personagem---");
        System.out.println("");
        System.out.println("---Digite seu Nome");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        sc.nextLine();
        String nome = sc.nextLine();

        switch (op) {
            case 1 -> heroi = new Guerreiro(nome);
            case 2 -> heroi = new Mago(nome);
            case 3 -> heroi = new Arqueiro(nome);
            default -> {
            }
        }

        PersonagemBD.addPersonagemBD(heroi);
    }

    public static void edit(){
        Cenarios.clearLines();
        System.out.println("---Editar Personagem---");
        System.out.println("");
        Heroi heroi = selectPersonagem();
        if(heroi == null) return;
        System.out.println("---     ---");
        System.out.println("1 - Mudar nome");
        System.out.println("2 - Reiniciar arma");
        System.out.println("3 - Voltar");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (op){
            case 1:
                sc.nextLine();
                System.out.println("Digite o nome:");
                String nome = sc.nextLine();
                PersonagemBD.mudarNome(heroi.getNome(),nome);
                break;
            case 2:
                PersonagemBD.resetArma(heroi.getNome());
                break;
            case 3:
                break;

        }
    }

    public static void del() {
        Cenarios.clearLines();
        System.out.println("---Deletar Personagem---");
        System.out.println("");
        Heroi heroi = selectPersonagem();
        if(heroi != null)
            PersonagemBD.deletePersonagem(heroi.getNome());
    }

    public static Heroi selectPersonagem(){

        ArrayList<Heroi> herois = PersonagemBD.getPersonagem();
        if(herois.size()==0) return null;
        //System.out.println("---Seleção de Personagem---");
        //System.out.println("");
        for(int i=0; i < herois.size();i++) {
            int aux = i+1;
            System.out.println(aux + " - " + herois.get(i).getNome() + " (" + herois.get(i).returnClasse(herois.get(i).getClasse()) +
                    "  " + herois.get(i).getVida() + "/150) Arma: " + herois.get(i).getRaridadeArma() + " " + herois.get(i).getDanoArma() + " de dano");
        }

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        return herois.get(op-1);

    }

    public static void salvar(Heroi heroi){
        if(PersonagemBD.salvar(heroi))
            System.out.println("Jogo salvo com sucesso");
        else
            System.out.println("Erro ao salvar o jogo");
        System.out.println("");
    }
}
