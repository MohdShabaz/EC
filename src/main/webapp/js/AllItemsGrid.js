var cart_product_list="";/*use to show products list*/
var result;/*use to store response */
var x;
var remove_pid;
var pid;
var total=0;
var price=[];
var count=0;


//cart_list_ajax();

window.onload = function cart_list_ajax(){
console.log(document.getElementById('place_order').innerHTML);
 cart_product_list="";
 total=0;
 price=[];
  $.ajax({
        type :"GET",
       url: "http://localhost:9000/EC/webapi/itemService/itemsTop",
       async: false,
       headers: {
            'username':sessionStorage.username?sessionStorage.getItem('username'):'',
            'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

       },
       
       
            dataType:'JSON',
       success: function(response){
        console.log(response);
        
             result=response;
             //cart_product_list+='<ul class="list-group">';
             
             cart_product_list += '<div class="grid-container">';
              for(i in result)
              {
                cart_list(i);/*function call*/
              }
              cart_product_list+='</ul>';
           //console.log(cart_product_list);

              document.getElementById('cart').innerHTML=cart_product_list;
 
            }
          });
};









function cart_list(x){
	
	
	cart_product_list+='<div class="grid-item">';	
	


	cart_product_list+='<td colspan="2" style="border: 3px solid #CCC" ><a href="displaySelectedItem.html?itemId='+result[x].item_id+'"><img id="image1" src="'+result[x].pic_location+'" style="height:150px;width:150px"><a></td>';

	cart_product_list+='<p>Name:'+result[x].name+'</p>'
	cart_product_list+='<p>Barcode:'+result[x].barcode+'</p>'
	cart_product_list+='<p>Price:'+Math.floor((result[x].discount)*100 )+'</p>'
	cart_product_list+='<p>Discount:'+result[x].price.toFixed(2)+'</p>'
	
	cart_product_list+='</div>';

	
	
//	cart_product_list+='<div class="grid-item">'+result[x].name+'</div>';	
//	cart_product_list+='<div class="grid-item">'+result[x].barcode+'</div>';
//	cart_product_list+='<div class="grid-item">'+Math.floor((result[x].discount)*100 )+'</div>';
//	cart_product_list+='<div class="grid-item">'+result[x].price.toFixed(2)+'</div>';
	
         total=parseFloat(parseFloat(total)+parseFloat(result[x].price)).toFixed(2);
                    price.push(parseFloat(result[x].price.toFixed(2)));
          console.log(cart_product_list);

};

function Remove(x){
	console.log(result[x].item_id);
	  $.ajax({
	            type :"DELETE",
	            url: "http://localhost:9000/EC/webapi/ShoppingCartService/deleteCartItem/"+result[x].item_id,
	            dataType:'text',
	            headers: {
	                'username':sessionStorage.username?sessionStorage.getItem('username'):'',
	                'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

	           },
	            complete: function(response){
	             location.reload();
	            }
	          });
};

function update(x){
	 $('#update'+result[x].id).hide();
	  if($('#Quantity'+result[x].id).val()>result[x].available_quantity)
	  {
	    alert("available quantity "+result[x].available_quantity);
	    $('#Quantity'+result[x].id).val(result[x].available_quantity);
	  }
		if($('#Quantity'+result[x].id).val()<1){
			alert("Quantity can not be less than one");
			window.location="http://localhost:9000/EC/Cart_ebay.html";
		}
		else{
	  $.ajax({
	            type :"PUT",
	            data : result[x].item_id,
	            url: "http://localhost:9000/EC/webapi/ShoppingCartService/updateCart/"+result[x].item_id+"/"+$('#Quantity'+result[x].id).val(),
	            dataType:'text',
	            headers: {
	                'username':sessionStorage.username?sessionStorage.getItem('username'):'',
	                'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

	           },
	           
	            complete: function(response){
	            	window.location="http://localhost:9000/EC/Cart_ebay.html";
	              //cart_list_ajax();

	            }
	          });
	       }
};

function ShopMore(){
	window.location = "http://localhost:9000/EC/homepage.html";
};
function PlaceOrder(){

	console.log("Hello kushagra");
	window.location = "http://localhost:9000/EC/checkout.html";

};