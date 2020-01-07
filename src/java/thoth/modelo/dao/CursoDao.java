package thoth.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import thoth.modelo.bean.Aluno;
import thoth.modelo.bean.Curso;
import thoth.modelo.bean.Funcionario;
import thoth.modelo.bean.Localizacao;
import thoth.modelo.bean.Matricula;
import thoth.modelo.bean.Professor;
import thoth.modelo.conexao.Conexao;

public class CursoDao {
    
    private Connection conn = null;                    // Prepara a conexão com o banco de Dados
    private PreparedStatement ps   = null;			   // Prepara o ambiente ou caminho para a banco de dados 
    private ResultSet rs   = null;					   // Envia as funções sql da aplicação para o banco
    private String sql  = "";						   // atributo que guarda o código sql para ser enviado ao banco
    private List<Curso> listaCurso = new ArrayList<>();
    
    /**
     * Método responsável por criar o salvar as CURSO. 
     * 
     * @param curso
     */
    public void salvar( Curso curso ) throws SQLException
    {
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "INSERT INTO public.curso(curs_titulo, curs_objetivo, \n" +
       "            curs_metodo, curs_ementa, curs_bibliografia, curs_carga_hr, curs_valor, \n" +
       "            curs_area, curs_localizacao, curs_evento, curs_qtdevaga, curs_professor)\n" +
       "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ps = conn.prepareStatement(sql);  

        ps.setString(1, curso.getTitulo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(2, curso.getObjetivo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(3, curso.getMetodologia() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(4, curso.getEmenta() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(5, curso.getBibliografia() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setInt(6, curso.getCargaHr() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setDouble(7, curso.getValor() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(8, curso.getArea() );
        ps.setLong(9, curso.getLocal().getCodigo() );
        ps.setString(10, curso.getEvento() );
        ps.setShort(11, curso.getQtdeVaga());
        ps.setLong(12, curso.getProfessor().getCodigo());
        
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
        
    }
        
    /**
     * Método responável por Pesquisar a CURSO do banco de dados.
     * @param codigo
     * @return 
     */
    public Curso pesquisarCurso( long codigo )
    {
        
        conn = Conexao.getConexao();
        	
	sql = "SELECT c.*, u.*, p.*, l.*\n" +
"                	FROM public.curso c\n" +
"                	JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
"                	JOIN professor p 	ON p.usu_cod = c.curs_professor\n" +
"                	JOIN usuario u 		ON p.usu_cod = u.usu_cod\n" +
"                WHERE c.curs_cod = ?";
	
	Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, codigo);
            rs = ps.executeQuery();

        if(rs.next()){

            curso = new Curso();
            local = new Localizacao();
            professor = new Professor();
           
            professor.setCodigo( rs.getLong("usu_cod") );
            professor.setNome( rs.getString("usu_nome") );
            professor.setEmail( rs.getString("usu_email") );
            professor.setSenha( rs.getString("usu_senha") );
            professor.setStatus( rs.getString("usu_status") );
            professor.setFone( rs.getString("usu_fone") );
            professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
            professor.setEspecialidade( rs.getString("prof_especialidade") );
            
            local.setCodigo( rs.getLong("local_cod") );
            local.setNome( rs.getString("local_nome") );
            local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
            local.setCep( rs.getString("local_end_cep") );
            local.setLogradouro( rs.getString("local_end_logradouro") );
            local.setBairro(rs.getString("local_end_bairro") );
            local.setNumero(rs.getShort("local_end_numerico") );
          
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
            curso.setLocal(local);
            curso.setEvento( rs.getString("curs_evento") );
            curso.setQtdeVaga(rs.getShort("curs_qtdevaga"));
            curso.setProfessor(professor);
     
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO PESQUISAR CURSO, MOTIVO: "+ex);
        }
        
        return curso;
        
    }
    
    /**
     *  ESTE MÉTODO É RESPONSÁVEL POR RETORNAR O ÚLTIMO CURSO INSERIDO.
     * @return 
     */
    public Curso ultimoCursoInserido(){
        
        conn = Conexao.getConexao();
        	
	sql = "SELECT c.*, u.*, p.*, l.*\n" +
"                	FROM public.curso c\n" +
"                	JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
"                	JOIN professor p 	ON p.usu_cod = c.curs_professor\n" +
"                	JOIN usuario u 		ON p.usu_cod = u.usu_cod\n" +
"                WHERE c.curs_cod = (select max(curs_cod) from curso)";
	
	Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

        if(rs.next()){

            curso = new Curso();
            local = new Localizacao();
            professor = new Professor();
           
            professor.setCodigo( rs.getLong("usu_cod") );
            professor.setNome( rs.getString("usu_nome") );
            professor.setEmail( rs.getString("usu_email") );
            professor.setSenha( rs.getString("usu_senha") );
            professor.setStatus( rs.getString("usu_status") );
            professor.setFone( rs.getString("usu_fone") );
            professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
            professor.setEspecialidade( rs.getString("prof_especialidade") );
            
            local.setCodigo( rs.getLong("local_cod") );
            local.setNome( rs.getString("local_nome") );
            local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
            local.setCep( rs.getString("local_end_cep") );
            local.setLogradouro( rs.getString("local_end_logradouro") );
            local.setBairro(rs.getString("local_end_bairro") );
            local.setNumero(rs.getShort("local_end_numerico") );
          
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
            curso.setLocal(local);
            curso.setEvento( rs.getString("curs_evento") );
            curso.setQtdeVaga(rs.getShort("curs_qtdevaga"));
            curso.setProfessor(professor);
     
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO PESQUISAR CURSO, MOTIVO: "+ex);
        }
        
        return curso;
    }
    
    /**
     * Método responsável por listar todos os CURSOS do banco de dados
     * @return 
     */
    public List<Curso> listarCurso() throws SQLException
    {
        
        conn = Conexao.getConexao();        
         
	sql = "SELECT c.*, u.*, p.*, l.*\n" +
"                	FROM public.curso c\n" +
"                	JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
"                	JOIN professor p 	ON p.usu_cod = c.curs_professor\n" +
"                	JOIN usuario u 		ON p.usu_cod = u.usu_cod ORDER BY c.curs_titulo";
	
        Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
		
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while(rs.next()){

            curso = new Curso();
            local = new Localizacao();
            professor = new Professor();
           
            professor.setCodigo( rs.getLong("usu_cod") );
            professor.setNome( rs.getString("usu_nome") );
            professor.setEmail( rs.getString("usu_email") );
            professor.setSenha( rs.getString("usu_senha") );
            professor.setStatus( rs.getString("usu_status") );
            professor.setFone( rs.getString("usu_fone") );
            professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
            professor.setEspecialidade( rs.getString("prof_especialidade") );
            
            local.setCodigo( rs.getLong("local_cod") );
            local.setNome( rs.getString("local_nome") );
            local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
            local.setCep( rs.getString("local_end_cep") );
            local.setLogradouro( rs.getString("local_end_logradouro") );
            local.setBairro(rs.getString("local_end_bairro") );
            local.setNumero(rs.getShort("local_end_numerico") );
          
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
            curso.setLocal(local);
            curso.setEvento( rs.getString("curs_evento") );
            curso.setQtdeVaga(rs.getShort("curs_qtdevaga"));
            curso.setProfessor(professor);
            
            listaCurso.add(curso);
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        	
	return listaCurso;
        
    }
    
    /**
     * Método responsável por listar todos os CURSOS por Funcionário
     * @param cd_funcionario
     * @return 
     */
    public List<Curso> listarCurso( long cd_funcionario )
    {
        
        conn = Conexao.getConexao();        
         
	sql = "SELECT c.*, u.*, p.*, l.*, f.*\n" +
"                	FROM public.curso c\n" +
"                	JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
"                	JOIN professor p 	ON p.usu_cod = c.curs_professor\n" +
"                	JOIN usuario u 		ON p.usu_cod = u.usu_cod\n" +
"                	JOIN funcionario f	ON u.usu_cod = f.usu_cod\n" +
"                WHERE f.usu_cod = ?";
	
        Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
        Funcionario funcionario = null;
		
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cd_funcionario);
            rs = ps.executeQuery();

            while(rs.next()){

                curso = new Curso();
                local = new Localizacao();
                professor = new Professor();
                funcionario = new Funcionario();

                professor.setCodigo( rs.getLong("usu_cod") );
                professor.setNome( rs.getString("usu_nome") );
                professor.setEmail( rs.getString("usu_email") );
                professor.setSenha( rs.getString("usu_senha") );
                professor.setStatus( rs.getString("usu_status") );
                professor.setFone( rs.getString("usu_fone") );
                professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
                professor.setEspecialidade( rs.getString("prof_especialidade") );

                local.setCodigo( rs.getLong("local_cod") );
                local.setNome( rs.getString("local_nome") );
                local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
                local.setCep( rs.getString("local_end_cep") );
                local.setLogradouro( rs.getString("local_end_logradouro") );
                local.setBairro(rs.getString("local_end_bairro") );
                local.setNumero(rs.getShort("local_end_numerico") );

                funcionario.setCodigo( rs.getLong("usu_cod") );
                funcionario.setNome( rs.getString("usu_nome") );
                funcionario.setEmail( rs.getString("usu_email") );
                funcionario.setSenha( rs.getString("usu_senha") );
                funcionario.setStatus( rs.getString("usu_status") );
                funcionario.setCpf( rs.getString("fun_cpf") );
                funcionario.setTipo( rs.getString("prof_tipo") );

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
                curso.setLocal(local);
                curso.setEvento( rs.getString("curs_evento") );
                curso.setQtdeVaga(rs.getShort("curs_qtdevaga"));
                curso.setProfessor(professor);
                curso.setFuncionario(funcionario);

                listaCurso.add(curso);
            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO LISTAR CURSO");
        }
        	
	return listaCurso;
        
    }
    
    /**
     * ESTE MÉTODO É RESPONSÁVEL POR LISTAR TODOS OS CURSOS DO PROFESSOR ATRAVÉS DO SEU RESPECTIVO STATUS.
     * @param cd_professor
     * @param status_curso
     * @return
     * @throws SQLException 
     */
    public List<Curso> listarCurso( long cd_professor, String status_curso ) throws SQLException
    {
        
        conn = Conexao.getConexao();        
         
	sql = "SELECT c.*, u.*, p.*, l.*\n" +
"                	FROM public.curso c\n" +
"			JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
"                	JOIN professor p 	ON p.usu_cod = c.curs_professor\n" +
"                	JOIN usuario u 		ON p.usu_cod = u.usu_cod\n" +
"                WHERE p.usu_cod = ? AND c.curs_status = ?";
	
        Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
		
        ps = conn.prepareStatement(sql);
        ps.setLong(1, cd_professor);
        ps.setString(2, status_curso);
        rs = ps.executeQuery();

        while(rs.next()){

            curso = new Curso();
            local = new Localizacao();
            professor = new Professor();
            
            professor.setCodigo( rs.getLong("usu_cod") );
            professor.setNome( rs.getString("usu_nome") );
            professor.setEmail( rs.getString("usu_email") );
            professor.setSenha( rs.getString("usu_senha") );
            professor.setStatus( rs.getString("usu_status") );
            professor.setFone( rs.getString("usu_fone") );
            professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
            professor.setEspecialidade( rs.getString("prof_especialidade") );
            
            local.setCodigo( rs.getLong("local_cod") );
            local.setNome( rs.getString("local_nome") );
            local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
            local.setCep( rs.getString("local_end_cep") );
            local.setLogradouro( rs.getString("local_end_logradouro") );
            local.setBairro(rs.getString("local_end_bairro") );
            local.setNumero(rs.getShort("local_end_numerico") );
                        
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
            curso.setLocal(local);
            curso.setEvento( rs.getString("curs_evento") );
            curso.setQtdeVaga(rs.getShort("curs_qtdevaga"));
            curso.setProfessor(professor);
            
            listaCurso.add(curso);
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        	
	return listaCurso;
        
    }
    
    /**
     * Método responsável por listar todos CURSOS com base no status passado.
     * @param status_curso
     * @return 
     */
    public List<Curso> listarCurso( String status_curso ) 
    {
   
        conn = Conexao.getConexao();        
         
	sql = "SELECT c.*, u.*, p.*, l.*\n" +
                "	FROM public.curso c\n" +
                "		JOIN localizacao l	ON l.local_cod = c.curs_localizacao \n" +
                "		JOIN usuario u		ON u.usu_cod   = c.curs_professor\n" +
                "		JOIN professor p	ON p.usu_cod   = u.usu_cod\n" +
                " WHERE c.curs_status = ? ORDER BY c.curs_titulo";
	
        Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
		
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, status_curso);
            rs = ps.executeQuery();

            while(rs.next()){

                curso = new Curso();
                local = new Localizacao();
                professor = new Professor();

                professor.setCodigo( rs.getLong("usu_cod") );
                professor.setNome( rs.getString("usu_nome") );
                professor.setEmail( rs.getString("usu_email") );
                professor.setSenha( rs.getString("usu_senha") );
                professor.setStatus( rs.getString("usu_status") );
                professor.setFone( rs.getString("usu_fone") );
                professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
                professor.setEspecialidade( rs.getString("prof_especialidade") );

                local.setCodigo( rs.getLong("local_cod") );
                local.setNome( rs.getString("local_nome") );
                local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
                local.setCep( rs.getString("local_end_cep") );
                local.setLogradouro( rs.getString("local_end_logradouro") );
                local.setBairro(rs.getString("local_end_bairro") );
                local.setNumero(rs.getShort("local_end_numerico") );

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
                curso.setLocal(local);
                curso.setEvento( rs.getString("curs_evento") );
                curso.setQtdeVaga(rs.getShort("curs_qtdevaga"));
                curso.setProfessor(professor);

                listaCurso.add(curso);
            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO LISTAR CURSO, MOTIVO: "+ex);
        }
                	
	return listaCurso;
        
    }
    
    /**
     * ESTE MÉTODO VERIFICA LISTA OS CURSOS EM QUE O ALUNO AINDA NÃO NÃO MATRICULADO.
     * @param status_curso
     * @param cd_curso
     * @param cd_aluno
     * @return
     */
    public List<Curso> listarCurso( String status_curso, long cd_aluno ) 
    {
   
        conn = Conexao.getConexao();        
         
	sql = "SELECT c.*, u.*, p.*, l.*\n" +
"                	FROM public.curso c\n" +
"                		JOIN localizacao l	ON l.local_cod = c.curs_localizacao \n" +
"                		JOIN usuario u		ON u.usu_cod   = c.curs_professor\n" +
"                		JOIN professor p	ON p.usu_cod   = u.usu_cod\n" +
"                 WHERE c.curs_status = ? AND c.curs_cod "
                + "NOT IN(select mat_curs_cod from matricula where mat_alu_cod = ?) \n" +
"               ORDER BY c.curs_titulo";
	
        Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
		
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, status_curso);
            ps.setLong(2, cd_aluno);
            rs = ps.executeQuery();

            while(rs.next()){

                curso = new Curso();
                local = new Localizacao();
                professor = new Professor();

                professor.setCodigo( rs.getLong("usu_cod") );
                professor.setNome( rs.getString("usu_nome") );
                professor.setEmail( rs.getString("usu_email") );
                professor.setSenha( rs.getString("usu_senha") );
                professor.setStatus( rs.getString("usu_status") );
                professor.setFone( rs.getString("usu_fone") );
                professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
                professor.setEspecialidade( rs.getString("prof_especialidade") );

                local.setCodigo( rs.getLong("local_cod") );
                local.setNome( rs.getString("local_nome") );
                local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
                local.setCep( rs.getString("local_end_cep") );
                local.setLogradouro( rs.getString("local_end_logradouro") );
                local.setBairro(rs.getString("local_end_bairro") );
                local.setNumero(rs.getShort("local_end_numerico") );

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
                curso.setLocal(local);
                curso.setEvento( rs.getString("curs_evento") );
                curso.setQtdeVaga(rs.getShort("curs_qtdevaga"));
                curso.setProfessor(professor);

                listaCurso.add(curso);
            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO LISTAR CURSO, MOTIVO: "+ex);
        }
                	
	return listaCurso;
        
    }
    
    /**
     * ESTE MÉTODO É O RESPONSÁVEL POR LISTAR OS CURSOS DO ALUNO, ATRAVÉS DO CÓDIGO DO ALUNO INFORMADO.
     * @param cd_aluno
     * @return
     * @throws SQLException 
     */
    public List<Curso> listarMeusCursos( long cd_aluno ) throws SQLException
    {
   
        conn = Conexao.getConexao();        
         
	sql = "SELECT c.*, u.*, p.*, l.*, m.*, ua.*, a.*\n" +
"                	FROM public.curso c\n" +
"                		JOIN localizacao l	ON l.local_cod = c.curs_localizacao \n" +
"                		JOIN usuario u		ON u.usu_cod   = c.curs_professor\n" +
"                		JOIN professor p	ON p.usu_cod   = u.usu_cod\n" +
"				JOIN matricula m	ON m.mat_curs_cod = c.curs_cod\n" +
"				JOIN aluno a		ON a.usu_cod   = m.mat_alu_cod	\n" +
"				JOIN usuario ua		ON ua.usu_cod   = c.curs_professor\n" +
"           WHERE m.mat_alu_cod = ? ORDER BY c.curs_titulo";
	
        Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
	Matricula matricula = null;
        Aluno aluno = null;
        
        ps = conn.prepareStatement(sql);
        ps.setLong(1, cd_aluno);
        rs = ps.executeQuery();

        while(rs.next()){

            curso = new Curso();
            local = new Localizacao();
            professor = new Professor();
            matricula = new Matricula();
            aluno = new Aluno();
            
            professor.setCodigo( rs.getLong("usu_cod") );
            professor.setNome( rs.getString("usu_nome") );
            professor.setEmail( rs.getString("usu_email") );
            professor.setSenha( rs.getString("usu_senha") );
            professor.setStatus( rs.getString("usu_status") );
            professor.setFone( rs.getString("usu_fone") );
            professor.setCurrilo( rs.getString("prof_prof_link_curriculo") );
            professor.setEspecialidade( rs.getString("prof_especialidade") );
            
            local.setCodigo( rs.getLong("local_cod") );
            local.setNome( rs.getString("local_nome") );
            local.setQtdeVaga( rs.getInt("local_qtde_vagas") );
            local.setCep( rs.getString("local_end_cep") );
            local.setLogradouro( rs.getString("local_end_logradouro") );
            local.setBairro(rs.getString("local_end_bairro") );
            local.setNumero(rs.getShort("local_end_numerico") );
          
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
            curso.setLocal(local);
            curso.setEvento( rs.getString("curs_evento") );
            curso.setQtdeVaga(rs.getShort("curs_qtdevaga"));
            curso.setProfessor(professor);
            
            aluno.setCodigo( rs.getLong("usu_cod") );
            aluno.setNome( rs.getString("usu_nome") );
            aluno.setEmail( rs.getString("usu_email") );
            aluno.setSenha( rs.getString("usu_senha") );
            aluno.setStatus( rs.getString("usu_status") );
            aluno.setFone( rs.getString("usu_fone") );
            aluno.setNumMatricula( rs.getLong("alu_num_matricula") );
         
            matricula.setCodigo( rs.getLong("mat_cod") );
            matricula.setValor( rs.getDouble("mat_valor") );
            matricula.setData( rs.getDate("mat_data_dt_mat") );
            matricula.setHora( rs.getTime("mat_data_hr_mat") );
            matricula.setAluno(aluno);
            matricula.setCurso(curso);
            matricula.setTransacaoPagamento( rs.getString("mat_transacao_pagamento") );
            
            listaCurso.add(curso);
        }

        conn = Conexao.fecharConexao();

        ps.close();
        rs.close();
        	
	return listaCurso;
        
    }
    
    /**
     * Método responsável por alterar dados no Banco 
     * @param curso
     * @param parte
     * @throws java.sql.SQLException
     */
    public void alterar( Curso curso, int parte ) throws SQLException
    {
        if( parte == 1 ){
            
            conn = Conexao.getConexao();      // Abri a conexão

            sql = "UPDATE public.curso\n" +
                    "   SET curs_titulo=?, curs_objetivo=?, \n" +
                    "       curs_metodo=?, curs_ementa=?, curs_bibliografia=?, \n" +
                    "       curs_professor=? \n" +
                 " WHERE curs_cod=?";

            ps = conn.prepareStatement(sql);  

            ps.setString(1, curso.getTitulo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
            ps.setString(2, curso.getObjetivo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
            ps.setString(3, curso.getMetodologia() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
            ps.setString(4, curso.getEmenta() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
            ps.setString(5, curso.getBibliografia() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO          
            ps.setLong(6, curso.getProfessor().getCodigo());        
            ps.setLong(7, curso.getCodigo());

            ps.executeUpdate();   // Executa ou commit o código SQL

            conn = Conexao.fecharConexao();
            
        }else if( parte == 2 ){
            
            conn = Conexao.getConexao();      // Abri a conexão

            sql = "UPDATE public.curso\n" +
                    "   SET curs_carga_hr=?, curs_valor=?, curs_status=?, curs_area=?, curs_localizacao=?, \n" +
                    "       curs_evento=?, curs_qtdevaga = ?, curs_professor=? \n" +
                 " WHERE curs_cod=?";

            ps = conn.prepareStatement(sql);  
           
            ps.setShort(1, curso.getCargaHr() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
            ps.setDouble(2, curso.getValor() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
            ps.setString(3, curso.getStatus());
            ps.setString(4, curso.getArea() );
            ps.setLong(5, curso.getLocal().getCodigo() );
            ps.setString(6, curso.getEvento() );
            ps.setShort(7, curso.getQtdeVaga());
            ps.setLong(8, curso.getProfessor().getCodigo());        
            ps.setLong(9, curso.getCodigo());

            ps.executeUpdate();   // Executa ou commit o código SQL

            conn = Conexao.fecharConexao();
            
        }else if( parte == 3 ){
            
            conn = Conexao.getConexao();      // Abri a conexão

            sql = "UPDATE public.curso\n" +
                    "   SET curs_status=?, curs_funcionario=? WHERE curs_cod=?";

            ps = conn.prepareStatement(sql);  
           
            ps.setString(1, curso.getStatus());       
            ps.setLong(2, curso.getFuncionario().getCodigo());       
            ps.setLong(3, curso.getCodigo());

            ps.executeUpdate();   // Executa ou commit o código SQL

            conn = Conexao.fecharConexao();
        }
    }
    
    /**
     * Método responsável por alterar dados no Banco 
     * @param curso
     */
    public void alterarAvaliar( Curso curso ) throws SQLException
    {
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "UPDATE public.curso\n" +
                "   SET curs_titulo=?, curs_objetivo=?, \n" +
                "       curs_metodo=?, curs_ementa=?, curs_bibliografia=?, \n" +
                "       curs_carga_hr=?, curs_valor=?, curs_status=?, curs_area=?, curs_localizacao=?, \n" +
                "       curs_evento=?, curs_funcionario=?, curs_qtdevaga = ?, curs_professor=? \n" +
             " WHERE curs_cod=?";

        ps = conn.prepareStatement(sql);  

        ps.setString(1, curso.getTitulo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(2, curso.getObjetivo() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(3, curso.getMetodologia() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(4, curso.getEmenta() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(5, curso.getBibliografia() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setShort(6, curso.getCargaHr() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setDouble(7, curso.getValor() );    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
        ps.setString(8, curso.getStatus());
        ps.setString(9, curso.getArea() );
        ps.setLong(10, curso.getLocal().getCodigo() );
        ps.setString(11, curso.getEvento() );
        ps.setLong(12, curso.getFuncionario().getCodigo());
        ps.setShort(13, curso.getQtdeVaga());
        ps.setLong(14, curso.getProfessor().getCodigo());        
        ps.setLong(15, curso.getCodigo());
        
        ps.executeUpdate();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
    }
    
    /**
     * Método responsável por excluir dados do banco de dados 
     * @param curso
     */
    public void excluir( Curso curso ) throws SQLException
    {
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "DELETE FROM public.curso WHERE curs_cod = ?";

        ps = conn.prepareStatement(sql);  
        
        ps.setLong(1, curso.getCodigo());    // Parâmetro é igual a um, pois ele só tem uma INTERROGAÇÃO
      
        ps.execute();   // Executa ou commit o código SQL

        conn = Conexao.fecharConexao();
    }
    
}
