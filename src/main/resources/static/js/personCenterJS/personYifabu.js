var baseUrl = "http://localhost:8080";

$(function () {
    //闲置详情框的高度
    $("#goodsModal").css("height", window.screen.height);
    //图片的高度
    $("#pic-part").css("height", $("#goodsModal").height() * 0.5);
    //价格、用户等其他信息部分
    $("#other-part").css("height", $("#goodsModal").height() * 0.5);
})

function showYifabu(goodsId) {
    showYifabuDetail(goodsId);
}

function showYifabuDetail(goodsId) {
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
                document.getElementById("goodsPic1").src = baseUrl + "/file/GoodsPic/" + goodsPic1.substring(0, 4) + "/" + goodsPic1.substring(4, 6) + "/"
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
                document.getElementById("ownerSay").innerHTML = data.result.goodsOther;
                if (data.result.goodsPic2 != null && data.result.goodsPic2 != '') {
                    console.log("2:" + data.result.goodsPic2);
                    $.cookie("goodsPic2", data.result.goodsPic2);
                } else {
                    $.cookie("goodsPic2", "null");
                }
                if (data.result.goodsPic3 != null && data.result.goodsPic3 != '') {
                    console.log("3:" + data.result.goodsPic3);
                    $.cookie("goodsPic3", data.result.goodsPic3);
                } else {
                    $.cookie("goodsPic3", "null");
                }
                if (data.result.goodsPic4 != null && data.result.goodsPic4 != '') {
                    console.log("4:" + data.result.goodsPic4);
                    $.cookie("goodsPic4", data.result.goodsPic4);
                } else {
                    $.cookie("goodsPic4", "null");
                }
                if (data.result.goodsPic5 != null && data.result.goodsPic5 != '') {
                    console.log("5:" + data.result.goodsPic5);
                    $.cookie("goodsPic5", data.result.goodsPic5);
                } else {
                    $.cookie("goodsPic5", "null");
                }
                if (data.result.goodsPic6 != null && data.result.goodsPic6 != '') {
                    console.log("6:" + data.result.goodsPic6);
                    $.cookie("goodsPic6", data.result.goodsPic6);
                } else {
                    $.cookie("goodsPic6", "null");
                }
                if (data.result.goodsPic7 != null && data.result.goodsPic7 != '') {
                    console.log("7:" + data.result.goodsPic7);
                    $.cookie("goodsPic7", data.result.goodsPic7);
                } else {
                    $.cookie("goodsPic7", "null");
                }
                if (data.result.goodsPic8 != null && data.result.goodsPic8 != '') {
                    console.log("8:" + data.result.goodsPic8);
                    $.cookie("goodsPic8", data.result.goodsPic8);
                } else {
                    $.cookie("goodsPic8", "null");
                }
                if (data.result.goodsPic9 != null && data.result.goodsPic9 != '') {
                    console.log("9:" + data.result.goodsPic9);
                    $.cookie("goodsPic9", data.result.goodsPic9);
                } else {
                    $.cookie("goodsPic9", "null");
                }
            }
        },
        error: function (e) {
            alert("查看失败，请重试或联系管理员");
            return;
        }
    });

    $('#goodsModal').modal('show');
}