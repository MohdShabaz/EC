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
       url: "http://localhost:9000/EC/webapi/ShoppingCartService/AllCartDetails",
       async: false,
       headers: {
            'username':sessionStorage.username?sessionStorage.getItem('username'):'',
            'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

       },
       
       
            dataType:'JSON',
       success: function(response){
        console.log(response);
        
             result=response;
             cart_product_list+='<ul class="list-group">';
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
	
	
	result[x].price=((result[x].old_price)*(1-result[x].discount)*(1-result[x].clearance_discount));
	
	
	 cart_product_list+='<li class="list-group-item">';
     cart_product_list+='<div class="row">'+
                          '<div class=row >'+
                           //'<p style="Color:grey;margin-left:20px" ><strong>From </strong>'+result[x].name+'</p>'+
                           '<p style="Color:grey;margin-left:20px">Barcode: '+result[x].barcode+'</p>'+
                          '</div>'+
                          '<div class=row>'+
                            '<a href="#" id = "' + x +'" onclick="Remove('+x+')">'+
                            '<p style="Color:grey;text-align:right;margin-right:50px"><span class="glyphicon glyphicon-remove"></span>Remove</p>'+
                            '</a>'+
                          '</div>'+
                          '<div>'+
                            '<span class="col-sm-1" style="text-align:right ;width:20px">'+
															 	//'<input type="checkbox" onChange="checkbok('+x+')" id="checkbox'+result[x].id+'" name="'+result[x].id+'" value="'+result[x].product_id+'" checked>'+
														'</span>'+
                            '<span class="col-sm-15"><div class=row>'+
                                '<span class="col-sm-1" style="margin-left:20px">'+
                                  '<table style=" border-collapse: separate; border-spacing: 4px;" >'+
                                    '<tr >'+
                                      '<td colspan="2" style="border: 3px solid #CCC" ><a href="#"><img id="image1" src="'+result[x].pic+'" style="height:100px;width:100px"><a></td>'+
                                    '</tr>'+
                                  '</table>'+
                                '</span>'+
                                '<span class="col-sm-2" style="margin-left:50px"><p>'+result[x].name+'</p></span>'+
                                '<span class="col-sm-2">'+
																	 '<p>QTY:<input type="text" value="'+result[x].quantity+'" style="width:30px" onChange="quantity('+x+')" id="Quantity'+result[x].id+'">'+
																	 '<a  href="#" style="margin-left:10px" onclick="update('+x+')" id="update'+result[x].id+'">Update</a>'+
																	 '</p>'+
																	 '<p>Shipping : Free</p>'+
																'</span>'+
								'<span class="col-sm-3"><p><b>MRP : Rs.'+((result[x].quantity)*(result[x].old_price)).toFixed(0)+'</b></p></span>'+
								
								
								'<span class="col-sm-3"><p><b>Discount:'+((result[x].discount*100).toFixed(0))+'%</b></p></span>'+
								'<span class="col-sm-3"><p><b>Clearance Disc:'+((result[x].clearance_discount*100).toFixed(0))+'%</b></p></span>'+

								
								
								
                                '<span class="col-sm-2"><br><p><b>Final Price after all discounts :Rs.'+((result[x].quantity)*(result[x].price)).toFixed(0)+'</b></p>'+
                                  
																'</span>'+
																'</div>'+
                            '</span>'+
                         '</div>'+
                       '</div>'+
                    '</li>';
         total=parseFloat(parseFloat(total)+parseFloat(result[x].price)).toFixed(0);
                    price.push(parseFloat(result[x].price.toFixed(0)));

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