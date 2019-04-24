window.onload = function () {

console.log("1");
var test="Hi";

    
    
$.ajax({url:"http://localhost:9000/EC/webapi/ShoppingCartService/AllCartDetails", type:"GET",
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


$.ajax({url:"http://localhost:9000/EC/webapi/ShoppingCartService/getPrice", type:"GET",
 async: false,
 headers: {
		'username':sessionStorage.getItem("username"),
		'password':sessionStorage.getItem("password")
	},
 success: function(data){
	 console.log("price :");
	 console.log(1);
	 console.log(Math.round(parseFloat(data+1)));
  document.getElementById("price").innerHTML=Math.round(parseFloat(data));
 },
 error: function(data)
 {
  
 }
});
}
 
 function ConfirmOrder()
 {
 
 var json_={
   shipping_address: document.getElementById("address_").value,
   payment_type: document.getElementById("payId").value
 };
 
 console.log(json_);
 
 $.ajax({url:"http://localhost:9000/EC/webapi/orderService/addOrder", type:"POST",
 
 data: JSON.stringify(json_),
 datatype: "json",
 headers: {
		'username':sessionStorage.getItem("username"),
		'password':sessionStorage.getItem("password")
	},
 async: false,
 success: function(data){
  console.log(data);
},
 error: function(data)
 {
  
 }
 });
 window.location.href = "items.html";
 }