$(function () {
    //发布闲置
    $("#fabu-a").click(function () {
        window.location.href = 'fabu.html';
    });
    //搜索框的回车事件
    $('#sousuoInput').bind('keydown', function (event) {
        if (event.keyCode == "13") {
            window.location.href = 'home.html?keyword=' + $("#sousuoInput").val() + "&currentPage=1";
            window.event.returnValue=false;
        }
    });
});
