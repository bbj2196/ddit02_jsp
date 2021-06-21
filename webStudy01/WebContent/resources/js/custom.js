/**
 * 
 */
$.customAlert = function(msg){
	alert(msg);
}

$.fn.sessionTimer = function(obj){
	let a= this
	console.log(a)
	const TIMEOUT = obj.timeout
	const URL = obj.url
	const NOBTN = obj.noBtn
	const YESBTN = obj.yesBtn
	const BOX = obj.box
	let initval =TIMEOUT
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