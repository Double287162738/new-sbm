var totalPage;
var currentPage;
var keyword;
var specialType;
var nowGoodsId;
var souType="";
var souArea={};
$(function () {
    //闲置详情框的高度
    $("#goodsModal").css("height", window.screen.height);
    //图片的高度
    $("#pic-part").css("height", $("#goodsModal").height() * 0.5);
    //价格、用户等其他信息部分
    $("#other-part").css("height", $("#goodsModal").height() * 0.5);
    var locationUrl = window.location.href;
    if (locationUrl.indexOf("?") == -1 || locationUrl.indexOf("&") == -1) {
        return;
    }
    if (locationUrl.indexOf("#") != -1) {
        locationUrl = locationUrl.substring(0, locationUrl.indexOf("#"));
    }
    keyword = locationUrl.split("?")[1].split("&")[0].split("=")[1];
    currentPage = locationUrl.split("?")[1].split("&")[1].split("=")[1];
    specialType = locationUrl.split("?")[1].split("&")[2].split("=")[1];
    $("#sousuoInput").val(decodeURI(keyword));
    $("#findInput").val(decodeURI(keyword));
    //接收搜索请求
    if (keyword != null && currentPage != null) {
        if(specialType=='rec' || specialType=='pop'){
            if(specialType=='rec'){
                $("#sousou_rec").addClass('sousou-btn-click');
                $("#sousou_rec").siblings().removeClass("sousou-btn-click");
            }else {
                $("#sousou_pop").addClass('sousou-btn-click');
                $("#sousou_pop").siblings().removeClass("sousou-btn-click");
            }

            //此处specialType的作用只是作为临时参数，所以需要置空
            souType=specialType;
            specialType='';
        }
        getSouSouResult();
    }else {
        return;
    }
    //搜索框的回车事件
    $('#findInput').bind('keydown', function (event) {
        if (event.keyCode == "13") {
            keyword=$("#findInput").val();
            console.log(keyword);
            currentPage=1;
            getSouSouResult();
        }
    });
    $("#sousou").click(function () {
        keyword=$("#findInput").val();
        $("#sousuoInput").val("");
        currentPage=1;
        getSouSouResult();
    });

    $(".especiallyArea").change(function () {
        var groupCheckbox=$("input[name='area']");
        souArea={};
        var index=0;
        for(i=0;i<groupCheckbox.length;i++){
            if(groupCheckbox[i].checked){
                souArea[index]=groupCheckbox[i].value;
                index++;
            }
        }
        getSouSouResult();
    });


});

function especiallySouSou(obj,type) {
    $(obj).addClass('sousou-btn-click');
    $(obj).siblings().removeClass("sousou-btn-click");
    souType=type;
    getSouSouResult();
}


function getSouSouResult(){
    openLoading();
    document.getElementById("showPart").innerHTML = '';
    var souSouInparameterDTO={"keyWord":keyword,"souType":souType,"currentPage":currentPage,"souArea":souArea,"specialType":specialType};
    $.ajax({
        url: baseUrl + "/sousou/sougoods.do",
        type: "post",
        data: souSouInparameterDTO,
        dataType: 'json',
        success: function (data) {
            if (data.records != null && data.records.length>0 ) {
                for (var i = 0; i < data.records.length; i++) {
                    var goodsPic1 = data.records[i].goodsPic1;
                    $("#showPart").append(
                        "<div id='xianzhi-all'>" +
                        "<div id='xianzhi-pic-border'>" +
                        "<a onclick='openDetail(\"" + data.records[i].goodsId + "\")' data-toggle='modal' >" +
                        "<div id='xianzhi-pic'>" +
                        "<img src='" + goodsPicURL + goodsPic1.substring(0, 4) + "/" + goodsPic1.substring(4, 6) + "/"
                        + goodsPic1.substring(6, 8) + "/" + goodsPic1 + "' />" +
                        "</div>" +
                        "</a>" +
                        "</div>" +
                        "<div id='xianzhi-other-border'>" +
                        "<div id='xianzhi-price-border'><span class='glyphicon glyphicon-yen'></span><span id='xianzhi-price'>" + data.records[i].goodsPrice + "</span></div>" +
                        "<div id='xianzhi-name'>" + data.records[i].goodsName + "</div>" +
                        "<div id='xianzhi-keyword'>" + data.records[i].goodsWords + "</div>" +
                        "</div>" +
                        "</div>")
                }
                totalPage = data.totalPage;
                makePage(totalPage);
                closeLoading();
            } else {
                var innerHtml =
                '<div class="goods_null_part">'+
                    '<div sou_goods_null_pic>'+
                        '<svg class="icon" aria-hidden="true" style="width: 200px;height: 200px">'+
                            '<use xlink:href="#icon-fabu-null"></use>'+
                        '</svg>'+
                     '</div>'+
                    '<div class="kkry">空空如也...</div>'+
                '</div>';
                document.getElementById("showPart").innerHTML = innerHtml;
                closeLoading();
            }
        },
        error: function (e) {
            openAlert("服务异常，请联系客服");
        }
    });
}

//展示查询获得的商品
function makeGoodsDetails(keyword, currentPage) {
    if (keyword != null && currentPage != null) {
        var data = {"keyword": keyword, "currentPage": currentPage};
        $.ajax({
            url: baseUrl + "/sousou/sougoods.do",
            type: "post",
            data: data,
            dataType: 'json',
            success: function (data) {
                if (data.records != null) {
                    document.getElementById("showPart").innerHTML = '';
                    for (var i = 0; i < data.records.length; i++) {
                        var goodsPic1 = data.records[i].goodsPic1;
                        $("#showPart").append(
                            "<div id='xianzhi-all'>" +
                            "<div id='xianzhi-pic-border'>" +
                            "<a onclick='openDetail(" + data.records[i].goodsId + ")' data-toggle='modal' >" +
                            "<div id='xianzhi-pic'>" +
                            "<img src='" + goodsPicURL + goodsPic1.substring(0, 4) + "/" + goodsPic1.substring(4, 6) + "/"
                            + goodsPic1.substring(6, 8) + "/" + goodsPic1 + "' />" +
                            "</div>" +
                            "</a>" +
                            "</div>" +
                            "<div id='xianzhi-other-border'>" +
                            "<div id='xianzhi-price-border'><span class='glyphicon glyphicon-yen'></span><span id='xianzhi-price'>" + data.records[i].goodsPrice + "</span></div>" +
                            "<div id='xianzhi-name'>" + data.records[i].goodsName + "</div>" +
                            "<div id='xianzhi-keyword'>" + data.records[i].goodsWords + "</div>" +
                            "</div>" +
                            "</div>")
                    }
                    ifAble(currentPage);
                    ifHavaUpOrDown(totalPage);
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

//点击查看商品详情
function openDetail(e) {
    openLoading();
    var url = baseUrl + "/sousou/sougoodsDetail.do";
    var data = {"goodsId": e};
    nowGoodsId=e;
    $.ajax({
        url: url,
        type: "post",
        data: data,
        dataType: 'json',
        success: function (data) {
            if (data.result == null) {
                closeLoading();
                openAlert(data.errorMessages);
            } else {
                if (data.result.userName == null) {
                    data.result.userName = "一位不愿透漏姓名的唐马儒先生";
                }
                if (data.result.goodsName == null) {
                    data.result.goodsName = "神秘物品";
                }
                if (data.result.goodsCollectionAmount == null) {
                    data.result.goodsCollectionAmount = 0;
                }
                if (data.result.goodsClickAmount == null) {
                    data.result.goodsClickAmount = 0;
                }
                if (data.result.goodsQq == null) {
                    data.result.goodsQq = "太粗心了，竟然没有留下QQ";
                }
                if (data.result.goodsWx == null) {
                    data.result.goodsWx = "太粗心了，竟然没有留下QQ";
                }
                var goodsPic1 = data.result.goodsPic1;
                document.getElementById("goodsPic1").src = goodsPicURL + goodsPic1.substring(0, 4) + "/" + goodsPic1.substring(4, 6) + "/"
                    + goodsPic1.substring(6, 8) + "/" + goodsPic1;
                document.getElementById("owner").innerHTML = data.result.userName;
                document.getElementById("goods").innerHTML = data.result.goodsName;
                document.getElementById("goodsName").innerHTML = data.result.goodsName;
                document.getElementById("goodsWords").innerHTML = data.result.goodsWords;

                document.getElementById("goodsPrice").innerHTML = "&nbsp;" + data.result.goodsPrice;
                document.getElementById("goodsCollectionAmount").innerHTML = "&nbsp;被" + data.result.goodsCollectionAmount + "人收藏";
                document.getElementById("goodsClickAmount").innerHTML = "&nbsp;" + data.result.goodsClickAmount + "人浏览过";
                document.getElementById("qqSpan").innerHTML = "&nbsp;" + data.result.goodsQq;
                document.getElementById("weixinSpan").innerHTML = "&nbsp;" + data.result.goodsWx;
                document.getElementById("areaSpan").innerHTML = data.result.goodsArea+"区";
                document.getElementById("ownerSay").innerHTML = data.result.goodsOther;
                document.getElementById("goodsDetailPic").innerHTML = "";
                var otherPicDetail = "";
                if (data.result.goodsPic2 != null && data.result.goodsPic2 != '') {
                    otherPicDetail=otherPicDetail+'<li class="item falldown" id="item2">'
                                                 +'<figure>'
                                                    +'<div class="view"><img id="goodsPic2" src='+getPicUrl(data.result.goodsPic2)+'/></div>'
                                                 +'</figure>'
                                                 +'</li>';
                }
                if (data.result.goodsPic3 != null && data.result.goodsPic3 != '') {
                    otherPicDetail=otherPicDetail+'<li class="item falldown" id="item3">'
                        +'<figure>'
                        +'<div class="view"><img id="goodsPic3" src='+getPicUrl(data.result.goodsPic3)+'/></div>'
                        +'</figure>'
                        +'</li>';
                }
                if (data.result.goodsPic4 != null && data.result.goodsPic4 != '') {
                    otherPicDetail=otherPicDetail+'<li class="item falldown" id="item4">'
                        +'<figure>'
                        +'<div class="view"><img id="goodsPic4" src='+getPicUrl(data.result.goodsPic4)+'/></div>'
                        +'</figure>'
                        +'</li>';
                }
                if (data.result.goodsPic5 != null && data.result.goodsPic5 != '') {
                    otherPicDetail=otherPicDetail+'<li class="item falldown" id="item5">'
                        +'<figure>'
                        +'<div class="view"><img id="goodsPic5" src='+getPicUrl(data.result.goodsPic5)+'/></div>'
                        +'</figure>'
                        +'</li>';
                }
                if(data.result.ifCollected){
                    document.getElementById("ifCollect").innerHTML="已收藏";
                    $("#addCollectBtn").addClass("notAllowed");
                    $("#addCollectBtn").unbind();
                }else{
                    $("#addCollectBtn").bind("click", addCollection);
                    document.getElementById("ifCollect").innerHTML="加入收藏夹";
                    $("#addCollectBtn").removeClass("notAllowed");
                }
                document.getElementById("goodsDetailPic").innerHTML = otherPicDetail;
            }
            closeLoading();
            $('#goodsModal').modal('show');
        },
        error: function (e) {
            closeLoading();
            openAlert("查看失败，请重试或联系管理员");
            return;
        }
    });

}

//获得商品图片路径
function getPicUrl(picName) {
    return goodsPicURL + picName.substring(0, 4) + "/" + picName.substring(4, 6) + "/"
        + picName.substring(6, 8) + "/" + picName;
}

//添加收藏
function addCollection() {
    openLoading();
    var data = {"goodsId":nowGoodsId};
    $.ajax({
        url: baseUrl + "/collection/addCollection.do",
        type: "post",
        data:data,
        dataType: 'json',
        success: function (data) {
            if(data.result=="2"){
                window.location.href = 'login.html';
            }else{
                document.getElementById("ifCollect").innerHTML="已收藏";
                $("#addCollectBtn").addClass("notAllowed");
                $("#addCollectBtn").unbind();
            }
            closeLoading();
        },
        error: function (e) {
            document.getElementById("ifCollect").innerHTML="已收藏";
            $("#addCollectBtn").addClass("notAllowed");
            $("#addCollectBtn").unbind();
            closeLoading();
        }
    });
}




//画出分页部分
function makePage(totalPage) {
    if (totalPage > 1 && totalPage <= 4) {
        var page = "<ul class='pagination'>" +
            "<li><a id='upPage' class='btn' onclick='upPage()'>&laquo;上一页</a></li>";
        for (var i = 0; i < totalPage; i++) {
            page = page + "<li><a id='nowpage" + (i + 1) + "'class='btn clickpage' onclick=changePage(" + (i + 1) + ")>" + (i + 1) + "</a></li>";
        }
        page = page + "<li><a id='downPage' class='btn' onclick='downPage()'>&raquo;下一页 </a></li>";
        page = page + "</ul>";
        $("#yeshu").append(page);
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
        $("#yeshu").append(page);
        currentPage = 1;
        ifHavaUpOrDown(totalPage);
        $("#nowpage1").addClass("dddBlackground");
    }
}

function changePage(nowPage) {
    currentPage = nowPage;
    makeGoodsDetails(keyword, currentPage);
}

function firstPageClick(nowPage) {
    document.getElementById("yeshu").innerHTML = '';
    makePage(totalPage);
    changePage(nowPage);
}

function lastPageClick(nowPage) {
    document.getElementById("yeshu").innerHTML = '';
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
    $("#yeshu").append(page);
    changePage(nowPage);
    ifHavaUpOrDown(totalPage);
}

function dianLaterClick(nowPage) {
    document.getElementById("yeshu").innerHTML = '';
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
    $("#yeshu").append(page);
    changePage(nowPage);
}

function dianFrontClick(nowPage) {
    document.getElementById("yeshu").innerHTML = '';
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
    $("#yeshu").append(page);
    changePage(nowPage);
}

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
//	$(".disabled").click(function(event){
//		event.preventDefault();
//	});
}

function ifAble(nowPage) {
    var id = "nowpage" + nowPage;
    $(".clickpage").removeClass("disabled");
    $(".clickpage").removeClass("dddBlackground");
    $("#" + id).addClass("dddBlackground");
}

function downPage() {
    currentPage = currentPage + 1;
    var nowPage = currentPage;
    if (nowPage >= 3 && nowPage <= totalPage - 1) {
        document.getElementById("yeshu").innerHTML = '';
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
        $("#yeshu").append(page);
        makeGoodsDetails(keyword, currentPage);
    } else {
        makeGoodsDetails(keyword, currentPage);
    }
}

function upPage() {
    currentPage = currentPage - 1;
    var nowPage = currentPage;
    if (nowPage >= 5) {
        document.getElementById("yeshu").innerHTML = '';
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
        $("#yeshu").append(page);
        makeGoodsDetails(keyword, currentPage);
    } else {
        if (nowPage == 2) {
            document.getElementById("yeshu").innerHTML = '';
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
            $("#yeshu").append(page);
            makeGoodsDetails(keyword, currentPage);
        } else {
            makeGoodsDetails(keyword, currentPage);
        }
    }
}


