window.onload = function () {

console.log("1");
var test="Hi";

    
    
$.ajax({url:"http://localhost:8080/EC/webapi/ShoppingCartService/AllCartDetails", type:"GET",
 async: false,
 headers: {
		'username':sessionStorage.getItem("username"),
		'password':sessionStorage.getItem("password")
	},
 success: function(data){
  
  var myObject = eval('(' + data + ')');
  
  console.log(data);
  
  for (i in myObject)
  {
  var value=(parseInt(i)+1);
  console.log(myObject[i].pic+".png");
  if(value<=5)
  {
   document.getElementById((value)+('_item')).innerHTML=myObject[i].name;
    document.getElementById((value)+('_price')).innerHTML=myObject[i].price;
    document.getElementById((value)+('_quant')).innerHTML=myObject[i].quantity;
    document.getElementById((value)+('_pic')).src=myObject[i].pic+".png";
  }
  }
 },
 error: function(data)
 {
 
 }
});
}