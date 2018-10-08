document.addEventListener("DOMContentLoaded",function(event){

//  var contactModal=document.getElementById("contactModalLink");
//  contactModal.addEventListener("click"
  displayFooter();
  displayModal();
// displayLoginModal();
var token=localStorage.getItem('accessToken');
if(token!=null ){
  var logOutLink=document.getElementById('logOutLink');
  var logInLink=document.getElementById('logInLink');
  var messagesList=document.getElementById("adminMess");
  logOutLink.style.display="block";
  logInLink.style.display="none";
  messagesList.style.display="block";
}else{
  var logOutLink=document.getElementById('logOutLink');
  var logInLink=document.getElementById('logInLink');
  var messagesList=document.getElementById("adminMess");
  logInLink.style.display="block";
  logOutLink.style.display="none";
  messagesList.style.display="none";
}
  $('.input').blur(function() {
    var $this = $(this);
    if ($this.val())
      $this.addClass('used');
    else
      $this.removeClass('used');
  });

$('#tab1').on('click' , function(){
    $('#tab1').addClass('login-shadow');
   $('#tab2').removeClass('signup-shadow');
});

$('#tab2').on('click' , function(){
    $('#tab2').addClass('signup-shadow');
   $('#tab1').removeClass('login-shadow');
});

displayNewArrival();


var contactFormButton=document.getElementById("sendContactForm");
contactFormButton.addEventListener("click",contactFormValidation);
});
function displayNewArrival(){
  var newArrivalContainer=document.getElementById("newArrivalContainer");
  var contentContainer=document.getElementById("contentContainer");
  var url="http://localhost:8090/products";
  var productEl="";
  getDatafromAPI(url)
  .then(function(response){
    for(let i=0;i<6;i++){
      productEl=displayElement(response[i]);
      newArrivalContainer.innerHTML+=productEl;
    }

  });
}
function getDatafromAPI(url){
    var resp=$.get(url);
    return resp;
}
function displayElement(content){
var template=
    '<div class="col-md-4 col-sm-6 portfolio-item d-inline-block" id="arrivalId">'  +
      ' <a class="portfolio-link"  id="modalElement" href="pages/productDetail.html?id='+content.id+'">'+
      ' <div class="portfolio-hover">' +
        '</div>' +
        '<img id="imageId" class="img-fluid"  src='+content.imgUrl.substring(3)+' alt="">'  +
      '</a>'  +
      '<div class="portfolio-caption">'  +
      ' <h4>'+content.title+'</h4>'+
      '</div>'+
    '</div>'+
  ' </div>' ;
  return template;
}

function arrivalModal(id){
  var product=new Product();
  product.getProductDetails(id)
  .then(displayArrivalModal);

function displayArrivalModal(){
  var newArrivalModal=document.getElementById("arrivalModalBody");
  let element=arrivalModalTemplate(product);
  newArrivalModal.innerHTML=element;
}

function arrivalModalTemplate(content){
console.log(content);
  var template=
  '<div class="modal-body">'+
    '<h2 class="text-uppercase"><a>'+content.title+'</a></h2>'+
    '<img class=" img-fluid d-block mx-auto"   height= "400px" src="'+content.image.substring(3)+'" alt="">' +
    '<p>'+content.description+'</p>'+
    '<button class="btn" data-dismiss="modal" type="button">'+
    '<i class="fa fa-times"></i>' +
     '&#206;nchide</button>'+
    '</div>' ;
    return template;
}
}
function displayModal(){
  var modal=document.getElementById("contactModal");
  modal.innerHTML+=
                  '<div class="modal-dialog ">'+
                  '<div class="modal-content">'+
                  '<div class="modal-header">'+
                  '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>'+
                  '<h3 id="myModalLabel">We would Love to Hear From You</h3>'+
                  '</div>'+
                  '<div class="modal-body">'+
                  '<form class="form-horizontal col-sm-12">'+
                  ' <div class="form-group"><label>Name</label><input class="form-control required" id="contactFormName" placeholder="Your name" data-placement="top" data-trigger="manual" data-content="Must be at least 3 characters long, and must only contain letters." type="text"></div>'+
                  '<div class="form-group"><label>Message</label><textarea class="form-control" id="contactFormMessage" placeholder="Your message here.." data-placement="top" data-trigger="manual"></textarea></div>'+
                  '<div class="form-group"><label>E-Mail</label><input class="form-control email" id="contactFormMail" placeholder="email@you.com (so that we can contact you)" data-placement="top" data-trigger="manual" data-content="Must be a valid e-mail address (user@gmail.com)" type="text"></div>'+
                  '<div class="form-group"><label>Phone</label><input class="form-control phone" id="contactFormNumber" placeholder="0123456789" data-placement="top" data-trigger="manual" data-content="Must be a valid phone number (999-999-9999)" type="text"></div>'+
                  '<div class="form-group"><button type="submit" class="btn btn-success pull-right" id="sendContactForm">Send It!</button> <p class="help-block pull-left text-danger hide" id="form-error">  </p></div>'+
                  '</form>'+
                  '</div>'+
                  '<div class="modal-footer">'+
                  '<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>'+
                  '</div>'+
                  '</div>';

}
function contactFormValidation(event){
  event.preventDefault();
  var obj=new Object();
  var name=document.getElementById("contactFormName");
  var message=document.getElementById("contactFormMessage");
  var email=document.getElementById("contactFormMail");
  var number=document.getElementById("contactFormNumber");
  if(validName(name)&&validMessage(message)&&validEMail(email)&&validNumber(number)){
    var messageContent=new Message();
    obj.name=name.value;
    obj.message=message.value;
    obj.email=email.value;
    obj.number=number.value;
    messageContent.sendContactForm(obj);
    setTimeout(function(){ location.reload(); },1000);
  }
}
function validName(name){
  if(name.value==""){
    name.style.borderColor="red";
    return false;
  }else{
      name.style.borderColor="";
      return true;
    }
}
function validMessage(message){
  if(message.value==""){
    message.style.borderColor="red";
    return false;
  }else{
    message.style.borderColor="";
      return true;
  }
}
function validEMail(mail){
  if(mail.value==""){
    mail.style.borderColor="red";
    return false;
  }else{
    mail.style.borderColor="";
    return true;
  }
}
function validNumber(number){
  if(number.value==""|| isNaN(number.value)|| number.value.length!=10){
    number.style.borderColor="red";
    return false;
  }else{
    number.style.borderColor="";
      return true;
  }
}
function displayFooter(){
  var footer=document.getElementById("myFooter");
  footer.innerHTML='<div id="footer-bar">'+
                      '  <div class="container">'+
                      '   <div class="row py-4 d-flex align-items-center">'+
                      '     <div class="col-md-6 col-lg-5 text-center text-md-left mb-4 mb-md-0">'+
                      '       <h6 class="mb-0">Get connected with us on social networks!</h6>'+
                      '      </div>'+
                      '     <div class="col-md-6 col-lg-7 text-center text-md-right">'+
                      '       <a class="fb-ic">'+
                      '         <i class="fa fa-facebook white-text mr-4"> </i>'+
                      '       </a>'+
                      '       <a class="tw-ic">'+
                      '         <i class="fa fa-twitter white-text mr-4"> </i>'+
                      '        </a>'+
                      '       <a class="gplus-ic">'+
                      '         <i class="fa fa-google-plus white-text mr-4"> </i>'+
                      '       </a>'+
                      '       <a class="li-ic">'+
                      '         <i class="fa fa-linkedin white-text mr-4"> </i>'+
                      '       </a>'+
                              '<a class="ins-ic">'+
                      '         <i class="fa fa-instagram white-text"> </i>'+
                      '        </a>'+
                      '     </div>'+
                      '   </div>'+
                      ' </div>'+
                      '</div>'+
                      '<div class="container text-center text-md-left mt-5">'+
                      ' <div class="row mt-3">'+
                      '   <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">'+
                      '     <h6 class="text-uppercase font-weight-bold">Company name</h6>'+
                      '     <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">'+
                      '     <p>Here you can use rows and columns here to organize your footer content. Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>'+
                      '   </div>'+
                      '   <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">'+
                      '     <h6 class="text-uppercase font-weight-bold">Client Support</h6>'+
                      '     <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">'+
                      '     <p>'+
                      '       <a href="#!">Suport 24/7p</a>'+
                      '     </p>'+
                      '     <p>'+
                      '       <a href="#!">Returns & Replacements</a>'+
                      '     </p>'+
                      '     <p>'+
                      '       <a href="#!">Policies</a>'+
                      '     </p>'+
                      '     <p>'+
                      '       <a href="#!">Contact</a>'+
                      '     </p>'+
                      '   </div>'+
                      '   <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">'+
                      '     <h6 class="text-uppercase font-weight-bold">Useful links</h6>'+
                      '     <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">'+
                      '     <p>'+
                      '       <a href="#!">Your Account</a>'+
                      '     </p>'+
                      '     <p>'+
                      '       <a href="#!">Your Orders</a>'+
                      '     </p>'+
                      '     <p>'+
                      '       <a href="#!">Shipping Rates</a>'+
                      '     </p>'+
                      '     <p>'+
                      '       <a href="#!">Help</a>'+
                      '     </p>'+
                      '   </div>'+
                      '   <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">'+
                      '     <h6 class="text-uppercase font-weight-bold">Contact</h6>'+
                      '     <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">'+
                      '     <p>'+
                      '       <i class="fa fa-home mr-3"></i> New York, NY 10012, US</p>'+
                      '     <p>'+
                      '       <i class="fa fa-envelope mr-3"></i> info@example.com</p>'+
                      '     <p>'+
                      '       <i class="fa fa-phone mr-3"></i> + 01 234 567 88</p>'+
                      '     <p>'+
                      '       <i class="fa fa-print mr-3"></i> + 01 234 567 89</p>'+
                      '    </div>'+
                      ' </div>'+
                      '</div>'+
                      '<div class="footer-copyright text-center py-3">© 2018 Copyright:'+
                      ' <a href="#">MyStore </a>'+
                      '</div>';
                    }
