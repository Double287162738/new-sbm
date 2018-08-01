
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
                document.getElementById("ownerSay").innerHTML = data.result.goodsOther;
                document.getElementById("showGoodsDetailPic").innerHTML = "";
                var showOtherPicDetail = "";
                if (data.result.goodsPic2 != null && data.result.goodsPic2 != '') {
                    showOtherPicDetail=showOtherPicDetail+'<li class="item falldown" id="item2">'
                        +'<figure>'
                        +'<div class="view"><img src='+getPicUrl(data.result.goodsPic2)+'/></div>'
                        +'</figure>'
                        +'</li>';
                }
                if (data.result.goodsPic3 != null && data.result.goodsPic3 != '') {
                    showOtherPicDetail=showOtherPicDetail+'<li class="item falldown" id="item3">'
                        +'<figure>'
                        +'<div class="view"><img src='+getPicUrl(data.result.goodsPic3)+'/></div>'
                        +'</figure>'
                        +'</li>';
                }
                if (data.result.goodsPic4 != null && data.result.goodsPic4 != '') {
                    showOtherPicDetail=showOtherPicDetail+'<li class="item falldown" id="item4">'
                        +'<figure>'
                        +'<div class="view"><img src='+getPicUrl(data.result.goodsPic4)+'/></div>'
                        +'</figure>'
                        +'</li>';
                }
                if (data.result.goodsPic5 != null && data.result.goodsPic5 != '') {
                    showOtherPicDetail=showOtherPicDetail+'<li class="item falldown" id="item5">'
                        +'<figure>'
                        +'<div class="view"><img src='+getPicUrl(data.result.goodsPic5)+'/></div>'
                        +'</figure>'
                        +'</li>';
                }
                document.getElementById("showGoodsDetailPic").innerHTML = showOtherPicDetail;
            }
        },
        error: function (e) {
            alert("查看失败，请重试或联系管理员");
            return;
        }
    });

    $('#goodsModal').modal('show');
}

function getPicUrl(picName) {
    return goodsPicURL + picName.substring(0, 4) + "/" + picName.substring(4, 6) + "/"
        + picName.substring(6, 8) + "/" + picName;
}