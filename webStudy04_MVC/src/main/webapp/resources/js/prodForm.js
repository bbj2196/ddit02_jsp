/**
 * 
 */


$(function(){
	
console.log("stesset")
		let addForm = $("#addForm");
		$("#addBtn").on("click", function(eve) {
			eve.preventDefault();
			addForm.submit();
			return false;
		});
		addForm.validate()

		let prodBuyer = $("select[name='prodBuyer']")
		let prodLgu = $("select[name='prodLgu']").on("change", function(){
			let lgu = $(this).val();
			prodBuyer.find("option").hide();
			prodBuyer.find("option."+lgu).show();
			prodBuyer.find("option:first").show();
		}).change();

	
//	return this;
})