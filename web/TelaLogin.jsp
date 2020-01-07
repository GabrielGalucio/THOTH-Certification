<%-- 
    Document   : TelaLogin
    Created on : 27/10/2018, 18:47:25
    Author     : moise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
        
        <title>Tela Login</title>

        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/estilo.css" rel="stylesheet">
        <link href="bootstrap/js/javascript.js" rel="stylesheet">
        <link href="bootstrap/imagem/imagem_thoth.jpeg" rel="icon">
        <link href="bootstrap/imagem/avatar_login.png" rel="icon">
        <link href="bootstrap/js/jquery.js" rel="stylesheet">
        <link href="bootstrap/js/jquery_edge.js" rel="stylesheet">
        <link href="bootstrap/css/login.css" rel="stylesheet">        
        
        <link href="bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script> 
        <script src="bootstrap/js/login.js"></script>
        
    </head>
    <body>
    <div class="container">
        <div class="card card-container">
            
            <!-- CABEÇALHO DO LOGIN -->
            <img id="profile-img" class="profile-img-card" src="bootstrap/imagem/avatar_login.png" />
            <p id="profile-name" class="profile-name-card">THOTH CERTIFICAÇÕES</p>
            
            <form class="form-signin" action="Login_Controle" method="post">
                <span id="reauth-email" class="reauth-email"></span>
                <input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
                <div id="remember" class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Acessar</button>
            </form><!-- /form -->
            <a href="#" class="forgot-password">
                Esqueceu sua senha?
            </a>
            <div></div>
            <div></div>
            <a href="TelaPerfilUsuario.jsp" class="forgot-password">
                Cadastra-se
            </a>
            <div></div>
            <div class="alert alert-success">
               <h5>${mensagem}</h5>
            </div>
        </div>
    </div>
    </body>
</html>
