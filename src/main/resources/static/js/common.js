
var baseUrl = "http://localhost:8080";
$(function () {
    if(getCookie("userAvatar") != null){
        var userAvatarPic = getCookie("userAvatar");
        document.getElementById("loginOrLoginOut").innerHTML = '';
        $("#loginOrLoginOut").append("<li class='loginOrLoginOut'><a href='##' id='logOut'><span class='glyphicon glyphicon-log-in'></span> 注销</a></li>");
        document.getElementById("perPic").src = baseUrl + "/file/AvatarPic/" + userAvatarPic.substring(0, 4) + "/" + userAvatarPic.substring(4, 6) + "/"
            + userAvatarPic.substring(6, 8) + "/" + userAvatarPic;
    }else{
        document.getElementById("perPic").src = "../img/perPic.png";
        var s = "<li class='loginOrLoginOut'><a href='login.html?index=regist'><span class='glyphicon glyphicon-user'></span> 注册</a></li>"+
            "<li class='loginOrLoginOut'><a href='login.html'><span class='glyphicon glyphicon-log-in'></span> 登录</a></li>";
        document.getElementById("loginOrLoginOut").innerHTML = '';
        $("#loginOrLoginOut").append(s);
    }
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
    $("#sousuoBtn").click(function () {
        window.location.href = 'home.html?keyword=' + $("#sousuoInput").val() + "&currentPage=1";
        window.event.returnValue=false;
    });
    
    $("#logOut").click(function () {
        window.location.href = 'login.html';
    });

});

