$.ajax({
      type: "get",
      url:  "http://localhost:8080/EC/webapi/category/getAllCategory",
      dataType: "JSON",
      headers: {
    	  'username':sessionStorage.getItem('username'),
	      'password':sessionStorage.getItem('password')
 },
      success: function(productArray){
        console.log(productArray);
        var optionsAsString = "";
        for(var i = 0; i < productArray.length; i++) {
            optionsAsString += "<option value='" + productArray[i].id + "'>" + productArray[i].category_name + "</option>";
        }
        $( 'select[name="Category"]' ).append( optionsAsString );
      }
    });


$.ajax({
    type: "get",
    url:  "http://localhost:8080/EC/webapi/itemService/getAllBrands/",
    dataType: "JSON",
    headers: {
  	  'username':sessionStorage.getItem('username'),
	      'password':sessionStorage.getItem('password')
},
    success: function(brandArray){
    	var br = "";
    	for(var i = 0; i < brandArray.length; i++) {
    		br += "<option>"+brandArray[i].brand+"</option>";
    		//console.log($('#brands').innerHTML);
    	}
    	console.log(br);
    	document.getElementById('brands').innerHTML += br;
    	//jQuery("#brands").innerHTML += "<option>"+""+"</option>";
    }
  });

var json_array={
        key_values: []
        
   };
$('#Category_product').on('change', function() {
$('#Subcategory_product').empty();
console.log("1");
console.log(typeof(this.value));
var item_id = {
  "id"  : this.value
 };
console.log(JSON.stringify(item_id));
if(this.value!= ''){
 $.ajax({
         type: "get",
         url:  "http://localhost:8080/EC/webapi/subcategory/getAllSubCategories/"+this.value,
         dataType: "JSON",
         headers: {
        	 'username':sessionStorage.getItem('username'),
	         'password':sessionStorage.getItem('password')
       },
         success: function(response){
            if(response!=null)
              {
             console.log(response);
                var i;
                for (i=0;i<response.length;i++) {
                 $('<option value="'+ response[i].id+'">' + response[i].sub_category_name+ '</option>').appendTo('#Subcategory_product');
                        }
              }
            
          },
       failure: function()
       {
        console.log(2);
       },
          error: function()
          {
           console.log(3);
          }
        });
   }
else{
     $('<option value="'+''+'">' + "sub Category"+ '</option>').appendTo('#Subcategory');
    }
});

$('#Product_detail_form').submit(function(){
//	  alert("seller id: "+localStorage.user_Id);
	localStorage.barcode=jQuery("#Barcode").val();
//	alert(localStorage.barcode);
		var jsondata={
	             Product_Name: jQuery("#Product_Name").val(),
	             Category: jQuery("#Category_product").val(),
	             Subcategory: jQuery("#Subcategory_product").val(),
	             Price: jQuery("#Price").val(),
	             Quantity: jQuery("#Quantity").val(),
	             Address: jQuery("#Address").val(),
	             Description: jQuery("#Description").val(),
	             Discount : jQuery("#Discount").val(),
	             Brand: jQuery("#Brand").val(),
	             k_v : json_array,
	             Barcode : jQuery("#Barcode").val(),
	             Brand: jQuery("#Brand").val()
	             
	        };
		console.log(jsondata);
		console.log($('#Product_detail_form').serialize());
		
		$.ajax({
			type: "post",
			url:  "http://localhost:8080/EC/webapi/itemService/addItem",
			data    :JSON.stringify(jsondata),
			dataType: "JSON",
			headers: {
				 'username':sessionStorage.getItem('username'),
		         'password':sessionStorage.getItem('password')
//		         'seller_id': '786'
		       },
		       success: function(response){
					console.log(response);
					if(response.Response == "Failure")
					{	
						alert("Sorry Failed to add the item!!!");
					}
					else
					{
//						sessionStorage.setItem('username', jQuery("#Seller_Mobile").val());
//						sessionStorage.setItem('password', jQuery("#Seller_password").val());
						alert("Item Added Successfully. Redirecting to Dashboard");
						console.log(jQuery("#Barcode").val());
						
//						window.location.href ="http://localhost:8080/EC/image_upload.html?barcode="+jQuery("#Barcode").val();
//						window.location.href ="http://localhost:8080/EC/seller_dashboard.html";
						window.location="http://localhost:8080/EC/image_upload_form.html";
						
					}
					
			},
			error : function(response)
			{
				console.log(response);
				alert("Sorry Failed to add the item!!!");
			}
				     });
		
				   return false;

	     });



//var html = "<button type='submit' class='btn btn-success' id='key_value'>Add More Details</button>";

//document.getElementById("KeyValues").innerHTML = html;
var num=0;
//var json_array;

Storage.prototype.setObj = function(key, value) {
    this.setItem(key, JSON.stringify(value));
}

Storage.prototype.getObj = function(key) {
    var value = this.getItem(key);
    return value && JSON.parse(value);
}

function KV(){
	console.log(10);
	var html1=document.getElementById("KeyValues").innerHTML;
//	var html1=document.getElementById("KeyValues").innerHTML;

	if(num>0)
		{
		var k="#key_"+(num-1).toString();
		var v="#v_"+(num-1).toString();
		console.log("in k");
		console.log(k);
		console.log(v);
		var key_value_json={
	             key: jQuery(k).val(),
	             value: jQuery(v).val()
	             
	        };


		console.log(key_value_json);
		if(num==1)
		{
		json_array={
	             key_values: [key_value_json]
	             
	        };
		}
		else
			{
			
//			var obj = JSON.parse(json_array);
			var obj = json_array;
			
			json_array.key_values.push(key_value_json);
//			json_array= JSON.stringify(obj);
			console.log(json_array);
			
			}
		


		
		console.log(json_array);
		
		}
	if(num>0)
	{
	var phtml="<p></p>";
	for (i in json_array.key_values) {
		 phtml+="<p> Key : ";
		 phtml+=json_array.key_values[i].key;
		 phtml+="    Value: "
	     phtml+=json_array.key_values[i].value;
		 phtml+="</p>";
		}
	document.getElementById("PrevKeyValues").innerHTML = phtml;
	}
	
	html1="<p>Key: <input type = text  id =key_"+num+"></input>";
	html1+="Value: <input type = text  id =v_"+num+"></input></p>";
	num=num+1;
	document.getElementById("KeyValues").innerHTML = html1;
//	window.location="http://localhost:8080/EC/login.html";
}
