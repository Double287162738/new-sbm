$(function () {
    $("#registButton").click(function () {
        $("#loginmain").hide();
        $("#sm-pic").hide();
        $("#sm-pic2").show();
        $("#regist").show();
        $("#mobile-input").val("");
        $("#pwd-input").val("");
        document.getElementById("registMsg").innerText = "";
    })
    var locationUrl = window.location.href;
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
    $("#forgetB").click(function () {
        $("#loginmain").hide();
        $("#sm-pic").hide();
        $("#sm-pic3").show();
        $("#forget").show();
    });
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

    //注册获取手机验证码
    $("#get-code").click(function () {
        getYzmCode("regist");
    });
    //修改密码获取手机验证码
    $("#get_code_init_pwd").click(function () {
        getYzmCode("initPwd");
    });


    //获取验证码
    function getYzmCode(type) {
        openLoading();
        //校验是否位手机号
        var phoneCode;
        var pwd;
        if(type=='regist'){
            phoneCode = $("#mobile-input").val();
            pwd = $("#pwd-input").val().replace(/\s*/g,"");
        }else {
            phoneCode = $("#mobile-update").val();
            pwd = $("#pwd-update").val().replace(/\s*/g,"");
        }
        var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
        if (!myreg.test(phoneCode)) {
            if(type=='regist'){
                document.getElementById("registMsg").innerText ="请输入正确的手机号";
            }else {
                document.getElementById("initPwdMsg").innerText ="请输入正确的手机号";
            }
            closeLoading();
            return false;
        }else {
            document.getElementById("registMsg").innerText ="";
        }
        if(pwd==null || pwd.trim()=="" || pwd.trim()==null){
            if(type=='regist'){
                document.getElementById("registMsg").innerText ="请输入密码";
            }else {
                document.getElementById("initPwdMsg").innerText ="请输入密码";
            }
            closeLoading();
            return false;
        }
        var operateType;
        if(type=='regist'){
            operateType = 'regist';
        }else{
            operateType = 'initPwd';
        }
        var registPhone = {"phoneCode": phoneCode,"type":operateType};
        $.ajax({
            url: baseUrl + "/login/sendPhoneCode.do",
            type: "post",
            data: registPhone,
            dataType: 'json',
            success: function (data) {
                if(!data['result']){
                    if(type=='regist'){
                        document.getElementById("registMsg").innerText = data['errorMessages'];
                    }else{
                        document.getElementById("initPwdMsg").innerText = data['errorMessages'];
                    }
                    closeLoading();
                }else{
                    if(type=='regist'){
                        document.getElementById("registMsg").innerText = data['successMessage'];
                    }else{
                        document.getElementById("initPwdMsg").innerText = data['successMessage'];
                    }
                    getYzmInterval(type);
                    closeLoading();
                }
            },error:function () {
                if(type=='regist'){
                    document.getElementById("registMsg").innerText = '服务异常';
                }else{
                    document.getElementById("initPwdMsg").innerText = '服务异常';
                }

                closeLoading();
            }
        });
    }

    //验证码计时
    function getYzmInterval(type) {
        //设置验证码时间
        var time = 59;
        var btm;
        if(type=='regist'){
            btn = document.getElementById("get-code");
        }else {
            btn = document.getElementById("get_code_init_pwd");
        }
        btn.setAttribute("disabled", true);
        btn.innerText = "重新获取(59s)";
        timer = setInterval(function () {
            time--;
            if (time >= 1) {
                btn.innerText = "重新获取" + "(" + time + ")";
            } else {
                clearInterval(timer);
                btn.disabled = false;
                btn.innerText = "获取验证码";
            }
        }, 1000);
    }

    //注册
    $("#regist-btn").click(function () {
        var userName = $("#mobile-input").val();
        var passWord = $("#pwd-input").val();
        var phoneVerified = $("#code-input").val();
        if (userName.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("registMsg").innerText = "手机号不能为空";
            return;
        }
        if (passWord.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("registMsg").innerText = "请输入注册密码";
            return;
        }
        if (phoneVerified.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("registMsg").innerText = "请输入验证码";
            return;
        }
        var loginInfo = {"phoneCode": userName, "passWord": passWord, "phoneVerified": phoneVerified};

        $.ajax({
            url: baseUrl + "/login/registered.do",
            type: "post",
            data: loginInfo,
            dataType: 'json',
            success: function (data) {
                if (data['successMessage'] != null) {
                    $('#sm-pic2').click();
                    $("#mobile-input").val("");
                    $("#pwd-input").val("");
                    $("#code-input").val("");
                    document.getElementById("get-code").setAttribute("disabled", true);
                    document.getElementById("get-code").innerText = "获取验证码";
                    document.getElementById("loginMsg").innerText = "注册成功";
                }else {
                    document.getElementById("registMsg").innerText = data['errorMessages'];
                }
            },
            error: function (e) {
                document.getElementById("registMsg").innerText = "注册异常，请联系客服";
                return;
            }
        });
    });
    //修改密码
    $("#change_pwd").click(function () {
        var userName = $("#mobile-update").val();
        var passWord = $("#pwd-update").val();
        var phoneVerified = $("#code-update").val();
        if (userName.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("initPwdMsg").innerText = "手机号不能为空";
            return;
        }
        if (passWord.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("initPwdMsg").innerText = "请输入注册密码";
            return;
        }
        if (phoneVerified.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("initPwdMsg").innerText = "请输入验证码";
            return;
        }
        var data = {"phoneCode": userName, "passWord": passWord, "phoneVerified": phoneVerified};
        $.ajax({
            url: baseUrl + "/login/forgetPassword.do",
            type: "post",
            data: data,
            dataType: 'json',
            success: function (data) {
                if (data['successMessage'] != null) {
                    $('#sm-pic3').click();
                    $("#mobile-update").val("");
                    $("#pwd-update").val("");
                    $("#code-update").val("");
                    document.getElementById("get_code_init_pwd").setAttribute("disabled", true);
                    document.getElementById("get_code_init_pwd").innerText = "获取验证码";
                    document.getElementById("loginMsg").innerText = "修改密码成功";
                }else {
                    document.getElementById("initPwdMsg").innerText = data['errorMessages'];
                }
            },
            error: function (e) {
                document.getElementById("initPwdMsg").innerText = "修改密码异常，请联系客服";
                return;
            }
        });
    });


    //登录
    $("#loginButton").click(function () {
        openLoading();
        var userName = $("#username").val();
        var password = $("#password").val();
        if (userName.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("loginMsg").innerText = "用户名不能为空";
            closeLoading();
            return;
        }
        if (password.replace(/(^\s+)|(\s+$)/g, "") == "") {
            document.getElementById("loginMsg").innerText = "请输入登录密码";
            closeLoading();
            return;
        }
        var loginInfo = {"userName": userName, "password": password};
        $.ajax({
            url: baseUrl + "/login/userLogin.do",
            type: "post",
            data: loginInfo,
            dataType: 'json',
            success: function (data) {
                if (data.successMessage != null) {
                    closeLoading();
                    window.location.href = 'main.html';
                }else{
                    closeLoading();
                    document.getElementById("loginMsg").innerText = data.errorMessages;
                }
            },
            error: function (e) {
                document.getElementById("loginMsg").innerText = "登录异常，请联系客服";
                closeLoading();
                return;
            }
        });
    });
})


