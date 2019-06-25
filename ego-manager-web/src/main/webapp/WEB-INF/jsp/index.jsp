<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="common.jsp"/>
<title></title>Ego商城后台管理系统</title>
</head>
<body class="easyui-layout">
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>商品管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'item-add'}">新增商品</li>
	         		<li data-options="attributes:{'url':'item-list'}">查询商品</li>
	         		<li data-options="attributes:{'url':'item-param-list'}">规格参数</li>
	         	</ul>
         	</li>
         	<li>
         		<span>网站内容管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'content/content-category'}">内容分类管理</li>
	         		<li data-options="attributes:{'url':'content/content'}">内容管理</li>
	         	</ul>
         	</li>
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页">
		    </div>
		</div>
    </div>
	<div region="south" align="center" style="height: 30px">
		上海尚学堂智能科技有限公司 的icp备案号 （沪ICP备16053543号）
	</div>
<script type="text/javascript" src="js/index.js">
</script>
</body>
</html>