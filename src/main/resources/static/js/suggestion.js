var score=5;
$(function(){
	
	$(".star").on('click','span',function(){
		var index = $(".star span").index(this);
		score=index+1;
		$(".star span").each(function(){
			var clickIndex = $(".star span").index(this);
			if(clickIndex<=index){
				$(this).css("color","#fb775d");
			}else{
				$(this).css("color","#999");
			}
		});
	});
})

function suggestionSub(){
	var suggestion ={"suggestionScore":score,"suggestionDetail":$(".suggestion").val()};
    $.ajax({
        url: baseUrl + "/my-sbm/suggestion/addSuggestion.do",
        type: "post",
		data:suggestion,
        dataType: 'json',
        success: function (data) {
            $('#myModal').modal('show');
        },
        error: function (e) {
            $('#myModal').modal('show');
        }
    });
}


function closeSub(){
    window.location.href = 'main.html';
}

