package thoth.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import thoth.modelo.bean.Localizacao;
import thoth.modelo.conexao.Conexao;

public class LocalizacaoDao {
    
    private Connection conn = null;                    // Prepara a conexão com o banco de Dados
    private PreparedStatement ps   = null;			   // Prepara o ambiente ou caminho para a banco de dados 
    private ResultSet rs   = null;					   // Envia as funções sql da aplicação para o banco
    private String sql  = "";
    private List<Localizacao> listaLocalizacao = new ArrayList<>();
    
    /**
     * Método responsável por criar o salvar as LOCALIZAÇÃO. 
     * 
     * @param local
     */
    public void salvar( Localizacao local ) throws SQLException
    {
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "INSERT INTO public.localizacao(local_nome, local_qtde_vagas, local_end_cep, local_end_logradouro, local_end_bairro, local_end_numerico)\n" +
              "    VALUES (?, ?, ?, ?, ?, ?)";

        ps = conn.prepareStatement(sql);  

        ps.setString(1, local.getNome() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setInt(2, local.getQtdeVaga());
        ps.setString(3, local.getCep());
        ps.setString(4, local.getLogradouro());
        ps.setString(5, local.getBairro());
        ps.setShort(6, local.getNumero());
        
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
    }
    
    /**
     * Método responável por Pesquisar a LOCALIZACAO do banco de dados.
     * @param codigo
     * @return 
     */
    public Localizacao pesquisarLocalizacao( long codigo )
    {
        conn = Conexao.getConexao();
        	
	sql = "SELECT * FROM public.localizacao WHERE local_cod = ?";
	
	Localizacao local = null;
		
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setLong(1, codigo);
            rs = ps.executeQuery();

            if(rs.next()){

                local = new Localizacao();

                local.setCodigo( rs.getLong("local_cod") );
                local.setNome( rs.getString("local_nome") );
                local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
                local.setCep( rs.getString("local_end_cep") );
                local.setLogradouro( rs.getString("local_end_logradouro") );
                local.setBairro( rs.getString("local_end_bairro") );
                local.setNumero( rs.getShort("local_end_numerico") );

            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println("ERRO AO PESQUISAR - DAO LOCALIZAÇÃO");
        }
        
        return local;
        
    }
    
    /**
     * Método responsável por listar todas as LOCALIZAÇÕES do banco de dados
     * @return 
     */
    public List<Localizacao> listarLocalizacao() throws SQLException
    {
        conn = Conexao.getConexao();        
         
	sql = "SELECT * FROM public.localizacao";
	
	Localizacao local = null;
        
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while(rs.next()){

            local = new Localizacao();
            
            local.setCodigo( rs.getLong("local_cod") );
            local.setNome( rs.getString("local_nome") );
            local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
            local.setCep( rs.getString("local_end_cep") );
            local.setLogradouro( rs.getString("local_end_logradouro") );
            local.setBairro( rs.getString("local_end_bairro") );
            local.setNumero( rs.getShort("local_end_numerico") );
            
            listaLocalizacao.add(local);
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        	
	return listaLocalizacao;
        
    }
    
    /**
     * Método responsável por listar todas as LOCALIZAÇÕES com o nome informado.
     * @param nome
     * @return 
     */
    public List<Localizacao> listarLocalizacao( String nome ) throws SQLException
    {
        conn = Conexao.getConexao();        
         
	sql = "SELECT * FROM public.localizacao WHERE local_nome LIKE '%"+nome+"%'";
	
	Localizacao local = null;
        
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while(rs.next()){

            local = new Localizacao();
            
            local.setCodigo( rs.getLong("local_cod") );
            local.setNome( rs.getString("local_nome") );
            local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
            local.setCep( rs.getString("local_end_cep") );
            local.setLogradouro( rs.getString("local_end_logradouro") );
            local.setBairro( rs.getString("local_end_bairro") );
            local.setNumero( rs.getShort("local_end_numerico") );
            
            listaLocalizacao.add(local);
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        	
	return listaLocalizacao;
        
    }
    
    /**
     * Método responsável por alterar dados no Banco 
     * @param local
     */
    public void alterar( Localizacao local ) throws SQLException
    {
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "UPDATE public.localizacao\n" +
                "   SET local_nome=?, local_qtde_vagas=?, local_end_cep=?, \n" +
                "       local_end_logradouro=?, local_end_bairro=?, local_end_numerico=?\n" +
                " WHERE local_cod=?";

        ps = conn.prepareStatement(sql);  
        
        ps.setString(1, local.getNome() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setInt(2, local.getQtdeVaga());
        ps.setString(3, local.getCep());
        ps.setString(4, local.getLogradouro());
        ps.setString(5, local.getBairro());
        ps.setShort(6, local.getNumero() );
        ps.setLong(7, local.getCodigo());
      
        ps.executeUpdate();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
        
    }
    
    /**
     * Método responsável por excluir dados do banco de dados 
     * @param local
     */
    public void excluir( Localizacao local ) throws SQLException
    {
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "DELETE FROM public.localizacao WHERE local_cod = ?";

        ps = conn.prepareStatement(sql);  
        
        ps.setLong(1, local.getCodigo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
      
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();    }
    
}
