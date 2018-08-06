$(function () {
    $("#daohang1").on('mouseenter', "li", function () {
        var type;
        var index=$("#daohang1 li").index(this)+1;
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
            url: baseUrl + "/my-sbm/sousou/sougoodsHover.do",
            type: "post",
            data: data,
            dataType: 'json',
            success: function (data) {
                document.getElementById("daohang1HoverBottom"+index).innerHTML="";
                var innerHtml = "";
                if(data.length>2){
                    data.length=2;
                }
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

            },
            error: function (e) {
            }
        });
    });
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