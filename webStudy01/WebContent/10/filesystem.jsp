<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<ul>

<li class="directory" path="/" ><%=request.getContextPath() %></li>
<li>assadsdasadasd</li>
<ul class="directory">
<li>assadsd1</li>
<li>assads2</li>
</ul>
</ul>
<script type="text/javascript">
$("body").on("click",".directory_open",function(){
	let me = $(this)
	me.attr("class","directory");
	me.children().remove();
	
})
$("body").on("click",".directory",function(){
	let me = $(this)
	me.attr("class","directory_open");
	let name = me.text();
	let path = me.attr("path");
	console.log(me.children());
	
	$.ajax({
		url : "<%=request.getContextPath()%>/fileList.do",
		data : "path="+path,
		method : "get",
		dataType : "json",
		success : function(res) {
			let list = $("<ul>")
			me.append(list)
			$.each(res,function(i,v){
				let file =$("<li>")
				if(v.type=="dir"){
					file.attr("class","directory")
					file.attr("path",v.path)
				}
				file.text(v.name)
				list.append(file)
			})
		},
		error : function(xhr) {
			alert(xhr.status)
		}
	})
})

</script>
