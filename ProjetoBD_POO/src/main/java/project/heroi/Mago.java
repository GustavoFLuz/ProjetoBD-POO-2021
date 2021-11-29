package project.heroi;


public class Mago extends Heroi{

    public Mago(String nome){
        setNome(nome);
        setVida(150);
        setClassM();
        setOuro(100);
        inventario = new Inventario();
        arma = new Arma(12, "comum");
        setClasse(2);
    }

    public Mago(int id, String nome, int vida, int ouro, Inventario inv, int dano, String raridade){
        setId(id);
        setNome(nome);
        setVida(vida);
        setClassM();
        setOuro(ouro);
        inventario = inv;
        arma = new Arma(dano, raridade);
        setClasse(2);
    }

    public void setClassM(){
        setAtaque(1.5);
        setDefesa(1.25);
    }
}
