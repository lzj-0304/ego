<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="common.jsp"/>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="js/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" src="js/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="js/zTree_v3-3.5.32/js/jquery.ztree.excheck.min.js"></script>
<!--
  表格数据
-->
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

<!--
  表格工具栏
-->
<div id="tb">
    <a href="javascript:openAddItemDialog()" class="easyui-linkbutton" iconCls="icon-save" plain="true">新增</a>
    <a href="javascript:openModifyItemDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
    <a href="javascript:itemDelete()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    <a href="javascript:instockItem()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">下架</a>
    <a href="javascript:reshelfItem()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">上架</a>
    <br/>
    商品名:<input type="text" id="roleName"/>
    状态:<input id="time" type="text" class="easyui-datebox" ></input>
    <a href="javascript:queryItemByParams()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
</div>




<!--
   添加与编辑商品窗口div
-->
<div id="itemDialog" class="easyui-dialog"
     data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:80%;height:80%;padding:10px;">
    <form id="itemForm" class="itemForm" method="post">
        <input type="hidden" id="itemId" name="id"/>
        <table cellpadding="5">
            <tr>
                <td>商品类目:</td>
                <td>
                    <a href="javascript:selectItemCat()" class="easyui-linkbutton">选择类目</a><span id="itemCatName"></span>
                    <input type="hidden" id="cid" name="cid" style="width: 280px;"></input>
                </td>
            </tr>
            <tr>
                <td>商品标题:</td>
                <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 280px;"></input></td>
            </tr>
            <tr>
                <td>商品卖点:</td>
                <td><input class="easyui-textbox" name="sellPoint" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td>
            </tr>
            <tr>
                <td>商品价格:</td>
                <td><input class="easyui-numberbox" type="text" name="priceView" data-options="min:1,max:99999999,precision:2,required:true" />
                    <input type="hidden" name="price"/>
                </td>
            </tr>
            <tr>
                <td>库存数量:</td>
                <td><input class="easyui-numberbox" type="text" name="num" data-options="min:1,max:99999999,precision:0,required:true" /></td>
            </tr>
            <tr>
                <td>条形码:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="barcode" data-options="validType:'length[1,30]'" />
                </td>
            </tr>
            <tr>
                <td>商品图片:</td>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
                    <input type="hidden" name="image"/>
                </td>
            </tr>
            <tr>
                <td>商品描述:</td>
                <td>
                    <textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>
                </td>
            </tr>

            <tr class="params hide">
                <td>商品规格:</td>
                <td>

                </td>
            </tr>
        </table>
        <input type="hidden" name="itemParams"/>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
    </div>


</div>

<div id="itemCatDlg" class="easyui-dialog" title="选择类目" closed="true"  style="width: 500px;height:450px">
    <ul id="treeDemo" class="ztree"></ul>
</div>

<script type="text/javascript" src="js/item/item.list.js"></script>

