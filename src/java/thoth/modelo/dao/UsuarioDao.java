package thoth.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import thoth.modelo.bean.Usuario;
import thoth.modelo.conexao.Conexao;

public class UsuarioDao {
    
    private Connection conn = null;                    // Prepara a conexão com o banco de Dados
    private PreparedStatement ps   = null;			   // Prepara o ambiente ou caminho para a banco de dados 
    private ResultSet rs   = null;					   // Envia as funções sql da aplicação para o banco
    private String sql  = "";
    private List<Usuario> listaUsuario = new ArrayList<>();
    
    /**
     * Método responsável por criar o salvar as USUARIO. 
     * 
     * @param usuario
     */
    public void salvar( Usuario usuario ) throws SQLException
    {
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "INSERT INTO public.usuario(usu_nome, usu_email, usu_senha, usu_fone)\n" +
              "    VALUES (?, ?, ?, ?)";

        ps = conn.prepareStatement(sql);  

        ps.setString(1, usuario.getNome() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(2, usuario.getEmail());
        ps.setString(3, usuario.getSenha());
        ps.setString(4, usuario.getFone());
        
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
    }
    
    /**
     * Método responável por Pesquisar a USUARIO do banco de dados.
     * @param codigo
     * @return 
     */
    public Usuario pesquisarUsuario( long codigo ) throws SQLException
    {
        conn = Conexao.getConexao();
        	
	sql = "SELECT * FROM public.usuario WHERE usu_cod = ?";
	
	Usuario usuario = null;
		
        ps = conn.prepareStatement(sql);
        ps.setLong(1, codigo);
        rs = ps.executeQuery();

        if(rs.next()){

            usuario = new Usuario();
            
            usuario.setCodigo( rs.getLong("usu_cod") );
            usuario.setNome( rs.getString("usu_nome") );
            usuario.setEmail( rs.getString("usu_email") );
            usuario.setSenha( rs.getString("usu_senha") );
            usuario.setStatus( rs.getString("usu_status") );
            usuario.setFone( rs.getString("usu_fone") );
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        return usuario;
        
    }
    
    /**
     * Método responsável por Chamar o Usuário Recém-Cadastrado para posteriormente
     * se tornar professor ou aluno.
     * @return 
     */
    public long ultimoUsuarioCadastrado()
    {
        conn = Conexao.getConexao();
        	
	sql = "SELECT u.* FROM usuario u WHERE u.usu_cod = (select max(usu_cod) from usuario);";
	
	Usuario usuario = null;
		
        try {
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();

            if(rs.next()){

                usuario = new Usuario();

                usuario.setCodigo( rs.getLong("usu_cod") );
                usuario.setNome( rs.getString("usu_nome") );
                usuario.setEmail( rs.getString("usu_email") );
                usuario.setSenha( rs.getString("usu_senha") );
                usuario.setStatus( rs.getString("usu_status") );
                usuario.setFone( rs.getString("usu_fone") );
            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();

    } catch (SQLException ex) {
            System.out.println("ERRO AO IDENTIFICAR O ÚLTIMO USUARIO CADASTRADO\nDAO USUARIO: "+ex);
    }
       
        return usuario.getCodigo();
    }
    
    /**
     * Método responsável pela validação (login) do USUARIO no sistema.
     * @param email
     * @param senha
     * @return 
     */
    public Usuario realizarLogin( String email, String senha ) throws SQLException
    {
        conn = Conexao.getConexao();
        	
	sql = "SELECT * FROM public.usuario WHERE upper(usu_email) = upper(?) AND upper(usu_senha) = upper(?) AND usu_status = 'A'";
	
	Usuario usuario = null;
		
        ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, senha);
        
        rs = ps.executeQuery();

        if(rs.next()){

            usuario = new Usuario();
            
            usuario.setCodigo( rs.getLong("usu_cod") );
            usuario.setNome( rs.getString("usu_nome") );
            usuario.setEmail( rs.getString("usu_email") );
            usuario.setSenha( rs.getString("usu_senha") );
            usuario.setStatus( rs.getString("usu_status") );
            usuario.setFone( rs.getString("usu_fone") );
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        return usuario;
        
    }
    
    /**
     * Método responsável por listar todos os USUARIOS do banco de dados
     * @return 
     */
    public List<Usuario> listarUsuario() throws SQLException
    {
        conn = Conexao.getConexao();        
         
	sql = "SELECT * FROM public.usuario ORDER BY usu_nome";
	
	Usuario usuario = null;
		
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while(rs.next()){

            usuario = new Usuario();
            
            usuario.setCodigo( rs.getLong("usu_cod") );
            usuario.setNome( rs.getString("usu_nome") );
            usuario.setEmail( rs.getString("usu_email") );
            usuario.setSenha( rs.getString("usu_senha") );
            usuario.setStatus( rs.getString("usu_status") );
            usuario.setFone( rs.getString("usu_fone") );
            
            listaUsuario.add(usuario);
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        	
	return listaUsuario;
        
    }
    
    /**
     * Método responsável por verificar se o USUÁRIO já está cadastrado.
     * @param email
     * @return
     * @throws SQLException 
     */
    public boolean identificarUsuaricoCadastrado( String email ) throws SQLException
    {
        conn = Conexao.getConexao();        
         
	sql = "SELECT * FROM usuario WHERE upper(usu_email) = upper(?)";
	
        Usuario usuario = null;
	boolean existe = false;  // ESSA VARIÁVEL VERIFICAR SE EXISTE OU NÃO O USUÁRIO CADASTRADO.	
        ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        rs = ps.executeQuery();

        if(rs.next()){

            usuario = new Usuario();
            
            usuario.setCodigo( rs.getLong("usu_cod") );
            usuario.setNome( rs.getString("usu_nome") );
            usuario.setEmail( rs.getString("usu_email") );
            usuario.setSenha( rs.getString("usu_senha") );
            usuario.setStatus( rs.getString("usu_status") );
            usuario.setFone( rs.getString("usu_fone") );
         
        }
        
        /**
         *  Verifica se o USUÁRIO com o email existe ou não.
         */
        if( usuario != null ){
            existe = true; // O USUÁRIO JÁ É CADASTRADO
        }else{
            existe = false; // O USUÁRIO NÃO É CADASTRADO
        }
        
        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        	
	return existe;
        
    }
    
    /**
     * Método responsável por alterar dados no Banco 
     * @param usuario
     * @throws java.sql.SQLException
     */
    public void alterar( Usuario usuario ) throws SQLException
    {
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "UPDATE public.usuario\n" +
              "   SET usu_nome=?, usu_email=?, usu_senha=?, usu_status=?, usu_fone=?\n" +
              " WHERE usu_cod=? ";

        ps = conn.prepareStatement(sql);  
        
        ps.setString(1, usuario.getNome() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(2, usuario.getEmail());
        ps.setString(3, usuario.getSenha());
        ps.setString(4, usuario.getStatus());
        ps.setString(5, usuario.getFone());
        ps.setLong(6, usuario.getCodigo());
        
        ps.executeUpdate();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
        
    }
    
    /**
     * Método responsável por excluir dados do banco de dados 
     * @param usuario
     */
    public void excluir( Usuario usuario ) throws SQLException
    {
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "DELETE FROM public.usuario WHERE usu_cod = ?";

        ps = conn.prepareStatement(sql);  
        
        ps.setLong(1, usuario.getCodigo());    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
      
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
    }
    
}
