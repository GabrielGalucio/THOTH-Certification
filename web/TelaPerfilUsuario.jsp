<%-- 
    Document   : TelaPerfilUsuario
    Created on : 16/10/2018, 22:09:24
    Author     : moise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <title>Perfil Cadastro</title>
        
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/estilo.css" rel="stylesheet">
        <link href="bootstrap/js/javascript.js" rel="stylesheet">
        <link href="bootstrap/imagem/imagem_thoth.jpeg" rel="icon">
        
        
    </head>
    <body>
         
        <!-- CABEÇALHO DA PÁGINA -->
        <nav class="navbar navbar-dark bg-success">
            <div class="col-sm-5">
                <img src="bootstrap/imagem/imagem_thoth_logo.jpeg" height="80" width="80" class="img-circle img-responsive">
            </div>
            <div class="col-sm-7" >
                <h1>Thoth Certificação</h1>
            </div>
        </nav>
        
        <div class="container-fluid">
            
            <div class="col-sm-1"> 
            </div>
            
            <div class="col-sm-10"> <!-- COLUNA 1 DA PÁGINA -->
                
                <div class="container">
                    <div class="page-header">
                        <h1>CADASTRA-SE</h1>
                    </div>

                    <form>
                        <a href="TelaCadAluno.jsp"class="btn btn-success btn-lg btn-block">ALUNO</a>
                        <a href="TelaCadProfessor.jsp"class="btn btn-success btn-lg btn-block">PROFESSOR</a>
                        
                         <div class="form-group"></div>
                        
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais --> 
                            <!-- campo voltar -->
                            <a href="TelaLogin.jsp"class="btn btn-danger btn-lg">Voltar</a>
                        </div>
                    </form>                  
                </div>
            
            </div>
            
            <div class="col-sm-1"> <!-- COLUNA 2 DA PÁGINA -->
            </div>
                
        </div>
        
        <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
    </body>
</html>