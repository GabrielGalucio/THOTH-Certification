package thoth.controle;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import thoth.modelo.conexao.Conexao;

/**
 *
 * @author moise
 */
@WebServlet(name = "GerarCertificado", urlPatterns = {"/GerarCertificado"})
public class GerarCertificado extends HttpServlet {

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
            out.println("<title>Servlet GerarCertificado</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GerarCertificado at " + request.getContextPath() + "</h1>");
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
       
       long cod_aluno = Long.parseLong( request.getParameter("cx_aluno") );
       long cod_curso = Long.parseLong( request.getParameter("cx_curso") );
       
       Connection conn = Conexao.getConexao();
       JasperReport jasperReport = null;
       JasperDesign jasperDesign = null;
       
       HashMap parameters = new HashMap<>();
       parameters.put("codigo_aluno", cod_aluno);
       parameters.put("codigo_curso", cod_curso);
       
       String path = getServletContext().getRealPath("WEB-INF/");
       
        try {
            jasperDesign = JRXmlLoader.load(path + "/Certificado_v2.jrxml");
            
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            byte[] byteStram = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);
        //    byte[] byteStram = JasperRunManager.runReportToPdf(jasperReport, null, conn);
            OutputStream outStream = response.getOutputStream();
            response.setContentType("application/pdf");
            response.setContentLength( byteStram.length );
            outStream.write(byteStram, 0, byteStram.length);
        } catch (JRException ex) {
            System.out.println("ARQUIVO NÃO ENCONTRADO, MOTIVO: "+ex);
        }
       
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
        processRequest(request, response);
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
