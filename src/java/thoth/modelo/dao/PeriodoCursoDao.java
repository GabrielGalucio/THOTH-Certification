package thoth.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import thoth.modelo.bean.Curso;
import thoth.modelo.bean.Localizacao;
import thoth.modelo.bean.PeriodoCurso;
import thoth.modelo.bean.Professor;
import thoth.modelo.conexao.Conexao;

public class PeriodoCursoDao {
    
    private Connection conn = null;                    // Prepara a conexão com o banco de Dados
    private PreparedStatement ps   = null;			   // Prepara o ambiente ou caminho para a banco de dados 
    private ResultSet rs   = null;					   // Envia as funções sql da aplicação para o banco
    private String sql  = "";
    private List<PeriodoCurso> listaPeriodo = new ArrayList<>();
    
    /**
     * Método responsável por criar o salvar as PERIODO CURSO. 
     * 
     * @param periodo
     * @throws java.sql.SQLException
     */
    public void salvar( PeriodoCurso periodo )
    {
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "INSERT INTO public.periodo_curso(dt_inicio, hr_inicio, hr_fim, curs_cod) VALUES (?, ?, ?, ?)";

        try {  
            ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date( periodo.getDtInicio().getTime() ) );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
            ps.setTime(2, new java.sql.Time( periodo.getHrInicio().getTime() ) );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
            ps.setTime(3, new java.sql.Time( periodo.getHrFinal().getTime() ) );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
            ps.setLong(4, periodo.getCurso().getCodigo() );
            ps.execute();   // Executa ou commit o código SQL
        } catch (SQLException ex) {
            System.out.println("ERRO AO SALVAR PERIODO, MOTIVO: "+ex);
        }

        conn = Conexao.fecharConexao();
        
    }
    
    /**
     * ESTE MÉTODO É RESPONSÁVEL POR PESQUISAR O PERIODO DO CURSO.
     * @param codigo
     * @return 
     */
    public PeriodoCurso pesquisar( long codigo ){
        
        conn = Conexao.getConexao();
        	
	sql = "SELECT c.*, l.*, u.*, p.*, pc.*\n" +
"                FROM public.curso c\n" +
"                	JOIN localizacao l	ON l.local_cod = c.curs_localizacao                	\n" +
"			JOIN periodo_curso pc	ON pc.curs_cod = c.curs_cod\n" +
"                	JOIN usuario u		ON u.usu_cod   = c.curs_professor\n" +
"                	JOIN professor p	ON p.usu_cod   = u.usu_cod\n" +
"                WHERE pc.dt_cod = ?";
	
	PeriodoCurso periodo = null;
	Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
	
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, codigo);
            rs = ps.executeQuery();

            if(rs.next()){

                periodo = new PeriodoCurso();
                curso = new Curso();
                local = new Localizacao();
                professor = new Professor();

                local.setCodigo( rs.getLong("local_cod") );
                local.setNome( rs.getString("local_nome") );
                local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
                local.setCep( rs.getString("local_end_cep") );
                local.setLogradouro( rs.getString("local_end_logradouro") );
                local.setBairro(rs.getString("local_end_bairro") );
                local.setNumero(rs.getShort("local_end_numerico") );

                professor.setCodigo( rs.getLong("usu_cod") );
                professor.setNome( rs.getString("usu_nome") );
                professor.setEmail( rs.getString("usu_email") );
                professor.setSenha( rs.getString("usu_senha") );
                professor.setStatus( rs.getString("usu_status") );
                professor.setFone( rs.getString("usu_fone") );
                professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
                professor.setEspecialidade( rs.getString("prof_especialidade") );

                curso.setCodigo( rs.getLong("curs_cod") );
                curso.setTitulo(rs.getString("curs_titulo") );
                curso.setObjetivo(rs.getString("curs_objetivo") );
                curso.setMetodologia(rs.getString("curs_metodo") );
                curso.setEmenta(rs.getString("curs_ementa") );
                curso.setBibliografia(rs.getString("curs_bibliografia") );
                curso.setCargaHr(rs.getShort("curs_carga_hr") );
                curso.setValor(rs.getDouble("curs_valor") );
                curso.setStatus(rs.getString("curs_status") );
                curso.setArea( rs.getString("curs_area") );
                curso.setEvento( rs.getString("curs_evento") );
                curso.setQtdeVaga( rs.getShort("curs_qtdevaga") );
                curso.setProfessor(professor);

                periodo.setCodigo( rs.getLong("dt_cod") );
                periodo.setDtInicio( rs.getDate("dt_inicio") );
                periodo.setHrInicio( rs.getTime("hr_inicio") );
                periodo.setHrFinal( rs.getTime("hr_fim") );
                periodo.setCurso(curso);

            }

            conn = Conexao.fecharConexao();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO PESQUISAR PERIODO, MOTIVO: "+ex);
        }
        
        return periodo;
    }
    
    public PeriodoCurso pesquisarPorCurso( long codigo ){
        
        conn = Conexao.getConexao();
        	
	sql = "SELECT distinct c.*, l.*, pc.*, u.*, p.*\n" +
"                FROM public.curso c\n" +
"                	JOIN localizacao l	ON l.local_cod = c.curs_localizacao                	\n" +
"                	JOIN periodo_curso pc	ON pc.curs_cod = c.curs_cod\n" +
"			JOIN usuario u		ON u.usu_cod   = c.curs_professor\n" +
"               	JOIN professor p	ON p.usu_cod   = u.usu_cod\n" +
"                WHERE pc.curs_cod = ? AND pc.dt_inicio = (select min(dt_inicio) from periodo_curso WHERE curs_cod = ?)";
	
	PeriodoCurso periodo = null;
	Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
	
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, codigo);
            ps.setLong(2, codigo);
            rs = ps.executeQuery();

            if(rs.next()){

                periodo = new PeriodoCurso();
                curso = new Curso();
                local = new Localizacao();
                professor = new Professor();

                local.setCodigo( rs.getLong("local_cod") );
                local.setNome( rs.getString("local_nome") );
                local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
                local.setCep( rs.getString("local_end_cep") );
                local.setLogradouro( rs.getString("local_end_logradouro") );
                local.setBairro(rs.getString("local_end_bairro") );
                local.setNumero(rs.getShort("local_end_numerico") );

                professor.setCodigo( rs.getLong("usu_cod") );
                professor.setNome( rs.getString("usu_nome") );
                professor.setEmail( rs.getString("usu_email") );
                professor.setSenha( rs.getString("usu_senha") );
                professor.setStatus( rs.getString("usu_status") );
                professor.setFone( rs.getString("usu_fone") );
                professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
                professor.setEspecialidade( rs.getString("prof_especialidade") );

                curso.setCodigo( rs.getLong("curs_cod") );
                curso.setTitulo(rs.getString("curs_titulo") );
                curso.setObjetivo(rs.getString("curs_objetivo") );
                curso.setMetodologia(rs.getString("curs_metodo") );
                curso.setEmenta(rs.getString("curs_ementa") );
                curso.setBibliografia(rs.getString("curs_bibliografia") );
                curso.setCargaHr(rs.getShort("curs_carga_hr") );
                curso.setValor(rs.getDouble("curs_valor") );
                curso.setStatus(rs.getString("curs_status") );
                curso.setArea( rs.getString("curs_area") );
                curso.setEvento( rs.getString("curs_evento") );
                curso.setQtdeVaga( rs.getShort("curs_qtdevaga") );
                curso.setProfessor(professor);

                periodo.setCodigo( rs.getLong("dt_cod") );
                periodo.setDtInicio( rs.getDate("dt_inicio") );
                periodo.setHrInicio( rs.getTime("hr_inicio") );
                periodo.setHrFinal( rs.getTime("hr_fim") );
                periodo.setCurso(curso);

            }

            conn = Conexao.fecharConexao();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO PESQUISAR PERIODO, MOTIVO: "+ex);
        }
        
        return periodo;
    }
    
    /**
     * Método responsável por listar todas as PERIODO CURSO do banco de dados
     * @return 
     * @throws java.sql.SQLException 
     */
    public List<PeriodoCurso> listarPeriodo() throws SQLException
    {
        conn = Conexao.getConexao();
        	
	sql = "SELECT c.*, l.*, pc.*, u.*, p.*\n" +
"                FROM public.curso c\n" +
"                	JOIN localizacao l	ON l.local_cod = c.curs_localizacao                	\n" +
"                	JOIN periodo_curso pc	ON pc.curs_cod = c.curs_cod\n" +
"                	JOIN usuario u		ON u.usu_cod   = c.curs_professor\n" +
"                	JOIN professor p	ON p.usu_cod   = u.usu_cod";
	
	PeriodoCurso periodo = null;
	Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
	
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while(rs.next()){

            periodo = new PeriodoCurso();
            curso = new Curso();
            local = new Localizacao();
            professor = new Professor();
            
            local.setCodigo( rs.getLong("local_cod") );
            local.setNome( rs.getString("local_nome") );
            local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
            local.setCep( rs.getString("local_end_cep") );
            local.setLogradouro( rs.getString("local_end_logradouro") );
            local.setBairro(rs.getString("local_end_bairro") );
            local.setNumero(rs.getShort("local_end_numerico") );
            
            professor.setCodigo( rs.getLong("usu_cod") );
            professor.setNome( rs.getString("usu_nome") );
            professor.setEmail( rs.getString("usu_email") );
            professor.setSenha( rs.getString("usu_senha") );
            professor.setStatus( rs.getString("usu_status") );
            professor.setFone( rs.getString("usu_fone") );
            professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
            professor.setEspecialidade( rs.getString("prof_especialidade") );
            
            curso.setCodigo( rs.getLong("curs_cod") );
            curso.setTitulo(rs.getString("curs_titulo") );
            curso.setObjetivo(rs.getString("curs_objetivo") );
            curso.setMetodologia(rs.getString("curs_metodo") );
            curso.setEmenta(rs.getString("curs_ementa") );
            curso.setBibliografia(rs.getString("curs_bibliografia") );
            curso.setCargaHr(rs.getShort("curs_carga_hr") );
            curso.setValor(rs.getDouble("curs_valor") );
            curso.setStatus(rs.getString("curs_status") );
            curso.setArea( rs.getString("curs_area") );
            curso.setEvento( rs.getString("curs_evento") );
            curso.setQtdeVaga( rs.getShort("curs_qtdevaga") );
            curso.setProfessor(professor);
            
            periodo.setCodigo( rs.getLong("dt_cod") );
            periodo.setDtInicio( rs.getDate("dt_inicio") );
            periodo.setHrInicio( rs.getTime("hr_inicio") );
            periodo.setHrFinal( rs.getTime("hr_fim") );
            periodo.setCurso(curso);
            
            listaPeriodo.add(periodo);
            
        }

        conn = Conexao.fecharConexao();
        ps.close();
        rs.close();
        	
	return listaPeriodo;		
        
    }
    
    /**
     * Método responsável por listar todos os PERIODOS com o código do curso informado.
     * @param codigo
     * @return 
     * @throws java.sql.SQLException 
     */
    public List<PeriodoCurso> listarPeriodo( long codigo ) throws SQLException
    {
        conn = Conexao.getConexao();
        	
	sql = "SELECT c.*, l.*, pc.*, u.*, p.*\n" +
              "                FROM public.curso c\n" +
              "                	JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
              "                	JOIN periodo_curso pc	ON pc.curs_cod = c.curs_cod\n" +
              "                	JOIN usuario u		ON u.usu_cod   = c.curs_professor\n" +
              "                	JOIN professor p	ON p.usu_cod   = u.usu_cod\n" +
               "WHERE c.curs_cod = ? ";
	
	PeriodoCurso periodo = null;
	Curso curso  = null;
        Localizacao local = null;        
        Professor professor = null;
	
        ps = conn.prepareStatement(sql);
        ps.setLong(1, codigo);
        rs = ps.executeQuery();

        while(rs.next()){

            periodo = new PeriodoCurso();
            curso = new Curso();
            local = new Localizacao();
            professor = new Professor();
            
            local.setCodigo( rs.getLong("local_cod") );
            local.setNome( rs.getString("local_nome") );
            local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
            local.setCep( rs.getString("local_end_cep") );
            local.setLogradouro( rs.getString("local_end_logradouro") );
            local.setBairro(rs.getString("local_end_bairro") );
            local.setNumero(rs.getShort("local_end_numerico") );
            
            professor.setCodigo( rs.getLong("usu_cod") );
            professor.setNome( rs.getString("usu_nome") );
            professor.setEmail( rs.getString("usu_email") );
            professor.setSenha( rs.getString("usu_senha") );
            professor.setStatus( rs.getString("usu_status") );
            professor.setFone( rs.getString("usu_fone") );
            professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
            professor.setEspecialidade( rs.getString("prof_especialidade") );
            
            curso.setCodigo( rs.getLong("curs_cod") );
            curso.setTitulo(rs.getString("curs_titulo") );
            curso.setObjetivo(rs.getString("curs_objetivo") );
            curso.setMetodologia(rs.getString("curs_metodo") );
            curso.setEmenta(rs.getString("curs_ementa") );
            curso.setBibliografia(rs.getString("curs_bibliografia") );
            curso.setCargaHr(rs.getShort("curs_carga_hr") );
            curso.setValor(rs.getDouble("curs_valor") );
            curso.setStatus(rs.getString("curs_status") );
            curso.setArea( rs.getString("curs_area") );
            curso.setEvento( rs.getString("curs_evento") );
            curso.setQtdeVaga( rs.getShort("curs_qtdevaga") );
            curso.setProfessor(professor);
            
            periodo.setCodigo( rs.getLong("dt_cod") );
            periodo.setDtInicio( rs.getDate("dt_inicio") );
            periodo.setHrInicio( rs.getTime("hr_inicio") );
            periodo.setHrFinal( rs.getTime("hr_fim") );
            periodo.setCurso(curso);
            
            listaPeriodo.add(periodo);
            
        }

        conn = Conexao.fecharConexao();
        ps.close();
        rs.close();
        	
	return listaPeriodo;		
        
    }
    
    public List<Date> listarDataCurso( long cd_curso )
    {
        List<Date> datasCurso = new ArrayList<>();
        
        conn = Conexao.getConexao();
        	
	sql = "SELECT * FROM view_data_curso WHERE curs_cod = ? ";
	
	Date data = null;
	
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cd_curso);
            rs = ps.executeQuery();

            while(rs.next()){
                
                data = rs.getDate("dt_inicio");
                datasCurso.add(data);

            }

            conn = Conexao.fecharConexao();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoCursoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
                	
	return datasCurso;		
        
    }
    
    /**
     * Método responsável por alterar dados no Banco 
     * @param periodo
     * @throws java.sql.SQLException
     */
    public void alterar( PeriodoCurso periodo ) throws SQLException
    {
        
        
        sql = "UPDATE public.periodo_curso\n" +
              "   SET dt_inicio=?, hr_inicio=?, hr_fim=?, curs_cod=?\n" +
              " WHERE dt_cod=? ";

        ps = conn.prepareStatement(sql);  
        
        ps.setDate(1, new java.sql.Date( periodo.getDtInicio().getTime() ) );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setTime(2, new java.sql.Time( periodo.getHrInicio().getTime() ) );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setTime(3, new java.sql.Time( periodo.getHrFinal().getTime() ) );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setLong(4, periodo.getCurso().getCodigo() );
        ps.setLong(5, periodo.getCodigo() );
        
        ps.executeUpdate();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
        
    }
    
    /**
     * Método responsável por excluir dados do banco de dados 
     * @param periodo
     * @throws java.sql.SQLException
     */
    public void excluir( PeriodoCurso periodo ) throws SQLException
    {
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "DELETE FROM public.periodo_curso WHERE dt_cod = ?";

        ps = conn.prepareStatement(sql);  
        
        ps.setLong(1, periodo.getCodigo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
      
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
        
    }
    
}
