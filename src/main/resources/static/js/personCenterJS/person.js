var nowFile = null;
var uploadFile = null;
var openEditPerInfo = false;
var ifUploadPerPic = false;
var perInfo;
var totalPage;
var currentPage;
var nowYiFaBuClick = 'allClick';//当前选中的已发布内容
$(function () {
    var locationUrl = window.location.href;
    var index = locationUrl.split("?")[1].split("=")[1];
    //初始化取消和保存按钮绑定的所有事件
    $("#save-btn").unbind("click");
    $("#cancel-btn").unbind();

    //个人资料点击
    $("#per-ziliao").click(function () {
        $("#per-ziliao").addClass("per-click");
        $("#per-fabu").removeClass("per-click");
        $("#per-shoucang").removeClass("per-click");
        $("#per-huishou").removeClass("per-click");
        $.ajax({
            url: baseUrl + "/my-sbm/personCenter/personInfo.do",
            type: "post",
            dataType: 'json',
            success: function (data) {
                if (data.result != null) {
                    var userAvatarPic = data.result.userAvatar;
                    document.getElementById("edit-input").value = data.result.userName;
                    document.getElementById("edit-input1").value = data.result.userQq;
                    document.getElementById("edit-input2").value = data.result.userWx;
                    document.getElementById("edit-input3").value = data.result.userSex;
                    document.getElementById("per-pic-img").src = avatarPicURL + userAvatarPic.substring(0, 4) + "/" + userAvatarPic.substring(4, 6) + "/"
                        + userAvatarPic.substring(6, 8) + "/" + userAvatarPic;
                    perInfo = {
                        "userName": data.result.userName,
                        "userQq": data.result.userQq,
                        "userWx": data.result.userWx,
                        "userSex": data.result.userSex,
                        "userAvatar": avatarPicURL + userAvatarPic.substring(0, 4) + "/" + userAvatarPic.substring(4, 6) + "/"
                        + userAvatarPic.substring(6, 8) + "/" + userAvatarPic
                    };
                } else {
                    console.log("查无此人");
                }
            },
            error: function (e) {
                console.log("请联系管理员");
            }
        });
        $("#per-click-show").show();
        $("#yifabu-click-show").hide();
        $("#sc-click-show").hide();
        $("#recycle-click-show").hide();
    });

    //搜索已发布
    $("#souYiFaBuBtn").click(function () {
        yifabu("ALL");
    });
    //已发布点击
    $("#per-fabu").click(function () {
        document.getElementById("yifabu").innerHTML = '';
        document.getElementById("yifabu-page").innerHTML = '';
        $("#per-ziliao").removeClass("per-click");
        $("#per-fabu").addClass("per-click");
        $("#per-shoucang").removeClass("per-click");
        $("#per-huishou").removeClass("per-click");
        $("#per-click-show").hide();
        $("#sc-click-show").hide();
        $("#recycle-click-show").hide();
        $("#yifabu-click-show").show();
        nowYiFaBuClick = "ALL";
        yiFaBuClick(nowYiFaBuClick);
    });
    //判断用户点击操作
    if (index == "personZL") {
        document.getElementById("per-ziliao").click();
    } else if (index == "personFB") {
        document.getElementById("per-fabu").click();
    }  else if (index == "personSC") {
        document.getElementById("per-shoucang").click();
    }else {
        document.getElementById("per-ziliao").click();
    }


    //个人资料启动编辑
    $("#edit-open").click(function () {
        if (!openEditPerInfo) {
            openEditPerInfo = true;
            document.getElementById("edit-input").readOnly = false;
            document.getElementById("edit-input1").readOnly = false;
            document.getElementById("edit-input2").readOnly = false;
            document.getElementById("edit-input3").readOnly = false;
            $("#edit-name").css("border-bottom", "2px solid 	#FF8C00");
            $("#edit-qq").css("border-bottom", "2px solid #FF8C00");
            $("#edit-weixin").css("border-bottom", "2px solid #FF8C00");
            $("#edit-sex").css("border-bottom", "2px solid #FF8C00");
            $("#edit-input").focus();
            $("#per-pic-img").addClass("hoverImg");
            $("#per-pic-img").removeClass("notAllowed");
            $("#save-btn").removeClass("disabled");
            $("#cancel-btn").removeClass("disabled");
            $("#cancel-btn").bind("click", closePerInfoEdit);
            $("#select-new-pic").click(function () {
                document.getElementById("select-pic-input").click();
            })
            $("#save-btn").bind("click", savePerInfoChange);
        } else {

        }
    })

})


//选择图片放入到头像框中
$(function () {
    $("#select-pic-input").change(function () {
        var ifFileYes = true;
        var $file = $(this);
        var fileObj = $file[0];
        var windowURL = window.URL || window.webkitURL;
        var dataURL;
        var $img = $("#per-pic-img");
        var nowImg = $("#select-pic-input");
        //赋值全局变量
        uploadFile = new FormData($("#perPicForm")[0]);
        nowFile = document.getElementById("select-pic-input").files[0];
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
                alert("请选择图片,不要选择非图片文件。");
            }
        }
    });
});


//取消按钮
function closePerInfoEdit() {
    openEditPerInfo = false;
    document.getElementById("edit-input").readOnly = true;
    document.getElementById("edit-input1").readOnly = true;
    document.getElementById("edit-input2").readOnly = true;
    document.getElementById("edit-input3").readOnly = true;
    $("#edit-name").css("border-bottom", "2px solid #DCDCDC");
    $("#edit-qq").css("border-bottom", "2px solid #DCDCDC");
    $("#edit-weixin").css("border-bottom", "2px solid #DCDCDC");
    $("#edit-sex").css("border-bottom", "2px solid #DCDCDC");
    $("#per-pic-img").addClass("notAllowed");
    $("#per-pic-img").removeClass("hoverImg");
    $("#save-btn").addClass("disabled");
    $("#cancel-btn").addClass("disabled");
    $("#save-btn").unbind();
    $("#cancel-btn").unbind();
    $("#select-new-pic").unbind();
    //恢复初始值
    document.getElementById("edit-input").value = perInfo.userName;
    document.getElementById("edit-input1").value = perInfo.userQq;
    document.getElementById("edit-input2").value = perInfo.userWx;
    document.getElementById("edit-input3").value = perInfo.userSex;
    if(perInfo.userAvatar!=null){
        document.getElementById("per-pic-img").src = perInfo.userAvatar;
    }
};


//保存用户修改信息
function savePerInfoChange() {
    //用户信息
    var newUserName = document.getElementById("edit-input").value.trim();
    var newUserQq = document.getElementById("edit-input1").value.trim();
    var newUserWx = document.getElementById("edit-input2").value.trim();
    var newUserSex = document.getElementById("edit-input3").value.trim();
    perInfo.userName=newUserName;
    perInfo.userQq=newUserQq;
    perInfo.userWx=newUserWx;
    perInfo.userSex=newUserSex;
    var user = {"userName":newUserName,
                "userSex":newUserSex,
                "userQq":newUserQq,
                "userWx":newUserWx
                };
    //头像有改动
    if(ifUploadPerPic){
        uploadFile.append("user",JSON.stringify(user));
    }else{
        //头像无改动，formData一个不存在的表单
        uploadFile = new FormData($("#perPicForm")[1]);
        uploadFile.append("user",JSON.stringify(user));
    }

    $.ajax({
        url: baseUrl + "/my-sbm/personCenter/uploadPerInfo.do",
        // 请求方式
        type: "post",
        data: uploadFile,
        processData: false,
        contentType: false,
        async: false,
        // 服务器响应的数据类型
        dataType: "json",
        // 请求成功时执行的回调函数
        success: function (data) {
            console.log(data.result);
            perInfo.userAvatar= null;
            var newAvatarPicUrl=avatarPicURL + data.result.substring(0, 4) + "/" + data.result.substring(4, 6) + "/"
                + data.result.substring(6, 8) + "/" + data.result;
            document.getElementById("perPic").src=newAvatarPicUrl;
            alert("修改成功");
            document.getElementById("cancel-btn").click();
        },
        error:function () {
            alert("非常抱歉，修改失败，请重试或联系管理员");
        }
    });
}


//显示已发布内容
function showPerFabuGoods(data) {
    var append = "";
    document.getElementById("yifabu").innerHTML = '';
    for (var i = 0; i < data.records.length; i++) {
        var goodsPic1 = data.records[i].goodsPic1;
        var goodsName = data.records[i].goodsName;
        var goodsWords = data.records[i].goodsWords;
        var goodsPrice = data.records[i].goodsPrice;
        var goodsId = data.records[i].goodsId;
        append = append +
            "<div id='fabu-all'>" +
            "<div id='fabu-pic'>" +
            "<img src='" + goodsPicURL + goodsPic1.substring(0, 4) + "/" + goodsPic1.substring(4, 6) + "/"
            + goodsPic1.substring(6, 8) + "/" + goodsPic1 + "' />" +
            "</div>" +
            "<div id='fabu-other'>" +
            "<h4>" + goodsName + "</h4>" +
            "<h5>" + goodsWords + "</h5>" +
            "<div id='fabu-other-jiage'>" +
            "<img src='../img/jiage.png' />" +
            "<span> " + goodsPrice + "</span>" +
            "</div>" +
            "</div>" +
            "<div id='fabu-btn-1'>" +
            "<button id='show' onclick=showYifabu('" + goodsId + "')>查看</button>" +
            "<button id='edit' onclick=editYifabu('" + goodsId + "')>编辑</button>" +
            "<button id='delete'>删除</button>" +
            "</div>" +
            "</div>";
    }
    $("#yifabu").append(append);
}//显示已发布内容end


//画出分页部分
function makePage(totalPage) {
    document.getElementById("yifabu-page").innerHTML = '';
    if (totalPage > 1 && totalPage <= 4) {
        var page = "<ul class='pagination'>" +
            "<li><a id='upPage' class='btn' onclick='upPage()'>&laquo;上一页</a></li>";
        for (var i = 0; i < totalPage; i++) {
            page = page + "<li><a id='nowpage" + (i + 1) + "'class='btn clickpage' onclick=changePage(" + (i + 1) + ")>" + (i + 1) + "</a></li>";
        }
        page = page + "<li><a id='downPage' class='btn' onclick='downPage()'>&raquo;下一页 </a></li>";
        page = page + "</ul>";
        $("#yifabu-page").append(page);
        currentPage = 1;
        ifHavaUpOrDown(totalPage);
        $("#nowpage1").addClass("dddBlackground");
    }
    if (totalPage > 4) {
        var page = "<ul class='pagination'>" +
            "<li><a id='upPage' class='btn' onclick='upPage()'>&laquo;上一页</a></li>";
        for (var i = 0; i < 3; i++) {
            page = page + "<li><a id='nowpage" + (i + 1) + "'class='btn clickpage' onclick=changePage(" + (i + 1) + ")>" + (i + 1) + "</a></li>";
        }
        page = page + "<li><a class='btn disabled'>..</a></li>";
        page = page + "<li><a id='nowpage" + totalPage + "'class='btn clickpage' onclick=lastPageClick(" + totalPage + ")>" + totalPage + "</a></li>";
        page = page + "<li><a id='downPage' class='btn' onclick='downPage()'>&raquo;下一页 </a></li>";
        page = page + "</ul>";
        $("#yifabu-page").append(page);
        currentPage = 1;
        ifHavaUpOrDown(totalPage);
        $("#nowpage1").addClass("dddBlackground");
    }
}


//上一页和下一页是否可点击
function ifHavaUpOrDown(totalPage) {
    if (currentPage <= 1) {
        $("#upPage").addClass("disabled");
    } else {
        $("#upPage").removeClass("disabled");
    }
    if (currentPage >= totalPage) {
        $("#downPage").addClass("disabled");
    } else {
        $("#downPage").removeClass("disabled");
    }
}

//点击页数，显示点击的当前页
function changePage(nowPage) {
    currentPage = nowPage;
    ifAble(nowPage);
    makeGoodsDetails(currentPage);
    ifHavaUpOrDown(totalPage);
}

//点击第一页
function firstPageClick(nowPage) {
    document.getElementById("yifabu-page").innerHTML = '';
    makePage(totalPage);
    changePage(nowPage);
}

//点击最后一页
function lastPageClick(nowPage) {
    document.getElementById("yifabu-page").innerHTML = '';
    var page = "<ul class='pagination'>" +
        "<li><a id='upPage' class='btn' onclick='upPage()'>&laquo;上一页</a></li>" +
        "<li><a id='nowpage" + 1 + "'class='btn clickpage' onclick=firstPageClick(" + 1 + ")>" + 1 + "</a></li>" +
        "<li><a class='btn disabled'>..</a></li>";
    for (var i = nowPage - 2; i <= nowPage; i++) {
        if (i == nowPage - 2) {
            page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=dianLaterClick(" + (i) + ")>" + (i) + "</a></li>";
        } else {
            page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=changePage(" + (i) + ")>" + (i) + "</a></li>";
        }
    }
    page = page + "<li><a id='downPage' class='btn' onclick='downPage()'>&raquo;下一页 </a></li>";
    page = page + "</ul>";
    $("#yifabu-page").append(page);
    changePage(nowPage);
    ifHavaUpOrDown(totalPage);
}

//点击省略...号后面一页
function dianLaterClick(nowPage) {
    document.getElementById("yifabu-page").innerHTML = '';
    var page = "<ul class='pagination'>" +
        "<li><a id='upPage' class='btn' onclick='upPage()'>&laquo;上一页</a></li>" +
        "<li><a id='nowpage" + 1 + "'class='btn clickpage' onclick=firstPageClick(" + 1 + ")>" + 1 + "</a></li>" +
        "<li><a class='btn disabled'>..</a></li>";
    if (nowPage == 3) {
        for (var j = 3; j < 6; j++) {
            if (j == 5) {
                page = page + "<li><a id='nowpage" + (j) + "'class='btn clickpage' onclick=dianFrontClick(" + (j) + ")>" + (j) + "</a></li>";
            } else {
                page = page + "<li><a id='nowpage" + (j) + "'class='btn clickpage' onclick=changePage(" + (j) + ")>" + (j) + "</a></li>";
            }
        }
    } else {
        for (var i = nowPage - 2; i <= nowPage; i++) {
            if (i > 2) {
                if (i == nowPage - 2) {
                    page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=dianLaterClick(" + (i) + ")>" + (i) + "</a></li>";
                } else {
                    if (i == nowPage) {
                        page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=dianFrontClick(" + (i) + ")>" + (i) + "</a></li>";
                    } else {
                        page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=changePage(" + (i) + ")>" + (i) + "</a></li>";
                    }
                }
            }
        }
    }
    page = page + "<li><a class='btn disabled'>..</a></li>";
    page = page + "<li><a id='nowpage" + totalPage + "'class='btn clickpage' onclick=lastPageClick(" + totalPage + ")>" + totalPage + "</a></li>";
    page = page + "<li><a id='downPage' class='btn' onclick='downPage()'>&raquo;下一页 </a></li>";
    page = page + "</ul>";
    $("#yifabu-page").append(page);
    changePage(nowPage);
}

//点击省略...号前面一页
function dianFrontClick(nowPage) {
    document.getElementById("yifabu-page").innerHTML = '';
    var page = "<ul class='pagination'>" +
        "<li><a id='upPage' class='btn' onclick='upPage()'>&laquo;上一页</a></li>" +
        "<li><a id='nowpage" + 1 + "'class='btn clickpage' onclick=firstPageClick(" + 1 + ")>" + 1 + "</a></li>" +
        "<li><a class='btn disabled'>..</a></li>";
    if (nowPage == totalPage - 2) {
        for (var i = nowPage - 2; i <= nowPage; i++) {
            if (i == nowPage - 2) {
                page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=dianLaterClick(" + (i) + ")>" + (i) + "</a></li>";
            } else {
                page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=changePage(" + (i) + ")>" + (i) + "</a></li>";
            }
        }
    } else {
        for (var i = nowPage; i < nowPage + 3; i++) {
            if (i < totalPage - 1) {
                if (i == nowPage) {
                    page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=dianLaterClick(" + (i) + ")>" + (i) + "</a></li>";
                } else {
                    if (i == nowPage + 2) {
                        page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=dianFrontClick(" + (i) + ")>" + (i) + "</a></li>";
                    } else {
                        page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=changePage(" + (i) + ")>" + (i) + "</a></li>";
                    }
                }
            }
        }
    }
    page = page + "<li><a class='btn disabled'>..</a></li>";
    page = page + "<li><a id='nowpage" + totalPage + "'class='btn clickpage' onclick=lastPageClick(" + totalPage + ")>" + totalPage + "</a></li>";
    page = page + "<li><a id='downPage' class='btn' onclick='downPage()'>&raquo;下一页 </a></li>";
    page = page + "</ul>";
    $("#yifabu-page").append(page);
    changePage(nowPage);
}


//每一页是否可点击
function ifAble(nowPage) {
    var id = "nowpage" + nowPage;
    $(".clickpage").removeClass("disabled");
    $(".clickpage").removeClass("dddBlackground");
    $("#" + id).addClass("dddBlackground");
}


//下一页
function downPage() {
    currentPage = currentPage + 1;
    var nowPage = currentPage;
    if (nowPage >= 3 && nowPage <= totalPage - 1) {
        document.getElementById("yifabu-page").innerHTML = '';
        var page = "<ul class='pagination'>" +
            "<li><a id='upPage' class='btn' onclick='upPage()'>&laquo;上一页</a></li>" +
            "<li><a id='nowpage" + 1 + "'class='btn clickpage' onclick=firstPageClick(" + 1 + ")>" + 1 + "</a></li>" +
            "<li><a class='btn disabled'>..</a></li>";
        if (nowPage <= totalPage - 3) {
            for (var i = nowPage; i <= nowPage + 2; i++) {
                if (i == nowPage) {
                    page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=dianLaterClick(" + (i) + ")>" + (i) + "</a></li>";
                } else {
                    if (i == nowPage + 2) {
                        if (totalPage - i == 1) {
                            page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=changePage(" + (i) + ")>" + (i) + "</a></li>";
                        } else {
                            page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=dianFrontClick(" + (i) + ")>" + (i) + "</a></li>";
                        }
                    } else {
                        page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=changePage(" + (i) + ")>" + (i) + "</a></li>";
                    }
                }
            }
            if (nowPage < totalPage - 3) {
                page = page + "<li><a class='btn disabled'>..</a></li>";
            }
        } else {
            for (var i = nowPage - 1; i <= totalPage - 1; i++) {
                if (i == nowPage - 1) {
                    page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=dianLaterClick(" + (i) + ")>" + (i) + "</a></li>";
                } else {
                    page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=changePage(" + (i) + ")>" + (i) + "</a></li>";
                }
            }
        }
        page = page + "<li><a id='nowpage" + totalPage + "'class='btn clickpage' onclick=lastPageClick(" + totalPage + ")>" + totalPage + "</a></li>";
        page = page + "<li><a id='downPage' class='btn' onclick='downPage()'>&raquo;下一页 </a></li>";
        page = page + "</ul>";
        $("#yifabu-page").append(page);
        makeGoodsDetails(currentPage);
    } else {
        makeGoodsDetails(currentPage);
    }
}


//上一页
function upPage() {
    currentPage = currentPage - 1;
    var nowPage = currentPage;
    if (nowPage >= 5) {
        document.getElementById("yifabu-page").innerHTML = '';
        var page = "<ul class='pagination'>" +
            "<li><a id='upPage' class='btn' onclick='upPage()'>&laquo;上一页</a></li>" +
            "<li><a id='nowpage" + 1 + "'class='btn clickpage' onclick=firstPageClick(" + 1 + ")>" + 1 + "</a></li>" +
            "<li><a class='btn disabled'>..</a></li>";
        for (var i = nowPage - 2; i <= nowPage; i++) {
            if (i == nowPage - 2) {
                page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=dianLaterClick(" + (i) + ")>" + (i) + "</a></li>";
            } else {
                if (i == nowPage) {
                    page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=dianFrontClick(" + (i) + ")>" + (i) + "</a></li>";
                } else {
                    page = page + "<li><a id='nowpage" + (i) + "'class='btn clickpage' onclick=changePage(" + (i) + ")>" + (i) + "</a></li>";
                }
            }
        }
        if (totalPage - currentPage > 1) {
            page = page + "<li><a class='btn disabled'>..</a></li>";
        }
        page = page + "<li><a id='nowpage" + totalPage + "'class='btn clickpage' onclick=lastPageClick(" + totalPage + ")>" + totalPage + "</a></li>";
        page = page + "<li><a id='downPage' class='btn' onclick='downPage()'>&raquo;下一页 </a></li>";
        page = page + "</ul>";
        $("#yifabu-page").append(page);
        makeGoodsDetails(currentPage);
    } else {
        if (nowPage == 2) {
            document.getElementById("yifabu-page").innerHTML = '';
            var page = "<ul class='pagination'>" +
                "<li><a id='upPage' class='btn' onclick='upPage()'>&laquo;上一页</a></li>" +
                "<li><a id='nowpage" + 1 + "'class='btn clickpage' onclick=firstPageClick(" + 1 + ")>" + 1 + "</a></li>";
            for (var i = nowPage; i <= 3; i++) {
                if (i == nowPage) {
                    page = page + "<li><a id='nowpage" + i + "'class='btn clickpage' onclick=changePage(" + i + ")>" + i + "</a></li>";
                } else {
                    page = page + "<li><a id='nowpage" + i + "'class='btn clickpage' onclick=dianFrontClick(" + i + ")>" + i + "</a></li>";
                }
            }
            page = page + "<li><a class='btn disabled'>..</a></li>";
            page = page + "<li><a id='nowpage" + totalPage + "'class='btn clickpage' onclick=lastPageClick(" + totalPage + ")>" + totalPage + "</a></li>";
            page = page + "<li><a id='downPage' class='btn' onclick='downPage()'>&raquo;下一页 </a></li>";
            page = page + "</ul>";
            $("#yifabu-page").append(page);
            makeGoodsDetails(currentPage);
        } else {
            makeGoodsDetails(currentPage);
        }
    }
}

//画出当前页的内容
function makeGoodsDetails(currentPage) {
    if (currentPage != null) {
        ifAble(currentPage);
        ifHavaUpOrDown(totalPage);
        if ($("#souYiFaBuInput").val() != null && $("#souYiFaBuInput").val() != '') {
            var data = {
                "yiFaBuKind": nowYiFaBuClick,
                "currentPage": currentPage,
                "keyword": $("#souYiFaBuInput").val()
            };
        } else {
            var data = {"yiFaBuKind": nowYiFaBuClick, "currentPage": currentPage};
        }
        $.ajax({
            url: baseUrl + "/my-sbm/personCenter/personFabu.do",
            type: "post",
            data: data,
            dataType: 'json',
            success: function (data) {
                if (data.result != null) {
                    showPerFabuGoods(data.result);
                } else {
                    console.log("无数据");
                }
            },
            error: function (e) {
                console.log("错误");
            }
        });
    } else {
        return;
    }
}


function yiFaBuClick(e) {
    //是否搜索关键字
    if ($("#souYiFaBuInput").val() != null && $("#souYiFaBuInput").val() != '') {
        var data = {"yiFaBuKind": e, "keyword": $("#souYiFaBuInput").val()};
    } else {
        var data = {"yiFaBuKind": e};
    }
    $.ajax({
        url: baseUrl + "/my-sbm/personCenter/personFabu.do",
        type: "post",
        dataType: 'json',
        data: data,
        success: function (data) {
            if (data.errorMessage == '2') {
                alert("用户未登录，请重新登录");
                return;
            } else {
                showPerFabuGoods(data.result);
                makePage(data.result.totalPage);
                totalPage = data.result.totalPage;
            }
        },
        error: function (e) {
            console.log("请联系管理员");
        }
    });
}


//查看已发布的内容
function yifabu(e) {
    $("#" + e).parent().siblings().removeClass("active");//父类的兄弟节点remove active
    $("#" + e).parent().addClass("active");//父类add active
    //查询
    yiFaBuClick(e);
}

