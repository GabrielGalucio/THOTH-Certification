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
@WebServlet(name = "Funcionario_Alterar_Controle", urlPatterns = {"/Funcionario_Alterar_Controle"})
public class Funcionario_Alterar_Controle extends HttpServlet {

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
            out.println("<title>Servlet Funcionario_Alterar_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Funcionario_Alterar_Controle at " + request.getContextPath() + "</h1>");
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
        Funcionario funcionario = new Funcionario();
        FuncionarioDao funcDao = new FuncionarioDao();
    //    HttpSession sessao = request.getSession();
        
        request.setCharacterEncoding("UTF-8");
        
        String nome = request.getParameter("cx_nome_funcionario");
        String email = request.getParameter("cx_email_funcionario");
        String tipo = request.getParameter("lista_perfil");
        String fone = request.getParameter("cx_telefone");
        String cpf = request.getParameter("cx_cpf");
        String senha = request.getParameter("cx_senha_funcionario");        
        long cd_func = Long.parseLong( request.getParameter("cx_codigo_func") );        
        
        System.out.println("-------------------- CONTROLE ALTERAR FUNCIONÁRIO ----------------------------");
        System.out.println("Código: "+cd_func);
        System.out.println("Nome: "+nome);
        System.out.println("Email: "+email);
        System.out.println("Tipo: "+tipo);
        System.out.println("Fone: "+fone);
        System.out.println("Cpf: "+cpf);
        System.out.println("Senha: "+senha);

        System.out.println("-------------------- CONTROLE ALTERAR FUNCIONÁRIO ----------------------------");
        
        usuario.setCodigo(cd_func);
        usuario.setNome(nome.toUpperCase());
        usuario.setEmail(email.toUpperCase());
        usuario.setStatus("A");
        usuario.setSenha(senha);
        usuario.setFone(fone);
        
        try {
             
            usupesquisa = usuDao.pesquisarUsuario(cd_func);  // PEGA O OBJETO JÁ CADASTRADO PARA COMPARAR ANTES DE ALTERAR
             
            if( usupesquisa.getEmail().toUpperCase().equals( usuario.getEmail() ) ){

               usuDao.alterar(usuario);

               System.out.println("Usuário Alterado com Sucesso !!!");

               funcionario.setCodigo( cd_func );

               funcionario.setTipo(tipo.toUpperCase());
               funcionario.setCpf(cpf.toUpperCase());

               funcDao.alterar(funcionario);

               System.out.println("Funcionário Alterado com Sucesso !!!");
               request.setAttribute("mensagem", "Usuário: "+usuario.getNome()+" Alterado com Sucesso !!!");

            }else if(usuDao.identificarUsuaricoCadastrado(email)){
               
                 request.setAttribute("mensagem", "Usuário: "+usuario.getNome()+" já é Cadastrado !!");
                // request.getRequestDispatcher("TelaAlterarFuncionario.jsp").forward(request, response);
            }else{
                 
                usuDao.alterar(usuario);

                System.out.println("Usuário Alterado com Sucesso !!!");

                funcionario.setCodigo(cd_func);
                funcionario.setTipo(tipo.toUpperCase());
                funcionario.setCpf(cpf.toUpperCase());                
                funcDao.alterar(funcionario);
                
                System.out.println("Funcionário Alterado com Sucesso !!!");
                request.setAttribute("mensagem", "Usuário: "+usuario.getNome()+" Alterado com Sucesso !!!");
             //   request.getRequestDispatcher("TelaAlterarFuncionario.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            request.setAttribute("mensagem", "Erro ao Alterar Usuário Funcionário");
            System.out.println("Erro ao Salvar o Alterar, Motivo: "+ex);
        }
        
        request.getRequestDispatcher("TelaAlterarFuncionario.jsp").forward(request, response);
       
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
