$('#login')
.click(
		function(){
	 sessionStorage.setItem("username", document.getElementById("seller_email").value);
	  sessionStorage.setItem("password", document.getElementById("seller_password").value);
	  console.log(sessionStorage.getItem("username"));
   $.ajax({
       url: "webapi/sellerService/sellerInfo",
       data: "",
       async :true,
       cache: false,
       processData: false,
       contentType: false,
       type: 'GET',
       //username: sessionStorage.getItem("username"),
       //password: sessionStorage.getItem("password"),
       headers: {
   		'username':sessionStorage.getItem("username"),
   		'password':sessionStorage.getItem("password")
		},
       success: function (dataofconfirm) {
       	/* window.open("http://localhost:9000"+"/EC/404.html", "_blank"); */
       	//console.log(dataofconfirm);
       	sessionStorage.setItem('name', JSON.parse(dataofconfirm).name);
		sessionStorage.setItem('mobile', JSON.parse(dataofconfirm).mobile);
       	window.location.href ="http://localhost:9000/EC/seller_dashboard.html";  
       	
       },
       error: function(data){
       	console.log("Failure");
       }
       
   });
   return false;
	
});
/**
 * 
 */