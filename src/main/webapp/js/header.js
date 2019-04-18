 //$(document).ready(function(){
		if(localStorage.email=='admin@gmail.com')
			{

			}
		 else  if(sessionStorage.name!=null){
		        document.getElementById('signin_top_bar').innerHTML="Hi!  "+sessionStorage.name,
		        $('#user_info').width(80),
		        document.getElementById('user_name').innerHTML=sessionStorage.mobile,
		        //document.getElementById('user_email').innerHTML=sessionStorage.mobile,
		        document.getElementById('user_image').src="images/profile_avatar_60x60.png",
		        document.getElementById('account_setting').innerHTML="Account setting",
		        document.getElementById('sign_out').innerHTML="Sign Out";
		        $('#sign_out').click(function(){
		        	sessionStorage.clear();
		        });
			$('#user_info').hover(function(){
				$(this).find('.dropdown-menu').first().stop(true, true).slideDown(0);
							 $(this).toggleClass('open');
					 }, function () {
							 $(this).find('.dropdown-menu').first().stop(true, true).slideUp(0);
							 $(this).toggleClass('open');
			});
				 }
 //});
function Seller(){
	  //alert(localStorage.user_PINCODE);
	  if(localStorage.fName!=null)
	  {
	   if(localStorage.user_PINCODE!=8888)
	   {
	     window.location="http://localhost:9000/EC/Seller_dashboard.html";
	   }
	   else {
	     window.location="http://localhost:9000/EC/address_form_seller.html";
	   }
	  }
	  else {
		  window.location="http://localhost:9000/EC/loginseller.html";
	 }
	 if(sessionStorage.name!=null){
	        document.getElementById('signin_top_bar').innerHTML="Hi!  "+sessionStorage.name,
	        $('#user_info').width(80),
	        document.getElementById('user_name').innerHTML=sessionStorage.mobile,
	        //document.getElementById('user_email').innerHTML=sessionStorage.mobile,
	        document.getElementById('user_image').src="images/profile_avatar_60x60.png",
	        document.getElementById('account_setting').innerHTML="Account setting",
	        document.getElementById('sign_out').innerHTML="Sign Out";
	        $('#sign_out').click(function(){
	        	sessionStorage.clear();
	        });
	        
      }
};

function searchfunction(){
  if($('#Category').val() == '')
     {
     }
  else {
	  localStorage.category_name=$('#Category').val();
        window.location="http://localhost:9000/EC/prod_list_1.html";
     }
};

function cart(){
    localStorage.cart="cart";
	  if(localStorage.user_Id!=null)
		  {
		  window.location="http://localhost:9000/EC/cart.html";
		  }
	  else
		  {
		  window.location="http://localhost:9000/EC/loginseller.html";
		  }
  };
