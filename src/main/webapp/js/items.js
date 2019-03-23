function addToCart(butt)
{
console.log(butt.name);

var jsonObj = { "item_id ":butt.name, "quantity":1};

console.log(sessionStorage.getItem("username"));


$.ajax({url:"http://localhost:8080/EC/webapi/ShoppingCartService/addCart", type:"POST",
 data: butt.name,
 dataType: "text",
 headers: {
		'username':sessionStorage.getItem("username"),
		'password':sessionStorage.getItem("password")
	},
  async: true,
 
 success: function(data){
  console.log(23);
 },
 error: function(data){
  
 }
});

document.getElementById("myForm").style.display = "block";

}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}

window.onload = function () {
document.getElementById("myForm").style.display = "none";
console.log("1");
var test="Hi";

    
    
$.ajax({url:"http://localhost:8080/EC/webapi/itemService/itemsTop", type:"GET",
 async: false,
 headers: {
		'username':sessionStorage.getItem("username"),
		'password':sessionStorage.getItem("password")
	},
 success: function(data){
  var myObject = eval('(' + data + ')');
  for (i in myObject)
  {
      var value=(parseInt(i)+1);
      
      var loc = window.location.pathname;
      var dir = loc.substring(0, loc.lastIndexOf('/'));
      
      console.log(dir);
      
//       if(i==0)
//        document.getElementById('1_name').innerHTML=myObject[i]["name"];
//       if(i==1)
//        document.getElementById('2_name').innerHTML=myObject[i]["name"];
//       if(i==2)
//        document.getElementById('3_name').innerHTML=myObject[i]["name"];
      document.getElementById(value+('_name')).innerHTML=myObject[i]["name"];
      document.getElementById(value+('_image')).src=myObject[i]["pic_location"]+'.png';
      
      document.getElementById("button_"+i).name=myObject[i]["item_id"];
      
      
      
      console.log(myObject[i]["discount"]);
      
      
      document.getElementById(value+('_discount')).innerHTML=(parseInt(parseFloat(myObject[i]["discount"])*100))+'%'+" discount";
     
  } 
 },
       error: function(data)
       {
        console.log("2");
       }
});
}