var userAvatar=null;
$(function () {
    getAvatarPicName();
    if(userAvatar != null && userAvatar != ""){
        document.getElementById("loginOrLoginOut").innerHTML = '';
        $("#loginOrLoginOut").append("<li class='loginOrLoginOut'><a href='##' id='logOut'><span class='glyphicon glyphicon-log-in'></span> 注销</a></li>");
        document.getElementById("perPic").src = avatarPicURL + userAvatar.substring(0, 4) + "/" + userAvatar.substring(4, 6) + "/"
            + userAvatar.substring(6, 8) + "/" + userAvatar;
    }else{
        document.getElementById("perPic").src = "../img/perPic.png";
        var s = "<li class='loginOrLoginOut'><a href='login.html?index=regist'><span class='glyphicon glyphicon-user'></span> 注册</a></li>"+
            "<li class='loginOrLoginOut'><a href='login.html'><span class='glyphicon glyphicon-log-in'></span> 登录</a></li>";
        document.getElementById("loginOrLoginOut").innerHTML = '';
        $("#loginOrLoginOut").append(s);
    }
    //发布闲置
    $("#fabu-a").click(function () {
        if(userAvatar != null && userAvatar != ""){
            window.location.href = 'fabu.html';
        }else{
            window.location.href = 'login.html';
        }

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


    //用户注销
    $("#logOut").click(function () {
        $.ajax({
            url: baseUrl + "/my-sbm/login/userLoginOut.do",
            type: "post",
            dataType: 'json',
            success: function (data) {
                if (data.result) {
                    window.location.href = 'login.html';
                } else {
                    window.location.href = 'login.html';
                }
            },
            error: function (e) {
                window.location.href = 'login.html';
            }
        });
    });

    $("#perPic").click(function () {
        if(userAvatar != null && userAvatar != ""){
            window.location.href = 'person.html?index=personZL';
        }else{
            window.location.href = 'login.html';
        }
    });



});

function getAvatarPicName() {
    $.ajax({
        url: baseUrl + "/my-sbm/personCenter/avatarPic.do",
        // 请求方式
        type: "get",
        data: "",
        processData: false,
        contentType: false,
        async: false,
        dataType: "text",
        success: function (data) {
           userAvatar=data;
        }
    });
}