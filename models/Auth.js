
function Auth(){
 this.token="";
}
Auth.prototype.login=function(username, password){
 var root = 'http://localhost:8090';
 console.log(username);
 console.log(password);
 return $.post(root+"/auth/login",{
    username: username,
	   password: password
   },
   function(response) {
    var xmlhttp = new XMLHttpRequest();
    console.log(response);
    xmlhttp.open("post", "Login", true);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 401 && xmlhttp.status == 200) {
            document.cookie = "token=" + response.token+';expires=Mon, 10 Jun 2019 12:00:00 UTC;domain=.'+ document.domain + ';path=/';
        }
    };
    //console.log(response);
   });

  };
Auth.prototype.logOut=function(token){
  var root = 'http://localhost:8090';
  return $.ajax({
   url:root+"/auth/logout",
   method: 'GET',
   headers:{'X-Auth-Token':token},
 });
};

Auth.prototype.register=function(userName,password){
 var root = 'http://localhost:8090';
 return $.post(root+"/auth/register",{
    username: userName,
	   password: password
   });
  };
