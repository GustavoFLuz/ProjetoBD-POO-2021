package project.heroi;


public class Arqueiro extends Heroi{

    public Arqueiro(String nome){
        setNome(nome);
        setVida(150);
        setClassA();
        setOuro(100);
        inventario = new Inventario();
        arma = new Arma(12, "comum");
        setClasse(3);
    }

    public Arqueiro(int id, String nome, int vida, int ouro, Inventario inv, int dano, String raridade){
        setId(id);
        setNome(nome);
        setVida(vida);
        setClassA();
        setOuro(ouro);
        inventario = inv;
        arma = new Arma(dano, raridade);
        setClasse(3);
    }

    public void setClassA(){
        setAtaque(2);
        setDefesa(0.75);
    }

}
