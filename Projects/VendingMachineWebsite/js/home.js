  loadItems();


function loadItems(){
    var displayedItems = $('#displayedItems');

    $.ajax({
        method: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function(itemArray){
            $.each(itemArray, function(index, item){
                var id = item.id;
                var name = item.name;
                var price = item.price;
                var quantity = item.quantity;

                var button = '<button type="button" onClick="selectItem('+ id +')" style="width: 33%;">';
                    button += '<p>' + id + '</p>';
                    button += '<p>' + name + '</p>';
                    button += '<p> $ ' + price + '</p>';
                    button += '<p> quantity: ' + quantity + '</p>'; 
                    button += '</button>'
                    displayedItems.append(button);
            });
        },
        error: function(){
            $('#errorMessages')
            .append($("<li>")
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again later.'));
            }

    });

    }

function vendItem(itemIdToVend){
    var storedFunds = $('#storedMoney').val();

    $.ajax({
        method: 'POST',
        url: "http://tsg-vending.herokuapp.com/money/" + storedFunds + "/item/" + itemIdToVend,
        success: function(change){
            var changeInfo = change.quarters + " Quarters, " + change.dimes + " Dimes, " 
            + change.nickels + " Nickels, " + change.pennies + " Pennies"

            $('#storedMoney').val(0);
            $('#showChange').val(changeInfo);
            $('#message').val('Thank you!!!');
            $('#displayedItems').empty();
            loadItems(); 
            
        },
        error: function(jqXHR, textStatus, errorThrown){
                $('#message').val(jqXHR.responseJSON.message);
        }

        })
    }




function addMoney(amount){
    storedMoney = parseFloat($('#storedMoney').val());
    newStoredMoney = storedMoney + parseFloat(amount);
    $('#storedMoney').val('');
    $('#storedMoney').val(newStoredMoney.toFixed(2));
}

function selectItem(id){
    
    $('#selectedItemId').val('');
    $('#selectedItemId').val(id);
}

function returnChange(){
    $('#selectedItemId').val('');
    $('#storedMoney').val(0);
    $('#showChange').val('');
    $('#message').val('');
}


