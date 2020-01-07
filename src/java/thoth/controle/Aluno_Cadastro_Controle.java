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
import javax.servlet.http.HttpSession;
import thoth.modelo.bean.Aluno;
import thoth.modelo.bean.Usuario;
import thoth.modelo.dao.AlunoDao;
import thoth.modelo.dao.UsuarioDao;

/**
 *
 * @author estagiario.ti
 */
@WebServlet(name = "Aluno_Cadastro_Controle", urlPatterns = {"/Aluno_Cadastro_Controle"})
public class Aluno_Cadastro_Controle extends HttpServlet {

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
            out.println("<title>Servlet Aluno_Cadastro_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Aluno_Cadastro_Controle at " + request.getContextPath() + "</h1>");
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
        UsuarioDao usuDao = new UsuarioDao();
        Aluno aluno = new Aluno();
        AlunoDao aluDao = new AlunoDao();        
        HttpSession sessao = request.getSession();
        
        request.setCharacterEncoding("UTF-8");
       
        String nome = request.getParameter("cx_nome_aluno");
        String email = request.getParameter("cx_email_aluno");
        String senha = request.getParameter("cx_senha_aluno");
        String fone = request.getParameter("cx_fone_aluno");
      
        usuario.setNome(nome.toUpperCase());
        usuario.setEmail(email.toUpperCase());
        usuario.setSenha(senha);
        usuario.setFone(fone);

        try {
            
            if(usuDao.identificarUsuaricoCadastrado(email)){
               
                 request.setAttribute("mensagem", "Usuário: "+usuario.getEmail()+" já é Cadastrado !!");
                
            }else{
                  
                usuDao.salvar(usuario);

                System.out.println("Usuário Cadastrado com Sucesso !!!");

                // Pega o último objeto USUÁRIO e o vincula ao objeto ALUNO.
                long cd_usuario = usuDao.ultimoUsuarioCadastrado();

                aluno.setCodigo(cd_usuario);

                aluDao.salvar(aluno);

                request.setAttribute("mensagem", "Usuário: "+usuario.getNome()+" Cadastrado com Sucesso !!!");
                System.out.println("Usuário Cadastrado com Sucesso !!!"); 
                
                Aluno a = aluDao.pesquisarAluno();  // RETORNA O ÚLTIMO PROFESSOR CADASTRADO 
                sessao.setAttribute("aluno", a); 
                
              //  request.setAttribute("mensagem", "Registro: "+usuario.getNome()+" Cadastrado com Sucesso !!!");
                
                System.out.println("Professor Cadastrado com Sucesso !!!");
                request.getRequestDispatcher("TelaPerfilAluno.jsp").forward(request, response);
                
            }
            
        } catch (SQLException ex) {
            System.out.println("ERRO AO SALVAR, ALUNO, MOTIVO: "+ex);
        }
      
        request.getRequestDispatcher("TelaCadAluno.jsp").forward(request, response);
        
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
