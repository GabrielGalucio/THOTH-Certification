package thoth.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "Localizacao_Alterar_Controle", urlPatterns = {"/Localizacao_Alterar_Controle"})
public class Localizacao_Alterar_Controle extends HttpServlet {

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
            out.println("<title>Servlet Localizacao_Alterar_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Localizacao_Alterar_Controle at " + request.getContextPath() + "</h1>");
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
       
       request.setCharacterEncoding("UTF-8");
       
       long codigo = Long.parseLong( request.getParameter("cx_codigo") );
       String nome = request.getParameter("cx_nome");
       int qtde_vaga = Integer.parseInt( request.getParameter("cx_qtde_vaga") );
       String cep = request.getParameter("cx_cep");
       short numLocal = Short.valueOf(request.getParameter("cx_numero_local") );
       String logradouro = request.getParameter("cx_logradouro");
       String bairro = request.getParameter("cx_bairro");
       
       local.setCodigo(codigo);
       local.setNome(nome);
       local.setQtdeVaga(qtde_vaga);
       local.setCep(cep);
       local.setNumero(numLocal);
       local.setLogradouro(logradouro);
       local.setBairro(bairro);
       
        try {
            localDao.alterar(local);
            
            request.setAttribute("mensagem", "Local: "+local.getNome()+" Alterado com Sucesso !!!");
            System.out.println("Local Cadastrado com Sucesso !!!"); 
 
        } catch (SQLException ex) {
            System.out.println("ERRO AO ALTERAR LOCALIZAÇÃO - CONTROLE LOCALIZAÇÃO, ERRO: "+ex);
        }
        
        request.getRequestDispatcher("TelaLocalizacaoAlterar.jsp?id="+local.getCodigo()).forward(request, response);
        
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
