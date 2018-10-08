function Message(){
  this.id="";
  this.name="";
  this.message="";
  this.email="";
  this.number="";
}
Message.prototype.sendContactForm=function(newMessage){
  return $.ajax({
      url:"http://localhost:8090/message",
      method:'POST',
      data:{
          "name":newMessage.name,
          "message": newMessage.message,
          "email" :newMessage.email,
          "number":newMessage.number,
      },
    })
  }
Message.prototype.getMessages=function(token){
  return $.ajax({
    url:"http://localhost:8090/secured/message/all",
    headers: {
            'X-Auth-Token': token
       },
    method:'GET'
  })
}
Message.prototype.deleteMessage=function(id,token){
  return $.ajax({
    url:"http://localhost:8090/secured/message/delete/"+id,
    headers: {
            'X-Auth-Token': token
       },
    method:'DELETE'
  })
}
