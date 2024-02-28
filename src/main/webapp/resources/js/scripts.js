function mostrarDiv(divId) {
    var divs = document.getElementsByTagName("div");
    for (var i = 0; i < divs.length; i++) {
        if (divs[i].id === divId) {
            divs[i].style.display = "block";
        } else {
            divs[i].style.display = "none";
        }
    }
}
