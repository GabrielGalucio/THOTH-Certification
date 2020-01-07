package thoth.modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    static Connection con = null;

    private static final String URL = "jdbc:postgresql://localhost/Thoth";
    private static final String USER = "postgres";
    private static final String SENHA = "123";

    public static Connection getConexao(){
        
        try {
            
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, SENHA);
        //    System.out.println("Conexão ok  !");
            
        } catch (Exception e) {
                System.out.println("Erro na conexao !" +e.getMessage());
        }
        return con;
    }
    
    public static Connection fecharConexao()
    {
        try{
           con.close();           
        }catch(SQLException e){
           System.out.println("Erro ao fechar Conexao !" +e.getMessage());
        }

        return con;
    }

    public static void main(String[] args) {
      Conexao.getConexao();		

    }
    
}
