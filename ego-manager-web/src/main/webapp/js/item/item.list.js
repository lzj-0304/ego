var itemEditor, zTreeObj;
$(function () {
    //创建富文本编辑器
    itemEditor = EGO.createEditor("#itemForm [name=desc]");
    //初始化类目选择和图片上传器
    EGO.init({fun:function(node){}});
});

function queryItemByParams() {
    $("#itemList").datagrid("load",{

    });
}

function getSelectionsIds() {
    var itemList = $("#itemList");
    var sels = itemList.datagrid("getSelections");
    var ids = [];
    for (var i in sels) {
        ids.push(sels[i].id);
    }
    ids = ids.join(",");
    return ids;
}

/**
 * 上架商品
 */
function reshelfItem() {
    var ids = getSelectionsIds();
    if (ids.length == 0) {
        $.messager.alert('提示', '未选中商品!');
        return;
    }
    $.messager.confirm('确认', '确定上架ID为 ' + ids + ' 的商品吗？', function (r) {
        if (r) {
            var params = {"ids": ids};
            $.post("/item/reshelf", params, function (data) {
                if (data.status == 200) {
                    $.messager.alert('提示', '上架商品成功!', undefined, function () {
                        $("#itemList").datagrid("reload");
                    });
                }
            });
        }
    });
}

/**
 * 下架商品
 */
function instockItem() {
    var ids = getSelectionsIds();
    if (ids.length == 0) {
        $.messager.alert('提示', '未选中商品!');
        return;
    }
    $.messager.confirm('确认', '确定下架ID为 ' + ids + ' 的商品吗？', function (r) {
        if (r) {
            var params = {"ids": ids};
            $.post("/item/instock", params, function (data) {
                if (data.status == 200) {
                    $.messager.alert('提示', '下架商品成功!', undefined, function () {
                        $("#itemList").datagrid("reload");
                    });
                }
            });
        }
    });
}


/**
 * 删除商品
 */
function itemDelete() {
    var sels = $("#itemList").datagrid("getSelections");
    if (sels.length == 0) {
        $.messager.alert('提示', '未选中商品!');
        return;
    }
    $.messager.confirm('确认', '确定删除ID为 ' + getSelectionsIds() + ' 的商品吗？', function (r) {
        if (r) {
            var params = {"ids": getSelectionsIds()};
            $.post("/item/delete", params, function (data) {
                if (data.status == 200) {
                    $.messager.alert('提示', '删除商品成功!', undefined, function () {
                        $("#itemList").datagrid("reload");
                    });
                }
            });
        }
    });
}


/**
 * 富文本编辑器全局变量itemAddEditor
 * ztree树全局变量
 */

function openAddItemDialog() {
   openAddOrUpdateDlg("itemDialog","添加商品");
}


/**
 * 选择类目
 */
function selectItemCat() {
    loadItemCatInfo();
    openAddOrUpdateDlg("itemCatDlg","选择类目");
}

/**
 * 加载类目信息
 */
function loadItemCatInfo() {
    $.ajax({
        type:"post",
        url:"itemCat/all",
        dataType:"json",
        success:function (data) {
            // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                view:{
                    showLine: false
                    // showIcon: false
                },
                check: {
                    enable: true,
                    chkStyle:"radio",
                    chkboxType: { "Y": "ps", "N": "ps" }
                },
                callback: {
                    onCheck: zTreeOnCheck
                }
            };
            var zNodes =data;
            zTreeObj=$.fn.zTree.init($("#treeDemo"), setting, zNodes);
        }
    })
}

function zTreeOnCheck(event, treeId, treeNode) {
    var nodes= zTreeObj.getCheckedNodes(true);
    if(nodes.length>0) {
        $("#cid").val(treeNode.id);
        $("#itemCatName").html(treeNode.name);
    }else{
        $("#cid").val("");
        $("#itemCatName").html("");
    }
}



function openModifyItemDialog() {
    var ids = getSelectionsIds();
    if(ids.length == 0){
        $.messager.alert('提示','必须选择一个商品才能编辑!');
        return ;
    }
    if(ids.indexOf(',') > 0){
        $.messager.alert('提示','只能选择一个商品!');
        return ;
    }
    /**
     * 填充表单数据
     */
    var data = $("#itemList").datagrid("getSelections")[0];
    data.priceView = EGO.formatPrice(data.price);
    //完成data数据的回显
    $("#itemForm").form("load",data);
    /**
     * 商品描述信息 先清空原始描述信息 在设置新的描述信息
     */
    itemEditor.html("");
    // 加载商品描述，回显商品的描述信息
    $.getJSON('/item/desc/'+data.id,function(_data){
        //创建富文本编辑器
        itemEditor.html(_data.itemDesc);
    });

    /**
     * 类目回显
     * 图片回显
     */
    EGO.init({
        "pics" : data.image
    });
    openAddOrUpdateDlg("itemDialog","编辑商品");
}



//提交表单
function submitForm(){
    //有效性验证
    if(!$('#itemForm').form('validate')){
        $.messager.alert('提示','表单还未填写完成!');
        return ;
    }
    //取商品价格，单位为“分”
    $("#itemForm [name=price]").val(eval($("#itemForm [name=priceView]").val()) * 100);
    //同步文本框中的商品描述
    itemAddEditor.sync();
    //ajax的post方式提交表单
    var url="/item/save";
    var itemId = $("#itemId").val();
    if(null != itemId && "undefined" != itemId && itemId !=""){
        url = "/item/update";
    }
    $.post(url,$("#itemForm").serialize(), function(data){
        if(data.status == 200){
            $.messager.alert('提示','新增商品成功!');
            // 清除表单信息
            clearForm();
            $("#itemDialog").dialog("close");
            /**
             * 刷新表格数据
             */
            queryItemByParams();
        }
    });
}

/**
 * 清除表单信息
 */
function clearForm(){
    // 清除表单信息
    $('#itemForm').form('reset');
    // 清除类目信息
    $("#itemCatName").html("");
    // 清除富文本编辑框内容
    itemAddEditor.html('');
}
