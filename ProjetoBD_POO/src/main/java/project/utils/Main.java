package project.utils;

import project.heroi.*;
import project.inimigos.*;
import project.logs.*;


public class Main {

    public static void main(String[] args) {

        CriarHerois();
        CriarInimigos();
        CriarLogs();

        Cenarios.menu();

    }

    public static void CriarHerois(){
        Heroi heroi = new Guerreiro("Guerreiro");
        Heroi heroi2 = new Arqueiro("Arqueiro");
        Heroi heroi3 = new Mago("Mago");

        PersonagemBD.addPersonagemBD(heroi);
        PersonagemBD.addPersonagemBD(heroi2);
        PersonagemBD.addPersonagemBD(heroi3);
    }

    public static void CriarInimigos(){
        Inimigo inimigoFraco = new Inimigo ("inimigoFraco", 100, 10, 10, 10, 100);
        Inimigo inimigoMedio = new Inimigo ("inimigoMedio", 200, 20, 20, 20, 200);
        Inimigo inimigoForte = new Inimigo ("inimigoForte", 300, 30, 30, 30, 300);

        for(int i=0; i<5; i++){
            InimigoBD.addInimigo(inimigoFraco);
        }
        for(int i=0; i<3; i++){
            InimigoBD.addInimigo(inimigoMedio);
        }
        InimigoBD.addInimigo(inimigoForte);
    }

    public static void CriarLogs(){
        LogsBD.addLogs("Arqueiro", "inimigoFraco", 100);
        LogsBD.addLogs("Arqueiro", "inimigoMedio", 156);
        LogsBD.addLogs("Arqueiro", "inimigoFraco", 200);
    }

}

