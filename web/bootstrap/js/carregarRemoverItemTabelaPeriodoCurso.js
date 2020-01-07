periodo_array = []; // Array de produtos

$('#adicionar_periodo').on('click', function () {

    var table = $('#tabela_periodo tbody'), // Tabela de produtos

        dtPeriodo = $('#cx_data').val(), // Select com a lista de produtos

        hrInicial = $('#hora_inicio').val(), // Campo com a quantidade de produtos
        
        hrTermino = $('#hora_termino').val(), // Campo com a quantidade de produtos
     
      remove = "<a href='#' class='del_periodo'>Remover<i class='icon-remove-small'></i></a>", 
      // Link para remover o produto
        
          
    input_hidden = $('#lista_periodo_array');  
    
    // Remove a mensagem de cesta vazia depois que produto é adicionado
    $('#cesta_vazia').remove();

    // Insere o período selecionado no select list dentro da tabela                
   // table.append("<tr><td>" + dtPeriodo + "</td><td>" + hrInicial + "</td><td>" + hrTermino + "</td><td>" + remove + "</td></tr>");
   table.append("<tr><td><input type='\'hidden'\' id='\'cx_dt'\' name='\'cx_dt'\' value="+ dtPeriodo + "></td><td><input type='\'hidden'\' id='\'cx_h1'\' name='\'cx_h1'\' value=" + hrInicial + "></td><td><input type='\'hidden'\' id='\'cx_h2'\' name='\'cx_h2'\' value=" + hrTermino + "></td><td>" + remove + "</td></tr>"); 
  
    periodo_array.push("[" + dtPeriodo + "]" + "[" + hrInicial + "]" + "[" + hrTermino + "]");

    input_hidden.val(periodo_array);

    console.log("novo periodo adicionado :)");

    console.log(input_hidden.val());

});

// Remoção de periodos da tabela
$(document).on('click', '.del_periodo', function (e) {
    e.preventDefault();

    // Janela de confirmação de exclusão do item
    var dialog = confirm("Clique 'OK' para remover o período, ou 'cancelar' para mantê-lo na lista.");

    if (dialog == true) {
        var tr = $(this).closest('tr');
        var dt = tr.find('td').eq(0).text();
        var horaIni = tr.find('td').eq(1).text();
        var horaTermino = tr.find('td').eq().text();
        var index = periodo_array.indexOf("[" + dt + "]" + "[" + horaIni + "]" + "[" + horaTermino + "]");
        periodo_array.splice(index, 1);
        tr.remove();
        console.log("period removido :(");
        console.log(periodo_array);

    } else if (dialog == false) {

        console.log("periodo mantido :O");
        console.log(periodo_array);

    }
});