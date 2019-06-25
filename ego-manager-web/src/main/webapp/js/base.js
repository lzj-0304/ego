/**
 * Created by lp on 2018/1/11.
 */
/**
 * 基本crud 通用的js 代码
 */
/**
 * 打开对话框
 * @param dlgId
 * @param title
 */
function  openAddOrUpdateDlg(dlgId,title) {
    $("#"+dlgId).dialog("open").dialog("setTitle",title);
}

function  closeDlgData(dlgId) {
    $("#"+dlgId).dialog("close");
}


/**
 * 保存与更新方法封装
 * @param formId
 * @param saveOrUpdateUrl
 * @param dialogId
 * @param searchByParams
 */
function saveOrUpdateData(formId,saveOrUpdateUrl,dialogId,searchByParams) {
    $("#"+formId).form("submit",{
        url:saveOrUpdateUrl,
        onSubmit:function(){
            return $("#"+formId).form("validate");
        },
        success:function (data) {
            /**
             * data 为原始的json 字符串
             *   需要转换为js 对象
             */
            data=JSON.parse(data);
            if(data.code==200){
                closeDlgData(dialogId);
                searchByParams();
            }else{
                $.messager.alert("来自crm",data.msg,"error");
            }
        }
    })
}


function  openModifyDialog(dataGrdidId,formId,dialogId,title) {
    var rows=$("#"+dataGrdidId).datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请选择一条记录进行更新!");
        return ;
    }
    if(rows.length>1){
        $.messager.alert("来自crm","只能选择一条记录进行更新!");
        return ;
    }

    /**
     * 更新操作
     */
    $("#"+formId).form("load",rows[0]);//填充表单数据  数据回显
    openAddOrUpdateDlg(dialogId,title);
}


function deleteData(dataGridId,deleteUrl,searchByParams) {
    var rows=$("#"+dataGridId).datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请至少选中一条记录进行删除!");
        return;
    }
    $.messager.confirm("来自crm","确定删除选中的"+rows.length+"条记录?",function (r) {
        if(r){
            var ids="ids=";
            for(var i=0;i<rows.length;i++){
                if(i<=rows.length-2){
                    ids=ids+rows[i].id+"&ids=";
                }else{
                    ids=ids+rows[i].id;
                }
            }
            $.ajax({
                type:"post",
                url:deleteUrl,
                data:ids,
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        // 刷新datagrid
                       searchByParams();
                    }else{
                        $.messager.alert("来自crm",data.msg,"error");
                    }
                }
            })
        }
    })
}
