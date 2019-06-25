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
    var ids = getSelectionsIds();
    if (ids.length == 0) {
        $.messager.alert('提示', '未选中商品!');
        return;
    }
    $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的商品吗？', function (r) {
        if (r) {
            var params = {"ids": ids};
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

function openAddItemDailog() {
    $(".tree-title:contains('新增商品')",parent.document).click();
}