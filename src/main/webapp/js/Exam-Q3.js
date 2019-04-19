
var sellerId;

function SearchSeller() {	
	sellerId = document.getElementById("sellerId").value;
	$.ajax({
		 url:"http://localhost:9000/EC/webapi/sellerService/getAllSellerItems/" + sellerId,
		 type:"GET",
		 async: false,
		 
		 success: function(data){
			 console.log(data);
			 LoadSellersTable(data);
//			 LoadItemData(data);
		 },
		 error: function(data)
		 {
		  
		 }
		});
}

function LoadSellersTable(data) {
	sellersList = '<tr>';
    for(var i = 0; i < data.length; i++) {
    	if ((i % 3) == 0) {
    		sellersList += '</tr>';
    		sellersList += '<tr>';
    	}
    	
      sellersList += AppendSellerInfo(data[i]);/*function call*/
    }
    sellersList +='</tr>';
    document.getElementById('ItemInfo').innerHTML = sellersList;
}

function AppendSellerInfo(data) {
	var htmlDiv = "<td>";
	htmlDiv += '<div  id ="customDiv" class="panel panel-default"><div class="panel-body"><div class="container-sm-11">';
    var itemPrice = (data.price).toFixed(2);
    var discount = (data.discount*100).toFixed(2);          
    var discountedPrice = ((itemPrice * (100 - discount))/100.0).toFixed(2);

	htmlDiv += '<img height=200px width=200px src=' + data.pic_location + '>' + '<br><label class="control-label col-sm-2">Item ID: </label><h5 align = "center" id = "itemid">'+data.item_id+'</h5>'+
    '<br><label class="control-label col-sm-2">Item Quantity: </label><h5 align = "center">'+data.item_quantity+'</h5>'+
    '<br><label class="control-label col-sm-2">Item Price: </label><h5 align = "center">'+discountedPrice+'</h5>'+
    '<br><label class="control-label col-sm-2">Seller Name: </label><h5 align = "center">'+data.seller_name+'</h5>';
	
	htmlDiv += "</div></div></div></td>";
	return htmlDiv;
}