$.ajax({
      type: "get",
      url:  "http://localhost:8080/EC/webapi/category/getAllCategory",
      dataType: "JSON",
      headers: {
    'username':'9367535629',
    'password':'johnwick3'
 },
      success: function(productArray){
        console.log(productArray);
        
        var html="<ul><div class ='category'>";
        for(var i = 0; i < productArray.length; i++) {
            html += "<li onmouseover='subcatdisplay("+productArray[i].id+")'><span class='category'> "+ productArray[i].category_name + "</span></li>";
        }
        html+="</ul>";
        document.getElementById("categories").innerHTML = html;
      }
    });


function subcatdisplay(catId)
{
	$('#Subcategories').empty();
	console.log("1");
	console.log(catId);
	var item_id = {
	  "id"  : this.value
	 };
	console.log(JSON.stringify(item_id));
	if(this.value!= ''){
	 $.ajax({
	         type: "get",
	         url:  "http://localhost:8080/EC/webapi/subcategory/"+catId,
	         dataType: "JSON",
	         headers: {
	         'username':'9367535629',
	         'password':'johnwick3'
	       },
	       cache: true,
	         success: function(productArray){
	            if(productArray!=null)
	              {
	             console.log(productArray);
	             var html="<ul><div class ='category'>";
	             for(var i = 0; i < productArray.length; i++) {
	                 html += "<li onclick='getItemSubCat("+productArray[i].category_id+")'> "+ productArray[i].sub_category_name + "</li>";
	             }
	             html+="</ul>";
	             document.getElementById("Subcategories").innerHTML = html;
	                
	              }
	           
	          },
	       failure: function()
	       {
	        console.log("failure");
	       },
	          error: function()
	          {
	           console.log("error");
	          }
	        });
	   }
	else{
	     
	    }	
}

function getAllItemsOfSubCat(subcatId){
	
}

