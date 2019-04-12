$('#modify').click(function(){
	console.log(2);
	window.location="http://localhost:9000/EC/login.html";
});

$('#new_product').click(function(){
	console.log("3");
  window.location="http://localhost:9000/EC/new_product_upload.html";
});
$('#Orders').click(function(){
	  window.location="http://localhost:9000/EC/Seller_orders.html";
	});
//$('#modify').click(function(){
//	window.location="http://localhost:9000/EC/login.html";
//});
//$('#Delete').click(function(){
//  window.location="http://localhost:5224/ebaytester/product_delete.html";
//});
//$('#change_address').click(function(){
//  window.location="http://localhost:9000/EC/address_form_seller.html";
//});
//$('#AddDeal').click(function(){
//	  window.location="http://localhost:9000/EC/addDealToProduct.html";
//	});

$.ajax({
	    type: "get",
	    url:  "http://localhost:9000/EC/webapi/bankService/getSellerBalance/",
	    dataType: "text",
	    headers: {
	    	'username':sessionStorage.username?sessionStorage.getItem('username'):'',
	    	'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')
	},
	    success: function(data){
	     
	      document.getElementById("sellerBalance").innerHTML = "Balance: Rs. " + data;
	      
	      ///document.getElementById("categories").style.cursor = pointer;
	      //console.log("here12")
	    }
	  });
