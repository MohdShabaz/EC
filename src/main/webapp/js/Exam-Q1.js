var itemId;

function SearchItem() {	
	itemId = document.getElementById("itemId").value;
	$.ajax({
		 url:"http://localhost:9000/EC/webapi/itemService/getitem/" + itemId,
		 type:"GET",
		 async: false,
		 headers: {
		     'username':sessionStorage.username?sessionStorage.getItem('username'):'',
		     'password':sessionStorage.password?sessionStorage.getItem('password'):''//sessionStorage.getItem('password')

		},
		 success: function(data){
			 console.log(data);
			 LoadItemData(data);
		 },
		 error: function(data)
		 {

		 }
		});
}

function LoadItemData(data) {
	var htmlDiv = "";
    var itemPrice = (data.price).toFixed(2);
    var discount = (data.discount*100).toFixed(2);          
    var discountedPrice = ((itemPrice * (100 - discount))/100.0).toFixed(2);

	htmlDiv += '<img height=20% width=30% src=' + data.pic_location + '>' + '<br><label class="control-label col-sm-2">Item ID: </label><h5 id = "itemid">'+itemId+'</h5>'+
    '<br><label class="control-label col-sm-2">Item Name: </label><h5>'+data.name+'</h5>'+
    '<br><label class="control-label col-sm-2">Item Price: </label><h5>'+discountedPrice+'</h5>';

	document.getElementById("ItemInfo").innerHTML = htmlDiv;
} 