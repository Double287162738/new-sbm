
$(function(){
    $('.bot-img ul li').click(function(){
        var _this=$(this);
        _this.addClass('active').siblings('li').removeClass('active');
        var int=_this.index();
        $('.activeimg').animate({left:int*-500},"slow");
    });
    var list=$('.bot-img ul li').length;
    $('.activeimg').css({
        width:list*500,
    });
    $('.right').click(function(){
        next(list)

    })
    $('.left').click(function(){
        prev(list)
    });

    
})
var index=0;
//下一张
function next(list){
    if(index<list-1){
        index++;
        $('.activeimg').animate({left:index*-500},"slow");
        $('.bot-img ul li').eq(index).addClass('active').siblings('li').removeClass('active')
    }else{
        index=0;
        $('.activeimg').animate({left:index*-522},"slow");
        $('.bot-img ul li').eq(index).addClass('active').siblings('li').removeClass('active')
    }
}
//        上一张
function prev(list){
    index--;
    if(index<0){
        index=list-1;
        $('.activeimg').animate({left:index*-500},"slow");
        $('.bot-img ul li').eq(index).addClass('active').siblings('li').removeClass('active')
    }else{
        $('.activeimg').animate({left:index*-500},"slow");
        $('.bot-img ul li').eq(index).addClass('active').siblings('li').removeClass('active')
    }
}

$(function () {
    var locationUrl = window.location.href;
    if (locationUrl.indexOf("id") == -1) {
        return;
    }
    var id = locationUrl.split("?")[1].split("=")[1];
    var url = baseUrl + "/my-sbm/sousou/sougoodsDetail.do";
    var data = {"goodsId": id};
    $.ajax({
        url: url,
        type: "post",
        data: data,
        dataType: 'json',
        success: function (data) {
            document.getElementById("activeimg").innerHTML = "";
            document.getElementById("bot-img").innerHTML = "";
            if (data.result == null) {
                alert(data.errorMessages);
            } else {
                if (data.result.userName == null) {
                    data.result.userName = "一位不愿透漏姓名的唐马儒先生";
                }
                if (data.result.goodsName == null) {
                    data.result.goodsName = "未知物品";
                }
                if (data.result.goodsCollectionAmount == null) {
                    data.result.goodsCollectionAmount = 0;
                }
                if (data.result.goodsClickAmount == null) {
                    data.result.goodsClickAmount = 0;
                }
                if (data.result.goodsQq == null) {
                    data.result.goodsQq = "空空如也";
                }
                if (data.result.goodsWx == null) {
                    data.result.goodsWx = "空空如也";
                }
                $("#name").val(data.result.goodsName);
                $("#words").val(data.result.goodsWords);
                $("#price").val("&nbsp;" + data.result.goodsPrice);
                $("#scnum").val("&nbsp;被" + data.result.goodsCollectionAmount + "人收藏");
                $("#llnum").val("&nbsp;" + data.result.goodsClickAmount + "人浏览过");
                $("#qq").val("&nbsp;" + data.result.goodsQq);
                $("#wx").val("&nbsp;" + data.result.goodsWx);
                $("#area").val(data.result.goodsArea+"区");
                $("#other").val( data.result.goodsOther);
                var activeimgHtml="";
                var botImgHtml="";
                if (data.result.goodsPic1 != null && data.result.goodsPic1 != '') {
                    activeimgHtml=activeimgHtml+'<div><img id="pic1" src='+getPicUrl(data.result.goodsPic1)+'></div>';
                    botImgHtml=botImgHtml+'<li class="active"><img src='+getPicUrl(data.result.goodsPic1)+'></li>';
                }
                if (data.result.goodsPic2 != null && data.result.goodsPic2 != '') {
                    activeimgHtml=activeimgHtml+'<div><img id="pic2" src='+getPicUrl(data.result.goodsPic2)+'></div>';
                    botImgHtml=botImgHtml+'<li><img src='+getPicUrl(data.result.goodsPic2)+'></li>';
                }
                if (data.result.goodsPic3 != null && data.result.goodsPic3 != '') {
                    activeimgHtml=activeimgHtml+'<div><img id="pic3" src='+getPicUrl(data.result.goodsPic3)+'></div>';
                    botImgHtml=botImgHtml+'<li><img src='+getPicUrl(data.result.goodsPic3)+'></li>';
                }
                if (data.result.goodsPic4 != null && data.result.goodsPic4 != '') {
                    activeimgHtml=activeimgHtml+'<div><img id="pic4" src='+getPicUrl(data.result.goodsPic4)+'></div>';
                    botImgHtml=botImgHtml+'<li><img src='+getPicUrl(data.result.goodsPic4)+'></li>';
                }
                if (data.result.goodsPic5 != null && data.result.goodsPic5 != '') {
                    activeimgHtml=activeimgHtml+'<div><img id="pic5" src='+getPicUrl(data.result.goodsPic5)+'></div>';
                    botImgHtml=botImgHtml+'<li><img src='+getPicUrl(data.result.goodsPic5)+'></li>';
                }
                document.getElementById("activeimg").innerHTML = activeimgHtml;
                document.getElementById("bot-img").innerHTML = botImgHtml;

            }
        },
        error: function (e) {
            alert("查看失败，请重试或联系管理员");
            return;
        }
    });

})


function getPicUrl(picName) {
    return goodsPicURL + picName.substring(0, 4) + "/" + picName.substring(4, 6) + "/"
        + picName.substring(6, 8) + "/" + picName;
}