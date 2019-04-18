$('#login')
.click(
		function(){
	 sessionStorage.setItem("username", document.getElementById("buyer_email").value);
	 sessionStorage.setItem("password", document.getElementById("buyer_password").value);
	  console.log(sessionStorage.getItem("username"));
   $.ajax({
       url: "webapi/buyerService/buyerInfo",
       data: "",
       async :true,
       cache: false,
       processData: false,
       contentType: false,
       type: 'GET',
       //username: sessionStorage.getItem("username"),
       //password: sessionStorage.getItem("password"),
       headers: {
   		//'username':document.getElementById("buyer_email").value,
   		//'password':document.getElementById("buyer_password").value
   		'username': sessionStorage.getItem("username"),
        'password': sessionStorage.getItem("password")
		},
       success: function (dataofconfirm) {
       	/* window.open("http://localhost:9000"+"/EC/404.html", "_blank"); */
       	console.log(dataofconfirm);
       	
       	//sessionStorage.setItem('username', JSON.parse(dataofconfirm).mobile);
		//sessionStorage.setItem('password', JSON.parse(dataofconfirm).password);
		sessionStorage.setItem('name', JSON.parse(dataofconfirm).name);
		sessionStorage.setItem('mobile', JSON.parse(dataofconfirm).mobile);
		//alert(dataofconfirm);
       	window.location.href ="http://localhost:9000/EC/homepage.html";  
       	/* console.log( "the feedback from your API: " + dataconfirm ); */
       	/* document.getElementById("stage").innerHTML=url;
       	document.getElementById("stage").style.height = "800px"; */
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