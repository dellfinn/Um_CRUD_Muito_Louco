package factory;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {

    //conexao com o data base
    private static final String login = "admin";
    private static final String senha = "brasil";
    private static final String host = "localhost";
    private static final String dbName = "escola";
    private static final String url = "jdbc:mysql://" + host + "/" + dbName;

    public static Connection createConnectionToMySQL()throws Exception {
        //faz que a classe seja carregada pela JVM
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,login,senha);
        return connection;
    }

    public static void main(String[] args) throws Exception {
        //Recuperar uma conexão com o banco de dados

        Connection con = createConnectionToMySQL();

        //Testar a conexao
        if (con!=null){
            System.out.println("Conexão obetida com sucesso");
        con.close();
        }else{
            System.out.println("Conexão fallhou");
        }
    }

    }
