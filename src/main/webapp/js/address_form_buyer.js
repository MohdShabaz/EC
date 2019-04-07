
function ChangeAddress(){
	var address_2 = jQuery("#address").val();		
	$.ajax({
			
                 type: "put",
                 url:  "http://localhost:9000/EC/webapi/buyerService/updateBuyerAddress/"+address_2,
                 dataType: "JSON",
                 
                 headers: {
                     'username':sessionStorage.username?sessionStorage.getItem('username'):'',
                     'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

                },
                 success: function(response){
                   console.log(response);
                   if(response.Response == "Success")
                   {
                	   window.location="http://localhost:9000/EC/checkout.html?address_2="+address_2;
                	   document.getElementById("address").innerHTML=address_2;
					
	                   
                   }
                   else
                        {
                          alert("enter detail correct");
                        }
                 }
               });
             return false;
 };
   