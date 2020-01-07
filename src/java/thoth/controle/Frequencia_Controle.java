package thoth.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thoth.modelo.bean.Frequencia;
import thoth.modelo.dao.FrequenciaDao;

/**
 *
 * @author moise
 */
@WebServlet(name = "Frequencia_Controle", urlPatterns = {"/Frequencia_Controle"})
public class Frequencia_Controle extends HttpServlet {

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
            out.println("<title>Servlet Frequencia_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Frequencia_Controle at " + request.getContextPath() + "</h1>");
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
       
       long cd_frequencia = Long.parseLong( request.getParameter("src_freqAluno") );
       String freq =  request.getParameter("src_freqVl");
       
       Frequencia f = new Frequencia();
       FrequenciaDao fDao = new FrequenciaDao();
       
        System.out.println("Valores passados: "+cd_frequencia+" - resultado: "+freq);
       
       f = fDao.pesquisarFrequencia( cd_frequencia );
       
       if( f != null ){
           
           f.getCodigo();
           f.getData_frequencia();
           f.getMatricula();
           
           if(  freq.equals("P")  ){
               
               f.setStatus("F");
               System.out.println("Valores passados: "+cd_frequencia+" - resultado ALterado para: "+f.getStatus());
           }else if( freq.equals("F")  ){
               
               f.setStatus("P");
               
           }
           
           fDao.alterar(f);
           
       }else{
           System.out.println("FREQUÊNCIA NÃO ENCONTRADA.");
       }
       
       request.getRequestDispatcher("TelaMatriculasCurso.jsp?cd_curso="+f.getMatricula().getCurso().getCodigo()).forward(request, response);
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
