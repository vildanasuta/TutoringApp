function toggle () {
    var x=document.getElementById("sidebar");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
function modulesShow(){
    var x=document.getElementById("modulesSubmenu");
    x.classList.toggle("collapse");
}
function wikiShow(){
    var x = document.getElementById("wikiSubmenu");
    x.classList.toggle("collapse");
}





