window.addEventListener("load",function(){
  var urlString=window.location.href;
  urlString=getUrlParameter("id");
  var product=new Product();
  var token=localStorage.getItem('role');
  if(token=="ADMIN" ){
    var deleteButton=document.getElementById("deleteButton");
    var updateButton=document.getElementById("updateButton");
    deleteButton.style.display="block";
    updateButton.style.display="block";
  }else{
    var deleteButton=document.getElementById("deleteButton");
    var updateButton=document.getElementById("updateButton");
    deleteButton.style.display="none";
    updateButton.style.display="none";
  }
  var addToCart=document.getElementById("addToCart");
  addToCart.addEventListener("click",addElementToCart);
  product.getProductDetails(urlString)
  .then(displayProduct);
//  var sendUpdate=document.getElementById("sendUpdate");
//  sendUpdate.addEventListener("click",sendUpdateInfo);
function displayProduct(){
  var title=document.getElementById("title");
        title.innerHTML="<h3>" + product.title +'</h3>';
        var image=document.getElementById("img");
        image.innerHTML="<img src="+product.image+" />";
        var description =document.getElementById("description");
        description.innerHTML+=product.description;
        var size=document.getElementById("size");
        size.innerHTML+=product.size;
        var price=document.getElementById("price");
        price.innerHTML+=product.price;
        var arrival=document.getElementById("arrival");
        arrival.innerHTML+=product.arrival;
}
function addElementToCart(event){
  var cart=new Cart();
  var orderid=localStorage.getItem("orderid");
  var cat=getUrlParameter("categorie").toUpperCase();
  var obj=new Object();
  obj.productid=urlString;
  obj.quantity=1;
  obj.prodType=cat
  cart.addElementToCart(orderid,obj)
  .then(function(response){
    console.log(response);
    localStorage.setItem("orderid",response.value);
    alert("Added to cart")})
  .catch(function(error){
    alert("error add");
  })
}
})

function getUrlParameter(name) {
   name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
   var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
   var results = regex.exec(location.search);
   return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};
