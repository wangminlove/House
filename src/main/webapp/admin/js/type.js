$(function () {
    $('#dd').datagrid({
        title:'房屋类型',
        url:'getType',
        toolbar:"#ToolBar",
        /*singleSelect:true,//单行选择*/
        pagination:true,  //启用分页
        fit:true,
        fitColumns:true,
        pageList:[6,9], //设置每页大小
        pageSize:3, //每页三条
        columns:[[
            {field:'ck',checkbox:'true'},
            {field:'id',title:'编号',width:100,align:"center"},
            {field:'name',title:'房屋类型',width:100,align:"center"},
            {field: 'Operation', title: '操作', width: 100,align:"center",
                formatter: function (value, row, index) {
                    var e = '<a href="#" id="delete" onclick="do_delete(' +row.id+ ')">删除</a> ';
                    return e;
                }
            }
        ]]
    });
})
function Add(id,title) {
    $("#"+id).dialog('open').dialog('setTitle', title);
}


function CloseDialog(id) {
    //关闭对话框
    $("#" + id).dialog('close')
    //打开对话框
}

//添加功能
function SaveDialog() {
    /*传统使用ajax技术实现添加
    1.获取表单对象的数据  2.使用ajax方法|post方法发送异步请求 3.处理回调用函数*/

    //2.使用easyui提交表单
    $("#ModiyDialogForm").form('submit', {
        url: "AddType",//地址
        success: function (data) {//此时data返回的json字符串
            data = $.parseJSON(data);//将json字符串转化json对象
            if (data.result == 1) {
                $.messager.alert("系统提示", "添加成功");
                $("#AddDialog").dialog('close'); //关闭
                $("#dd").datagrid('reload'); //刷新
            } else {
                $.messager.alert("系统提示", "添加失败");
            }
        }
    });
}
//修改操作
function ModifyBySelect() {
    var SelectRows = $("#dd").datagrid('getSelections');
    if (SelectRows.length != 1) {
        $.messager.alert("系统提示","请选择一行数据","info");
        return;
    }
    $("#UpdateDialog").dialog('open').dialog('setTitle',"修改房屋类型>>>>>");
    var row=SelectRows[0];
    $.getJSON(
        "getSingleType",
        {"id":row.id},
        function (data) {
            $('#UpdateDialogForm').form('load',data);
        }
    )
}
function UpdateSaveDialog() {
    $("#UpdateDialogForm").form('submit', {
        url: "UpdateType",//地址
        success: function (data) {//此时data返回的json字符串
            data = $.parseJSON(data);//将json字符串转化json对象
            if (data.result == 1) {
                $.messager.alert("系统提示", "修改成功");
                $("#UpdateDialog").dialog('close'); //关闭
                $("#dd").datagrid('reload'); //刷新
            } else {
                $.messager.alert("系统提示", "修改失败");
            }
        }
    });
}
function do_delete(id) {
    $.messager.confirm("操作提示", "您确定要执行删除吗？", function (data) {
        if (data) {
            $.getJSON(
                "deleteType",
                {"id":id},
                function (data) {
                    if (data.result==1){
                        $.messager.alert("系统提示", "删除成功");
                        $("#dd").datagrid('reload'); //刷新
                    }else {
                        $.messager.alert("系统提示", "删除失败");
                    }
                }
            )
        }
    });
}
function DeleteMoreById() {
    var SelectRows = $("#dd").datagrid('getSelections');
    if (SelectRows.length == 0) {
        $.messager.alert("系统提示","请至少选择一行数据","info");
        return;
    }
    var value="";
    for (var i = 0; i < SelectRows.length; i++) {
        value=value+SelectRows[i].id+",";
    }
    value=value.substring(0,value.length-1);
    $.messager.confirm("操作提示", "您确定要执行批量删除吗？", function (r) {
        if (r) {
            $.getJSON("deleteListType", {"id": value}, function (data) {
                if (data.result > 0) {
                    $.messager.alert("系统提示", "删除成功");
                    $("#dd").datagrid('reload'); //刷新
                } else {
                    $.messager.alert("系统提示", "删除失败");
                }
            })
        }
    });
}