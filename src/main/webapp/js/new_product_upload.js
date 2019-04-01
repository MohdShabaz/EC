document.getElementById("1234").innerHTML = "567";

$.ajax({
      type: "get",
      url:  "http://localhost:9000/EC/webapi/category/getAllCategory",
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
         url:  "http://localhost:9000/EC/webapi/subcategory/getAllSubCategories/"+this.value,
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
	
	  
		var jsondata={
	             Product_Name: jQuery("#Product_Name").val(),
	             Category: jQuery("#Category_product").val(),
	             Subcategory: jQuery("#Subcategory_product").val(),
	             Price: jQuery("#Price").val(),
	             Quantity: jQuery("#Quantity").val(),
	             Address: jQuery("#Address").val(),
	             Description: jQuery("#Description").val(),
	             Discount : jQuery("#Discount").val(),
	             Barcode : jQuery("#Barcode").val()
	             
	        };
		console.log(jsondata);
//		localStorage.setItem("lastname", "smith1");
		console.log($('#Product_detail_form').serialize());
		
		$.ajax({
			type: "post",
			url:  "http://localhost:9000/EC/webapi/itemService/addItem",
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
						sessionStorage.setItem('username', jQuery("#Seller_Mobile").val());
						sessionStorage.setItem('password', jQuery("#Seller_password").val());
						alert("Item Added Successfully. Redirecting to Dashboard");
						console.log(jQuery("#Barcode").val());
//						window.location.href ="http://localhost:9000/EC/image_upload.html?barcode="+jQuery("#Barcode").val();
						window.location.href ="http://localhost:9000/EC/image_upload.html";
						
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
