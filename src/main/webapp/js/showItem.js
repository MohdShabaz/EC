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
          
          var y = (product.price).toFixed(2);
          document.getElementById("priceItem").innerHTML = y;

          
          console.log(product.barcode);

          document.getElementById("itemImage").src = product.pic_location;
          
          document.getElementById("newprice").innerHTML = (y*(100-x)/100).toFixed(2);
       
          
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

function shopNow(){
console.log("yes shopNow");
}