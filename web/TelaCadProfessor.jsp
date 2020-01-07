<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
        
        <title>Cadastro Professor</title>
        
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/estilo.css" rel="stylesheet">
        <link href="bootstrap/js/javascript.js" rel="stylesheet">
        <link href="bootstrap/imagem/imagem_thoth.jpeg" rel="icon">
        
        <!-- SCRIPT RESPONSÁVEL POR APAGAR OS CAMPOS -->
        <script id="apaga_campos">
            document.getElementById('cx_nome_professor').value=''; // Limpa o campo
            document.getElementById('cx_email_professor').value=''; // Limpa o campo
            document.getElementById('cx_senha_professor').value=''; // Limpa o campo
            document.getElementById('cx_nome_professor').value=''; // Limpa o campo
            document.getElementById('lista_especialidade').value=''; // Limpa o campo
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
                
                 <h2>Cadastro Professor</h2> <!-- TÍTULO -->
                
                <form action="Professor_Cadastro_Controle" method="post">
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo código -->
                        <label for="cx_nome_professor">Nome:</label>                        
                        <input class="form-control" type="text" name="cx_nome_professor" id="cx_nome_professor" size="100" maxlength="200" placeholder="Informe seu nome completo" autofocus required>
                            
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_email_professor">Email:</label>
                        <input class="form-control" type="email" name="cx_email_professor" id="cx_email_professor" size="50" maxlength="50" placeholder="Informe o seu endereço de email" required>
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_senha_professor">Senha:</label>
                        <input class="form-control" type="password" name="cx_senha_professor" id="cx_senha_professor" size="50" maxlength="50" placeholder="Informe sua senha de acesso" required>
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_fone_professor">Número Telefone:</label>
                        <input class="form-control" type="tel" name="cx_fone_professor" id="cx_fone_professor" size="11" maxlength="11" placeholder="00000000000" required>
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo nome -->
                        <label for="cx_url_curriculo_prof">Adicione a URL Curriculo da Plataforma Lattes:</label>
                        <input class="form-control" type="url" name="cx_url_curriculo_prof" id="cx_url_curriculo_prof" size="200" maxlength="200" placeholder="Adicione a URL Curriculo da Plataforma Lattes" required>
                    </div>
                    
                    <div class="col-sm-5">
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                          <label for="lista_especialidade">Especialidade:</label>
                            <select class="form-control" id="lista_especialidade" name="lista_especialidade" required>
                              <option value="">Selecione uma opção</option>                          
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
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- botão salvar-->
                        <input type="submit" value="Salvar" id="salvar" name="salvar" class="btn btn-success btn-lg">
                        <!-- campo limpar -->
                        <input type="reset" onclick="apaga_campos" value="Limpar" id="novo" name="novo" class="btn btn-secondary btn-lg">
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
