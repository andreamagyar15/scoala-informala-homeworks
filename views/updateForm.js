window.addEventListener("load",function(){

  var sendUpdate=document.getElementById("sendUpdate");
  sendUpdate.addEventListener("click",sendUpdateInfo);
  var datepickerUpdate=document.getElementById("datepickerUpdate");
  $(function() {
  $("body").delegate("#datepickerUpdate", "focusin", function(){
      $(this).datepicker();
      
  });
});
})
function sendUpdateInfo(){
  var urlString=window.location.href;
  urlString=getUrlParameter("categorie");
  var idUpdate=document.getElementById("idUpdate").innerHTML;

  let titleUpdate=document.getElementById("titleUpdate").value;
  let descriptionUpdate=document.getElementById("descriptionUpdate").value;
  let sizeUpdate=document.getElementById("sizeUpdate").value;
  let amountUpdate=document.getElementById("amountUpdate").value;
//  let arrivalUpdate=document.getElementById("datapickerUpdate").value;
  let priceUpdate=document.getElementById("priceUpdate").value;
  let imageUpdate=document.getElementById("imageUpdate").value;
  let updatedProduct=new Object();
  let productToBeSend=new Product();
  updatedProduct.title=titleUpdate;
  updatedProduct.description=descriptionUpdate;
  updatedProduct.price=priceUpdate;
  updatedProduct.image=imageUpdate;
  updatedProduct.amount=amountUpdate;
  updatedProduct.size=sizeUpdate;

  updatedProduct.arrival="12/03/2019";
  updatedProduct.prodType=urlString.toUpperCase();
  var cookieFromStorage=localStorage.getItem("accessToken");
  productToBeSend.updateProduct(idUpdate,updatedProduct,cookieFromStorage)
  .then(function(response){
    setTimeout(function(){
    location.reload();
  },2000);})
  .catch(function(error){
    alert("NOT authoriazed");
  })
}

function updateProduct(event){

  var idProd=event.path[2].id;
  if(idProd===""){
    var urlString=window.location.href;
    urlString=getUrlParameter("id");
    idProd=  urlString;
  }
  var oldProduct=new Product();
  oldProduct.getProductDetails(idProd)
  .then(displayDetailsInForm);


function displayDetailsInForm(){
  var idUpdate=document.getElementById("idUpdate");
  var titleUpdate=document.getElementById("titleUpdate");

  var descriptionUpdate=document.getElementById("descriptionUpdate");
  var sizeUpdate=document.getElementById("sizeUpdate");
  var amountUpdate=document.getElementById("amountUpdate");
  var arrivalUpdate=document.getElementById("datapickerUpdate");
  var priceUpdate=document.getElementById("priceUpdate");
  var imageUpdate=document.getElementById("imageUpdate");
  idUpdate.innerHTML=idProd;
  titleUpdate.value=oldProduct.title;
  descriptionUpdate.value=oldProduct.description;
  priceUpdate.value=oldProduct.price;
  imageUpdate.value=oldProduct.image;
  amountUpdate.value=oldProduct.amount;
  sizeUpdate.value=oldProduct.size;
  arrivalUpdate.value=oldProduct.arrival;
  typeUpdate.value=oldProduct.type;
}
}

function deleteProduct(event){
  event.preventDefault();
  var idProd=event.path[2].id;
  if(idProd==""){
    var urlString=window.location.href;
    urlString=getUrlParameter("id");
    idProd=  urlString;
  }
  var oldProduct=new Product();
  var urlString=window.location.href;
  urlString=getUrlParameter("categorie");
  var productToBeDeleted=new Product;
  var cookieFromStorage=localStorage.getItem('accessToken');
  productToBeDeleted.deleteProduct(urlString,idProd,cookieFromStorage)
  .then(function(repsonse){
    setTimeout(function(){
    var  urlStringCat=getUrlParameter("categorie");
  window.location.href ="productList.html?categorie="+urlStringCat;
  },2000);})
  .catch(function(error){
    alert("NOT authoriazed");
  })
}
function getUrlParameter(name) {
   name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
   var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
   var results = regex.exec(location.search);
   return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};
function getCookie(name) {
      var value = "; " + document.cookie;
      var parts = value.split("; " + name + "=");
      if (parts.length == 2) {
          return parts.pop().split(";").shift();
      }
}
function getCookieFromLocalStorage(){
 var storedToken=localStorage.getItem('accessToken');
 return storedToken;
}
