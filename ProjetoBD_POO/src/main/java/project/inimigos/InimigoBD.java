package project.inimigos;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;

public class InimigoBD {

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

    public static void addInimigo(Inimigo inimigo){

        String nome = inimigo.getNome();
        int vida = inimigo.getVida();
        int dano = inimigo.getDano();
        int defesa = inimigo.getDefesa();
        int recompensaMin = inimigo.getRecompensa_min();
        int recompensaMax = inimigo.getRecompensa_max();

        if(!existEncontraveis(nome)){
            adicionarEncontraveis(nome);
        }

        int id = getIdInimigo(nome);
        connect();
        try {
            String sql = "INSERT INTO inimigo(id_encontravel, vida, dano, defesa, recompensa_min, recompensa_max) VALUES(?, ?, ?, ?, ?, ?)";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, vida);
            pst.setInt(3, dano);
            pst.setInt(4, defesa);
            pst.setInt(5, recompensaMin);
            pst.setInt(6, recompensaMax);
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

    private static void adicionarEncontraveis(String nome){
        connect();
        try {
            String sql = "INSERT INTO encontraveis(nome, tipo) VALUES(?, 'inimigo')";
            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();                           // executa o comando

        } catch (SQLException e) {
            System.out.println("Erro de operação: " + e.getMessage());
        }finally {
            try{
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    private static boolean existEncontraveis(String nome){
        connect();
        String sql = "SELECT * FROM encontraveis WHERE nome = '" + nome +"'";
        int aux = -1;
        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()) {
                aux = result.getInt("id");
                if (aux != -1) return true;
            }
        }catch(SQLException e){
            System.out.println("Erro de opecação: " + e.getMessage() );
        } finally {
            try{
                connection.close();
            }catch(SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return false;
    }

    public static ArrayList<Inimigo> getInimigos(){
        Map<Integer,String> nomes = idEncontravel();
        connect();
        String sql = "SELECT * FROM inimigo";
        ArrayList<Inimigo> inimigos = new ArrayList<>();

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){

                int id = result.getInt("id_encontravel");
                String nome = nomes.get(id);
                int vida = result.getInt("vida");
                int dano = result.getInt("dano");
                int defesa = result.getInt("defesa");
                int recompensaMin = result.getInt("recompensa_min");
                int recompensaMax = result.getInt("recompensa_max");

                Inimigo inimigoTemp = new Inimigo(nome, vida, dano, defesa, recompensaMin, recompensaMax);

                inimigos.add(inimigoTemp);
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
        return inimigos;
    }

    public static void deleteInimigo(String nome){
        int id = getIdInimigo(nome);
        connect();
        String sql = "DELETE FROM encontraveis WHERE id = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
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
        int id = getIdInimigo(nomeantigo);
        String sql = "UPDATE encontraveis SET nome = ? WHERE id = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, novonome);
            pst.setInt(2, id);
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

    public static void mudarAtributos(String nome, int vida, int dano, int defesa){
        int id = getIdInimigo(nome);
        connect();
        String sql = "UPDATE inimigo SET vida = ?, dano = ?, defesa = ? WHERE id_encontravel = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1, vida);
            pst.setInt(2, dano);
            pst.setInt(3, defesa);
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

    public static void mudarRecompensas(String nome, int recMin, int recMax){
        int id = getIdInimigo(nome);
        connect();
        String sql = "UPDATE inimigo SET recompensa_min = ?, recompensa_max = ? WHERE id_encontravel = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1, recMin);
            pst.setInt(2, recMax);
            pst.setInt(3, id);
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

    private static int getIdInimigo(String nome){
        connect();
        String sql = "SELECT id FROM encontraveis WHERE nome ='" + nome + "' and tipo = 'inimigo'";
        int id = -1;

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()) {
                id = result.getInt("id");
            }
        }catch(SQLException e){
            System.out.println("Erro de opecação: " + e.getMessage());
        }finally {
            try{
                connection.close();
            }catch(SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return id;
    }

    private static Map<Integer, String> idEncontravel(){
        Map<Integer,String> nomes = new HashMap<>();
        connect();
        String sql = "SELECT * FROM encontraveis WHERE tipo = 'inimigo'";

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()) {
                nomes.put(result.getInt("id"),result.getString("nome"));
            }
        }catch(SQLException e){
            System.out.println("Erro de opecação: " + e.getMessage());
        }finally {
            try{
                connection.close();
            }catch(SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return nomes;

    }

}
