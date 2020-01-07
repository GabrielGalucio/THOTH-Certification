package thoth.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thoth.modelo.bean.Localizacao;
import thoth.modelo.dao.LocalizacaoDao;

/**
 *
 * @author moise
 */
@WebServlet(name = "Localizacao_Cadastro_Controle", urlPatterns = {"/Localizacao_Cadastro_Controle"})
public class Localizacao_Cadastro_Controle extends HttpServlet {

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
            out.println("<title>Servlet Localizacao_Cadastro_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Localizacao_Cadastro_Controle at " + request.getContextPath() + "</h1>");
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
       
       Localizacao local = new Localizacao();
       LocalizacaoDao localDao = new LocalizacaoDao();
       
       String nome = request.getParameter("cx_nome");
       int qtde_vaga = Integer.parseInt( request.getParameter("cx_qtde_vaga") );
       String cep = request.getParameter("cx_cep");
       short numLocal = Short.valueOf(request.getParameter("cx_numero_local") );
       String logradouro = request.getParameter("cx_logradouro");
       String bairro = request.getParameter("cx_bairro");
       
       local.setNome(nome);
       local.setQtdeVaga(qtde_vaga);
       local.setCep(cep);
       local.setNumero(numLocal);
       local.setLogradouro(logradouro);
       local.setBairro(bairro);
       
        try {
            localDao.salvar(local);
            
            request.setAttribute("mensagem", "Local: "+local.getNome()+" Cadastrado com Sucesso !!!");
            System.out.println("Local Cadastrado com Sucesso !!!"); 
 
        } catch (SQLException ex) {
            System.out.println("ERRO AO SALVAR LOCALIZAÇÃO - CONTROLE LOCALIZAÇÃO, ERRO: "+ex);
        }
        
        request.getRequestDispatcher("TelaLocalizacao.jsp").forward(request, response);

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
