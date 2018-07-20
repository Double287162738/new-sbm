var ifUploadPerPic = false;
var oldData = {};
var editGoodsId;
var goodsPics={
    "pic1":"",
    "pic2":"",
    "pic3":"",
    "pic4":"",
    "pic5":""
}
var picAmount = 0;
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
                document.getElementById("mainPic").src = goodsPicURL + goodsPic1.substring(0, 4) + "/" + goodsPic1.substring(4, 6) + "/"
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
                editGoodsId=data.result.goodsId;
                if (data.result.goodsPic2 != null && data.result.goodsPic2 != '') {
                    goodsPics.pic2=data.result.goodsPic2;
                    picAmount++;
                } else {
                    goodsPics.pic2="null";
                }
                if (data.result.goodsPic3 != null && data.result.goodsPic3 != '') {
                    goodsPics.pic3=data.result.goodsPic3;
                    picAmount++;
                } else {
                    goodsPics.pic3="null";
                }
                if (data.result.goodsPic4 != null && data.result.goodsPic4 != '') {
                    goodsPics.pic4=data.result.goodsPic4;
                    picAmount++;
                } else {
                    goodsPics.pic4="null";
                }
                if (data.result.goodsPic5 != null && data.result.goodsPic5 != '') {
                    goodsPics.pic5=data.result.goodsPic5;
                    picAmount++;
                } else {
                    goodsPics.pic5="null";
                }
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


//返回商品ID
function getGoodsId(){
    return editGoodsId;
}
//返回
function getGoodsPics(){
    console.log(goodsPics);
    return goodsPics;
}
//返回图片数量
function getPicAmount(){
    return picAmount;
}