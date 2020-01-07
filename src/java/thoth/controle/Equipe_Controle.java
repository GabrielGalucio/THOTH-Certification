package thoth.controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thoth.modelo.bean.Professor;
import thoth.modelo.dao.ProfessorDao;

/**
 *
 * @author moise
 */
@WebServlet(name = "Equipe_Controle", urlPatterns = {"/Equipe_Controle"})
public class Equipe_Controle extends HttpServlet {

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
            out.println("<title>Servlet Equipe_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Equipe_Controle at " + request.getContextPath() + "</h1>");
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
        
        ProfessorDao profDao = new ProfessorDao();
        
        request.setCharacterEncoding("UTF-8");
        
        long codigo = Long.parseLong( request.getParameter("ODIGJ2") );
        long cd_curso = Long.parseLong( request.getParameter("CR003") );
        profDao.removerEquipe(codigo);
        
        
        request.getRequestDispatcher("TelaEquipeProfessor.jsp?cd="+cd_curso+"").forward(request, response);
        
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
        
        ProfessorDao profDao = new ProfessorDao();
        
        request.setCharacterEncoding("UTF-8");
        
        long cd = Long.parseLong( request.getParameter("cx_cd_curso") );
        String nome = request.getParameter("cx_nome");
        
        profDao.adicionarEquipe(nome.toUpperCase(), cd);
        
        
        request.getRequestDispatcher("TelaEquipeProfessor.jsp?cd="+cd+"").forward(request, response);
        
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
