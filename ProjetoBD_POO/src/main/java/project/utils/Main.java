package project.utils;

import project.heroi.*;
import project.inimigos.*;
import project.logs.*;


public class Main {

    public static void main(String[] args) {

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
        Inimigo inimigo1 = new Inimigo ("inimigo1", 100, 10, 10, 10, 100);
        Inimigo inimigo2 = new Inimigo ("inimigo2", 200, 20, 20, 20, 200);
        Inimigo inimigo3 = new Inimigo ("inimigo3", 300, 30, 30, 30, 300);

        InimigoBD.addInimigo(inimigo1);
        InimigoBD.addInimigo(inimigo1);
        InimigoBD.addInimigo(inimigo2);
        InimigoBD.addInimigo(inimigo3);
    }

    public static void CriarLogs(){
        LogsBD.addLogs("Guerreiro", "inimigo1", 100);
        LogsBD.addLogs("Guerreiro", "inimigo2", 156);
        LogsBD.addLogs("Guerreiro", "inimigo1", 200);
    }

}

