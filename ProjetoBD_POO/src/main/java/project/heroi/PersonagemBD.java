package project.heroi;

import java.sql.*;
import java.util.ArrayList;

public class PersonagemBD {

    static Connection connection; //conexão com bd
    static Statement statement; //prepara SELECT
    static ResultSet result; //executa SELECT
    static PreparedStatement pst; // prepara querry de INSERT, DELETE, UPDATE

    static final String user = "root";
    static final String password = "12345678";
    static final String database = "projetobdpoo";

    //URL de conexão com servidor
    static final String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";


    public static void connect(){

        try{
            connection = DriverManager.getConnection(url, user, password);
            //System.out.println("Conexão feita com sucesso: " + connection);
        }catch(SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());

        }
    }

    public static void addPersonagemBD(Heroi heroi){
        connect();
        try {
            String sql = "INSERT INTO heroi(nome, vida, classe, ouro, danoarma, raridadearma) VALUES(?, ?, ?, ?, ?, ?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, heroi.getNome());
            pst.setInt(2, heroi.getVida());
            pst.setInt(3, heroi.getClasse());
            pst.setInt(4, heroi.getOuro());
            pst.setInt(5, heroi.arma.getDano());
            pst.setString(6, heroi.getRaridadeArma());
            pst.execute();                           // executa o comando

        } catch (SQLException e) {
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        addInventario(heroi);
    }

    private static void addInventario(Heroi heroi){
        int id = getIdHeroi(heroi.getNome());
        connect();
        try {
            String sql = "INSERT INTO inventario(id_heroi, pocoesvida, pocoesdano, pocoesdefesa) VALUES(?, ?, ?, ?)";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, heroi.inventario.getPocoesVida());
            pst.setInt(3, heroi.inventario.getPocoesDano());
            pst.setInt(4, heroi.inventario.getPocoesDefesa());
            pst.execute();                           // executa o comando

        } catch (SQLException e) {
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    public static ArrayList<Heroi> getPersonagem(){
        ArrayList<Inventario> invs = getInventario();
        connect();
        String sql = "SELECT * FROM heroi";
        ArrayList<Heroi> herois = new ArrayList<>();

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                Heroi heroiTemp = null;
                int id = result.getInt("id");
                String nome = result.getString("nome");
                int vida = result.getInt("vida");
                int ouro = result.getInt("ouro");
                int classe = result.getInt("classe");
                int dano = result.getInt("danoarma");
                String raridade = result.getString("raridadeArma");
                Inventario inv = null;

                for(int i=0;i<invs.size();i++){
                    if(invs.get(i).getId() == id){
                        inv = invs.get(i);
                        invs.remove(i);
                        break;
                    }
                }
                if(classe == 1){
                    heroiTemp = new Guerreiro(id,nome, vida, ouro, inv, dano,raridade);
                } else if(classe == 2){
                    heroiTemp = new Mago(id,nome, vida, ouro, inv, dano, raridade);
                } else if(classe == 3){
                    heroiTemp = new Arqueiro(id,nome, vida, ouro, inv, dano, raridade);
                }
                herois.add(heroiTemp);
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage() );
        }finally {
            try{
                connection.close();
            }catch(SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return herois;
    }

    private static ArrayList<Inventario> getInventario(){
        connect();
        String sql = "SELECT * FROM inventario";

        ArrayList<Inventario> invs = new ArrayList<>();
        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()) {
                int id = result.getInt("id_heroi");
                int pocoesvida = result.getInt("pocoesvida");
                int pocoesdano = result.getInt("pocoesdano");
                int pocoesdefesa = result.getInt("pocoesdefesa");
                Inventario inv = new Inventario(id, pocoesvida, pocoesdano, pocoesdefesa);
                invs.add(inv);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage() );
        }finally {
            try{
                connection.close();
            }catch(SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return invs;
    }

    public static void deletePersonagem(String nome){
        connect();
        String sql = "DELETE FROM heroi WHERE nome = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }finally {
            try {
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    public static void mudarNome(String nomeantigo, String novonome){
        connect();
        String sql = "UPDATE heroi SET nome = ? WHERE nome = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, novonome);
            pst.setString(2, nomeantigo);
            pst.execute();
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }finally {
            try {
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    public static void resetArma(String nome){
        connect();
        String sql = "UPDATE heroi SET danoarma = 10, raridadearma = 'comum' WHERE nome = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }finally {
            try {
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    private static int getIdHeroi(String nome){
        connect();
        String sql = "SELECT * FROM heroi WHERE nome ='" + nome + "'";
        int id = -1;

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()) {
                id = result.getInt("id");
            }
        }catch(SQLException e){
            System.out.println("Erro de opecação: " + e.getMessage() );
        }finally {
            try{
                connection.close();
            }catch(SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return id;
    }

    public static boolean salvar(Heroi heroi){
        int id  = heroi.getId();
        int vida = heroi.getVida();
        int ouro = heroi.getOuro();
        Inventario inv = heroi.getInventario();
        int danoarma = heroi.getDanoArma();
        String raridade = heroi.getRaridadeArma();
        connect();
        String sql = "UPDATE heroi SET vida = ?, ouro = ?, danoarma = ?, raridadearma = ? WHERE id = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1, vida);
            pst.setInt(2, ouro);
            pst.setInt(3, danoarma);
            pst.setString(4, raridade);
            pst.setInt(5, id);
            pst.execute();
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
            return false;
        }finally {
            try {
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                return false;
            }
        }
        salvarInv(inv, id);
        return true;
    }
    private static void salvarInv(Inventario inv, int id){
        connect();
        String sql = "UPDATE inventario SET pocoesvida = ?, pocoesdano = ?, pocoesdefesa = ? WHERE id_heroi = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1, inv.getPocoesVida());
            pst.setInt(2, inv.getPocoesDano());
            pst.setInt(3, inv.getPocoesDefesa());
            pst.setInt(4, id);
            pst.execute();
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }finally {
            try {
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
