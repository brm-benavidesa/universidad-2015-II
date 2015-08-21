var fechas = function(edad){
 
  var hoy = new Date();
  var mes = ((hoy.getMonth()+1)<= 9) ? "0" + (hoy.getMonth()+1) : (hoy.getMonth()+1);
  var f2 =  (hoy.getFullYear()+"-"+mes+"-"+hoy.getUTCDate());
  var f1 = edad;
  var aFecha1 = f1.split('-');
  var aFecha2 = f2.split('-');
  var fFecha1 = Date.UTC(aFecha1[0], aFecha1[1] - 1, aFecha1[2]);
  var fFecha2 = Date.UTC(aFecha2[0], aFecha2[1] - 1, aFecha2[2]);
  var dif = fFecha2 - fFecha1;
  var anios = Math.floor((dif / (1000 * 60 * 60 * 24 * 30.4375)))/12;
  if(anios.toFixed()>anios){
    var edadP = anios.toFixed()-1;
  }else{
     edadP = anios.toFixed();
  }
  if(edadP%2===0){
    $('#cuerpo').css('background-color','#F4FA58');
  }else{
    $("#cuerpo").css("background-color","#0000FF");
  }
  $("#anios").text(edadP);
};
$(document).ready(function(){
  
  $("#fNacimiento").datepicker({
    format: 'yyyy-mm-dd',
    orientation:"auto top",
    autoclose:true,
    language:'es'
  });
  
});


