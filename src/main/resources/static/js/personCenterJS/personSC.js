var totalPage;
var currentPage;

$(function () {
    $("#per-shoucang").click(function () {
        document.getElementById("souScInput").value = "";
        $("#per-shoucang").addClass("per-click");
        $("#per-shoucang").siblings().removeClass("per-click");
        $("#per-click-show").hide();
        $("#yifabu-click-show").hide();
        $("#recycle-click-show").hide();
        $("#sc-click-show").show();
        findSC();
    });

    //搜索SC
    $("#souScBtn").click(function () {
        findSC();
    });
});

function findSC() {
    openLoading();
    var data;//传输参数
    if ($("#souScInput").val().trim() != null) {
        data = {"keyword": $("#souScInput").val()};
    }
    $.ajax({
        url: baseUrl + "/personCenter/personSc.do",
        type: "post",
        data: data,
        dataType: 'json',
        success: function (data) {
            if (data.errorMessage == '2') {
                closeLoading();
                openAlert("用户未登录，请重新登录");
                return;
            } else {
                showPerScGoods(data.result);
                if(data.result!=null){
                    makePage(data.result.totalPage);
                    totalPage = data.result.totalPage;
                }
                closeLoading();
            }
        },
        error: function (e) {
            closeLoading();
            openAlert("服务异常，请联系客服");
        }
    });
}


//显示SC内容
function showPerScGoods(data) {
    var append = "";
    document.getElementById("shoucang").innerHTML = '';
    if(data==null || data.records==null || data.records.length == 0){
        selectIsNull("shoucang");
    }else{
        for (var i = 0; i < data.records.length; i++) {
            var goodsPic1 = data.records[i].goodsPic1;
            var goodsName = data.records[i].goodsName;
            var goodsWords = data.records[i].goodsWords;
            var goodsPrice = data.records[i].goodsPrice;
            var goosdId = data.records[i].goodsId;
            append = append +
                "<div id='fabu-all' class='goods_"+goosdId+"'>" +
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
                "<div id='fabu-btn'>" +
                "<button id='showSc' onclick=showYifabu('" + goosdId + "')>查看</button>" +
                "<button id='deleteSc' onclick=deleteScGoods('" + goosdId + "')>删除</button>" +
                "</div>" +
                "</div>";
        }
        $("#shoucang").append(append);
    }
}//显示SC内容end

function deleteScGoods(goodsId) {
    openLoading();
    $.ajax({
        url: baseUrl + "/personCenter/deleteScGoods.do",
        // 请求方式
        type: "post",
        data: goodsId,
        contentType: 'application/text',
        // 服务器响应的数据类型
        dataType: "json",
        // 请求成功时执行的回调函数
        success: function (data) {
            if(data["result"]){
                $(".goods_"+goodsId).remove();
                closeLoading();
                openAlert("该收藏已删除")
            }else{
                closeLoading();
                openAlert(data["errorMessages"]);
            }
        },
        error:function () {
            closeLoading();
            openAlert("非常抱歉，服务异常，请重试或联系管理员");
        }
    });
}


//画出分页部分
function makePage(totalPage) {
    document.getElementById("shoucang-page").innerHTML = '';
    if (totalPage > 1 && totalPage <= 4) {
        var page = "<ul class='pagination'>" +
            "<li><a id='upPage' class='btn' onclick='upPage()'>&laquo;上一页</a></li>";
        for (var i = 0; i < totalPage; i++) {
            page = page + "<li><a id='nowpage" + (i + 1) + "'class='btn clickpage' onclick=changePage(" + (i + 1) + ")>" + (i + 1) + "</a></li>";
        }
        page = page + "<li><a id='downPage' class='btn' onclick='downPage()'>&raquo;下一页 </a></li>";
        page = page + "</ul>";
        $("#shoucang-page").append(page);
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
        $("#shoucang-page").append(page);
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


//画出当前页的内容
function makeGoodsDetails(currentPage) {
    if (currentPage != null) {
        var data;//传输参数
        if ($("#souScInput").val().trim() != null) {
            data = {"keyword": $("#souScInput").val(), "currentPage": currentPage};
        } else {
            data = {"currentPage": currentPage};
        }
        ifAble(currentPage);
        ifHavaUpOrDown(totalPage);
        $.ajax({
            url: baseUrl + "/personCenter/personSc.do",
            data: data,
            type: "post",
            dataType: 'json',
            success: function (data) {
                if (data.result != null) {
                    showPerScGoods(data.result);
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


//上一页
function upPage() {
    currentPage = currentPage - 1;
    var nowPage = currentPage;
    if (nowPage >= 5) {
        document.getElementById("shoucang-page").innerHTML = '';
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
        $("#shoucang-page").append(page);
        makeGoodsDetails(currentPage);
    } else {
        if (nowPage == 2) {
            document.getElementById("shoucang-page").innerHTML = '';
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
            $("#shoucang-page").append(page);
            makeGoodsDetails(currentPage);
        } else {
            makeGoodsDetails(currentPage);
        }
    }
}


//下一页
function downPage() {
    currentPage = currentPage + 1;
    var nowPage = currentPage;
    if (nowPage >= 3 && nowPage <= totalPage - 1) {
        document.getElementById("shoucang-page").innerHTML = '';
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
        $("#shoucang-page").append(page);
        makeGoodsDetails(currentPage);
    } else {
        makeGoodsDetails(currentPage);
    }
}


//每一页是否可点击
function ifAble(nowPage) {
    var id = "nowpage" + nowPage;
    $(".clickpage").removeClass("disabled");
    $(".clickpage").removeClass("dddBlackground");
    $("#" + id).addClass("dddBlackground");
}


//点击省略...号前面一页
function dianFrontClick(nowPage) {
    document.getElementById("shoucang-page").innerHTML = '';
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
    $("#shoucang-page").append(page);
    changePage(nowPage);
}


//点击省略...号后面一页
function dianLaterClick(nowPage) {
    document.getElementById("shoucang-page").innerHTML = '';
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
    $("#shoucang-page").append(page);
    changePage(nowPage);
}


//点击最后一页
function lastPageClick(nowPage) {
    document.getElementById("shoucang-page").innerHTML = '';
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
    $("#shoucang-page").append(page);
    changePage(nowPage);
    ifHavaUpOrDown(totalPage);
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
    document.getElementById("shoucang-page").innerHTML = '';
    makePage(totalPage);
    changePage(nowPage);
}