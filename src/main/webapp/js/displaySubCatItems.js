$.ajax({

      type: "get",
      url:  "http://localhost:9000/EC/webapi/subcategory/getAllitemsSubCategory/"+myFunction(),
      //data: JSON.stringify(myFunction()),
      //datatype : JSON,
      headers: {
       'username':sessionStorage.username?sessionStorage.getItem('username'):'',
       'password':sessionStorage.username?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

 },
      success: function(productArray){
       console.log("hereX")
        console.log(productArray);
       console.log("hereY")
        
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

       html += "<a><img src="+productArray[i].pic_location+" height='75' width='75' /></a>";

       html+="</td>";

       html+="</tr>";

       html+="</tbody>";

       

       

//       html += "<h3> Item Name:"+productArray[i].pic_location+"</h3>";

//       html += "<a><img src="+productArray[i].pic_location+" /></a>";

       html += "</div>";

       html += "</div>";

       html += "</li>";
       
                          
          }
          html+="</ul>";
          console.log("html is "+html);
          document.getElementById("allitemsubcat").innerHTML = html;
          
      }
    });


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