$.ajax({

      type: "get",
      url:  "http://localhost:9000/EC/webapi/orderService/sellerOrderDetails",
      //data: JSON.stringify(myFunction()),
      //datatype : JSON,
      headers: {
       'username':sessionStorage.username?sessionStorage.getItem('username'):'',
       'password':sessionStorage.username?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

 },
      success: function(OrdersArray){
       console.log("hereX")
        console.log(OrdersArray);
       console.log("hereY")
        
//        var html="<ul><div class ='category'>";
//        for(var i = 0; i < OrdersArray.length; i++) {
//            html += "<li onmouseover='subcatdisplay("+OrdersArray[i].id+")'><span class='category'> "+ OrdersArray[i].category_name + "</span></li>";
//        }
//        html+="</ul>";
//        //console.log("here11");
//        document.getElementById("categories").innerHTML = html;
//        //console.log("here12")
       /*
       var html="<ul><div>";
          for(var i = 0; i < OrdersArray.length; i++) {
           //console.log("prod item_id is "+OrdersArray[i].item_id);
              html += "<li onclick='itemDisplay("+OrdersArray[i].item_id+")'><div class='row'> ";
              //html += "<li><div class='row'>";
              html += "<div class='column' style='background-color:#ffffff;'>";
              html += "<tbody>";
              html += "<tr>";
              html += "<img src='images'></img>"
              html += "<h2> Item Name:"+OrdersArray[i].name+"</h2>";
              html += "</div>";
              html += "</div>";
              html += "</li>";*/
       
       var html="<ul><div>";
          for(var i = 0; i < OrdersArray.length; i++) {
//          for(var i = 0; i <1 ; i++) {
       html += "<li onclick='itemDisplay("+OrdersArray[i].item_id+")'><div class='row'> ";

       //html += "<li><div class='row'>";

       html += "<div class='column' style='background-color:#aaa;'>";

       html+="<tbody>";

       html+="<tr>";

       html+="<td>";

       html += "<h2> Item Name:"+OrdersArray[i].name+"</h2>";
       

       html+="</td>";
       html+="<td>";

       html += "<h2> Item Quantity:"+OrdersArray[i].quantity+"</h2>";

       html+="</td>";
 html+="<td>";
       
       
      

       html += "<a><img src="+OrdersArray[i].pic_location+" height='75' width='75' /></a>";

       html+="</td>";

       


       

       

//       html += "<h3> Item Name:"+OrdersArray[i].pic_location+"</h3>";

//       html += "<a><img src="+OrdersArray[i].pic_location+" /></a>";

       

      

      
      

       html+="</tr>";

       html+="</tbody>";
       html += "</div>";
       html += "</div>";
       html+="</li>";
    	   
       html+="</td>";
       html += "<li onclick='itemDisplay("+OrdersArray[i].id+")'>Ship<\li>";
       html+="<td>";
       
                          
          }
          html+="<\div>";
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