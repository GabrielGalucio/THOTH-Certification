<%
    
    int id = Integer.parseInt( request.getParameter("id") );
    
    if( id == 1){
        
        response.sendRedirect("TelaAlterarProfessor.jsp");
        
    }else if( id == 2 ){
        
        response.sendRedirect("TelaEquipeProfessor.jsp");
        
    }else if( id == 3 ){
        
        response.sendRedirect("TelaCriarCurso.jsp");
        
    }else if( id == 4 ){
        
        response.sendRedirect("TelaCursoAberto.jsp");
        
    }

%>
