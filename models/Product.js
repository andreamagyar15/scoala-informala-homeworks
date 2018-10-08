function Product(){
  this.id="";
  this.title="";
  this.description="";
  this.size="";
  this.amount="";
  this.price="";
  this.imageURL="";
  this.arrival="";
  this.type=""
}
Product.prototype.getProductDetails=function(id){
  var that=this;
  return $.ajax({
       url:"http://localhost:8090/products"+"/"+id,
       method:'GET'
   })
   .then (function(response){
       that.title=response.title;
       that.description=response.description;
       that.size=response.size;
       that.price=response.price;
       that.image=response.imgUrl;
       that.arrival=response.arrival;
       that.amount=response.amount;
   });
 }
  Product.prototype.createNewProduct=function(newElement,token){
       return $.ajax({
           url:"http://localhost:8090/secured/products",
           //xhrFields: {
      //  withCredentials: true
  //  },
           method:"POST",
           //contentType: 'application/json',
           credentials: "same-origin",
           data:{
               "title":newElement.title,
               "description": newElement.description,
               "price" :newElement.price,
               "amount":newElement.amount,
               "size": newElement.size,
               "imgUrl":newElement.image,
               "arrival":newElement.arrival,
               "prodType":newElement.type
           },
           beforeSend : function( xhr ) {
         xhr.setRequestHeader("X-Auth-Token", token );

     },
            // Access-Control-Allow-Credentials: true,
             //withCredentials: false
           //},
       })
}
Product.prototype.deleteProduct=function(type,id,token){
  return $.ajax({
       url:"http://localhost:8090/secured/products/delete/"+type+"/"+id,
       method:'DELETE',

      // headers:{'X-Auth-Token':'RWsfbuJLBUVb6_gYZxgy0fanS_2zeeBW'},
       headers: {
               'X-Auth-Token': token
          },
          beforeSend : function( xhr ) {
            xhr.setRequestHeader("X-Auth-Token", token );
    },
   });
};
Product.prototype.updateProduct=function(id,product,token){
  return $.ajax({
      url:"http://localhost:8090/secured/products/update/"+id,
      method:"POST",
      headers: {
              'X-Auth-Token': token
         },
         credentials: "same-origin",
      data:{
          "title":product.title,
          "description": product.description,
          "price" :product.price,
          "amount":product.amount,
          "size": product.size,
          "imgUrl":product.image,
          "arrival":product.arrival,
          "prodType":product.prodType
      },
      beforeSend : function( xhr ) {
    xhr.setRequestHeader("X-Auth-Token", token );
},
      //success:function(response){
      //    console.log("response post: ",response);
     // }//
  })
}
