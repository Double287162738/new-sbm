$(function(){
});


function noPicCollapse(){
	document.getElementById("topic-no-pic-detail").innerHTML='';
	var s='1.再喜欢一个人也不要把爱表达的太满 男人就是恃宠而骄的大猪蹄子，不要让他以为你离不开他。'+
					'2.他喜欢你可以真的很喜欢你，他不爱你时的绝情也是真的，你再多的挽留也留不住他，反而让他看不起你。'+
					'<a class="topic-no-pic-read-all" onclick="topicNoPicReadAll()">阅读全文</a>';
	document.getElementById("topic-no-pic-detail").innerHTML=s;
}
function havePicCollapse(){
	document.getElementById("topic-have-pic-detail").innerHTML='';
		var s = 
		'<div class="topic-pic"><img src="../img/2.jpg"/></div>'+
		'<div class="topic-detail">'+
			'上个图 看一下赛程 法国队轻松吗？他的对手是阿根廷，乌拉圭，'+
			'比利时。一个比一个强大，都是夺冠的热门。克罗地亚伟大吗？'+
			'，俄罗斯，英格兰。？？大家赞誉的克罗地亚'+
			'不过是因为实力不够，导致自己场场加时罢了。拼命也是真的，'+
			'那法国的坎特不拼吗？马图伊迪不拼吗？真的是服了一些人'+
			'的唯弱者论了。'+
			'<a id="topic-have-pic-read-all" onclick="topicHavePicReadAll()">阅读全文</a>'+
		'</div>';
		document.getElementById("topic-have-pic-detail").innerHTML=s;
}

function topicNoPicReadAll(){
	document.getElementById("topic-no-pic-detail").innerHTML='';
		var s='我的唯一忠告就是，找个人品好的男朋友。<br>'+
				'一个人品好，可靠的人，即使没谈过恋爱也会认真学，会努力照顾你<br>'+
				'。这不是因为你优秀，是因为他觉得这是他的责任。责任感这个东西太重要了，<br>'+
				'而且体现在方方面面，他对工作什么态度，对别人什么态度，对父母什么态度，<br>'+
				'对自己什么态度。能够始终一以贯之，有做人的清晰底线的人才值得依靠。<br>'+
				'那些说他虽然对别人没什么礼貌但特别宠我；虽然他对工作懒懒散散，但对我一百个殷勤；<br>'+
				'虽然对父母总是冷眼呵斥，但我们感情很好。这不叫嫁给爱情，这叫猪油蒙眼。<a onclick="noPicCollapse()">收起</a>';
				document.getElementById("topic-no-pic-detail").innerHTML=s;
}


function topicHavePicReadAll(){
	document.getElementById("topic-have-pic-detail").innerHTML='';
		var s = "<img style='max-width:100%' src='../img/2.jpg'/>"+
		'<div class="topic-detail-br">'+
		"骚白刚刚结束的和wfd的撞车，被血虐。<br><br><br>"+
		"完全看不出来这就是三百连胜的水平，wf一只刚刚降级的队伍，选择了一个并不强势的阵容，把骚白锤到第二个人头都拿不到。<br><br>"+
		"差距不是一句大能概括的，骚白直播中一直强调自己的车队有多少多少个职业选手，自己的车队可以和职业战队训练，现在看来应该是给职业战队送分增加信心的。<br><br>"+
		"总而言之，可以说明一点。他所谓的无敌至少是建立在不遇到次级联赛及以上的队伍，打一打平常的路人队和一般的代练队还可以，一旦遇到实力较强的人车队/战队，原形毕露。<br><br>"+
		"那么问题来了，为什么三百多把都没有遇到呢。可能就是运气好吧（滑稽）<a onclick='havePicCollapse()'>收起<a>"+
		'</div>';
		document.getElementById("topic-have-pic-detail").innerHTML=s;
}
function reply(obj){
	obj.parentNode.parentNode.innerHTML='<input placeholder="回复"/>'+
						'<button class="btn btn-default" onclick="cancelReply(this)">取消</button>'+
						'<button class="btn btn-primary">评论</button>';
}
function cancelReply(obj){
	obj.parentNode.innerHTML='<div><img src="../img/topic-hf.png" /><a onclick="reply(this)">回复</a></div>';
}
