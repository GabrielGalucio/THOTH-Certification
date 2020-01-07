<%-- 
    Document   : TelaAlterarProfessor
    Created on : 29/10/2018, 11:37:16
    Author     : moise
--%>
<%@page import="thoth.modelo.dao.ProfessorDao"%>
<%@page import="thoth.modelo.bean.Professor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <title>Configuração de Conta</title>
        
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/estilo.css" rel="stylesheet">
        <link href="bootstrap/js/javascript.js" rel="stylesheet">
        <link href="bootstrap/imagem/imagem_thoth.jpeg" rel="icon">
        
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
            
            <div id="col1_cad_aluno" class="col-sm-3"> 
            </div>
            
            <div class="col-sm-6"> <!-- COLUNA 1 DA PÁGINA -->
                
                 <h2>Configuração da Conta</h2> <!-- TÍTULO -->
                
                <form action="Professor_Alterar_Controle" method="post">
                    
                                     
                    <div class="form-group">
                        <input class="form-control" type="hidden" name="cx_prof" value="<%= professor.getCodigo() %>" id="cx_prof" size="4" maxlength="4" placeholder="0">
                        <input class="form-control" type="hidden" name="cx_codigo" id="cx_codigo" value="<%= professor.getCodigo() %>" size="100" maxlength="200">
                    </div>
                   
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo código -->
                        <label for="cx_nome_professor">Nome:</label>                        
                        <input class="form-control" type="text" name="cx_nome_professor" id="cx_nome_professor" value="<%= professor.getNome() %>" size="100" maxlength="200" placeholder="Informe seu nome completo" autofocus required>
                            
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_email_professor">Email:</label>
                        <input class="form-control" type="email" name="cx_email_professor" id="cx_email_professor" value="<%= professor.getEmail() %>" size="50" maxlength="50" placeholder="Informe o seu endereço de email" required>
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_senha_professor">Senha:</label>
                        <input class="form-control" type="password" name="cx_senha_professor" id="cx_senha_professor" value="" size="50" maxlength="50" placeholder="Informe sua senha de acesso" required>
                    </div>
                    
                    <div class="col-xs-4">
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_fone_professor">Número Telefone:</label>
                        <input class="form-control" type="tel" name="cx_fone_professor" id="cx_fone_professor" value="<%= professor.getFone() %>" size="11" maxlength="11" placeholder="00000000000" required>
                    </div>
                    </div>
                    <div class="col-xs-2"></div>
                    <div class="col-xs-6">
                        
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                          <label for="status_conta">Status da Conta - [A] Ativo - [I] Inativo</label>
                            <select class="form-control" id="lista_especialidade" name="status_conta" required>
                                <option selected value="<%= professor.getStatus() %>"><%= professor.getStatus() %></option>                          
                              <option value="A">A</option>  
                              <option value="I">I</option>
                            </select>
                        </div>
                        
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_url_curriculo_prof">Adicione a URL Curriculo da Plataforma Lattes:</label>
                        <input class="form-control" type="url" name="cx_url_curriculo_prof" id="cx_url_curriculo_prof" value="<%= professor.getCurrilo() %>" size="200" maxlength="200" placeholder="Adicione a URL Curriculo da Plataforma Lattes" required>
                    </div>
                    
                    <div class="col-sm-5">
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                          <label for="lista_especialidade">Especialidade:</label>
                            <select class="form-control" id="lista_especialidade" name="lista_especialidade" required>
                                <option value="<%= professor.getEspecialidade() %>"><%= professor.getEspecialidade() %></option>                          
                              <option value="AUTÔNOMO">AUTÔNOMO</option>    
                              <option value="DOUTORADO">DOUTORADO</option>
                              <option value="ESPECIALIZAÇÃO">ESPECIALIZAÇÃO</option>
                              <option value="GRADUAÇÃO">GRADUAÇÃO</option>
                              <option value="MESTRADO">MESTRADO</option>
                              <option value="PHD">PHD</option>
                              <option value="TÉCNICO">TÉCNICO</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="col-sm-7">
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                            <!-- campo nome -->
                            <label for="cx_complemento_especialiadade">Especificação de Especialidade:</label>
                            <input class="form-control" type="text" name="cx_complemento_especialiadade" id="cx_complemento_especialiadade" size="100" maxlength="100" placeholder="Especifique a especialização escolhida">
                        </div>
                    </div>
                    
                    <div class="col-xs-4">
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- botão salvar-->
                        <input type="submit" value="Salvar" id="salvar" name="salvar" class="btn btn-success btn-lg">
                        <!-- campo voltar -->
                        <a type="reset" href="TelaPerfilProfessor.jsp" class="btn btn-danger btn-lg">Voltar</a>
                    </div>
                    </div>
                   
                    <div class="col-xs-8">
                        <div class="alert alert-success">
                             <h5>${mensagem}</h5>
                        </div>
                    </div>
                   
                </form>
                
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
          </div>  <!-- BLOCO DE EXCLUSÃO --> 
                        
            </div>
            
            <div id="col3_cad_aluno" class="col-sm-3"> <!-- COLUNA 2 DA PÁGINA -->
                
            </div>
                
        </div>
        
        <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
    </body>
</html>