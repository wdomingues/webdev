

$('.mask-money-old').maskMoney({ showSymbol: true, symbol: "R$", decimal: ",", thousands: "." });

$(".custom3").inputmask({'alias': 'numeric', allowMinus: false, digits: 2});

$(document).ready(function(){
    $("input.dinheiro").maskMoney({showSymbol:true, symbol:"R$", decimal:",", thousands:"."});
});