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

	cart_product_list+='<td colspan="2" style="border: 3px solid #CCC" ><a href="displaySelectedItem.html?itemId='+result[x].item_id+'"><img id="image1" src="'+result[x].pic_location+'" style="height:30px;width:30px"><a></td>';


	
	cart_product_list+='</div>';

	
	
	cart_product_list+='<div class="grid-item">'+result[x].name+'</div>';	
	cart_product_list+='<div class="grid-item">'+result[x].barcode+'</div>';
	cart_product_list+='<div class="grid-item">'+Math.floor((result[x].discount)*100 )+'</div>';
	cart_product_list+='<div class="grid-item">'+result[x].price.toFixed(2)+'</div>';
	
	
	/*
	 cart_product_list+='<li class="list-group-item">';
     cart_product_list+=
    	 '<div class="grid-container">'+
//                          '<div class=row >'+
//                           //'<p style="Color:grey;margin-left:20px" ><strong>From </strong>'+result[x].name+'</p>'+
//                           '<p style="Color:grey;margin-left:20px">Barcode: '+result[x].barcode+'</p>'+
//                          '</div>'+
//                          '<div class=row>'+
//                            '<a href="#" id = "' + x +'" onclick="Remove('+x+')">'+
//                            '<p style="Color:grey;text-align:right;margin-right:50px"><span class="glyphicon glyphicon-remove"></span>Remove</p>'+
//                            '</a>'+
//                          '</div>'+
                          //'<div class="grid-container">'+
//                            '<span class="col-sm-1" style="text-align:right ;width:20px">'+
//															 	//'<input type="checkbox" onChange="checkbok('+x+')" id="checkbox'+result[x].id+'" name="'+result[x].id+'" value="'+result[x].product_id+'" checked>'+
//														'</span>'+
                            //'<span class="col-sm-11"><div class="grid-container">'+
                                '<span class="col-sm-1" style="margin-left:20px">'+
                                  '<table style=" border-collapse: separate; border-spacing: 4px;" >'+
                                    '<tr >'+
                                      '<td colspan="2" style="border: 3px solid #CCC" ><a href="displaySelectedItem.html?itemId='+result[x].item_id+'"><img id="image1" src="'+result[x].pic_location+'" style="height:30px;width:30px"><a></td>'+
                                    '</tr>'+
                                  '</table>'+
                                '</span>'+
                                '<span class="col-sm-2" style="margin-left:50px"><p>'+result[x].name+'</p></span>'+
                                '<span class="col-sm-2" style="margin-left:50px"><p>'+result[x].barcode+'</p></span>'+
                                '<span class="col-sm-2" style="margin-left:50px"><p>'+Math.floor((result[x].discount)*100 )+'</p></span>'+
//                                '<span class="col-sm-3">'+
//																	 '<p>QTY:<input type="text" value="'+result[x].price+'" style="width:30px" onChange="quantity('+x+')" id="Quantity'+result[x].id+'">'+
//																	 '<a  href="#" style="margin-left:10px" onclick="update('+x+')" id="update'+result[x].id+'">Update</a>'+
//																	 '</p>'+
//																	 '<p>Shipping</p>'+
																'</span>'+
								'<span class="col-sm-2"><p>Rs.'+Math.floor(result[x].price)+'</p></span>'+
//                                '<span class="col-sm-2"><p><b>Rs.'+result[x].price+'</b></p>'+
//                                  '<p>Free</p>'+
//																'</span>'+
																'</div>'+
                            //'</span>'+
                         //'</div>'+
                       '</div>'+
                    '</li>'
                       
                       
                       */
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