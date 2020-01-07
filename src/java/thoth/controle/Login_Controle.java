package thoth.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thoth.modelo.bean.Aluno;
import thoth.modelo.bean.Funcionario;
import thoth.modelo.bean.Professor;
import thoth.modelo.bean.Usuario;
import thoth.modelo.dao.AlunoDao;
import thoth.modelo.dao.FuncionarioDao;
import thoth.modelo.dao.ProfessorDao;
import thoth.modelo.dao.UsuarioDao;

/**
 *
 * @author moise
 */
@WebServlet(name = "Login_Controle", urlPatterns = {"/Login_Controle"})
public class Login_Controle extends HttpServlet {

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
            out.println("<title>Servlet Login_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login_Controle at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        
        Usuario usuario = new Usuario();
        UsuarioDao usuDao = new UsuarioDao();
        Aluno aluno = new Aluno();
        AlunoDao aluDao = new AlunoDao();
        Funcionario funcionario = new Funcionario();
        FuncionarioDao funDao = new FuncionarioDao();
        Professor professor = new Professor();
        ProfessorDao profDao = new ProfessorDao();
        
        HttpSession sessao = request.getSession();
        
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("inputEmail");
        String senha = request.getParameter("inputPassword");
        
        System.out.println("O email informado é: "+email);
        System.out.println("A senha informada é: "+senha);
        
        try {
            
            /**
             * Verifica se existe o email cadastrado no sistema
             */
            if(usuDao.identificarUsuaricoCadastrado(email)){
                
                // Se entra existe
                                
                usuario = usuDao.realizarLogin(email, senha);
                
                if( usuario != null ){
                    // Usuário encontrado
                    
                    /**
                     * BLOCO DE CONDIÇÕES DE USUÁRIOS POR PERFIS
                     */
                    aluno = aluDao.pesquisarAluno( usuario.getCodigo() ); // VERIFICA SE O USUÁRIO É O ALUNO
                    professor = profDao.pesquisarProfessor( usuario.getCodigo() ); // VERIFICA SE O USUÁRIO É PROFESSOR
                    funcionario = funDao.pesquisarFuncionario( usuario.getCodigo() ); // VERIFICA SE O USUÁRIO É FUNCIONÁRIO
                    
                    if( aluno != null ){
                        // O USUÁRIO É ALUNO
                        
                        sessao.setAttribute("aluno", aluno);
                           // request.setAttribute("mensagem", "Usuário encontrado é FUNCIONÁRIO PRODUÇÃO");                
                        request.getRequestDispatcher("TelaPerfilAluno.jsp").forward(request, response);
                        
                    }else if( professor != null ){
                        // O USUÁRIO É PROFESSOR
                        
                        sessao.setAttribute("professor", professor);
                        
                    //    request.setAttribute("professor", professor);
                 //       request.setAttribute("mensagem", "Usuário encontrado é PROFESSOR");                
                        request.getRequestDispatcher("TelaPerfilProfessor.jsp").forward(request, response);
                        
                    }else if( funcionario != null ){
                        // O USUÁRIO É FUNCIONÁRIO
                        
                        if( funcionario.getTipo().equals("PRODUÇÃO") ){
                            // O USUÁRIO É FUNCIONÁRIO TIPO PRODUÇÃO
                            
                            sessao.setAttribute("funcionario", funcionario);
                           // request.setAttribute("mensagem", "Usuário encontrado é FUNCIONÁRIO PRODUÇÃO");                
                            request.getRequestDispatcher("TelaPerfilFuncionario.jsp").forward(request, response);
                            
                        }else if( funcionario.getTipo().equals("ADMINISTRADOR") ){
                            // O USUÁRIO É FUNCIONÁRIO TIPO ADMINISTRADOR
                            
                            sessao.setAttribute("funcionario", funcionario);
                           // request.setAttribute("mensagem", "Usuário encontrado é FUNCIONÁRIO PRODUÇÃO");                
                            request.getRequestDispatcher("TelaPerfilFuncionarioAdministrador.jsp").forward(request, response);
                            
                        }
                        
                    }
                  
                    
                }else{
                    // Usuário Não encontrado
                    
                    request.setAttribute("mensagem", "Email ou Senha Inválido");
                
                    request.getRequestDispatcher("TelaLogin.jsp").forward(request, response);
                }
                
            }else{
                // Se não, ele não possui cadastrado
                
                request.setAttribute("mensagem", "Esse Email não é Cadastrado");
                
                request.getRequestDispatcher("TelaLogin.jsp").forward(request, response);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login_Controle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
