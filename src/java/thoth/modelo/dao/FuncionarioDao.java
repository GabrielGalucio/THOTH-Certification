package thoth.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import thoth.modelo.bean.Funcionario;
import thoth.modelo.conexao.Conexao;

public class FuncionarioDao {
    
    private Connection conn = null;                    // Prepara a conexão com o banco de Dados
    private PreparedStatement ps   = null;			   // Prepara o ambiente ou caminho para a banco de dados 
    private ResultSet rs   = null;					   // Envia as funções sql da aplicação para o banco
    private String sql  = "";
    private List<Funcionario> listaFuncionario = new ArrayList<>();
    
    /**
     * Método responsável por criar o salvar as FUNCIONARIO. 
     * 
     * @param funcionario
     */
    public void salvar( Funcionario funcionario ) throws SQLException
    {
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "INSERT INTO public.funcionario(usu_cod, fun_cpf, fun_tipo)VALUES (?, ?, ?)";

        ps = conn.prepareStatement(sql);  

        ps.setLong(1, funcionario.getCodigo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(2, funcionario.getCpf() );
        ps.setString(3, funcionario.getTipo() );
        
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
        
    }
    
    /**
     * Método responável por Pesquisar a FUNCIONARIO do banco de dados.
     * @param codigo
     * @return 
     */
    public Funcionario pesquisarFuncionario( long codigo )
    {
               
        conn = Conexao.getConexao();
        	
	sql = "SELECT u.*, f.* FROM usuario u JOIN funcionario f ON u.usu_cod = f.usu_cod WHERE f.usu_cod = ?";
	
	Funcionario funcionario = null;
		
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, codigo);
            rs = ps.executeQuery();

            if(rs.next()){

                funcionario = new Funcionario();

                funcionario.setCodigo( rs.getLong("usu_cod") );
                funcionario.setNome( rs.getString("usu_nome") );
                funcionario.setEmail( rs.getString("usu_email") );
                funcionario.setSenha(rs.getString("usu_senha") );
                funcionario.setStatus(rs.getString("usu_status") );
                funcionario.setFone(rs.getString("usu_fone") );
                funcionario.setCpf(rs.getString("fun_cpf") );
                funcionario.setTipo( rs.getString("fun_tipo") );
            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO PESQUISAR FUNCIONARIO, MOTIVO: "+ex);
        }
        
        return funcionario;
        
    }
    
    /**
     * Método responsável por listar todos os FUNCIONARIO do banco de dados
     * @return 
     */
    public List<Funcionario> listarFuncionario() throws SQLException
    {
        
        conn = Conexao.getConexao();        
         
	sql = "SELECT u.*, f.* FROM usuario u JOIN funcionario f ON u.usu_cod = f.usu_cod";
	
	Funcionario funcionario = null;
		
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while(rs.next()){

            funcionario = new Funcionario();
            
            funcionario.setCodigo( rs.getLong("usu_cod") );
            funcionario.setNome( rs.getString("usu_nome") );
            funcionario.setEmail( rs.getString("usu_email") );
            funcionario.setSenha(rs.getString("usu_senha") );
            funcionario.setStatus(rs.getString("usu_status") );
            funcionario.setFone(rs.getString("usu_fone") );
            funcionario.setCpf(rs.getString("fun_cpf") );
            funcionario.setTipo( rs.getString("fun_tipo") );
            
            listaFuncionario.add(funcionario);
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        	
	return listaFuncionario;
        
    }
    
    /**
     * Método responsável por alterar dados no Banco 
     * @param funcionario
     */
    public void alterar( Funcionario funcionario ) throws SQLException
    {
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "UPDATE public.funcionario SET fun_cpf=?, fun_tipo=? WHERE usu_cod=?";

        ps = conn.prepareStatement(sql);  
        
        ps.setString(1, funcionario.getCpf() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(2, funcionario.getTipo());
        ps.setLong(3, funcionario.getCodigo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        
        ps.executeUpdate();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
        
    }
    
    /**
     * Método responsável por excluir dados do banco de dados 
     * @param funcionario
     */
    public void excluir( Funcionario funcionario ) throws SQLException
    {
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "DELETE FROM public.funcionario WHERE usu_cod = ?";

        ps = conn.prepareStatement(sql);  
        
        ps.setLong(1, funcionario.getCodigo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
      
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
    }
    
}
