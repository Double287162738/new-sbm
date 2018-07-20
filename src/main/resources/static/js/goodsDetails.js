$(function () {
    var appendHTML = '';
    if ($.cookie("goodsPic2") == "null") {
        $("#item2").remove();
    } else {
        document.getElementById("goodsPic2").src = makePicURL($.cookie("goodsPic2"));
    }
    if ($.cookie("goodsPic3") == "null") {
        $("#item3").remove();
    } else {
        document.getElementById("goodsPic3").src = makePicURL($.cookie("goodsPic3"));
    }
    if ($.cookie("goodsPic4") == "null") {
        $("#item4").remove();
    } else {
        document.getElementById("goodsPic4").src = makePicURL($.cookie("goodsPic4"));
    }
    if ($.cookie("goodsPic5") == "null") {
        $("#item5").remove();
    } else {
        document.getElementById("goodsPic5").src = makePicURL($.cookie("goodsPic5"));
    }
})

function makePicURL(picName) {
    var picURL = goodsPicURL + picName.substring(0, 4) + "/" + picName.substring(4, 6) + "/"
        + picName.substring(6, 8) + "/" + picName;
    return picURL;
}