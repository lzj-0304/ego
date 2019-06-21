<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <jsp:include page="common.jsp"/>
    <title>Ego | 后台管理</title>
    <script type="text/javascript" src="../js/main.js"></script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 78px;background-color: #E0ECFF">
    <table style="padding: 5px" width="100%">
        <tr>
            <td width="50%">
                易购商城LOGO
            </td>
            <td valign="bottom" align="right" width="50%">
                <font size="3">&nbsp;&nbsp;<strong>欢迎：</strong>${(user.userName)}</font>
            </td>
        </tr>
    </table>
</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="padding-top: 100px"><font color="blue"
                                                                 size="10">
                欢迎使用易购商城后台管理系统</font></div>
        </div>
    </div>
</div>
<div region="west" style="width: 200px" title="导航菜单" split="true">
    <div class="easyui-accordion" data-options="fit:true,border:false">
        <div title="商品管理" data-options="selected:true,iconCls:'icon-yxgl'" style="padding: 10px">
            <a href="javascript:openTab('新增商品','sale_chance/index','icon-yxjhgl')"
               class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-yxjhgl'" style="width: 150px">新增商品</a>
            <a href="javascript:openTab('查询商品','sale_chance/index?state=1','icon-khkfjh')"
               class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">查询商品</a>
            <a href="javascript:openTab('规格参数','sale_chance/index?state=1','icon-khkfjh')"
               class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khkfjh'" style="width: 150px">规格参数</a>
        </div>
        <div title="网站内容管理"  data-options="iconCls:'icon-khgl'" style="padding:10px;">
            <a href="javascript:openTab('内容分类管理','customer/index','icon-khxxgl')"
               class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khxxgl'" style="width: 150px;">内容分类管理</a>
            <a href="javascript:openTab('内容管理','customer_loss/index','icon-khlsgl')"
               class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-khlsgl'" style="width: 150px;">内容管理</a>
        </div>
    </div>
</div>
<div region="south"  style="height:70px;background-color: #E0ECFF"
     align="center">
    版本所有 上海尚学堂 <a href="http://www.shsxt.com" target="_blank">www.shsxt.com</a>(2016-2026)<br/>
    上海尚学堂地址：上海市松江区荣乐东路2369弄45号绿地伯顿大厦2层 咨询电话：021-67690939<br/>
	 上海尚学堂智能科技有限公司 的icp备案号 （沪ICP备16053543号）
</div>
</body>
</html>