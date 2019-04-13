var result;

$(document).ready(function (){
	$.ajax({
	    type: "get",
	    url:  "http://localhost:9000/EC/webapi/bankService/getSellerBalance/",
	    async: false,
	    dataType: "text",
	    headers: {
	    	'username':sessionStorage.username?sessionStorage.getItem('username'):'',
	    	'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')
	},
	    success: function(data){
	     
	      document.getElementById("sellerBalance").innerHTML = "Balance: Rs. " + data;
	      
	      ///document.getElementById("categories").style.cursor = pointer;
	      console.log("here12");
	    },
		error :function(data){
			console.log("here11");
		}
	  });
	
	
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
	             load_cart();
	       }
	 });
});


function load_cart(){
	//console.log("11")
	cart_product_list='<ul class="list-group">';
    for(i in result)
    {
      cart_list(i);/*function call*/
    }
    cart_product_list+='</ul>';
    document.getElementById('cart').innerHTML=cart_product_list;
}

function cart_list(x){
	//console.log("xx ")
	 cart_product_list+='<li class="list-group-item">';
     cart_product_list+=
    	 '<div class="row">'+
//                          '<div class=row >'+
//                           //'<p style="Color:grey;margin-left:20px" ><strong>From </strong>'+result[x].name+'</p>'+
//                           '<p style="Color:grey;margin-left:20px">Barcode: '+result[x].barcode+'</p>'+
//                          '</div>'+
//                          '<div class=row>'+
//                            '<a href="#" id = "' + x +'" onclick="Remove('+x+')">'+
//                            '<p style="Color:grey;text-align:right;margin-right:50px"><span class="glyphicon glyphicon-remove"></span>Remove</p>'+
//                            '</a>'+
//                          '</div>'+
                          '<div>'+
//                            '<span class="col-sm-1" style="text-align:right ;width:20px">'+
//															 	//'<input type="checkbox" onChange="checkbok('+x+')" id="checkbox'+result[x].id+'" name="'+result[x].id+'" value="'+result[x].product_id+'" checked>'+
//														'</span>'+
                            '<span class="col-sm-11"><div class=row>'+
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
                            '</span>'+
                         '</div>'+
                       '</div>'+
                    '</li>'
         //total=parseFloat(parseFloat(total)+parseFloat(result[x].price)).toFixed(2);
                    //price.push(parseFloat(result[x].price.toFixed(2)));
          //console.log(cart_product_list);

};




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

