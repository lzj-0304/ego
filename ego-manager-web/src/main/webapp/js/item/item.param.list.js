function getSelectionsIds() {
    var itemList = $("#itemParamList");
    var sels = itemList.datagrid("getSelections");
    var ids = [];
    for (var i in sels) {
        ids.push(sels[i].id);
    }
    ids = ids.join(",");
    return ids;
}

/**
 * 打开添加商品规格窗口
 */
function openAddItemParamDialog() {
    openAddOrUpdateDlg("itemParamDialog","添加商品规格");
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
        /**
         * 加入cid 是否添加规格记录判断
         */
        var cid =treeNode.id;
        $.post("itemParam/query/"+cid,{},function(data){
            if(null != data.data){
                $.messager.alert('提示','该分类商品规格已添加,不可重复添加!');
                treeNode.checked=false;
            }else{
                $("#cid").val(cid);
                $("#itemCatName").html(treeNode.name);
            }
        });
    }else{
        $("#cid").val("");
        $("#itemCatName").html("");
    }
}


/**
 * 添加分组
 */
$(".addGroup").click(function(){
    var temple = $(".itemParamAddTemplate li").eq(0).clone();
    $(this).parent().parent().append(temple);
    temple.find(".addParam").click(function(){
        var li = $(".itemParamAddTemplate li").eq(2).clone();
        li.find(".delParam").click(function(){
            $(this).parent().remove();
        });
        li.appendTo($(this).parentsUntil("ul").parent());
    });
    temple.find(".delParam").click(function(){
        $(this).parent().remove();
    });
});


$("#itemParamAddTable .submit").click(function(){
    var params = [];
    var groups = $("#itemParamAddTable [name=group]");
    groups.each(function(i,e){
        var p = $(e).parentsUntil("ul").parent().find("[name=param]");
        var _ps = [];
        p.each(function(_i,_e){
            var _val = $(_e).siblings("input").val();
            if($.trim(_val).length>0){
                _ps.push(_val);
            }
        });
        var _val = $(e).siblings("input").val();
        if($.trim(_val).length>0 && _ps.length > 0){
            params.push({
                "group":_val,
                "params":_ps
            });
        }
    });
    var url = "/itemParam/save/"+$("#itemParamAddTable [name=cid]").val();
    $.post(url,{"paramData":JSON.stringify(params)},function(data){
        if(data.status == 200){
            $.messager.alert('提示','新增商品规格成功!',undefined,function(){
                closeDlgData("itemParamDialog");
                $("#itemParamList").datagrid("reload");
            });
        }
    });
});


$("#itemParamAddTable .close").click(function () {
   closeDlgData("itemParamDialog");
    $("#cid").val("");
    $("#itemCatName").html("");
});










/**
 * 打开商品规格编辑窗口
 */
function openModifyItemParamDialog() {
    var ids = getSelectionsIds();
    if(ids.length == 0){
        $.messager.alert('提示','必须选择一个商品才能编辑!');
        return ;
    }
    if(ids.indexOf(',') > 0){
        $.messager.alert('提示','只能选择一个商品!');
        return ;
    }
    openAddOrUpdateDlg("itemParamDialog","编辑商品规格");
}


function itemParamDelete() {
    var sels = $("#itemParamList").datagrid("getSelections");
    if (sels.length == 0) {
        $.messager.alert('提示', '未选中商品规格!');
        return;
    }
    $.messager.confirm('确认', '确定删除ID为 ' + getSelectionsIds() + ' 的商品规格吗？', function (r) {
        if (r) {
            var params = {"ids": getSelectionsIds()};
            $.post("/itemParam/delete", params, function (data) {
                if (data.status == 200) {
                    $.messager.alert('提示', '删除商品成功!', undefined, function () {
                        $("#itemParamList").datagrid("reload");
                    });
                }
            });
        }
    });
}