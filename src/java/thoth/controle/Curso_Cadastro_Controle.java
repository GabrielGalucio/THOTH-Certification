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
import thoth.modelo.bean.Localizacao;
import thoth.modelo.bean.PeriodoCurso;
import thoth.modelo.bean.Professor;
import thoth.modelo.dao.CursoDao;
import thoth.modelo.dao.LocalizacaoDao;
import thoth.modelo.dao.PeriodoCursoDao;
import thoth.modelo.dao.ProfessorDao;

/**
 *
 * @author moise
 */
@WebServlet(name = "Curso_Cadastro_Controle", urlPatterns = {"/Curso_Cadastro_Controle"})
public class Curso_Cadastro_Controle extends HttpServlet {

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
            out.println("<title>Servlet Curso_Cadastro_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Curso_Cadastro_Controle at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
       PeriodoCurso periodo = new PeriodoCurso();
       PeriodoCursoDao periodoDao = new PeriodoCursoDao();
       
       // DADOS DO CURSO CURSO
       request.setCharacterEncoding("UTF-8");
       
       String titulo = request.getParameter("cx_titulo");
       String objetivo = request.getParameter("txt_objetivo");
       String metodologia = request.getParameter("txt_metodologia");
       String ementa = request.getParameter("txt_ementa");
       String bibliografia = request.getParameter("txt_bibliografia");
       String area = request.getParameter("lista_area");
       String evento = request.getParameter("lista_evento");
       long cd_local = Long.parseLong( request.getParameter("lista_local") );
       short qtdeVaga = Short.parseShort( request.getParameter("cx_qtdeVaga") );
       double valor = Double.parseDouble( request.getParameter("cx_Valor") );
       short cargaHoraria = Short.parseShort( request.getParameter("cx_cargaHoraria") );       
       String data_periodo = request.getParameter("lista_periodo_array");
       long cd_professor = Long.parseLong( request.getParameter("cx_prof") );
        local = localDao.pesquisarLocalizacao(cd_local);        
        professor = profDao.pesquisarProfessor( cd_professor );

        curso.setTitulo(titulo);
        curso.setObjetivo(objetivo);
        curso.setMetodologia(metodologia);
        curso.setEmenta(ementa);
        curso.setBibliografia(bibliografia);
        curso.setArea(area);
        curso.setEvento(evento);
        curso.setLocal(local);
        curso.setQtdeVaga(qtdeVaga);
        curso.setValor(valor);
        curso.setQtdeVaga(qtdeVaga);
        curso.setCargaHr(cargaHoraria);
        curso.setProfessor(professor);
        
        try {
            cursoDao.salvar(curso);
        } catch (SQLException ex) {
            System.out.println("ERRO AO SALVAR CURSO, MOTIVO: "+ex);
        } 
        
        String dt_periodos[] = data_periodo.split( Pattern.quote(",") ); // DIVIDE AS DATAS E HORAS VINDAS DA PÁGINA JSP
        Curso c = cursoDao.ultimoCursoInserido();  // RETORNA O ÚLTIMO CURSO INSERIDO PARA VINCULAR O PERIODO.
        
        /**
         *   ESTE LAÇO FOR LISTARÁ TODAS AS DATAS E SUAS RESPECTIVOS HORAS.
         */
        for( int i = 0; i < dt_periodos.length; i++ ){
            
            /**
             *  O BLOCO ABAIXO É O RESPONSÁVEL POR DIVIDIR A DATA, HORA INICIAL E HORA TÉRMINO DA ARRAY
             */
            String d1 = dt_periodos[i].substring(1, 11);            
            String h1  = dt_periodos[i].substring(13, 18);
            String h2 = dt_periodos[i].substring(20, 25);
            
         //   SimpleDateFormat dt_inicial = new SimpleDateFormat("dd/MM/yyyy"); // FORMATADOR DA DATA
            SimpleDateFormat dt_inicial = new SimpleDateFormat("yyyy-MM-dd"); // FORMATADOR DA DATA
            SimpleDateFormat hora = new SimpleDateFormat("HH:mm");            // FORMATADOR DA HORA
           
            try {
                Date dataInicio = dt_inicial.parse( d1 );  // CONVERSÃO DA DATA DO TIPO STRING PARA O TIPO DATE UTIL
                Date hr_1 = hora.parse( h1 );              // CONVERSÃO DA HORA INICIAL DO TIPO STRING PARA O TIPO DATE UTIL
                Date hr_2 = hora.parse( h2 );              // CONVERSÃO DA HORA TÉRMINO DO TIPO STRING PARA O TIPO DATE UTIL
                
                Time horaIni = new Time( hr_1.getTime() );   // CONVERSÃO DA HORA INICIAL DO TIPO DATE UTIL PARA TIPO TIME
                Time horaTermino = new Time( hr_2.getTime() ); // CONVERSÃO DA HORA TERMINO DO TIPO DATE UTIL PARA TIPO TIME
                
                periodo.setDtInicio( dataInicio );
                periodo.setHrInicio(horaIni);
                periodo.setHrFinal(horaTermino);
                periodo.setCurso(c);
                
                periodoDao.salvar(periodo);
                
            } catch (ParseException ex) {
                System.out.println("Erro ao converter STRING para DATA, motivo: "+ex);
            }
                
            
        }
        
        request.getRequestDispatcher("TelaCriarCurso.jsp").forward(request, response); 
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
