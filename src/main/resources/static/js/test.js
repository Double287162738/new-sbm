$(function () {
    var data = {"keyword": "12", "currentPage": "2"};
//	$("#ok").click(function(){
//		$.ajax({
//            url:"http://localhost:8080/my-sbm/sousou/sougoods.do",
//            type:"post",
//            data:data,
//            dataType:'json',
//            success:function(data){
//            	if(data.successMessage!=null){
//            		window.location.href = 'main.html';
//            	}
//            	if(data.errorMessages!=null){
//            	}
//            	
//            },
//            error:function(e){
//    			return;
//            }
//        });  
//    });
    $("#ok").click(function () {
        window.location.href = 'home.html?keyword=' + $("#input").val();
    })
})
