var changeBtnId;
var baseUrl = "http://localhost:8080";
var editPicAmount = 0;
var deletePicId = '';
var changePicId = '';
var uploadFiles = [];

function changePic(id) {
    changeBtnId = id;
    document.getElementById("select-other-pic" + changeBtnId).click();
}

$(function () {
    setCookie("changePicId", "");
    setCookie("deletePicId", "");
})


function selectOtherPic(nowClickOtherPicNum) {
    var ifFileYes = true;
    var $file = $("#select-other-pic" + nowClickOtherPicNum);
    var fileObj = $file[0];
    var windowURL = window.URL || window.webkitURL;
    var dataURL;
    var $img;
    if (nowClickOtherPicNum == '1') {
        $img = window.parent.document.getElementById("mainPic");
    } else {
        $img = $("#goodsPic" + nowClickOtherPicNum);
    }
    var nowImg = $("#select-other-pic" + nowClickOtherPicNum);
    if (changePicId == '') {
        changePicId = nowClickOtherPicNum + '-';
    } else {
        if (changePicId.indexOf(nowClickOtherPicNum) == -1) {
            changePicId = changePicId + nowClickOtherPicNum + '-';
        }
    }
    var nowFile = document.getElementById("select-other-pic" + nowClickOtherPicNum).files[0];
    if (nowFile != null && !/image\/\w+/.test(nowFile.type)) {
        ifFileYes = false;
    }
    if (fileObj && fileObj.files && fileObj.files[0] && /image\/\w+/.test(nowFile.type)) {
        ifUploadPerPic = true;
        ifFileYes = true;
        dataURL = windowURL.createObjectURL(fileObj.files[0]);
        if (nowClickOtherPicNum == '1') {
            window.parent.document.getElementById("mainPic").src = dataURL;
        } else {
            $img.attr('src', dataURL);
        }

    } else {
        if (!ifFileYes) {
            alert("请选择图片文件,不要选择非图片文件。");
        }
    }
}


$(function () {
    editPicAmount = $.cookie("editPicAmount");
    var appendHTML = '';
    if ($.cookie("edit-goodsPic2") == "null") {
        $("#item2").remove();
    } else {
        document.getElementById("goodsPic2").src = makePicURL($.cookie("edit-goodsPic2"));
    }
    if ($.cookie("edit-goodsPic3") == "null") {
        $("#item3").remove();
    } else {
        document.getElementById("goodsPic3").src = makePicURL($.cookie("edit-goodsPic3"));
    }
    if ($.cookie("edit-goodsPic4") == "null") {
        $("#item4").remove();
    } else {
        document.getElementById("goodsPic4").src = makePicURL($.cookie("edit-goodsPic4"));
    }
    if ($.cookie("edit-goodsPic5") == "null") {
        $("#item5").remove();
    } else {
        document.getElementById("goodsPic5").src = makePicURL($.cookie("edit-goodsPic5"));
    }
    if ($.cookie("edit-goodsPic6") == "null") {
        $("#item6").remove();
    } else {
        document.getElementById("goodsPic6").src = makePicURL($.cookie("edit-goodsPic6"));
    }
    if ($.cookie("edit-goodsPic7") == "null") {
        $("#item7").remove();
    } else {
        document.getElementById("goodsPic7").src = makePicURL($.cookie("edit-goodsPic7"));
    }
    if ($.cookie("edit-goodsPic8") == "null") {
        $("#item8").remove();
    } else {
        document.getElementById("goodsPic8").src = makePicURL($.cookie("edit-goodsPic8"));
    }
    if ($.cookie("edit-goodsPic9") == "null") {
        $("#item9").remove();
    } else {
        document.getElementById("goodsPic9").src = makePicURL($.cookie("edit-goodsPic9"));
    }
})

function makePicURL(picName) {
    var picURL = baseUrl + "/file/GoodsPic/" + picName.substring(0, 4) + "/" + picName.substring(4, 6) + "/"
        + picName.substring(6, 8) + "/" + picName;
    return picURL;
}


function deletePic(otherPicId) {
    if (--editPicAmount < 1) {
        alert("不能再删除了！");
        return;
    }
    deletePicId = deletePicId + otherPicId + '-';
    setCookie("deletePicId", deletePicId);
    console.log(getCookie("deletePicId"));
    $("#item" + otherPicId).remove();
}


$(function () {
    $("#saveOtherPicEdit").click(function () {
        var uploadFiles = new FormData($("#otherPicForm")[0]);
        setCookie("changePicId", changePicId);
        var editGoodsName = window.parent.document.getElementById("edit-goodsName-input").value;
        var editGoodsWords = window.parent.document.getElementById("edit-goodsWords-input").value;
        var editGoodsArea = window.parent.document.getElementById("edit-goodsArea-select").value;
        var editGoodsType = window.parent.document.getElementById("edit-goodsType-select").value;
        var editGoodsPrice = window.parent.document.getElementById("edit-jiage-input").value;
        var editGoodsQq = window.parent.document.getElementById("edit-qq-input").value;
        var editGoodsWx = window.parent.document.getElementById("edit-wx-input").value;
        var goods = {
            "goodsName": editGoodsName, "goodsWords": editGoodsWords, "goodsArea": editGoodsArea,
            "goodsType": editGoodsType, "goodsPrice": editGoodsPrice, "goodsQq": editGoodsQq,
            "goodsWx": editGoodsWx
        };
        $.ajax({
            url: baseUrl + "/my-sbm/personCenter/editGoodsDetails.do",
            type: "post",
            data: goods,
            dataType: 'json',
            success: function (data) {
                if (data.result) {
                    $.ajax({
                        // 规定把请求发送到那个URL  
                        url: baseUrl + "/my-sbm/personCenter/editOtherPic.do",
                        // 请求方式  
                        type: "post",
                        data: uploadFiles,
                        processData: false,
                        contentType: false,
                        async: false,
                        // 服务器响应的数据类型  
                        dataType: "json",
                        // 请求成功时执行的回调函数  
                        success: function (data) {
                            // 图片显示地址  
                            if (data.result == "success") {
                                alert("保存成功");
                                parent.closeEdit();
                                parent.yifabu("ALL");
                            } else {
                                alert(data.errorMessage);
                            }
                        }
                    });
                } else {
                    alert(data.errorMessages);
                }
            },
            error: function (e) {
                alert("请重试或者联系管理员");
                return;
            }
        });

    });
})



















