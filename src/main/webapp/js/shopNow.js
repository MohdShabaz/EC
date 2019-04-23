function shopNow(butto){

	console.log(document.getElementById("quan").value+" x,x "+document.getElementById("quan").max);
	//alert(document.getElementById("quan").value+" x,x "+document.getElementById("quan").max);
var q = 1
if(document.getElementById("quan")==null){
	q = 1;
}
else if(document.getElementById("quan").value==""){
	q = 1;
}else{
	q = parseInt(document.getElementById("quan").value);
}
console.log("Quantity ="+q);
if(q <= parseInt(document.getElementById("quan").max) && q >0){

console.log(butto.baseURI);
var x = butto.baseURI;
var itemId = myFunction();
console.log("hello" + itemId);

var jsonObj = { item_id: itemId, quantity:document.getElementById("quan").value};

console.log(jsonObj);
if($("#BdayDiscountCashBack").length)
{
	console.log("In if dkjhbc");
	window.location = "http://localhost:9000/EC/checkout_shop_now.html?item_id="+itemId+"&quantity="+q+"&cashback="+document.getElementById('BdayDiscountCashBack').innerHTML;
}
else
{
console.log("Not In if dkjhbc");
window.location = "http://localhost:9000/EC/checkout_shop_now.html?item_id="+itemId+"&quantity="+q;
}
}
else{

console.log(document.getElementById("quan").value);
console.log(document.getElementById("quan").max);
alert("Max Quantity Exceeded!!")
}
}

function myFunction(){
	var a = location.href;
	console.log("a is"+a);
	var b = a.substring(a.indexOf("=")+1,a.indexOf(","));
	console.log("b is"+b);
	var d = a.substring(a.indexOf(",")+1);
	console.log("d is"+d);
	document.getElementById("sub_category").href="displaySubCatItems.html?"+d;
	d=d.substring(d.indexOf(",")+1);
	var e =decodeURIComponent( d.substring(d.indexOf("=")+1,d.indexOf(",")));
	console.log("e is "+decodeURIComponent(e));
	document.getElementById("category").innerHTML = decodeURIComponent(e);
	var f = d.substring(d.indexOf(",")+1);
	console.log("f is"+f);
	var g =f.substring(f.indexOf("=")+1);
	console.log("g is "+g);
	document.getElementById("sub_category").innerHTML = decodeURIComponent(g);
	var h =g.substring(g.indexOf(",")+1);
	console.log("h is "+h);
	//document.getElementById("sub_category").href = g;

	var c=JSON.parse('{"id":'+b+'}');
	console.log("c is "+typeof(c));
	return b;
	}