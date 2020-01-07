<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>
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
        <title>Realizar Matrícula</title>

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
            <img src="bootstrap/imagem/imagem_thoth.jpeg" height="280" width="700">              
          </div>
          <div class="col-sm-4">          
            <div class="form-group">
                
                <% 
                NumberFormat valor = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols (new Locale ("pt", "BR")));
                %>
                
                <h1> Valor R$ <%= valor.format( curso.getValor() )  %>  </h1>                
             </div>
             
                <form action="${initParam['posturl']}" method="post" id="form_curso">
                    
                   
                    <input class="form-control" type="hidden" name="upload" value="1" id="upload" size="4" maxlength="4">
                    <input class="form-control" type="hidden" name="return" value="http://localhost:8080/Thoth/Matricula_Controle?src_curso=<%= curso.getCodigo() %>&src_aluno=<%= aluno.getCodigo() %>" id="return">
                    <input class="form-control" type="hidden" name="cmd" value="_cart" id="cmd">
                    <input class="form-control" type="hidden" name="business" value="${initParam['business']}" id="business">
                    
                    <!-- ITEM DA VENDA -->                    
                    <input class="form-control" type="hidden" name="item_name_1" value="<%= curso.getTitulo() %>" id="item_name_1" size="200" maxlength="200" placeholder="0">
                    <input class="form-control" type="hidden" name="item_number_1" value="<%= curso.getCodigo() %>" id="item_number_1" size="4" maxlength="4" placeholder="0">
                    <input class="form-control" type="hidden" name="amount_1" value="<%= curso.getValor() %>" id="amount_1" size="20" maxlength="20" placeholder="0">
                    <input class="form-control" type="hidden" name="quantity_1" value="1" id="quantity_1" size="4" maxlength="4" placeholder="0">
                                                
                    <div class="form-group"> 
                        <input type="submit" value="Efetuar Pagamento" id="pag" name="pag" class="btn btn-success btn-lg btn-block">
                    </div>
                    
                </form>
               
             <div class="alert alert-success">
                <h5>${mensagem}</h5>
            </div>               
                            
          </div>    
          </div>
            </div>
        
        <div class="container">
        
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
                
                <div>
                    <h3><center>*** AVISO IMPORTANTE LEIA COM ATENÇÃO ***</center></h3>
                    <p>
                       1. O cursos é livre e você terá acesso após o pagamento do valor informado a cima ao conteúdo e avaliação, 
                       o certificado de conclusão do curso é gerado de forma gratuita após o término deste
                       curso e sua frequência ser maior ou igual à 95% da carga horária. 
                    </p>
                    <p>
                        2. O certificado de conclusão deste Curso Livre é válido 
                        em todo o Brasil com amparo legal no Decreto Presidencial n° 5.154, 
                        de 23 de julho de 2004, Art 1° e 3°. E na lei nº 9.394, que estabelece 
                        as Diretrizes e Bases da Educação Nacional que mostra que os Cursos Livres 
                        passaram a integrar a Educação Profissional, 
                        veja <a href="http://www.planalto.gov.br/ccivil_03/_ato2004-2006/2004/decreto/d5154.htm"><b>mais aqui</b></a>;
                    </p>
                    <p>
                       3. Você poderá utilizar o Certificado de Conclusão de Curso para completar 
                       horas em atividades extra-curriculares exigidas em faculdades, contar como 
                       atividades em concursos públicos ou simplesmente constar em seu currículo. 
                    </p>
                    <P>
                        4. Se você já tem conhecimento sobre um assunto e precisa de um certificado 
                        para comprová-lo, basta clicar em Solicitar a Prova. (Não há necessidade de 
                        fazer a leitura do curso para prestar a prova de suficiência.)
                    </P>
                </div>
                
            </div>
            <div class="col-sm-4">
            
                <div class="container">
                
                    <div class="form-group">
                    <h3>Professor</h3>
                        <h4>Nome: <%= curso.getProfessor().getNome() %>  </h4>
                        <h4>Especialidade: <%= curso.getProfessor().getEspecialidade() %></h4>
                        <h4>Currículo Lattes: <a href="<%= curso.getProfessor().getCurrilo() %>"> <%= curso.getProfessor().getCurrilo() %> </a> </h4>
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
                    </div>
                    </div>
                    <div class="form-group"></div>
                    
                </div>
            
            </div>
            
        </div> 
        
        <% } %>
        
        </div> 
        
      
       <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
        <script src="bootstrap/js/jquery_edge.js"></script>  
    </body>
</html>