package thoth.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import thoth.modelo.bean.Professor;
import thoth.modelo.conexao.Conexao;

public class ProfessorDao {
    
    private Connection conn = null;                    // Prepara a conexão com o banco de Dados
    private PreparedStatement ps   = null;			   // Prepara o ambiente ou caminho para a banco de dados 
    private ResultSet rs   = null;					   // Envia as funções sql da aplicação para o banco
    private String sql  = "";
    private List<Professor> listaProfessor = new ArrayList<>();
    
    /**
     * Método responsável por criar o salvar as PROFESSOR. 
     * 
     * @param professor
     */
    public void salvar( Professor professor ) throws SQLException
    {
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "INSERT INTO public.professor(usu_cod, prof_prof_link_curriculo, prof_especialidade)\n" +
              "    VALUES (?, ?, ?)";

        ps = conn.prepareStatement(sql);  
        
        ps.setLong(1, professor.getCodigo());
        ps.setString(2, professor.getCurrilo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(3, professor.getEspecialidade() );
        
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
        
    }
    
    /**
     * ESTE MÉTODO É O RESPONSÁVEL POR SALVAR OS MEMBROS ADICIONADOS NA EQUIPE DO PROFESSOR. 
     * @param nome
     * @param cd_professor
     */
    public void adicionarEquipe( String nome, long cd_professor ){
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "INSERT INTO public.equipe(equipe_nome, equipe_prof)VALUES (?, ?)";
        
        try {  
            
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, nome );
            ps.setLong(2, cd_professor );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
            ps.execute();   // Executa ou commit o código SQL
            conn = Conexao.fecharConexao();
        } catch (SQLException ex) {
            System.out.println("ERRO AO ADICIONAR EQUIPE - PROFESSOR DAO, MOTIVO: "+ex);
        }
    
        
    }
    
    /**
     * ESTE MÉTODO TEM A RESPONSABILIDADE DE EXCLUIR O MEMBRO DA EQUIPE.
     * @param cd_membro_equipe
     */
    public void removerEquipe( long cd_membro_equipe ){
        
         conn = Conexao.getConexao();      // Abri a conexão

        sql = "DELETE FROM public.equipe WHERE equipe_cod = ?";

        try {  
            ps = conn.prepareStatement(sql);                    
            ps.setLong(1, cd_membro_equipe);    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO      
            ps.execute();   // Executa ou commit o código SQL
            conn = Conexao.fecharConexao();
        } catch (SQLException ex) {
            System.out.println("ERRO AO EXCLUIR MEMBRO DA EQUIPE, MOTIVO: "+ex);
        }

    }
    
    /**
     * Método responável por Pesquisar a PROFESSOR do banco de dados.
     * @param codigo
     * @return 
     */
    public Professor pesquisarProfessor( long codigo )
    {
        conn = Conexao.getConexao();
        	
	sql = "SELECT u.*, p.* FROM usuario u \n" +
              "JOIN professor p ON u.usu_cod = p.usu_cod\n" +
              "WHERE p.usu_cod = ?";
	
        Professor professor = null;
		
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, codigo);
            rs = ps.executeQuery();

            if(rs.next()){

                professor = new Professor();

                professor.setCodigo( rs.getLong("usu_cod") );
                professor.setNome( rs.getString("usu_nome") );
                professor.setEmail( rs.getString("usu_email") );
                professor.setSenha( rs.getString("usu_senha") );
                professor.setStatus( rs.getString("usu_status") );
                professor.setFone( rs.getString("usu_fone") );
                professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
                professor.setEspecialidade( rs.getString("prof_especialidade") );                
                conn = Conexao.fecharConexao();
                ps.close();
                rs.close();        
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return professor;
        
    }
    
    /**
     * ESTE MÉTODO RETORNA O ÚLTIMO PROFESSOR CADASTRADO
     * @return 
     */
    public Professor pesquisarProfessor()
    {
        conn = Conexao.getConexao();
        	
	sql = "SELECT u.*, p.* FROM usuario u \n" +
              "JOIN professor p ON u.usu_cod = p.usu_cod\n" +
              "WHERE p.usu_cod = (select max(usu_cod) from professor)";
	
        Professor professor = null;
		
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()){

                professor = new Professor();

                professor.setCodigo( rs.getLong("usu_cod") );
                professor.setNome( rs.getString("usu_nome") );
                professor.setEmail( rs.getString("usu_email") );
                professor.setSenha( rs.getString("usu_senha") );
                professor.setStatus( rs.getString("usu_status") );
                professor.setFone( rs.getString("usu_fone") );
                professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
                professor.setEspecialidade( rs.getString("prof_especialidade") );                
                conn = Conexao.fecharConexao();
                ps.close();
                rs.close();        
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return professor;
        
    }
    
    /**
     * Método responsável por listar todos os PROFESSOR do banco de dados
     * @return 
     * @throws java.sql.SQLException 
     */
    public List<Professor> listarProfessor() throws SQLException
    {
        conn = Conexao.getConexao();        
         
	sql = "SELECT u.*, p.* FROM usuario u "
                + "JOIN professor p ON u.usu_cod = p.usu_cod ORDER BY u.usu_nome";
	
        Professor professor = null;
        
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while(rs.next()){

            professor = new Professor();
            
            professor.setCodigo( rs.getLong("usu_cod") );
            professor.setNome( rs.getString("usu_nome") );
            professor.setEmail( rs.getString("usu_email") );
            professor.setSenha( rs.getString("usu_senha") );
            professor.setFone( rs.getString("usu_fone") );
            professor.setStatus( rs.getString("usu_status") );
            professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
            professor.setEspecialidade( rs.getString("prof_especialidade") );
            
            listaProfessor.add(professor);
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        	
	return listaProfessor;
        
    }
    
    /**
     *  ESTE MÉTODO É RESPONSÁVEL POR LISTAR TODOS OS MEMBROS DA EQUIPE DO PROFESSOR
     * @param cd_professor
     * @return
     */
    public List<Professor> listarEquipe( long cd_professor )
    {
        conn = Conexao.getConexao();        
         
	sql = "SELECT u.*, p.*, e.* FROM usuario u\n" +
              "       JOIN professor p ON u.usu_cod = p.usu_cod \n" +
              "       JOIN equipe e 	ON p.usu_cod = e.equipe_prof \n" +
              "       WHERE p.usu_cod = ? ORDER BY e.equipe_nome";
	
        Professor professor = null;
        int qtde_membros = 0;
        String[] nome_equipe = {};
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cd_professor);
            rs = ps.executeQuery();

            while(rs.next()){
                
                professor = new Professor();

                    professor.setCodigo( rs.getLong("usu_cod") );
                    professor.setNome( rs.getString("usu_nome") );
                    professor.setEmail( rs.getString("usu_email") );
                    professor.setSenha( rs.getString("usu_senha") );
                    professor.setStatus( rs.getString("usu_status") );
                    professor.setFone( rs.getString("usu_fone") );
                    professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
                    professor.setEspecialidade( rs.getString("prof_especialidade") );    
                    professor.setCd_equipe( rs.getLong("equipe_cod") );
                    professor.setEquipe( rs.getString("equipe_nome" ) );
                    
                    listaProfessor.add(professor);
            }
            
            conn = Conexao.fecharConexao();
            ps.close();
            rs.close();
        
        } catch (SQLException ex) {
            System.out.println("ERRO AO ENCONTRAR EQUIPE, MOTIVO: "+ex);
        }
                
	return listaProfessor;
        
    }
    
    /**
     * Método responsável por alterar dados no Banco 
     * @param professor
     */
    public void alterar( Professor professor ) throws SQLException
    {
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "UPDATE public.professor\n" +
              "   SET prof_prof_link_curriculo=?, prof_especialidade=?\n" +
              " WHERE usu_cod=?";

        ps = conn.prepareStatement(sql);  
        
        ps.setString(1, professor.getCurrilo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(2, professor.getEspecialidade() );        
        ps.setLong(3, professor.getCodigo());
        
        ps.executeUpdate();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
        
    }
    
    /**
     * Método responsável por excluir dados do banco de dados 
     * @param professor
     */
    public void excluir( Professor professor ) throws SQLException
    {
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "DELETE FROM public.professor WHERE usu_cod = ?";

        ps = conn.prepareStatement(sql);  
        
        ps.setLong(1, professor.getCodigo());    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
      
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();

        
    }
    
}
