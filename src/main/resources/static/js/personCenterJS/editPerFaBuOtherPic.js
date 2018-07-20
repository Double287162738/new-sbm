var changeBtnId;
var deletePicId = '';
var changePicId = '';

function changePic(id) {
    changeBtnId = id;
    document.getElementById("select-other-pic" + changeBtnId).click();
}

var goodsId = window.parent.getGoodsId();
var pics= window.parent.getGoodsPics();
var picAmount=window.parent.getPicAmount();

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
    if (pics.pic2 == "null") {
        $("#item2").remove();
    } else {
        document.getElementById("goodsPic2").src = makePicURL(pics.pic2);
    }
    if (pics.pic3 == "null") {
        $("#item3").remove();
    } else {
        document.getElementById("goodsPic3").src = makePicURL(pics.pic3);
    }
    if (pics.pic4 == "null") {
        $("#item4").remove();
    } else {
        document.getElementById("goodsPic4").src = makePicURL(pics.pic4);
    }
    if (pics.pic5 == "null") {
        $("#item5").remove();
    } else {
        document.getElementById("goodsPic5").src = makePicURL(pics.pic5);
    }
})

function makePicURL(picName) {
    var picURL = goodsPicURL + picName.substring(0, 4) + "/" + picName.substring(4, 6) + "/"
        + picName.substring(6, 8) + "/" + picName;
    return picURL;
}


function deletePic(otherPicId) {
    if (--picAmount < 1) {
        alert("不能再删除了！");
        return;
    }
    deletePicId = deletePicId + otherPicId + '-';
    $("#item" + otherPicId).remove();
}


$(function () {
    $("#saveOtherPicEdit").click(function () {
        var uploadFiles = new FormData($("#otherPicForm")[0]);
        var editGoodsName = window.parent.document.getElementById("edit-goodsName-input").value;
        var editGoodsWords = window.parent.document.getElementById("edit-goodsWords-input").value;
        var editGoodsArea = window.parent.document.getElementById("edit-goodsArea-select").value;
        var editGoodsType = window.parent.document.getElementById("edit-goodsType-select").value;
        var editGoodsPrice = window.parent.document.getElementById("edit-jiage-input").value;
        var editGoodsQq = window.parent.document.getElementById("edit-qq-input").value;
        var editGoodsWx = window.parent.document.getElementById("edit-wx-input").value;
        var goodsOther = window.parent.document.getElementById("edit-ownerSay").value;
        var goods = {
            "goodsName": editGoodsName, "goodsWords": editGoodsWords, "goodsArea": editGoodsArea,
            "goodsType": editGoodsType, "goodsPrice": editGoodsPrice, "goodsQq": editGoodsQq,
            "goodsWx": editGoodsWx,"goodsId":goodsId,"goodsOther":goodsOther
        };
        uploadFiles.append("goods",JSON.stringify(goods));
        uploadFiles.append("deletePic",deletePicId);
        uploadFiles.append("changePic",changePicId);
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
    });
})



















