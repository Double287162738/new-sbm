var ifUploadPerPic = false;
var oldData = {};
$(function () {
    //闲置详情框的高度
    $("#editModal").css("height", window.screen.height);
    //图片的高度
    $("#edit-pic-part").css("height", $("#editModal").height() * 0.5);
    //价格、用户等其他信息部分
    $("#edit-other-part").css("height", $("#editModal").height() * 0.5);

    $("#editModal").scroll(function () {
        var top = 100 + $("#editModal").scrollTop();
        $("#edit-saveDiv").css("top", top + "px");
    });

})

function saveEdit() {
    window.frames["edit-obj1"].document.getElementById("saveOtherPicEdit").click();
}

function closeEdit() {
    $('#editModal').modal('hide');
}


//打开编辑窗口
function editYifabu(goodsId) {
    var url = baseUrl + "/my-sbm/sousou/sougoodsDetail.do";
    var data = {"goodsId": goodsId};
    var editPicAmount = 0;
    $.ajax({
        url: url,
        type: "post",
        data: data,
        dataType: 'json',
        success: function (data) {
            if (data.result == null) {
                alert(data.errorMessages);
            } else {
                if (data.result.userName == null) {
                    data.result.userName = "一位不愿透漏姓名的唐马儒先生";
                }
                if (data.result.goodsName == null) {
                    data.result.goodsName = "神秘物品";
                }
                if (data.result.goodsQq == null) {
                    data.result.goodsQq = "太粗心了，竟然没有留下QQ";
                }
                if (data.result.goodsWx == null) {
                    data.result.goodsWx = "太粗心了，竟然没有留下QQ";
                }
                if (data.result.goodsArea == null) {
                    data.result.goodsArea = "Z";
                }
                if (data.result.goodsType == null) {
                    data.result.goodsType = "Z";
                }
                var goodsPic1 = data.result.goodsPic1;
                document.getElementById("mainPic").src = baseUrl + "/file/GoodsPic/" + goodsPic1.substring(0, 4) + "/" + goodsPic1.substring(4, 6) + "/"
                    + goodsPic1.substring(6, 8) + "/" + goodsPic1;
                document.getElementById("edit-owner").value = data.result.userName;
                document.getElementById("edit-goods").value = data.result.goodsName;
                document.getElementById("edit-goodsName-input").value = data.result.goodsName;
                document.getElementById("edit-goodsWords-input").value = data.result.goodsWords;
                $("#edit-goodsArea-select").val(data.result.goodsArea);
                document.getElementById("edit-goodsType-select").value = data.result.goodsType;
                document.getElementById("edit-jiage-input").value = data.result.goodsPrice;
                document.getElementById("edit-qq-input").value = data.result.goodsQq;
                document.getElementById("edit-wx-input").value = data.result.goodsWx;
                document.getElementById("edit-ownerSay").value = data.result.goodsOther;
                oldData = {
                    "goodsName": data.result.userName,
                    "goodsWords": data.result.goodsWords,
                    "goodsArea": data.result.goodsArea,
                    "goodsType": data.result.goodsType,
                    "goodsPrice": data.result.goodsPrice,
                    "goodsQq": data.result.goodsQq,
                    "goodsWx": data.result.goodsWx,
                    "goodsOther": data.result.goodsOther
                };
                setCookie("goodsId", data.result.goodsId);
                if (data.result.goodsPic2 != null && data.result.goodsPic2 != '') {
                    $.cookie("edit-goodsPic2", data.result.goodsPic2);
                    editPicAmount++;
                } else {
                    $.cookie("edit-goodsPic2", "null");
                }
                if (data.result.goodsPic3 != null && data.result.goodsPic3 != '') {
                    $.cookie("edit-goodsPic3", data.result.goodsPic3);
                    editPicAmount++;
                } else {
                    $.cookie("edit-goodsPic3", "null");
                }
                if (data.result.goodsPic4 != null && data.result.goodsPic4 != '') {
                    $.cookie("edit-goodsPic4", data.result.goodsPic4);
                    editPicAmount++;
                } else {
                    $.cookie("edit-goodsPic4", "null");
                }
                if (data.result.goodsPic5 != null && data.result.goodsPic5 != '') {
                    $.cookie("edit-goodsPic5", data.result.goodsPic5);
                    editPicAmount++;
                } else {
                    $.cookie("edit-goodsPic5", "null");
                }
                if (data.result.goodsPic6 != null && data.result.goodsPic6 != '') {
                    $.cookie("edit-goodsPic6", data.result.goodsPic6);
                    editPicAmount++;
                } else {
                    $.cookie("edit-goodsPic6", "null");
                }
                if (data.result.goodsPic7 != null && data.result.goodsPic7 != '') {
                    $.cookie("edit-goodsPic7", data.result.goodsPic7);
                    editPicAmount++;
                } else {
                    $.cookie("edit-goodsPic7", "null");
                }
                if (data.result.goodsPic8 != null && data.result.goodsPic8 != '') {
                    $.cookie("edit-goodsPic8", data.result.goodsPic8);
                    editPicAmount++;
                } else {
                    $.cookie("edit-goodsPic8", "null");
                }
                if (data.result.goodsPic9 != null && data.result.goodsPic9 != '') {
                    $.cookie("edit-goodsPic9", data.result.goodsPic9);
                    editPicAmount++;
                } else {
                    $.cookie("edit-goodsPic9", "null");
                }
                $.cookie("editPicAmount", editPicAmount);
            }
        },
        error: function (e) {
            alert("查看失败，请重试或联系管理员");
            return;
        }
    });
    $('#editModal').modal('show');
}


//选择图片显示
$(function () {
    $("#select-main-pic").change(function () {
        var ifFileYes = true;
        var $file = $(this);
        var fileObj = $file[0];
        var windowURL = window.URL || window.webkitURL;
        var dataURL;
        var $img = $("#mainPic");
        var nowImg = $("#select-main-pic");
        nowFile = document.getElementById("select-main-pic").files[0];
        if (nowFile != null && !/image\/\w+/.test(nowFile.type)) {
            ifFileYes = false;
        }
        if (fileObj && fileObj.files && fileObj.files[0] && /image\/\w+/.test(nowFile.type)) {
            ifUploadPerPic = true;
            ifFileYes = true;
            dataURL = windowURL.createObjectURL(fileObj.files[0]);
            $img.attr('src', dataURL);
        } else {
            if (!ifFileYes) {
                alert("请选择图片文件,不要选择非图片文件。");
            }
        }
    });


    $(".info").click(function () {
        //document.getElementById("select-main-pic").click();
        window.frames["edit-obj1"].document.getElementById("select-other-pic1").click();
    })
});
