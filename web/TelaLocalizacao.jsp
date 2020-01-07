<%-- 
    Document   : TelaLocalizacao
    Created on : 17/10/2018, 22:21:21
    Author     : moise
--%>

<%@page import="thoth.modelo.bean.Funcionario"%>
<%@page import="thoth.modelo.bean.Professor"%>
<%@page import="thoth.modelo.bean.Localizacao"%>
<%@page import="thoth.modelo.dao.LocalizacaoDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <title>Gerenciador de Locais do Curso</title>
        
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/estilo.css" rel="stylesheet">
        <link href="bootstrap/js/javascript.js" rel="stylesheet">
        <link href="bootstrap/imagem/imagem_thoth.jpeg" rel="icon">
        
        <style>
            /* PÕE A BARRA DE ROLAGEM VERTICAL NA TABELA LOCAL */
            .table-overflow {
                max-height:400px;
                overflow-y:auto;
            }
        </style>
        
        <!-- SCRIPT RESPONSÁVEL POR APAGAR OS CAMPOS -->
        <script id="apaga_campos">
            document.getElementById('cx_codigo').value=''; // Limpa o campo
            document.getElementById('cx_nome').value=''; // Limpa o campo
            document.getElementById('cx_qtde_vaga').value=''; // Limpa o campo
            document.getElementById('cx_cep').value=''; // Limpa o campo
            document.getElementById('cx_numero_local').value=''; // Limpa o campo
            document.getElementById('cx_logradouro').value=''; // Limpa o campo
            document.getElementById('cx_bairro').value=''; // Limpa o campo
        </script>
        
        <script>
            function Mudarestado(el) {
              //  alert(el); 
                var atributo = el;
 
               // this.getAtributo = function () {
                    return atributo;
             //   };
            }
        </script>
      
    </head>
    <body>
         
        <%
            Funcionario funcionario = (Funcionario) session.getAttribute("funcionario");

            if( funcionario == null ){
                response.sendRedirect("TelaLogin.jsp");
            }
        %>
        <!-- CABEÇALHO DA PÁGINA -->
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
                <li class=""><a href="TelaPerfilFuncionario.jsp">Home</a></li>
                
                <!-- DROPDOW RESPONSÁVEL PELAS OPÇÕES DO MENU PRINCIPAL -->
                <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Menu principal<span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a onclick="location='http://localhost:8080/Thoth/RotaSessaoFuncionario.jsp?id=1'">Gerenciar Locais do Curso</a></li>                    
                  </ul>
                </li>
                
              </ul>
              <ul class="nav navbar-nav navbar-right">
                  <li><a href="#"><span class="glyphicon glyphicon-user"></span> <%= funcionario.getNome() %> </a></li>
                
                <!-- DROPDOW RESPONSÁVEL PELA OPÇÕES REFERENTES A CONTA -->
                <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a data-toggle="modal" data-target="#confirm1">Sair</a></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <!-- FIM BLOCO DA BARRA DE NAVEGAÇÃO DA TELA -->
        
        <div class="container-fluid">
        
            <div class="col-sm-4"> <!-- COLUNA 1 DA PÁGINA -->
                
                 <h2>Locais do Curso</h2> <!-- TÍTULO -->
                
                <form action="Localizacao_Cadastro_Controle" method="post">
                
                <%  
                LocalizacaoDao localDao = new LocalizacaoDao();             
                %>    
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo código -->
                        <label for="cx_codigo">C&oacute;digo:</label>                        
                        <input class="form-control" type="number" value="" name="cx_codigo" id="cx_codigo" size="4" maxlength="4" placeholder="0" disabled>
                    </div>
                                                           
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_nome">Nome Local:</label>
                        <input class="form-control" type="text" value="" name="cx_nome" id="cx_nome" size="80" maxlength="80" placeholder="Informe a descrição da área" autofocus required>
                    </div>
                    
                    <!-- GERA DUAS COLUNAS PARA PÔR QTDE VAGA e CEP um no lado do outro -->
                        <div class="col-xs-4">
                            <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                <!-- campo QTDE VAGA -->
                                <label for="cx_qtde_vaga">Quant. Vaga:</label>
                                <input class="form-control" type="number" value="" name="cx_qtde_vaga" id="cx_qtde_vaga" min="1" size="4" value="1" maxlength="4" placeholder="Informe a quantidade de vagas"required>
                            </div>
                        </div>
                        <div class="col-xs-4">
                             <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                <!-- campo CEP -->
                                <label for="cx_cep">CEP:</label>
                                <input class="form-control" type="text" value="" name="cx_cep" id="cx_cep" size="8" maxlength="8" placeholder="00000000"required>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                <!-- campo NÚMERO DO LOCAL -->
                                <label for="cx_numero_local">Número local:</label>
                                <input class="form-control" type="number" value="" name="cx_numero_local" id="cx_numero_local" min="0" size="4" value="1" maxlength="4"required>
                            </div>
                        </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo logradouro -->
                        <label for="cx_logradouro">Nome Lograduro:</label>
                        <input class="form-control" type="text" value="" name="cx_logradouro" id="cx_logradouro" size="50" maxlength="50" placeholder="Informe o logradouro do local" autofocus required>
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo bairro -->
                        <label for="cx_bairro">Nome Bairro:</label>
                        <input class="form-control" type="text" value="" name="cx_bairro" id="cx_bairro" size="100" maxlength="100" placeholder="Informe o bairro do local" autofocus required>
                    </div>
                    
                    <!-- BOTÕES -->
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- botão salvar-->
                        <input type="submit" value="Salvar" id="salvar" name="salvar" class="btn btn-success btn-lg">
                        <!-- campo pesquisar -->
                        <input type="submit" value="Pesquisar" id="pesquisar" name="pesquisar" class="btn btn-secondary btn-lg">
                        <!-- campo limpar -->
                        <input type="reset" value="Novo" onclick="apaga_campos" id="novo" name="novo" class="btn btn-secondary btn-lg">
                        <!-- campo excluir -->
                        <input type="submit" value="Excluir" id="excluir" name="excluir" class="btn btn-danger btn-lg" disabled>
                    </div>

                </form>
                  
                 <div class="alert alert-success">
                     <h5>${mensagem}</h5>
                 </div>
                 
            </div>
            
            <div class="col-sm-8"> <!-- COLUNA 2 DA PÁGINA -->
                              
                <h1>Lista de Locais de Curso</h1>
                
                <div class="form-group input-group">
                    <span class="input-group-addon">
                        <i class="glyphicon glyphicon-search"></i>
                    </span>
                    <input name="consulta" id="txt_consulta" placeholder="Consultar por nome do local" type="text" class="form-control">
                </div>
                
                <div class="table-overflow">
                <table class="table table-striped table-bordered table-hover"> <!-- TABELA -->
                    <thead class="success"> <!-- O CABEÇALHO DA TABELA -->
                    
                        <td class="success">C&oacute;digo</td>
                        <td class="success">Nome Local</td>
                        <td class="success">CEP</td>
                        <td class="success">Logradouro</td>
                        <td class="success">Bairro</td>
                        <td class="success">Nº Local</td>
                        <td class="success">Quant. Vaga</td>
                        <td class="success"></td> <!-- BOTÃO ALTERAR -->
                        <td class="success"></td> <!-- BOTÃO EXCLUIR -->
                        
                    </thead>
                    
                    <tbody>
                        
                        <% 
                       long cd = 0;
                        for(Localizacao l: localDao.listarLocalizacao()){ 
                            
                        %>
                        <tr> <!-- LINHA 1 DA TABELA -->
                            <td><%= l.getCodigo() %></td>
                            <td><%= l.getNome() %></td>
                            <td><%= l.getCep() %></td>
                            <td><%= l.getLogradouro() %></td>
                            <td><%= l.getBairro() %></td>
                            <td><%= l.getNumero() %></td>
                            <td><%= l.getQtdeVaga() %></td>
                            <td>
                                <input type="button" value="Alterar" id="alterar" name="alterar" onclick="location='http://localhost:8080/Thoth/TelaLocalizacaoAlterar.jsp?id=<%= l.getCodigo() %>'" class="btn btn-secondary btn-sm">
                            </td>
                            <td>
        <!-- ========================================================================================================== -->  
                                
        <input type="button" value="Excluir" id="excluir" name="excluir" onclick="location='http://localhost:8080/Thoth/Localizacao_Excluir_Controle?cd=<%= l.getCodigo() %>'" class="btn btn-danger btn-sm">
                            <!-- BLOCO DE VALIDAÇÃO E EXCLUSÃO DE REGISTRO DA TABELA -->
                             
                             <!--   <button type="button" name="excluir" id="excluir" class="btn btn-danger btn-sm" data-toggle="modal" onclick=" " data-target="#confirm">Excluir</button>     -->                        
                                
                                <div class="modal fade" id="confirm" name="confirm" role="dialog">
                                <div class="modal-dialog modal-md">                                 
                                  <div class="modal-content">
                                    <div class="modal-body">
                                        <p> DESEJA REALMENTE EXCLUIR ESTE REGISTRO: <%= cd %> ? </p>
                                    </div>
                                    <div class="modal-footer">
                                      <input type="button" value="Excluir" id="excluir" name="excluir" onclick="location='http://localhost:8080/Thoth/Localizacao_Excluir_Controle1?cd=Mudarestado.getAtributo()'" class="btn btn-danger btn-sm">
                                      <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">Não Excluir</button>
                                    </div>
                                  </div>

                                    </div>
                                  </div>  <!-- BLOCO DE EXCLUSÃO --> 
           <!-- ========================================================================================================== -->                          
                         
                            </td>
                        </tr>
                        
                        <% } %>
                        
                    </tbody>
                    
                </table>
           
            <!-- ========================================================================================= -->
                <div class="modal fade" id="confirm1" name="confirm1" role="dialog">
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
        
          <!-- ==================================================================================== -->
                        
                </div>
                
            </div>
                      
        </div>
        
        <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
    </body>
</html>