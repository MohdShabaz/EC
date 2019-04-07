$('#Seller_detail_form').submit(function(){
//	  alert("seller id: "+localStorage.user_Id);
		var jsondata={
	             Name: jQuery("#Seller_Name").val(),
	             Mobile: jQuery("#Seller_Mobile").val(),
	             Email: jQuery("#Seller_Email").val(),
	             Password: jQuery("#Seller_password").val(),
	             Address1: jQuery("#Seller_Address1").val(),
	             Address2: jQuery("#Seller_Address2").val(), 
	        };
		
		console.log(jsondata);
		console.log($('#Seller_detail_form').serialize());
		$.ajax({
			type: "post",
			url:  "http://localhost:9000/EC/webapi/sellerService/addSeller",
			data    :JSON.stringify(jsondata),
			dataType: "JSON",
			async :true,
		    cache: false,
		    processData: false,
		    contentType: false,
			headers: {
				 'username':"", //sessionStorage.getItem('username'),
		         'password':"" //sessionStorage.getItem('password')
//		         'seller_id': '786'
		       },

			success: function(response){
					console.log(response.Response);
					if(response.Response == "Failure")
					{	
						alert("Sorry Failed to add the User!!!");
					}
					else
					{
						sessionStorage.setItem('username', jQuery("#Seller_Mobile").val());
						sessionStorage.setItem('password', jQuery("#Seller_password").val());
						alert("Added Successfully. Redirecting to Dashboard");
						window.location.href ="http://localhost:9000/EC/loginseller.html";
					}
					
			},
			error : function(response)
			{
				console.log(response);
				alert("Sorry Failed to add the item!!!");
			}
				     });
		
				   return false;
	     });
/**
 * 
 */