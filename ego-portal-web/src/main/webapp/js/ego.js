var TT = TAOTAO = {
		checkLogin : function(){
			//获取本地保存的sso_token的值
			var _ticket = $.cookie("sso_token");
			if(!_ticket){
				return ;
			}
			$.ajax({
				//发送ajax请求，获得用户的登录状态
				url : "http://localhost:8083/user/token/" + _ticket,
				dataType : "jsonp",
				type : "GET",
				success : function(data){
					if(data.status == 200){
						var username = data.data.username;
						var html = username + "，欢迎来到淘淘！" +
								"<a href=\"javascript:TT.logout()\" " +
								"class=\"link-logout\">[退出]</a>";
						$("#loginbar").html(html);
					}
				}
			});
		},
		
		logout : function (){
			//获取本地保存的sso_token的值
			var _ticket = $.cookie("sso_token");
			if(!_ticket){
				return ;
			}
			
			$.ajax({
				//发送ajax请求，获得用户的登录状态
				url : "http://localhost:8083/user/logout/" + _ticket,
				dataType : "jsonp",
				type : "GET",
				success : function(data){
					if(data.status == 200){
						 location.href='http://localhost:8081';
					}
				}
			});
			
		}



	}

	$(function(){
		// 查看是否已经登录，如果已经登录查询登录信息
		TT.checkLogin();
	});