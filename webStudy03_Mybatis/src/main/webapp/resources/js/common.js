/**
 * 
 */

$(".controlBtn").on("click",function(){
	let path=$(this).data("gopage");
	location.href = path;
})