$(function () {
    $("#daohang1").on('mouseenter', "li", function () {
        var type;
        var index=$("#daohang1 li").index(this)+1;
        $("#daohang1HoverBottom"+index).attr("background",'url("../img/hover-loading.gif") center no-repeat');
        if(index==1){
            type="DZ";
        }
        if(index==2){
            type="SJ";
        }
        if(index==3){
            type="RC";
        }
        if(index==4){
            type="JZ";
        }
        if(index==5){
            type="HT";
        }
        var data={"type":type};
        $.ajax({
            url: baseUrl + "/sousou/sougoodsHover.do",
            type: "post",
            data: data,
            dataType: 'json',
            success: function (data) {
                // document.getElementById("#daohang1HoverBottom"+index).style.background = "";
                $("#daohang1HoverBottom"+index).css("background","none");
                var innerHtml = "";
                if(data.length>2){
                    data.length=2;
                }
                if(data.length==0){
                    var innerHtml =
                        '<div class="hover_part">'+
                            '<div class="hover_img">'+
                                '<svg class="icon" aria-hidden="true" style="width: 100px;height: 100px">'+
                                '<use xlink:href="#icon-fabu-null"></use>'+
                                '</svg>'+
                            '</div>'+
                            '<div class="hover_content">'+
                                '空空如也，等你来发布...'+
                            '</div>'+
                        '</div>';
                    document.getElementById("daohang1HoverBottom"+index).innerHTML=innerHtml;
                }else {
                    for(var i=0;i<data.length;i++){
                        if(data[i].goodsCollectionAmount==null){
                            data[i].goodsCollectionAmount=0;
                        }
                        innerHtml=innerHtml+'<div class="daohang1HoverRec" onclick="showSpecialGood(\''+data[i].goodsId+'\')">'+
                            '<div class="recPic"><img src='+getPicUrl(data[i].goodsPic1)+'/></div>'+
                            '<div class="recDetail">'+
                            '<div class="recName"><h5>'+data[i].goodsName+'</h5></div>'+
                            '<div class="recCommon"><h5>'+data[i].goodsWords+'</h5></div>'+
                            '<div class="recCommon"><h5>￥'+data[i].goodsPrice+'</h5></div>'+
                            '<div class="recCommon"><h5>❤'+data[i].goodsCollectionAmount+'</h5></div>'+
                            '</div>'+
                            '</div>';
                    }
                    document.getElementById("daohang1HoverBottom"+index).innerHTML=innerHtml;
                }

            },
            error: function (e) {
                openAlert("服务异常，请联系客服");
            }
        });
    });

    showMainGoods();
});



function addSuggestion() {
    window.location.href = 'suggestion.html';
}
function souSpecialType(specialType) {
    window.location.href="home.html?keyword=&currentPage=1&specialType="+specialType;
}
function showSpecialGood(id) {
    window.location.href="speciallyGood.html?id="+id;
}
function getPicUrl(picName) {
    return goodsPicURL + picName.substring(0, 4) + "/" + picName.substring(4, 6) + "/"
        + picName.substring(6, 8) + "/" + picName;
}

//主页展示的商品
function showMainGoods() {
    $.ajax({
        url: baseUrl + "/sousou/souMainGoods.do",
        type: "post",
        dataType: 'json',
        success: function (data) {
            var mainGoods1 = "";
            var mainGoods2 = "";
            if(data.length<5){
                for(var i=0;i<data.length;i++){
                    mainGoods1 = mainGoods1+
                        '<div class="main_goods_part">'+
                        '<a href="speciallyGood.html?id='+data[i]["goodsId"]+'">'+
                        '<div class="main_goods_img">'+
                        '<img src="'+getPicUrl(data[i]["goodsPic1"])+'"/>'+
                        '</div>'+
                        '<div class="main_goods_text">'+
                        '<h4>'+data[i]["goodsName"]+' <strong>￥'+data[i]["goodsPrice"]+'</strong></h4>'+
                        '</div>'+
                        '</a>'+
                        '</div>'
                }
                for(var i=data.length;i<5;i++){
                    mainGoods1 = mainGoods1+
                        '<div class="main_goods_part no_goods">'+
                        '<a href="javascript:void(0);">'+
                        '<div class="main_goods_img">'+
                        '<img src="../img/hover-loading.gif"/>'+
                        '</div>'+
                        '<div class="main_goods_text">'+
                        '<h4>虚位以待</h4>'+
                        '</div>'+
                        '</a>'+
                        '</div>'
                }
                for(var i=0;i<5;i++){
                    mainGoods2 = mainGoods2+
                        '<div class="main_goods_part no_goods">'+
                        '<a href="javascript:void(0);">'+
                        '<div class="main_goods_img">'+
                        '<img src="../img/hover-loading.gif"/>'+
                        '</div>'+
                        '<div class="main_goods_text">'+
                        '<h4>虚位以待</h4>'+
                        '</div>'+
                        '</a>'+
                        '</div>'
                }
            }else if(data.length==5){
                for(var i=0;i<data.length;i++){
                    mainGoods1 = mainGoods1+
                        '<div class="main_goods_part">'+
                        '<a href="speciallyGood.html?id='+data[i]["goodsId"]+'">'+
                        '<div class="main_goods_img">'+
                        '<img src="'+getPicUrl(data[i]["goodsPic1"])+'"/>'+
                        '</div>'+
                        '<div class="main_goods_text">'+
                        '<h4>'+data[i]["goodsName"]+' <strong>￥'+data[i]["goodsPrice"]+'</strong></h4>'+
                        '</div>'+
                        '</a>'+
                        '</div>'
                }
                for(var i=0;i<5;i++){
                    mainGoods2 = mainGoods2+
                        '<div class="main_goods_part no_goods">'+
                        '<a href="javascript:void(0);">'+
                        '<div class="main_goods_img">'+
                        '<img src="../img/hover-loading.gif"/>'+
                        '</div>'+
                        '<div class="main_goods_text">'+
                        '<h4>虚位以待</h4>'+
                        '</div>'+
                        '</a>'+
                        '</div>'
                }
            }else if(data.length==10){
                for(var i=0;i<5;i++){
                    mainGoods1 = mainGoods1+
                        '<div class="main_goods_part">'+
                        '<a href="speciallyGood.html?id='+data[i]["goodsId"]+'">'+
                        '<div class="main_goods_img">'+
                        '<img src="'+getPicUrl(data[i]["goodsPic1"])+'"/>'+
                        '</div>'+
                        '<div class="main_goods_text">'+
                        '<h4>'+data[i]["goodsName"]+' <strong>￥'+data[i]["goodsPrice"]+'</strong></h4>'+
                        '</div>'+
                        '</a>'+
                        '</div>'
                }
                for(var i=5;i<10;i++){
                    mainGoods2 = mainGoods2+
                        '<div class="main_goods_part">'+
                        '<a href="speciallyGood.html?id='+data[i]["goodsId"]+'">'+
                        '<div class="main_goods_img">'+
                        '<img src="'+getPicUrl(data[i]["goodsPic1"])+'"/>'+
                        '</div>'+
                        '<div class="main_goods_text">'+
                        '<h4>'+data[i]["goodsName"]+' <strong>￥'+data[i]["goodsPrice"]+'</strong></h4>'+
                        '</div>'+
                        '</a>'+
                        '</div>'
                }
            }else if(data.length>5 && data.length <10){
                for(var i=0;i<5;i++){
                    mainGoods1 = mainGoods1+
                        '<div class="main_goods_part">'+
                        '<a href="speciallyGood.html?id='+data[i]["goodsId"]+'">'+
                        '<div class="main_goods_img">'+
                        '<img src="'+getPicUrl(data[i]["goodsPic1"])+'"/>'+
                        '</div>'+
                        '<div class="main_goods_text">'+
                        '<h4>'+data[i]["goodsName"]+' <strong>￥'+data[i]["goodsPrice"]+'</strong></h4>'+
                        '</div>'+
                        '</a>'+
                        '</div>'
                }
                for(var i=5;i<data.length;i++){
                    mainGoods2 = mainGoods2+
                        '<div class="main_goods_part">'+
                        '<a href="speciallyGood.html?id='+data[i]["goodsId"]+'">'+
                        '<div class="main_goods_img">'+
                        '<img src="'+getPicUrl(data[i]["goodsPic1"])+'"/>'+
                        '</div>'+
                        '<div class="main_goods_text">'+
                        '<h4>'+data[i]["goodsName"]+' <strong>￥'+data[i]["goodsPrice"]+'</strong></h4>'+
                        '</div>'+
                        '</a>'+
                        '</div>'
                }
                for(var i=data.length;i<10;i++){
                    mainGoods2 = mainGoods2+
                        '<div class="main_goods_part">'+
                        '<a href="speciallyGood.html?id='+data[i]["goodsId"]+'">'+
                        '<div class="main_goods_img">'+
                        '<img src="'+getPicUrl(data[i]["goodsPic1"])+'"/>'+
                        '</div>'+
                        '<div class="main_goods_text">'+
                        '<h4>'+data[i]["goodsName"]+' <strong>￥'+data[i]["goodsPrice"]+'</strong></h4>'+
                        '</div>'+
                        '</a>'+
                        '</div>'
                }

            }
            document.getElementById("mainGoods1").innerHTML  = mainGoods1;
            document.getElementById("mainGoods2").innerHTML  = mainGoods2;
        },
        error: function (e) {
            openAlert("服务异常，请联系客服");
        }
    });
}