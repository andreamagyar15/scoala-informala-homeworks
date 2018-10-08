function Cart(){
  this.productId="";
  this.quantity="";
  this.productType="";
  this.prod="";
}
Cart.prototype.addElementToCart=function(orderid,newElement){
  return $.ajax({
      url:"http://localhost:8090/order/add/"+orderid,
      method:'POST',
       contentType:"application/json",
      data:JSON.stringify({
          "productid":newElement.productid,
          "quantity":newElement.quantity,
          "productType":newElement.prodType.toUpperCase(),
      }),
    })
  }
Cart.prototype.getCart=function(orderid){
  return $.ajax({
    url:"http://localhost:8090/order/all/"+orderid,
    method:'GET'
  })
}
Cart.prototype.deleteCartElement=function(obj,orderid){
  return $.ajax({
    url:"http://localhost:8090/order/delete/"+orderid+"/"+obj.prod,
    method:'POST',
  //  contentType:"application/json",
    //data:JSON.stringify({
  //    "prod":obj.prod,
  //  }),
  })
}
Cart.prototype.checkout=function(id){
  return $.ajax({
    url:"http://localhost:8090/order/checkout/"+id,
    method:'POST',
  })
}
Cart.prototype.getTotal=function(id){
  return $.ajax({
    url:"http://localhost:8090/order/total/"+id,
    method:"GET"
  })
}
  Cart.prototype.updateQuantity=function(id,quantity){
    return $.ajax({
      url:"http://localhost:8090/order/quantity/"+id+"/"+quantity,
      method:"POST"
    })
}
