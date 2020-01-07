<%-- 
    Document   : TelALocalizacaoAlterar
    Created on : 19/10/2018, 19:56:28
    Author     : moise
--%>

<%@page import="thoth.modelo.dao.LocalizacaoDao"%>
<%@page import="thoth.modelo.bean.Localizacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
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
        
    </head>
    <body>
         
        <!-- CABEÇALHO DA PÁGINA -->
        <nav class="navbar navbar-dark bg-success"> 
            <div class="col-sm-4">
                <img src="bootstrap/imagem/imagem_thoth_logo.jpeg" height="80" width="80" class="img-circle img-responsive">
            </div>
            <div class="col-sm-8" >
                <h1>Locais de Realiza&ccedil;&atilde;o do Curso</h1>
            </div>
        </nav>
        
        
        <div class="container-fluid">
            
             <div class="col-sm-3"> <!-- COLUNA 2 DA PÁGINA -->        
            </div>
            
            <div class="col-sm-6"> <!-- COLUNA 1 DA PÁGINA -->
                
                 <h2>Locais do Curso - ALTERAÇÃO</h2> <!-- TÍTULO -->
                
                <form action="Localizacao_Alterar_Controle" method="post">
                    
                    <% 
                     Localizacao local = new Localizacao();
                     LocalizacaoDao localDao = new LocalizacaoDao();
                     
                     local = localDao.pesquisarLocalizacao( Long.parseLong(request.getParameter("id") ) );
                    
                     if( local != null ){
                    %>
                    
                    <div class="col-xs-0">
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                            <!-- campo código -->
                                                   
                            <input class="form-control" type="hidden" name="cx_codigo" value="<%= local.getCodigo() %>" id="cx_codigo" size="4" maxlength="4" placeholder="0">
                       
                    </div>
                    <div class="col-xs-12">
                        
                        <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                            <!-- campo nome -->
                            <label for="cx_nome">Nome Local:</label>
                            <input class="form-control" type="text" name="cx_nome" id="cx_nome" value="<%= local.getNome() %>" size="80" maxlength="80" placeholder="Informe a descrição da área" autofocus required>
                        </div>

                    </div>
                    
                    <!-- GERA DUAS COLUNAS PARA PÔR QTDE VAGA e CEP um no lado do outro -->
                        <div class="col-xs-4">
                            <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                <!-- campo QTDE VAGA -->
                                <label for="cx_qtde_vaga">Quant. Vaga:</label>
                                <input class="form-control" type="number" name="cx_qtde_vaga" id="cx_qtde_vaga" value="<%= local.getQtdeVaga() %>" min="1" size="4" value="1" maxlength="4" placeholder="Informe a quantidade de vagas"required>
                            </div>
                        </div>
                        <div class="col-xs-4">
                             <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                <!-- campo CEP -->
                                <label for="cx_cep">CEP:</label>
                                <input class="form-control" type="text" name="cx_cep" id="cx_cep" value="<%= local.getCep() %>" size="8" maxlength="8" placeholder="00000000"required>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                                <!-- campo NÚMERO DO LOCAL -->
                                <label for="cx_numero_local">Número local:</label>
                                <input class="form-control" type="number" name="cx_numero_local" id="cx_numero_local" value="<%= local.getNumero() %>" min="0" size="4" value="1" maxlength="4"required>
                            </div>
                        </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo logradouro -->
                        <label for="cx_logradouro">Nome Lograduro:</label>
                        <input class="form-control" type="text" name="cx_logradouro" id="cx_logradouro" value="<%= local.getLogradouro() %>" size="50" maxlength="50" placeholder="Informe o logradouro do local" autofocus required>
                    </div>
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- campo bairro -->
                        <label for="cx_bairro">Nome Bairro:</label>
                        <input class="form-control" type="text" name="cx_bairro" id="cx_bairro" value="<%= local.getBairro() %>" size="100" maxlength="100" placeholder="Informe o bairro do local" autofocus required>
                    </div>
                    
                    <!-- BOTÕES -->
                    
                    <div class="form-group"> <!-- agrupará e vai separar o label do campo e dos demais -->
                        <!-- botão salvar-->
                        <input type="submit" value="Salvar" id="salvar" name="salvar" class="btn btn-success btn-lg">
                        <!-- campo voltar -->
                        <input type="reset" value="Voltar" id="voltar" name="voltar" onclick="location='http://localhost:8080/Thoth/TelaLocalizacao.jsp'" class="btn btn-danger btn-lg">
                    </div>
                    
                    <% 
                    }else{
                        System.out.println("Local não encontrado !!!");
                    }
                    %>
                    
                </form>
                 
                 <div class="alert alert-success">
                     <h5>${mensagem}</h5>
                 </div>    
                    
            </div>
            
            <div class="col-sm-3"> <!-- COLUNA 2 DA PÁGINA -->        
            </div>
                
        </div>
        
        <!-- FIM DO CÓDIGO -->
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/script.js"></script>
    </body>
</html>
