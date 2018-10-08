document.addEventListener("DOMContentLoaded",function(event){
var token=localStorage.getItem('accessToken');
listAllMessage(token);
});

function listAllMessage(token){
  getMessagesFromApi(token)
  .then(displayMessages)
}

function getMessagesFromApi(token){
  var messages =new Message();
  return messages.getMessages(token);
}
function displayMessages(content){
  var messageContainer=document.getElementById("messageList");
  var template=document.getElementById("messageTemplate");
  for(var i=0;i<content.length;i++){
    displayTemplate(content[i]);
  }

  function displayTemplate(element){
    var templateClone=template.cloneNode(true);
    var messageId=templateClone.querySelector("#messageId");
    var messageSender=templateClone.querySelector("#messageSender");
    var messageContent=templateClone.querySelector("#messageContent");
    var email=templateClone.querySelector("#senderMail");
    var number=templateClone.querySelector("#senderNumber");
    messageId.innerHTML=element.id;
    messageSender.innerHTML=element.name;
    messageContent.innerHTML=element.message;
    email.innerHTML=element.email;
    number.innerHTML=element.number;
    var deleteButton=templateClone.querySelector("#deleteButton");
    deleteButton.addEventListener("click",function(event){
      deleteMessage(event);
        })
    messageContainer.appendChild(templateClone);
}
function deleteMessage(token){
  var id=event.path[2].firstElementChild.innerHTML;
  var messages =new Message;
  var token=localStorage.getItem('accessToken');
  messages.deleteMessage(id,token)
  .then(
    setTimeout(function(){
      location.reload() }, 500)
  )
}
}
