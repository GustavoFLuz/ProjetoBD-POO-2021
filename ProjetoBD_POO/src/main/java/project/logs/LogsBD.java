package project.logs;

import java.sql.*;
import java.util.ArrayList;

public class LogsBD {

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

    public static void addLogs(String nome, String inimigo, int ouro) {
        connect();
        try {
            String sql = "INSERT INTO logs(nome_heroi, nome_encontravel, ganho) VALUES(?, ?, ?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, inimigo);
            pst.setInt(3, ouro);
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

    public static ArrayList<String> getLogs(String nome) {
        ArrayList<String> logs = new ArrayList<>();
        connect();
        String sql = "SELECT * FROM logs WHERE nome_heroi = '" + nome + "'";

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){

                String inimigo = result.getString("nome_encontravel");
                if(inimigo == null){
                    inimigo = "um inimigo";
                }
                int ganho = result.getInt("ganho");

                String aux = nome + " ganhou " + ganho + " ao derrotar " + inimigo;

                logs.add(aux);

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
        return logs;
    }
}
