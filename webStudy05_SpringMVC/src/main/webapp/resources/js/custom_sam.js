/**
 * 
 */
$.customAlert = function(msg){
	alert(msg);
}
$.timeFormat=function(time){
	// 1:59
	let min = Math.floor(time / 60)
	let sec = time % 60
	return min + ":" + sec
	
}
function makeMessageArea(element){
	let messageArea = $("<div>").prop("id","messageArea")
			.append("세션을 연장하겠습니까?",
			$("<br>"),
			$("<input>").attr({
				"type":"button",
				"id":"yesBtn",
				"value":"예"
			})
			,$("<input>").attr({
				"type":"button",
				"id":"noBtn",
				"value":"아니오"
			})
			).on("click","input",function(){
				let id = $(this).prop("id");
				if(id == "yesBtn"){
					$.ajax({
						url : URL,
						method : "",
						dataType : "",
						success : function(res) {

						},
						error : function(xhr) {
							alert(xhr.status)
						}
					})
				}
			}).hide();
	element.after(messageArea)
	return messageArea
//	<div id="msgArea">
//	세션을 연장하겠습니까?<br>
//	<input type="button" id="yesBtn" value="예">
//	<input type="button" id="noBtn" value="아니오">
//</div>
}

$.fn.sessionTimer = function(obj){
	if(!obj || !obj.timeout){
		throw "타이머를 구현하기 위해 필수 파라미터가 필요함"
		return
	}
	let TIMERAREA= this
	const TIMEOUT = obj.timeout
	const URL = obj.url ? obj.url : ""; 
	const NOBTN = obj.noBtn
	const YESBTN = obj.yesBtn
	const BOX = obj.box
	let initval =-1
	let messageArea = null
	let timerJob=null
	
	function destory(){
		clearInterval(inval)
		location.reload()
	}
	function init(){
		initval = TIMEOUT-1
	
	let flag=false
	let inval=setInterval(function () {
		if(initval >60 || flag){
				BOX.hide()
			}else{
				BOX.show()
			}
			initval--
//			let time = Math.floor(initval/60)+":"+(initval%60)
			TIMERAREA.text($.timeFormat(initval))
			if(initval <=0){
				distroy();
			}
		},100)
		if(messageArea == null){messageArea = makeMessageArea(TIMERAREA)}
		setTimeout(function(){
			messageArea.show()
		})
		
	YESBTN.on("click",function () {
		$.ajax({
			url:URL,
 			method:"head",
 			success : function () {
 				initval=TIMEOUT
 			},
 			error : function (xhr) {
 			alert("에러코드 : "+xhr.status)
 			}
 		})	
	})
	
	NOBTN.on("click",function () {
		flag = true;
	})
	}
	init()
	return this
}