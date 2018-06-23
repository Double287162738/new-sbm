$(function () {
    //搜索框点击
    $("#sousuoInput").focus(function () {
        $("#nav-input").css("border-bottom", "1px solid rgba(240,20,20,.4)");
    })
    //搜索框失去焦点
    $("#sousuoInput").blur(function () {
        $("#nav-input").css("border-bottom", "1px solid #C0C0C0");
    })
    //发布闲置
    $("#fabu-a").click(function () {
        window.location.href = 'fabu.html';
    });
    //搜索框的回车事件
    $('#sousuoInput').bind('keydown', function (event) {
        if (event.keyCode == "13") {
            window.location.href = 'home.html?keyword=' + $("#sousuoInput").val() + "&currentPage=1";
        }
    });
    $("#perPicDiv").on("mouseenter", function () {
        $("#person-detail").css("display", "block");
    }).on("mouseleave", function () {
        $("#person-detail").css("display", "none");
    })
});
