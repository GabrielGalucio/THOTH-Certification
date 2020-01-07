package thoth.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import thoth.modelo.bean.Aluno;
import thoth.modelo.conexao.Conexao;

public class AlunoDao {
    
    private Connection conn = null;                    // Prepara a conexão com o banco de Dados
    private PreparedStatement ps   = null;			   // Prepara o ambiente ou caminho para a banco de dados 
    private ResultSet rs   = null;					   // Envia as funções sql da aplicação para o banco
    private String sql  = "";
    private List<Aluno> listaAluno = new ArrayList<>();
    
    /**
     * Método responsável por criar o salvar as ALUNO. 
     * 
     * @param aluno
     */
    public void salvar( Aluno aluno ) throws SQLException
    {
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "INSERT INTO public.aluno(usu_cod)VALUES (?)";

        ps = conn.prepareStatement(sql);  

        ps.setLong(1, aluno.getCodigo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
      
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
        
    }
    
    /**
     * Método responável por Pesquisar a ALUNO do banco de dados.
     * @param codigo
     * @return 
     */
    public Aluno pesquisarAluno( long codigo )
    {
        
        conn = Conexao.getConexao();
        	
	sql = "SELECT u.*, a.* FROM usuario u JOIN aluno a ON u.usu_cod = a.usu_cod WHERE a.usu_cod = ? ";
	
	Aluno aluno = null;
		
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, codigo);
            rs = ps.executeQuery();

            if(rs.next()){

                aluno = new Aluno();

                aluno.setCodigo( rs.getLong("usu_cod") );
                aluno.setNome( rs.getString("usu_nome") );
                aluno.setEmail( rs.getString("usu_email") );
                aluno.setStatus( rs.getString("usu_status") );
                aluno.setFone( rs.getString("usu_fone") );
                aluno.setNumMatricula( rs.getLong("alu_num_matricula") );
            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO PESQUISAR ALUNO, MOTIVO: "+ex);
        }
        
        return aluno;
        
    }
    
    /**
     * ESTE MÉTODO É RESPONSÁVEL POR RETORNAR O ÚLTIMO ALUNO CADASTRADO
     * @return 
     */
    public Aluno pesquisarAluno()
    {
        
        conn = Conexao.getConexao();
        	
	sql = "SELECT u.*, a.* FROM usuario u JOIN aluno a ON u.usu_cod = a.usu_cod "
                + "WHERE a.usu_cod = (select max(usu_cod) from aluno) ";
	
	Aluno aluno = null;
		
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()){

                aluno = new Aluno();

                aluno.setCodigo( rs.getLong("usu_cod") );
                aluno.setNome( rs.getString("usu_nome") );
                aluno.setEmail( rs.getString("usu_email") );
                aluno.setStatus( rs.getString("usu_status") );
                aluno.setFone( rs.getString("usu_fone") );
                aluno.setNumMatricula( rs.getLong("alu_num_matricula") );
            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO PESQUISAR ALUNO, MOTIVO: "+ex);
        }
        
        return aluno;
        
    }
    
    /**
     * Método responsável por listar todos os ALUNO do banco de dados
     * @return 
     */
    public List<Aluno> listarAluno() throws SQLException
    {
        
        conn = Conexao.getConexao();        
         
	sql = "SELECT u.*, a.* FROM usuario u JOIN aluno a ON u.usu_cod = a.usu_cod ORDER BY usu_nome";
	
	Aluno aluno = null;
		
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while(rs.next()){

            aluno = new Aluno();
            
            aluno.setCodigo( rs.getLong("usu_cod") );
            aluno.setNome( rs.getString("usu_nome") );
            aluno.setEmail( rs.getString("usu_email") );
            aluno.setStatus( rs.getString("usu_status") );
            aluno.setFone( rs.getString("usu_fone") );
            aluno.setNumMatricula( rs.getLong("alu_num_matricula") );
            
            listaAluno.add(aluno);
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        	
	return listaAluno;
        
    }
    
    /**
     * Método responsável por excluir dados do banco de dados 
     * @param aluno
     */
    public void excluir( Aluno aluno ) throws SQLException
    {
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "DELETE FROM public.aluno WHERE usu_cod = ?";

        ps = conn.prepareStatement(sql);  
        
        ps.setLong(1, aluno.getCodigo());    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
      
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
        
    }
    
}
