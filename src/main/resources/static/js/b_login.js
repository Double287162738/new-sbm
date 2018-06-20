var baseUrl = "http://localhost:8080/my-sbm";
$(function () {
    var a = document.getElementById('sm-pic');
    $("#sm-pic").hover(function () {
        $("#sm-login-warning").show();
    });
    $("#sm-pic").mouseleave(function () {
        $("#sm-login-warning").hide();
    });
    $("#sm-pic").click(function () {
        $("#loginmain").hide();//密码登录隐藏
        $("#login-sm-part").show();//扫码登陆显示
        $("#sm-login-warning").hide();//密码登录情况提示扫码登录内容 隐藏
        $("#sm-pic").hide();//扫码登录图片隐藏
        $("#sm-pic1").show();//密码登录图片显示
    });
    $("#sm-pic1").click(function () {
        $("#loginmain").show();//密码登录隐藏
        $("#login-sm-part").hide();//扫码登陆显示
        $("#sm-pic").show();//扫码登录图片隐藏
        $("#sm-pic1").hide();//密码登录图片显示
    });
    $("#registB").click(function () {
        $("#loginmain").hide();
        $("#sm-pic").hide();
        $("#sm-pic2").show();
        $("#regist").show();
    })
    $("#forgetB").click(function () {
        $("#loginmain").hide();
        $("#sm-pic").hide();
        $("#sm-pic3").show();
        $("#forget").show();
    });



    /* 手机验证码*/
    $("#get-code").click(function () {

        //校验是否位手机号
        var phoneCode = $("#mobile-input").val();
            var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
            if (!myreg.test(phoneCode)) {
                document.getElementById("loginMsg1").innerText ="请输入正确的手机号";
                return false;
            }else {
                document.getElementById("loginMsg1").innerText ="";
            }
        //设置验证码时间
        var time = 59;
        var btn = document.getElementById("get-code");
        document.getElementById("get-code").setAttribute("disabled", true);
        document.getElementById("get-code").innerText = "重新获取(59s)";
        timer = setInterval(function () {
            time--;
            if (time >= 1) {
                document.getElementById("get-code").innerText = "重新获取" + "(" + time + ")";
            } else {
                clearInterval(timer);
                btn.disabled = false;
                document.getElementById("get-code").innerText = "获取验证码";
            }
        }, 1000);
        //封装数据
        var loginInfo1 = {"phoneCode": phoneCode};
        var urlll = baseUrl + "/login/sendPhoneCode";
        $.ajax({
            url: urlll,
            type: "post",
            data: loginInfo1,
            dataType: 'json',
            success: function (data) {
                if(data===undefined){
                    return  true;
                }
                if (data.errorMessages != null) {
                    $(".handler").css({'left': 0}).removeClass('handler_ok_bg').addClass('handler_bg');
                    $(".drag_bg").css({'width': 0});
                    $(".drag_text").text("拖动滑块验证").css({'color': '#808080'});
                    ifMove = true;
                    isMove = false;
                    document.getElementById("loginMsg1").innerText = data.errorMessages;
                    return false;
                }

            }

        });
        return true;
        document.getElementById("loginMsg1").innerText = "123";
    })

 /*  注册 */
    var urll = baseUrl + "/login/registered";
    $("#regist-btn").click(function () {
        var userName = $("#mobile-input").val();
        var passWord = $("#pwd-input").val();
        var phoneVerified = $("#code-input").val();
        if (userName.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("loginMsg1").innerText = "手机号不能为空";
            return;
        }
        if (passWord.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("loginMsg1").innerText = "请输入注册密码";
            return;
        }
        if (phoneVerified.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("loginMsg1").innerText = "请输入验证码";
            return;
        }
        var loginInfo = {"phoneCode": userName, "passWord": passWord, "phoneVerified": phoneVerified};

        $.ajax({
            url: urll,
            type: "post",
            data: loginInfo,
            dataType: 'json',
            success: function (data) {
                if (data.successMessage != null) {
                    window.location.href = 'login.html';
                }
                if (data.errorMessages != null) {
                    $(".handler").css({'left': 0}).removeClass('handler_ok_bg').addClass('handler_bg');
                    $(".drag_bg").css({'width': 0});
                    $(".drag_text").text("拖动滑块验证").css({'color': '#808080'});
                    ifMove = true;
                    isMove = false;
                    document.getElementById("loginMsg1").innerText = data.errorMessages;
                    return;
                }

            },
            error: function (e) {
                document.getElementById("loginMsg1").innerText = "注册异常，请联系客服";
                return;
            }
        });
    });


//==================
    $("#sm-pic2").click(function () {
        $("#sm-pic2").hide();
        $("#sm-pic").show();
        $("#regist").hide();
        $("#loginmain").show();
    })
    $("#sm-pic3").click(function () {
        $("#sm-pic3").hide();
        $("#sm-pic").show();
        $("#forget").hide();
        $("#loginmain").show();
    })
})

/*登陆*/
$(function () {
    var url = baseUrl + "/login/userLogin.do";
    $("#loginButton").click(function () {
        var userName = $("#username").val();
        var password = $("#password").val();
        if (userName.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("loginMsg").innerText = "用户名不能为空";
            return;
        }
        if (password.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("loginMsg").innerText = "请输入登录密码";
            return;
        }
        if (getCookie("verCode") != "Y") {
            document.getElementById("loginMsg").innerText = "请拖动验证码验证登录";
            return;
        }
        var loginInfo = {"userName": userName, "password": password};
        console.log(loginInfo);
        console.log(username);
        console.log(getCookie("verCode"));
        $.ajax({
            url: url,
            type: "post",
            data: loginInfo,
            dataType: 'json',
            success: function (data) {
                if (data.successMessage != null) {
                    window.location.href = 'main.html';
                }
                if (data.errorMessages != null) {
                    $(".handler").css({'left': 0}).removeClass('handler_ok_bg').addClass('handler_bg');
                    $(".drag_bg").css({'width': 0});
                    $(".drag_text").text("拖动滑块验证").css({'color': '#808080'});
                    ifMove = true;
                    isMove = false;
                    document.getElementById("loginMsg").innerText = data.errorMessages;
                    return;
                }

            },
            error: function (e) {
                document.getElementById("loginMsg").innerText = "登录异常，请联系客服";
                return;
            }
        });
    });


})

