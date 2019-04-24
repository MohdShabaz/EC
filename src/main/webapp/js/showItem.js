$.ajax({

      type: "get",
      url:  "http://localhost:9000/EC/webapi/itemService/getitem/"+myFunction(),
      //data: JSON.stringify(myFunction()),
      //datatype : JSON,
      headers: {
       'username':sessionStorage.username?sessionStorage.getItem('username'):'',
       'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

 },
      success: function(product){
       console.log("hereA")
        console.log(product);
       console.log("hereB")
        
          document.getElementById("nameItem").innerHTML = product.name;
       document.getElementById("c_item_name").innerHTML = product.name;
          var x = (product.discount*100).toFixed(2);          
          document.getElementById("discountItem").innerHTML = x;
          console.log("seller");
          console.log(product.seller_name);
          console.log("Quantity:");
          console.log(product.quantity);
          document.getElementById("Item_barcode").innerHTML = product.barcode;
          document.getElementById("Quantity-left").innerHTML = product.quantity;
          document.getElementById("seller_name").innerHTML = product.seller_name;
          document.getElementById("descriptionItem").innerHTML = product.description;
          document.getElementById("quan").max = product.quantity;
          document.getElementById("category").innerHTML = product.category_name;
          document.getElementById("sub_category").innerHTML = product.sub_category_name;
          var y = (product.price).toFixed(2);
          document.getElementById("priceItem").innerHTML = y;
          var today = new Date();
  		  var dd = String(today.getDate()).padStart(2, '0');
  		  var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
  		  var yyyy = today.getFullYear();
  		  today =mm + '-'+dd;

  		  if(sessionStorage.dob == today)
  		  {
  			  
  			  
  			  
  			  
  			$.ajax({  
           	 url: "http://localhost:9000/EC/webapi/dealsService/isItemInBirDeal/"+myFunction(), 
                type: 'GET',
                headers: {
                    'username':sessionStorage.username?sessionStorage.getItem('username'):'',
                    'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

              },
              
              success: function (response) {  
           	  	//console.log("Deals " + response);
           	  	//console.log("dkfjhvkjdfvn");
           	  	 console.log(response);
           	  	 var html="<div style='color:red;'>Happy Birthday "+sessionStorage.name+"</div>";
           	  	if(response.result=="success")
           	  	{
           	  		//html+="<div>"+response.bDiscount.toFixed(2)+" % cashBack for this item</div>";
           	  		html+="<div  style='color:blue;'> Rs <span id='BdayDiscountCashBack'>"+(response.bDiscount*y).toFixed(2)+"</span> cashBack for this item</div>";
           	  	}
           	  	
           	  	document.getElementById("cashBack").innerHTML=html;
           	  	console.log(html);
              }
            
       	 });
  			  
  			  
  			  
  			  
  			  
  	      }
  		  
          
  		  
  		  // Started for buy 1 get 1
  		  
  		  console.log("Buy 1 get 1");
  		$.ajax({  
          	 url: "http://localhost:9000/EC/webapi/dealsService/dealNumber/"+myFunction(), 
               type: 'GET',
               datatype: 'JSON',
               headers: {
                   'username':sessionStorage.username?sessionStorage.getItem('username'):'',
                   'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

             },
             
             success: function (response) {
            	 console.log("sljdfbvldfjv");
          	  	console.log(response);
          	  	if(parseInt(response.deal_id) == 1){
          	  		document.getElementById("Buy1Get1").innerHTML = "Buy One Get One Offer!!";
          	  	}
             },
             error: function(response){
            	 console.log("Error. Save me");
             }
           
      	 });
  		  
  		  
  		  // Ended for buy 1 get 1
  		  
  		  
  		  
  		  
  		  
          var clearance_dis = (product.clearance_discount*100).toFixed(2);
          
          console.log("clearance_dis ---> "+clearance_dis);
          
          if(clearance_dis<0){
              document.getElementById("clearance_dis").innerHTML = "Item not in clearance sale";
        	  
          }
          else{
              document.getElementById("clearance_dis").innerHTML = clearance_dis;
        	  
          }


          
          console.log(product.barcode);

          document.getElementById("itemImage").src = product.pic_location;
          
          document.getElementById("newprice").innerHTML = (y*(100-x)*(100-clearance_dis)/(100*100)).toFixed(2);
//          document.getElementById("newprice").innerHTML = y;
          
          var html="";
          for(var i = 0; i < product.key_value.length; i++) {
        	  html +="<div >"+product.key_value[i].label+":  <span>"+product.key_value[i].attr+"</span></div>";
          }
          document.getElementById("KeyValue").innerHTML=html;
          var total_stars = product.total_stars;
          var total_users_rated = product.total_users_rated;
          
          var final_rating = 0;
          
          if(total_users_rated!=0){
        	  final_rating = total_stars/total_users_rated;
          }
          
          console.log("final rating is "+final_rating);
          
          //document.getElementById("stars").innerHTML = getStars(3.6);
          document.getElementById("stars").innerHTML = getStars(final_rating);
          
          document.getElementById("rating_figure").innerHTML = final_rating.toFixed(1);
          document.getElementById("total_users_rated").innerHTML = total_users_rated;
      }
    });


function myFunction(){
	var a = location.href;
	console.log("a is"+a);
	var b = a.substring(a.indexOf("=")+1);
	console.log("b is"+b);
	var d = a.substring(a.indexOf(",")+1);
	console.log("d is"+d);
	//document.getElementById("sub_category").href="displaySubCatItems.html?"+d;
	d=d.substring(d.indexOf(",")+1);
	var e =decodeURIComponent( d.substring(d.indexOf("=")+1,d.indexOf(",")));
	console.log("e is "+decodeURIComponent(e));
	//document.getElementById("category").innerHTML = decodeURIComponent(e);
	var f = d.substring(d.indexOf(",")+1);
	console.log("f is"+f);
	var g =f.substring(f.indexOf("=")+1);
	console.log("g is "+g);
	//document.getElementById("sub_category").innerHTML = decodeURIComponent(g);
	var h =g.substring(g.indexOf(",")+1);
	console.log("h is "+h);
	//document.getElementById("sub_category").href = g;

	//var c=JSON.parse('{"id":'+b+'}');
	//console.log("c is "+typeof(c));
	return b;
	}

//function shopNow(){
//console.log("yes shopNow");
//}
function getStars(rating) {

	  // Round to nearest half
	  rating = Math.round(rating * 2) / 2;
	  let output = [];

	  // Append all the filled whole stars
	  for (var i = rating; i >= 1; i--)
	    output.push('<i class="fa fa-star" aria-hidden="true" style="color: gold;"></i>&nbsp;');

	  // If there is a half a star, append it
	  if (i == .5) output.push('<i class="fa fa-star-half-o" aria-hidden="true" style="color: gold;"></i>&nbsp;');

	  // Fill the empty stars
	  for (let i = (5 - rating); i >= 1; i--)
	    output.push('<i class="fa fa-star-o" aria-hidden="true" style="color: gold;"></i>&nbsp;');

	  return output.join('');

	}