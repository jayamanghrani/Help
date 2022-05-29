var modals = document.getElementsByClassName('modal');
// Get the button that opens the modal
var btns = document.getElementsByClassName("openmodal");
var spans=document.getElementsByClassName("close");
var i;
console.log(event);
for(i=0;i<btns.length;i++){
   btns[i].onclick = function() {
      modals[i].style.display = "block";
   }
}
for(i=0;i<spans.length;i++){
    spans[i].onclick = function() {
       modals[i].style.display = "none";
    }
 }
 
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}