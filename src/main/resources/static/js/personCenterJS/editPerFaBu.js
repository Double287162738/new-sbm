var ifUploadPerPic = false;
var oldData = {};
var editGoodsId;
var picAmount = 0;
var changeBtnId;
var deletePicId = new Array();
var changePicId = new Array();
var SmType2Html_DZ='<option value="SJ">手机</option>'
                +'<option value="DN">电脑</option>'
                +'<option value="PB">平板 </option>'
                +'<option value="OTHER">其他 </option>';
var SmType2Html_SJ='<option value="ZYK">专业课</option>'+
                    '<option value="XXK">选修课</option>'+
                    '<option value="KYZL">考研资料 </option>'+
                    '<option value="SJ-OTHER">其他 </option>';
var SmType2Html_RC='<option value="RCYP">日常用品</option>';
var SmType2Html_JZ='<option value="JZ">兼职</option>';
var SmType2Html_OTHER='<option value="OTHER">其他</option>';
$(function () {
    //闲置详情框的高度
    $("#editModal").css("height", window.screen.height);
    //图片的高度
    $("#edit-pic-part").css("height", $("#editModal").height() * 0.5);
    //价格、用户等其他信息部分
    $("#edit-other-part").css("height", $("#editModal").height() * 0.5);

    //设置保存修改按钮的位置
    $("#editModal").scroll(function () {
        var top = 100 + $("#editModal").scrollTop();
        $("#edit-saveDiv").css("top", top + "px");
    });

    //两种type联动
    $("#edit-goodsType1-select").on("change",function () {
        var type1 = $("#edit-goodsType1-select").val();
        if(type1=="DZ"){
            document.getElementById("edit-goodsType2-select").innerHTML = SmType2Html_DZ;
        }else if(type1=="SJ"){
            document.getElementById("edit-goodsType2-select").innerHTML = SmType2Html_SJ;
        }else if(type1=="RC"){
            document.getElementById("edit-goodsType2-select").innerHTML = SmType2Html_RC;
        }else if(type1=="JZ"){
            document.getElementById("edit-goodsType2-select").innerHTML = SmType2Html_JZ;
        }else{
            document.getElementById("edit-goodsType2-select").innerHTML = SmType2Html_OTHER;
        }
    });

})


//打开编辑窗口
function editYifabu(goodsId) {
    document.getElementById("editGoodsDetailPic").innerHTML = "";
    document.getElementById("mainPic").src="../img/perCenter/loading.gif";
    picAmount=0;
    deletePicId = [];
    changePicId = [];
    var url = baseUrl + "/sousou/sougoodsDetail.do";
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
                document.getElementById("edit-goodsType1-select").value = data.result.goodsType;

                document.getElementById("edit-goodsType2-select").innerHTML = '';
                var append = '';
                if(data.result.goodsType=="DZ"){
                    append = '<option value="">请选择类型</option>'+
                        '<option value="SJ">手机</option>'+
                        '<option value="DN">电脑</option>'+
                        '<option value="PB">平板 </option>'+
                        '<option value="DZ-OTHER">其他 </option>';
                }else if(data.result.goodsType=="SJ"){
                    append = '<option value="">请选择类型</option>'+
                        '<option value="ZYK">专业课</option>'+
                        '<option value="XXK">选修课</option>'+
                        '<option value="KYZL">考研资料 </option>'+
                        '<option value="SJ-OTHER">其他 </option>';
                }else if(data.result.goodsType=="RC"){
                    append = '<option value="RCYP">日常用品</option>';
                }else if(data.result.goodsType=="JZ"){
                    append = '<option value="">请选择类型</option>'+
                        '<option value="JZ">兼职</option>';
                }
                $("#edit-goodsType2-select").append(append);
                document.getElementById("edit-goodsType2-select").value = data.result.goodsSpecificType;
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
                var editOtherPicDetail = "";
                if (data.result.goodsPic2 != null && data.result.goodsPic2 != '') {
                    editOtherPicDetail=editOtherPicDetail+'<li class="item falldown" id="item2">'
                    +'<figure>'
                        +'<div class="hovereffect">'
                        +'<input style="display: none !important;" onchange="selectOtherPic('+2+')" id="select-other-pic2" type="file" multiple name="uploadFiles2" accept="image/*"/>'
                        +'<div class="view"><img id="goodsPic2" src='+getPicUrl(data.result.goodsPic2)+'/></div>'
                        +'<div class="overlay">'
                        +'<a class="info" id="changePic2" onclick="changePic('+2+')">更换</a>'
                        +'<a class="info" id="deletePic2" onclick="deletePic('+2+')">删除</a>'
                        +'</div>'
                        +'</div>'
                     +'</figure>'
                     +'</li>';
                    picAmount++;
                }
                if (data.result.goodsPic3 != null && data.result.goodsPic3 != '') {
                    editOtherPicDetail=editOtherPicDetail+'<li class="item falldown" id="item3">'
                        +'<figure>'
                        +'<div class="hovereffect">'
                        +'<input style="display: none !important;" onchange="selectOtherPic('+3+')" id="select-other-pic3" type="file" multiple name="uploadFiles3" accept="image/*"/>'
                        +'<div class="view"><img id="goodsPic3" src='+getPicUrl(data.result.goodsPic3)+'/></div>'
                        +'<div class="overlay">'
                        +'<a class="info" id="changePic3" onclick="changePic('+3+')">更换</a>'
                        +'<a class="info" id="deletePic3" onclick="deletePic('+3+')">删除</a>'
                        +'</div>'
                        +'</div>'
                        +'</figure>'
                        +'</li>';
                    picAmount++;
                }
                if (data.result.goodsPic4 != null && data.result.goodsPic4 != '') {
                    editOtherPicDetail=editOtherPicDetail+'<li class="item falldown" id="item4">'
                        +'<figure>'
                        +'<div class="hovereffect">'
                        +'<input style="display: none !important;" onchange="selectOtherPic('+4+')" id="select-other-pic4" type="file" multiple name="uploadFiles4" accept="image/*"/>'
                        +'<div class="view"><img id="goodsPic4" src='+getPicUrl(data.result.goodsPic4)+'/></div>'
                        +'<div class="overlay">'
                        +'<a class="info" id="changePic4" onclick="changePic('+4+')">更换</a>'
                        +'<a class="info" id="deletePic4" onclick="deletePic('+4+')">删除</a>'
                        +'</div>'
                        +'</div>'
                        +'</figure>'
                        +'</li>';
                    picAmount++;
                }
                if (data.result.goodsPic5 != null && data.result.goodsPic5 != '') {
                    editOtherPicDetail=editOtherPicDetail+'<li class="item falldown" id="item5">'
                        +'<figure>'
                        +'<div class="hovereffect">'
                        +'<input style="display: none !important;" onchange="selectOtherPic('+5+')" id="select-other-pic5" type="file" multiple name="uploadFiles5" accept="image/*"/>'
                        +'<div class="view"><img id="goodsPic5" src='+getPicUrl(data.result.goodsPic5)+'/></div>'
                        +'<div class="overlay">'
                        +'<a class="info" id="changePic5" onclick="changePic('+5+')">更换</a>'
                        +'<a class="info" id="deletePic5" onclick="deletePic('+5+')">删除</a>'
                        +'</div>'
                        +'</div>'
                        +'</figure>'
                        +'</li>';
                    picAmount++;
                }
                document.getElementById("editGoodsDetailPic").innerHTML = editOtherPicDetail;
            }
        },
        error: function (e) {
            openAlert("查看失败，请重试或联系管理员");
            return;
        }
    });
    $('#editModal').modal('show');
}

//组装图片路径
function getPicUrl(picName) {
    return goodsPicURL + picName.substring(0, 4) + "/" + picName.substring(4, 6) + "/"
        + picName.substring(6, 8) + "/" + picName;
}

//更换图片方法
function changePic(id) {
    changeBtnId = id;
    document.getElementById("select-other-pic" + changeBtnId).click();
}

//图片
function selectOtherPic(nowClickOtherPicNum) {
    var ifFileYes = true;
    var $file = $("#select-other-pic" + nowClickOtherPicNum);
    var fileObj = $file[0];
    var windowURL = window.URL || window.webkitURL;
    var dataURL;
    var $img;
    if (nowClickOtherPicNum == '1') {
        $img = $("#mainPic");
    } else {
        $img = $("#goodsPic" + nowClickOtherPicNum);
    }
    var nowImg = $("#select-other-pic" + nowClickOtherPicNum);
    if ($.inArray(nowClickOtherPicNum,changePicId)) {
        changePicId.push(nowClickOtherPicNum);
    }
    var nowFile = document.getElementById("select-other-pic" + nowClickOtherPicNum).files[0];
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
            openAlert("请选择图片文件,不要选择非图片文件。");
        }
    }
}

//删除图片
function deletePic(otherPicId) {
    if (--picAmount < 1) {
        openAlert("不能再删除了！");
        return;
    }
    //把删除的图片【替换记录】去掉
    var newChangePicId = [];
    for(var i=0;i<changePicId.length;i++){
        if(changePicId[i]!=otherPicId){
            newChangePicId.push(changePicId[i]);
        }
    }
    changePicId = newChangePicId;
    deletePicId.push(otherPicId);
    $("#item" + otherPicId).remove();
}

//保存修改
$(function () {
    $("#edit-saveBtn").click(function () {
        var formatChangePicId = new Array();
        for(var i=0;i<changePicId.length;i++){
            formatChangePicId.push(parseInt(changePicId[i]));
        }
        var uploadFiles = new FormData($("#otherPicForm")[0]);
        var newUploadFiles = new FormData();
        var flag = true;
        for(var i=1;i<=5;i++){
            if($.inArray(i,formatChangePicId)>=0){
                if(flag){
                    newUploadFiles.set("uploadFiles",uploadFiles.get("uploadFiles"+i));
                    flag = false;
                }else{
                    newUploadFiles.append("uploadFiles",uploadFiles.get("uploadFiles"+i));
                }
            }
        }
        var editGoodsName = document.getElementById("edit-goodsName-input").value;
        var editGoodsWords =document.getElementById("edit-goodsWords-input").value;
        var editGoodsArea = document.getElementById("edit-goodsArea-select").value;
        var editGoodsType = document.getElementById("edit-goodsType1-select").value;
        var editGoodsSpecificType = document.getElementById("edit-goodsType2-select").value;
        var editGoodsPrice = document.getElementById("edit-jiage-input").value;
        var editGoodsQq = document.getElementById("edit-qq-input").value;
        var editGoodsWx = document.getElementById("edit-wx-input").value;
        var goodsOther = document.getElementById("edit-ownerSay").value;
        var goods = {
            "goodsName": editGoodsName, "goodsWords": editGoodsWords, "goodsArea": editGoodsArea,
            "goodsType": editGoodsType,"goodsSpecificType":editGoodsSpecificType, "goodsPrice": editGoodsPrice, "goodsQq": editGoodsQq,
            "goodsWx": editGoodsWx,"goodsId":editGoodsId,"goodsOther":goodsOther
        };
        newUploadFiles.append("goods",JSON.stringify(goods));
        newUploadFiles.append("deletePic",deletePicId);
        newUploadFiles.append("changePic",changePicId);
        $.ajax({
            url: baseUrl + "/personCenter/editOtherPic.do",
            type: "post",
            data: newUploadFiles,
            processData: false,
            contentType: false,
            async: false,
            // 服务器响应的数据类型
            dataType: "json",
            // 请求成功时执行的回调函数
            success: function (data) {
                // 图片显示地址
                if (data.result == "success") {
                    openAlert("保存成功");
                    parent.closeEdit();
                    parent.yifabu("ALL");
                } else {
                    openAlert(data.errorMessage);
                }
            }
        });
    });
})

//关闭修改窗口
function closeEdit() {
    $('#editModal').modal('hide');
}

















