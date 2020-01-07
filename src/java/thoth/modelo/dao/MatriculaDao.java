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
import thoth.modelo.bean.Localizacao;
import thoth.modelo.bean.Matricula;
import thoth.modelo.bean.Professor;
import thoth.modelo.conexao.Conexao;

public class MatriculaDao {
    private Connection conn = null;                    // Prepara a conexão com o banco de Dados
    private PreparedStatement ps   = null;			   // Prepara o ambiente ou caminho para a banco de dados 
    private ResultSet rs   = null;					   // Envia as funções sql da aplicação para o banco
    private String sql  = "";
    private List<Matricula> listaMatricula = new ArrayList<>();
   
    /**
     * MÉTODO RESPONSÁVEL POR REALIZAR A MATRICULA
     * @param matricula 
     */
    public void salvar( Matricula matricula ){
        
        conn = Conexao.getConexao();      // Abri a conexão

        sql = "INSERT INTO public.matricula(mat_alu_cod, mat_curs_cod, mat_transacao_pagamento)VALUES (?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);   
            ps.setLong(1, matricula.getAluno().getCodigo());
            ps.setLong(2, matricula.getCurso().getCodigo());
            ps.setString(3, matricula.getTransacaoPagamento());
            ps.execute();   // Executa ou commit o código SQL

        } catch (SQLException ex) {
            System.out.println("ERRO AO REALIZAR, REMOVER OU ALTERAR MATRICULA, MTOIVO: "+ex);
        }

        conn = Conexao.fecharConexao();
        
    }
    
    /**
     * MÉTODO RESPONSÁVEL POR EXIBIR O NÚMERO DE MATRICULA REALIZADAS POR CURSO
     * @param cd_curso
     * @return 
     */
    public short numeroMatriculaPorCurso( long cd_curso ){
        
        conn = Conexao.getConexao();
        	
	sql = "SELECT * FROM matricula WHERE mat_curs_cod = ?";
        
        short qtde = 0;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cd_curso);
            rs = ps.executeQuery();

            while(rs.next()){
                qtde++;
            }
            
             ps.close();
            rs.close();
        
        } catch (SQLException ex) {
            System.out.println("ERRO AO PESQUISAR CURSO, MOTIVO: "+ex);
        }
        
        conn = Conexao.fecharConexao();

       
        return qtde;  
    }
    
    /**
     * ESTE MÉTODO É RESPONSÁVEL POR LISTAR OS ALUNOS MATRICULADOS NO CURSO 
     * COM BASE EM SEU CÓDIGO INFORMADO.
     * @param cd_curso
     * @return 
     */
    public List<Matricula> listarMatricula( long cd_curso )
    {
        
        conn = Conexao.getConexao();        
         
	sql = "SELECT c.*, u.*, p.*, l.*, m.*, ua.usu_cod as usu_cod_aluno , "
                + "ua.usu_nome as usu_nome_aluno, ua.usu_email as usu_email_aluno, \n" +
"	ua.usu_senha as usu_senha_aluno, ua.usu_status as usu_status_aluno, ua.usu_fone as usu_fone_aluno, a.alu_num_matricula\n" +
"                	FROM public.curso c\n" +
"                		JOIN localizacao l	ON l.local_cod = c.curs_localizacao\n" +
"                		JOIN usuario u		ON u.usu_cod   = c.curs_professor\n" +
"                		JOIN professor p	ON p.usu_cod   = u.usu_cod\n" +
"				JOIN matricula m	ON m.mat_curs_cod = c.curs_cod\n" +
"				JOIN aluno a		ON a.usu_cod   = m.mat_alu_cod\n" +
"				JOIN usuario ua		ON ua.usu_cod   = a.usu_cod\n" +
"           WHERE c.curs_cod = ? ORDER BY ua.usu_nome;";
	
        Curso curso  = null;
        Localizacao local = null;
        Professor professor = null;
	Matricula matricula = null;
        Aluno aluno = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cd_curso);
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

                aluno.setCodigo( rs.getLong("usu_cod_aluno") );
                aluno.setNome( rs.getString("usu_nome_aluno") );
                aluno.setEmail( rs.getString("usu_email_aluno") );
                aluno.setSenha( rs.getString("usu_senha_aluno") );
                aluno.setStatus( rs.getString("usu_status_aluno") );
                aluno.setFone( rs.getString("usu_fone_aluno") );
                aluno.setNumMatricula( rs.getLong("alu_num_matricula") );

                matricula.setCodigo( rs.getLong("mat_cod") );
                matricula.setValor( rs.getDouble("mat_valor") );
                matricula.setData( rs.getDate("mat_data_dt_mat") );
                matricula.setHora( rs.getTime("mat_data_hr_mat") );
                matricula.setAluno(aluno);
                matricula.setCurso(curso);
                matricula.setTransacaoPagamento( rs.getString("mat_transacao_pagamento") );

                listaMatricula.add(matricula);
            }

            conn = Conexao.fecharConexao();

            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("ERRO AO LISTAR MATRICULA, MOTIVO: "+ex);
        }
                
        return listaMatricula;
    }
}
