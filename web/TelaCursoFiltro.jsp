<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="thoth.modelo.dao.MatriculaDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="thoth.modelo.dao.PeriodoCursoDao"%>
<%@page import="thoth.modelo.bean.PeriodoCurso"%>
<%@page import="thoth.modelo.bean.Aluno"%>
<%@page import="thoth.modelo.bean.Funcionario"%>
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
        <title>Aluno</title>

        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/estilo.css" rel="stylesheet">
        <link href="bootstrap/css/listar_curso_tela_aluno.css" rel="stylesheet">
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
            Aluno aluno = (Aluno) session.getAttribute("aluno");
           
           if( aluno == null ){
               response.sendRedirect("TelaLogin.jsp");
           }
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
                <li class=""><a href="TelaPerfilAluno.jsp">Home</a></li>
                               
              
                 <!-- DROPDOW RESPONSÁVEL PELAS OPÇÕES DO CURSO -->
                <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Meus Cursos<span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a onclick="location='http://localhost:8080/Thoth/RotaSessaoAluno.jsp?id=1'">Meus Cursos</a></li>
                    <li><a >Cursos em Andamento</a></li>
                    <li><a onclick="location='http://localhost:8080/Thoth/RotaSessaoAluno.jsp?id=3'">Hitórico de Cursos</a></li>                    
                  </ul>
                </li>  
               
              </ul>
              <ul class="nav navbar-nav navbar-right">
                  <li><a href="#"><span class="glyphicon glyphicon-user"></span> <%= aluno.getNome() %> </a></li>
                
                <!-- DROPDOW RESPONSÁVEL PELA OPÇÕES REFERENTES A CONTA -->
                <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
                  <ul class="dropdown-menu">
                      <li><a onclick="location='http://localhost:8080/Thoth/RotaSessaoAluno.jsp?id=2'">Configuração de Conta</a></li>
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
          <div class="col-sm-8">
            <h3>CURSOS LIVRES DISPONÍVEIS - BEM-VINDO ALUNO</h3>              
          </div>
          <div class="col-sm-4">          
            
             <!-- RESPONSÁVEL PELO FILTRO DOS CURSOS POR ÁREA DE CONHECIMENTO -->
              <form action="TelaCursoFiltro.jsp" method="get">              
                <label for="cx_area_curso">Qual a Área de Conhecimento?</label>            
                <div class="col-md-8">
                    <select class="form-control" id="cx_area_curso" name="cx_area_curso" required>
                      <option selected value="">TODAS</option>
                      <option value="BIOLOGICA">BIOLÓGICA</option>
                      <option  value="EM ANDAMENTO">ECONOMIA</option>
                      <option value="ECONOMIA">EXATAS</option>
                      <option value="HUMANAS">HUMANAS</option>
                      <option value="SAUDE">SAÚDE</option>
                      <option value="TECNOLOGIA">TECNOLOGIA</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <input type="submit" value="PESQUISAR" id="salvar" name="salvar" class="btn btn-success btn-group-justifiedlg">
                </div>
            </form> 
              
          </div>    
          </div>
            </div>
        
        <div class="container">
        
        <div class="form-group"></div>    
            
        <div class="form-group">
            
        <% 
            // DECLARAÇÃO
            CursoDao cursDao = new CursoDao();
            MatriculaDao matDao = new MatriculaDao();
            PeriodoCurso periodo = new PeriodoCurso();
            PeriodoCursoDao periodoDao = new PeriodoCursoDao();
            int qtde_matricula;

        %>
        
         <div class="wrapper"> <!-- SEPARA AS GRIDS EM COLUNAS -->
            
            <%
                 
              for( Curso curso: cursDao.listarCurso( "A", aluno.getCodigo() ) ){
                  
                if( curso.getArea().equals(  request.getParameter("cx_area_curso")  ) ){
             %>
             
            <div>
                <div class="panel panel-primary">
                    <div class="panel-heading>">
                            <h5></h5>
                            <h2 class="panel-title">Professor: <%= curso.getProfessor().getNome() %></h2>                                   
                    </div>                
                    <div class="panel-body">
                        <h3> Curso: <%= curso.getTitulo() %> </h3>
                        <h5> Area Conhecimento: <%= curso.getArea() %> </h5>
                        <h5>Endereço: <%= curso.getLocal().getNome() %> </h5>
                        
                        <%
                            /**
                             * PESQUISA O PERIODO VINCULADO AO CURSO QUE EXIBA A DATA DE INÍCIO DO CURSO
                             */
                            periodo = periodoDao.pesquisarPorCurso(curso.getCodigo() );
                            
                            // CASO TENHA DUAS DATAS VINCULADAS TRAZ SÓ UMA
                            if( periodo != null ){
                                
                                for( int i = 0; i<1; i++ ){
                                // MÁSCARA QUE CONVERTE A DATA DO FORMATO YYYY-MM-DD PARA DD/MM/YYYY
                                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                                String dataInicio = formatador.format( periodo.getDtInicio() ); 
                        %>
                        
                        <h5>Data de Inicío: <%= dataInicio %> </h5>
                        
                        <%
                            }
                        }else{    
                        %>
                        
                        <h5>Data de Inicío: Indefinido </h5>
                        
                        <%
                        }

                        if( curso.getStatus().equals("A") ){
                        %>
                            
                        <h5>Status: ABERTO </h5>
                        
                        <% 
                        }else if( curso.getStatus().equals("P") ){
                        %>
                        
                         <h5>Status: PENDENTE </h5>
                        
                        <% 
                        }else if( curso.getStatus().equals("C") ){
                        %>
                        
                         <h5>Status: CANCELADO </h5>
                        
                        <% 
                        }else if( curso.getStatus().equals("E") ){
                        %>
                        
                         <h5>Status: ENCERRADO </h5>
                        
                        <% } %>
                        
                        <h5>Número de Vagas: <%= curso.getQtdeVaga() %> - Carga Horária: <%= curso.getCargaHr() %>Hrs </h5>
                        <h5>Tipo de Evento: <%= curso.getEvento() %> </h5>
                        
                        <%  
                        NumberFormat valor = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
                        %>
                        
                        <h3>Valor R$ <%= valor.format( curso.getValor() ) %> </h3>
                    </div>
                    <div class="panel-footer">
                        <a onclick="location='http://localhost:8080/Thoth/TelaRealizarMatricula.jsp?src_curso=<%= curso.getCodigo() %>'">Matricula-se</a>
                    </div>
                </div>
            </div>
               
            <% }}  %>  
             
        </div> <!-- FIM DO SEPARADOR DAS GRIDS das COLUNAS -->
        
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
        
       <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
        <script src="bootstrap/js/jquery_edge.js"></script>  
    </body>
</html>