package project.heroi;


public class Guerreiro extends Heroi{

    public Guerreiro(String nome){
        setNome(nome);
        setVida(150);
        setClassG();
        setOuro(100);
        inventario = new Inventario();
        arma = new Arma(12, "comum");
        setClasse(1);
    }

    public Guerreiro(int id, String nome, int vida, int ouro, Inventario inv, int dano, String raridade){
        setId(id);
        setNome(nome);
        setVida(vida);
        setClassG();
        setOuro(ouro);
        inventario = inv;
        arma = new Arma(dano, raridade);
        setClasse(1);
    }

    public void setClassG(){
        setAtaque(1);
        setDefesa(2);
    }
}
