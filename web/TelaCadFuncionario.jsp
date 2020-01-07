<%@page import="thoth.modelo.bean.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
        
        <title>Cadastro Funcionário</title>
        
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/estilo.css" rel="stylesheet">
        <link href="bootstrap/js/javascript.js" rel="stylesheet">
        <link href="bootstrap/imagem/imagem_thoth.jpeg" rel="icon">
        
        <!-- SCRIPT RESPONSÁVEL POR APAGAR OS CAMPOS -->
        <script id="apaga_campos">
            document.getElementById('cx_nome_funcionario').value=''; // Limpa o campo
            document.getElementById('cx_email_funcionario').value=''; // Limpa o campo
            document.getElementById('cx_senha_funcionario').value=''; // Limpa o campo
            document.getElementById('lista_perfil').value=''; // Limpa o campo
            document.getElementById('cx_cpf').value=''; // Limpa o campo
            document.getElementById('cx_telefone').value=''; // Limpa o campo
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
                    <li><a href="#">Locais Disponíveis</a></li>
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
                      <li><a onclick="location='http://localhost:8080/Thoth/RotaSessaoProfessor.jsp?id=1'">Configuração de Conta</a></li>
                    <li><a data-toggle="modal" data-target="#confirm">Sair</a></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <!-- FIM BLOCO DA BARRA DE NAVEGAÇÃO DA TELA -->
        
        <div class="container-fluid">
            
            <div id="col1_cad_aluno" class="col-sm-3"> 
            </div>
            
            <div class="col-sm-6"> <!-- COLUNA 1 DA PÁGINA -->
                
                 <h2>Cadastro Funcionário</h2> <!-- TÍTULO -->
                
                <form action="Funcionario_Cadastro_Controle" method="post">
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo código -->
                        <label for="cx_nome_funcionario">Nome:</label>                        
                        <input class="form-control" type="text" name="cx_nome_funcionario" id="cx_nome_funcionario" size="100" maxlength="200" placeholder="Informe seu nome completo" autofocus required>
                            
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_email_funcionario">Email:</label>
                        <input class="form-control" type="email" name="cx_email_funcionario" id="cx_email_funcionario" size="50" maxlength="50" placeholder="Informe o seu endereço de email" required>
                    </div>
                   
                    <!-- abaixo mesma GRID --------------------------------------------------------------------->
                    
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                          <label for="lista_perfil">Tipo:</label>
                            <select class="form-control" id="lista_perfil" name="lista_perfil" required>
                              <option value="">Selecione uma opção</option>
                              <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                              <option value="PRODUÇÃO">PRODUÇÃO</option>
                            </select>
                        </div>
                   
                    
                    <div class="col-sm-6">
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                            <!-- campo nome -->
                            <label for="cx_cpf">CPF:</label>
                            <input class="form-control" type="number" name="cx_cpf" id="cx_cpf" size="11" maxlength="11" placeholder="00000000000">
                        </div>
                    </div>
                    
                    <div class="col-sm-6">
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                            <!-- campo nome -->
                            <label for="cx_telefone">Telefone:</label>
                            <input class="form-control" type="tel" name="cx_telefone" id="cx_telefone" size="11" maxlength="11" placeholder="00000000000">
                        </div>
                    </div>
                    <!-- ======================================================================================= -->
                    
                    <div class="form-group"></div>
                    <div class="form-group"></div>

                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- botão salvar-->
                        <input type="submit" value="Salvar" id="salvar" name="salvar" class="btn btn-success btn-lg">
                        <!-- campo limpar -->
                        <input type="reset" onclick="apaga_campos" value="Novo" id="novo" name="novo" class="btn btn-secondary btn-lg">
                        <!-- campo voltar -->
                        <input type="submit" value="Voltar" id="voltar" name="voltar" class="btn btn-danger btn-lg">
                    </div>

                </form>
                 
                 <div class="alert alert-success">
                     <h5>${mensagem}</h5>
                 </div>
                 
            </div>
            
            <div id="col3_cad_aluno" class="col-sm-3"> <!-- COLUNA 2 DA PÁGINA -->
            </div>
                
        </div>
        
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
                 
        <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
    </body>
</html>
