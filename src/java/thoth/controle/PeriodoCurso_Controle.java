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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thoth.modelo.bean.Curso;
import thoth.modelo.bean.PeriodoCurso;
import thoth.modelo.dao.CursoDao;
import thoth.modelo.dao.PeriodoCursoDao;

/**
 *
 * @author moise
 */
@WebServlet(name = "PeriodoCurso_Controle", urlPatterns = {"/PeriodoCurso_Controle"})
public class PeriodoCurso_Controle extends HttpServlet {

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
            out.println("<title>Servlet PeriodoCurso_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PeriodoCurso_Controle at " + request.getContextPath() + "</h1>");
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
      //  processRequest(request, response);
      
        System.out.println("COD PASSADO: "+request.getParameter("ODIGJ2"));
        
      long cd = Long.parseLong( request.getParameter("ODIGJ2") );
      long cd_curso = Long.parseLong( request.getParameter("srccurso") );;
      
      PeriodoCursoDao periodoDao = new PeriodoCursoDao();
      
      PeriodoCurso periodoCurso = periodoDao.pesquisar(cd);
      
        try {
                    
            System.out.println("Data: "+periodoCurso.getDtInicio());
            
            if( periodoCurso != null ){
                periodoDao.excluir(periodoCurso);
            }else{
                System.out.println("OBJETO PERIODO NO CONTROLE NÃO ENCONTRA, ASSIM NÃO PODE SALVAR.");
            }
            
        } catch (SQLException ex) {
            System.out.println("PERIODO NÃO PÔDE SER EXCLUÍDO, MOTIVO: "+ex);
        }
      
        request.getRequestDispatcher("TelaAlterarCurso.jsp?id="+cd_curso+"").forward(request, response);
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
       
       PeriodoCurso periodo = new PeriodoCurso();
       PeriodoCursoDao periodoDao = new PeriodoCursoDao();
       Curso c = new Curso();
       CursoDao cursoDao = new CursoDao();
       
       String data = request.getParameter("cx_data");
       String h1 = request.getParameter("hora_inicio");
       String h2 = request.getParameter("hora_termino");
       c = cursoDao.pesquisarCurso( Long.parseLong( request.getParameter("cd_curso") ) );
       
    //   SimpleDateFormat dt_inicial = new SimpleDateFormat("dd/MM/yyyy"); // FORMATADOR DA DATA
         SimpleDateFormat dt_inicial = new SimpleDateFormat("yyyy-MM-dd"); // FORMATADOR DA DATA
         SimpleDateFormat hora = new SimpleDateFormat("HH:mm");            // FORMATADOR DA HORA

         try {
             Date dataInicio = dt_inicial.parse( data );  // CONVERSÃO DA DATA DO TIPO STRING PARA O TIPO DATE UTIL
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

       request.getRequestDispatcher("TelaAlterarCurso.jsp?id="+c.getCodigo()+"").forward(request, response);
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
