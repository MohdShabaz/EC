var sellersList = '';

window.onload = function PopulateSellersTable () {
	$.ajax({
		 url:"http://localhost:9000/EC/webapi/sellerService/getAllSellers",
		 type:"GET",
		 async: false,
		 headers: {
		     'username':sessionStorage.username?sessionStorage.getItem('username'):'',
		     'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

		},
		 success: function(data){
			 console.log(data);
			 LoadSellersTable(data);
		 },
		 error: function(data)
		 {
		  
		 }
		});
}

function LoadSellersTable(data) {
	sellersList = '<ul class="list-group">';
    for(var i = 0; i < data.length; i++) {
      AppendSellerInfo(data[i]);/*function call*/
    }
    sellersList +='</ul>';
    document.getElementById('sellersInfo').innerHTML = sellersList;
}

function AppendSellerInfo(seller) {
	console.log(seller);
	sellersList += '<li class="list-group-item">';
    sellersList +=
   	 '<div class="row">'+
      '<div>'+
       '<span class="col-sm-12"><div class=row>'+
           '<span class="col-sm-2" style="margin-left:50px">'+
             '<table style=" border-collapse: separate; border-spacing: 2px;" >'+
               '<tr >'+
                 '<td colspan="2" ><p>' + seller.serial_number + '</td>'+
               '</tr>'+
             '</table>'+
           '</span>'+
           '<span class="col-sm-1" style="margin-left:50px"><p>'+seller.seller_id+'</p></span>'+
           '<span class="col-sm-3" style="margin-left:100px"><p>'+seller.seller_name+'</p></span>'+
           '<span class="col-sm-3" style="margin-left:50px"><p>'+seller.seller_balance+'</p></span>'+
			'</span>'+
		'</div>'+
       '</span>'+
    '</div>'+
  '</div>'+
   '</li>';
}