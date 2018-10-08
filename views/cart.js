document.addEventListener("DOMContentLoaded",function(event){
  var orderId=localStorage.getItem("orderid");
  listAllCart(orderId);
  var deleteButton=document.getElementById("deleteFromCart");
  deleteButton.addEventListener("click",deleteElementFromCart);
  listTotal(orderId);
  var checkOutButton=document.getElementById("checkOutOrder");
  checkOutButton.addEventListener("click",checkoutOrder);
});

function listAllCart(orderid){
  getMessagesFromApi(orderid)
  .then(displayCart);
}

function listTotal(orderid){
  getTotalVal(orderid)
  .then(displayTotal);
}

function getMessagesFromApi(orderid){
  var cart =new Cart();
  return cart.getCart(orderid);
}

function getTotalVal(orderid){
  var cart =new Cart();
  return cart.getTotal(orderid);
}

function displayCart(content){
  var cartContainer=document.getElementById("cartList");
  var template=document.getElementById("cartTemplate");
  for(var i=0;i<content.length;i++){
    displayTemplate(content[i]);
  }

  function displayTemplate(element){
    var templateClone=template.cloneNode(true);
    templateClone.removeAttribute("id");
    templateClone.id=element.product.id;
    var cartTitle=templateClone.querySelector("#cartTitle");
    var cartDescription=templateClone.querySelector("#cartDescription");
    var cartImg=templateClone.querySelector("#cartImg");
    var cartPrice=templateClone.querySelector("#cartPrice");
    var elementid=templateClone.querySelector("#elementid");
    var quantity=templateClone.querySelector("#productQuant");

    cartTitle.innerHTML=element.product.title;
    cartDescription.innerHTML=element.product.description;
    cartImg.src=element.product.imgUrl;
    cartPrice.innerHTML=element.product.price;
    elementid.innerHTML=element.product.id;
    quantity.value=element.quantity;

    var deleteFromCart=templateClone.querySelector("#deleteFromCart");
    deleteFromCart.addEventListener("click",function(event){
      deleteElementFromCart(event);
    })
    cartContainer.appendChild(templateClone);
}

}
function displayTotal(element){
  var total=document.getElementById("totalCart");
  totalCart.innerHTML+=element;
}

function deleteElementFromCart(event){
  var id=event.path[2].id;
  var cart =new Cart();
  var orderid=localStorage.getItem("orderid");
  var obj=new Object();
  obj.prod=id;
   cart.deleteCartElement(obj,orderid)
  .then(function(response){
    setTimeout(function(){
      location.reload() }, 3000)
  })
}

  function checkoutOrder(event){
    event.preventDefault();
    var orderid=localStorage.getItem("orderid");
    var cart =new Cart();
    cart.checkout(orderid)
    .then(function(response){
      alert(response.message);
      console.log(response);
        localStorage.setItem("orderid",0);
      setTimeout(function(){
        location.reload() }, 3000)
    })
    .catch(function(error){
      alert("Error.Try again");
    })
  }

  function updateQuant(event){
    var orderid=localStorage.getItem("orderid");
    var cart =new Cart();
    let quantity=document.getElementById("productQuant").value;
    var id=event.path[2].id;
    cart.updateQuantity(id,quantity)
    .then(function(response){
      location.reload();
    })
    .catch(function(error){
      alert("error ");
  })
  }
