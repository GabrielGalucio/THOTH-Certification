<%-- 
    Document   : TelaCadAluno
    Created on : 17/10/2018, 13:16:09
    Author     : estagiario.ti
--%>

<%@page import="thoth.modelo.bean.Aluno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1"> 
              
        <title>Configuração de Conta - Aluno</title>
        
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/estilo.css" rel="stylesheet">
        <link href="bootstrap/js/javascript.js" rel="stylesheet">
        <link href="bootstrap/imagem/imagem_thoth.jpeg" rel="icon">
        
        <!-- SCRIPT RESPONSÁVEL POR APAGAR OS CAMPOS -->
        <script id="apaga_campos">
            document.getElementById('cx_nome_aluno').value=''; // Limpa o campo
            document.getElementById('cx_email_aluno').value=''; // Limpa o campo
            document.getElementById('cx_senha_aluno').value=''; // Limpa o campo
            document.getElementById('cx_fone_aluno').value=''; // Limpa o campo
        </script>
     
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
                    <li><a href="#">Cursos em Andamento</a></li>
                    <li><a href="#">Hitórico de Cursos</a></li>                    
                  </ul>
                </li>  
               
              </ul>
              <ul class="nav navbar-nav navbar-right">
                  <li><a href="#"><span class="glyphicon glyphicon-user"></span> <%= aluno.getNome() %> </a></li>
                
                <!-- DROPDOW RESPONSÁVEL PELA OPÇÕES REFERENTES A CONTA -->
                <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
                  <ul class="dropdown-menu">
                      <li><a onclick="location='http://localhost:8080/Thoth/.jsp?id=2'">Configuração de Conta</a></li>
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
                
                 <h2>Coniguração da Conta - Aluno</h2> <!-- TÍTULO -->
                
                <form action="Aluno_Alterar_Controle" method="post">
                    
                    <input class="form-control" type="hidden" name="cx_mat_aluno" value="<%= aluno.getNumMatricula() %>" id="cx_mat_aluno" size="4" maxlength="4" placeholder="0">
                    <input class="form-control" type="hidden" name="cx_cod_aluno" value="<%= aluno.getCodigo() %>" id="cx_cod_aluno" size="4" maxlength="4" placeholder="0">
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo código -->
                        <label for="cx_mat_aluno">Matrícula:</label>                        
                        <input class="form-control" type="number" name="cx_mat_aluno" value="<%= aluno.getNumMatricula() %>" id="cx_mat_aluno" size="4" maxlength="4" placeholder="0" disabled="">
                            
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo código -->
                        <label for="cx_nome_aluno">Nome:</label>                        
                        <input class="form-control" type="text" name="cx_nome_aluno" value="<%= aluno.getNome() %>" id="cx_nome_aluno" size="100" maxlength="200" placeholder="Informe seu nome completo" autofocus required>
                            
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_email_aluno">Email:</label>
                        <input class="form-control" type="email" name="cx_email_aluno" value="<%= aluno.getEmail() %>" id="cx_email_aluno" size="50" maxlength="50" placeholder="Informe o seu endereço de email" required>
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_senha_aluno">Senha:</label>
                        <input class="form-control" type="password" name="cx_senha_aluno" id="cx_senha_aluno" value="" size="50" maxlength="50" placeholder="Informe sua senha de acesso" required>
                    </div>
                    
                    <div class="col-xs-4">
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                            <!-- campo nome -->
                            <label for="cx_fone_aluno">Número Telefone:</label>
                            <input class="form-control" type="tel" name="cx_fone_aluno" value="<%= aluno.getFone() %>" id="cx_fone_aluno" size="11" maxlength="11" placeholder="00000000000" required>
                        </div>
                    </div>
                    <div class="col-xs-2"></div>
                    <div class="col-xs-6">
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                          <label for="status_conta">Status da Conta - [A] Ativo - [I] Inativo</label>
                            <select class="form-control" id="lista_especialidade" name="status_conta" required>
                                <option selected value="<%= aluno.getStatus() %>"><%= aluno.getStatus() %></option>                          
                              <option value="A">A</option>  
                              <option value="I">I</option>
                            </select>
                        </div>                        
                    </div>    
                        
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- botão salvar-->
                        <input type="submit" value="Salvar" id="salvar" name="salvar" class="btn btn-success btn-lg">
                        <!-- campo limpar -->
                        <input type="reset" onclick="apaga_campos" value="Limpar" id="limpar" name="limpar" class="btn btn-secondary btn-lg">
                        <!-- campo voltar -->
                        <a type="reset" href="TelaPerfilAluno.jsp" class="btn btn-danger btn-lg">Voltar</a>
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