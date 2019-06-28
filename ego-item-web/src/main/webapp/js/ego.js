var TT = TAOTAO = {
	checkLogin : function(){
		var _ticket = $.cookie("EGO_TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://sso.ego.com/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到淘淘！<a href=\"javascript:TT.logout()\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	},
	logout:function(){
		$.ajax({
			url : "http://sso.ego.com/user/logout/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					document.location.reload();
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});