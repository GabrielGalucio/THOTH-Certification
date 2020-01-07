package thoth.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thoth.modelo.bean.Curso;
import thoth.modelo.bean.Funcionario;
import thoth.modelo.bean.Localizacao;
import thoth.modelo.bean.PeriodoCurso;
import thoth.modelo.bean.Professor;
import thoth.modelo.dao.CursoDao;
import thoth.modelo.dao.FuncionarioDao;
import thoth.modelo.dao.LocalizacaoDao;
import thoth.modelo.dao.PeriodoCursoDao;
import thoth.modelo.dao.ProfessorDao;

/**
 *
 * @author moise
 */
@WebServlet(name = "Curso_Alterar_Controle", urlPatterns = {"/Curso_Alterar_Controle"})
public class Curso_Alterar_Controle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Curso_Alterar_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Curso_Alterar_Controle at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
       Curso curso = new Curso();
       CursoDao cursoDao = new CursoDao();
       
       long cd_curso = Long.parseLong( request.getParameter("id") );
       
       curso = cursoDao.pesquisarCurso(cd_curso);
       
       if( curso != null ){
           
           try {
               cursoDao.excluir(curso);
           } catch (SQLException ex) {
               System.out.println("ERRO AO EXCLUIR, MOTIVO: "+ex);
           }
       }else{
           System.out.println("CURSO NÃO ENCONTRADO, NO CONTROLE");
       }
       
       request.getRequestDispatcher("TelaPerfilProfessor.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
       
       Curso curso = new Curso();
       CursoDao cursoDao = new CursoDao();
       Localizacao local = new Localizacao();
       LocalizacaoDao localDao = new LocalizacaoDao();       
       Professor professor = new Professor();
       ProfessorDao profDao = new ProfessorDao();
       
       // DADOS DO CURSO CURSO
       request.setCharacterEncoding("UTF-8");
       
       int num_form = Integer.parseInt( request.getParameter("cx_form") );
       
        // EFETUARÁ AS ALTERAÇÕES DO PRIMEIRO FORM INTRODUÇÃO DA TELA ALTERAR CURSO
        switch (num_form) {
            case 1:
                {
                    long cd_curso = Long.parseLong( request.getParameter("cx_codigo") );
                    String titulo = request.getParameter("cx_titulo");
                    String objetivo = request.getParameter("txt_objetivo");
                    String metodologia = request.getParameter("txt_metodologia");
                    String ementa = request.getParameter("txt_ementa");
                    String bibliografia = request.getParameter("txt_bibliografia");
                    long cd_professor = Long.parseLong( request.getParameter("cd_prof") );
                    professor = profDao.pesquisarProfessor( cd_professor );
                    curso.setCodigo(cd_curso);
                    curso.setTitulo(titulo);
                    curso.setObjetivo(objetivo);
                    curso.setMetodologia(metodologia);
                    curso.setEmenta(ementa);
                    curso.setBibliografia(bibliografia);
                    curso.setProfessor(professor);
                    // --------------------------------------
                    System.out.println("Código: "+curso.getCodigo());
                    System.out.println("Título: "+curso.getTitulo());
                    System.out.println("Objetivo: "+curso.getObjetivo());
                    System.out.println("Metodologia: "+curso.getMetodologia());
                    System.out.println("Ementa: "+curso.getEmenta());
                    System.out.println("Bibliografia: "+curso.getBibliografia());
                    // --------------------------------------
                    try {
                        cursoDao.alterar(curso, 1);
                    } catch (SQLException ex) {
                        System.out.println("ERRO AO ALTERAR CURSO, MOTIVO: "+ex);
                    }
                    
                    request.getRequestDispatcher("TelaAlterarCurso.jsp?id="+curso.getCodigo()+"").forward(request, response);
                    
                    break;
                }
            case 2: // EFETUARÁ AS ALTERAÇÕES DO SEGUNDO FORM CARACTERÍSTICAS DA TELA ALTERAR CURSO
                {
                    String area = request.getParameter("lista_area");
                    String evento = request.getParameter("lista_evento");
                    String status = request.getParameter("cx_status");
                    long cd_local = Long.parseLong( request.getParameter("lista_local") );
                    short qtdeVaga = Short.parseShort( request.getParameter("cx_qtdeVaga") );
                    double valor = Double.parseDouble( request.getParameter("cx_Valor") );
                    short cargaHoraria = Short.parseShort( request.getParameter("cx_cargaHoraria") );
                    long cd_curso = Long.parseLong( request.getParameter("cx_codigo") );
                    long cd_professor = Long.parseLong( request.getParameter("cd_prof") );
                    local = localDao.pesquisarLocalizacao(cd_local);
                    professor = profDao.pesquisarProfessor( cd_professor );
                    curso.setCodigo(cd_curso);
                    curso.setArea(area);
                    curso.setEvento(evento);
                    curso.setLocal(local);
                    curso.setQtdeVaga(qtdeVaga);
                    curso.setValor(valor);
                    curso.setStatus(status);
                    curso.setCargaHr(cargaHoraria);
                    curso.setProfessor(professor);
                    System.out.println("Código: "+curso.getCodigo());
                    System.out.println("Area: "+curso.getArea());
                    System.out.println("Evento: "+curso.getEvento());
                    System.out.println("Localização: "+curso.getLocal());
                    System.out.println("Quant. de Vagas: "+curso.getQtdeVaga());
                    System.out.println("Valor R$: "+curso.getValor());
                    System.out.println("Carga horária: "+curso.getCargaHr());
                    System.out.println("Professor: "+curso.getProfessor().getNome());
                    try {
                        cursoDao.alterar(curso, 2);
                    } catch (SQLException ex) {
                        System.out.println("ERRO AO ALTERAR CURSO, MOTIVO: "+ex);
                    }
                    
                    request.getRequestDispatcher("TelaAlterarCurso.jsp?id="+curso.getCodigo()+"").forward(request, response);
                    break;
                }
            case 3:
                // SE ESSA CONDIÇÃO FOR VERDADEIRA O FUNCIONÁRIO PODERÁ ALTERAR O STATUS DO CURSO.
                
                FuncionarioDao funDao = new FuncionarioDao();
                
                long cd_curso = Long.parseLong( request.getParameter("cx_curso") );
                long cd_funcionario = Long.parseLong( request.getParameter("cd_funcionario") );
                
                Funcionario funcionario = funDao.pesquisarFuncionario( cd_funcionario );
                curso = cursoDao.pesquisarCurso(cd_curso);
                
                // RESULTADO SELECIONADO PELO FUNCIONÁRIO AO ANALISAR O CURSO
                String resultado_parecer_funcionario = request.getParameter("parecer");
                
                /**
                 * VERIFICA QUAL OPÇÃO E O FUNCIONÁRIO SELECIONOU E ATRIBUI AO STATUS DO CURSO.
                 */
                if( resultado_parecer_funcionario.equals("ABERTO") ){
                    curso.setStatus("A");
                }else if( resultado_parecer_funcionario.equals("CANCELADO") ){
                    curso.setStatus("C");
                }else if( resultado_parecer_funcionario.equals("ENCERRADO") ){
                    curso.setStatus("E");
                }else if( resultado_parecer_funcionario.equals("PENDENTE") ){
                    curso.setStatus("P");
                }else if( resultado_parecer_funcionario.equals("REPROVADO") ){
                    curso.setStatus("R");
                }
                
                curso.setFuncionario(funcionario);
                
                try {
                    cursoDao.alterar(curso, 3);
                } catch (SQLException ex) {
                    System.out.println("ERRO AO ALTERAR CURSO, MOTIVO: "+ex);
                }
                
                request.setAttribute("mensagem", "ANÁLISE DO CURSO ENVIADA !!!");
                request.getRequestDispatcher("TelaAnalisarCurso.jsp?src_curso="+curso.getCodigo()+"").forward(request, response);
                break;
            default:
                break;
        }
                
        request.getRequestDispatcher("TelaAlterarCurso.jsp?id="+curso.getCodigo()+"").forward(request, response); 
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
