/**
 * 
 */
$.customAlert = function(msg){
	alert(msg);
}

$.fn.sessionTimer = function(obj){
	if(!obj || !obj.timeout){
		throw "타이머를 구현하기 위해 필수 파라미터가 필요함"
		return
	}
	let a= this
	console.log(a)
	const TIMEOUT = obj.timeout
	const URL = obj.url ? obj.url : ""; 
	const NOBTN = obj.noBtn
	const YESBTN = obj.yesBtn
	const BOX = obj.box
	let initval =TIMEOUT-1
	let flag=false
	let inval=setInterval(function () {
		if(initval >60 || flag){
				BOX.hide()
			}else{
				BOX.show()
			}
			initval--
			let time = Math.floor(initval/60)+":"+(initval%60)
			a.text(time)
			if(initval <=0){
				clearInterval(inval)
			}
		},1000)
		
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
	
	return this
}