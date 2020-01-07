package thoth.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thoth.modelo.bean.Aluno;
import thoth.modelo.bean.Usuario;
import thoth.modelo.dao.AlunoDao;
import thoth.modelo.dao.UsuarioDao;

/**
 *
 * @author moise
 */
@WebServlet(name = "Aluno_Alterar_Controle", urlPatterns = {"/Aluno_Alterar_Controle"})
public class Aluno_Alterar_Controle extends HttpServlet {

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
            out.println("<title>Servlet Aluno_Alterar_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Aluno_Alterar_Controle at " + request.getContextPath() + "</h1>");
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
       
       Usuario usuario = new Usuario();
        Usuario usupesquisa = new Usuario();
        UsuarioDao usuDao = new UsuarioDao();
        Aluno aluno = new Aluno();
        
        request.setCharacterEncoding("UTF-8");
        
        long cod = Long.parseLong( request.getParameter("cx_cod_aluno") );
        long num_mat = Long.parseLong( request.getParameter("cx_mat_aluno") );
        String nome = request.getParameter("cx_nome_aluno");
        String email = request.getParameter("cx_email_aluno");
        String senha = request.getParameter("cx_senha_aluno");
        String status = request.getParameter("status_conta");
        String fone = request.getParameter("cx_fone_aluno");
        
        usuario.setCodigo(cod);
        usuario.setNome(nome.toUpperCase());
        usuario.setEmail(email.toUpperCase());
        usuario.setSenha(senha);
        usuario.setFone(fone);
        usuario.setStatus(status);
        
        try {
            
            usupesquisa = usuDao.pesquisarUsuario(cod);  // PEGA O OBJETO JÁ CADASTRADO PARA COMPARAR ANTES DE ALTERAR
             
             if( usupesquisa.getEmail().toUpperCase().equals( usuario.getEmail() ) ){
                 
                usuDao.alterar(usuario);
                aluno.setCodigo(cod);                
                aluno.setNumMatricula(num_mat);

                request.setAttribute("mensagem", "Dados da Conta: "+usuario.getEmail()+" Alterado com Sucesso !!!");
             
             }else if(usuDao.identificarUsuaricoCadastrado(email)){
               
                 request.setAttribute("mensagem", "Usuário: "+usuario.getEmail()+" já existe !!");
                
            }else{
                 
                usuDao.alterar(usuario);
                usuDao.alterar(usuario);
                aluno.setCodigo(cod);                
                aluno.setNumMatricula(num_mat); 

                request.setAttribute("mensagem", "Dados da Conta: "+usuario.getEmail()+" Alterado com Sucesso !!!");
                 
             }
             
        } catch (SQLException ex) {
            System.out.println("Erro ao Alterar o PROFESSOR, Motivo: "+ex);
        }
        
     //   response.sendRedirect("TelaAlterarProfessor.jsp");
       request.getRequestDispatcher("TelaAlterarAluno.jsp").forward(request, response);

       
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
