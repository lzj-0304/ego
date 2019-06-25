<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="common.jsp"/>
<table class="easyui-datagrid" id="itemList"
       pagination="true" rownumbers="true"  toolbar="#tb" url="/item/list" method="get" pageSize=20  fit="true">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">商品ID</th>
            <th data-options="field:'title',width:200">商品标题</th>
            <th data-options="field:'cid',width:100">叶子类目</th>
            <th data-options="field:'sellPoint',width:200">卖点</th>
            <th data-options="field:'price',width:70,align:'right'" formatter="EGO.formatPrice">价格</th>
            <th data-options="field:'num',width:70,align:'right'">库存数量</th>
            <th data-options="field:'barcode',width:100">条形码</th>
            <th data-options="field:'status',width:60,align:'center'" formatter="EGO.formatItemStatus">状态</th>
            <th data-options="field:'created',width:130,align:'center'" formatter="EGO.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center'" formatter="EGO.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>

<div id="tb">
    <a href="javascript:openAddItemDailog()" class="easyui-linkbutton" iconCls="icon-save" plain="true">新增</a>
    <a href="javascript:openModifyItemDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    <a href="javascript:itemDelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    <a href="javascript:instockItem()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">下架</a>
    <a href="javascript:reshelfItem()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">上架</a>
    <br/>
    商品名:<input type="text" id="roleName"/>
    状态:<input id="time" type="text" class="easyui-datebox" ></input>
    <a href="javascript:queryRolesByParams()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
</div>

<script type="text/javascript" src="js/item/item.list.js"></script>

