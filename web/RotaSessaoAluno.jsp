                                                                                                                                                                                                                                                                                                                                                                                   <%
    
    int id = Integer.parseInt( request.getParameter("id") );
    
    if( id == 1){
        
        response.sendRedirect("TelaMeusCurso.jsp");
        
    }else if( id == 2 ){
        
        response.sendRedirect("TelaAlterarAluno.jsp");
        
    }else if( id == 3 ){
        
        response.sendRedirect("TelaHistoricoCurso.jsp");
        
    }else if( id == 4 ){
        
        response.sendRedirect(".jsp");
        
    }

%>
