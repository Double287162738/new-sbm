var user;
$(function () {
    //获取初始用户信息
    getUserInfo();

    //发布闲置
    $("#fabu-a").click(function () {
        if(user != null && user != ""){
            window.location.href = 'fabu.html';
        }else{
            window.location.href = 'login.html';
        }

    });

    //搜索按钮搜索事件
    $("#sousuoBtn").click(function () {
        window.location.href = 'home.html?keyword=' + $("#sousuoInput").val() + "&currentPage=1&specialType=";
        window.event.returnValue=false;
    });
    $.ajaxSetup({
        complete:function(xhr,data){
            if("REDIRECT" == xhr.getResponseHeader("REDIRECT")){ //若HEADER中含有REDIRECT说明后端想重定向，
                var win = window;
                while(win != win.top){
                    win = win.top;
                }
                win.location.href = xhr.getResponseHeader("CONTENTPATH");//将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
            }
        }
    });
    //注销登录
    function loginOut() {
        $.ajax({
            url: baseUrl + "/login/userLoginOut.do",
            type: "post",
            dataType: 'json',
            success: function (data) {
                window.location.href = 'login.html';
            },
            error: function (e) {
                window.location.href = 'login.html';
            }
        });
    }

    //个人头像点击事件
    $("#perPic").click(function () {
        if(user != null && user != ""){
            window.location.href = 'person.html?index=personZL';
        }else{
            window.location.href = 'login.html';
        }
    });

    //获取用户信息
    function getUserInfo() {
        $.ajax({
            url: baseUrl + "/personCenter/getUserDetail.do",
            type: "post",
            dataType: 'json',
            success: function (data) {
                if (data['result'] == null) {
                    document.getElementById("perPic").src = "../img/perPic.png";
                    s = "<li class='loginOrLoginOut'><a href='login.html?index=regist'><span class='glyphicon glyphicon-user'></span> 注册</a></li>"+
                        "<li class='loginOrLoginOut'><a href='login.html'><span class='glyphicon glyphicon-log-in'></span> 登录</a></li>";
                    document.getElementById("loginOrLoginOut").innerHTML = s;
                }else {
                    user = data['result'];
                    var userAvatar = user.userAvatar;
                    if(userAvatar != null && userAvatar != ""){
                        document.getElementById("perPic").src = avatarPicURL + userAvatar.substring(0, 4) + "/" + userAvatar.substring(4, 6) + "/"+ userAvatar.substring(6, 8) + "/" + userAvatar;
                        var s = "<li id='logOut' class='loginOrLoginOut'><a href='javascript:void(0);'><span class='glyphicon glyphicon-log-in'></span> 注销</a></li>";
                        document.getElementById("loginOrLoginOut").innerHTML = s;
                    }else{
                        document.getElementById("perPic").src = "../img/perPic.png";
                        var s = "<li id='logOut' class='loginOrLoginOut'><a href='javascript:void(0);'><span class='glyphicon glyphicon-log-in'></span> 注销</a></li>";
                        document.getElementById("loginOrLoginOut").innerHTML = s;
                    }
                }
                $("#logOut").bind("click",loginOut);
            },
            error: function (e) {
                console.log("服务异常，请联系管理员");
            }
        });
    }


});
//获取个人商品信息为null
function selectIsNull(id) {
    var append =
        '<div class="null-part">'+
            '<div>'+
                '<svg class="icon" aria-hidden="true" style="width: 200px;height: 200px">'+
                    '<use xlink:href="#icon-fabu-null"></use>'+
                '</svg>'+
            '</div>'+
            '<div class="kkry">空空如也...</div>'+
        '</div>';
    document.getElementById(id).innerHTML=append;
}