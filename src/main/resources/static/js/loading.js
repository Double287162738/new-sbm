//开启loading
function openLoading() {
    $("body").append(
        '<div class="owner_loading">'+
        '<div class="loader">'+
        '<div class="loader-inner line-scale-pulse-out">'+
        '<div></div>'+
        '<div></div>'+
        '<div></div>'+
        '<div></div>'+
        '<div></div>'+
        '</div>'+
        '</div>'+
        '</div>'
    );
}

//关闭loading
function closeLoading() {
    $(".owner_loading").remove();
}

//开启alert
function openAlert(content) {
    $("body").append(
    '<div class="owner_alert">'+
        '<div class="owner_alert_part">'+
            '<div class="owner_alert_content">'+
                '<p class="content_detail">'+content+'</p>'+
            '</div>'+
            '<div class="owner_alert_btn">'+
                '<button class="owner_alert_ok" onclick="closeAlert()">已阅</button>'+
            '</div>'+
        '</div>'+
    '</div>'
    );
}

//关闭loading
function closeLoading() {
    $(".owner_loading").remove();
}

//关闭alert
function closeAlert(){
    $(".owner_alert").remove();
}