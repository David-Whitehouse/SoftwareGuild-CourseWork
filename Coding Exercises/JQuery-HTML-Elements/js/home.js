$(document).ready(function () {
    
    $('h1').css('text-align', 'center');
    $('h2').css('text-align', 'center');

    $('h1').removeClass('myBannerHeading').addClass('page-header');

    $('#yellowHeading').text('Yellow Team');

    $('#orangeTeamList').css('background-color', 'orange');
    $('#blueTeamList').css('background-color', 'aqua');
    $('#redTeamList').css('background-color', 'red');
    $('#yellowTeamList').css('background-color', 'yellow');

    $('#yellowTeamList').append('<li>Joseph Banks</li>');
    $('#yellowTeamList').append('<li>Simon Jones</li>');

    $('#oops').hide();

    $('h2').remove(":Contains('Bogus')");

    $('.footer').append('<p class="page-footer">David Whitehouse - David.A.Whitehouse90@gmail.com</p>');
    $('.page-footer').css('font-size', 24);
    $('.page-footer').css('font-family', 'courier');
});