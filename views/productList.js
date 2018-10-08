window.addEventListener("load",function(){
  var urlString=window.location.href;
  var token=localStorage.getItem('accessToken');
  var role=localStorage.getItem('role');
  var addButton=document.getElementById('addNewProduct');

  if(role=='ADMIN' ){
    addButton.style.display="block";
  }else{
    addButton.style.display="none";
  }

  urlString=getUrlParameter("categorie");
  var contentRootUrl;
  if(urlString==="bags" || urlString==="shoes" || urlString==="hats" || urlString=="tshirts"){
    contentRootUrl="http://localhost:8090/products/categories/"+urlString;
  }else if(urlString=="products"){
    contentRootUrl="http://localhost:8090/products";
  }

  var newProductButton=document.getElementById("addNewProduct");
  newProductButton.addEventListener("click",displayForm);
  var sendNewProduct=document.getElementById("sendNewProduct");
  sendNewProduct.addEventListener("click",validateAndSendForm);
  getDatafromAPI(contentRootUrl)
  .then(function(response){
    console.log(response);
    displayContent(contentRootUrl,response);

  });

  function getDatafromAPI(url){
    var resp=$.get(url);
    return resp;
}

  function displayForm(){
    var formState=document.getElementById("newProductForm");
    if(formState.style.display=="none"){
      formState.style.display="block";
    }else{
      formState.style.display="none";
    }
  }

  var datepicker=document.getElementById("datepicker");
  $( function() {
      $( "#datepicker" ).datepicker();
  } );
  function validateAndSendForm(event){
    event.preventDefault();
    var newProduct=new Product();
    var object=new Object();
    var newTitle=document.getElementById("titleInput");
    var newImage=document.getElementById("imageInput");
    var newSize=document.getElementById("sizeInput");
    var newPrice=document.getElementById("priceInput");
    var newDescription=document.getElementById("descriptionInput");
    var newArrival=document.getElementById("datepicker");
    var newAmount=document.getElementById("amountInput");
    var newType=document.getElementById("typeInput");
    var errorContainer=document.getElementById("erroText");
    var element=newType.selectedIndex;
    var type="";
    switch (element) {
      case 0:
        type="tshirts";
        break;
      case 1:
          type="shoes";
          break;
      case 2:
          type="hats";
          break;
      case 3:
          type="bags";
          break;
    }
    var ok=true;
    if(newAmount.value==""||isNaN(newAmount.value)){
      ok=false;
      newAmount.style.borderColor="red";
    }else{
      newAmount.style.borderColor="#ced4da";
      object.amount=newAmount.value;
    }
    if(newTitle.value=="" || newTitle.value.length>50){
      ok=false;
      newTitle.style.borderColor="red";
    }else{
      newTitle.style.borderColor="";
      object.title=newTitle.value;
    }
    if(newDescription.value==""|| newDescription.value.length>255){
      ok=false;
      newDescription.style.borderColor="red";
    }else{
      newDescription.style.borderColor="";
      object.description=newDescription.value;
    }
    if(newSize.value==""||isNaN(newSize.value)){
      ok=false;
      newSize.style.borderColor="red";
    }else{
      newSize.style.borderColor="";
      object.size=newSize.value;
    }
    if(newArrival.value==""){
      ok=false;
      newArrival.style.borderColor="red";
    }else{
      newArrival.style.borderColor="";
      object.arrival=newArrival.value;
    }
    if(newImage.value==""){
      ok=false;
      newImage.style.borderColor="red";
    }else{
      newImage.style.borderColor="";
      object.image=newImage.value;
    }
    if(newPrice.value==""||isNaN(newPrice.value)){
      ok=false;
      newPrice.style.borderColor="red";
    }else{
      newPrice.style.borderColor="";
      object.price=newPrice.value;
    }

    if (!ok){
      errorContainer.innerHTML="Please check the inputs";
    }else{
      object.type=type.toUpperCase();
      var cookieFromStorage=getCookieFromLocalStorage();
    newProduct.createNewProduct(object,cookieFromStorage)
    .then(function(response){
      setTimeout(function(){
      location.reload();
    },2000);
      })
    .catch(function(error){
        alert("NOT authoriazed");
      })
      }
    }
  });
function displayContent(url,respContent){
  var productListContainer= document.getElementById("listContainer");
  var productListEl="";
  for(var i=0;i<respContent.length;i++){
    var index=i+1;
    var productEntry="";
    var productContent=displayElements(respContent[i]);
    productEntry=productContent;
    productListEl+=productEntry;
    productListContainer.innerHTML+=  productEntry;
  }
}

function displayError(jqXHR,textStatus,errorThrom){
    console.log(jqXHR,textStatus,errorThrom);
    var content=document.getElementById("contentContainer");
    articleContainers.innerHTML="We are sorry. "+jqXHR.status;
}
function displayElements(content){
  let urlString=getUrlParameter("categorie");
  var template='<div class="col-md-4 col-sm-6 portfolio-item" id='+content.id+'>';
  template+='<a class="portfolio-link"  href="productDetail.html?id='+content.id+'&categorie='+urlString+'">';
  template+='<div class="portfolio-hover"></div>';
  template+='<img class="img-fluid" src="'+content.imgUrl+'" alt="">';
  template+='  <div class="portfolio-caption">';
  template+='<h4>'+content.title+'</h4>';
  template+='</div>';
  template+='  </a>';
  template+='<button class="addProductToCart" onClick="addToCart(event)">Add to cart</button>';
  template+='<div id="buttonAdmin">';
  var token=localStorage.getItem('role');
  if(token=='ADMIN' ){
      template+='<button type="button" class="btn btn-primary showButton" onClick="updateProduct(event)" data-toggle="modal" data-target="#updateModal" id="updateButton" >Update</button>';
      template+='<button type="button" class="btn btn-primary showButton" onClick="deleteProduct(event)" id="deleteButton" >Delete</button>';
  }else{
      template+='<button type="button" class="btn btn-primary hideButton" onClick="updateProduct(event)" data-toggle="modal" data-target="#updateModal" id="updateButton" >Update</button>';
      template+='<button type="button" class="btn btn-primary hideButton" onClick="deleteProduct(event)" id="deleteButton" >Delete</button>';
  }
  template+='</div>';
  template+=' </div>';
  return template;
}

function addToCart(event){
  event.preventDefault();
  var urlString=getUrlParameter("categorie");
  var orderid=localStorage.getItem("orderid");
  if (orderid==null){
    orderid=0;
  }
  var cart=new Cart();
  var obj=new Object();
  obj.productid=parseInt(event.path[1].id);
  obj.quantity=1;
  obj.prodType=urlString;
  cart.addElementToCart(orderid,obj)
    .then(function(response){
      console.log(response);
      localStorage.setItem("orderid",response.value);
      alert("Added")
    })
    .catch(function(error){
      alert("add error");
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
