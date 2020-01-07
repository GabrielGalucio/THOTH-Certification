<%@page import="thoth.modelo.bean.Frequencia"%>
<%@page import="thoth.modelo.dao.FrequenciaDao"%>
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
        <title>Visualizar Curso</title>

        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/estilo.css" rel="stylesheet">
        <link href="bootstrap/css/listar_curso_tela_aluno.css" rel="stylesheet">
        <link href="bootstrap/css/estilo_telaMatricula.css" rel="stylesheet">
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
                max-height:300px;
                overflow-y:auto;
            }
        </style>
               
    </head>
    <body>
        
        <% 
           
           Aluno aluno = (Aluno) session.getAttribute("aluno");
           
           Curso curso = new Curso();
           CursoDao cursoDao = new CursoDao();
           FrequenciaDao frequenciaDao = new FrequenciaDao();
           
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
                <li class=""><a href="TelaMeusCurso.jsp">Meus Cursos</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                  <li><a href="#"><span class="glyphicon glyphicon-user"></span> <%= aluno.getNome() %> </a></li>
                
                <!-- DROPDOW RESPONSÁVEL PELA OPÇÕES REFERENTES A CONTA -->
                <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a data-toggle="modal" data-target="#confirm">Sair</a></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <!-- FIM BLOCO DA BARRA DE NAVEGAÇÃO DA TELA -->
        
        <div class="container">
          
        <% 
        long cd_curso = Long.parseLong( request.getParameter("src_curso") );
        curso = cursoDao.pesquisarCurso( cd_curso );
        
        if( curso != null ){           
        %>    
            
          <div class="form-group">   
            <div class="col-sm-8">
                <img src="bootstrap/imagem/imagem_thoth.jpeg" height="200" width="700">  
            </div>
            
            <div class="col-sm-4">
                
                <%  
                 // VERIFICA SE O ALUNO PASSOU POR FREQUÊNCIA OU NÃO
                 boolean passou = frequenciaDao.percentualFrequencia( aluno.getCodigo() , curso.getCodigo() );
                
                 /**
                  * ESTE CONDIÇÃO VERIFICA SE O ALUNO CONSEGUIU ALCANÇAR O PERCENTUAL DE PRESENÇA E SE O CURSO
                  * ESTÁ ENCERRADO.
                  */
                 if( (passou) && ( curso.getStatus().equals("E") ) ){
                %>
                
                    <div class="form-group"></div>
                    <div class="form-group"></div>
                    
                    <div class="form-group"> 
                        <input type="button" value="Gerar Centificado" id="cert" name="cert" onclick="location='http://localhost:8080/Thoth/GerarCertificado?cx_aluno=<%= aluno.getCodigo() %>&cx_curso=<%= curso.getCodigo() %>'" class="btn btn-success btn-lg btn-block">
                    </div>
                    
                    <div class="form-group">
                        <div class="alert alert-success">
                        <h4>Parabéns por concluir o curso.</h4>
                        <h4>Seu certificado está liberado.</h4>
                    </div> 
                    </div>
                    
                  <% }else{ %>  
                  
                    <div class="form-group"></div>
                    <div class="form-group"></div>
                    
                    <div class="form-group"> 
                        <input type="button" value="Gerar Centificado" id="cert" name="cert" onclick="location='http://localhost:8080/Thoth/GerarCertificado?cx_aluno=<%= aluno.getCodigo() %>&cx_curso=<%= curso.getCodigo() %>'" class="btn btn-success btn-lg btn-block" disabled>
                    </div>
                    
                    <div class="form-group"></div>
                    <div class="alert alert-danger">
                        <h4>O curso ainda não foi encerrado</h4>
                        <h4>Ou você não atingiu os 95% de presença na frequência.</h4>
                    </div> 
                  <% } %>
               
            </div>
          </div>
        </div>
        
        <div class="container">
        
        <div class="form-group"></div>    
        <div class="form-group"></div>    
            
        <div class="form-group">
         
            <div class="col-sm-8">
                
                <h1> <%= curso.getTitulo() %> </h1>
                
                <div class="form-group">
                <h3>Objetivos</h3>
                <p>
                    <%= curso.getObjetivo() %>
                </p>
                </div>
                
                <div class="form-group">
                <h3>Metodologia</h3>
                <p>
                    <%= curso.getMetodologia() %>                  
                </p>
                </div>
                
                <div class="form-group">
                <h3>Ementa</h3>
                <p>
                    <%= curso.getEmenta() %>                    
                </p>
                </div>
                
                <div class="form-group">
                <h3>Bibliografia</h3>
                <p>
                    <%= curso.getBibliografia() %>                    
                </p>
                </div>
                
            </div>
                
            <div class="col-sm-4">
            
                <div class="container">
                
                    <div class="form-group">
                    <h3>Professor</h3>
                        <h4>Nome: <%= curso.getProfessor().getNome() %>  </h4>
                        <h4>Especialidade: <%= curso.getProfessor().getEspecialidade() %></h4>
                        <h4>Currículo Lattes: <a href="<%= curso.getProfessor().getCurrilo() %>"> 
                        <%= curso.getProfessor().getCurrilo() %> </a> </h4>
                    </div>
                    
                    <div class="form-group"></div>
                    
                    <div class="form-group">
                    <h3>Local de Realização</h3>
                        <h4>Nome: <%= curso.getLocal().getNome() %> </h4>
                        <h4>Logradouro: <%= curso.getLocal().getLogradouro() %></h4>
                        <h4>Bairro: <%= curso.getLocal().getBairro() %> - N°.: <%= curso.getLocal().getNumero() %> - CEP: <%= curso.getLocal().getCep() %> </h4>
                    </div>
                    <div class="form-group"></div>
                    
                    <div class="form-group">
                    <h3>Dados do Curso</h3>
                        <h4>Quantidade de Vagas:  <%= curso.getQtdeVaga() %> </h4>
                        <h4>Carga Horária: <%= curso.getCargaHr() %>h</h4>
                        <h4>Área de Conhecimento: <%= curso.getArea() %> </h4>
                        <h4>Evento: <%= curso.getEvento() %> </h4>
                        <h4>Status do Curso: <%= curso.getStatus() %> </h4>
                    </div>
                    <div class="form-group"></div>
                    
                    <div class="container">
                    
                    <div class="col-sm-4">
                    <div class="form-group"> <!-- TABELA RESPONSÁVEL POR LISTAR OS PERIODOS DO CURSO -->
                    <h3>Periodos de Realização</h3>
                        <div class="table-overflow"> <!-- TABELA DE CURSO -->              
                          <table class="table table-striped table-bordered table-hover"> <!-- TABELA -->
                            <thead class="success"> <!-- O CABEÇALHO DA TABELA -->
                                
                            </thead>
                            <tbody>
                                
                                <% 
                                    PeriodoCursoDao periodoDao = new PeriodoCursoDao();
                                    for( PeriodoCurso periodo: periodoDao.listarPeriodo( curso.getCodigo() ) ){
                                        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                                        String dataInicio = formatador.format( periodo.getDtInicio() );
                                %>
                                
                                <tr> <!-- LINHA 1 DA TABELA -->
                                    <td>Dia: <%= dataInicio %>  </td>
                                    <td> <%= periodo.getHrInicio() %> </td>
                                    <td> <%= periodo.getHrFinal() %> </td>                                        
                                </tr>
                                
                                <% } %>
                                
                            </tbody>
                        </table> 
                            
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
                                
                    <!-- BLOCO DA TABELA FREQUÊNCIA -->    
                    <div class="form-group"> <!-- TABELA RESPONSÁVEL POR LISTAR DE FREQUÊNCIAS DO CURSO -->
                    <h3>Frequência do Aluno</h3>
                        <div class="table-overflow"> <!-- TABELA DE CURSO -->              
                          <table class="table table-striped table-bordered table-hover" id="tb_frequencia"> <!-- TABELA -->
                            <thead class="success"> <!-- O CABEÇALHO DA TABELA -->
                                
                            </thead>
                            <tbody>
                                
                                <% 
                                    
                                    for( Frequencia frequencia: frequenciaDao.listarFrequencia( aluno.getCodigo(), curso.getCodigo() ) ){
                                        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                                        String data = formatador.format( frequencia.getData_frequencia() );
                                %>
                                
                                <tr> <!-- LINHA 1 DA TABELA -->
                                    <td>Dia: <%= data %>  </td>
                                    
                                    <%
                                    if( frequencia.getStatus().equals("P") ){    
                                    %>
                                    
                                    <td> 
                                        <input type="button" value="PRESENTE" id="frequencia" name="frequencia" class="btn btn-primary btn-group-justified">
                                    </td>  

                                    <% }else if( frequencia.getStatus().equals("F") ){                                 
                                    %>
                                
                                    <td> 
                                        <input type="button" value="FALTA" id="frequencia" name="frequencia" class="btn btn-danger btn-group-justified">
                                    </td>
                                    
                                    <% } %>
                                </tr>
                                
                                <% } %>
                                
                            </tbody>
                        </table> 
                      
                        </div>
                    </div>
                    </div>
                    </div>
                    <!-- FIM DO BLOCO DA TABELA FREQUÊNCIA -->            
                          
                    </div>
                    </div>
                    <div class="form-group"></div>
                    
                </div>
                
                    <div class="form-group"></div>
                    
                </div>                
                                
            </div>
            
        </div> 
        
        <% } %>
        
             
       <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
        <script src="bootstrap/js/jquery_edge.js"></script>  
    </body>
</html>