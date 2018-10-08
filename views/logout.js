window.addEventListener("load",function (){
 var auth=  new Auth();
 var logoutButton=document.getElementById("logOutLink");
 var form= document.getElementById("registerForm");
 var okRegister=document.getElementById("registerSubmit");
 console.log(okRegister);
 var message=document.getElementById("messageContainer");
 var welcomeMessage=document.getElementById("welcome");
 var loginButton=document.getElementById("loginButton");
 var registerButton=document.getElementById("registerButton");
 console.log(okRegister);
if(okRegister!=null){
  okRegister.addEventListener("click", function(){
   var userName=document.getElementById("name").value;
   console.log(userName);
   var password=document.getElementById("newpwd").value;
   console.log(password);
   var email=document.getElementById("newemail").value;
    if (validPass(password)&&validMail(email)) {
     auth.register(userName,password)
     .then(function(response){
      const token=response.accessToken;
      auth.token=token;
      document.cookie="accessToken="+token;
      console.log(response.accessToken);
      logoutButton.style.display="block";
      welcomeMessage.style.display="block";
      welcomeMessage.innerHTML="Welcome "+userName+"!";
      loginButton.style.display="none";
      registerButton.style.display="none";
     })
    .catch(function(e) {
     console.log(e) ;
     message.innerHTML=" Username already existing. Please login";
     });
    }else if(validPass(password)==false){
     console.log("The password is not correct");
     message.innerHTML=" The password is not correct";
    }
    else if(validMail(email)==false){
     console.log("The email is not correct");
     message.innerHTML=" The mail is not correct";
    }
 });
}

logoutButton.addEventListener("click",function (){
  console.log(auth.token);
  auth.logOut(localStorage.getItem('accessToken'))
  .then(function(response){
    console.log(response);
    //loginButton.style.display="block";
    //registerButton.style.display="block";
  //  logoutButton.style.display="none";
    //welcomeMessage.style.display="none";
    document.cookie="accessToken=";
    auth.token="";
    localStorage.removeItem("accessToken");
    localStorage.removeItem("role");
    setTimeout(function(){
      location.reload();
    },2000);
   })
  .catch(function(e) {
   console.log(e);})
 });
});

function validPass(myInput){
 var valid=false;
 var lowerCaseLetters = /[a-z]/g;
 var numbers = /[0-9]/g;
 var upperCaseLetters = /[A-Z]/g;
 if(myInput.match(lowerCaseLetters)&&myInput.match(upperCaseLetters)&&myInput.match(numbers)&&myInput.length >= 8){
  valid=true;
 }else{
  valid=false;
 }
 return valid;
}

function validMail(myInput){
   var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
   return re.test(myInput.toLowerCase());
 }
 function getCookie(name) {
       var value = "; " + document.cookie;
       var parts = value.split("; " + name + "=");
       if (parts.length == 2) {
           return parts.pop().split(";").shift();
       }
}
