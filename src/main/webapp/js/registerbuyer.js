//=================================================AJAX CALL FOR STORE A REGISTER DATA IN DATABASE=====================================//
//$('#register_form').submit(function()
function Submit(){
	var jsondata={
            Name: jQuery("#text1").val().concat(' ',jQuery("#text2").val()),
            Mobile: jQuery("#buyer_mobile").val(),
            Email: jQuery("#buyer_email").val(),
            Password: jQuery("#buyer_password").val(),
            Address1: jQuery("#buyer_address1").val(),
            Address2: jQuery("#buyer_address2").val(),
            DoB : jQuery("#buyer_dob").val().split("/").reverse().join("-")
       };
	
	console.log(jQuery("#buyer_dob").val().split("/").reverse().join("-"));
	console.log($('#register_form').serialize());
	$.ajax({
		type: "post",
		url:  "http://localhost:9000/EC/webapi/buyerService/addBuyer",
		data    :JSON.stringify(jsondata),
		dataType: "JSON",
		async :true,
	    cache: false,
	    processData: false,
	    contentType: false,
		headers: {
			 'username':"", //sessionStorage.getItem('username'),
	         'password':"" //sessionStorage.getItem('password')
//	         'seller_id': '786'
	       },

		success: function(response){
				console.log(response.Response);
				if(response.Response == "Failure")
				{	
					alert("Sorry Failed to add the User!!!");
				}
				else
				{
					sessionStorage.setItem('username', jQuery("#buyer_mobile").val());
					sessionStorage.setItem('password', jQuery("#buyer_password").val());
					sessionStorage.setItem('name', jQuery("#text1").val().concat(' ',jQuery("#text2").val()));
					sessionStorage.setItem('mobile', jQuery("#buyer_mobile").val());
					alert("Registered Successfully. Redirecting to Homepage");
					window.location.href ="http://localhost:9000/EC/homepage.html";
				}
				
		},
		error : function(response)
		{
			console.log(response);
			alert("Sorry Failed to add the item!!!");
		}
			     });
	
			   return false;
			   
    };
    
//=======================================================================END OF CODE==================================================//
