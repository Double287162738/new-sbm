$(function () {
    $("#shumaClick").click(function () {
        $("#shuma").show();
        $("#shuji").hide();
        $("#richang").hide();
        $("#jianzhi").hide();
        $("#shumaLi").addClass("active");
        $("#shujiLi").removeClass("active");
        $("#richangLi").removeClass("active");
        $("#jianzhiLi").removeClass("active");
    });
    $("#shujiClick").click(function () {
        $("#shuji").show();
        $("#shuma").hide();
        $("#richang").hide();
        $("#jianzhi").hide();
        $("#shujiLi").addClass("active");
        $("#shumaLi").removeClass("active");
        $("#richangLi").removeClass("active");
        $("#jianzhiLi").removeClass("active");

    });
    $("#richangClick").click(function () {
        $("#richang").show();
        $("#shuji").hide();
        $("#shuma").hide();
        $("#jianzhi").hide();
        $("#richangLi").addClass("active");
        $("#shumaLi").removeClass("active");
        $("#shujiLi").removeClass("active");
        $("#jianzhiLi").removeClass("active");
    });
    $("#jianzhiClick").click(function () {
        $("#jianzhi").show();
        $("#richang").hide();
        $("#shuji").hide();
        $("#shuma").hide();
        $("#jianzhiLi").addClass("active")
        $("#richangLi").removeClass("active");
        $("#shumaLi").removeClass("active");
        $("#shujiLi").removeClass("active");
    })
})
