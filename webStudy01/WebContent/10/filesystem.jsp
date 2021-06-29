<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<ul>

<li><span class="directory" path="/"><%=request.getContextPath() %></span></li>
</ul>
<script type="text/javascript">
$("body").on("click",".directory_open",function(){
	let me = $(this)
	me.attr("class","directory");
	me.siblings().remove();
	
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
			me.after(list)
			$.each(res,function(i,v){
				let li =$("<li>")
				let span =$("<span>")
				if(v.type=="dir"){
					span.attr("class","directory")
					span.attr("path",v.path)
				}
				span.text(v.name)
				li.append(span)
				list.append(li)
			})
		},
		error : function(xhr) {
			alert(xhr.status)
		}
	})
})

</script>
