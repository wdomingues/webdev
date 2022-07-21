

$('.mask-money-old').maskMoney({ showSymbol: true, symbol: "R$", decimal: ",", thousands: "." });

//$(".custom3").inputMask({'alias': 'numeric', allowMinus: false, digits: 2});

$(document).ready(function(){
    $("input.dinheiro").maskMoney({showSymbol:true, symbol:"R$", decimal:",", thousands:"."});
});

$("input.dinheiro").maskMoney({showSymbol:true, symbol:"R$", decimal:",", thousands:"."});