<%@page import="thoth.modelo.bean.Professor"%>
<%@page import="thoth.modelo.bean.Localizacao"%>
<%@page import="thoth.modelo.dao.LocalizacaoDao"%>
<%@page import="thoth.modelo.dao.CursoDao"%>
<%@page import="thoth.modelo.bean.Curso"%>
<%@page import="thoth.modelo.bean.PeriodoCurso" %>
<%@page import="thoth.modelo.dao.PeriodoCursoDao" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-BR">
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />

        <title>Alterar Curso</title>

        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/estilo.css" rel="stylesheet">
        <link href="bootstrap/js/javascript.js" rel="stylesheet">
        <link href="bootstrap/imagem/imagem_thoth.jpeg" rel="icon">
        
        <script src="bootstrap/js/validaCampoFormulario.js"></script>        
        <script src="bootstrap/js/validarCampoPeriodo.js"></script>
        <script src="bootstrap/js/limparCamposTabIntroducao_CriarCurso.js"></script>
        
    </head>
    <body>
        
        <% 
                
            Professor professor = (Professor) session.getAttribute("professor");

            if( professor == null ){
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
                <li class=""><a href="TelaPerfilProfessor.jsp">Home</a></li>
               
                 <!-- DROPDOW RESPONSÁVEL PELAS OPÇÕES DO CURSO -->
                <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Opções do Curso<span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="TelaCriarCurso.jsp">Criar projeto curso</a></li>
                  </ul>
                </li>  
                
              </ul>
              <ul class="nav navbar-nav navbar-right">
                  <li><a href="#"><span class="glyphicon glyphicon-user"></span> <%= professor.getNome() %> </a></li>
                
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

        <div class="container-fluid">

            <div class="col-sm-1" ></div>

            <div class="col-sm-10"> <!-- COLUNA 1 DA PÁGINA -->
                
                <% 
                    LocalizacaoDao localDao = new LocalizacaoDao();
                    PeriodoCursoDao periodoDao = new PeriodoCursoDao();
                    Curso c2 = new Curso();
                    CursoDao cur2 = new CursoDao();
                    long cd;
                    c2 = cur2.pesquisarCurso( Long.parseLong(request.getParameter("id") ) );
                    
                    cd = c2.getCodigo();
                %>
                
                <div class="row"> <!-- LINHA DE OPÇÕES DAS ABAS TAGS -->

                      <div class="span12">

                          <div class="page-header">

                                  <div class="tabbable" id="tabs">

                                      <ul class="nav nav-tabs"> <!-- BARRA DE OPÇÕES DAS ABAS TAGS -->

                                          <li class="active"><a href="#tabs_1" data-toggle="tab">INTRODUÇÃO</a></li>
                                          <li><a href="#tabs_2" data-toggle="tab">CARACTERÍSTICAS</a></li>
                                          <li><a href="#tabs_3" data-toggle="tab">PERIODO DE REALIZAÇÃO</a></li>
                                          <li><a href="#tabs_4" data-toggle="tab">FINALIZAR</a></li>

                                      </ul>

                                      <div class="tab-content"> <!-- CONTEÚDO DAS TABS -->
                                          
                                          <div class="tab-pane active" id="tabs_1">                                            
                                              
                                                  <% 
                                                    Curso curso = new Curso();
                                                    CursoDao cursoDao = new CursoDao();
                                                    curso = cursoDao.pesquisarCurso( Long.parseLong(request.getParameter("id") ) );
                                                    
                                                    if( curso != null ){
                                                  %>
                                              
                                              <form action="Curso_Alterar_Controle" method="post" id="form_curso"> <!-- FORMULÁRIO - ABA INTRODUÇÃO -->
                                                  
                                                <div class="form-group"></div>
                                                <div class="form-group"></div>

                                                    
                                                <input class="form-control" type="hidden" name="cx_codigo" value="<%= curso.getCodigo() %>" id="cx_codigo" size="4" maxlength="4" placeholder="0">
                                                <input class="form-control" type="hidden" name="cd_prof" value="<%= professor.getCodigo() %>" id="cd_prof" size="4" maxlength="4" placeholder="0">
                                                <input class="form-control" type="hidden" name="cx_form" value="1" id="cx_form" size="4" maxlength="4" placeholder="0">
                                                
                                               <div class="col-sm-12"> <!-- COLUNA 2 Do CAMPO TÍTULO CURSO -->

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- campo título -->
                                                    <label for="cx_titulo">T&iacute;tulo do Curso:</label>
                                                    <input class="form-control" type="text" name="cx_titulo" id="cx_titulo" size="50" maxlength="150" placeholder="Informe o nome de seu projeto de curso" value="<%= curso.getTitulo() %>"  autofocus required>                                                   
                                                </div>
                                                </div> <!-- FIM DA GRID DA COLUNA DO CAMPO -->

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- campo objetivos -->
                                                    <label for="txt_objetivo">Objetivos:</label>
                                                    <textarea class="form-control" name="txt_objetivo" id="txt_objetivo" placeholder="Apresente os objetivos geral, específico e os pretendidos." required><%= curso.getObjetivo() %></textarea>                                                    
                                                </div>

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- campo metolodia -->
                                                    <label for="txt_metodologia">Metodologia:</label>
                                                    <textarea class="form-control" name="txt_metodologia" id="txt_metodologia" placeholder="Apresente a estratégia de abordagem, técnicas de modelos adotados e os métodos que serão utilizados para a avaliação dos alunos." required><%= curso.getMetodologia() %></textarea>
                                                </div>

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- campo ementa -->
                                                    <label for="txt_ementa">Ementa:</label>
                                                    <textarea class="form-control" name="txt_ementa" id="txt_ementa" placeholder="Apresente todo o conteúdo a ser abordado no curso." required><%= curso.getEmenta() %></textarea>
                                                </div>

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- campo bibliografia -->
                                                    <label for="txt_bibliografia">Bibliografia:</label>
                                                    <textarea class="form-control" name="txt_bibliografia" id="txt_bibliografia" placeholder="Apresente as referências utilizadas no curso." required><%= curso.getBibliografia() %></textarea>
                                                </div>

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- botão salvar-->
                                                    <input type="submit" value="Cancelar" id="cancelar" name="cancelar" class="btn btn-danger btn-lg">
                                                    <!-- campo excluir -->
                                                    <input type="submit" value="Salvar Alteração" id="salvar" name="salvar" class="btn btn-success btn-lg">
                                                </div>
                                                
                                                <% } %>
                                                
                                                </form>
                                                
                                          </div> <!-- FIM TAB 1 -->
                                          
                                          <div class="tab-pane" id="tabs_2">
                                            
                                            <% 
                                               Curso curso1 = new Curso();
                                               CursoDao cursoDao1 = new CursoDao();
                                               curso1 = cursoDao1.pesquisarCurso( Long.parseLong(request.getParameter("id") ) );
                                               if( curso1 != null ){
                                             %>  
                                              
                                            <form action="Curso_Alterar_Controle" method="post" id="form_curso1"> 
                                                 
                                            <div class="col-sm-1"></div>
                                              
                                            <div class="col-sm-10">
                                             
                                                <input class="form-control" type="hidden" name="cx_codigo" value="<%= curso1.getCodigo() %>" id="cx_codigo" size="4" maxlength="4" placeholder="0">
                                                <input class="form-control" type="hidden" name="cd_prof" value="<%= professor.getCodigo() %>" id="cd_prof" size="4" maxlength="4" placeholder="0">
                                                <input class="form-control" type="hidden" name="cx_form" value="2" id="cx_form" size="4" maxlength="4" placeholder="0">
                                                <input class="form-control" type="hidden" name="cx_status" value="<%= curso1.getStatus() %>" id="cx_form" size="4" maxlength="4" placeholder="0">
                                                
                                                
                                                <div class="form-group"></div>
                                                <div class="form-group"></div>

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                <label for="lista_area">Áreas de Conhecimento:</label>
                                                  <select class="form-control" id="lista_area" name="lista_area"  required>
                                                      <option selected value="<%= curso1.getArea() %>"><%= curso1.getArea() %></option>
                                                    <option value="BIOLÓGICA">BIOLÓGICA</option>
                                                    <option value="CULTURA">CULTURA</option>
                                                    <option value="ECÔNOMIA">ECONOMIA</option>
                                                    <option value="EXATAS">EXATAS</option>
                                                    <option value="HUMANAS">HUMANAS</option>
                                                    <option value="SAÚDE">SAÚDE</option>
                                                    <option value="TECNOLOGIA">TECNOLOGIA</option>
                                                  </select>
                                                  </div>
                                                  <div class="form-group"></div>
                                                  <div class="form-group"></div>

                                                  <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                  <label for="lista_evento">Evento do Curso:</label>
                                                    <select class="form-control" id="lista_evento" name="lista_evento" required>
                                                        <option value="<%= curso1.getEvento() %>"><%= curso1.getEvento() %></option>    
                                                        <option value="MESA REDONDA">MESA REDONDA</option>                                                      
                                                        <option value="MINICURSO">MINI-CURSO</option>
                                                        <option value="PALESTRA">PALESTRA</option>
                                                        <option value="SEMINÁRIO">SEMINÁRIO</option>
                                                        <option value="WORKSHOP">WORKSHOP</option>
                                                    </select>
                                                    </div>

                                                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <label for="lista_local">Local de Realização:</label>
                                                      <select class="form-control" id="lista_local" name="lista_local" required>
                                                          <option value="<%= curso1.getLocal().getCodigo() %>"><%= curso1.getLocal().getNome() %></option>
                                                        <% 
                                                        for(Localizacao l: localDao.listarLocalizacao()){
                                                        %>
                                                            <option value=<%= l.getCodigo() %>> <%= l %> </option>
                                                        <% } %>
                                                      </select>
                                                      </div>
                                                        
                                                      <!-- campos abaixo são referentes a quantidade de vaga e valor do curso -->
                                                        
                                                      <div class="col-sm-2"> <!-- COLUNA 1 Do CAMPO CÓDIGO -->

                                                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                            <!-- campo quantidade Vaga -->
                                                            <label for="cx_qtdeVaga">Quantidade Vaga:</label>
                                                            <input class="form-control" type="number" name="cx_qtdeVaga" id="cx_qtdeVaga" value="<%= curso1.getQtdeVaga() %>" size="4" maxlength="4" placeholder="0">

                                                        </div>
                                                      </div> <!-- FIM DA GRID DA COLUNA DO CAMPO -->
                                                
                                                      <div class="col-sm-2"> <!-- COLUNA 2 Do CAMPO TÍTULO CURSO -->
                                                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                            <!-- campo valor do curso -->
                                                            <label for="cx_Valor">Valor R$:</label>
                                                            <input class="form-control" type="number" step="0.010" name="cx_Valor" id="cx_Valor" value="<%= curso1.getValor() %>" size="4" maxlength="4" placeholder="0,00">

                                                        </div>
                                                      </div>
                                                      
                                                      <div class="col-sm-2">
                                                          <!-- carga horária do curso -->
                                                            <label for="cx_cargaHoraria">Carga Horária:</label>
                                                            <input class="form-control" type="number" name="cx_cargaHoraria" id="cx_cargaHoraria" value="<%= curso1.getCargaHr() %>" size="4" maxlength="4" placeholder="0">
                                                      </div>
                                                     <!-- fim destes campos retratados -->

                                                 <div class="col-sm-6">
                                                
                                                  
                                                    <div class="form-group"></div>
                                                    <div class="form-group"></div>
                                                    <div class="form-group"></div>
                                                
                                                  <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                      <!-- botão salvar-->
                                                      <input type="submit" value="Anterior" id="anterior" name="anterior" class="btn btn-danger btn-lg">
                                                      <!-- campo excluir -->
                                                      <input type="submit" value="Salvar Alteração" id="salvar" name="salvar" class="btn btn-success btn-lg">
                                                  </div>
                                                </div>
                                                       
                                            </div>
                                                   
                                              <div class="col-sm-1"></div>
                                              
                                              <% } %>
                                              </form>
                                          </div> <!-- FIM TAB 2 -->
                                          
                                          <div class="tab-pane" id="tabs_3">
                                            
                                            <div class="col-sm-1"></div>
                                              
                                        <div class="col-sm-10">
                                                
                                            

                                              <div class="form-group"></div>
                                              <div class="form-group"></div>
                                              
                                              <div class="col-sm-0"></div>
                                            
                                            <div class="col-sm-10">
                                                
                                                <div class="form-group">
                                                    <h4>Per&iacute;odo de Realizaç&atilde;o</h4>
                                                </div>
                                                
                                                <form action="PeriodoCurso_Controle" method="post" id="form_curso3">
                                                
                                                <div class="col-xs-5"> <!-- coluna 1 - DATA -->
                                                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                        <label for="cx_data">Data:</label>
                                                        <input class="form-control" type="date" name="cx_data" id="cx_data" required>
                                                    </div>
                                                </div> <!-- fim coluna 1 - DATA -->
                                                
                                                <div class="col-xs-3"> <!-- coluna 2 - HORA INICIAL -->
                                                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                      <label for="hora_inicio">Hora In&iacute;cio:</label>
                                                      <input class="form-control" type="time" name="hora_inicio" id="hora_inicio" required>
                                                    </div>
                                                </div> <!-- fim coluna 2 - HORA INICIAL -->
                                                
                                                <div class="col-xs-3"> <!-- coluna 3 - HORA TÉRMINO -->
                                                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <label for="hora_termino">Hr. T&eacute;rmino:</label>
                                                    <input class="form-control" type="time" name="hora_termino" id="hora_termino" required>
                                                    </div>
                                                </div> <!-- fim coluna 3 - HORA TÉRMINO -->
                                                
                                                <input type="hidden" name="cd_curso" id="cd_curso" value="<%= cd %>">
                                                
                                                <div class="col-xs-1"> <!-- coluna 4 - BOTÃO -->
                                                    <div class="form-group"> 
                                                    <label></label>
                                                        <input type="submit" value="+" id="adicionar_periodo" name="adicionar_periodo" class="btn btn-primary btn-lh">
                                                    </div>
                                                </div> <!-- fim coluna 4 - BOTÃO -->
                                                
                                                </form>
                                                
                                                <!-- TABELA DAS DATAS -->
                                                <h4>Informe a quantidade de vagas por turno</h4>
                                                
                                                <div class="table-overflow">
                                                <table class="table table-striped table-bordered table-hover" id="tabela_periodo">
                                                
                                                  <thead>
                                                      <td class="success"></td>
                                                      <td class="success">Dia(s) adicionado(s)</td>
                                                      <td class="success">Hora In&iacute;cio</td>
                                                      <td class="success">Hora Término</td>
                                                      <td class="success"></td>
                                                </thead>
                                                  <tbody>
                                                      
                                                    <%
                                                
                                                    for( PeriodoCurso periodo: periodoDao.listarPeriodo( cd ) ){   

                                                    %>
                                                      
                                                      <tr> <!-- LINHA 1 DA TABELA -->
                                                          
                                                        <td><input type="hidden" id="cod_curso"> <%= periodo.getCodigo() %> </td>
                                                        <td><%= periodo.getDtInicio() %></td>
                                                        <td><%= periodo.getHrInicio() %></td>
                                                        <td><%= periodo.getHrFinal() %></td>
                                                        <td>
                                                            <input type="button" value="Excluir" id="excluir" name="excluir" onclick="location='http://localhost:8080/Thoth/PeriodoCurso_Controle?ODIGJ2=<%= periodo.getCodigo() %>&srccurso=<%= cd %>'" class="btn btn-danger btn-group-justified">
                                                        </td>
                                                        
                                                      </tr>
                                                      <% } %>
                                                    </tbody>
                                                </table>
                                                </div>
                                                <!-- FIM DA TABELA DAS DATAS -->                                            
                                                
                                            </div>
                                            
                                            <div class="col-sm-2"></div>
                                                  <div class="form-group"></div>
                                                  <div class="form-group"></div>


                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- botão salvar-->
                                                    <input type="submit" value="Anterior" id="anterior" name="anterior" class="btn btn-danger btn-lg">
                                                    
                                                    <div class="form-group"></div> <!-- ESPAÇO ENTRE BOTÕES

                                                    <div class="form-group"></div> <!-- ESPAÇO ENTRE BOTÕES
                                                    
                                                    <!-- campo excluir -->
                                                    <input type="submit" value="Próximo" id="proximo1" name="proximo1" class="btn btn-success btn-lg">
                                                </div>

                                           
                                        </div>
                                        <div class="col-sm-1"></div>
                                              
                                          </div> <!-- FIM TAB 3 -->

                                          <div class="tab-pane" id="tabs_4">

                                               <div class="col-sm-1"></div>
                                              
                                            <div class="col-sm-10">
                                             
                                                
                                                <div class="form-group"></div>
                                                <div class="form-group"></div>

                                                <div class="col-sm-6">
                                                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                      <!-- botão anteiro-->
                                                    <input type="submit" value="Anterior" id="bt_anterior" name="bt_anterior" class="btn btn-danger btn-lg">  
                                                    </div>  
                                                </div>
                                                  
                                                <div class="col-sm-6">
                                                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                        <input type="button" value="Enviar" id="enviar" name="enviar" onClick="submeteFormulario()" class="btn btn-success btn-lg">
                                                    </div>  
                                                </div>
                                                
                                             
                                            </div>
                                              
                                              <div class="col-sm-1"></div>


                                          </div> <!-- FIM TAB 5 -->

                                      </div>
                                              
                                  
                                  </div>
                            
                          </div> <!-- fim da tag com a CLASS PAGE_HEADER -->

                      </div>

                    </div>
                
                <!-- BLOCO RESPONSÁVEL POR EXIBIR A JANELA DE SAIR DO PERFIL -->
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
                                                    
            </div>

            <div class="col-sm-1"></div>

        </div>

        <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
        <script src="bootstrap/js/jquery_edge.js"></script>        
        <script src="bootstrap/js/navegacao_aba_criarCurso.js"></script>        
        
        <!-- ESTE SCRIPT É O RESPONSÁVEL PELA MÁSCARA NO CAMPO VALOR -->
        <script>
            document.getElementById("cx_Valor").addEventListener("change", function(){
                this.value = parseFloat(this.value).toFixed(2);
            });
        </script>
        
        <script>            	
            function submeteFormulario(){
                document.getElementById("form_curso").submit(); 
                document.getElementById("form_curso1").submit();                
            }
        </script>
        
    </body>
</html>
