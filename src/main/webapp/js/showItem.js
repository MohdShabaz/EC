$.ajax({

      type: "get",
      url:  "http://localhost:9000/EC/webapi/itemService/getitem/"+myFunction(),
      //data: JSON.stringify(myFunction()),
      //datatype : JSON,
      headers: {
       'username':sessionStorage.username?sessionStorage.getItem('username'):'',
       'password':sessionStorage.username?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

 },
      success: function(product){
       console.log("hereA")
        console.log(product);
       console.log("hereB")
        
          document.getElementById("nameItem").innerHTML = product.name;
          
          var x = (product.discount*100).toFixed(2);          
          document.getElementById("discountItem").innerHTML = x;
          console.log("seller");
          console.log(product.seller_name);
          document.getElementById("seller_name").innerHTML = product.seller_name;
          document.getElementById("descriptionItem").innerHTML = product.description;
          
          var y = (product.price).toFixed(2);
          document.getElementById("priceItem").innerHTML = y;

          document.getElementById("itemImage").src = product.pic_location;
       
       
//        var html="<ul><div class ='category'>";
//        for(var i = 0; i < productArray.length; i++) {
//            html += "<li onmouseover='subcatdisplay("+productArray[i].id+")'><span class='category'> "+ productArray[i].category_name + "</span></li>";
//        }
//        html+="</ul>";
//        //console.log("here11");
//        document.getElementById("categories").innerHTML = html;
//        //console.log("here12")
       
//       var html="<ul><div>";
//          for(var i = 0; i < productArray.length; i++) {
//           //console.log("prod item_id is "+productArray[i].item_id);
//              html += "<li onclick='itemDisplay("+productArray[i].item_id+")'><div class='row'> ";
//              //html += "<li><div class='row'>";
//              html += "<div class='column' style='background-color:#aaa;'>";
//              html += "<h2> Item Name:"+productArray[i].name+"</h2>";
//              html += "</div>";
//              html += "</div>";
//              html += "</li>";
//                          
//          }
//          html+="</ul>";
//          document.getElementById("allitemsubcat").innerHTML = html;
          
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