var baseUrl = "http://localhost:8080";
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
    if ($.cookie("goodsPic6") == "null") {
        $("#item6").remove();
    } else {
        document.getElementById("goodsPic6").src = makePicURL($.cookie("goodsPic6"));
    }
    if ($.cookie("goodsPic7") == "null") {
        $("#item7").remove();
    } else {
        document.getElementById("goodsPic7").src = makePicURL($.cookie("goodsPic7"));
    }
    if ($.cookie("goodsPic8") == "null") {
        $("#item8").remove();
    } else {
        document.getElementById("goodsPic8").src = makePicURL($.cookie("goodsPic8"));
    }
    if ($.cookie("goodsPic9") == "null") {
        $("#item9").remove();
    } else {
        document.getElementById("goodsPic9").src = makePicURL($.cookie("goodsPic9"));
    }
})

function makePicURL(picName) {
    var picURL = baseUrl + "/file/GoodsPic/" + picName.substring(0, 4) + "/" + picName.substring(4, 6) + "/"
        + picName.substring(6, 8) + "/" + picName;
    return picURL;
}