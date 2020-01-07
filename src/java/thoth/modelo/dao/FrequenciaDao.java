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
import thoth.modelo.bean.Curso;
import thoth.modelo.bean.Frequencia;
import thoth.modelo.bean.Localizacao;
import thoth.modelo.bean.Matricula;
import thoth.modelo.bean.Professor;
import thoth.modelo.conexao.Conexao;

public class FrequenciaDao {
    private Connection conn = null;                    // Prepara a conexão com o banco de Dados
    private PreparedStatement ps   = null;			   // Prepara o ambiente ou caminho para a banco de dados 
    private ResultSet rs   = null;					   // Envia as funções sql da aplicação para o banco
    private String sql  = "";
    private List<Frequencia> listaFrequencia = new ArrayList<>();
    
    public Frequencia pesquisarFrequencia( long cd_aluno ) 
    {
        
        conn = Conexao.getConexao();        
         
	sql = "SELECT distinct c.*, u.*, p.*, l.*, m.*, ua.usu_cod as usu_cod_aluno, ua.usu_nome as usu_nome_aluno, ua.usu_email as usu_email_aluno, \n" +
"		ua.usu_senha as usu_senha_aluno, ua.usu_status as usu_status_aluno, ua.usu_fone as usu_fone_aluno, \n" +
"		a.alu_num_matricula as alu_matricuala_aluno, fr.*\n" +
"                	FROM public.curso c\n" +
"                		JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
"                		JOIN professor p	ON p.usu_cod   = c.curs_professor \n" +
"                		JOIN usuario u		ON u.usu_cod   = p.usu_cod\n" +
"				JOIN matricula m	ON m.mat_curs_cod = c.curs_cod\n" +
"				JOIN aluno a		ON a.usu_cod   = m.mat_alu_cod	\n" +
"				JOIN usuario ua		ON ua.usu_cod   = a.usu_cod\n" +
"				JOIN frequencia fr	ON m.mat_cod = fr.fr_mat_cod\n" +
"           WHERE fr.fr_cod = ? GROUP BY c.curs_cod, u.usu_cod, p.usu_cod, l.local_cod, m.mat_cod, \n" +
"                ua.usu_cod, fr.fr_cod, a.usu_cod ORDER BY fr.fr_data ASC";
	
        Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
	Matricula matricula = null;
        Aluno aluno = null;
	Frequencia frequencia = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cd_aluno);
            rs = ps.executeQuery();

            if(rs.next()){

                curso = new Curso();
                local = new Localizacao();
                professor = new Professor();
                matricula = new Matricula();
                aluno = new Aluno();
                frequencia = new Frequencia();

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

                aluno.setCodigo( rs.getLong("usu_cod_aluno") );
                aluno.setNome( rs.getString("usu_nome_aluno") );
                aluno.setEmail( rs.getString("usu_email_aluno") );
                aluno.setSenha( rs.getString("usu_senha_aluno") );
                aluno.setStatus( rs.getString("usu_status_aluno") );
                aluno.setFone( rs.getString("usu_fone_aluno") );
                aluno.setNumMatricula( rs.getLong("alu_matricuala_aluno") );

                matricula.setCodigo( rs.getLong("mat_cod") );
                matricula.setValor( rs.getDouble("mat_valor") );
                matricula.setData( rs.getDate("mat_data_dt_mat") );
                matricula.setHora( rs.getTime("mat_data_hr_mat") );
                matricula.setAluno(aluno);
                matricula.setCurso(curso);
                matricula.setTransacaoPagamento( rs.getString("mat_transacao_pagamento") );

                frequencia.setCodigo( rs.getLong("fr_cod") );
                frequencia.setStatus( rs.getString("fr_status") );
                frequencia.setMatricula(matricula);
                frequencia.setData_frequencia( rs.getDate("fr_data") );

            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO LISTAR FREQUÊNCIA");
        }
        	
	return frequencia;
        
    }
    
    /**
     * ESTE MÉTODO É O RESPONSÁVEL POR LISTAR A LISTA DE FREQUÊNCIA DO ALUNO
     * @param cd_aluno
     * @return 
     */
    public List<Frequencia> listarFrequencia( long cd_aluno ) 
    {
        
        conn = Conexao.getConexao();        
         
	sql = "SELECT distinct c.*, u.*, p.*, l.*, m.*, ua.*, a.*, fr.*\n" +
"                	FROM public.curso c\n" +
"                		JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
"                		JOIN professor p	ON p.usu_cod   = c.curs_professor \n" +
"                		JOIN usuario u		ON u.usu_cod   = p.usu_cod\n" +
"				JOIN matricula m	ON m.mat_curs_cod = c.curs_cod\n" +
"				JOIN aluno a		ON a.usu_cod   = m.mat_alu_cod	\n" +
"				JOIN usuario ua		ON ua.usu_cod   = a.usu_cod\n" +
"				JOIN frequencia fr	ON m.mat_cod = fr.fr_mat_cod\n" +
"           WHERE a.usu_cod = ? GROUP BY c.curs_cod, u.usu_cod, p.usu_cod, l.local_cod, m.mat_cod, "
                + "ua.usu_cod, fr.fr_cod, a.usu_cod ORDER BY fr.fr_data ASC";
	
        Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
	Matricula matricula = null;
        Aluno aluno = null;
	Frequencia frequencia = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cd_aluno);
            rs = ps.executeQuery();

            while(rs.next()){

                curso = new Curso();
                local = new Localizacao();
                professor = new Professor();
                matricula = new Matricula();
                aluno = new Aluno();
                frequencia = new Frequencia();

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

                frequencia.setCodigo( rs.getLong("fr_cod") );
                frequencia.setStatus( rs.getString("fr_status") );
                frequencia.setMatricula(matricula);
                frequencia.setData_frequencia( rs.getDate("fr_data") );

                listaFrequencia.add(frequencia);
            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO LISTAR FREQUÊNCIA");
        }
        	
	return listaFrequencia;
        
    }
    
    /**
     * ESTE MÉTODO É RESPONSÁVEL POR LISTAR A FREQUÊNCIA DO ALUNO ATRAVÉS DO CURSO INFORMADO.
     * @param cd_aluno
     * @param cd_curso
     * @return 
     */
    public List<Frequencia> listarFrequencia( long cd_aluno, long cd_curso ) 
    {
        
        conn = Conexao.getConexao();        
         
	sql = "SELECT c.*, u.*, p.*, l.*, m.*, ua.usu_cod as usu_cod_aluno, ua.usu_nome as usu_nome_aluno, ua.usu_email as usu_email_aluno, \n" +
"	ua.usu_senha as usu_senha_aluno, ua.usu_status as usu_status_aluno, ua.usu_fone as usu_fone_aluno , a.alu_num_matricula as alu_num_matricula_aluno, fr.*\n" +
"                	FROM public.curso c\n" +
"                		JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
"                		JOIN professor p	ON p.usu_cod   = c.curs_professor\n" +
"                		JOIN usuario u		ON u.usu_cod   = p.usu_cod\n" +
"				JOIN matricula m	ON m.mat_curs_cod = c.curs_cod\n" +
"				JOIN aluno a		ON a.usu_cod   = m.mat_alu_cod\n" +
"				JOIN usuario ua		ON ua.usu_cod   = a.usu_cod\n" +
"				JOIN frequencia fr	ON m.mat_cod = fr.fr_mat_cod\n" +
"           WHERE a.usu_cod = ? AND c.curs_cod = ? ORDER BY (ua.usu_nome, fr.fr_data)";
	
        Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
	Matricula matricula = null;
        Aluno aluno = null;
	Frequencia frequencia = null;
        List<Frequencia> listaFrequencia1 = new ArrayList<>();
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cd_aluno);
            ps.setLong(2, cd_curso);
            rs = ps.executeQuery();

            while(rs.next()){

                curso = new Curso();
                local = new Localizacao();
                professor = new Professor();
                matricula = new Matricula();
                aluno = new Aluno();
                frequencia = new Frequencia();

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

                matricula.setCodigo( rs.getLong("mat_cod") );
                matricula.setValor( rs.getDouble("mat_valor") );
                matricula.setData( rs.getDate("mat_data_dt_mat") );
                matricula.setHora( rs.getTime("mat_data_hr_mat") );
                matricula.setAluno(aluno);
                matricula.setCurso(curso);
                matricula.setTransacaoPagamento( rs.getString("mat_transacao_pagamento") );
                
                aluno.setCodigo( rs.getLong("usu_cod_aluno") );
                aluno.setNome( rs.getString("usu_nome_aluno") );
                aluno.setEmail( rs.getString("usu_email_aluno") );
                aluno.setSenha( rs.getString("usu_senha_aluno") );
                aluno.setStatus( rs.getString("usu_status_aluno") );
                aluno.setFone( rs.getString("usu_fone_aluno") );
                aluno.setNumMatricula( rs.getLong("alu_num_matricula_aluno") );

                frequencia.setCodigo( rs.getLong("fr_cod") );
                frequencia.setStatus( rs.getString("fr_status") );
                frequencia.setMatricula(matricula);
                frequencia.setData_frequencia( rs.getDate("fr_data") );

                listaFrequencia1.add(frequencia);
            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO LISTAR FREQUÊNCIA,MOTIVO: "+ex);
        }
        	
	return listaFrequencia1;
        
    }
  
    /**
     * RETORNA O PERCENTUAL DE FREQUENCIA DO ALUNO NO CURSO, SE FOR VERDADEIRO, PASSOU,
     * CASO CONTRÁRIO, NÃO PASSOU.
     * @param cd_aluno
     * @param cd_curso
     * @return 
     */
    public boolean percentualFrequencia( long cd_aluno, long cd_curso )
    {
        boolean situacao = true;
        
        conn = Conexao.getConexao();        
         
	sql = "SELECT \n" +
"	(100 * count(fr.*))/\n" +
"	(\n" +
"		SELECT count(*)\n" +
"                	FROM public.curso c\n" +
"                		JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
"                		JOIN professor p	ON p.usu_cod   = c.curs_professor\n" +
"                		JOIN usuario u		ON u.usu_cod   = p.usu_cod\n" +
"				JOIN matricula m	ON m.mat_curs_cod = c.curs_cod\n" +
"				JOIN aluno a		ON a.usu_cod   = m.mat_alu_cod\n" +
"				JOIN usuario ua		ON ua.usu_cod   = a.usu_cod\n" +
"				JOIN frequencia fr	ON m.mat_cod = fr.fr_mat_cod\n" +
"           WHERE a.usu_cod = ? AND c.curs_cod = ? GROUP BY a.usu_cod\n" +
"	) \"percentual_presencao\"\n" +
"                	FROM public.curso c\n" +
"                		JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
"                		JOIN professor p	ON p.usu_cod   = c.curs_professor\n" +
"                		JOIN usuario u		ON u.usu_cod   = p.usu_cod\n" +
"				JOIN matricula m	ON m.mat_curs_cod = c.curs_cod\n" +
"				JOIN aluno a		ON a.usu_cod   = m.mat_alu_cod\n" +
"				JOIN usuario ua		ON ua.usu_cod   = a.usu_cod\n" +
"				JOIN frequencia fr	ON m.mat_cod = fr.fr_mat_cod\n" +
"           WHERE a.usu_cod = ? AND c.curs_cod = ? AND fr.fr_status = 'P' GROUP BY fr.fr_status ORDER BY fr.fr_status ASC";
        
        long resultado = 0;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cd_aluno);
            ps.setLong(2, cd_curso);
            ps.setLong(3, cd_aluno);
            ps.setLong(4, cd_curso);
            rs = ps.executeQuery();
            
            if(rs.next()){
                resultado = rs.getLong("percentual_presencao");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FrequenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if( resultado <= 95 ){
            situacao = false;
        }
        
        return situacao;
    }
    
    /**
     * ESTE MÉTODO É RESPONSÁVEL POR ALTERAR A FREQUÊNCIA DO ALUNO.
     * @param frequencia 
     */
    public void alterar( Frequencia frequencia )
    {
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "UPDATE frequencia SET fr_status = ? WHERE fr_cod = ?";

        try {  
            ps = conn.prepareStatement(sql);
            ps.setString(1, frequencia.getStatus() );
            ps.setLong(2, frequencia.getCodigo());

            ps.executeUpdate();   // Executa ou commit o código SQL

        } catch (SQLException ex) {
            System.out.println("Não foi possível alterar, motivo: "+ex);
        }
        
        
        conn = Conexao.fecharConexao();
    }
    
}
