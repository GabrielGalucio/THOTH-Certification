package thoth.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thoth.modelo.bean.Aluno;
import thoth.modelo.bean.Curso;
import thoth.modelo.bean.Matricula;
import thoth.modelo.dao.AlunoDao;
import thoth.modelo.dao.CursoDao;
import thoth.modelo.dao.MatriculaDao;

/**
 *
 * @author moise
 */
@WebServlet(name = "Matricula_Controle", urlPatterns = {"/Matricula_Controle"})
public class Matricula_Controle extends HttpServlet {

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
            out.println("<title>Servlet Matricula_Controle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Matricula_Controle at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        
        Curso curso = new Curso();
        CursoDao cursoDao = new CursoDao();
        Aluno aluno = new Aluno();
        AlunoDao alunoDao = new AlunoDao();
        Matricula matricula = new Matricula();
        MatriculaDao matriculaDao = new MatriculaDao();
         
        long cd_curso = Long.parseLong( request.getParameter("src_curso") );
        long cd_aluno = Long.parseLong( request.getParameter("src_aluno") );        
        String transacao = request.getParameter("tx");
        
        curso = cursoDao.pesquisarCurso(cd_curso);
        aluno = alunoDao.pesquisarAluno(cd_aluno);
                
        SimpleDateFormat dt_inicial = new SimpleDateFormat("yyyy-MM-dd"); // FORMATADOR DA DATA
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
        
        try {
            
            Date dataMatricula = dt_inicial.parse( "2018-11-02" );  // CONVERSÃO DA DATA DO TIPO STRING PARA O TIPO DATE UTIL
            Date hr_1 = hora.parse( "01:11" ); 
            Time horaIni = new Time( hr_1.getTime() );
                        
            matricula.setCodigo(0);             
            matricula.setData( dataMatricula );
            matricula.setHora(horaIni);
            matricula.setValor(0);
            matricula.setAluno(aluno);
            matricula.setTransacaoPagamento(transacao);
            matricula.setCurso(curso);
           
           // matriculaDao.crudMatricula(matricula, "salvar");
                      
            matriculaDao.salvar(matricula);
            
            System.out.println("Matricula Enfetuada !!!");
            request.setAttribute("mensagem", "Parabéns !!!, Matricula Enfetuada");
            
        } catch (ParseException ex) {
            System.out.println("Conversão de DATA|HORA INVALIDO, MOTIVO: "+ex);
            request.setAttribute("mensagem", "Infelizmente a Matricula não pôde ser Efetuada");
        }
        
        request.getRequestDispatcher("TelaRealizarMatricula.jsp?src_curso="+curso.getCodigo()+"").forward(request, response);
        
        
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
