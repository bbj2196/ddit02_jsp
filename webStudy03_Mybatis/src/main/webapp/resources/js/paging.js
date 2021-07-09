/**
 * 
 * $("#searchForm).paging({
 *  		pagingArea : "#pagingArea",
 *  		pageLink : ".pageLink",
 *  		searchUI : "#searchUI",
 *  		pageKey : "page",
 *   		pageParam : "page",
 *   		btnSelector : "#searchBtn"
 * });
 * 
 */

$.fn.paging=function(param){
	let searchForm=this;
	param = param?param : {};
	const PAGINGAREA = $(param.pagingArea?param.pagingArea:"#pagingArea");
	const PAGINGLINK = param.pageLink ? param.pageLink : ".pageLink";
	const SEARCHUI = $(param.searchUI ? param.searchUI : "#searchUI");
	const PAGEKEY = param.pageKey ? param.pageKey : "page";
	const PAGEPARAM = param.pageParam ? param.pageParam : "page";
	const BTNSELECTOR = param.btnSelector ? param.btnSelector : "#searchBtn";
	PAGINGAREA.on("click",PAGINGLINK,function(eve){
		eve.preventDefault();
		let page = $(this).data(PAGEKEY);
		searchForm.find("[name='"+PAGEPARAM+"']").val(page)
		searchForm.submit()
		return false;
	})
	
	SEARCHUI.on("click",BTNSELECTOR,function(){
		let inputs = SEARCHUI.find(":input[name]")
		$(inputs).each(function(idx,input){
			let name = this.name
			let value = $(this).val();
			searchForm.find("[name='"+name+"']").val(value)
			
		})
		searchForm.submit()
	})
	return this;
}