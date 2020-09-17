/**
 * Gets the input of the hovered image along with it's text
 * @param textImage
 */
function upDate(textImage) {

    //The hovered image's src address is extracted from img tag
    var src = textImage.getAttribute( "src" );
    //The hovered image's alt text is extracted from img tag
    var alt = textImage.getAttribute( "alt" );
    //'A' variable holds the element of image which shows hovered ones
    A = document.getElementById('image');
    //The image element is set to the hovered source image
    A.style.backgroundImage = "url('" + src + "')";
    //The image element text is set to the hovered source image's alt text
    A.innerHTML = alt;
}

function unDo() {
    //The image element is set with empty url to not hold any image when not hovering
    A.style.backgroundImage = "url('')";
    //The image element is set with default message when not hovering
    A.innerHTML = "Hover to display here.";

}
