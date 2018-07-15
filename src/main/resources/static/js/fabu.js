
var xzInfo =
    {"goodsType":"",
    "goodsArea":"",
    "goodsSpecificType":"",
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
        console.log(xzInfo);
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
        uploadUrl: "http://localhost:8080/my-sbm/fabu/uploadPic.do",
        maxFileSize: 1024 * 20,
        minFileCount: 1,
        maxFileCount: 5, /*允许最大上传数，可以多个，当前设置单个*/
        enctype: 'multipart/form-data',
        //allowedPreviewTypes : [ 'image' ], //allowedFileTypes: ['image', 'video', 'flash'],
        allowedFileExtensions: ["jpg", "png", "gif"], /*上传文件格式*/
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        dropZoneTitle: "请选择闲置图片（第一张将作为展示图片）",
        dropZoneClickTitle: "",
        //uploadExtraData: {"id": id},//这个是外带数据
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
        otherPic = data.response.result;
        console.log("main:" + mainPic);
        console.log("other:" + otherPic);
        if (mainPic == "用户没有登录" || otherPic == "用户没有登录") {
            document.getElementById("alert-p").innerHTML = "你还未登录，请<a href='login.html'>登录</a>";
            $("#myAlert").show();
            return;
        }
        if (mainPic == null || otherPic == null) {
            return;
        }
        //图片上传成功之后，提交其他信息
        var url = baseUrl + "/fabu/uploadDetail.do";
        var goodsDetail = {
            "goodsType": $("#fabu-type-select").val(),
            "goodsArea": $("#fabu-area-select").val(),
            "goodsName": $("#fabuName").val(),
            "goodsPrice": $("#fabuPrice").val(),
            "goodsWords": $("#fabuWords").val(),
            "goodsQq": $("#fabuQq").val(),
            "goodsWx": $("#fabuWx").val(),
            "goodsOther": $("#fabu-part2-text").val(),
            "goodsPic1": mainPic.toString(),
            "goodsPic2": otherPic.toString()
        };
        $.ajax({
            url: url,
            type: "post",
            data: goodsDetail,
            dataType: 'json',
            success: function (data) {
                if (data.result == 1) {
                    alert("发布成功");
                }
                if (data.result == 2) {
                    document.getElementById("alert-p").innerHTML = "你还未登录，请<a href='login.html'>登录</a>";
                    $("#myAlert").show();
                    return;
                }
                if (data.result == 0) {
                    document.getElementById("alert-p").innerText = data.errorMessages;
                    $("#myAlert").show();
                    return;
                }
            },
            error: function (e) {
                document.getElementById("alert-p").innerText = '呜呜呜,SK嗖嗖罢工了,请联系客服妹妹。';
                $("#myAlert").show();
                return;
            }
        });  //提交其他信息结束
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




// var mainPic;//封面图片返回图片名字
// var otherPic;//其他图片
// var baseUrl = "http://localhost:8080/my-sbm";
// $(function () {
//     if(getCookie("userAvatar") == null){
//         window.location.href = 'login.html';
//     }
//     var index = 1;
//     $("#main-other-system").hide();
//     $("#tedian-pic").click(function () {
//         if (index % 2 == 1) {
//             $("#tedian-pic").css("transform", "scale(1.2)");
//         } else {
//             $("#tedian-pic").css("transform", "scale(1.0)");
//         }
//         index++;
//         $("#main-other-system").slideToggle();
//     });
//
//     $("#fabu-part2-text").click(function () {
//         $("#fabu-part2-text").css("text-align", "left");
//         $("#fabu-part2-text").css("line-height", "20px");
//         document.getElementById("fabu-part2-text").value = '';
//     });
//     //发布按钮
//     $("#fabu-confirm").click(function () {
//         $("#myAlert").hide();//先把警示框隐藏。
//         var fabuType = $("#fabu-type-select").val().trim();
//         var fabuArea = $("#fabu-area-select").val().trim();
//         var fabuName = $("#fabuName").val().trim();
//         var fabuPrice = $("#fabuPrice").val().trim();
//         var fabuWords = $("#fabuWords").val().trim();
//         var fabuQq = $("#fabuQq").val().trim();
//         var fabuWx = $("#fabuWx").val().trim();
//         var fabuText = $("#fabu-part2-text").val().trim();
//         if (fabuType == '') {
//             document.getElementById("alert-p").innerText = '请选择发布类型,让你的宝贝更快的被发现。';
//             $("#myAlert").show();
//             return;
//         }
//         if (fabuArea == '') {
//             document.getElementById("alert-p").innerText = '请选择宝贝所在区域,让你的宝贝更快的被发现。';
//             $("#myAlert").show();
//             return;
//         }
//         if (fabuName == '') {
//             document.getElementById("alert-p").innerText = '请给宝贝起个名字吧,让你的宝贝更快的被发现。';
//             $("#myAlert").show();
//             return;
//         } else {
//             if (fabuName.length > 10) {
//                 document.getElementById("alert-p").innerText = '你的宝贝是俄罗斯的吗？名字也太长了点吧。。';
//                 $("#myAlert").show();
//                 return;
//             }
//         }
//         if (fabuPrice == '') {
//             document.getElementById("alert-p").innerText = '爱情可以无价，但是请给宝贝出个正常的价格吧。。';
//             $("#myAlert").show();
//             return;
//         } else {
//             if (fabuPrice.length > 5) {
//                 document.getElementById("alert-p").innerText = '你这个价格忒大了，找不开零钱呐。。。';
//                 $("#myAlert").show();
//                 return;
//             }
//         }
//         if (fabuWords == '') {
//             document.getElementById("alert-p").innerText = '请说说宝贝的特点吧。(提示:你可以点击箭头快速选择关键字)';
//             $("#myAlert").show();
//             return;
//         } else {
//             if (fabuWords.length > 15) {
//                 document.getElementById("alert-p").innerText = '哼,你有王婆卖瓜的嫌疑，宝贝特点太多啦';
//                 $("#myAlert").show();
//                 return;
//             }
//         }
//         if (fabuQq == '' && fabuWx == '') {
//             document.getElementById("alert-p").innerText = '请至少留下QQ和微信中的任何一个,让小伙伴能联系到你呐。';
//             $("#myAlert").show();
//             return;
//         } else {
//             if (fabuQq.length < 4) {
//                 document.getElementById("alert-p").innerText = '我读书少,别骗我,我还没见过' + fabuQq.length + '位的QQ号呢';
//                 $("#myAlert").show();
//                 return;
//             }
//             if (fabuQq.length > 14) {
//                 document.getElementById("alert-p").innerText = '马化腾说：我们家没有' + fabuQq.length + '位的QQ号';
//                 $("#myAlert").show();
//                 return;
//             }
//             if (fabuWx.length > 15) {
//                 document.getElementById("alert-p").innerText = '你的微信号是在火星申请的吗，怎么会这么长！';
//                 $("#myAlert").show();
//                 return;
//             }
//         }
//         if (fabuText == '') {
//             document.getElementById("alert-p").innerText = '介绍一下你的宝贝吧。';
//             $("#myAlert").show();
//             return;
//         } else {
//             if (fabuText.length > 500) {
//                 document.getElementById("alert-p").innerText = '亲！您这是写了一篇作文吗,这个介绍也太长了吧，我受不了！';
//                 $("#myAlert").show();
//                 return;
//             }
//         }
//         //图片上传
//         $('#file-1').fileinput("upload");
//         $('#file-2').fileinput("upload");
//     })
//     $(".close").click(function () {
//         $("#myAlert").hide();
//     });
//
//
// })
//
//
// //图片上传部分
// $(function () {
//     //单一图片上传
//     $('#file-1').fileinput({//初始化上传文件框
//         autoReplace: true,//自动替换图片
//         showUpload: true,
//         showRemove: true,
//         uploadAsync: false,
//         uploadLabel: "上传",//设置上传按钮的汉字
//         uploadClass: "btn btn-primary",//设置上传按钮样式
//         showCaption: true,//是否显示标题
//         language: "zh",//配置语言
//         uploadUrl: "http://localhost:8080/my-sbm/fabu/uploadPic.do",
//         maxFileSize: 1024 * 20,
//         minFileCount: 1,
//         maxFileCount: 1, /*允许最大上传数，可以多个，当前设置单个*/
//         enctype: 'multipart/form-data',
//         //allowedPreviewTypes : [ 'image' ], //allowedFileTypes: ['image', 'video', 'flash'],
//         allowedFileExtensions: ["jpg", "png", "gif"], /*上传文件格式*/
//         msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
//         dropZoneTitle: "请选择封面图片",
//         dropZoneClickTitle: "",
//         //uploadExtraData: {"id": id},//这个是外带数据
//         showBrowse: true,
//         browseOnZoneClick: true,
//         layoutTemplates: {
//             //actionDelete:'', //去除上传预览的缩略图中的删除图标
//             actionUpload: '',//去除上传预览缩略图中的上传图片；
//             //actionZoom: ''   //去除上传预览缩略图中的查看详情预览的缩略图标。
//         },
//         slugCallback: function (filename) {
//             return filename.replace('(', '_').replace(']', '_');
//         }
//     });
//     //filebatchuploadsuccess:同步上传回调
//     //fileuploaded：异步上传回调
//     $('#file-1').on("filebatchuploadsuccess", function (event, data, previewId, index) {
//         mainPic = data.response.result;
//     })
//
//     //多图片上传
//     $('#file-2').fileinput({//初始化上传文件框
//         //removeFromPreviewOnError:true,//不显示不符合要求的文件格式
//         showUpload: true,
//         showRemove: true,
//         uploadAsync: false,//关闭异步上传
//         uploadLabel: "上传",//设置上传按钮的汉字
//         uploadClass: "btn btn-primary",//设置上传按钮样式
//         showCaption: true,//是否显示标题
//         language: "zh",//配置语言
//         uploadUrl: "http://localhost:8080/my-sbm/fabu/uploadPic.do",
//         maxFileSize: 1024 * 20,
//         minFileCount: 1,
//         maxFileCount: 8, /*允许最大上传数，可以多个，当前设置单个*/
//         enctype: 'multipart/form-data',
//         //allowedPreviewTypes : [ 'image' ], //allowedFileTypes: ['image', 'video', 'flash'],
//         allowedFileExtensions: ["jpg", "png", "gif"], /*上传文件格式*/
//         msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
//         dropZoneTitle: "请选择其他详情图片",
//         dropZoneClickTitle: "",
//         //uploadExtraData: {"id": id},//这个是外带数据
//         showBrowse: true,
//         browseOnZoneClick: true,
//         layoutTemplates: {
//             //actionDelete:'', //去除上传预览的缩略图中的删除图标
//             actionUpload: '',//去除上传预览缩略图中的上传图片；
//             //actionZoom: ''   //去除上传预览缩略图中的查看详情预览的缩略图标。
//         },
//         slugCallback: function (filename) {
//             return filename.replace('(', '_').replace(']', '_');
//         }
//     });
//
//     $('#file-2').on("filebatchuploadsuccess", function (event, data, previewId, index) {
//         otherPic = data.response.result;
//         console.log("main:" + mainPic);
//         console.log("other:" + otherPic);
//         if (mainPic == "用户没有登录" || otherPic == "用户没有登录") {
//             document.getElementById("alert-p").innerHTML = "你还未登录，请<a href='login.html'>登录</a>";
//             $("#myAlert").show();
//             return;
//         }
//         if (mainPic == null || otherPic == null) {
//             return;
//         }
//         //图片上传成功之后，提交其他信息
//         var url = baseUrl + "/fabu/uploadDetail.do";
//         var goodsDetail = {
//             "goodsType": $("#fabu-type-select").val(),
//             "goodsArea": $("#fabu-area-select").val(),
//             "goodsName": $("#fabuName").val(),
//             "goodsPrice": $("#fabuPrice").val(),
//             "goodsWords": $("#fabuWords").val(),
//             "goodsQq": $("#fabuQq").val(),
//             "goodsWx": $("#fabuWx").val(),
//             "goodsOther": $("#fabu-part2-text").val(),
//             "goodsPic1": mainPic.toString(),
//             "goodsPic2": otherPic.toString()
//         };
//         $.ajax({
//             url: url,
//             type: "post",
//             data: goodsDetail,
//             dataType: 'json',
//             success: function (data) {
//                 if (data.result == 1) {
//                     alert("发布成功");
//                 }
//                 if (data.result == 2) {
//                     document.getElementById("alert-p").innerHTML = "你还未登录，请<a href='login.html'>登录</a>";
//                     $("#myAlert").show();
//                     return;
//                 }
//                 if (data.result == 0) {
//                     document.getElementById("alert-p").innerText = data.errorMessages;
//                     $("#myAlert").show();
//                     return;
//                 }
//             },
//             error: function (e) {
//                 document.getElementById("alert-p").innerText = '请联系管理员';
//                 $("#myAlert").show();
//                 return;
//             }
//         });  //提交其他信息结束
//     })
// })
//
// //选择
// function selectBtn(btn) {
//     var old = $("#fabuWords").val();
//     if (old.length < 1) {
//         document.getElementById("fabuWords").value = btn.value;
//     } else {
//         document.getElementById("fabuWords").value = old + "/" + btn.value;
//     }
// }
