var submit=document.getElementById("confirmsignin");
var register=document.getElementById("confirmsignup");
var auth =new Auth();
submit.addEventListener("click", loginEvent);
register.addEventListener("click",registerEvent);

  function loginEvent(event){
      event.preventDefault();
      let username = document.getElementById("userName").value;
      let password = document.getElementById("passwordLog").value;
      auth.login(username,password)
      .then(function(response){
        console.log(response);
        let loginMessage = document.getElementById("loginMessage");
        let token=response.jwtToken;
        let role=response.role;
        auth.token=token;
        document.cookie="accessToken"+token+';expires=Mon, 10 Jun 2019 12:00:00 UTC;'+';path=/';
        localStorage.setItem("accessToken", token);
        localStorage.setItem("role", role);
        console.log(response.token);
        loginMessage.style.display="block";
        loginMessage.style.color="red";
        loginMessage.innerHTML="Logged in";
        setTimeout(function(){
          location.reload();
        },2000);
       })
       .catch(function(error) {
          console.log(error);
          let loginMessage = document.getElementById("loginMessage");
          loginMessage.style.display="block";
          loginMessage.style.color="red";
          loginMessage.innerHTML="Username or password not correct";
          setTimeout(function(){
            location.reload();
          },2000);
        })
     }
  function registerEvent(event){
       event.preventDefault();
       let registerMessage = document.getElementById("registerMessage");
       let username=document.getElementById("userRegist").value;
       let password=document.getElementById("password").value;
       auth.register(username,password)
       .then(function(response){
         let token=response.token;
         auth.token=token;
         document.cookie="accessToken"+token;
         localStorage.setItem("accessToken", token);
         console.log(response.accessToken);
         registerMessage.style.display="block";
         registerMessage.style.color="red";
         registerMessage.innerHTML="Logged in";
         setTimeout(function(){
           location.reload();
         },2000);
        })
        .catch(function(error) {
          console.log(error);
          registerMessage.style.display="block";
          registerMessage.style.color="red";
          registerMessage.innerHTML=error.responseJSON.message;
        })
}
