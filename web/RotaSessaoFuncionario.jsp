                                                                                                                                                                                                                                                                                                                                                                                   <%
    
    int id = Integer.parseInt( request.getParameter("id") );
    
    if( id == 1){
        
        response.sendRedirect("TelaLocalizacao.jsp");
        
    }else if( id == 2 ){
        
        response.sendRedirect("TelaAlterarFuncionario.jsp");
        
    }else if( id == 3 ){
        
        response.sendRedirect("TelaCriarCurso.jsp");
        
    }else if( id == 4 ){
        
        response.sendRedirect("TelaListarProfessor.jsp");
        
    }else if( id == 5 ){
        
        response.sendRedirect("TelaCadFuncionario.jsp");
        
    }else if( id == 6 ){
        
        response.sendRedirect("TelaFuncionarioCursosAbertos.jsp");
        
    }else if( id == 7 ){
        
        response.sendRedirect("TelaFuncionarioCursosCancelados.jsp");
        
    }else if( id == 8 ){
        
        response.sendRedirect("TelaFuncionarioCursosEncerrado.jsp");
        
    }

%>
