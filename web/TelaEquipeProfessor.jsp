<%-- 
    Document   : TelaAreaCurso
    Created on : 11/10/2018, 21:49:11
    Author     : moise
--%>
<%-- IMPORTS DO SISTENA --%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="thoth.modelo.bean.Professor" %>
<%@page import="thoth.modelo.dao.ProfessorDao" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
        
        <title>Gerenciar Membros da Equipe</title>
        
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
        </script>
        
    </head>
    <body>
        
        <% 
           // HttpSession sessao = request.getSession();
            ProfessorDao profDao = new ProfessorDao();
            
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
                    <li><a href="TelaAlterarProfessor.jsp">Configuração de Conta</a></li>
                    <li><a data-toggle="modal" data-target="#confirm">Sair</a></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <!-- FIM BLOCO DA BARRA DE NAVEGAÇÃO DA TELA -->
        
        <div class="container-fluid">
        
            <div class="col-sm-2"> <!-- COLUNA 1 DA PÁGINA -->      
            </div>
            
            <div class="col-sm-8"> <!-- COLUNA 2 DA PÁGINA -->
              
                <h3>Adicione e Remova membros à sua equipe</h3>
                
                <form action="Equipe_Controle" method="post">
                    <div class="col-xs-11">
                        <input class="form-control" type="hidden" value="<%= professor.getCodigo() %>" name="cx_cd_curso" id="cx_cd_curso" size="4" maxlength="4" placeholder="">
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->                    
                            <input class="form-control" type="text" value="" name="cx_nome" id="cx_nome" size="120" maxlength="120" placeholder="Insira o nome do seu auxíliar/convidado da equipe" autofocus required>
                        </div>
                        </div>
                        <div class="col-xs-1">
                            <div class="form-group">
                                <input type="submit" value="+" id="salvar" name="salvar" class="btn btn-danger btn-group-sm">
                            </div>
                        </div>                        
                </form>
               </div>         
            </div>
                
            
            <div class="container-fluid">
                
                <div class="col-sm-2"></div>
                <div class="col-sm-8"> <!-- COLUNA 2 DA PÁGINA -->
                
               <div class="table-overflow">
                <table class="table table-striped table-bordered table-hover"> <!-- TABELA -->
                    <thead class="success"> <!-- O CABEÇALHO DA TABELA -->
                    
                        <td class="success">C&oacute;digo</td>
                        <td class="success">Membro da Equipe</td>
                        <td class="success"></td> <!-- BOTÃO ALTERAR -->
                        <td class="success"></td> <!-- BOTÃO EXCLUIR -->
                        
                    </thead>
                    
                    <tbody>
                        
                        <%-- SCRIPTLET - DECLARAÇÃO DO MÉTODO DAO RESPONSÁVEL POR LISTAR AS AREAS DO CURSO --%>
                        <%                          
                          for( Professor p: profDao.listarEquipe( professor.getCodigo() ) ){
                       %>
                        
                        <tr> <!-- LINHA DA TABELA -->
                            <td><%= p.getCd_equipe() %></td>
                            <td><%= p.getEquipe() %></td>
                            <td>
                                <input type="button" value="Excluir" id="excluir" name="excluir" onclick="location='http://localhost:8080/Thoth/Equipe_Controle?ODIGJ2=<%= p.getCd_equipe() %>&CR003=<%= p.getCodigo() %>'" class="btn btn-danger btn-group-justified">
                            </td>
                        </tr>
                         
                        <%
                            }
                        %>
                        
                    </tbody>
                    
                </table>
               </div> 
                
            </div>
            </div>
            
            <div class="col-sm-2"> <!-- COLUNA 1 DA PÁGINA -->      
            </div>            
                        
        </div>
        
        <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
    </body>
</html>