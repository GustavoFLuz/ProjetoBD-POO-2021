package project.logs;

import project.heroi.Heroi;
import project.inimigos.Inimigo;

import java.util.ArrayList;
import java.util.Scanner;

public class Logs {

    public static void addLogs(Heroi heroi, Inimigo inimigo, int ouro){
        LogsBD.addLogs(heroi.getNome(),inimigo.getNome(),ouro);
    }

    public static void getLogs(Heroi heroi){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> log = LogsBD.getLogs(heroi.getNome());

        log.forEach((e)->System.out.println(e));
        sc.nextLine();
    }

}
