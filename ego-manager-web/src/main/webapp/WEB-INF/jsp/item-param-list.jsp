<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="common.jsp"/>
<link rel="stylesheet" href="js/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="js/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="js/zTree_v3-3.5.32/js/jquery.ztree.excheck.min.js"></script>
<table class="easyui-datagrid" id="itemParamList"
       pagination="true" rownumbers="true"  toolbar="#tb" url="/itemParam/list" method="get" pageSize=10  fit="true">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">ID</th>
        	<th data-options="field:'itemCatId',width:80">商品类目ID</th>
        	<th data-options="field:'cname',width:100">商品类目</th>
            <th data-options="field:'paramData',width:300">规格(只显示分组名称)</th>
            <th data-options="field:'created',width:130,align:'center',formatter:EGO.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:EGO.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>


<!--
表格工具栏
-->
<div id="tb">
    <a href="javascript:openAddItemParamDialog()" class="easyui-linkbutton" iconCls="icon-save" plain="true">新增</a>
    <a href="javascript:openModifyItemParamDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    <a href="javascript:itemParamDelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    <br/>
    商品名:<input type="text" id="roleName"/>
    状态:<input id="time" type="text" class="easyui-datebox" ></input>
    <a href="javascript:queryItemParamByParams()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
</div>




<div id="itemParamDialog" class="easyui-dialog"
     data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:80%;height:80%;padding:10px;">
    <table cellpadding="5" style="margin-left: 30px" id="itemParamAddTable" class="itemParam">
        <tr>
            <td>商品类目:</td>
            <td><a href="javascript:selectItemCat()" class="easyui-linkbutton selectItemCat">选择类目</a><span id="itemCatName"></span>
                <input type="hidden" id="cid" name="cid" style="width: 280px;"></input>
            </td>
        </tr>
        <tr class="hide addGroupTr">
            <td>规格参数:</td>
            <td>
                <ul>
                    <li><a href="javascript:void(0)" class="easyui-linkbutton addGroup">添加分组</a></li>
                </ul>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <a href="javascript:void(0)" class="easyui-linkbutton submit">提交</a>
                <a href="javascript:void(0)" class="easyui-linkbutton close">关闭</a>
            </td>
        </tr>
    </table>


    <div  class="itemParamAddTemplate" style="display: none;">
        <li class="param">
            <ul>
                <li>
                    <input class="easyui-textbox" style="width: 150px;" name="group"/>&nbsp;
                    <a href="javascript:void(0)" class="easyui-linkbutton addParam"  title="添加参数" data-options="plain:true,iconCls:'icon-add'"></a>
                </li>
                <li>
                    <span>|-------</span><input  style="width: 150px;" class="easyui-textbox" name="param"/>&nbsp;
                    <a href="javascript:void(0)" class="easyui-linkbutton delParam" title="删除" data-options="plain:true,iconCls:'icon-cancel'"></a>
                </li>
            </ul>
        </li>
    </div>
</div>


<div id="itemCatDlg" class="easyui-dialog" title="选择类目" closed="true"  style="width: 500px;height:450px">
    <ul id="treeDemo" class="ztree"></ul>
</div>








<script type="text/javascript" src="js/item/item.param.list.js"></script>