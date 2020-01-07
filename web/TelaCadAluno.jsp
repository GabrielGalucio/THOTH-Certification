<%-- 
    Document   : TelaCadAluno
    Created on : 17/10/2018, 13:16:09
    Author     : estagiario.ti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1"> 
              
        <title>Cadastro Aluno</title>
        
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
            
            <div id="col1_cad_aluno" class="col-sm-3"> 
            </div>
            
            <div class="col-sm-6"> <!-- COLUNA 1 DA PÁGINA -->
                
                 <h2>Cadastro Aluno</h2> <!-- TÍTULO -->
                
                <form action="Aluno_Cadastro_Controle" method="post">
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo código -->
                        <label for="cx_nome_aluno">Nome:</label>                        
                        <input class="form-control" type="text" name="cx_nome_aluno" id="cx_nome_aluno" size="100" maxlength="200" placeholder="Informe seu nome completo" autofocus required>
                            
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_email_aluno">Email:</label>
                        <input class="form-control" type="email" name="cx_email_aluno" id="cx_email_aluno" size="50" maxlength="50" placeholder="Informe o seu endereço de email" required>
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_senha_aluno">Senha:</label>
                        <input class="form-control" type="password" name="cx_senha_aluno" id="cx_senha_aluno" size="50" maxlength="50" placeholder="Informe sua senha de acesso" required>
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_fone_aluno">Número Telefone:</label>
                        <input class="form-control" type="tel" name="cx_fone_aluno" id="cx_fone_aluno" size="11" maxlength="11" placeholder="00000000000" pattern="[0-9]{10,11}" required>
                    </div>
                                
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- botão salvar-->
                        <input type="submit" value="Salvar" id="salvar" name="salvar" class="btn btn-success btn-lg">
                        <!-- campo limpar -->
                        <input type="reset" onclick="apaga_campos" value="Limpar" id="limpar" name="limpar" class="btn btn-secondary btn-lg">
                        <!-- campo voltar -->
                        <a type="reset" href="TelaPerfilUsuario.jsp" class="btn btn-danger btn-lg">Voltar</a>
                    </div>

                </form>
                 
                 <div class="alert alert-success">
                     <h3>${mensagem}</h3>
                 </div>
                 
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