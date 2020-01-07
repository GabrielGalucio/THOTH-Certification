<%@page import="thoth.modelo.bean.Professor"%>
<%@page import="thoth.modelo.dao.ProfessorDao"%>
<%@page import="thoth.modelo.dao.LocalizacaoDao"%>
<%@page import="thoth.modelo.bean.Localizacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-BR">
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />

        <title>Criar Curso</title>

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
                  <li><a href="#"><span class="glyphicon glyphicon-user"></span><%= professor.getNome() %></a></li>
                
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
                    ProfessorDao profDao = new ProfessorDao();
                %>
                
               
                <div class="row"> <!-- LINHA DE OPÇÕES DAS ABAS TAGS -->
                   
                      <div class="span12">

                          <div class="page-header">

                                  <div class="tabbable" id="tabs">

                                      <ul class="nav nav-tabs"> <!-- BARRA DE OPÇÕES DAS ABAS TAGS -->

                                          <li class="active"><a href="#tabs-1" data-toggle="tab">INTRODUÇÃO</a></li>
                                          <li><a href="#tabs-2" data-toggle="tab">CARACTERÍSTICAS</a></li>
                                          <li><a href="#tabs-3" data-toggle="tab">PERIODO DE REALIZAÇÃO</a></li>
                                          <li><a href="#tabs-4" data-toggle="tab">FINALIZAR</a></li>

                                      </ul>

                                    <form action="Curso_Cadastro_Controle" method="post" id="form_curso"> <!-- FORMULÁRIO - ABA INTRODUÇÃO -->
                                      <div class="tab-content"> <!-- CONTEÚDO DAS TABS -->

                                          <div class="tab-pane active" id="tabs-1">                                            


                                                <div class="form-group"></div>
                                                <div class="form-group"></div>
                                                <input class="form-control" type="hidden" name="cx_prof" value="<%= professor.getCodigo() %>" id="cx_prof" size="4" maxlength="4" placeholder="0">
                                                <div class="col-sm-2"> <!-- COLUNA 1 Do CAMPO CÓDIGO -->

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- campo código -->
                                                    <label for="cx_codigo">C&oacute;digo:</label>
                                                    <input class="form-control" type="number" name="cx_codigo" id="cx_codigo" size="4" maxlength="4" placeholder="0" disabled>

                                                </div>
                                                 </div> <!-- FIM DA GRID DA COLUNA DO CAMPO -->
                                                <div class="col-sm-10"> <!-- COLUNA 2 Do CAMPO TÍTULO CURSO -->

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- campo título -->
                                                    <label for="cx_titulo">T&iacute;tulo do Curso:</label>
                                                    <input class="form-control" type="text" name="cx_titulo" id="cx_titulo" size="50" maxlength="150" placeholder="Informe o nome de seu projeto de curso" autofocus required>
                                                </div>
                                                </div> <!-- FIM DA GRID DA COLUNA DO CAMPO -->

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- campo objetivos -->
                                                    <label for="txt_objetivo">Objetivos:</label>
                                                    <textarea class="form-control" name="txt_objetivo" id="txt_objetivo" placeholder="Apresente os objetivos geral, específico e os pretendidos." required></textarea>
                                                </div>

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- campo metolodia -->
                                                    <label for="txt_metodologia">Metodologia:</label>
                                                    <textarea class="form-control" name="txt_metodologia" id="txt_metodologia" placeholder="Apresente a estratégia de abordagem, técnicas de modelos adotados e os métodos que serão utilizados para a avaliação dos alunos." required></textarea>
                                                </div>

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- campo ementa -->
                                                    <label for="txt_ementa">Ementa:</label>
                                                    <textarea class="form-control" name="txt_ementa" id="txt_ementa" placeholder="Apresente todo o conteúdo a ser abordado no curso." required></textarea>
                                                </div>

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- campo bibliografia -->
                                                    <label for="txt_bibliografia">Bibliografia:</label>
                                                    <textarea class="form-control" name="txt_bibliografia" id="txt_bibliografia" placeholder="Apresente as referências utilizadas no curso." required></textarea>
                                                </div>

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- botão salvar-->
                                                    <input type="submit" value="Cancelar" id="cancelar" name="cancelar" class="btn btn-danger btn-lg">
                                                    <!-- campo limpar -->
                                                    <input type="button" value="Limpar" id="limpar" name="limpar" onclick="return limparCamposTabIntroducao()" class="btn btn-secondary btn-lg">
                                                    <!-- campo excluir -->
                                                    <input type="button" value="Próximo" id="proximo1" name="proximo1" onclick="return navegacao()" class="btn btn-success btn-lg">
                                                </div>

                                            

                                          </div> <!-- FIM TAB 1 -->

                                          <div class="tab-pane" id="tabs-2">
                                              
                                            <div class="col-sm-1"></div>
                                              
                                            <div class="col-sm-10">
                                             

                                                <div class="form-group"></div>
                                                <div class="form-group"></div>

                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                <label for="lista_area">Áreas de Conhecimento:</label>
                                                  <select class="form-control" id="lista_area" name="lista_area"  required>
                                                    <option selected value="">Selecione uma opção</option>
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
                                                        <option value="">Selecione uma opção</option>    
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
                                                        <option value="">Selecione uma opção</option>
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
                                                            <input class="form-control" type="number" name="cx_qtdeVaga" id="cx_qtdeVaga" size="4" maxlength="4" placeholder="0">

                                                        </div>
                                                      </div> <!-- FIM DA GRID DA COLUNA DO CAMPO -->
                                                
                                                      <div class="col-sm-2"> <!-- COLUNA 2 Do CAMPO TÍTULO CURSO -->
                                                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                            <!-- campo valor do curso -->
                                                            <label for="cx_Valor">Valor R$:</label>
                                                            <input class="form-control" type="number" step="0.010" name="cx_Valor" id="cx_Valor" size="4" maxlength="4" placeholder="0,00">

                                                        </div>
                                                      </div>
                                                      
                                                      <div class="col-sm-2">
                                                          <!-- carga horária do curso -->
                                                            <label for="cx_cargaHoraria">Carga Horária:</label>
                                                            <input class="form-control" type="number" name="cx_cargaHoraria" id="cx_cargaHoraria" size="4" maxlength="4" placeholder="0">
                                                      </div>
                                                     <!-- fim destes campos retratados -->

                                                 <div class="col-sm-6">
                                                
                                                  
                                                    <div class="form-group"></div>
                                                    <div class="form-group"></div>
                                                    <div class="form-group"></div>
                                                
                                                  <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                      <!-- botão salvar-->
                                                      <input type="submit" value="Anterior" id="anterior" name="anterior" class="btn btn-danger btn-lg">
                                                      <!-- campo limpar -->
                                                      <input type="submit" value="Limpar" id="limpar" name="limpar" class="btn btn-secondary btn-lg">
                                                      <!-- campo excluir -->
                                                      <input type="submit" value="Próximo" id="proximo1" name="proximo1" class="btn btn-success btn-lg">
                                                  </div>
                                                </div>
                                             
                                            </div>
                                               
                                              <div class="col-sm-1"></div>

                                          </div> <!-- FIM TAB 2 -->

                                          <div class="tab-pane" id="tabs-3">
                                            
                                            <div class="col-sm-1"></div>
                                              
                                        <div class="col-sm-10">
                                                
                                            

                                              <div class="form-group"></div>
                                              <div class="form-group"></div>
                                              
                                              <div class="col-sm-0"></div>
                                            
                                            <div class="col-sm-10">
                                                
                                                <div class="form-group">
                                                    <h4>Per&iacute;odo de Realizaç&atilde;o</h4>
                                                </div>
                                                
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
                                                
                                                <div class="col-xs-1"> <!-- coluna 4 - BOTÃO -->
                                                    <div class="form-group"> 
                                                    <label></label>
                                                        <button type="button" id="adicionar_periodo" class="btn btn-primary btn-lh">+</button>
                                                    </div>
                                                </div> <!-- fim coluna 4 - BOTÃO -->
                                                
                                                <!-- TABELA DAS DATAS -->
                                                <h4>Informe a quantidade de vagas por turno</h4>
                                                
                                                <div class="table-overflow">
                                                <table class="table table-striped table-bordered table-hover" id="tabela_periodo">
                                                
                                                  <thead>
                                                      <td class="success">Dia(s) adicionado(s)</td>
                                                      <td class="success">Hora In&iacute;cio</td>
                                                      <td class="success">Hora Término</td>
                                                      <td class="success"></td>
                                                </thead>
                                                  <tbody>
                                                      <tr> <!-- LINHA 1 DA TABELA -->
                                                          
                                                        <td colspan="4" align="center" id="cesta_vazia">
                                                            nenhum período adicionado
                                                        </td>
                                                        
                                                      </tr>

                                                    </tbody>
                                                </table>
                                                </div>
                                                <!-- FIM DA TABELA DAS DATAS -->                                            
                                                 <input type="hidden" name="lista_periodo_array" id="lista_periodo_array" value="" />
                                            </div>
                                            
                                            <div class="col-sm-2"></div>
                                                  <div class="form-group"></div>
                                                  <div class="form-group"></div>


                                                <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                                    <!-- botão salvar-->
                                                    <input type="submit" value="Anterior" id="anterior" name="anterior" class="btn btn-danger btn-lg">
                                                    
                                                    <div class="form-group"></div> <!-- ESPAÇO ENTRE BOTÕES

                                                    <!-- campo limpar -->
                                                    <input type="submit" value="Limpar" id="limpar" name="limpar" class="btn btn-secondary btn-lg">
                                                    
                                                    <div class="form-group"></div> <!-- ESPAÇO ENTRE BOTÕES
                                                    
                                                    <!-- campo excluir -->
                                                    <input type="submit" value="Próximo" id="proximo1" name="proximo1" class="btn btn-success btn-lg">
                                                </div>

                                           
                                        </div>
                                        <div class="col-sm-1"></div>
                                              
                                          </div> <!-- FIM TAB 3 -->

                                          <div class="tab-pane" id="tabs-4">

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
                                                  <input type="submit" value="Enviar" id="enviar" name="enviar" class="btn btn-success btn-lg">
                                                    </div>  
                                                </div>
                                                
                                             
                                            </div>
                                              
                                              <div class="col-sm-1"></div>


                                          </div> <!-- FIM TAB 5 -->

                                      </div>
                                        
                                    </form>
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

            <div class="col-sm-1"></div>

        </div>

        <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
        <script src="bootstrap/js/jquery_edge.js"></script>        
        <script src="bootstrap/js/navegacao_aba_criarCurso.js"></script>
        
        <script src="bootstrap/js/carregarRemoverItemTabelaPeriodoCurso.js"></script>
        
        <!-- ESTE SCRIPT É O RESPONSÁVEL PELA MÁSCARA NO CAMPO VALOR -->
        <script>
            document.getElementById("cx_Valor").addEventListener("change", function(){
                this.value = parseFloat(this.value).toFixed(2);
            });
        </script>
        
    </body>
</html>
