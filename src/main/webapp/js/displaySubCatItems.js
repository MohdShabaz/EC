var item_array;
$.ajax({

      type: "get",
      url:  "http://localhost:9000/EC/webapi/subcategory/getAllitemsSubCategory/"+myFunction(),
      //data: JSON.stringify(myFunction()),
      //datatype : JSON,
      headers: {
       'username':sessionStorage.username?sessionStorage.getItem('username'):'',
             'password':sessionStorage.password?sessionStorage.getItem('password'):''

 },
      success: function(productArray){
    	  item_array = productArray;
       console.log("hereX");
        console.log(productArray);
       console.log("hereY");
        
//        var html="<ul><div class ='category'>";
//        for(var i = 0; i < productArray.length; i++) {
//            html += "<li onmouseover='subcatdisplay("+productArray[i].id+")'><span class='category'> "+ productArray[i].category_name + "</span></li>";
//        }
//        html+="</ul>";
//        //console.log("here11");
//        document.getElementById("categories").innerHTML = html;
//        //console.log("here12")
       /*
       var html="<ul><div>";
          for(var i = 0; i < productArray.length; i++) {
           //console.log("prod item_id is "+productArray[i].item_id);
              html += "<li onclick='itemDisplay("+productArray[i].item_id+")'><div class='row'> ";
              //html += "<li><div class='row'>";
              html += "<div class='column' style='background-color:#ffffff;'>";
              html += "<tbody>";
              html += "<tr>";
              html += "<img src='images'></img>"
              html += "<h2> Item Name:"+productArray[i].name+"</h2>";
              html += "</div>";
              html += "</div>";
              html += "</li>";*/
       
       var html="<ul><div>";
          for(var i = 0; i < productArray.length; i++) {
       html += "<li onclick='itemDisplay("+productArray[i].item_id+")'><div class='row'> ";
       
       //html += "<li><div class='row'>";

       html += "<div class='column' style='background-color:#aaa;'>";

       html+="<tbody>";

       html+="<tr>";

       html+="<td>";

       html += "<h2> Item Name:"+productArray[i].name+"</h2>";

       html+="</td>";

       html+="<td>";
       
       html += "<div id='container'>";
       
       html += "<div style='position: relative;'><a><img id='content_img"+i+"' src="+productArray[i].pic_location+" height='75' width='75' /></a></div>";
       
       html += "<div style='border:1px solid black;position: absolute;left: 50px;z-index: 2;display: none;' id=content_img"+i+"details> <div>Item Name:"+productArray[i].name+"</div>" +
           "<div>Description: "+productArray[i].description+"</div></div>";
       
       
       html+="</td>";

       html+="</tr>";

       html+="</tbody>";

       

       

//       html += "<h3> Item Name:"+productArray[i].pic_location+"</h3>";

//       html += "<a><img src="+productArray[i].pic_location+" /></a>";

       html += "</div>";

       html += "</div>";
       
       
       
       html += "</li>";
       
                          
          }
          html+="</div>"
          html+="</ul>";
          console.log("html is "+html);
          document.getElementById("allitemsubcat").innerHTML = html;
          
      }
    });




$.ajax({

    type: "get",
    url:  "http://localhost:9000/EC/webapi/itemService/getAllBrands/",
    //data: JSON.stringify(myFunction()),
    //datatype : JSON,
    headers: {
     'username':sessionStorage.username?sessionStorage.getItem('username'):'',
           'password':sessionStorage.password?sessionStorage.getItem('password'):''

},
    success: function(brandArray){
     //console.log("hereX");
      //console.log(productArray);
     //console.log("hereY");
     
     var html="";
     html += '<option value="">' +'</option>';
        for(var i = 0; i < brandArray.length; i++) {
        	html += '<option value="' + brandArray[i].brand + '">'+ brandArray[i].brand +'</option>';
     
                        
        }
        console.log(html);
        document.getElementById("brand").innerHTML = html;
        
    }
  });

function ValueTest(min, max, limit){
	if(min>=0 && min<=max && (max<=limit || limit==-1)){
		return true;
	}return false;
}

function myFilter(){
	if(filterValuesCheck()==false){
		alert("Enter proper values for Filters")
		return;
	}
	var html="<ul><div>";
	var minP = parseInt(document.getElementById("minPrice").value);
	var maxP = parseInt(document.getElementById("maxPrice").value);
	var minR = parseInt(document.getElementById("minRating").value);
	var maxR = parseInt(document.getElementById("maxRating").value);
	var br = document.getElementById("brand").value;
	if((ValueTest(minP, maxP, -1) && ValueTest(minR, maxR, 5)) == false){
		alert("Enter proper values for Filters")
		return;
	}
	if(br == null){
		br = "";
	}
	for(var i = 0; i < item_array.length; i++) {
		if(filterCondition(i, minP, maxP, minR, maxR, br) == true){
			   html += "<li onclick='itemDisplay("+item_array[i].item_id+")'><div class='row'> ";
		       
		       //html += "<li><div class='row'>";

		       html += "<div class='column' style='background-color:#aaa;'>";

		       html+="<tbody>";

		       html+="<tr>";

		       html+="<td>";

		       html += "<h2> Item Name:"+item_array[i].name+"</h2>";

		       html+="</td>";

		       html+="<td>";
		       
		       html += "<div id='container'>";
		       
		       html += "<div style='position: relative;'><a><img id='content_img"+i+"' src="+item_array[i].pic_location+" height='75' width='75' /></a></div>";
		       
		       html += "<div style='border:1px solid black;position: absolute;left: 50px;z-index: 2;display: none;' id=content_img"+i+"details> <div>Item Name:"+item_array[i].name+"</div>" +
		           "<div>Description: "+item_array[i].description+"</div></div>";
		       
		       
		       html+="</td>";

		       html+="</tr>";

		       html+="</tbody>";

		       

		       

//		       html += "<h3> Item Name:"+productArray[i].pic_location+"</h3>";

//		       html += "<a><img src="+productArray[i].pic_location+" /></a>";

		       html += "</div>";

		       html += "</div>";
		       
		       
		       
		       html += "</li>";
		}
	}
	html+="</div>"
    html+="</ul>";
    console.log("html is "+html);
    document.getElementById("allitemsubcat").innerHTML = html;
}

function filterValuesCheck(){
	if(PositiveIntegerTest("minPrice") && PositiveIntegerTest("maxPrice") &&
			PositiveIntegerTest("minRating") && PositiveIntegerTest("maxRating")){
		return true;
	}return false;
}

function filterCondition(x,minP, maxP, minR, maxR, br){
	var price = item_array[x].price;
	var rating = parseFloat(item_array[x].rating);
	var brand = item_array[x].brand;
	if(brand == null){
		brand = "";
	};
	if(price >= minP && price<=maxP && rating>=minR && rating>=minR && (br == "" || br == brand)){
		return true;
	}
	return false;
}

function PositiveIntegerTest(x){
	if(/^\d+$/.test(document.getElementById(x).value) && document.getElementById(x).value >= 0){
		return true;
	}else{
		return false;
	}
}

function myFunction(){
var a = location.href;
console.log("a is"+a);
var b = a.substring(a.indexOf("=")+1);
console.log("b is"+b);
var c=JSON.parse('{"id":'+b+'}');
console.log("c is "+typeof(c));
return b;
}

function itemDisplay(itemId){
window.location.href = "http://localhost:9000/EC/displaySelectedItem.html?itemId="+itemId;
}

jQuery(document).ready(function($){ 
window.onmouseover = function (event)
{
 var imgContent=event.target;
 if (imgContent.id.includes('content_img'))
 {
  var idOfDetails=imgContent.id+"details";
  document.getElementById(idOfDetails).style.display = "block";
 }
}

window.onmouseout = function (event)
{
 var imgContent=event.target;
 if (imgContent.id.includes('content_img'))
 {
  var idOfDetails=imgContent.id+"details";
  document.getElementById(idOfDetails).style.display = "none";
 }
}
});