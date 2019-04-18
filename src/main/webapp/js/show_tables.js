var cart_product_list="";/*use to show products list*/
var result;/*use to store response */
var result1;/*use to store response */
var result2;/*use to store response */
var result3;/*use to store response */
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
       url: "http://localhost:9000/EC/webapi/showTableService/buyersAll/",
       async: false,
       headers: {
            'username':sessionStorage.username?sessionStorage.getItem('username'):'',
            'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

       },
       
       
            dataType:'JSON',
       success: function(response){
    	   console.log(result);
    		console.log("result----------->"+result);
        
             result=response;
             //cart_product_list+='<ul class="list-group">';
             
             cart_product_list += '<div class="grid-container">';
             
         	cart_product_list+='<div class="grid-item">ID</div>';	
        	cart_product_list+='<div class="grid-item">Name</div>';
        	cart_product_list+='<div class="grid-item">Mobile</div>';
        	cart_product_list+='<div class="grid-item">Password</div>';
        	cart_product_list+='<div class="grid-item">Email</div>';
        	cart_product_list+='<div class="grid-item">DOB</div>';
        	cart_product_list+='<div class="grid-item">Address_1</div>';
        	cart_product_list+='<div class="grid-item">Address_2</div>';
              for(i in result)
              {
                cart_list(i);/*function call*/
              }
              cart_product_list+='</ul>';
           //console.log(cart_product_list);

              document.getElementById('cart').innerHTML=cart_product_list;
 
            }
          });
  
  $.ajax({
      type :"GET",
     url: "http://localhost:9000/EC/webapi/showTableService/sellerAll/",
     async: false,
     headers: {
          'username':sessionStorage.username?sessionStorage.getItem('username'):'',
          'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

     },
     
     
          dataType:'JSON',
     success: function(response){
  	   console.log(result1);
  		console.log("result----------->"+result1);
      
           result1=response;
           //cart_product_list+='<ul class="list-group">';
           cart_product_list="";
           cart_product_list += '<div class="grid-container2">';
           
       	cart_product_list+='<div class="grid-item2">ID</div>';	
      	cart_product_list+='<div class="grid-item2">Name</div>';
      	cart_product_list+='<div class="grid-item2">Mobile</div>';
      	cart_product_list+='<div class="grid-item2">Password</div>';
      	cart_product_list+='<div class="grid-item2">Email</div>';
      	cart_product_list+='<div class="grid-item2">Address_1</div>';
      	cart_product_list+='<div class="grid-item2">Address_2</div>';
            for(i in result1)
            {
              cart_list_2(i);/*function call*/
            }
            cart_product_list+='</ul>';
         //console.log(cart_product_list);

            document.getElementById('cart2').innerHTML=cart_product_list;

          }
        });
  
  
  $.ajax({
      type :"GET",
     url: "http://localhost:9000/EC/webapi/showTableService/sellerAllAccount",
     async: false,
     headers: {
          'username':sessionStorage.username?sessionStorage.getItem('username'):'',
          'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

     },
     
     
          dataType:'JSON',
     success: function(response){
  	   console.log(result2);
 		console.log("result----------->"+result2);
  		console.log("response----------->"+response);
      
           result2=response;
           //cart_product_list+='<ul class="list-group">';
           cart_product_list="";
           cart_product_list += '<div class="grid-container3">';
           
      	cart_product_list+='<div class="grid-item3">Seller ID</div>';
      	cart_product_list+='<div class="grid-item3">Account Number</div>';
      	cart_product_list+='<div class="grid-item3">Balance</div>';

            for(i in result2)
            {
              cart_list_3(i);/*function call*/
            }
            cart_product_list+='</ul>';
         //console.log(cart_product_list);

            document.getElementById('cart3').innerHTML=cart_product_list;

          }
        });
  
  $.ajax({
      type :"GET",
     url: "http://localhost:9000/EC/webapi/showTableService/buyerAllAccount",
     async: false,
     headers: {
          'username':sessionStorage.username?sessionStorage.getItem('username'):'',
          'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

     },
     
     
          dataType:'JSON',
     success: function(response){
  	   console.log(result2);
 		console.log("result----------->"+result2);
  		console.log("response----------->"+response);
      
           result2=response;
           //cart_product_list+='<ul class="list-group">';
           cart_product_list="";
           cart_product_list += '<div class="grid-container3">';
           
      	cart_product_list+='<div class="grid-item3">Buyer ID</div>';
      	cart_product_list+='<div class="grid-item3">Account Number</div>';
      	cart_product_list+='<div class="grid-item3">Balance</div>';

            for(i in result2)
            {
              cart_list_3(i);/*function call*/
            }
            cart_product_list+='</ul>';
         //console.log(cart_product_list);

            document.getElementById('cart4').innerHTML=cart_product_list;

          }
        });
};









function cart_list(x){
	
	
//	cart_product_list+='<div class="grid-item">';	
//
//	cart_product_list+='<td colspan="2" style="border: 3px solid #CCC" ><a href="displaySelectedItem.html?itemId='+result[x].item_id+'"><img id="image1" src="'+result[x].pic_location+'" style="height:30px;width:30px"><a></td>';
//
//
//	
//	cart_product_list+='</div>';

	console.log("in function result"+result);
	
	cart_product_list+='<div class="grid-item">'+result[x].buyer_id+'</div>';	
	cart_product_list+='<div class="grid-item">'+result[x].name+'</div>';
	cart_product_list+='<div class="grid-item">'+result[x].mobile+'</div>';
	cart_product_list+='<div class="grid-item">'+result[x].password+'</div>';
	cart_product_list+='<div class="grid-item">'+result[x].email+'</div>';
	cart_product_list+='<div class="grid-item">'+result[x].dob+'</div>';
	cart_product_list+='<div class="grid-item">'+result[x].address_1+'</div>';
	cart_product_list+='<div class="grid-item">'+result[x].address_2+'</div>';

	

          console.log(cart_product_list);

};


function cart_list_2(x){
	
	
//	cart_product_list+='<div class="grid-item">';	
//
//	cart_product_list+='<td colspan="2" style="border: 3px solid #CCC" ><a href="displaySelectedItem.html?itemId='+result[x].item_id+'"><img id="image1" src="'+result[x].pic_location+'" style="height:30px;width:30px"><a></td>';
//
//
//	
//	cart_product_list+='</div>';

	console.log("in function result"+result1);
	
	cart_product_list+='<div class="grid-item">'+result1[x].seller_id+'</div>';	
	cart_product_list+='<div class="grid-item">'+result1[x].name+'</div>';
	cart_product_list+='<div class="grid-item">'+result1[x].mobile+'</div>';
	cart_product_list+='<div class="grid-item">'+result1[x].password+'</div>';
	cart_product_list+='<div class="grid-item">'+result1[x].email+'</div>';
	cart_product_list+='<div class="grid-item">'+result1[x].address_1+'</div>';
	cart_product_list+='<div class="grid-item">'+result1[x].address_2+'</div>';

	

          console.log(cart_product_list);

};


function cart_list_3(x){
	
	

	console.log("in function result"+result2);
	
	cart_product_list+='<div class="grid-item">'+result2[x].holderID+'</div>';	
	cart_product_list+='<div class="grid-item">'+result2[x].accNumber+'</div>';
	cart_product_list+='<div class="grid-item">'+result2[x].currBalance+'</div>';


	

          console.log(cart_product_list);

};
