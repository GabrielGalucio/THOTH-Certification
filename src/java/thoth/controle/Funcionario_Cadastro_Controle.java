package thoth.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thoth.modelo.bean.Funcionario;
import thoth.modelo.bean.Usuario;
import thoth.modelo.dao.FuncionarioDao;
import thoth.modelo.dao.UsuarioDao;

/**
 *
 * @author moise
 */
@WebServlet(name = "Funcionario_Cadastro_Controle", urlPatterns = {"/Funcionario_Cadastro_Controle"})
public class Funcionario_Cadastro_Controle extends HttpServlet {

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
            out.println("<title>Servlet Funcionario_Cadastro_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Funcionario_Cadastro_Controle at " + request.getContextPath() + "</h1>");
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
        Funcionario funcionario = new Funcionario();
        FuncionarioDao funcDao = new FuncionarioDao();
        HttpSession sessao = request.getSession();
        
        request.setCharacterEncoding("UTF-8");
        
        String nome = request.getParameter("cx_nome_funcionario");
        String email = request.getParameter("cx_email_funcionario");
        String tipo = request.getParameter("lista_perfil");
        String fone = request.getParameter("cx_telefone");
        String cpf = request.getParameter("cx_cpf");
        
        usuario.setNome(nome.toUpperCase());
        usuario.setEmail(email.toUpperCase());
        usuario.setSenha("123");
        usuario.setFone(fone);
        
        try {
            
            if(usuDao.identificarUsuaricoCadastrado(email)){
               
                 request.setAttribute("mensagem", "Usuário: "+usuario.getNome()+" já é Cadastrado !!");
                
            }else{
                 
                usuDao.salvar(usuario);

                System.out.println("Usuário Cadastrado com Sucesso !!!");

                long cd_usuario = usuDao.ultimoUsuarioCadastrado();

                funcionario.setCodigo(cd_usuario);

                funcionario.setTipo(tipo.toUpperCase());
                funcionario.setCpf(cpf.toUpperCase());
                
                funcDao.salvar(funcionario);
                
                System.out.println("Funcionário Alterado com Sucesso !!!");
                request.setAttribute("mensagem", "Usuário: "+usuario.getNome()+" Alterado com Sucesso !!!");
              //  request.getRequestDispatcher("TelaPerfilProfessor.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            request.setAttribute("mensagem", "Erro ao Cadastrar Usuário Funcionário");
            System.out.println("Erro ao Salvar o PROFESSOR, Motivo: "+ex);
        }
        
        request.getRequestDispatcher("TelaCadFuncionario.jsp").forward(request, response);
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
