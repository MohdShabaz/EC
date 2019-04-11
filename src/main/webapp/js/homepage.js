$.ajax({
      type: "get",
      url:  "http://localhost:9000/EC/webapi/category/getAllCategory/",
      dataType: "JSON",
      headers: {
    'username':sessionStorage.username?sessionStorage.getItem('username'):'',
    'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')
 },
      success: function(productArray){
       console.log("here1");
        console.log(productArray);
       console.log("here2");
        
        var html="<ul><div class ='category'>";
        for(var i = 0; i < productArray.length; i++) {
            html += "<li onmouseover='subcatdisplay("+productArray[i].id+")'><span class='category'> "+ productArray[i].category_name + "</span></li>";
        }
        html+="</ul>";
        //console.log("here11");
        document.getElementById("categories").innerHTML = html;
        
        ///document.getElementById("categories").style.cursor = pointer;
        //console.log("here12")
      }
    });

$.ajax({
    type: "get",
    url:  "http://localhost:9000/EC/webapi/bankService/getBuyerBalance/",
    dataType: "text",
    headers: {
  'username':sessionStorage.username?sessionStorage.getItem('username'):'',
  'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')
},
    success: function(data){
     
      document.getElementById("walletModal_balance").innerHTML = data;
      
      ///document.getElementById("categories").style.cursor = pointer;
      //console.log("here12")
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
         url:  "http://localhost:9000/EC/webapi/subcategory/getAllSubCategories/"+catId,
         dataType: "JSON",
         headers: {
        	 'username':sessionStorage.username?sessionStorage.getItem('username'):'',
        			    'password':sessionStorage.username?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')
        			 },
       cache: true,
         success: function(productArray){
            if(productArray!=null)
              {
             console.log("here3");
             console.log(productArray);
             console.log("here4");
             var html="<ul><div class ='category'>";
             for(var i = 0; i < productArray.length; i++) {
                 html += "<li onclick='getAllItemsOfSubCat("+productArray[i].id+")'> "+ productArray[i].sub_category_name + "</li>";
             }
             html+="</ul>";
             document.getElementById("Subcategories").innerHTML = html;
             //document.getElementById("Subcategories").style.cursor = pointer;

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
function cart()
{
	window.location = "http://localhost:9000/EC/Cart_ebay.html";
};

function AddMoney() {
	var moneyToAdd = document.getElementById("enterAmount").value;
	$.ajax({
		 url:"http://localhost:9000/EC/webapi/bankService/addBuyerWallet", 
		 type:"POST",
		 
		 headers: {
				'username': sessionStorage.getItem("username"),
				'password': sessionStorage.getItem("password")
			},
		dataType : "text",	
		data : moneyToAdd,	
		async: false,
		 success: function(data){
			 console.log("Successfully added money to buyer's account.");
		},
		 error: function(data)
		 {
		  
		 }
		 });
};

function getAllItemsOfSubCat(subcatId){
window.location.href = "http://localhost:9000/EC/displaySubCatItems.html?subcatId="+subcatId;
}