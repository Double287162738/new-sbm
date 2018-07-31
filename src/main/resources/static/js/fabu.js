
var xzInfo =
    {"goodsType":"1",
    "goodsArea":"2",
    "goodsSpecificType":"3",
    "goodsName":"",
    "goodsPrice":"",
    "goodsQq":"",
    "goodsWx":"",
    "goodsWords":"",
    "goodsOther":"",
};
$(function(){
    showFirstTitle();
    var index = 1;
    $("#tedian-pic").click(function () {
        if (index % 2 == 1) {
            $("#tedian-pic").css("transform", "scale(1.2)");
        } else {
            $("#tedian-pic").css("transform", "scale(1.0)");
        }
        index++;
        $("#teDianDiv").slideToggle();
    });
})
$(function () {
    $("#previous").click(function () {
        $("#xzTypeDiv").show();
        $("#xzDetailDiv").hide();
        $("#xzSmPicDiv").hide();
    });
    $("#next").click(function () {
        xzInfo.goodsArea=$("#fabu-area-select").val();
        xzInfo.goodsSpecificType=$("#fabu-type-select").val();
        xzInfo.goodsName = $("#fabuName").val();
        xzInfo.goodsPrice=$("#fabuPrice").val();
        xzInfo.goodsQq=$("#fabuQq").val();
        xzInfo.goodsWx=$("#fabuWx").val();
        xzInfo.goodsWords=$("#fabuWords").val();
        xzInfo.goodsOther=$("#xzSmOther").val();
        var msg = checkGoodsDetail();
        if(msg.trim()!=""){
            alert(msg);
            return;
        }
        $("#xzTypeDiv").hide();
        $("#xzDetailDiv").hide();
        $("#xzSmPicDiv").show();
        $("#firstTitle").text("请选择展示图片");
    });
    $("#onlyPrevious").click(function () {
        $("#xzTypeDiv").hide();
        $("#xzDetailDiv").show();
        $("#xzSmPicDiv").hide();
        $("#firstTitle").text("输入闲置其他信息");
    });
    $("#fabuSave").click(function () {
        $('#file-2').fileinput("upload");
    });
})

$(function(){
    //多图片上传
    $('#file-2').fileinput({//初始化上传文件框
        //removeFromPreviewOnError:true,//不显示不符合要求的文件格式
        showUpload: true,
        showRemove: true,
        uploadAsync: false,//关闭异步上传
        uploadLabel: "上传",//设置上传按钮的汉字
        uploadClass: "btn btn-primary",//设置上传按钮样式
        showCaption: true,//是否显示标题
        language: "zh",//配置语言
        uploadUrl: baseUrl+"/my-sbm/fabu/uploadPic.do",
        maxFileSize: 1024 * 20,
        minFileCount: 1,
        maxFileCount: 5, /*允许最大上传数，可以多个，当前设置单5个*/
        enctype: 'multipart/form-data',
        //allowedPreviewTypes : [ 'image' ], //allowedFileTypes: ['image', 'video', 'flash'],
        allowedFileExtensions: ["jpg", "png", "gif"], /*上传文件格式*/
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        dropZoneTitle: "请选择闲置图片（第一张将作为展示图片）",
        dropZoneClickTitle: "",
        uploadExtraData: function(previewId, index){
            var data=xzInfo;
            return data;
        },//这个是外带数据
        showBrowse: true,
        browseOnZoneClick: true,
        layoutTemplates: {
            //actionDelete:'', //去除上传预览的缩略图中的删除图标
            actionUpload: '',//去除上传预览缩略图中的上传图片；
            //actionZoom: ''   //去除上传预览缩略图中的查看详情预览的缩略图标。
        },
        slugCallback: function (filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
    });

    $('#file-2').on("filebatchuploadsuccess", function (event, data, previewId, index) {
        if(data.result=="2"){
            alert("未登录，请登录后再发布。");
            window.location.href = 'login.html';
        }else{
            if(data.errorMessages!=null){
                alert(data.errorMessages);
                return;
            }else{
                alert("发布成功");
                window.location.href = 'fabu.html';
            }
        }
    })



})

function showFirstTitle() {
    $("#firstTitle").animate({
        marginTop:"40px",
        marginLeft:"479px"
    })
};

//选择
function selectBtn(btn) {
    var old = $("#fabuWords").val();
    if (old.length < 1) {
        document.getElementById("fabuWords").value = btn.value;
    } else {
        document.getElementById("fabuWords").value = old + "/" + btn.value;
    }
}

function selectXzType(type){
    $("#xzDetailDiv").show();
    $("#xzSmPicDiv").hide();
    $("#xzTypeDiv").hide();
    xzInfo.goodsType=type;
    $("#firstTitle").text("输入闲置其他信息");


}

function checkGoodsDetail(){
    var msg="";
    if(xzInfo.goodsArea.trim()==""){
        msg=msg+"区域、"
    }
    if(xzInfo.goodsSpecificType.trim()==""){
        msg=msg+"类型、"
    }
    if(xzInfo.goodsName.trim()==""){
        msg=msg+"名称、"
    }
    if(xzInfo.goodsPrice.trim()==""){
        msg=msg+"价格、"
    }
    if(xzInfo.goodsSpecificType.trim()==""){
        msg=msg+"类型、"
    }
    if(xzInfo.goodsQq.trim()==""&&xzInfo.goodsWords.trim()==""){
        msg=msg+"QQ或微信(至少填写一个)、"
    }
    if(msg!=""){
        msg=msg.substring(0,msg.length-1)+"为必填项。"
    }
    return msg;

}
