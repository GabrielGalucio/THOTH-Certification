<%@page import="java.util.Date"%>
<%@page import="thoth.modelo.bean.Frequencia"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="thoth.modelo.bean.PeriodoCurso"%>
<%@page import="thoth.modelo.dao.PeriodoCursoDao"%>
<%@page import="thoth.modelo.dao.FrequenciaDao"%>
<%@page import="thoth.modelo.dao.MatriculaDao"%>
<%@page import="thoth.modelo.bean.Matricula"%>
<%@page import="thoth.modelo.bean.Professor"%>
<%@page import="thoth.modelo.bean.Curso"%>
<%@page import="thoth.modelo.dao.CursoDao"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <title>Professor - Matriculados</title>

        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/estilo.css" rel="stylesheet">
        <link href="bootstrap/js/javascript.js" rel="stylesheet">
        <link href="bootstrap/imagem/imagem_thoth.jpeg" rel="icon">
        <link href="bootstrap/js/jquery.js" rel="stylesheet">
        <link href="bootstrap/js/jquery_edge.js" rel="stylesheet">
        
        <script src="bootstrap/js/validaCampoFormulario.js"></script>        
        <script src="bootstrap/js/validarCampoPeriodo.js"></script>
        <script src="bootstrap/js/limparCamposTabIntroducao_CriarCurso.js"></script>
        <script src="bootstrap/js/mascaraCampos.js"></script>       
        
         <style>
            /* PÕE A BARRA DE ROLAGEM VERTICAL NA TABELA LOCAL */
            .table-overflow {
                max-height:500px;
                overflow-y:auto;
            }
        </style>
        
    </head>
    <body>
        
        <% 
         //  HttpSession sessao = request.getSession();
           
           Professor professor = (Professor) session.getAttribute("professor");
           
           if( professor == null ){
               response.sendRedirect("TelaLogin.jsp");
           }
           
           FrequenciaDao frequenciaDao = new FrequenciaDao();
           PeriodoCursoDao periodoDao = new PeriodoCursoDao();
           MatriculaDao matDao = new MatriculaDao();
           
           // DECLARAÇÃO
           CursoDao cursoDao = new CursoDao();
           Curso curso = cursoDao.pesquisarCurso( Long.parseLong( request.getParameter("cd_curso") ) );
           if( curso != null ){
           
         %>
        <!-- BLOCO DA BARRA DE NAVEGAÇÃO DA TELA -->
        <nav class="navbar navbar-inverse">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                        
              </button>
                <div id="img_logo">
              <img src="bootstrap/imagem/imagem_thoth_logo.jpeg" height="50" width="50" class="img-circle img-responsive">
                </div>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
              <ul class="nav navbar-nav">
                <li class=""><a onclick="location='http://localhost:8080/Thoth/TelaPerfilProfessor.jsp'">Home</a></li>
                
                <!-- DROPDOW RESPONSÁVEL PELAS OPÇÕES DO MENU PRINCIPAL -->
                <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Menu principal<span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a onclick="location='http://localhost:8080/Thoth/RotaSessaoProfessor.jsp?id=2'">Gerenciar membros à sua Equipe</a></li>                    
                  </ul>
                </li>
                  
                 <!-- DROPDOW RESPONSÁVEL PELAS OPÇÕES DO CURSO -->
                <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Opções do Curso<span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a onclick="location='http://localhost:8080/Thoth/RotaSessaoProfessor.jsp?id=3'">Criar projeto curso</a></li>
                    <li><a href="#">Cursos em Andamento</a></li>
                    <li><a href="#">Hitórico de Cursos</a></li>                    
                  </ul>
                </li>  
                 
              </ul>
              <ul class="nav navbar-nav navbar-right">
                  <li><a href="#"><span class="glyphicon glyphicon-user"></span> <%= professor.getNome() %> </a></li>
                
                <!-- DROPDOW RESPONSÁVEL PELA OPÇÕES REFERENTES A CONTA -->
                <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
                  <ul class="dropdown-menu">
                      <li><a onclick="location='http://localhost:8080/Thoth/RotaSessaoProfessor.jsp?id=1'">Configuração de Conta</a></li>
                    <li><a data-toggle="modal" data-target="#confirm">Sair</a></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <!-- FIM BLOCO DA BARRA DE NAVEGAÇÃO DA TELA -->
        
        <div class="container">
          
        <div class="form-group">
          <div class="col-sm-9">
              <h3>CURSO: <%= curso.getTitulo() %> </h3>              
          </div>
          <div class="col-sm-3">          
            
          </div>    
          </div>
            </div>
        
        <div class="container">
        
        <div class="form-group"></div>    
            
        <div class="form-group">
        
          <div class="table-overflow"> <!-- TABELA DE CURSO -->
              
          <table class="table table-striped table-bordered table-hover"> <!-- TABELA -->
            <thead class="success"> <!-- O CABEÇALHO DA TABELA -->

                <td class="success">C&oacute;digo Matricula</td>
                <td class="success">Nome Aluno</td>
                
                <% 
                for( Date d: periodoDao.listarDataCurso(curso.getCodigo() ) ){
                    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                    String dataInicio = formatador.format( d );
                %>
                
                <td class="success"> <%= dataInicio %> </td> <!-- BOTÃO ALTERAR -->
                
                <% } %>
                
            </thead>

            <tbody>
                
                    <%                 
                    for(Matricula matricula: matDao.listarMatricula( curso.getCodigo() ) ){                
                    %>
                    
                <tr> <!-- LINHA 1 DA TABELA -->
                    
                    <td><%= matricula.getCodigo() %></td>
                    <td>[<%= matricula.getAluno().getNumMatricula() %>] - <%= matricula.getAluno().getNome() %> </td>
                    
                    <%
                    for( Frequencia f: frequenciaDao.listarFrequencia( matricula.getAluno().getCodigo(), matricula.getCurso().getCodigo() ) ){   
                    %>
                    
                    <td>
                        
                        <% 
                        if( f.getStatus().equals("P") ){
                        %>
                        
                        <input type="button" value="<%= f.getStatus() %>" id="alterar" name="alterar"  onclick="location='http://localhost:8080/Thoth/Frequencia_Controle?src_freqAluno=<%= f.getCodigo() %>&src_freqVl=<%= f.getStatus() %>'" class="btn btn-primary btn-group-justified">
                        
                        <%
                        }else if( f.getStatus().equals("F") ){    
                        %>
                        
                        <input type="button" value="<%= f.getStatus() %>" id="alterar" name="alterar"  onclick="location='http://localhost:8080/Thoth/Frequencia_Controle?src_freqAluno=<%= f.getCodigo() %>&src_freqVl=<%= f.getStatus() %>'" class="btn btn-danger btn-group-justified">
                        
                        <% } %>
                        
                    </td>  
                    
                    <% } %>
                   
                                        
                </tr>
                
                <% } %>
                
            </tbody>

        </table>  
        
        <% } %>        
                
       <!-- TELA DE CONFIRMAÇÃO PARA SAIR DA TELA DO USUÁRIO -->
        <div class="modal fade" id="confirm" name="confirm" role="dialog">
        <div class="modal-dialog modal-md">                                 
          <div class="modal-content">
            <div class="modal-body">
                <p> DESEJA REALMENTE SAIR? </p>
            </div>
            <div class="modal-footer">
              <input type="button" value="Sair" id="excluir" name="sair" onclick="location='http://localhost:8080/Thoth/DeslogarSessao.jsp'" class="btn btn-danger btn-sm">
              <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">Cancelar</button>
            </div>
          </div>

            </div>
          </div>  <!-- BLOCO DE EXCLUSÃO -->         
                
        </div>
        </div>      
        </div> 
        
       <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
        <script src="bootstrap/js/jquery_edge.js"></script>  
    </body>
</html>